package mu.mcb.mcshares.models;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)  
public class Customer extends Entity{
	
	@Indexed(unique = true)
	@NotNull()
	private String customerId;
	
	@Field()
	private String customerType;
	
	@Field()
	private String birthDate;
	
	@Field()
	private String dateIncorp;
	
	@Field()
	private String registrationNo;
	
	@Field()
	private MailingAddress mailingAddress;
	
	@Field()
	private ContactDetail contactDetails;
	
	@Field()
	private ShareDetail sharesDetails;

	@XmlElement(name = "customer_id")
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@XmlElement(name = "Customer_Type")
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	@XmlElement(name = "Date_Of_Birth")
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@XmlElement(name = "Date_Incorp")
	public String getDateIncorp() {
		return dateIncorp;
	}

	public void setDateIncorp(String dateIncorp) {
		this.dateIncorp = dateIncorp;
	}

	@XmlElement(name = "Registration_No")
	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	@XmlElement(name = "Mailing_Address")
	public MailingAddress getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(MailingAddress mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	@XmlElement(name = "Contact_Details")
	public ContactDetail getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(ContactDetail contactDetails) {
		this.contactDetails = contactDetails;
	}

	@XmlElement(name = "Shares_Details")
	public ShareDetail getSharesDetails() {
		return sharesDetails;
	}

	public void setSharesDetails(ShareDetail sharesDetails) {
		this.sharesDetails = sharesDetails;
	}
}
