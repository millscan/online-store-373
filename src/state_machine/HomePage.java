package state_machine;

import java.util.Scanner;

public class HomePage extends Page {
    private int Identifier = 1;   
	
	 
	public static void nextPage(PageState pageState) {
		System.out.println("next page");
		System.out.println("Do you want to search(1), browse categories(2), or view featured items(3)?:");	
		Scanner reader1 = new Scanner(System.in);
		int inputInteger = reader1.nextInt(); 
		
		
		if(inputInteger == 1) {
		
		}
		else if (inputInteger == 2) {
		System.out.println("next page");
		pageState.setPage(new CategoryPage()); 	
		}
		
		else if (inputInteger == 3) {
			
		}
	}
	
	public int getIdentifier() {
		return Identifier; 
	}
	
}
