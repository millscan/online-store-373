package state_machine;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

import online_store_group_project.Order;

public class OrderConfirmationPage extends Page {

	private int Identifier = 12;   
	
	 
	@SuppressWarnings("deprecation")
	public void nextPage(PageState pageState) {
	Scanner reader = new Scanner(System.in);
	
	
    
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
				DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss"); 
				Date dateobj = new Date(); 	 
				String id = UUID.randomUUID().toString();
				boolean shipped = false; 
				
				Order newOrder = new Order(id, pageState.getCart(), dateobj, shipped); 
		        pageState.getStore().orders.add(newOrder); 
		        
		        System.out.println("Your order " + id + " was confirmed!");
		        System.out.println("");
		        System.out.println("Date: " + dateobj.getDate() + " " + dateobj.getTime());
		        System.out.println("Items Purchased:");
		        pageState.getStore().PrintCart(pageState.getCart()); 
		        System.out.println("Total cost: $" + pageState.getCost());
			    
		        String input2 = reader.nextLine(); 
			
		        pageState.getStore().completeTransactions(pageState.getCart(), newOrder, pageState);
		pageState.setPreviousPage(this);	
		//pageState.setPage(new ItemPage());		
		} 
	  }
	}
 }
	
	
	
	public int getIdentifier() {
		return Identifier; 
	}
	
	
	
}
