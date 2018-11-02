package data_storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import online_store_group_project.User;

public class StoreDataIO {
	
	public enum FileErrorCodes{
		Success,
		FileError,
		OutputError,
		UsernameInUseError,
		EmailInUseError
	}
		
	public static FileErrorCodes storeUserData(User u) {		
		
		File usersFile = new File("users.csv");
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		String csvLine = new String();
		byte userData[] = new byte[1000];
		
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
			System.out.println(csvLine);
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
		
		csvLine = String.format("%s%s,%s,%s,%s,%s",csvLine, u.getUsername(), u.getFirstName(), u.getLastName(), u.getEmailAddress(), u.getPassword());
		System.out.println(csvLine);
		
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
