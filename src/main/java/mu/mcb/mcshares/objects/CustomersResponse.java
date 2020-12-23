package mu.mcb.mcshares.objects;

import java.util.List;

import mu.mcb.mcshares.models.Customer;

public class CustomersResponse extends Response {
	private List<Customer> customers;

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
}
