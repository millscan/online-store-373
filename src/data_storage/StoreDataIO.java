package data_storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import online_store_group_project.*;

public class StoreDataIO {
	
	public enum FileErrorCodes{
		Success,
		FileError,
		OutputError,
		UsernameInUseError,
		EmailInUseError
	}
	
	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	
	public static void LoadOwners(Store store) {
		File usersFile = new File("users.csv");
		ArrayList<Owner> owners = new ArrayList<Owner>();
		String[] csvLines = getLinesFromCsv("users.csv");
		System.out.println("Loading owners...");
		
		if(usersFile.exists() && csvLines != null) {
			for(String csvLine : csvLines) {
				//System.out.println(csvLine);
				String parsedUserData[] = csvLine.split("#");
				if(parsedUserData[0].equals("Owner")) {
					Owner loadedOwner = new Owner(
							store,
							parsedUserData[1], 
							parsedUserData[2], 
							parsedUserData[3],
							parsedUserData[4],
							parsedUserData[5]
					);
					
					String itemFilePath = "userData/" + loadedOwner.getUsername() + "/items.csv";
					//TODO: get owner items from item file
					loadedOwner.setItems(getItemsFromItemFile(store, itemFilePath));
					owners.add(loadedOwner);
				}
				
			}
			
		}
		store.owners = owners;
	}
	
	public static void LoadCustomers(Store store){
		File usersFile = new File("users.csv");
		ArrayList<Customer> customers = new ArrayList<Customer>();
		String[] csvLines = getLinesFromCsv("users.csv");
		System.out.println("Loading customers...");
		
		if(usersFile.exists() && csvLines!= null) {
			for(String csvLine : csvLines) {
				//System.out.println(csvLine);
				String parsedUserData[] = csvLine.split("#");
				if(parsedUserData[0].equals("Customer")) {
					Customer customer = new Customer(
							store,
							parsedUserData[1], 
							parsedUserData[2], 
							parsedUserData[3],
							parsedUserData[4],
							parsedUserData[5]
					);

					String orderFoldersPath = "userData/" + customer.getUsername() + "/orders";
					ArrayList<Order> customerOrders = getOrdersFromPath(store, orderFoldersPath);
					customer.setOrders(customerOrders);					
					customers.add(customer);
				}

			}	
		}
		store.customers = customers;
	}
	
	public static void LoadUsers(Store store) {
		LoadOwners(store);
		LoadCustomers(store);
	}
	
	public static ArrayList<Order> getOrdersFromPath(Store store, String orderFoldersPath){
		
		//get folders for each order
		String[] orderIds = getSubdirectories(new File(orderFoldersPath));
		ArrayList<Order> orders = new ArrayList<Order>();
		if(orderIds == null) {
			return orders;
		}
				
		//for each order, get order data and item data
		for(String orderId : orderIds) {
			ArrayList<Item> orderItems = new ArrayList<Item>();
			Date orderTimestamp;
			boolean orderShipped;

			String orderInfoString = getCsvString(orderFoldersPath + "/" + orderId +  "/order-info.csv");
			
			try {
				String[] orderInfoSplit = orderInfoString.split("#");
				orderTimestamp = dateFormat.parse(orderInfoSplit[0]);
				orderShipped = (orderInfoSplit[1] == "true") ? true : false;
			}
			catch(IndexOutOfBoundsException e) {
				System.out.println("couldnt read order data. order not added");
				continue;
			}
			catch(ParseException e) {
				System.out.println("couldnt parse order date. order not added");
				continue;
			}
			
			//gets items from order-items.csv
			
			String orderItemsPath = orderFoldersPath + "/" + orderId +  "/order-items.csv";
			orderItems = getItemsFromItemFile(store, orderItemsPath);
			
			
			//File orderItemsFile = new File(folderPath + "/items.csv");
			Order o = new Order(orderId, orderItems, orderTimestamp, orderShipped);
			orders.add(o);			
			//System.out.println(folderPath);
		}
		
		return orders;
	}
	
	public static ArrayList<Item> getItemsFromItemFile(Store store, String itemsFilePath){
		ArrayList<Item> items = new ArrayList<Item>();
		System.out.println(itemsFilePath);
		File itemsFile = new File(itemsFilePath);
		if(itemsFile.exists()) {
			String[] itemDataStrings = getLinesFromCsv(itemsFilePath);
			
			//STRING FORMAT: id, seller id, name, description, category, price, quantity
			for(String itemDataString : itemDataStrings) {
				String[] itemDataSplit = itemDataString.split("#");
				try {
					items.add(new Item(store, itemDataSplit[0], itemDataSplit[1], itemDataSplit[2], itemDataSplit[3], itemDataSplit[4], itemDataSplit[5]));
				}
				catch(IndexOutOfBoundsException e) {
					System.out.println("Can't create item from this string: " + itemDataString);
				}
			}
		}
		return items;
	}
	
