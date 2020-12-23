package mu.mcb.mcshares.models;

import java.time.Instant;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Entity {
	
	@Id
	private ObjectId _id;
	
	@CreatedDate
	@JsonIgnore
	@Field()
	private Instant createdDate = Instant.now();
	
	/**
	 * @return the Id
	 */
	public String getId() {
		if(null != this._id) {
			return this._id.toHexString();
		} else {
			return null;
		}
	};

	/**
	 * @return set the Id
	 */
	public void setId(ObjectId id) {
		this._id = id;
	};

	/**
	 * @return the createdDate
	 */
	public Instant getCreatedDate() {
		return createdDate;
	}

}
