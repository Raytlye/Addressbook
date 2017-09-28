package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import logger.FileLogger;

public class UserReader {
	
	FileLogger fl = new FileLogger();
	Logger logger = fl.getLogger();
	
	@SuppressWarnings("unchecked")
	public ArrayList<User> read(File file) {
		ArrayList<User> users;
		
		try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
			logger.info("Setting read file to users Array");
			users = (ArrayList<User>) objectInputStream.readObject();
			
		} catch (Exception e) {
        	logger.log(Level.WARNING, "Reading file failed and returning empty ArrayList", e);
			users = new ArrayList<User>();
			return users;
    		
        }
		return users;
	}

}
