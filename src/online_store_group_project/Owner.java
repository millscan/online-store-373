package online_store_group_project;

import java.util.ArrayList;

public class Owner extends User {
	
	private ArrayList<Item> items;
	private ArrayList<Rating> ratings;
	private ArrayList<Order> receivedOrders;
	
	public Owner(Store store, String username, String emailAddress, String firstName, String lastName, String password) {
		super(store, username, emailAddress, firstName, lastName, password);
		this.items = new ArrayList<Item>();
		this.ratings = new ArrayList<Rating>();
		this.receivedOrders = new ArrayList<Order>();
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
		
		if(store.items.contains(item)) {
			System.out.println("Item already exists. Not added to store");
			return;
		}
		if(store.itemNameTaken(item.getId())) {
			System.out.println("Item name taken, not added to the store");
			return;
		}
		store.items.add(item);
		this.items.add(item);
	}
	
	public void removeItem(Item item) {
		this.items.remove(item);
		store.items.remove(item);
	}

	public ArrayList<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(ArrayList<Rating> ratings) {
		this.ratings = ratings;
	}

	public ArrayList<Order> getReceivedOrders() {
		return receivedOrders;
	}

	public void setReceivedOrders(ArrayList<Order> receivedOrders) {
		this.receivedOrders = receivedOrders;
	}

}
