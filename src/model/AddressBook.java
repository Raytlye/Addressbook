package model;

import java.io.File;
import java.util.ArrayList;

public class AddressBook {
	
	private ArrayList<User> addressBook;
	private UserReader reader = new UserReader();
	private UserWriter writer = new UserWriter();
	private ArrayList<AddressBookListener> listeners 
		= new ArrayList<AddressBookListener>();
	
	public void setAddressBook(File file) {
		
		addressBook = reader.read(file);
		
	}
	
	public void setEmptyAddressBook() {
		
		addressBook = new ArrayList<User>();
		
	}
	
	public void saveUser(File file) {
		
		writer.write(addressBook, file);
		
	}
	
	public void addUser(User user) {
		
		addressBook.add(user);
		
		for(AddressBookListener listener : listeners) {
					
			listener.userAdded();
				
		}
			
	}
	
	public void removeUser(User user) {
		
		addressBook.remove(user);
		for(AddressBookListener listener : listeners) {
			
			listener.userRemoved();
			
		}
		
	}
	
	public void updateUser(User updatedUser, int index) {
		
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
		
		return addressBook;
		
	}
	
	public void addListener(AddressBookListener listener) {
		
		listeners.add(listener);
		
	}

}
