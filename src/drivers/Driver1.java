package drivers;
import data_storage.StoreDataIO;
import online_store_group_project.*;
import java.util.*;



public class Driver1 {
	
	public static void main(String args[]) {
		
		//Create Store  
		Store s1 = new Store(); 
		
	    //Create Customers 
		Customer c1 = new Customer(s1, "joethebro1997", "joe@example.com", "Joe", "Shmo", "joespass");
		Customer c2 = new Customer(s1, "mattthebro1997", "matt@example.com", "Matt", "Marshall", "mattyice");
		Customer c3 = new Customer(s1, "Peterthebro1997", "Peter@example.com", "Peter", "Piper", "PipingInTheName");
		Customer c4 = new Customer(s1, "Jakethebro1997", "Jake@example.com", "Jake", "Snake", "JakeTheSnake");
		Customer c5 = new Customer(s1, "Christhebro1997", "Chris@example.com", "Chris", "Haavisto", "ChrisTwoFor");
		
		//Create Owners 
		Owner o1 = new Owner(s1, "RobertSacre", "robert@example.com", "Robert", "Sacre", "SacreTheGod"); 
		Owner o2 = new Owner(s1, "ComputerGuys", "Computers@example.com", "Alexander", "Computer", "CompSciSci"); 
		Owner o3 = new Owner(s1, "SlickApparel", "FancyApparel@example.com", "Bob", "Aparrel", "CoatsShoesAndDudes"); 
		Owner o4 = new Owner(s1, "BeautySuppliers", "beautifulwomen@example.com", "Marie", "Taylor", "Beauty123"); 
		Owner o5 = new Owner(s1, "VideoGameMasters", "VideoJuegos@example.com", "Jimmy", "Carter", "CarterTown"); 
		
		//Create Categories 
		Category cg1 = new Category("Other"); 
		Category cg2 = new Category("Computers"); 
		Category cg3 = new Category("Electronic Accessories"); 
		Category cg4 = new Category("Shoes"); 
		Category cg5 = new Category("Shirts"); 
		Category cg6 = new Category("Beauty"); 
		Category cg7 = new Category("Video Games"); 
		
		//Create Items
		Item i1 = new Item(s1, o1, "American Flag" , "Large American Flag" , cg1, 16.00, 1);
		Item i2 = new Item(s1, o2, "HP Spectre" , "It has a solid state drive, sleek and fast!" , cg2, 2000.00, 2);    
		Item i3 = new Item(s1, o2, "Computer Mouse" , "Adjustable HP mouse but does not come with mouse pad" , cg3, 3.00, 8);
		Item i4 = new Item(s1, o3, "2017 Black Jordans" , "Shoes that will make you jump higher!" , cg4, 120.00, 1);
		Item i5 = new Item(s1, o3, "White T-shirt" , "Brand new White Ts" , cg5, 8.00, 7);
		Item i6 = new Item(s1, o4, "Lip Gloss" , "Bright pink lip gloss" , cg6, 20.00, 2);
		Item i7 = new Item(s1, o5, "Madden 2018" , "The newest Football Video Game on the Market!" , cg7, 50.00, 4);
		
		//Add Items to their respective owner
		o1.addItem(i1); 
		o2.addItem(i2); 
		o2.addItem(i3); 
		o3.addItem(i4); 
		o3.addItem(i5); 
		o4.addItem(i6); 
		o5.addItem(i7); 
		
		//Add All Users to the Store 
		s1.users.add(o1); 
		s1.users.add(o2);
		s1.users.add(o3);
		s1.users.add(o4);
		s1.users.add(o5);
		s1.users.add(c1); 
		s1.users.add(c2);
		s1.users.add(c3);
		s1.users.add(c4);
		s1.users.add(c5);
		
		//Add Owners to the Store 
		s1.owners.add(o1); 
		s1.owners.add(o2); 
		s1.owners.add(o3); 
		s1.owners.add(o4); 
		s1.owners.add(o5); 
		
		//Add Customers to the Store 
		s1.customers.add(c1);
		s1.customers.add(c2);
		s1.customers.add(c3);
		s1.customers.add(c4);
		s1.customers.add(c5);
		
		//Testing all functions in the Store Class 
		System.out.println("Test for getting the Categories, Items in a Category, all Items, and featured Items:"); 
		System.out.println(""); 
		
	    ArrayList<Category> Categories = new ArrayList<Category>(); 
	    ArrayList<Item> Items = new ArrayList<Item>(); 
	    
		Categories = s1.getCategories(); 
		Collections.sort(Categories);
		
	    System.out.println("Print All Categories:"); 
	    for(int i = 0; i < Categories.size(); i++) { 
	    System.out.println(Categories.get(i).getName()); }  
	    System.out.println(""); 
	    
		Items = s1.getItemsWithCategory(cg2); 
	    
		System.out.println("Print Items in the Category-> Computers:"); 
	    for(int i = 0; i < Items.size(); i++) {
	    System.out.println(Items.get(i).getName());
	    }
	    System.out.println(""); 
		
	    Items = s1.getAllItems(); 
	    
	    System.out.println("Print All Items:"); 
	    for(int i = 0; i < Items.size(); i++) {
	    System.out.println(Items.get(i).getName());
	    }
	    System.out.println(""); 
	    
	    Items = s1.getFeaturedItems(); 
	    
	    System.out.println("Print Featured Items:"); 
	    for(int i = 0; i < Items.size(); i++) {
	    System.out.println(Items.get(i).getName());
	    }
	    System.out.println(""); 
	    
	    //Test User Exists Function in Store 
	    System.out.println("Testing already taken user email address and username:");
	    
	    Customer c6 = new Customer(s1, "joethebro1997", "joe@example.com", "Joe", "Shmo", "joespass");
	    System.out.println(""); 
	    
	    //Test Item Exists Function in Store 
	    System.out.println("Testing item with same name:");
	    
	    Item i8 = new Item(s1, o5, "Madden 2018" , "The newest Football Video Game on the Market!" , cg7, 50.00, 4);
	    System.out.println(""); 
	    
	    //Testing Sorting Featured Items Alphabetically 
	    Collections.sort(Items);  
	    
	    System.out.println("Sorted Featured Items:"); 
	    for(int i = 0; i < Items.size(); i++) {
	    System.out.println(Items.get(i).getName());
	    }
	    System.out.println(""); 

		System.out.println(StoreDataIO.storeUserData(c1));
	}

	
	
}
