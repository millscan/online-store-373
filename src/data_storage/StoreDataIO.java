package data_storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import online_store_group_project.*;

public class StoreDataIO {
	
	public enum FileErrorCodes{
		Success,
		FileError,
		OutputError,
		UsernameInUseError,
		EmailInUseError
	}
	
	public static ArrayList<User> LoadUsers(Store s) {
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
		}
		
		//get last version of csv
		try {
			inStream = new FileInputStream(usersFile);
			inStream.read(userData);
			csvText = (new String(userData)).trim();
			if(csvText.length() != 0) {
				csvText += '\n';
			}
			csvLines = csvText.split("\n");
			
			for(String csvLine : csvLines) {
				System.out.println(csvLine);
				String parsedUserData[] = csvLine.split(",");
				switch(parsedUserData[0]) {
					case "Customer":
						users.add(new Customer(
								s,
								parsedUserData[1], 
								parsedUserData[2], 
								parsedUserData[3],
								parsedUserData[4],
								parsedUserData[5]
						));
						break;
					case "Owner":
						users.add(new Owner(
							s,
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
		}		
		catch(FileNotFoundException e) {
			System.out.println("File not found exception");
		}
		catch(IOException e) {
			System.out.println("IO exception");
		}
		
		try {
			inStream.close();
		}catch(IOException e){
			System.out.println("Cannot close user file");
		}
		
		
		return users;
	}
		
	public static FileErrorCodes storeUserData(Store s, User u) {		
		
		FileOutputStream outStream = null;
		String csvLine = new String();
		String userType = u.getClass().getSimpleName().trim();
		File userDataFile;
		File userDataFolder;
		
		File usersFile = new File("users.csv");
		System.out.println(userType);
		if(userType.equals("Customer")) {
			userDataFolder = new File("userData/" + u.getUsername() + "/orders");
		}
		else {
			userDataFolder = new File("userData/" + u.getUsername() + "/items");
		}
		
		if(!userDataFolder.exists()) {
			userDataFolder.mkdirs();
		}
		
		//make sure file is working
		if(!usersFile.exists()) {
			try {
				usersFile.createNewFile();
			}
			catch(IOException e) {
				return FileErrorCodes.OutputError;
			}
		}
		
		
		csvLine = getCsvString(usersFile);	

			
		//setup output stream to input user to csv file
		try {
			outStream = new FileOutputStream(usersFile);
		}
		catch(FileNotFoundException e) {
			return FileErrorCodes.FileError;
		}
		
		//TODO: Use payson's function to check if username/email exists
		
		//WRITE USER DATA TO USERS.CSV
		String userCsvString = String.format("%s,%s,%s,%s,%s,%s",userType, u.getUsername(), u.getFirstName(), u.getLastName(), u.getEmailAddress(), u.getPassword());
		
		if(csvLine.contains(userCsvString)) {
			System.out.println("User already in users file");
		}
		else {
			csvLine = String.format("%s%s,%s,%s,%s,%s,%s",csvLine, userType, u.getUsername(), u.getFirstName(), u.getLastName(), u.getEmailAddress(), u.getPassword());
			WriteStringToFile(outStream, csvLine);
		}
		
		//WRITE USER ITEMS/ORDERS to {username}/items or {username}/orders
		String userElementsString = new String();
		if(userType.equals("Customer")) {
			File ordersFolder = new File("userData" + u.getUsername() + "/orders" );
			if(!ordersFolder.exists()) {
				ordersFolder.mkdir();
			}
			for(Order o : ((Customer)u).getOrders()) {
				
			}
		}
		else {
			userDataFile = new File("userData/" + u.getUsername() + "/items.csv");
		}
		
		
		try {
			outStream.close();
		}
		catch(IOException ie){
			return FileErrorCodes.FileError;
		}
		return FileErrorCodes.Success;
		
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
	
	public static FileErrorCodes WriteStringToFile(FileOutputStream fOut, String s) {
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
		return FileErrorCodes.Success;
	}
}
