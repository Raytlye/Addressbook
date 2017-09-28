package model;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;

import logger.FileLogger;

public class AddressBook {
	
	private ArrayList<User> addressBook;
	private UserReader reader = new UserReader();
	private UserWriter writer = new UserWriter();
	private ArrayList<AddressBookListener> listeners 
		= new ArrayList<AddressBookListener>();
	
	FileLogger fl = new FileLogger();
	Logger logger = fl.getLogger();
	
	public void setAddressBook(File file) {
		
		logger.info("Reading from " + file + " to ArrayList<User> addressBook");
		addressBook = reader.read(file);
		
	}
	
	public void setEmptyAddressBook() {
		
		logger.info("Setting addressBook as a new ArrayList<User>");
		addressBook = new ArrayList<User>();
		
	}
	
	public void saveUser(File file) {
		
		logger.info("Saving addressbook to " + file);
		writer.write(addressBook, file);
		
	}
	
	public void addUser(User user) {
		
		logger.info("Adding " + user.getNachname() + " to addressBook and iterating through the listeners");
		
		addressBook.add(user);
		
		for(AddressBookListener listener : listeners) {
					
			listener.userChanged();
				
		}
			
	}
	
	public void removeUser(User user) {
		
		logger.info("Removing " + user.getNachname() + " from addressBook and iterating through the listeners");
		
		addressBook.remove(user);
		for(AddressBookListener listener : listeners) {
			
			listener.userChanged();
			
		}
		
	}
	
	public void updateUser(User updatedUser, int index) {
		
		logger.info("Update " + updatedUser.getNachname() + " at Index " + index + " and iterating through the listeners");
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
		
		logger.info("Returning addressBook");
		
		return addressBook;
		
	}
	
	public void addListener(AddressBookListener listener) {
		
		logger.info("Adding " + listener.getClass() + " to ArrayList listeners");
		
		listeners.add(listener);
		
	}

}
