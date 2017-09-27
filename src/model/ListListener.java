package model;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import view.AddressTable;

public class ListListener implements ListSelectionListener{
	
	private AddressTable adTable;
	
	public ListListener(AddressTable adTable) {
		
		this.adTable = adTable;
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		
		adTable.btnDelete.setEnabled(true);
		adTable.btnEdit.setEnabled(true);
		
	}

}