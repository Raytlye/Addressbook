package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserReader {
	
	private static Logger logger = LogManager.getRootLogger();
	
	@SuppressWarnings("unchecked")
	public ArrayList<User> read(File file) {
		ArrayList<User> users;
		
		try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
			logger.info("Setting read file to users Array");
			users = (ArrayList<User>) objectInputStream.readObject();
			
		} catch (Exception e) {
        	logger.log(Level.WARN, "Reading file failed and returning empty ArrayList", e);
			users = new ArrayList<User>();
			return users;
    		
        }
		return users;
	}

}
