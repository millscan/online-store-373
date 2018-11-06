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
		byte userData[] = new byte[1000];
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
				String parsedUserData[] = csvLine.split(",");
				for(String data : parsedUserData) {
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
		
	public static FileErrorCodes storeUserData(User u) {		
		
		File usersFile = new File("users.csv");
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		String csvLine = new String();
		byte userData[] = new byte[1000];
		String userType = u.getClass().getSimpleName();
		
		//make sure file is working
		if(!usersFile.exists()) {
			try {
				usersFile.createNewFile();
			}
			catch(IOException e) {
				return FileErrorCodes.OutputError;
			}
		}
		
		//get last version of csv
		try {
			inStream = new FileInputStream(usersFile);
			inStream.read(userData);
			csvLine = (new String(userData)).trim();
			if(csvLine.length() != 0) {
				csvLine += '\n';
			}
			//System.out.println(csvLine);
		}
		catch(FileNotFoundException e) {
			return FileErrorCodes.FileError;
		}
		catch(IOException e) {
			return FileErrorCodes.FileError;
		}
		
			
		//setup output stream
		try {
			outStream = new FileOutputStream(usersFile);
		}
		catch(FileNotFoundException e) {
			try {
				inStream.close();
			}
			catch(IOException ie){
				return FileErrorCodes.FileError;
			}
			return FileErrorCodes.FileError;
		}
		
		//TODO: Use payson's function to check if username/email exists
		
		csvLine = String.format("%s%s,%s,%s,%s,%s,%s",csvLine, userType, u.getUsername(), u.getFirstName(), u.getLastName(), u.getEmailAddress(), u.getPassword());
		//System.out.println(csvLine);
		
		try {
			outStream.write(csvLine.getBytes());
		}
		catch(IOException e) {
			try {
				inStream.close();
				outStream.close();
			}
			catch(IOException ie){
				return FileErrorCodes.FileError;
			}
			return FileErrorCodes.OutputError;
		}
		
		try {
			inStream.close();
			outStream.close();
		}
		catch(IOException ie){
			return FileErrorCodes.FileError;
		}
		return FileErrorCodes.Success;
		
	}
}
