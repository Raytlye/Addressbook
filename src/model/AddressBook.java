package model;

import java.io.File;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddressBook {
	
	private ArrayList<User> addressBook;
	private UserReader reader = new UserReader();
	private UserWriter writer = new UserWriter();
	private ArrayList<AddressBookListener> listeners 
		= new ArrayList<AddressBookListener>();
	
	private static Logger logger = LogManager.getRootLogger();
	
	public void setAddressBook(File file) {
		
		logger.info("Reading from {} to address book", file.getAbsolutePath());
		addressBook = reader.read(file);
		
	}
	
	public void setEmptyAddressBook() {
		
		logger.debug("Setting address book as a new ArrayList<User>");
		addressBook = new ArrayList<User>();
		
	}
	
	public void saveUser(File file) {
		
		writer.write(addressBook, file);
		
	}
	
	public void addUser(User user) {
		
		logger.debug("Adding {} to address book and iterating through the listeners", user.getNachname());
		
		addressBook.add(user);
		
		for(AddressBookListener listener : listeners) {
					
			listener.userChanged();
				
		}
			
	}
	
	public void removeUser(User user) {
		
		logger.debug("Removing {} from address book and iterating through the listeners", user.getNachname());
		
		addressBook.remove(user);
		for(AddressBookListener listener : listeners) {
			
			listener.userChanged();
			
		}
		
	}
	
	public void updateUser(User updatedUser, int index) {
		
		logger.debug("Update {} at Index: {} and iterating through the listeners", updatedUser.getNachname(), index);
		User user = addressBook.get(index);
		
		user.setStudiengang(updatedUser.getStudiengang());
		user.setNachname(updatedUser.getNachname());
		user.setVorname(updatedUser.getVorname());
		user.setPid(updatedUser.getPid());
		user.setVegan(updatedUser.getVegan());
		
		for(AddressBookListener listener : listeners) {
			
			listener.userChanged();
			
		}
		
	}
	
	public ArrayList<User> getAddressBook() {
		
		logger.debug("Returning {}", addressBook);
		
		return addressBook;
		
	}
	
	public void addListener(AddressBookListener listener) {
		
		logger.debug("Adding listener to ArrayList listeners");
		
		listeners.add(listener);
		
	}

}
