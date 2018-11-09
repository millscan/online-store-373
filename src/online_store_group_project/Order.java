package online_store_group_project;

import java.util.Date;
import java.util.UUID;

import data_storage.StoreDataIO;

import java.util.ArrayList;

public class Order {

	private String id;
	private Store store;
	private Date timestamp;
	private boolean shipped;
	private Owner seller;
	private Customer buyer;
	private ArrayList<Item> items;

	public Order(Store store) {
		this.store = store;
		this.store.orders.add(this);
		this.id = UUID.randomUUID().toString();
		this.items = new ArrayList<Item>();
		this.timestamp = new Date();
		this.shipped = false;
	}
	
	public Order(Store store, Owner seller, Customer buyer, ArrayList<Item> items, Date timestamp, boolean shipped) {
		this.store = store;
		this.store.orders.add(this);
		this.id = UUID.randomUUID().toString();
		this.seller = seller;
		this.buyer = buyer;
		this.items = items;
		this.timestamp = timestamp;
		this.shipped = shipped;
	}
	
	public Order(Store store, Owner seller, Customer buyer, boolean shipped) {
		this.store = store;
		this.store.orders.add(this);
		this.id = UUID.randomUUID().toString();
		this.seller = seller;
		this.buyer = buyer;
		this.items = new ArrayList<Item>();
		this.timestamp = new Date();
		this.shipped = shipped;
	}
	
	//USED FOR LOADING DATA INTO ORDER FROM FILE
	public Order(Store store, String id, Owner seller, Customer buyer, ArrayList<Item> items, Date timestamp, boolean shipped) {
		this.store = store;
		this.store.orders.add(this);
		this.id = id;
		this.items = items;
		this.timestamp = timestamp;
		this.shipped = shipped;
	}
	
	public String getID() {
		return this.id;
	}


	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public void setBuyer(Customer c) {
		this.buyer = c;
	}
	
	public Customer getBuyer() {
		return this.buyer;
	}
	
	public void setSeller(Owner o) {
		this.seller = o;
	}
	
	public Owner getSeller() {
		return this.seller;
	}
	
	public ArrayList<Item> getItems(){
		return this.items;
	}
	
	public Date getTimestamp() {
		return this.timestamp;
	}
	
	public void ship() {
		this.shipped = true;
		//remove item from Owners list of items!
	}
	
	public boolean getShippedStatus() {
		return this.shipped;
	}
	
}
