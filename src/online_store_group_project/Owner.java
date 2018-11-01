package online_store_group_project;

import java.util.ArrayList;

public class Owner extends User {
	
	private ArrayList<Item> items;
	private ArrayList<Rating> ratings;
	private ArrayList<Order> receivedOrders;
	
	public Owner() {
		super();
		this.items = new ArrayList<Item>();
		this.ratings = new ArrayList<Rating>();
		this.receivedOrders = new ArrayList<Order>();
	}
	
	public Owner(String username, String emailAddress, String firstName, String lastName, String password) {
		super(username, emailAddress, firstName, lastName, password);
		this.items = new ArrayList<Item>();
		this.ratings = new ArrayList<Rating>();
		this.receivedOrders = new ArrayList<Order>();
	}

}
