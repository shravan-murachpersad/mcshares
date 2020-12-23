package mu.mcb.mcshares.models;

import javax.xml.bind.annotation.XmlElement;

public class MailingAddress { 
	private String addressLine1;
	private String addressLine2;
	private String townCity;
	private String country;
	
	@XmlElement(name = "Address_Line1")
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	@XmlElement(name = "Address_Line2")
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	@XmlElement(name = "Town_City")
	public String getTownCity() {
		return townCity;
	}
	public void setTownCity(String townCity) {
		this.townCity = townCity;
	}
	
	@XmlElement(name = "Country")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
