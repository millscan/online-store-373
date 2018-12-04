package GUI;

import data_storage.StoreDataIO;
import online_store_group_project.*;

public class OnlineStoreDriver {
	
	private Store store;
	private GUI_skeleton mainFrame;
	
	OnlineStoreDriver(){
		store = StoreDataIO.LoadStoreData();
		mainFrame = new GUI_skeleton(store);
	}
	
	public static void main(String[] args) {
		OnlineStoreDriver driver = new OnlineStoreDriver();
	}
}
