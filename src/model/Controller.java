package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
	private boolean isSaved = true;
	
	private static Logger logger = LogManager.getRootLogger();
	
	public Controller(AddressBook book, AddressTable adTable) {
		
		this.book = book;
		this.adTable = adTable;
		
	}

	public boolean openFileChooser() {
		logger.debug("Opening JFileChooser at {}", SAVE_LOCATION);
		JFileChooser jfc = new JFileChooser(SAVE_LOCATION);

		int returnValue = jfc.showOpenDialog(adTable);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			book.setAddressBook(selectedFile);
			logger.debug("Opening {} and setting AddressBook to it", selectedFile.getName());
			return true;
		}else {
			logger.debug("User pressed Cancel");
			return false;
		}
		
	}
	
	public void openSaveAsDirectory() {
		logger.debug("Opening JOptionPane to type name for File");
		String name;
		do {
			logger.debug("Repeating method until user types a correct name or cancels input");
		    name = JOptionPane.showInputDialog(null, "Please type a name for your file");
		    if(name == null) {
		    	logger.debug("User pressed Cancel");
		        break;
		    }
		} while(name.isEmpty());
		
		if(name!=null) {
			File fileName = new File(SAVE_LOCATION + FILE_SEPERATOR + name + FILE_SUFFIX);
			book.saveUser(fileName);
			isSaved = true;
			logger.debug("Saving file at {}", fileName);
		}
		
	}
	
	public void openSaveDirectory() {
		
		if(selectedFile != null) {
			logger.debug("Saving into existing file...");
			book.saveUser(selectedFile);
			isSaved = true;
		}else {
			logger.warn("Opening method to save into a new file because there is no existing file to save into");
			openSaveAsDirectory();
		}
		
	}
	
	public void setEmptySelectedFile() {
		
		logger.debug("Setting selectedFile to null");
		selectedFile = null;
		
	}
	
	public void createJOptionSelectPane() {
		
		logger.warn("No row to delete/edit was selected");
		JDialog dialog = new JDialog();
		JOptionPane.showMessageDialog(dialog,
				"Please select one row!",
				"Warning",
		JOptionPane.WARNING_MESSAGE);
			
	}
	
	public void createJOptionOpenPane() {
		
		logger.warn("No file was opened");
		JDialog dialog = new JDialog();
		JOptionPane.showMessageDialog(dialog,
				"Please open a file first!",
				"Warning",
		JOptionPane.WARNING_MESSAGE);
		
	}
	
	public void checkCounter() {
		
		logger.debug("Checking attribute createTableCounter");
		if(adTable.createTableCounter > 0) {
			adTable.removeTablePanel();
		}
		
	}
	
	public void createAndUpdateUI() {
		
		logger.debug("Creating Panel, incrementing createTableCounter and updating GUI");
		isSaved = false;
		adTable.createTablePanel();
		adTable.createTableCounter++;
		SwingUtilities.updateComponentTreeUI(adTable);
		
	}
	
	public void checkBeforeExiting() {
		
		logger.info("Check if user wants to exit the program");
		int result = JOptionPane.showConfirmDialog(null, "Do you wish to exit the programm?",null, JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			checkSavedBeforeExiting();
		}
		
	}
	
	public void checkSavedBeforeExiting() {
		
		if(!isSaved) {
			logger.warn("Opening Save Dialog because file wasn't saved yet");
			int result = JOptionPane.showConfirmDialog(null, "Any unsaved changes will be lost. Do you wish to save first?",null, JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION) {
				openSaveDirectory();
				System.exit(0);
			}
			else {
				System.exit(0);
			}
		}
		else {System.exit(0);}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
		
		switch(action) {
		
		case "NEW":
			logger.debug("Opening " + ValueInputWindow.class);
			new ValueInputWindow(adTable, book);
			
			break;
			
		case "DELETE":
				
			if(adTable.table.getSelectedRowCount() != 1) {createJOptionSelectPane();}
			
			else {new DeleteMessage(adTable, book, adTable.table);logger.debug("Opening " + DeleteMessage.class);}
			
			break;
			
		case "EDIT":
			
			if(adTable.table.getSelectedRowCount() != 1) {createJOptionSelectPane();}
			
			else {new ValueEditWindow(adTable, book, adTable.table);logger.debug("Opening " + ValueEditWindow.class);}
			
			break;
			
		case "READ":
			
			if(openFileChooser() == true) {
				if(!isSaved) {
					logger.warn("Opening Save Dialog because file wasn't saved yet");
					int result = JOptionPane.showConfirmDialog(null, "Any unsaved changes will be lost. Do you wish to save first?",null, JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
						openSaveDirectory();
					}
				}
				checkCounter();
				createAndUpdateUI();
			}
			
			break;
			
		case "NEWFILE":
			if(!isSaved) {
				logger.warn("Opening Save Dialog because file wasn't saved yet");
				int result = JOptionPane.showConfirmDialog(null, "Any unsaved changes will be lost. Do you wish to save first?",null, JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					openSaveDirectory();
				}
			}
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
			
			checkBeforeExiting();
			break;
			
		default :
			logger.error("Default switch case for all buttons was used");
			JDialog dialog = new JDialog();
			JOptionPane.showMessageDialog(dialog,
				    "Something went horribly wrong!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			
			break;
		
		}
		
	}
	
	
	
}
