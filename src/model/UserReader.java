package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class UserReader {
	
	@SuppressWarnings("unchecked")
	public ArrayList<User> read(File file) {
		ArrayList<User> users;
		
		try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
			
			users = (ArrayList<User>) objectInputStream.readObject();
			
		} catch (Exception e) {
        	
			users = new ArrayList<User>();
			return users;
    		
        }
		return users;
	}

}
