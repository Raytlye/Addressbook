package model;

import java.util.logging.Logger;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import logger.FileLogger;
import view.AddressTable;

public class ListListener implements ListSelectionListener{
	
	private AddressTable adTable;
	
	FileLogger fl = new FileLogger();
	Logger logger = fl.getLogger();
	
	public ListListener(AddressTable adTable) {
		
		this.adTable = adTable;
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
		logger.info("Enabled Button " + adTable.btnDelete.getName() + " " + adTable.btnEdit.getName());
		adTable.btnDelete.setEnabled(true);
		adTable.btnEdit.setEnabled(true);
		
	}

}