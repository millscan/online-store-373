package state_machine;
import online_store_group_project.*;
import java.util.ArrayList;

public class PageState {
	 private Page CurrentPage; 
	 private ArrayList<Page> PreviousPage = new ArrayList<Page>(); 
	 private String Category; 
	 private Item item; 
	 private ArrayList<Item> cart = new ArrayList<Item>(); 
	 private Store s1; 
	
	 public void PageState(Page StartPage) {
	 CurrentPage = StartPage; 
	 }
	 
     public void setPage(Page currentPage) {
    	this.CurrentPage = currentPage; 
     }
     
     public Page getNextPage() {
    	return CurrentPage; 
     }
     
     public void setPreviousPage(Page previousPage) {
    	 PreviousPage.add(previousPage); 
     }
     
     public Page getPreviouspage() {
    	 Page previousPage = PreviousPage.get(PreviousPage.size() - 1);
    	 PreviousPage.remove(PreviousPage.size() - 1);
    	 return previousPage; 
     }
     
     public void setStore(Store S1) {
    	this.s1 = S1; 
     }
     
     public Store getStore() {
    	return s1; 
     }
     
     public void setCategory(String category) {
    	this.Category = category; 
     }
     
     public String getCategory() {
    	return Category; 
     }
     
     public void setItem(Item aitem) { 
    	this.item = aitem; 
     }
     
     public Item getItem() {
    	 return item; 
     }
     
     public ArrayList<Item> getCart(){
    	 return cart; 
     }
     
}
