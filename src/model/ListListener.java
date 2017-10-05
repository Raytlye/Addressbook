package model;


import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.AddressTable;

public class ListListener implements ListSelectionListener{
	
	private AddressTable adTable;
	
	private static Logger logger = LogManager.getRootLogger();
	
	public ListListener(AddressTable adTable) {
		
		this.adTable = adTable;
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
		logger.debug("Enabled Buttons: {} {}",adTable.btnDelete.getName(), adTable.btnEdit.getName());
		adTable.btnDelete.setEnabled(true);
		adTable.btnEdit.setEnabled(true);
		
	}

}