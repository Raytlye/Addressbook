package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import view.AddressTable;
import view.DeleteMessage;
import view.ValueEditWindow;
import view.ValueInputWindow;

public class Controller implements ActionListener{
	
	private AddressBook book;
	private File selectedFile;
	private String SAVE_LOCATION = "C:\\Users\\lvonnied\\eclipse-workspace\\Swing Address book";
	private String FILE_SEPERATOR = "/";
	private String FILE_SUFFIX = ".object";
	private AddressTable adTable;
	
	public Controller(AddressBook book, AddressTable adTable) {
		
		this.book = book;
		this.adTable = adTable;
		
	}

	public boolean openFileChooser() {
		JFileChooser jfc = new JFileChooser("C:\\Users\\lvonnied\\eclipse-workspace\\Swing Address book");

		int returnValue = jfc.showOpenDialog(adTable);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			book.setAddressBook(selectedFile);
			return true;
		}else {
			return false;
		}
		
	}
	
	public void openSaveAsDirectory() {
		
		String name;
		do {
		    name = JOptionPane.showInputDialog(null, "Please type a name for your file");
		    if(name == null) {
		        break;
		    }
		} while(name.isEmpty());
		
		if(name!=null) {
			File fileName = new File(SAVE_LOCATION + FILE_SEPERATOR + name + FILE_SUFFIX);
			book.saveUser(fileName);
		}
		
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
	
	public void createJOptionSelectPane() {
		
		JDialog dialog = new JDialog();
		JOptionPane.showMessageDialog(dialog,
				"Please select one row!",
				"Warning",
		JOptionPane.WARNING_MESSAGE);
			
	}
	
	public void createJOptionOpenPane() {
		
		JDialog dialog = new JDialog();
		JOptionPane.showMessageDialog(dialog,
				"Please open a file first!",
				"Warning",
		JOptionPane.WARNING_MESSAGE);
		
	}
	
	public void checkCounter() {
		
		if(adTable.createTableCounter > 0) {
			adTable.removeTablePanel();
		}
		
	}
	
	public void createAndUpdateUI() {
		
		adTable.createTablePanel();
		adTable.createTableCounter++;
		SwingUtilities.updateComponentTreeUI(adTable);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
		
		switch(action) {
		
		case "NEW":
			new ValueInputWindow(adTable, book);
			
			break;
			
		case "DELETE":
				
			if(adTable.table.getSelectedRowCount() != 1) {createJOptionSelectPane();}
			
			else {new DeleteMessage(adTable, book, adTable.table);}
			
			break;
			
		case "EDIT":
			
			if(adTable.table.getSelectedRowCount() != 1) {createJOptionSelectPane();}
			
			else {new ValueEditWindow(adTable, book, adTable.table);}
			
			break;
			
		case "READ":
			
			if(openFileChooser() == true) {
				checkCounter();
				createAndUpdateUI();
			}
			
			break;
			
		case "NEWFILE":
			
			checkCounter();
			book.setEmptyAddressBook();
			setEmptySelectedFile();
			createAndUpdateUI();
			break;
		
		case "SAVEAS":
			
			if(adTable.tableIsVisible == true) {
				openSaveAsDirectory();
			}else {
				createJOptionOpenPane();
			}
			break;
			
		case "SAVE":
			
			if(adTable.tableIsVisible == true) {openSaveDirectory();}
			else {createJOptionOpenPane();}
			break;
			
		case "EXIT":
			
			int result = JOptionPane.showConfirmDialog(null, "Any unsaved changes will be lost. Do you wish to save first?",null, JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				openSaveDirectory();
				System.exit(0);
			}
			else {System.exit(0);}
			break;
			
		default :
			JDialog dialog = new JDialog();
			JOptionPane.showMessageDialog(dialog,
				    "Something went horribly wrong!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			
			break;
		
		}
		
	}
	
	
	
}
