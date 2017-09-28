package view;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.AbstractTableModel;

import logger.FileLogger;
import model.AddressBook;
import model.User;

public class TableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] columnNames = {"Studiengang", "Nachname", "Vorname", "PID", "Vegan"};
	private static ArrayList<User> userArray;
	
	FileLogger fl = new FileLogger();
	Logger logger = fl.getLogger();
	
	public TableModel(AddressBook book) {
		
		userArray = book.getAddressBook();
		
	}

	@Override
	public int getColumnCount() {
		
		logger.info("Getting Column Count for " + TableModel.class);
		return columnNames.length;
		
	}

	@Override
	public int getRowCount() {
		
		logger.info("Getting Row Count for " + TableModel.class);
		return userArray.size();
		
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		switch(col) {
		
		case 0:
			logger.info("Getting Studiengang at " + row);
			return userArray.get(row).getStudiengang();
			
		case 1:
			logger.info("Getting Nachname at " + row);
			return userArray.get(row).getNachname();
			
		case 2:
			logger.info("Getting Vorname at " + row);
			return userArray.get(row).getVorname();
			
		case 3:
			logger.info("Getting PID at " + row);
			return new Integer(userArray.get(row).getPid());
			
		case 4:
			logger.info("Getting Vegan at " + row);
			return new Boolean(userArray.get(row).getVegan());
			
		default :
			logger.log(Level.WARNING, "Couldn't retrieve value at Column: " + col + " Row: "+ row);
			return null;
		}
		
	}
	
	public String getColumnName(int column) {
		logger.info("Getting columnNames for " + TableModel.class);
		return columnNames[column];
		
	}

}
