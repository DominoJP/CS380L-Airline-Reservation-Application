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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

/**
 * Design Documentation: "PassengerInfoUI."
 * Description: JPanel subclass for passenger information. 
 * JTextFields for Full Name. 
 * JComboBoxes for Gender, Date of Birth, and Country of Residence.
 * <p>
 * Functions: fires PropertyChangeEvent "passengerName" for use by PaymentPane when booking.
 * PropertyChanges for "passengerAmount" determine the amount of PassengerPanes to be used.
 * Method checkMinor(), taking DoB as input from JComboBoxes, determines whether the current Passenger
 * is a minor (under 16 for airline accommodation purposes).
 * <p>
 * Data Structures: Arrays as input for gender, date of birth, and region of residence JComboBoxes.
 * Algorithms: N/A.
 * @version 3.2, Created: November 18, 2023
 * @author Jevy Miranda
 */
public class PassengerPane extends JPanel implements PropertyChangeListener {
	private static final long serialVersionUID = 1L;
	private int selectedPassengerAmount;
	private Flight selectedFlight;
	private String selectedCabin;
	
	private ArrayList<String> years = new ArrayList<>();
	private String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	private String[] days = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", 
								"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	
	private PropertyChangeSupport support;
	private JTextField textName;
	private JComboBox comboBox;
	private JComboBox comboBoxCountry;
	private JComboBox comboBoxState;
	private JComboBox comboBoxBirthDay;
	private JComboBox comboBoxBirthMonth;
	private JComboBox comboBoxBirthYear;
	
	private JLabel lblFullName;
	private JLabel lblDoBInvalidDateFormat;
	private String[] DepartmentofStateGenderMarkerArray = {
			"Male (M)",
			"Female (F)",
			"Unspecified (X)",
			"Undisclosed (U)"
	};
	private String[] cabinArray = {
			"Economy",
			"First Class"
	};
 	
	private String[] countryArray = {
		"United States",
		"Canada",
		"Mexico",
	};

