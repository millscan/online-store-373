package online_store_group_project;

import java.util.ArrayList;
import java.util.Collections;

public class Store {
 
	public ArrayList<Customer> customers = new ArrayList<Customer>();
	public ArrayList<Owner> owners = new ArrayList<Owner>(); 
	public ArrayList<User> users = new ArrayList<User>(); 
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
	
	public ArrayList<Item> getItemsWithCategory(Category category){
		ArrayList<Item> items = new ArrayList<Item>();
		for(int i = 0; i < owners.size(); i++) {
			for(int j = 0; j < owners.get(i).getItems().size(); j++) {
				if(owners.get(i).getItems().get(j).getCategory().getName() == category.getName()) {
				 items.add(owners.get(i).getItems().get(j)); }}}
		
		//Implement the Alphabetical Sort Function
		
		return items;
	}
	
	//Get All the Categories contained in the store 
	public ArrayList<Category> getCategories(){
		ArrayList<Category> categories = new ArrayList<Category>();
		boolean NewCategory = true; 
	   
		for(int i = 0; i < owners.size(); i++) {  //Iterate through all owners
	       for(int j = 0; j < owners.get(i).getItems().size(); j++) { //Iterate through all items of specific owner
	    	  Category category = owners.get(i).getItems().get(j).getCategory(); 
	    	  if(!categories.isEmpty()) {  //Check if it is not the first Category to be added
	    			  
	    		for(int k = 0; k < categories.size(); k++) { //Iterate through all existing categories
	    		  if(categories.get(k).getName() == category.getName()) {  //if Category already exists in list 
	    	    	  NewCategory = false; }}
	    		
	    		  if(NewCategory) {  //If Category does not already exist in the list
	    		    categories.add(category);}
	    	  		  NewCategory = true;} //Reset Category boolean
	    	  else { categories.add(category); }}} //First Category to be added 
		
		return categories;
	}
	
	//Get all the items contained in the store 
	public ArrayList<Item> getAllItems(){
		ArrayList<Item> items = new ArrayList<Item>();
		for(int i = 0; i < owners.size(); i++) {  //Iterate through all owners
		     for(int j = 0; j < owners.get(i).getItems().size(); j++) { //Iterate through all items of specific owner
		    	 items.add(owners.get(i).getItems().get(j)); }}   //Add Item to the list 
		return items;
		}
	
	//Get all the featured items contained in the store 
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
	public boolean itemExists(String itemName) {
		 ArrayList<Item> itemList =  this.getAllItems();
		 for(int i = 0; i < itemList.size(); i++) {
		     if(itemList.get(i).getName() == itemName) {
		     System.out.println("Item Name is already in use. Please chose a different name.");  
		     return true; 
		  }
		}
		return false;
	}
	
}
