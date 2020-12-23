package mu.mcb.mcshares.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import mu.mcb.mcshares.models.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
	
	@Query("{'contactDetails.contactName' : {$regex : ?0}}")
    public List<Customer> findByContactDetailsContactName(String username);
	
	public Optional<Customer> findByCustomerId(String customerId);
}
