package mu.mcb.mcshares.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mu.mcb.mcshares.models.EventLog;

public interface EventLogRepository extends MongoRepository<EventLog, String> {
	
}
