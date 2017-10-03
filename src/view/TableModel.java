package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.AddressBook;
import model.User;

public class TableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] columnNames = {"Studiengang", "Nachname", "Vorname", "PID", "Vegan"};
	private static ArrayList<User> userArray;
	
	private static Logger logger = LogManager.getRootLogger();
	
	public TableModel(AddressBook book) {
		
		userArray = book.getAddressBook();
		
	}

	@Override
	public int getColumnCount() {
		
		//logger.info("Getting Column Count for " + TableModel.class);
		return columnNames.length;
		
	}

	@Override
	public int getRowCount() {
		
		//logger.info("Getting Row Count for " + TableModel.class);
		return userArray.size();
		
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		switch(col) {
		
		case 0:
			logger.info("Getting Studiengang at column: " + col + " row: " + row);
			return userArray.get(row).getStudiengang();
			
		case 1:
			logger.info("Getting Nachname at column: " + col + " row: " + row);
			return userArray.get(row).getNachname();
			
		case 2:
			logger.info("Getting Vorname at column: " + col + " row: " + row);
			return userArray.get(row).getVorname();
			
		case 3:
			logger.info("Getting PID at column: " + col + " row: " + row);
			return new Integer(userArray.get(row).getPid());
			
		case 4:
			logger.info("Getting Vegan at column: " + col + " row: " + row);
			return new Boolean(userArray.get(row).getVegan());
			
		default :
			logger.log(Level.WARN, "Couldn't retrieve value at column: " + col + " row: "+ row);
			return null;
		}
		
	}
	
	public String getColumnName(int column) {
		//logger.info("Getting columnNames for " + TableModel.class);
		return columnNames[column];
		
	}

}
