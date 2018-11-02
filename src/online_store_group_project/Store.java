package online_store_group_project;

import java.util.ArrayList;
import java.util.Collections;

public class Store {
 
	private ArrayList<User> users = new ArrayList<User>();
	private ArrayList<Owner> owners; 
	private ArrayList<Transaction> transactions;
	private String url;
	
	
	//Alphabetical Sort Function 
	/*
	Collections.sort(users, new Comparator<User>() {
	    public int compare(User user1, User user2) {
	        return user1.getFirstName().compareTo(user2.getFirstName());
	    }
	});  */ 
	
	
	public ArrayList<Item> getItemsWithCategory(String category){
		ArrayList<Item> items = new ArrayList<Item>();
		for(int i = 0; i < owners.size(); i++) {
			for(int j = 0; j < owners.get(i).getItems().size(); j++) {
				if(owners.get(i).getItems().get(j).getCategory() == category) {
				 items.add(owners.get(i).getItems().get(j)); }}}
		
		//Implement the Alphabetical Sort Function
		
		return items;
	}
	
	public ArrayList<String> getCategories(){
		ArrayList<String> categories = new ArrayList<String>();
		boolean NewCategory = true; 
	   
		for(int i = 0; i < owners.size(); i++) {  //Iterate through all owners
	       for(int j = 0; j < owners.get(i).getItems().size(); j++) { //Iterate through all items of specific owner
	    	  String Category = owners.get(i).getItems().get(j).getCategory(); 
	    	  if(!categories.isEmpty()) {  //Check if it is not the first Category to be added
	    			  
	    		for(int k = 0; k < categories.size(); k++) { //Iterate through all existing categories
	    		  if(categories.get(k) == Category) {  //if Category already exists in list 
	    	    	  NewCategory = false; }}
	    		
	    		  if(NewCategory) {  //If Category does not already exist in the list
	    		    categories.add(Category);}
	    	  		  NewCategory = true;} //Reset Category boolean
	    	  else { categories.add(Category); }}} //First Category to be added 
		
		return categories;
	}
	
	public ArrayList<Item> getAllItems(){
		ArrayList<Item> items = new ArrayList<Item>();
		//TODO: iterate through owners, adding each item to list
		return items;
	}
	
	
	//get 8 random items to display
	public ArrayList<Item> getFeaturedItems() {
		ArrayList<Item> featuredItems = getAllItems();
		if(featuredItems.size() < 9) {
			return featuredItems;
		}
		else {
			Collections.shuffle(featuredItems);
			return new ArrayList<Item>(featuredItems.subList(0, 8));
		}
	}
}
