package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserWriter {
	
	public void write(ArrayList<User> users, File fileName) {
		
		try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName))) {
					
			writer.writeObject(users);
			System.out.println("File saved");
					
		}catch(Exception e){
			
			throw new RuntimeException("File couldn't been saved", e);
			
		}

	}
}
