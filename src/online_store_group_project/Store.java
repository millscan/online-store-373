package online_store_group_project;

import java.util.ArrayList;
import java.util.Collections;

public class Store {
 
	private ArrayList<User> users;
	private ArrayList<Transaction> transactions;
	private String url;
	
	public ArrayList<Item> getItemsWithCategory(String category){
		ArrayList<Item> items = new ArrayList<Item>();
		//TODO: iterate through owners, adding all items with category given
		return items;
	}
	
	public ArrayList<String> getCategories(){
		ArrayList<String> categories = new ArrayList<String>();
		//TODO: iterate through owners, adding new categories to categories ArrayList
		return categories;
	}
	
	public ArrayList<Item> getAllItems(){
		ArrayList<Item> items = new ArrayList<Item>();
		//TODO: iterate through owners, adding each item to list
		return items;
	}
	
	public ArrayList<User> getUsers(){
		return users;
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
}
