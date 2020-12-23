package mu.mcb.mcshares.stores;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
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
		
	public Optional<Customer> findById(String _id) {
		return customerRepository.findById(_id);
	}
	
	public List<Customer> find() {
		return customerRepository.findAll();
	}
	
	public List<Customer> find(String project) {
		Query query = new Query();
		
		String[] projectionFields = project.split(",");
		
		for(int i=0; i<projectionFields.length; i++) {
			query.fields().include(projectionFields[i]);
		}
		
		return customerRepository.findAll();
	}
}
