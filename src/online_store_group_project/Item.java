package online_store_group_project;

import java.util.UUID;

public class Item implements Comparable<Item> {
	
	private Store store; 
	private String id;
	private String name;
	private String description;
	private Category category;
	private double price;
	private int quantity;
	private Owner seller;
	
	
	//Constructor
	public Item(Store store, Owner seller, String name, String description, Category category, double price, int quantity) {
		
		if(!store.itemExists(name)) {
		this.id = UUID.randomUUID().toString();
		this.store = store; 
		this.seller = seller;
		this.name = name;
		this.description = description;
		this.category = category;
		category.addItem(this); 
		this.price = price;
		this.quantity = quantity;
		}
		
		else {
	    System.out.println("Item not created");	
		}
	}
	
	//Constructor for creating items with stored data
	public Item(String id, Store store, Owner seller, String name, String description, Category category, double price, int quantity) {
		
		if(!store.itemExists(name)) {
		this.id = id;
		this.store = store; 
		this.seller = seller;
		this.name = name;
		this.description = description;
		this.category = category;
		category.addItem(this); 
		this.price = price;
		this.quantity = quantity;
		}
		
		else {
	    System.out.println("Item not created");	
		}
	}
	
	
	public String getID() {
		return this.id;
	}
	
	//Getters and Setters 
	public Owner getSeller() {
		return seller;
	}

	public void setSeller(Owner seller) {
		this.seller = seller;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	//Over-riding compare method to sort Items alphabetically by name
	public int compareTo(Item i1) {
	
		return this.getName().compareTo(i1.getName());
	}	
		
}
