package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import model.AddressBook;
import model.User;

public class DeleteMessage extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AddressBook book;
	private JTable table;

	public DeleteMessage(JFrame parent, AddressBook book, JTable table) {
		
		super(parent, true);
		this.book = book;
		this.table = table;
		
		setSize(320,150);
		
		JLabel lblAreYouSure = new JLabel("Are you sure you want to delete the selected row?");
		lblAreYouSure.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblAreYouSure, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0,2));
		
		JButton btnNo = new JButton("No");
		panel.add(btnNo);
		btnNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
			}
			
		});
		
		JButton btnYes = new JButton("Yes");
		panel.add(btnYes);
		btnYes.addActionListener(this);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		ArrayList<User> users = book.getAddressBook();
		User user = users.get(table.convertRowIndexToModel(table.getSelectedRow()));
		book.removeUser(user);
		setVisible(false);
		
	}

}
