package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
		
	    JFrame frame = new JFrame("Save file as");

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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
		
		switch(action) {
		
		case "NEW":
			new ValueInputWindow(adTable, book);
			
			break;
			
		case "DELETE":
				
			if(adTable.table.getSelectedRowCount() != 1) {
					
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,
						"Please select one row!",
						"Warning",
				JOptionPane.WARNING_MESSAGE);
					
				}
			
			else {
					
				new DeleteMessage(adTable, book, adTable.table);	
				
			}
			
			break;
			
		case "EDIT":
			
			if(adTable.table.getSelectedRowCount() != 1) {
				
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,
						"Please select one row!",
						"Warning",
				JOptionPane.WARNING_MESSAGE);
					
				}
			
			else {
					
				new ValueEditWindow(adTable, book, adTable.table);	
				
			}
			
			break;
			
		case "READ":
			if(openFileChooser() == true) {
				if(adTable.createTableCounter > 0) {
					adTable.removeTablePanel();
				}
				adTable.createTablePanel();
				adTable.createTableCounter++;
				SwingUtilities.updateComponentTreeUI(adTable);
			}
			
			break;
			
		case "NEWFILE":
			
			if(adTable.createTableCounter > 0) {
				adTable.removeTablePanel();
			}
			book.setEmptyAddressBook();
			setEmptySelectedFile();
			adTable.createTablePanel();
			adTable.createTableCounter++;
			SwingUtilities.updateComponentTreeUI(adTable);
			break;
		
		case "SAVEAS":
			
			if(adTable.tableIsVisible == true) {
				openSaveAsDirectory();
			}else {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,
						"Please open a file first!",
						"Warning",
				JOptionPane.WARNING_MESSAGE);
			}
			break;
			
		case "SAVE":
			
			if(adTable.tableIsVisible == true) {
				openSaveDirectory();
			}else {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,
						"Please open a file first!",
						"Warning",
				JOptionPane.WARNING_MESSAGE);
			}
				break;
			
		case "EXIT":
			int result = JOptionPane.showConfirmDialog(null, 
					   "Are you sure you wish to exit application?",null, JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
					    System.exit(0);
					}
			break;
			
		default :
			JFrame frame = new JFrame();
			JOptionPane.showMessageDialog(frame,
				    "Something went horribly wrong!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			
			break;
			
		
		}
		
	}
	
	
	
}
