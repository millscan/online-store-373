package state_machine;

import java.util.Scanner;

public class FeaturedItemsPage extends Page {

	private int Identifier = 3; 
	
	public void nextPage(PageState pageState) {
		System.out.println("Featured Items Page");
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine(); 
		
		if (input.equals("home")) {
	    	pageState.setPage(new HomePage());	
	    	}
	    	
	    	else {
	    		if (input.equals("back")) {
	    		pageState.setPage(pageState.getPreviouspage());		
	    		}
	    		
	    		else { 
	    			
	    			
	    			
	    			
	    		}
	    	}
	    }  
	
	public int getIdentifier() {
		return Identifier; 
	}
	
	
}
