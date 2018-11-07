package state_machine;

import java.util.Scanner;

public class CategoryPage extends Page{
    
    public int type = 2; 
	
	public void nextPage(PageState pageState) {
		System.out.println("Category Page");
		Scanner reader1 = new Scanner(System.in);
		int inputInteger = reader1.nextInt(); 
	}
	
	public int getIdentifier() {
		return type; 
	}
	
}
