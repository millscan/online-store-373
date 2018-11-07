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
	
	public static ArrayList<User> LoadUsers(Store store) {
		File usersFile = new File("users.csv");
		String csvText = null;
		ArrayList<User> users = new ArrayList<User>();
		
		System.out.println("Loading users...");
		
		//make sure file is working
		if(!usersFile.exists()) {
			System.out.println("User file not found!!");
			return null;
		}
		
		csvText = getCsvString("users.csv");

		String[] csvLines = csvText.split("\n");
		
		//System.out.println(csvText);
		
		//for user csv line, create user and add appropriate orders/items
		for(String csvLine : csvLines) {
			//System.out.println(csvLine);
			String parsedUserData[] = csvLine.split("#");
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
					ArrayList<Order> customerOrders = getOrdersFromPath(orderFoldersPath);
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
	
	public static ArrayList<Order> getOrdersFromPath(String orderFoldersPath){
		
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
			
			//File orderItemsFile = new File(folderPath + "/items.csv");
			Order o = new Order(orderId, orderItems, orderTimestamp, orderShipped);
			orders.add(o);			
			//System.out.println(folderPath);
		}
		
		return orders;
	}
	
	public static ArrayList<Item> getItemsFromItemFile(String itemsFilePath){
		ArrayList<Item> items = new ArrayList<Item>();
		String[] itemDataStrings = getCsvString(itemsFilePath).split("\n");
		
		//STRING FORMAT: id, seller id, name, description, category, price, quantity
		for(String itemDataString : itemDataStrings) {
			String[] itemDataSplit = itemDataString.split("#");
			//Item readItem = new Item(itemDataSplit[0], itemDataSplit[1], itemDataSplit[2], itemDataSplit[3], itemDataSplit[4], itemDataSplit[5], itemDataSplit[6]);
		}
		return items;
	}
		
	public static FileErrorCodes storeUserData(User u) {		

		String userCsvText = new String();
		String userType = u.getClass().getSimpleName().trim();
	
		//make sure file is working		
		
		userCsvText = getCsvString("users.csv");	
		
		//TODO: Use payson's function to check if username/email exists
		
		//WRITE USER DATA TO USERS.CSV
		//System.out.println(userCsvText);
		String userCsvLine = String.format("%s#%s#%s#%s#%s#%s",userType, u.getUsername(), u.getFirstName(), u.getLastName(), u.getEmailAddress(), u.getPassword());
		
		if(userCsvText.contains(userCsvLine)) {
			System.out.println("User already in users file");
		}
		else {
			writeLineToFile("users.csv", userCsvLine, true);
		}
		
		//WRITE USER ITEMS/ORDERS to {username}/items or {username}/orders
		if(userType.equals("Customer")) {
			storeCustomerOrders((Customer)u);			
		}
		else {
			//HANDLE ITEMS FOR OWNERS
		}

		return FileErrorCodes.Success;
		
	}
	
	public static void storeCustomerOrders(Customer customer) {
		String ordersPath = "userData/" + customer.getUsername() + "/orders";
		
		File orderFolder = new File(ordersPath);
		if(!orderFolder.exists()) {
			orderFolder.mkdirs();
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
				String itemString = String.format("%s#%s#%s#%s#%s#%s#%s", 
						i.getId(), i.getSeller().getId(), i.getName(), i.getDescription(), i.getCategory(), i.getPrice(), i.getQuantity());
						writeLineToFile(orderItemsFilePath, itemString, true);
			}
		}
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
	
	public static FileErrorCodes WriteStringToFile(String filePath, String s) {
		FileOutputStream fOut;
		try {
			File file = new File(filePath);
			createFile(file);
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
	
	public static void writeLineToFile(String filePath, String line, boolean append) {
	//create file if doesnt exist
	File file = new File(filePath);
	createFile(file);
	
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
