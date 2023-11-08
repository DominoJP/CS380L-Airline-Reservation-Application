import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
   JPanel for Passenger Details, based on aa.com.
   @author Jevy Miranda
   @version 1.0
*/

public class PassengerDetailsPane extends JPanel implements PropertyChangeListener {
	
	private int selectedPassengerAmount;
	private Flight selectedFlight;
	private String selectedCabin;
	
	private PropertyChangeSupport support;

	private static final long serialVersionUID = 1L;
	private JTextField textName;
	private JTextField textDoB;
	private JComboBox comboBoxState;
	String[] DepartmentofStateGenderMarkerArray = {
			"Male (M)",
			"Female (F)",
			"Unspecified (X)",
			"Undisclosed (U)"
	};
	String[] cabinArray = {
			"Economy",
			"First Class"
	};
 	
	//FIXME: populate from .txt
	String[] countryArray = {
		"Afghanistan",
		"United States"
	};
	//FIXME: populate from .txt
	String[] stateArray = {
			"California"
		};

	public PassengerDetailsPane(JPanel contentPane, int passengerIndex, String nextPassengerDetailsPane, Flight flight) {
		support = new PropertyChangeSupport(this);
		selectedPassengerAmount = 1;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{168, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPassengerNumber = new JLabel(" Passenger " + passengerIndex);
		lblPassengerNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		GridBagConstraints gbc_lblPassengerNumber = new GridBagConstraints();
		gbc_lblPassengerNumber.anchor = GridBagConstraints.WEST;
		gbc_lblPassengerNumber.gridheight = 2;
		gbc_lblPassengerNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassengerNumber.gridx = 0;
		gbc_lblPassengerNumber.gridy = 0;
		add(lblPassengerNumber, gbc_lblPassengerNumber);
		
		JLabel lblCabin = new JLabel(" Cabin");
		GridBagConstraints gbc_lblCabin = new GridBagConstraints();
		gbc_lblCabin.anchor = GridBagConstraints.WEST;
		gbc_lblCabin.insets = new Insets(0, 0, 5, 5);
		gbc_lblCabin.gridx = 2;
		gbc_lblCabin.gridy = 0;
		add(lblCabin, gbc_lblCabin);
		
		JComboBox comboBoxCabin = new JComboBox(cabinArray);
		GridBagConstraints gbc_comboBoxCabin = new GridBagConstraints();
		gbc_comboBoxCabin.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCabin.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCabin.gridx = 2;
		gbc_comboBoxCabin.gridy = 1;
		add(comboBoxCabin, gbc_comboBoxCabin);
		
		JLabel lblFullName = new JLabel(" Full Name");
		GridBagConstraints gbc_lblFullName = new GridBagConstraints();
		gbc_lblFullName.anchor = GridBagConstraints.WEST;
		gbc_lblFullName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFullName.gridx = 0;
		gbc_lblFullName.gridy = 2;
		add(lblFullName, gbc_lblFullName);
		
		JLabel lblGender = new JLabel(" Gender");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		gbc_lblGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblGender.gridx = 2;
		gbc_lblGender.gridy = 2;
		add(lblGender, gbc_lblGender);
		
		textName = new JTextField();
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.insets = new Insets(0, 0, 5, 5);
		gbc_textName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textName.gridx = 0;
		gbc_textName.gridy = 3;
		add(textName, gbc_textName);
		textName.setColumns(10);
		
		JComboBox comboBox = new JComboBox(DepartmentofStateGenderMarkerArray);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 3;
		add(comboBox, gbc_comboBox);
		
		JLabel lblFullNameAndGender = new JLabel("  Enter name and gender as printed on government-issued photo ID");
		lblFullNameAndGender.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblFullNameAndGender = new GridBagConstraints();
		gbc_lblFullNameAndGender.anchor = GridBagConstraints.WEST;
		gbc_lblFullNameAndGender.gridwidth = 3;
		gbc_lblFullNameAndGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblFullNameAndGender.gridx = 0;
		gbc_lblFullNameAndGender.gridy = 4;
		add(lblFullNameAndGender, gbc_lblFullNameAndGender);
		
		JSeparator separatorFullName = new JSeparator();
		GridBagConstraints gbc_separatorFullName = new GridBagConstraints();
		gbc_separatorFullName.insets = new Insets(0, 0, 5, 5);
		gbc_separatorFullName.gridx = 0;
		gbc_separatorFullName.gridy = 5;
		add(separatorFullName, gbc_separatorFullName);
		
		JLabel lblDoB = new JLabel(" Date of Birth (mm/dd/yyyy)");
		GridBagConstraints gbc_lblDoB = new GridBagConstraints();
		gbc_lblDoB.anchor = GridBagConstraints.WEST;
		gbc_lblDoB.insets = new Insets(0, 0, 5, 5);
		gbc_lblDoB.gridx = 0;
		gbc_lblDoB.gridy = 6;
		add(lblDoB, gbc_lblDoB);
		
		textDoB = new JTextField();
		GridBagConstraints gbc_textDoB = new GridBagConstraints();
		gbc_textDoB.insets = new Insets(0, 0, 5, 5);
		gbc_textDoB.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDoB.gridx = 0;
		gbc_textDoB.gridy = 7;
		add(textDoB, gbc_textDoB);
		textDoB.setColumns(10);
		
		JLabel lblDoBInvalidDateFormat = new JLabel("Invalid date format.");
		lblDoBInvalidDateFormat.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblDoBInvalidDateFormat.setForeground(Color.RED);
		GridBagConstraints gbc_lblDoBInvalidDateFormat = new GridBagConstraints();
		gbc_lblDoBInvalidDateFormat.insets = new Insets(0, 0, 5, 5);
		gbc_lblDoBInvalidDateFormat.gridx = 2;
		gbc_lblDoBInvalidDateFormat.gridy = 7;
		add(lblDoBInvalidDateFormat, gbc_lblDoBInvalidDateFormat);
		lblDoBInvalidDateFormat.setVisible(false);
		
		JSeparator separatorDoB = new JSeparator();
		GridBagConstraints gbc_separatorDoB = new GridBagConstraints();
		gbc_separatorDoB.gridwidth = 3;
		gbc_separatorDoB.insets = new Insets(0, 0, 5, 5);
		gbc_separatorDoB.gridx = 0;
		gbc_separatorDoB.gridy = 8;
		add(separatorDoB, gbc_separatorDoB);
		
		JLabel lblCountry = new JLabel(" Country or Region of Residence");
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.anchor = GridBagConstraints.WEST;
		gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx = 0;
		gbc_lblCountry.gridy = 9;
		add(lblCountry, gbc_lblCountry);
		
		JLabel lblState = new JLabel(" State of Residence");
		GridBagConstraints gbc_lblState = new GridBagConstraints();
		gbc_lblState.anchor = GridBagConstraints.WEST;
		gbc_lblState.insets = new Insets(0, 0, 5, 5);
		gbc_lblState.gridx = 2;
		gbc_lblState.gridy = 9;
		add(lblState, gbc_lblState);
		// lblState
		lblState.setVisible(false);
		
		JComboBox comboBoxCountry = new JComboBox(countryArray);
		comboBoxCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxCountry.getSelectedItem().toString().equals("United States")) {
					lblState.setVisible(true);
					comboBoxState.setVisible(true);
				}
			}
		});
		GridBagConstraints gbc_comboBoxCountry = new GridBagConstraints();
		gbc_comboBoxCountry.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCountry.gridx = 0;
		gbc_comboBoxCountry.gridy = 10;
		add(comboBoxCountry, gbc_comboBoxCountry);
		
		comboBoxState = new JComboBox(stateArray);
		GridBagConstraints gbc_comboBoxState = new GridBagConstraints();
		gbc_comboBoxState.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxState.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxState.gridx = 2;
		gbc_comboBoxState.gridy = 10;
		add(comboBoxState, gbc_comboBoxState);
		
		JButton btnPlaceholder = new JButton("Placeholder");
		GridBagConstraints gbc_btnPlaceholder = new GridBagConstraints();
		gbc_btnPlaceholder.anchor = GridBagConstraints.WEST;
		gbc_btnPlaceholder.insets = new Insets(0, 0, 0, 5);
		gbc_btnPlaceholder.gridx = 0;
		gbc_btnPlaceholder.gridy = 11;
		add(btnPlaceholder, gbc_btnPlaceholder);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// FIXME: LOGIC TO KEEP COUNT OF PASSENGERS
				if (passengerIndex == selectedPassengerAmount) {
					support.firePropertyChange("sumRunningTotal", null, null);
					((CardLayout) contentPane.getLayout()).show(contentPane, "PAY");
				}
				else {
					selectedCabin = comboBoxCabin.getSelectedItem().toString();
					support.firePropertyChange("selectedCabin" + passengerIndex, null, selectedCabin);
					((CardLayout) contentPane.getLayout()).show(contentPane, nextPassengerDetailsPane);
				}
			}
		});
		GridBagConstraints gbc_btnContinue = new GridBagConstraints();
		gbc_btnContinue.anchor = GridBagConstraints.EAST;
		gbc_btnContinue.insets = new Insets(0, 0, 0, 5);
		gbc_btnContinue.gridx = 2;
		gbc_btnContinue.gridy = 11;
		add(btnContinue, gbc_btnContinue);
		// comboBoxState
		comboBoxState.setVisible(false);
		
	}
	
	

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ((evt.getPropertyName()).equals("passengerAmount")) {
			this.selectedPassengerAmount = ((int) evt.getNewValue());
		}
		
		if ((evt.getPropertyName()).equals("selectedFlight")) {
			this.selectedFlight = ((Flight) evt.getNewValue());
			System.out.println("selectedFlight PropertyChangeEvent");
		}
		
	}

}