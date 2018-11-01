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
