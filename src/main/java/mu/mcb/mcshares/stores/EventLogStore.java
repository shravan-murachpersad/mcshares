package mu.mcb.mcshares.stores;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mu.mcb.mcshares.models.EventLog;
import mu.mcb.mcshares.repository.EventLogRepository;

@Component
public class EventLogStore {
	
	@Autowired
	private EventLogRepository eventLogRepository;

	public void create(EventLog eventLog) {
		if(null == eventLog.getId()) {
			eventLog.setId(ObjectId.get());
		}
		eventLogRepository.save(eventLog);
	}
}
