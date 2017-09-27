package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.AddressBook;
import model.AddressBookListener;
import model.Controller;

public class AddressTable extends JFrame implements AddressBookListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TableModel tableModel;
	private AddressBook book;
	private JTable table;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JPanel tablePanel;
	private ImageIcon img = new ImageIcon("database.png");
	private Controller controller;
	private int createTableCounter = 0;
	private JPanel btnPanel;
	private boolean tableIsVisible = false;
	private JButton btnNew;
	private JButton btnEdit;
	private JButton btnDelete;

	public AddressTable(AddressBook book) {
		
		this.book = book;
		controller = new Controller(book);
		menuBar = new JMenuBar();
		
		menu = new JMenu("File");
		menuBar.add(menu);
		
		
		menuItem = new JMenuItem("New");
		menuItem.setActionCommand("NEWFILE");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Open File...");
		menuItem.setActionCommand("READ");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Save");
		menuItem.setActionCommand("SAVE");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Save As...");
		menuItem.setActionCommand("SAVEAS");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Exit");
		menuItem.setActionCommand("EXIT");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		
		btnNew = new JButton("New Value");
			btnNew.setActionCommand("NEW");
			btnNew.addActionListener(this);
		btnEdit = new JButton("Edit");
			btnEdit.setActionCommand("EDIT");
			btnEdit.addActionListener(this);
			btnEdit.setEnabled(false);
		btnDelete = new JButton("Delete");
			btnDelete.setActionCommand("DELETE");
			btnDelete.addActionListener(this);
			btnDelete.setEnabled(false);
		btnPanel = new JPanel(new GridLayout(0,3));
		btnPanel.add(btnDelete);
		btnPanel.add(btnEdit);
		btnPanel.add(btnNew);
		
		add(btnPanel,BorderLayout.SOUTH);
		
		btnPanel.setVisible(false);
		
		setSize(1030, 600);
		setJMenuBar(menuBar);
		setLocationRelativeTo(null);
		setIconImage(img.getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		book.addListener(this);
	
	}
	
	public void createTablePanel() {
		
		tableModel = new TableModel(book);
		btnPanel.setVisible(true);
		tableIsVisible = true;
		
		table = new JTable(tableModel);
			table.setAutoCreateRowSorter(true);
			table.getSelectionModel().addListSelectionListener(new ListListener());
		
		JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(1000, 500));
		
		tablePanel = new JPanel();
		
		tablePanel.add(scrollPane);
		
		add(tablePanel,BorderLayout.CENTER);
		
	}
	
	class ListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			btnDelete.setEnabled(true);
			btnEdit.setEnabled(true);
			
		}
		
	}
	
	public void removeTablePanel() {
		
		remove(tablePanel);
		
	}
	
	@Override
	public void userAdded() {
		
		tableModel.fireTableDataChanged();
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);
		
		
	}

	@Override
	public void userRemoved() {
		
		tableModel.fireTableDataChanged();
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);
		
	}

	@Override
	public void userChanged() {
		
		tableModel.fireTableDataChanged();
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String action = e.getActionCommand();
		
		switch(action) {
		
		case "NEW":
			new ValueInputWindow(this, book);
			
			break;
			
		case "DELETE":
				
			if(table.getSelectedRowCount() != 1) {
					
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,
						"Please select one row!",
						"Warning",
				JOptionPane.WARNING_MESSAGE);
					
				}
			
			else {
					
				new DeleteMessage(this, book, table);	
				
			}
			
			break;
			
		case "EDIT":
			
			if(table.getSelectedRowCount() != 1) {
				
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,
						"Please select one row!",
						"Warning",
				JOptionPane.WARNING_MESSAGE);
					
				}
			
			else {
					
				new ValueEditWindow(this, book, table);	
				
			}
			
			break;
			
		case "READ":
			if(controller.openFileChooser() == true) {
				if(createTableCounter > 0) {
					removeTablePanel();
				}
				createTablePanel();
				createTableCounter++;
				SwingUtilities.updateComponentTreeUI(this);
			}
			
			break;
			
		case "NEWFILE":
			
			if(createTableCounter > 0) {
				removeTablePanel();
			}
			book.setEmptyAddressBook();
			controller.setEmptySelectedFile();
			createTablePanel();
			createTableCounter++;
			SwingUtilities.updateComponentTreeUI(this);
			break;
		
		case "SAVEAS":
			
			if(tableIsVisible == true) {
				controller.openSaveAsDirectory();
			}else {
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame,
						"Please open a file first!",
						"Warning",
				JOptionPane.WARNING_MESSAGE);
			}
			break;
			
		case "SAVE":
			
			if(tableIsVisible == true) {
				controller.openSaveAsDirectory();
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
	
	public static void main(String[] args) {
		
		AddressBook book = new AddressBook();
		new AddressTable(book);
		
	}

}
