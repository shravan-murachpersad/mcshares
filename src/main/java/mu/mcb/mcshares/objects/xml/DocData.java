package mu.mcb.mcshares.objects.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import mu.mcb.mcshares.models.Customer;

public class DocData {
	private List<Customer> dataItem;
	
	@XmlElement(name = "DataItem_Customer")
	public List<Customer> getDataItem() {
		return dataItem;
	}

	public void setDataItem(List<Customer> dataItem_Customer) {
		dataItem = dataItem_Customer;
	}
}
