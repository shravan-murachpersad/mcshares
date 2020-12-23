package mu.mcb.mcshares.models;

import javax.xml.bind.annotation.XmlElement;

public class ContactDetail { 
	private String contactName;
	private String contactDetail;
	
	@XmlElement(name = "Contact_Name")
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	@XmlElement(name = "Contact_Number")
	public String getContactDetail() {
		return contactDetail;
	}
	public void setContactDetail(String contactDetail) {
		this.contactDetail = contactDetail;
	}
}
