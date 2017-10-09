package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.AddressBook;
import model.AddressBookListener;
import model.Controller;
import model.ListListener;

public class AddressTable extends JFrame implements AddressBookListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TableModel tableModel;
	private AddressBook book;
	public JTable table;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private JPanel tablePanel;
	private ImageIcon img = new ImageIcon("database.png");
	private Controller controller;
	public int createTableCounter = 0;
	private JPanel btnPanel;
	public boolean tableIsVisible = false;
	private JButton btnNew;
	public JButton btnEdit;
	public JButton btnDelete;
	private ListListener listener;
	
	private static Logger logger;

	public AddressTable(AddressBook book) {
		
		logger = LogManager.getRootLogger();
		logger.debug("Creating JFrame AddressTable");
		this.book = book;
		controller = new Controller(book, this);
		listener = new ListListener(this);
		menuBar = new JMenuBar();
		
		menu = new JMenu("File");
		menuBar.add(menu);
		
		
		menuItem = new JMenuItem("New");
		menuItem.setActionCommand("NEWFILE");
		menuItem.addActionListener(controller);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Open File...");
		menuItem.setActionCommand("READ");
		menuItem.addActionListener(controller);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Save");
		menuItem.setActionCommand("SAVE");
		menuItem.addActionListener(controller);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Save As...");
		menuItem.setActionCommand("SAVEAS");
		menuItem.addActionListener(controller);
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Exit");
		menuItem.setActionCommand("EXIT");
		menuItem.addActionListener(controller);
		menu.add(menuItem);
		
		btnNew = new JButton("New Value");
			btnNew.setActionCommand("NEW");
			btnNew.addActionListener(controller);
		btnEdit = new JButton("Edit");
			btnEdit.setActionCommand("EDIT");
			btnEdit.addActionListener(controller);
			btnEdit.setEnabled(false);
		btnDelete = new JButton("Delete");
			btnDelete.setActionCommand("DELETE");
			btnDelete.addActionListener(controller);
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
			table.getSelectionModel().addListSelectionListener(listener);
		
		JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(1000, 500));
		
		tablePanel = new JPanel();
		
		tablePanel.add(scrollPane);
		
		tablePanel.setName("Addressbook Panel");
		logger.debug("Creating TablePanel: {}", tablePanel.getName());
		
		add(tablePanel,BorderLayout.CENTER);
		
	}
	
	public void removeTablePanel() {
		
		logger.debug("Removing {}", tablePanel.getName());
		remove(tablePanel);
		
	}

	@Override
	public void userChanged() {
		
		logger.debug("Refreshing tableModel and disabling buttons: {} {}", btnDelete.getName(), btnEdit.getName());
		tableModel.fireTableDataChanged();
		btnDelete.setEnabled(false);
		btnEdit.setEnabled(false);
		
	}
	
	public static void main(String[] args) {
		
		AddressBook book = new AddressBook();
		new AddressTable(book);
		
	}

}
