package data_storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
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
	
	public static ArrayList<User> LoadUsers(Store store) {
		File usersFile = new File("users.csv");
		FileInputStream inStream = null;
		byte userData[] = new byte[10000];
		String csvText = null;
		String[] csvLines;
		ArrayList<User> users = new ArrayList<User>();
		
		System.out.println("Loading users...");
		
		//make sure file is working
		if(!usersFile.exists()) {
			System.out.println("User file not found!!");
			return null;
		}
		
		csvText = getCsvString(usersFile);
		
		//get last version of csv
		csvText = (new String(userData)).trim();
		if(csvText.length() != 0) {
			csvText += '\n';
		}
		else {
			System.out.println("No users data in file");
			return null;
		}
		csvLines = csvText.split("\n");
		
		//for user csv line, create user and add appropriate orders/items
		for(String csvLine : csvLines) {
			//System.out.println(csvLine);
			String parsedUserData[] = csvLine.split(",");
			switch(parsedUserData[0]) {
				case "Customer":
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
					users.add(customer);
							
					break;
				case "Owner":
					users.add(new Owner(
						store,
						parsedUserData[1], 
						parsedUserData[2], 
						parsedUserData[3],
						parsedUserData[4],
						parsedUserData[5]
					));
					break;
				default:
					break;
			}

		}
		//System.out.println(csvText);
		
		return users;
	}
	
	public static ArrayList<Order> getOrdersFromPath(Store store, String orderFoldersPath){
		
		//get folders for each order
		String[] orderFolderPaths = getSubdirectories(new File(orderFoldersPath));
		ArrayList<Order> orders = new ArrayList<Order>();
				
		//for each order, get order data and item data
		for(String folderPath : orderFolderPaths) {
			String orderId = folderPath;
			ArrayList<Item> orderItems = new ArrayList<Item>();
			Date orderTimestamp;
			boolean orderShipped;

			File orderInfoFile = new File(folderPath + "/orderInfo.csv");
			String orderInfoString = getCsvString(orderInfoFile);
			
			try {
				String[] orderInfoSplit = orderInfoString.split(",");
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
			
			//File orderItemsFile = new File(folderPath + "/items.csv");
			Order o = new Order(orderId, orderItems, orderTimestamp, orderShipped);
			orders.add(o);			
			//System.out.println(folderPath);
		}
		
		return orders;
	}
	
	public static ArrayList<Item> getItemsFromItemFile(Store store, File itemsFile){
		ArrayList<Item> items = new ArrayList<Item>();
		String[] itemDataStrings = getCsvString(itemsFile).split("\n");
		
		//STRING FORMAT: id, seller id, name, description, category, price, quantity
		for(String itemDataString : itemDataStrings) {
			String[] itemDataSplit = itemDataString.split(",");
			//Item readItem = new Item(itemDataSplit[0], itemDataSplit[1], itemDataSplit[2], itemDataSplit[3], itemDataSplit[4], itemDataSplit[5], itemDataSplit[6]);
		}
		return items;
	}
		
	public static FileErrorCodes storeUserData(Store s, User u) {		

		String csvLine = new String();
		String userType = u.getClass().getSimpleName().trim();
		File userDataFolder;
		
		File usersFile = new File("users.csv");
		//System.out.println(userType);
		userDataFolder = new File("userData/" + u.getUsername());

		
		if(!userDataFolder.exists()) {
			userDataFolder.mkdir();
		}
		
		//make sure file is working
		createFile(usersFile);
		
		
		csvLine = getCsvString(usersFile);	
		
		//TODO: Use payson's function to check if username/email exists
		
		//WRITE USER DATA TO USERS.CSV
		String userCsvString = String.format("%s,%s,%s,%s,%s,%s",userType, u.getUsername(), u.getFirstName(), u.getLastName(), u.getEmailAddress(), u.getPassword());
		
		if(csvLine.contains(userCsvString)) {
			System.out.println("User already in users file");
		}
		else {
			csvLine = String.format("%s%s,%s,%s,%s,%s,%s",csvLine, userType, u.getUsername(), u.getFirstName(), u.getLastName(), u.getEmailAddress(), u.getPassword());
			WriteStringToFile(usersFile, csvLine);
		}
		
		//WRITE USER ITEMS/ORDERS to {username}/items or {username}/orders
		String userElementsString = new String();
		if(userType.equals("Customer")) {
			String ordersPath = "userData/" + u.getUsername() + "/orders";
			File ordersFolder = new File(ordersPath);
			if(!ordersFolder.exists()) {
				ordersFolder.mkdir();
			}
			for(Order o : ((Customer)u).getOrders()) {
				String orderFolderPath = ordersPath + "/" + o.getID();
				File orderFolder = new File(orderFolderPath);
				File orderInfoFile = new File(orderFolderPath + "/orderInfo.csv");
				if(!orderFolder.exists()) {
					orderFolder.mkdirs();
				}
				
				createFile(orderInfoFile);
				String orderInfoString = String.format("%s,%s,%s", o.getID(), dateFormat.format(o.getTimestamp()), o.getShippedStatus());
				WriteStringToFile(orderInfoFile, orderInfoString);
				
				String orderItemsFilePath = orderFolderPath + "/items.csv";
				File orderItemsFile = new File(orderItemsFilePath);
				createFile(orderItemsFile);
				
				String orderItemsCsvString = new String();
				
				for(Item i: o.getItems()){
					//STRING FORMAT: id, seller id, name, description, category, price, quantity
					orderItemsCsvString = orderItemsCsvString.concat(String.format("%s,%s,%s,%s,%s,%s,%s%n", 
							i.getID(), i.getSeller().getId(), i.getName(), i.getDescription(), i.getCategory().getName(), i.getPrice(), i.getQuantity()));
				}
				
				WriteStringToFile(orderItemsFile, orderItemsCsvString);
			}
		}
		else {
			//HANDLE ITEMS FOR OWNERS
		}

		return FileErrorCodes.Success;
		
	}
	
	public static void createFile(File f) {
		if(!f.exists()) {
			try {
				f.createNewFile();
			}
			catch(IOException e){
				System.out.println("Error creating file.");
				return;
			}
		}
	}
	
	
	public static String getCsvString(File file) {
		byte userData[] = new byte[1000];
		String csvLine;
		FileInputStream inStream = null;
		
		//get last version of csv
		try {
			inStream = new FileInputStream(file);
			inStream.read(userData);
			csvLine = (new String(userData)).trim();
			if(csvLine.length() != 0) {
				csvLine += '\n';
			}
			inStream.close();
			return csvLine;
			//System.out.println(csvLine);
		}
		catch(IOException e) {
			System.out.println("Problems getting lines from csv file");
			return null;
		}
	}
	
	public static String[] getLinesFromCsv(File file) {
		String csvLine;
		
		//get last version of csv
		csvLine = getCsvString(file);
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
	
	public static FileErrorCodes WriteStringToFile(File file, String s) {
		FileOutputStream fOut;
		try {
			fOut = new FileOutputStream(file);
		}
		catch(IOException e) {
			System.out.println("Error getting file to write to");
			return FileErrorCodes.FileError;
		}
		
		try {
			fOut.write(s.getBytes());
		}
		catch(IOException e) {
			try {
				fOut.close();
			}
			catch(IOException ie){
				return FileErrorCodes.FileError;
			}
			return FileErrorCodes.OutputError;
		}
		
		try {
			fOut.close();
		}
		catch(IOException ie){
			return FileErrorCodes.FileError;
		}
		
		return FileErrorCodes.Success;
	}
	
}