	private String[] states = {"Alaska", "Alabama", "Arkansas", "American Samoa", "Arizona", "California", "Colorado", "Connecticut", 
	                   "District of Columbia", "Delaware", "Florida", "Georgia", "Guam", "Hawaii", "Iowa", "Idaho", "Illinois", 
	                   "Indiana", "Kansas", "Kentucky", "Louisiana", "Massachusetts", "Maryland", "Maine", "Michigan", 
	                   "Minnesota", "Missouri", "Mississippi", "Montana", "North Carolina", "North Dakota", "Nebraska", 
	                   "New Hampshire", "New Jersey", "New Mexico", "Nevada", "New York", "Ohio", "Oklahoma", "Oregon", 
	                   "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", 
	                   "Utah", "Virginia", "Virgin Islands", "Vermont", "Washington", "Wisconsin", "West Virginia", "Wyoming"};
	
	
	/**
	 * Constructor.
	 * @param contentPane
	 * @param passengerIndex
	 * @param previousPane
	 * @param nextPassengerDetailsPane
	 * @param flight
	 */
	public PassengerPane(JPanel contentPane, int passengerIndex, String previousPane, String nextPassengerDetailsPane, Flight flight) {
		support = new PropertyChangeSupport(this);
		selectedPassengerAmount = 1;
		
		for (int i = 1900; i <= ZonedDateTime.now().getYear(); i++) {
			years.add(String.valueOf(i));
		}
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{70, 71, 85, 20, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblPassengerNumber = new JLabel(" Passenger " + passengerIndex + " Details:");
		lblPassengerNumber.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		GridBagConstraints gbc_lblPassengerNumber = new GridBagConstraints();
		gbc_lblPassengerNumber.anchor = GridBagConstraints.WEST;
		gbc_lblPassengerNumber.gridwidth = 3;
		gbc_lblPassengerNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassengerNumber.gridx = 0;
		gbc_lblPassengerNumber.gridy = 0;
		add(lblPassengerNumber, gbc_lblPassengerNumber);
		
		JLabel lblRequired = new JLabel(" (* Required)");
		lblRequired.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		GridBagConstraints gbc_lblRequired = new GridBagConstraints();
		gbc_lblRequired.anchor = GridBagConstraints.WEST;
		gbc_lblRequired.insets = new Insets(0, 0, 5, 0);
		gbc_lblRequired.gridx = 4;
		gbc_lblRequired.gridy = 0;
		add(lblRequired, gbc_lblRequired);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		add(separator, gbc_separator);
		
		lblFullName = new JLabel(" Full Name (*)");
		GridBagConstraints gbc_lblFullName = new GridBagConstraints();
		gbc_lblFullName.gridwidth = 2;
		gbc_lblFullName.anchor = GridBagConstraints.WEST;
		gbc_lblFullName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFullName.gridx = 0;
		gbc_lblFullName.gridy = 2;
		add(lblFullName, gbc_lblFullName);
		
		JLabel lblNameRequired = new JLabel("Required.");
		lblNameRequired.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblNameRequired.setForeground(Color.RED);
		GridBagConstraints gbc_lblNameRequired = new GridBagConstraints();
		gbc_lblNameRequired.insets = new Insets(0, 0, 5, 5);
		gbc_lblNameRequired.gridx = 2;
		gbc_lblNameRequired.gridy = 2;
		add(lblNameRequired, gbc_lblNameRequired);
		
		// CUSTOM
		lblNameRequired.setVisible(false);
		
		JLabel lblGender = new JLabel(" Gender (*)");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		gbc_lblGender.insets = new Insets(0, 0, 5, 0);
		gbc_lblGender.gridx = 4;
		gbc_lblGender.gridy = 2;
		add(lblGender, gbc_lblGender);
		
		textName = new JTextField();
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.gridwidth = 3;
		gbc_textName.insets = new Insets(0, 0, 5, 5);
		gbc_textName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textName.gridx = 0;
		gbc_textName.gridy = 3;
		add(textName, gbc_textName);
		textName.setColumns(10);
		
		comboBox = new JComboBox(DepartmentofStateGenderMarkerArray);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 4;
		gbc_comboBox.gridy = 3;
		add(comboBox, gbc_comboBox);
		
		JLabel lblFullNameAndGender = new JLabel("  Enter name and gender as printed on government-issued photo ID");
		lblFullNameAndGender.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblFullNameAndGender = new GridBagConstraints();
		gbc_lblFullNameAndGender.anchor = GridBagConstraints.WEST;
		gbc_lblFullNameAndGender.gridwidth = 5;
		gbc_lblFullNameAndGender.insets = new Insets(0, 0, 5, 0);
		gbc_lblFullNameAndGender.gridx = 0;
		gbc_lblFullNameAndGender.gridy = 4;
		add(lblFullNameAndGender, gbc_lblFullNameAndGender);
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.insets = new Insets(0, 0, 5, 5);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 5;
		add(separator_2, gbc_separator_2);
		
		JLabel lblDoB = new JLabel(" Date of Birth (Month, Day, Year) (*)");
		GridBagConstraints gbc_lblDoB = new GridBagConstraints();
		gbc_lblDoB.anchor = GridBagConstraints.WEST;
		gbc_lblDoB.gridwidth = 3;
		gbc_lblDoB.insets = new Insets(0, 0, 5, 5);
		gbc_lblDoB.gridx = 0;
		gbc_lblDoB.gridy = 6;
		add(lblDoB, gbc_lblDoB);
		
		lblDoBInvalidDateFormat = new JLabel("  Unattended minor(s).");
		lblDoBInvalidDateFormat.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblDoBInvalidDateFormat.setForeground(Color.RED);
		GridBagConstraints gbc_lblDoBInvalidDateFormat = new GridBagConstraints();
		gbc_lblDoBInvalidDateFormat.anchor = GridBagConstraints.WEST;
		gbc_lblDoBInvalidDateFormat.insets = new Insets(0, 0, 5, 0);
		gbc_lblDoBInvalidDateFormat.gridx = 4;
		gbc_lblDoBInvalidDateFormat.gridy = 6;
		add(lblDoBInvalidDateFormat, gbc_lblDoBInvalidDateFormat);
		lblDoBInvalidDateFormat.setVisible(false);
		
		comboBoxBirthMonth = new JComboBox(months);
		GridBagConstraints gbc_comboBoxBirthMonth = new GridBagConstraints();
		gbc_comboBoxBirthMonth.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxBirthMonth.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxBirthMonth.gridx = 0;
		gbc_comboBoxBirthMonth.gridy = 7;
		add(comboBoxBirthMonth, gbc_comboBoxBirthMonth);
		
		comboBoxBirthDay = new JComboBox(days);
		GridBagConstraints gbc_comboBoxBirthDay = new GridBagConstraints();
		gbc_comboBoxBirthDay.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxBirthDay.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxBirthDay.gridx = 1;
		gbc_comboBoxBirthDay.gridy = 7;
		add(comboBoxBirthDay, gbc_comboBoxBirthDay);
		
		comboBoxBirthYear = new JComboBox(years.toArray());
		GridBagConstraints gbc_comboBoxBirthYear = new GridBagConstraints();
		gbc_comboBoxBirthYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxBirthYear.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxBirthYear.gridx = 2;
		gbc_comboBoxBirthYear.gridy = 7;
		add(comboBoxBirthYear, gbc_comboBoxBirthYear);
		
		JLabel lblCountry = new JLabel(" Country/Region of Residence (*)");
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.gridwidth = 3;
		gbc_lblCountry.anchor = GridBagConstraints.WEST;
		gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx = 0;
		gbc_lblCountry.gridy = 8;
		add(lblCountry, gbc_lblCountry);
		
		JLabel lblState = new JLabel(" State of Residence (*)");
		GridBagConstraints gbc_lblState = new GridBagConstraints();
		gbc_lblState.anchor = GridBagConstraints.WEST;
		gbc_lblState.insets = new Insets(0, 0, 5, 0);
		gbc_lblState.gridx = 4;
		gbc_lblState.gridy = 8;
		add(lblState, gbc_lblState);
		
		comboBoxCountry = new JComboBox(countryArray);
		comboBoxCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxCountry.getSelectedItem().toString().equals("United States")) {
					lblState.setVisible(true);
					comboBoxState.setVisible(true);
				} else {
					lblState.setVisible(false);
					comboBoxState.setVisible(false);
				}
			}
		});
		GridBagConstraints gbc_comboBoxCountry = new GridBagConstraints();
		gbc_comboBoxCountry.gridwidth = 3;
		gbc_comboBoxCountry.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCountry.gridx = 0;
		gbc_comboBoxCountry.gridy = 9;
		add(comboBoxCountry, gbc_comboBoxCountry);
		
		comboBoxState = new JComboBox(states);
		GridBagConstraints gbc_comboBoxState = new GridBagConstraints();
		gbc_comboBoxState.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxState.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxState.gridx = 4;
		gbc_comboBoxState.gridy = 9;
		add(comboBoxState, gbc_comboBoxState);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 10;
		add(separator_1, gbc_separator_1);
		
		JButton btnBack = new JButton("Return");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, previousPane);
			}
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.anchor = GridBagConstraints.WEST;
		gbc_btnBack.insets = new Insets(0, 0, 0, 5);
		gbc_btnBack.gridx = 0;
		gbc_btnBack.gridy = 11;
		add(btnBack, gbc_btnBack);
		
		JButton btnNext = new JButton("Continue");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textName.getText().equals("")) {
					lblNameRequired.setVisible(true);
				} else {
					lblNameRequired.setVisible(false);
					support.firePropertyChange("passengerName" + passengerIndex, null, textName.getText());
					if (passengerIndex == selectedPassengerAmount) {
						support.firePropertyChange("sumRunningTotal", null, null);
						checkMinor(passengerIndex);
						((CardLayout) contentPane.getLayout()).show(contentPane, "PAY");
					}
					else {
						((CardLayout) contentPane.getLayout()).show(contentPane, nextPassengerDetailsPane);
					}
				}
			}
		});
		GridBagConstraints gbc_btnNext = new GridBagConstraints();
		gbc_btnNext.anchor = GridBagConstraints.EAST;
		gbc_btnNext.gridx = 4;
		gbc_btnNext.gridy = 11;
		add(btnNext, gbc_btnNext);
		
		comboBoxBirthYear.setSelectedItem("2000");
	}
	
	/**
	 * Fires PropertyChangeEvent if passenger is a minor (under 16 for airline accommodation purposes).
	 */
	private void checkMinor(int passengerIndex) {
		int year = Integer.parseInt(comboBoxBirthYear.getSelectedItem().toString());
		int month = Integer.parseInt(comboBoxBirthMonth.getSelectedItem().toString());
		int day = Integer.parseInt(comboBoxBirthMonth.getSelectedItem().toString());
		LocalDateTime ldt = LocalDateTime.of(year, month, day, 00, 00);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZonedDateTime.now().getZone());
		if (ChronoUnit.YEARS.between(zdt, ZonedDateTime.now()) < 16) {
			support.firePropertyChange("isMinor" + passengerIndex, null, true);
		} else {
			support.firePropertyChange("isMinor" + passengerIndex, null, false);
		}
	}
	/**
	 * Resets JTextFields and JComboBoxes to default;
	 */
	private void resetPane() {
		textName.setText("");
		comboBox.setSelectedIndex(0);
		comboBoxCountry.setSelectedIndex(0);
		comboBoxState.setSelectedIndex(0);
		comboBoxBirthDay.setSelectedIndex(0);
		comboBoxBirthMonth.setSelectedIndex(0);
		comboBoxBirthYear.setSelectedItem("2000");
	}
	
	/**
	 * Adds PropertyChangeListener.
	 */
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }
	
	/**
	 * Listens for PropertyChangeEvent.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ((evt.getPropertyName()).equals("passengerAmount")) {
			this.selectedPassengerAmount = ((int) evt.getNewValue());
		}
		if ((evt.getPropertyName()).equals("selectedFlight")) {
			this.selectedFlight = ((Flight) evt.getNewValue());
		}
		if ((evt.getPropertyName()).equals("reservationBooked")) {
			resetPane();
		}
	}

}