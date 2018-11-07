package online_store_group_project;

import java.util.ArrayList;
import java.util.Collections;

public class Store {
 
	public ArrayList<Customer> customers = new ArrayList<Customer>();
	public ArrayList<Owner> owners = new ArrayList<Owner>(); 
	public ArrayList<User> users = new ArrayList<User>(); 
	public ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Transaction> transactions;
	private String url;
	
	
	//Setters and Getters 
	public ArrayList<Customer> getCustomers(){
		return customers;
	}
	
	public ArrayList<Owner> getOwners(){
		return owners;
	}
	
	public ArrayList<User> getUsers(){
		return users; 
	}
	

	
	//Alphabetical Sort Function 
	/*
	Collections.sort(users, new Comparator<User>() {
	    public int compare(User user1, User user2) {
	        return user1.getFirstName().compareTo(user2.getFirstName());
	    }
	});  */ 
	
	
    //All getter functions to display the items in the store//

	//Get All the Categories contained in the store 
	public ArrayList<String> getCategories(){
		ArrayList<String> categoryNames = new ArrayList<String>();
		for(Item i : this.items) {
			if(!categoryNames.contains(i.getCategory())) {
				categoryNames.add(i.getCategory());
			}
		}
		
		return categoryNames;
	}
	
	//Get all the items contained in the store 
	public ArrayList<Item> getAllItems(){
		return this.items;
	}
	
	//Get all the featured items contained in the store 
	//this is dope
	public ArrayList<Item> getFeaturedItems() { //get 8 random items to display
		ArrayList<Item> featuredItems = getAllItems();
		if(featuredItems.size() < 6) {
			return featuredItems;
		}
		else {
			Collections.shuffle(featuredItems);
			return new ArrayList<Item>(featuredItems.subList(0, 6));
		}
	}
		
	//Verification Methods// 
	
	//Make sure username and emailaddress are valid 
	public boolean userExists(String emailAddress) {
		int i = 0;
		while(i < this.getUsers().size()) {
			if (this.getUsers().get(i).getEmailAddress() == emailAddress) {
				System.out.println("Email Address is already in use. Please use a different email address.");
				return true;
			}
			else if(this.getUsers().get(i).getUsername() == emailAddress) {
				System.out.println("Username is already in use. Please use a different username.");
				return true;
			}
			i++;
		}
		return false;
	}
	
	//Make sure item name is valid 
	public boolean itemNameTaken(String itemName) {
		 for(Item i : this.items) {
		     if(i.getName() == itemName) {
		     System.out.println("Item Name is already in use. Please chose a different name.");  
		     return true; 
		  }
		}
		return false;
	}
	
	
	// SEARCHING/BROWSING ITEMS
	
	//SEARCH ALL ITEMS
	public ArrayList<Item> searchItems(String itemName){
		ArrayList<Item> searchResults = new ArrayList<Item>();
		for(Item item : this.items) {
			if(item.getName().contains(itemName)) {
				searchResults.add(item);
			}
		}
		return searchResults;
	}
	
	//GET ITEMS IN GIVEN CATEGORY
	public ArrayList<Item> getItemsInCategory(String categoryName) {
		ArrayList<Item> categoryItems = new ArrayList<Item>();
		 for(Item item : this.items) {
			 if(item.getCategory().equals(categoryName)) {
				 categoryItems.add(item);
			 }
		 }
		 return categoryItems; 
	}
	
	//GET ITEMS IN GIVEN CATEGORY WITH SEARCH TERM
	public ArrayList<Item> SearchItemsInCategory(String categoryName, String itemName) {
		
		//get category items
		ArrayList<Item> searchResults = getItemsInCategory(categoryName);
		 for(Item categoryItem : searchResults) {
			 //if item not included in search, remove from items
			 if(!categoryItem.getName().contains(itemName)) {
				 searchResults.remove(categoryItem);
			 }
		 }
		 return searchResults; 
	}
	
	
	//Print Categories
	public void PrintCategories(ArrayList<Category> Categories) {
		for(int i = 0; i < Categories.size(); i++) {
			System.out.println(Categories.get(i).getName());
		}
	}
	
	//Print All items in a specific Category
	public void PrintItemsInCategory(Category cg1) {
	    for(int i = 0; i < cg1.getItems().size(); i++) {
	    	System.out.println(cg1.getItems().get(i).getName()); 
	    }
	}
	
	
}
