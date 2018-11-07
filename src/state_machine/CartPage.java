package state_machine;

import java.util.ArrayList;
import java.util.Scanner;

import online_store_group_project.Item;

public class CartPage extends Page {

	private int Identifier = 8;   
	
	 
	public void nextPage(PageState pageState) {
    ArrayList<Item> cart = pageState.getCart(); 
	Scanner reader = new Scanner(System.in);
	
    
	while(true) {
	String input = reader.nextLine();
	System.out.println("These are all the items in your cart with prices:");
	
	
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
			
			
			
		pageState.setPreviousPage(this);	
		} 
	  }
	}
 }
	
	
	
	public int getIdentifier() {
		return Identifier; 
	}
	
	
	
	
}
