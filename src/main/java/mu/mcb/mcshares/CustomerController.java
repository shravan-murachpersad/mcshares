package mu.mcb.mcshares;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mu.mcb.mcshares.models.Customer;
import mu.mcb.mcshares.objects.CustomersResponse;
import mu.mcb.mcshares.objects.Response;
import mu.mcb.mcshares.objects.xml.RequestDoc;
import mu.mcb.mcshares.stores.CustomerStore;
import mu.mcb.mcshares.utils.RequestDocXmlParser;

@RestController
@RequestMapping("/secure")
public class CustomerController {
	
	@Autowired
	private CustomerStore customerStore;

	@GetMapping("/customer")
    public CustomersResponse getCustomers() {
		CustomersResponse response = new CustomersResponse();
		
		List<Customer> customerList = customerStore.find();
		response.setCustomers(customerList);
		response.setResponseCode(Errors.SUCCESS.getResponseCode());
		response.setResponseDescription(Errors.SUCCESS.getResponseDescription());
		
        return response;
    }
	
	@GetMapping("/customer/{customerId}")
    public CustomersResponse getCustomerByCustomerId(@PathVariable String customerId) { 
		CustomersResponse response = new CustomersResponse();
		
		Optional<Customer> customer = customerStore.findByCustomerId(customerId);
		
		List<Customer> customerList = new ArrayList<Customer>();
		customerList.add(customer.get());
		
		response.setCustomers(customerList);
		response.setResponseCode(Errors.SUCCESS.getResponseCode());
		response.setResponseDescription(Errors.SUCCESS.getResponseDescription());
		
        return response;
    }
	
	@GetMapping("/search")
    public CustomersResponse searchCustomers(@RequestParam String q) {
		CustomersResponse response = new CustomersResponse();
		
		List<Customer> customerList = customerStore.findByContactName(q);
		response.setCustomers(customerList);
		response.setResponseCode(Errors.SUCCESS.getResponseCode());
		response.setResponseDescription(Errors.SUCCESS.getResponseDescription());
		
        return response;
    }
	
	@PostMapping("/customer")
	public Response createCustomer(Customer customer) {
		if(!isCustomerProfileValid(customer)) {
			// send error code
		}
		
		customerStore.create(customer);
		
		Response response = new Response();
		
		return response;
    }
	
	@PostMapping("/bulkCreateCustomer")
	public Response bulkCreateCustomer(@RequestParam("registrationFile") MultipartFile registrationFile) {
		try {
			InputStream requestDocStream = registrationFile.getInputStream();
			
			RequestDocXmlParser requestDocParser = new RequestDocXmlParser();
			RequestDoc requestDoc = requestDocParser.parse(requestDocStream);
			
			List<Customer> customerList = requestDoc.getDocData().getDataItem();
			
			for(Customer customer : customerList) {
				if(!isCustomerProfileValid(customer)) {
					// send error code
				}
				
				customerStore.create(customer);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Response response = new Response();
		
		return response;
    }
	
	@PutMapping("/customer")
	public String updateCustomer() {
        return "Hello World!";
    }
        
	private boolean isCustomerProfileValid(Customer customerProfile) {
		String customerType = customerProfile.getCustomerType();
		
		if(null == customerType) {
	
		}
		
		customerType = customerType.toLowerCase();
		
		return true;
	}
}
