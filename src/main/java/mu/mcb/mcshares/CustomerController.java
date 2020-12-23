package mu.mcb.mcshares;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mu.mcb.mcshares.models.Customer;
import mu.mcb.mcshares.models.EventLog;
import mu.mcb.mcshares.objects.CustomersResponse;
import mu.mcb.mcshares.objects.Response;
import mu.mcb.mcshares.objects.xml.RequestDoc;
import mu.mcb.mcshares.stores.CustomerStore;
import mu.mcb.mcshares.stores.EventLogStore;
import mu.mcb.mcshares.utils.RequestDocXmlParser;
import mu.mcb.mcshares.utils.Utils;

@RestController
@RequestMapping("/secure")
public class CustomerController {
	
	@Autowired
	private CustomerStore customerStore;
	
	@Autowired
	private EventLogStore eventLogStore;
	
	private static final String CUSTOMER_CREATE_EVENT = "CUSTOMER_CREATE";
//	private static final String CUSTOMER_UPDATE_EVENT = "CUSTOMER_UPDATE"; 

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
	public Response createCustomer(@RequestBody Customer customer) {
		Response response = new Response();
		
		if(!isCustomerProfileValid(customer, response)) {
			// Save to error table
			EventLog eventLog = new EventLog();
			eventLog.setCustomerId(customer.getCustomerId());
			eventLog.setRequestDate(String.valueOf(LocalDate.now()));
			eventLog.setRequestType(CUSTOMER_CREATE_EVENT);
			eventLog.setErrorDescription(response.getResponseDescription());
			eventLogStore.create(eventLog);
			
			return response;
		}
		
		customerStore.create(customer);
		
		response.setResponseCode(Errors.SUCCESS.getResponseCode());
		response.setResponseDescription(Errors.SUCCESS.getResponseDescription());
		
		return response;
    }
	
	@PostMapping("/bulkCreateCustomer")
	public Response bulkCreateCustomer(@RequestParam("registrationFile") MultipartFile registrationFile) {
		Response response = new Response();
		
		try {
			InputStream requestDocStream = registrationFile.getInputStream();
			
			RequestDocXmlParser requestDocParser = new RequestDocXmlParser();
			RequestDoc requestDoc = requestDocParser.parse(requestDocStream);
			
			List<Customer> customerList = requestDoc.getDocData().getDataItem();
			
			for(Customer customer : customerList) {
				if(!isCustomerProfileValid(customer, response)) {
					EventLog eventLog = new EventLog();
					eventLog.setCustomerId(customer.getCustomerId());
					eventLog.setRequestDate(String.valueOf(LocalDate.now()));
					eventLog.setRequestType(CUSTOMER_CREATE_EVENT);
					eventLog.setErrorDescription(response.getResponseDescription());
					eventLogStore.create(eventLog);
				} else {
					customerStore.create(customer);
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		response.setResponseCode(Errors.SUCCESS.getResponseCode());
		response.setResponseDescription(Errors.SUCCESS.getResponseDescription());
		
		return response;
    }
	
	@PutMapping("/customer")
	public String updateCustomer() {
        return "Hello World!";
    }
        
	private boolean isCustomerProfileValid(Customer customerProfile, Response response) {
		boolean isValid = true;
		
		String customerType = customerProfile.getCustomerType();
		
		if(null == customerType) {
			response.setResponseCode(Errors.INVALID_CUSTOMER_TYPE.getResponseCode());
			response.setResponseDescription(Errors.INVALID_CUSTOMER_TYPE.getResponseDescription());
			
			isValid = false;
			
			return isValid;
		}
		
		if(customerType.equalsIgnoreCase("individual")) {
			// validating birth date.
			
			if(customerProfile.getBirthDate().isEmpty()) {
				response.setResponseCode(Errors.INVALID_CUSTOMER_AGE_MISSING.getResponseCode());
				response.setResponseDescription(Errors.INVALID_CUSTOMER_AGE_MISSING.getResponseDescription());
				
				isValid = false;
				
				return isValid;
			}
			
			DateTimeFormatter birthDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate birthDate = LocalDate.parse(customerProfile.getBirthDate(), birthDateFormatter);
			LocalDate now = LocalDate.now();
			
			int age = Utils.calculateAge(birthDate, now);
			
			if(age < 18) {
				response.setResponseCode(Errors.INVALID_CUSTOMER_AGE.getResponseCode());
				response.setResponseDescription(Errors.INVALID_CUSTOMER_AGE.getResponseDescription());
				
				isValid = false;
			}
			
			// validating number of shares.
			int numberOfShares = customerProfile.getSharesDetails().getNumShares();
			
			if(numberOfShares < 0) {
				response.setResponseCode(Errors.INVALID_CUSTOMER_SHARES_AMOUNT.getResponseCode());
				response.setResponseDescription(Errors.INVALID_CUSTOMER_SHARES_AMOUNT.getResponseDescription());
				
				isValid = false;
			}
			
			// validating share price.
			Double sharePrice = customerProfile.getSharesDetails().getSharePrice();
			
			if(sharePrice < 0) {
				response.setResponseCode(Errors.INVALID_CUSTOMER_SHARES_PRICE.getResponseCode());
				response.setResponseDescription(Errors.INVALID_CUSTOMER_SHARES_PRICE.getResponseDescription());
				
				isValid = false;
			}
		}
		
		return isValid;
	}
}
