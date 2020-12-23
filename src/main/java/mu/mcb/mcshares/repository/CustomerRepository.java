package mu.mcb.mcshares.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mu.mcb.mcshares.models.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
