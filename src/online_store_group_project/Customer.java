package online_store_group_project;

import java.util.ArrayList;

public class Customer extends User {
	
	private String shippingAddress;
	private ArrayList<Order> orders;
	
	public Customer(Store store, String username, String emailAddress, String firstName, String lastName, String password) {
		super(store, username, emailAddress, firstName, lastName, password);
		this.orders = new ArrayList<Order>();
	}
	
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	
	public String getShippingAddress() {
		return this.shippingAddress;
	}
	
	public int getNumberOfOrders() {
		return this.orders.size();
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public void removeOrder(Order order) {
		orders.remove(order);
	}
}
