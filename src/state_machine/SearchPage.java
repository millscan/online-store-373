package state_machine;

import java.util.Scanner;

public class SearchPage extends Page {

	private int Identifier = 4; 
	
	public void nextPage(PageState pageState) {
		System.out.println("Search Page");
		Scanner reader = new Scanner(System.in);
		String input = reader.nextLine(); 
		
		if (input.equals("home")) {
	    	pageState.setPage(new HomePage());	
	    	}
	    	
	    	else {
	    		if (input.equals("back")) {
	    		pageState.setPage(new HomePage());		
	    		}
	    		
	    		else { 
	    			
	    			
	    			
	    			
	    			
	      }
	   }
	}
	
	public int getIdentifier() {
		return Identifier; 
	}
	
	
}
