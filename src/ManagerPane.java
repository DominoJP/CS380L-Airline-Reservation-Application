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
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.TimeZone;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.SystemColor;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The ManagerPane class is a GUI panel that allows managers who are logged in to pull up documentation
 * on what revenue was earned by the company. Either total revenue earned during the companies entire existence,
 * earned over a period of time, or by a specific customer.
 * @author Logan Langewisch
 * @version 2.0, Date Last Modified: December 3, 2023
 */
public class ManagerPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textCustomer;
	private ZonedDateTime startDate;
	private ZonedDateTime endDate;
	private String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	private String[] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", 
							"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", 
							"24", "25", "26", "27", "28", "29", "30", "31"};
	private JTextField textStartYear;
	private JTextField textEndYear;

	/**
	 * Create the panel.
	 */
	public ManagerPane(JPanel contentPane, Manager owner) {
		owner.getReservations();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{70, 63, 57, 115, 62};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
		setLayout(gridBagLayout);
		
		JLabel lblOptions = new JLabel("Options:");
		GridBagConstraints gbc_lblOptions = new GridBagConstraints();
		gbc_lblOptions.anchor = GridBagConstraints.WEST;
		gbc_lblOptions.insets = new Insets(0, 0, 5, 5);
		gbc_lblOptions.gridx = 1;
		gbc_lblOptions.gridy = 1;
		add(lblOptions, gbc_lblOptions);
		
		JComboBox comboBoxOptions = new JComboBox();
		comboBoxOptions.setModel(new DefaultComboBoxModel(new String[] {"Get Total Revenue", "Get Customer Revenue", "Get Partial Revenue"}));
		GridBagConstraints gbc_comboBoxOptions = new GridBagConstraints();
		gbc_comboBoxOptions.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOptions.gridwidth = 2;
		gbc_comboBoxOptions.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxOptions.gridx = 2;
		gbc_comboBoxOptions.gridy = 1;
		add(comboBoxOptions, gbc_comboBoxOptions);
		
		JLabel lblCustomer = new JLabel("Customer ID:");
		GridBagConstraints gbc_lblCustomer = new GridBagConstraints();
		gbc_lblCustomer.gridwidth = 2;
		gbc_lblCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_lblCustomer.gridx = 1;
		gbc_lblCustomer.gridy = 3;
		add(lblCustomer, gbc_lblCustomer);
		lblCustomer.setVisible(false);
		
		textCustomer = new JTextField();
		GridBagConstraints gbc_textCustomer = new GridBagConstraints();
		gbc_textCustomer.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCustomer.insets = new Insets(0, 0, 5, 5);
		gbc_textCustomer.gridx = 3;
		gbc_textCustomer.gridy = 3;
		add(textCustomer, gbc_textCustomer);
		textCustomer.setColumns(10);
		textCustomer.setVisible(false);
		
		JLabel lblStart = new JLabel(" From (MM/DD/YYYY)");
		GridBagConstraints gbc_lblStart = new GridBagConstraints();
		gbc_lblStart.gridwidth = 3;
		gbc_lblStart.anchor = GridBagConstraints.WEST;
		gbc_lblStart.insets = new Insets(0, 0, 5, 5);
		gbc_lblStart.gridx = 1;
		gbc_lblStart.gridy = 4;
		add(lblStart, gbc_lblStart);
		lblStart.setVisible(false);
		
		JComboBox comboBoxStartMonth = new JComboBox(months);
		GridBagConstraints gbc_comboBoxStartMonth = new GridBagConstraints();
		gbc_comboBoxStartMonth.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxStartMonth.gridx = 1;
		gbc_comboBoxStartMonth.gridy = 5;
		add(comboBoxStartMonth, gbc_comboBoxStartMonth);
		comboBoxStartMonth.setVisible(false);
		
		JComboBox comboBoxStartDay = new JComboBox(days);
		GridBagConstraints gbc_comboBoxStartDay = new GridBagConstraints();
		gbc_comboBoxStartDay.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxStartDay.gridx = 2;
		gbc_comboBoxStartDay.gridy = 5;
		add(comboBoxStartDay, gbc_comboBoxStartDay);
		comboBoxStartDay.setVisible(false);
		
		textStartYear = new JTextField();
		GridBagConstraints gbc_textStartYear = new GridBagConstraints();
		gbc_textStartYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_textStartYear.insets = new Insets(0, 0, 5, 5);
		gbc_textStartYear.gridx = 3;
		gbc_textStartYear.gridy = 5;
		add(textStartYear, gbc_textStartYear);
		textStartYear.setColumns(10);
		textStartYear.setVisible(false);
		
		JLabel lblEnd = new JLabel(" To (MM/DD/YYYY)");
		GridBagConstraints gbc_lblEnd = new GridBagConstraints();
		gbc_lblEnd.gridwidth = 4;
		gbc_lblEnd.anchor = GridBagConstraints.WEST;
		gbc_lblEnd.insets = new Insets(0, 0, 5, 0);
		gbc_lblEnd.gridx = 1;
		gbc_lblEnd.gridy = 6;
		add(lblEnd, gbc_lblEnd);
		lblEnd.setVisible(false);
		
		JComboBox comboBoxEndMonth = new JComboBox(months);
		GridBagConstraints gbc_comboBoxEndMonth = new GridBagConstraints();
		gbc_comboBoxEndMonth.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEndMonth.gridx = 1;
		gbc_comboBoxEndMonth.gridy = 7;
		add(comboBoxEndMonth, gbc_comboBoxEndMonth);
		comboBoxEndMonth.setVisible(false);
		
		JComboBox comboBoxEndDay = new JComboBox(days);
		GridBagConstraints gbc_comboBoxEndDay = new GridBagConstraints();
		gbc_comboBoxEndDay.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEndDay.gridx = 2;
		gbc_comboBoxEndDay.gridy = 7;
		add(comboBoxEndDay, gbc_comboBoxEndDay);
		comboBoxEndDay.setVisible(false);
		
		textEndYear = new JTextField();
		GridBagConstraints gbc_textEndYear = new GridBagConstraints();
		gbc_textEndYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_textEndYear.insets = new Insets(0, 0, 5, 5);
		gbc_textEndYear.gridx = 3;
		gbc_textEndYear.gridy = 7;
		add(textEndYear, gbc_textEndYear);
		textEndYear.setColumns(10);
		textEndYear.setVisible(false);
		
		JLabel lblRevenue = new JLabel("Revenue:");
		GridBagConstraints gbc_lblRevenue = new GridBagConstraints();
		gbc_lblRevenue.anchor = GridBagConstraints.WEST;
		gbc_lblRevenue.insets = new Insets(0, 0, 5, 5);
		gbc_lblRevenue.gridx = 1;
		gbc_lblRevenue.gridy = 9;
		add(lblRevenue, gbc_lblRevenue);
		
		JLabel lblCurrentRevenue = new JLabel("");
		GridBagConstraints gbc_lblCurrentRevenue = new GridBagConstraints();
		gbc_lblCurrentRevenue.insets = new Insets(0, 0, 5, 5);
		gbc_lblCurrentRevenue.gridx = 2;
		gbc_lblCurrentRevenue.gridy = 9;
		add(lblCurrentRevenue, gbc_lblCurrentRevenue);
		
		JButton btnGenerate = new JButton("Generate");
		GridBagConstraints gbc_btnGenerate = new GridBagConstraints();
		gbc_btnGenerate.anchor = GridBagConstraints.EAST;
		gbc_btnGenerate.insets = new Insets(0, 0, 5, 5);
		gbc_btnGenerate.gridx = 3;
		gbc_btnGenerate.gridy = 9;
		add(btnGenerate, gbc_btnGenerate);
		
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(comboBoxOptions.getSelectedItem().toString()){
				case "Get Total Revenue":
					lblCurrentRevenue.setText(owner.getTotalRevenue().toString());
					break;
				case "Get Customer Revenue":
					owner.getAllAccounts();
					lblCurrentRevenue.setText(owner.getAccountRevenue(Integer.parseInt(textCustomer.getText())).toString());
					break;
				case "Get Partial Revenue":
					String startMDY = textStartYear.getText() + "-" + comboBoxStartMonth.getSelectedItem().toString() + "-"
										+ comboBoxStartDay.getSelectedItem().toString();
					int endD = Integer.parseInt(comboBoxEndDay.getSelectedItem().toString()) + 1;
					String endDFormatted = String.format("%02d", endD);
					String endMDY = textEndYear.getText() + "-" + comboBoxEndMonth.getSelectedItem().toString() + "-" + endDFormatted;
					
					LocalDate startDate = LocalDate.parse(startMDY);
					ZonedDateTime start = startDate.atStartOfDay(ZoneId.of(ZoneId.systemDefault().toString()));
					
					LocalDate endDate = LocalDate.parse(endMDY);
					ZonedDateTime end = endDate.atStartOfDay(ZoneId.of(ZoneId.systemDefault().toString()));
					
					// startDate = ZonedDateTime.parse(startMDY + "T00:00:00+00:00" + "[" + ZoneId.systemDefault().toString() + "]");
					// endDate = ZonedDateTime.parse(endMDY + "T23:59:59+00:00" + "[" +ZoneId.systemDefault().toString() + "]");
					
					lblCurrentRevenue.setText(owner.getPartialRevenue(start, end).toString());
					break;
				}
			}
		});
		
		comboBoxOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblCurrentRevenue.setText("");
				
				lblCustomer.setVisible(false);
				textCustomer.setVisible(false);
				lblStart.setVisible(false);
				// lblMDYStart.setVisible(false);
				comboBoxStartMonth.setVisible(false);
				comboBoxStartDay.setVisible(false);
				textStartYear.setVisible(false);
				lblEnd.setVisible(false);
				// lblMDYEnd.setVisible(false);
				comboBoxEndMonth.setVisible(false);
				comboBoxEndDay.setVisible(false);
				textEndYear.setVisible(false);
				
				if(comboBoxOptions.getSelectedItem().toString().equals("Get Customer Revenue")) {
					lblCustomer.setVisible(true);
					textCustomer.setVisible(true);
					
					textCustomer.setText("");
				}else if(comboBoxOptions.getSelectedItem().toString().equals("Get Partial Revenue")) {
					lblStart.setVisible(true);
					// lblMDYStart.setVisible(true);
					comboBoxStartMonth.setVisible(true);
					comboBoxStartDay.setVisible(true);
					textStartYear.setVisible(true);
					lblEnd.setVisible(true);
					// lblMDYEnd.setVisible(true);
					comboBoxEndMonth.setVisible(true);
					comboBoxEndDay.setVisible(true);
					textEndYear.setVisible(true);
				}
			}
		});
		
		owner.totalReservations();
		
		
	}
}
