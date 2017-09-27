package model;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controller {
	
	private AddressBook book;
	private File selectedFile;
	private String SAVE_LOCATION = "C:\\Users\\lvonnied\\eclipse-workspace\\Swing Address book";
	private String FILE_SEPERATOR = "/";
	private String FILE_SUFFIX = ".object";
	
	public Controller(AddressBook book) {
		
		this.book = book;
		
	}

	public boolean openFileChooser() {
		JFileChooser jfc = new JFileChooser("C:\\Users\\lvonnied\\eclipse-workspace\\Swing Address book");

		int returnValue = jfc.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			book.setAddressBook(selectedFile);
			return true;
		}else {
			return false;
		}
		
	}
	
	public void openSaveAsDirectory() {
		
	    JFrame frame = new JFrame("InputDialog Example #1");

	    String name = JOptionPane.showInputDialog(frame, "Please type a name for your file");
	    
	    if(name == null) {
	    	
	    	name = "file";
	    	
	    }
	    
	    File fileName = new File(SAVE_LOCATION + FILE_SEPERATOR + name + FILE_SUFFIX);

	    book.saveUser(fileName);
		
	}
	
	public void openSaveDirectory() {
		
		if(selectedFile != null) {
			book.saveUser(selectedFile);
		}else {
			openSaveAsDirectory();
		}
		
	}
	
	public void setEmptySelectedFile() {
		
		selectedFile = null;
		
	}
	
	
	
}
