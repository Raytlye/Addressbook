package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserWriter {
	
	private static Logger logger = LogManager.getRootLogger();
	
	public void write(ArrayList<User> users, File fileName) {
		
		try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName))) {
			logger.info("Writing address book into {}", fileName);
			writer.writeObject(users);
			System.out.println("File saved");
					
		}catch(Exception e){
			
			logger.log(Level.ERROR, "File couldn't been written", e);
			
		}

	}
}
