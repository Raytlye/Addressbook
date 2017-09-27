package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import model.AddressBook;
import model.User;

public class ValueInputWindow extends JDialog implements ActionListener {
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
	
	public ValueInputWindow(JFrame parent, AddressBook book) {
		super(parent, true);
		this.book = book;
		setTitle("New Entry");
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{14, 118, 68, 78, 83, 54, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		txtStudiengang = new JTextField();
		txtStudiengang.setText("Studiengang");
		GridBagConstraints gbc_txtStudiengang = new GridBagConstraints();
		gbc_txtStudiengang.insets = new Insets(0, 0, 5, 5);
		gbc_txtStudiengang.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtStudiengang.gridx = 1;
		gbc_txtStudiengang.gridy = 2;
		getContentPane().add(txtStudiengang, gbc_txtStudiengang);
		txtStudiengang.setColumns(10);
		txtStudiengang.addMouseListener(new MouseAdapter() {
			 @Override
	            public void mouseClicked(MouseEvent e){
	                txtStudiengang.setText("");
	            }
		});
		
		txtNachname = new JTextField();
		txtNachname.setText("Nachname");
		GridBagConstraints gbc_txtNachname = new GridBagConstraints();
		gbc_txtNachname.insets = new Insets(0, 0, 5, 5);
		gbc_txtNachname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNachname.gridx = 2;
		gbc_txtNachname.gridy = 2;
		getContentPane().add(txtNachname, gbc_txtNachname);
		txtNachname.setColumns(10);
		txtNachname.addMouseListener(new MouseAdapter() {
			 @Override
	            public void mouseClicked(MouseEvent e){
	                txtNachname.setText("");
	            }
		});
		
		txtVorname = new JTextField();
		txtVorname.setText("Vorname");
		GridBagConstraints gbc_txtVorname = new GridBagConstraints();
		gbc_txtVorname.insets = new Insets(0, 0, 5, 5);
		gbc_txtVorname.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtVorname.gridx = 3;
		gbc_txtVorname.gridy = 2;
		getContentPane().add(txtVorname, gbc_txtVorname);
		txtVorname.setColumns(10);
		txtVorname.addMouseListener(new MouseAdapter() {
			 @Override
	            public void mouseClicked(MouseEvent e){
	                txtVorname.setText("");
	            }
		});
		
		txtPID = new JTextField();
		txtPID.setText("PID (Zahl)");
		GridBagConstraints gbc_txtPID = new GridBagConstraints();
		gbc_txtPID.insets = new Insets(0, 0, 5, 5);
		gbc_txtPID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPID.gridx = 4;
		gbc_txtPID.gridy = 2;
		getContentPane().add(txtPID, gbc_txtPID);
		txtPID.setColumns(10);
		txtPID.addMouseListener(new MouseAdapter() {
			 @Override
	            public void mouseClicked(MouseEvent e){
	                txtPID.setText("");
	            }
		});
		
		chcbxVegan = new JCheckBox("Vegan?");
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String studiengang = txtStudiengang.getText();
		String nachname = txtNachname.getText();
		String vorname = txtVorname.getText();
		int pid = Integer.parseInt(txtPID.getText());
		boolean vegan = chcbxVegan.isSelected();
		
		User user = new User(studiengang, nachname, vorname, pid, vegan);
		book.addUser(user);
		
		this.setVisible(false);
	}

}
