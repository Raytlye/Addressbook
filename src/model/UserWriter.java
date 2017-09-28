package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import logger.FileLogger;

public class UserWriter {
	
	FileLogger fl = new FileLogger();
	Logger logger = fl.getLogger();
	
	public void write(ArrayList<User> users, File fileName) {
		
		try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName))) {
			logger.info("Writing file...");
			writer.writeObject(users);
			System.out.println("File saved");
					
		}catch(Exception e){
			
			logger.log(Level.WARNING, "File couldn't been written", e);
			
		}

	}
}