	public static void storeAllUsers(Store s) {
		clearFile("users.csv");
		for(Owner owner : s.owners) {
			storeOwner(owner);
		}
		for(Customer customer: s.customers) {
			storeCustomer(customer);
		}
	}
		
	public static void storeOwner(Owner o) {
		writeLineToFile("users.csv", o.toCsvString(), true);
		storeOwnerItems(o);
	}
	
	public static void storeCustomer(Customer c) {
		writeLineToFile("users.csv", c.toCsvString(), true);
		storeCustomerOrders(c);
	}
	
	public static void storeCustomerOrders(Customer customer) {
		String ordersPath = "userData/" + customer.getUsername() + "/orders";
		
		File orderFolder = new File(ordersPath);
		if(!orderFolder.exists()) {
			orderFolder.mkdirs();
		}
		else {
			clearOrdersDirectory(orderFolder);
		}
		
		for(Order o : customer.getOrders()) {
			String orderFolderPath = ordersPath + "/" + o.getID();
			File orderFolderFile = new File(orderFolderPath);
			if(!orderFolderFile.exists()) {
				orderFolderFile.mkdirs();
			}
			
			String orderInfoString = String.format("%s#%s",dateFormat.format(o.getTimestamp()), o.getShippedStatus());
			String orderInfoFilePath = orderFolderPath + "/order-info.csv";
			writeLineToFile(orderInfoFilePath, orderInfoString, false);
			
			String orderItemsFilePath = orderFolderPath + "/order-items.csv";
			
			for(Item i: o.getItems()){
				//STRING FORMAT: id, seller id, name, description, category, price, quantity
				String itemString = String.format("%s#%s#%s#%s#%s#%s", 
						i.getSeller().getId(), i.getName(), i.getDescription(), i.getCategory(), i.getPrice(), i.getQuantity());
						writeLineToFile(orderItemsFilePath, itemString, true);
			}
		}
	}
	
	public static void storeOwnerItems(Owner owner) {
		File ownerFolder = new File("userData/" + owner.getUsername());
		ownerFolder.mkdirs();
		
		String itemsFilePath = ownerFolder + "/items.csv";

		clearFile(itemsFilePath);
		
		for(Item i : owner.getItems()) {
			String itemString = String.format("%s#%s#%s#%s#%s#%s", 
					i.getSeller().getId(), i.getName(), i.getDescription(), i.getCategory(), i.getPrice(), i.getQuantity());
			writeLineToFile(itemsFilePath, itemString, true);
		}
	}
	
	public static File getFile(String filePath) {
		File file = new File(filePath);
		if(!file.exists()) {
			try {
				file.createNewFile();
			}
			catch(IOException e){
				System.out.println("Error creating file.");
			}
		}
		return file;
	}
	
	public static void clearOrdersDirectory(File directory) {
		for(File orderDir : directory.listFiles()) {
			for(File file : orderDir.listFiles()) {
				file.delete();
			}
			orderDir.delete();
		}
	}
	
	public static String getCsvString(String filePath) {
		String csvLine;
		String csvText = new String();
		BufferedReader reader;
		
		//get last version of csv
		try {
			reader = new BufferedReader(new FileReader(filePath));
			csvLine = reader.readLine();
			while(csvLine != null) {
				csvText = csvText.concat(csvLine + "\n");
				csvLine = reader.readLine();
			}
			
			if(csvText.length() != 0) {
				csvText += '\n';
			}
			reader.close();
			//System.out.println(csvLine);
		}
		catch(IOException e) {
			System.out.println("Problems getting lines from csv file");
			return null;
		}
		
		return csvText;
	}
	
	public static String[] getLinesFromCsv(String filePath) {
		String csvLine;
		
		//get last version of csv
		csvLine = getCsvString(filePath);
		if(csvLine.trim().equals("")){
			return null;
		}
		return csvLine.split("\n");
		//System.out.println(csvLine);
	}
	
	public static String[] getSubdirectories(File folder) {
		String[] directories = folder.list(new FilenameFilter() {
		  @Override
		  public boolean accept(File current, String name) {
		    return new File(current, name).isDirectory();
		  }
		});
		return directories;
	}
	
	public static void clearFile(String filePath) {
		getFile(filePath);
		
		try(FileWriter fw = new FileWriter(filePath, false);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			} catch (IOException e) {
				
			    System.out.println("Error appending line to file. IOException");
			}
	}
	
	public static void writeLineToFile(String filePath, String line, boolean append) {

	try(FileWriter fw = new FileWriter(filePath, append);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw))
		{
		    out.println(line);
		} catch (IOException e) {
		    System.out.println("Error appending line to file. IOException");
		}
	}
	
}
