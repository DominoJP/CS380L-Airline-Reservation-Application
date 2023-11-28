import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;

import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.DefaultComboBoxModel;

public class ManagerPane extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ManagerPane(JPanel contentPane, Manager owner) {
		owner.getReservations();
		
		setLayout(null);
		
		JLabel lblOptions = new JLabel("Options");
		lblOptions.setBounds(36, 61, 55, 14);
		add(lblOptions);
		
		JComboBox comboBoxOptions = new JComboBox();
		comboBoxOptions.setModel(new DefaultComboBoxModel(new String[] {"Get Total Revenue", "Get Customer Revenue", "Review Reservations", "Review Customer Reservations"}));
		comboBoxOptions.setBounds(157, 57, 166, 22);
		add(comboBoxOptions);
		
		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setBounds(36, 124, 55, 14);
		add(lblCustomer);
		lblCustomer.setVisible(false);
		
		JTextPane textCustomerName = new JTextPane();
		textCustomerName.setBounds(157, 124, 166, 20);
		add(textCustomerName);
		textCustomerName.setVisible(false);
		
		JLabel lblRevenue = new JLabel("Revenue");
		lblRevenue.setBounds(36, 203, 46, 14);
		add(lblRevenue);
		
		JLabel lblCurrentRevenue = new JLabel("");
		lblCurrentRevenue.setBounds(157, 203, 166, 14);
		add(lblCurrentRevenue);
		
		JButton btnSelect = new JButton("Select");
		btnSelect.setBounds(157, 248, 89, 23);
		add(btnSelect);
		owner.totalReservations();
		
		comboBoxOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxOptions.getSelectedItem().toString().equals("Get Customer Revenue")) {
					lblCustomer.setVisible(true);
					textCustomerName.setVisible(true);
				}else {
					lblCustomer.setVisible(false);
					textCustomerName.setVisible(false);
				}
			}
		});
		
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(comboBoxOptions.getSelectedItem().toString()) {
				case "Get Total Revenue":
					lblCurrentRevenue.setText(owner.getTotalRevenue().toString());
					break;
				case "Get Partial Revenue":
					break;
				case "Get Customer Revenue":
					owner.getAllAccounts();
					lblCurrentRevenue.setText(owner.getAccountRevenue(textCustomerName.getText()).toString());
					break;
				case "Approve Refund":
					break;
				case "Cancel Reservation":
					break;
				}
			}
		});
	}
}
