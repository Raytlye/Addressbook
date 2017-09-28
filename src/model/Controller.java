package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Logger;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import logger.FileLogger;
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
	
	FileLogger fl = new FileLogger();
	Logger logger = fl.getLogger();
	
	public Controller(AddressBook book, AddressTable adTable) {
		
		this.book = book;
		this.adTable = adTable;
		
	}

	public boolean openFileChooser() {
		logger.info("Opening JFileChooser at " + SAVE_LOCATION);
		JFileChooser jfc = new JFileChooser(SAVE_LOCATION);

		int returnValue = jfc.showOpenDialog(adTable);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			book.setAddressBook(selectedFile);
			logger.info("Opening " + selectedFile.getName() + " and setting AddressBook to it");
			return true;
		}else {
			logger.info("User pressed Cancel");
			return false;
		}
		
	}
	
	public void openSaveAsDirectory() {
		logger.info("Opening JOptionPane to type name for File");
		String name;
		do {
			logger.info("Repeating method until user types a correct name or cancels input");
		    name = JOptionPane.showInputDialog(null, "Please type a name for your file");
		    if(name == null) {
		    	logger.info("User pressed Cancel");
		        break;
		    }
		} while(name.isEmpty());
		
		if(name!=null) {
			File fileName = new File(SAVE_LOCATION + FILE_SEPERATOR + name + FILE_SUFFIX);
			book.saveUser(fileName);
			isSaved = true;
			logger.info("Saving file at " + fileName);
		}
		
	}
	
	public void openSaveDirectory() {
		
		if(selectedFile != null) {
			logger.info("Saving into existing file...");
			book.saveUser(selectedFile);
			isSaved = true;
		}else {
			logger.info("Opening openSaveAsDirectory because a exisiting file to save into doesn't exist");
			openSaveAsDirectory();
		}
		
	}
	
	public void setEmptySelectedFile() {
		
		logger.info("Setting selectedFile to null");
		selectedFile = null;
		
	}
	
	public void createJOptionSelectPane() {
		
		logger.info("Opening Warning Message (Didn't select a row)");
		JDialog dialog = new JDialog();
		JOptionPane.showMessageDialog(dialog,
				"Please select one row!",
				"Warning",
		JOptionPane.WARNING_MESSAGE);
			
	}
	
	public void createJOptionOpenPane() {
		
		logger.info("Opening Warning Message (User didn't open a file)");
		JDialog dialog = new JDialog();
		JOptionPane.showMessageDialog(dialog,
				"Please open a file first!",
				"Warning",
		JOptionPane.WARNING_MESSAGE);
		
	}
	
	public void checkCounter() {
		
		logger.info("Checking attribute createTableCounter");
		if(adTable.createTableCounter > 0) {
			adTable.removeTablePanel();
		}
		
	}
	
	public void createAndUpdateUI() {
		
		logger.info("Creating Panel, incrementing createTableCounter and updating GUI");
		isSaved = false;
		adTable.createTablePanel();
		adTable.createTableCounter++;
		SwingUtilities.updateComponentTreeUI(adTable);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
		
		switch(action) {
		
		case "NEW":
			logger.info("Opening " + ValueInputWindow.class);
			new ValueInputWindow(adTable, book);
			
			break;
			
		case "DELETE":
				
			if(adTable.table.getSelectedRowCount() != 1) {createJOptionSelectPane();}
			
			else {new DeleteMessage(adTable, book, adTable.table);logger.info("Opening " + DeleteMessage.class);}
			
			break;
			
		case "EDIT":
			
			if(adTable.table.getSelectedRowCount() != 1) {createJOptionSelectPane();}
			
			else {new ValueEditWindow(adTable, book, adTable.table);logger.info("Opening " + ValueEditWindow.class);}
			
			break;
			
		case "READ":
			
			if(openFileChooser() == true) {
				if(!isSaved) {
					logger.info("Opening Save Dialog because file wasn't saved yet");
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
				logger.info("Opening Save Dialog because file wasn't saved yet");
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
			
			if(!isSaved) {
				logger.info("Opening Save Dialog because file wasn't saved yet");
				int result = JOptionPane.showConfirmDialog(null, "Any unsaved changes will be lost. Do you wish to save first?",null, JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) {
					openSaveDirectory();
					System.exit(0);
				}
			}
			else {System.exit(0);}
			break;
			
		default :
			logger.severe("Something went terribly wrong!");
			JDialog dialog = new JDialog();
			JOptionPane.showMessageDialog(dialog,
				    "Something went horribly wrong!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			
			break;
		
		}
		
	}
	
	
	
}
