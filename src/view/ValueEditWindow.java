package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.AddressBook;
import model.User;

public class ValueEditWindow extends JDialog implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtStudiengang;
	private JTextField txtNachname;
	private JTextField txtVorname;
	private JTextField txtPID;
	private JButton btnNewButton;
	private JButton btnCancel;
	private JCheckBox chcbxVegan;
	private AddressBook book;
	@SuppressWarnings("unused")
	private JTable table;
	private int index;
	private static Logger logger = LogManager.getRootLogger();

	public ValueEditWindow(JFrame parent, AddressBook book, JTable table) {
		super(parent, true);
		this.book = book;
		this.table = table;
		setTitle("Edit Entry");
		
		index = table.convertRowIndexToModel(table.getSelectedRow());
		ArrayList<User> users = book.getAddressBook();
		User user = users.get(index);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{14, 118, 68, 78, 83, 54, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		txtStudiengang = new JTextField();
		txtStudiengang.setText(user.getStudiengang());
		GridBagConstraints gbc_txtStudiengang = new GridBagConstraints();
		gbc_txtStudiengang.insets = new Insets(0, 0, 5, 5);
		gbc_txtStudiengang.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStudiengang.gridx = 1;
		gbc_txtStudiengang.gridy = 2;
		getContentPane().add(txtStudiengang, gbc_txtStudiengang);
		txtStudiengang.setColumns(10);
		
		txtNachname = new JTextField();
		txtNachname.setText(user.getNachname());
		GridBagConstraints gbc_txtNachname = new GridBagConstraints();
		gbc_txtNachname.insets = new Insets(0, 0, 5, 5);
		gbc_txtNachname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNachname.gridx = 2;
		gbc_txtNachname.gridy = 2;
		getContentPane().add(txtNachname, gbc_txtNachname);
		txtNachname.setColumns(10);
		
		txtVorname = new JTextField();
		txtVorname.setText(user.getVorname());
		GridBagConstraints gbc_txtVorname = new GridBagConstraints();
		gbc_txtVorname.insets = new Insets(0, 0, 5, 5);
		gbc_txtVorname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtVorname.gridx = 3;
		gbc_txtVorname.gridy = 2;
		getContentPane().add(txtVorname, gbc_txtVorname);
		txtVorname.setColumns(10);
		
		txtPID = new JTextField();
		txtPID.setText(String.valueOf(user.getPid()));
		GridBagConstraints gbc_txtPID = new GridBagConstraints();
		gbc_txtPID.insets = new Insets(0, 0, 5, 5);
		gbc_txtPID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPID.gridx = 4;
		gbc_txtPID.gridy = 2;
		getContentPane().add(txtPID, gbc_txtPID);
		txtPID.setColumns(10);
		
		chcbxVegan = new JCheckBox("Vegan?");
		chcbxVegan.setSelected(user.getVegan());
		GridBagConstraints gbc_chcbxVegan = new GridBagConstraints();
		gbc_chcbxVegan.insets = new Insets(0, 0, 5, 0);
		gbc_chcbxVegan.gridx = 5;
		gbc_chcbxVegan.gridy = 2;
		getContentPane().add(chcbxVegan, gbc_chcbxVegan);
		
		btnCancel = new JButton("Cancel");
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 4;
		gbc_btnCancel.gridy = 7;
		getContentPane().add(btnCancel, gbc_btnCancel);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
			}
			
		});
		
		btnNewButton = new JButton("Save");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 7;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		btnNewButton.addActionListener(this);
		
		pack();
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public static boolean isNumeric(String str)  {
		
		logger.debug("Testing if {} is numeric", str);
		try  {
			@SuppressWarnings("unused")
			int i = Integer.parseInt(str); 
		} catch(NumberFormatException nfe) {
			return false;
		}
		
		return true;  
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(!txtStudiengang.getText().isEmpty()&&!txtNachname.getText().isEmpty()&&!txtVorname.getText().isEmpty()&&isNumeric(txtPID.getText())) {
		
			String studiengang = txtStudiengang.getText();
			String nachname = txtNachname.getText();
			String vorname = txtVorname.getText();
			int pid = Integer.parseInt(txtPID.getText());
			boolean vegan = chcbxVegan.isSelected();
			
			User user = new User(studiengang, nachname, vorname, pid, vegan);
			
			book.updateUser(user, index);
			
			dispose();
			
		} else if(!isNumeric(txtPID.getText())){
			
			logger.warn("PID was not numeric");
			JDialog dialog = new JDialog();
			JOptionPane.showMessageDialog(dialog,
					"PID must be a number!",
					"Warning",
			JOptionPane.WARNING_MESSAGE);
			
		} else {
			
			logger.warn("One or more fields were empty");
			JDialog dialog = new JDialog();
			JOptionPane.showMessageDialog(dialog,
					"Fields cannot be empty!",
					"Warning",
			JOptionPane.WARNING_MESSAGE);
		}
		
	} 

}
