package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.AddressBook;
import model.User;

public class TableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String[] columnNames = {"Studiengang", "Nachname", "Vorname", "PID", "Vegan"};
	private static ArrayList<User> userArray;
	
	public TableModel(AddressBook book) {
		
		userArray = book.getAddressBook();
		
	}

	@Override
	public int getColumnCount() {
		
		return columnNames.length;
		
	}

	@Override
	public int getRowCount() {
		
		return userArray.size();
		
	}

	@Override
	public Object getValueAt(int row, int col) {
		
		switch(col) {
		
		case 0:
			return userArray.get(row).getStudiengang();
			
		case 1:
			return userArray.get(row).getNachname();
			
		case 2:
			return userArray.get(row).getVorname();
			
		case 3:
			return new Integer(userArray.get(row).getPid());
			
		case 4:
			return new Boolean(userArray.get(row).getVegan());
			
		default :
			return null;
		}
		
	}
	
	public String getColumnName(int column) {
		
		return columnNames[column];
		
	}

}
