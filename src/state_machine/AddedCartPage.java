package state_machine;

import java.util.ArrayList;
import java.util.Scanner;

import online_store_group_project.Item;

public class AddedCartPage extends Page {

	private int Identifier = 7;   
	
	 
	public void nextPage(PageState pageState) {
    ArrayList<Item> cart = pageState.getCart(); 
	Scanner reader = new Scanner(System.in);
	
	System.out.println("Item Was Successfully Added to your Cart");
	System.out.println("");
	
	
	System.out.println("Type 'view' to view your cart or 'checkout' to begin checking out:");
    
	while(true) {
	String input = reader.nextLine();
	
	if (input.equals("home")) {
	pageState.setPage(new HomePage());	
	return;
	}
	
	else {
		if (input.equals("back")) {
		pageState.setPage(pageState.getPreviouspage());	
		return;
		}
		
		else { 
		 if(input.equals("view")) {
	     		 
		 }
		 
		 else if(input.equals("checkout")) {
			 
		 }
		 else {
	     System.out.println("Invalid Entry. Please Try Again");	
		 }	
		} 
	  }
	}
 }
	
	
	
	public int getIdentifier() {
		return Identifier; 
	}
	
	
}