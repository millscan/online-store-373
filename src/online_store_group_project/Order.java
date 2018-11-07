package online_store_group_project;

import java.util.Date;
import java.util.UUID;
import java.util.ArrayList;

public class Order {

	private String id;
	private ArrayList<Item> items;
	private Date timestamp;
	private boolean shipped;

	public Order() {
		id = UUID.randomUUID().toString();
		this.items = new ArrayList<Item>();
		this.timestamp = new Date();
		this.shipped = false;
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
