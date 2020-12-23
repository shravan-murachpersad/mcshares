package mu.mcb.mcshares.stores;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mu.mcb.mcshares.models.Customer;
import mu.mcb.mcshares.repository.CustomerRepository;

@Component
public class CustomerStore {
	
	@Autowired
	private CustomerRepository customerRepository;

	public void create(Customer customer) {
		if(null == customer.getId()) {
			customer.setId(ObjectId.get());
		}
		
		customerRepository.save(customer);
	}
	
	public void update(String customerId, Customer customer) {
		//customerRepository.
	}
		
	public List<Customer> find() {
		return customerRepository.findAll();
	}
	
	public Optional<Customer> findByCustomerId(String customerId) {
		return customerRepository.findByCustomerId(customerId);
	}
	
	public List<Customer> findByContactName(String query) {	
		return customerRepository.findByContactDetailsContactName(query);
	}
}
