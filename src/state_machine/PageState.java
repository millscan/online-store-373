package state_machine;



public class PageState {
	 public Page CurrentPage; 
	
	 public void PageState(Page StartPage) {
	 CurrentPage = StartPage; 
	 }
	 
     public void setPage(Page currentPage) {
    	this.CurrentPage = currentPage; 
     }
     
     public Page GetNextPage() {
    	return CurrentPage; 
     }
     
     
}
