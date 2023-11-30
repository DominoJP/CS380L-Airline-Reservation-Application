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
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

/**
 * The ManagerPane class is a GUI panel that allows managers who are logged in to pull up documentation
 * on what revenue was earned by the company. Either total revenue earned during the companies entire existence,
 * earned over a period of time, or by a specific customer.
 * @author Logan Langewisch
 * @version 1.4, Date Last Modified: November 29, 2023
 */
public class ManagerPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textCustomer;

	/**
	 * Create the panel.
	 */
	public ManagerPane(JPanel contentPane, Manager owner) {
		owner.getReservations();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{140, 0, 42, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel lblOptions = new JLabel("Options");
		GridBagConstraints gbc_lblOptions = new GridBagConstraints();
		gbc_lblOptions.insets = new Insets(0, 0, 5, 5);
		gbc_lblOptions.gridx = 0;
		gbc_lblOptions.gridy = 3;
		add(lblOptions, gbc_lblOptions);
		
		JComboBox comboBoxOptions = new JComboBox();
		comboBoxOptions.setModel(new DefaultComboBoxModel(new String[] {"Get Total Revenue", "Get Customer Revenue", "Get Partial Revenue"}));
		GridBagConstraints gbc_comboBoxOptions = new GridBagConstraints();
		gbc_comboBoxOptions.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxOptions.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOptions.gridx = 2;
		gbc_comboBoxOptions.gridy = 3;
		add(comboBoxOptions, gbc_comboBoxOptions);
		
		JLabel lblCustomer = new JLabel("Customer");
		GridBagConstraints gbc_lblCustomer = new GridBagConstraints();
		gbc_lblCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomer.gridx = 0;
		gbc_lblCustomer.gridy = 5;
		add(lblCustomer, gbc_lblCustomer);
		lblCustomer.setVisible(false);
		
		textCustomer = new JTextField();
		GridBagConstraints gbc_textCustomer = new GridBagConstraints();
		gbc_textCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_textCustomer.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCustomer.gridx = 2;
		gbc_textCustomer.gridy = 5;
		add(textCustomer, gbc_textCustomer);
		textCustomer.setColumns(10);
		textCustomer.setVisible(false);
		
		JLabel lblRevenue = new JLabel("Revenue");
		GridBagConstraints gbc_lblRevenue = new GridBagConstraints();
		gbc_lblRevenue.anchor = GridBagConstraints.SOUTH;
		gbc_lblRevenue.insets = new Insets(0, 0, 5, 5);
		gbc_lblRevenue.gridx = 0;
		gbc_lblRevenue.gridy = 7;
		add(lblRevenue, gbc_lblRevenue);
		
		JLabel lblCurrentRevenue = new JLabel("");
		GridBagConstraints gbc_lblCurrentRevenue = new GridBagConstraints();
		gbc_lblCurrentRevenue.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentRevenue.gridx = 2;
		gbc_lblCurrentRevenue.gridy = 7;
		add(lblCurrentRevenue, gbc_lblCurrentRevenue);
		
		JButton btnSelect = new JButton("Select");
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelect.gridx = 1;
		gbc_btnSelect.gridy = 9;
		add(btnSelect, gbc_btnSelect);
		
		comboBoxOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBoxOptions.getSelectedItem().toString().equals("Get Customer Revenue")) {
					lblCustomer.setVisible(true);
					textCustomer.setVisible(true);
				}else {
					lblCustomer.setVisible(false);
					textCustomer.setVisible(false);
				}
			}
		});
		
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(comboBoxOptions.getSelectedItem().toString()){
				case "Get Total Revenue":
					lblCurrentRevenue.setText(owner.getTotalRevenue().toString());
					break;
				case "Get Customer Revenue":
					owner.getAllAccounts();
					lblCurrentRevenue.setText(owner.getAccountRevenue(textCustomer.getText().toString()).toString());
					break;
				case "Get Partial Revenue":
					break;
				}
			}
		});
		
		owner.totalReservations();
		
		
	}
}
