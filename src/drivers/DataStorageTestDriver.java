package drivers;

import online_store_group_project.*;

import java.util.ArrayList;

import data_storage.*;

public class DataStorageTestDriver {

	public static void main(String[] args) {
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
		
		//Saving users in users.csv
		
		ArrayList<User> usersToSave = new ArrayList<User>();
		usersToSave.add(c1);
		usersToSave.add(c2);
		usersToSave.add(c3);
		usersToSave.add(c4);
		usersToSave.add(c5);
		usersToSave.add(o1);
		usersToSave.add(o2);
		usersToSave.add(o3);
		usersToSave.add(o4);
		usersToSave.add(o5);
		
		for(User u : usersToSave) {
			StoreDataIO.storeUserData(s1, u);
		}
		
		ArrayList<User> loadedUsers = StoreDataIO.LoadUsers(s1);
		System.out.println("Printing users:");
		for(User u : loadedUsers) {
			System.out.println(u.getUsername());
		}
	}

}
