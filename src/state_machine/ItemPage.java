package state_machine;

import java.util.Scanner;
import online_store_group_project.*;

public class ItemPage extends Page{

	private int Identifier = 6;   
	
	 
	public void nextPage(PageState pageState) {
	
	
	Scanner reader = new Scanner(System.in);
	
	Item item = pageState.getItem(); 
	
	pageState.getStore().PrintItemDetails(item); 
	
	while(true) {
	System.out.println("Do you want to add " + item.getName() + " to your cart? (yes/no):");	
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
	
	if (input.equals("yes")) {
	pageState.getCart().add(item); 
	
	pageState.setPreviousPage(this);
	pageState.setPage(new AddedCartPage());	
	return; 
	}
	else if (input.equals("no")) {
    
	pageState.setPreviousPage(this);
	pageState.setPage(new ItemsInCategoryPage());		
	return; 
	}
	else {
	System.out.println("Invaled Enry Try again.");	
	System.out.println("");	
	}
    		}
    	}
	 }
	}

	
	public int getIdentifier() {
		return Identifier; 
	}
	
	
}
