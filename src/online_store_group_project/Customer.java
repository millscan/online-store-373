package online_store_group_project;

import java.util.ArrayList;

public class Customer extends User {
	
	private String shippingAddress;
	private ArrayList<Order> orders;
	private ArrayList<Item> cart;
	
	public Customer(Store store, String username, String emailAddress, String firstName, String lastName, String password) {
		super(store, username, emailAddress, firstName, lastName, password);
		this.orders = new ArrayList<Order>();
		this.cart = new ArrayList<Item>();
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
	
	public ArrayList<Order> getOrders(){
		return this.orders;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public void removeOrder(Order order) {
		orders.remove(order);
	}
	
	public void addToCart(Item item) {
		cart.add(item);
	}
	
	public void removeFromCart(Item item) {
		cart.remove(item);
	}
	
	public ArrayList<Item> getCart(){
		return cart;
	}
	
	public void viewCart() {
		for (int i = 0; i < cart.size(); i++) {
			System.out.println(cart.get(i).getName() + " :$" + cart.get(i).getPrice() + "\n");
		}
	}
}
