package drivers;
import data_storage.StoreDataIO;
import online_store_group_project.*;

public class Driver1 {
	
	public static void main(String args[]) {
		Store s1 = new Store();
		Customer c1 = new Customer(s1, "joethebro1997", "joe@example.com", "Joe", "Shmo", "joespass");
		System.out.println(StoreDataIO.storeUserData(c1));
	}
	
}
