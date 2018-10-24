package online_store_group_project;

import java.util.ArrayList;

public class Customer extends User {
	
	private String shippingAddress;
	private ArrayList<Order> orders;
	
	public Customer() {
		this.orders = new ArrayList<Order>();
	}
	
	public Customer(String username) {
		this.orders = new ArrayList<Order>();
		this.setUsername(username);
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
