import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFormattedTextField;

/**
 * Design Documentation: "PaymentUI."
 * Description: JPanel subclass for payment and billing information. 
 * Displays total sum of fares + fees for all passengers.
 * JComboBoxes for Card Type, Expiry Date (MM/YY), Country, and State. 
 * JTextFields for Card Number, First/Last Name, Billing Address, and ZIP Code.
 * <p>
 * Functions: Determines sum total pricing from PropertyChangeEvents "selectedFlight," "passengerAmount," and "selectedCabin."
 * isUniqueReservation() validates that the pending reservation is not already booked for the same cabin of the same flight.
 * <p>
 * Data Structures: Arrays as inputs for card type, card date, country, and state.
 * Algorithms: N/A.
 * @version 2.2.1, Last Modified: Dec 6, 2023
 * @author Jevy Miranda
 */
public class PaymentPane extends JPanel implements PropertyChangeListener {
	private static final int MAXIMUM_PASSENERS_PER_BOOKING = 6;
	private int selectedPassengerAmount;
	private Flight selectedFlight;
	private String selectedCabin;
	private Reservation reservation;
	private BigDecimal runningTotal = new BigDecimal("0.00");
	// private BigDecimal[] fares = {new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"), 
	//							  new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00")};
	private ArrayList<String> passengerNames;
	private boolean[] areMinors = {true, true, true, true, true, true};
	private boolean hasAdult;
	private JButton btnPay;
	private JFormattedTextField formattedTextCardNumber;
	private JComboBox comboBoxMonth;
	
	private JTextField textFirstName;
	private JTextField textLastName;
	private JFormattedTextField formattedTextCSC;
	private JTextField textAddress;
	private JTextField textCity;
	private JLabel lblState;
	private JComboBox comboBoxState;
	private JComboBox comboBoxYear;
	private JFormattedTextField formattedTextZIP;
	private JLabel lblError;
	private PropertyChangeSupport support;

	private static final long serialVersionUID = 1L;
	
	private String[] states = {"Alaska", "Alabama", "Arkansas", "American Samoa", "Arizona", "California", "Colorado", "Connecticut", 
            "District of Columbia", "Delaware", "Florida", "Georgia", "Guam", "Hawaii", "Iowa", "Idaho", "Illinois", 
            "Indiana", "Kansas", "Kentucky", "Louisiana", "Massachusetts", "Maryland", "Maine", "Michigan", 
            "Minnesota", "Missouri", "Mississippi", "Montana", "North Carolina", "North Dakota", "Nebraska", 
            "New Hampshire", "New Jersey", "New Mexico", "Nevada", "New York", "Ohio", "Oklahoma", "Oregon", 
            "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", 
            "Utah", "Virginia", "Virgin Islands", "Vermont", "Washington", "Wisconsin", "West Virginia", "Wyoming"};

	private String[] countryArray = {
			"United States",
			"Canada",
			"Mexico",
		};
	
	private ArrayList<String> years;
	
	private String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	
	private String[] cardType = {
			"Credit",
			"Debit"
		};
	
	/*
	private String[] cardType = {
		"Visa",
		"Mastercard",
		"American Express",
		"Discover",
	};
	*/
	
	
	/**
	 * Constructor.
	 * @param contentPane
	 * @param account
	 */
	public PaymentPane(JPanel contentPane, Account account) {
		years = new ArrayList<>();
		for (int i = ZonedDateTime.now().getYear(); i < (ZonedDateTime.now().getYear() + 10); i++) {
			years.add("" + i);
		}
		
		support = new PropertyChangeSupport(this);
		selectedPassengerAmount = 1;
		selectedCabin = "Economy";
		passengerNames = new ArrayList<>();
		for (int i = 0; i < MAXIMUM_PASSENERS_PER_BOOKING; i++) {
			passengerNames.add("");
		}
		hasAdult = false;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 53, 99, 81, 80};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblCardType = new JLabel(" Card Type");
		GridBagConstraints gbc_lblCardType = new GridBagConstraints();
		gbc_lblCardType.insets = new Insets(0, 0, 5, 5);
		gbc_lblCardType.anchor = GridBagConstraints.WEST;
		gbc_lblCardType.gridx = 0;
		gbc_lblCardType.gridy = 0;
		add(lblCardType, gbc_lblCardType);
		
		JLabel lblCardNumber = new JLabel(" Card Number");
		GridBagConstraints gbc_lblCardNumber = new GridBagConstraints();
		gbc_lblCardNumber.gridwidth = 3;
		gbc_lblCardNumber.anchor = GridBagConstraints.WEST;
		gbc_lblCardNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblCardNumber.gridx = 1;
		gbc_lblCardNumber.gridy = 0;
		add(lblCardNumber, gbc_lblCardNumber);
		
		JLabel lblExpirationDate = new JLabel(" Expiry Date (MM/YY)");
		GridBagConstraints gbc_lblExpirationDate = new GridBagConstraints();
		gbc_lblExpirationDate.gridwidth = 2;
		gbc_lblExpirationDate.anchor = GridBagConstraints.WEST;
		gbc_lblExpirationDate.insets = new Insets(0, 0, 5, 0);
		gbc_lblExpirationDate.gridx = 4;
		gbc_lblExpirationDate.gridy = 0;
		add(lblExpirationDate, gbc_lblExpirationDate);
		
		JComboBox comboBoxCardType = new JComboBox(cardType);
		GridBagConstraints gbc_comboBoxCardType = new GridBagConstraints();
		gbc_comboBoxCardType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCardType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCardType.gridx = 0;
		gbc_comboBoxCardType.gridy = 1;
		add(comboBoxCardType, gbc_comboBoxCardType);
		
		try {
			formattedTextCardNumber = new JFormattedTextField(new MaskFormatter("################"));
			GridBagConstraints gbc_formattedTextCardNumber = new GridBagConstraints();
			gbc_formattedTextCardNumber.gridwidth = 3;
			gbc_formattedTextCardNumber.insets = new Insets(0, 0, 5, 5);
			gbc_formattedTextCardNumber.fill = GridBagConstraints.HORIZONTAL;
			gbc_formattedTextCardNumber.gridx = 1;
			gbc_formattedTextCardNumber.gridy = 1;
			add(formattedTextCardNumber, gbc_formattedTextCardNumber);
		} catch (ParseException e) {
	         e.printStackTrace();
	    }
		
		
		comboBoxMonth = new JComboBox(months);
		GridBagConstraints gbc_comboBoxMonth = new GridBagConstraints();
		gbc_comboBoxMonth.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxMonth.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxMonth.gridx = 4;
		gbc_comboBoxMonth.gridy = 1;
		add(comboBoxMonth, gbc_comboBoxMonth);
		comboBoxMonth.setSelectedItem("" + ZonedDateTime.now().getMonthValue());
		
		comboBoxYear = new JComboBox(years.toArray());
		GridBagConstraints gbc_comboBoxYear = new GridBagConstraints();
		gbc_comboBoxYear.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxYear.gridx = 5;
		gbc_comboBoxYear.gridy = 1;
		add(comboBoxYear, gbc_comboBoxYear);
		
		JLabel lblFirstName = new JLabel(" First Name");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 2;
		add(lblFirstName, gbc_lblFirstName);
		
		JLabel lblLastName = new JLabel(" Last Name");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.gridwidth = 2;
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 3;
		gbc_lblLastName.gridy = 2;
		add(lblLastName, gbc_lblLastName);
		
		JLabel lblCSC = new JLabel(" CSC");
		GridBagConstraints gbc_lblCSC = new GridBagConstraints();
		gbc_lblCSC.anchor = GridBagConstraints.WEST;
		gbc_lblCSC.insets = new Insets(0, 0, 5, 0);
		gbc_lblCSC.gridx = 5;
		gbc_lblCSC.gridy = 2;
		add(lblCSC, gbc_lblCSC);
		
		textFirstName = new JTextField();
		GridBagConstraints gbc_textFirstName = new GridBagConstraints();
		gbc_textFirstName.gridwidth = 3;
		gbc_textFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_textFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFirstName.gridx = 0;
		gbc_textFirstName.gridy = 3;
		add(textFirstName, gbc_textFirstName);
		textFirstName.setColumns(10);
		
		textLastName = new JTextField();
		textLastName.setColumns(10);
		GridBagConstraints gbc_textLastName = new GridBagConstraints();
		gbc_textLastName.gridwidth = 2;
		gbc_textLastName.insets = new Insets(0, 0, 5, 5);
		gbc_textLastName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textLastName.gridx = 3;
		gbc_textLastName.gridy = 3;
		add(textLastName, gbc_textLastName);
		
		try {
			formattedTextCSC = new JFormattedTextField(new MaskFormatter("###"));
			GridBagConstraints gbc_formattedTextCSC = new GridBagConstraints();
			gbc_formattedTextCSC.insets = new Insets(0, 0, 5, 0);
			gbc_formattedTextCSC.fill = GridBagConstraints.HORIZONTAL;
			gbc_formattedTextCSC.gridx = 5;
			gbc_formattedTextCSC.gridy = 3;
			add(formattedTextCSC, gbc_formattedTextCSC);
		} catch (ParseException e) {
	         e.printStackTrace();
	    }
		
		JLabel lblCountry = new JLabel(" Country");
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.anchor = GridBagConstraints.WEST;
		gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx = 0;
		gbc_lblCountry.gridy = 4;
		add(lblCountry, gbc_lblCountry);
		
		
		JLabel lblBillingAddress = new JLabel(" Billing Address");
		GridBagConstraints gbc_lblBillingAddress = new GridBagConstraints();
		gbc_lblBillingAddress.gridwidth = 2;
		gbc_lblBillingAddress.anchor = GridBagConstraints.WEST;
		gbc_lblBillingAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblBillingAddress.gridx = 3;
		gbc_lblBillingAddress.gridy = 4;
		add(lblBillingAddress, gbc_lblBillingAddress);
		
		JComboBox comboBoxCountry = new JComboBox(countryArray);
		comboBoxCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comboBoxCountry.getSelectedItem().toString().equals("United States")) {
					comboBoxState.setVisible(false);
					lblState.setVisible(false);
				} else {
					comboBoxState.setVisible(true);
					lblState.setVisible(true);
				}
			}
		});
		GridBagConstraints gbc_comboBoxCountry = new GridBagConstraints();
		gbc_comboBoxCountry.gridwidth = 3;
		gbc_comboBoxCountry.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCountry.gridx = 0;
		gbc_comboBoxCountry.gridy = 5;
		add(comboBoxCountry, gbc_comboBoxCountry);
		
		textAddress = new JTextField();
		textAddress.setColumns(10);
		GridBagConstraints gbc_textAddress = new GridBagConstraints();
		gbc_textAddress.gridwidth = 2;
		gbc_textAddress.insets = new Insets(0, 0, 5, 5);
		gbc_textAddress.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAddress.gridx = 3;
		gbc_textAddress.gridy = 5;
		add(textAddress, gbc_textAddress);
		
		JLabel lblCity = new JLabel(" City");
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.anchor = GridBagConstraints.WEST;
		gbc_lblCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity.gridx = 0;
		gbc_lblCity.gridy = 6;
		add(lblCity, gbc_lblCity);
		
		lblState = new JLabel(" State");
		GridBagConstraints gbc_lblState = new GridBagConstraints();
		gbc_lblState.anchor = GridBagConstraints.WEST;
		gbc_lblState.insets = new Insets(0, 0, 5, 5);
		gbc_lblState.gridx = 3;
		gbc_lblState.gridy = 6;
		add(lblState, gbc_lblState);
					
		JLabel lblPostalCode = new JLabel(" ZIP Code");
		GridBagConstraints gbc_lblPostalCode = new GridBagConstraints();
		gbc_lblPostalCode.anchor = GridBagConstraints.WEST;
		gbc_lblPostalCode.insets = new Insets(0, 0, 5, 0);
		gbc_lblPostalCode.gridx = 5;
		gbc_lblPostalCode.gridy = 6;
		add(lblPostalCode, gbc_lblPostalCode);
		
		textCity = new JTextField();
		textCity.setColumns(10);
		GridBagConstraints gbc_textCity = new GridBagConstraints();
		gbc_textCity.gridwidth = 3;
		gbc_textCity.insets = new Insets(0, 0, 5, 5);
		gbc_textCity.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCity.gridx = 0;
		gbc_textCity.gridy = 7;
		add(textCity, gbc_textCity);
		
		comboBoxState = new JComboBox(states);
		GridBagConstraints gbc_comboBoxState = new GridBagConstraints();
		gbc_comboBoxState.gridwidth = 2;
		gbc_comboBoxState.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxState.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxState.gridx = 3;
		gbc_comboBoxState.gridy = 7;
		add(comboBoxState, gbc_comboBoxState);
		
		JButton btnNewButton = new JButton("Return");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblError.setVisible(false);
				((CardLayout) contentPane.getLayout()).show(contentPane, "PASSENGER" + selectedPassengerAmount + "");
			}
		});
		
		try {
			formattedTextZIP = new JFormattedTextField(new MaskFormatter("#####"));
			GridBagConstraints gbc_formattedTextZIP = new GridBagConstraints();
			gbc_formattedTextZIP.insets = new Insets(0, 0, 5, 0);
			gbc_formattedTextZIP.fill = GridBagConstraints.HORIZONTAL;
			gbc_formattedTextZIP.gridx = 5;
			gbc_formattedTextZIP.gridy = 7;
			add(formattedTextZIP, gbc_formattedTextZIP);
		} catch (ParseException e) {
	         e.printStackTrace();
	    }
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 8;
		add(separator, gbc_separator);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.WEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 9;
		add(btnNewButton, gbc_btnNewButton);
		
		btnPay = new JButton("PAY $" + 0.00);
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				if (formattedTextCardNumber.getText().isBlank()|| textFirstName.getText().isBlank() || textLastName.getText().isBlank() 
						|| formattedTextCSC.getText().isBlank() || textAddress.getText().isBlank() || textCity.getText().isBlank() || formattedTextZIP.getText().isBlank()) {
						setError("Field empty or incomplete.");
						return;
					}
				*/
				
				if (cardIsExpired(Integer.parseInt(comboBoxMonth.getSelectedItem().toString()), Integer.parseInt(comboBoxYear.getSelectedItem().toString()))) {
					setError("Card expired.");
					return;
				}
				
				for (int i = 0; i < selectedPassengerAmount; i++) {
					if (areMinors[i] == false) {
						hasAdult = true;
					}
				}
				if (hasAdult == false) {
					lblError.setVisible(true);
					lblError.setText("Unaccompanied minor(s).");
					return;
				}
				
				if (threeHoursBefore()) {
					lblError.setVisible(true);
					lblError.setText("Cannot book, departs within 3 hr.");
					return;
				}
				
				if (flightHasPassed()) {
					lblError.setVisible(true);
					lblError.setText("Cannot book, already departed.");
					return;
				}
			
				if (updatePassengerCount() == true) {
					IDGenerator IDGen = new IDGenerator();
					reservation = new Reservation(IDGen.generateReservationID(), account, selectedFlight, selectedCabin, passengerNames, runningTotal, ZonedDateTime.now());
					// Update reservation history in active account.
					account.addReservationHistory(reservation);
					ReservationIO.writeReservation(account, reservation);
					try {
						FlightIO.rewritePassengerCount("Database/Flights.txt", selectedFlight, selectedCabin);
					} catch (IOException ioe) {
				        ioe.printStackTrace();
				    }
					support.firePropertyChange("reservationBooked", null, true);
					lblError.setVisible(false);
					((CardLayout) contentPane.getLayout()).show(contentPane, "MENU");
				} else {
					lblError.setVisible(true);
					lblError.setText("Insufficient seating.");
				}
			}
		});
		
		lblError = new JLabel("Error message.");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblError = new GridBagConstraints();
		gbc_lblError.gridwidth = 3;
		gbc_lblError.insets = new Insets(0, 0, 0, 5);
		gbc_lblError.gridx = 1;
		gbc_lblError.gridy = 9;
		add(lblError, gbc_lblError);
		GridBagConstraints gbc_btnPay = new GridBagConstraints();
		gbc_btnPay.anchor = GridBagConstraints.EAST;
		gbc_btnPay.gridwidth = 2;
		gbc_btnPay.gridx = 4;
		gbc_btnPay.gridy = 9;
		add(btnPay, gbc_btnPay);

		lblError.setVisible(false);
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
		
		// fires from FilterListPane
		if ((evt.getPropertyName()).equals("selectedFlight")) {
			this.selectedFlight = ((Flight) evt.getNewValue());
		}
		
		// fires from FilterPane
		if ((evt.getPropertyName()).equals("passengerAmount")) {
			this.selectedPassengerAmount = ((int) evt.getNewValue());
		}
		
		// fires from CabinClassPane
		if ((evt.getPropertyName()).equals("selectedCabin")) {
			this.selectedCabin = ((String) evt.getNewValue());
		}
		
		// fires from PassengerDetails
		switch(evt.getPropertyName()) {
		case "isMinor" + "1":
			areMinors[0] = (boolean) evt.getNewValue();
			break;
		case "isMinor" + "2":
			areMinors[1] = (boolean) evt.getNewValue();
			break;
		case "isMinor" + "3":
			areMinors[2] = (boolean) evt.getNewValue();
			break;
		case "isMinor" + "4":
			areMinors[3] = (boolean) evt.getNewValue();
			break;
		case "isMinor" + "5":
			areMinors[4] = (boolean) evt.getNewValue();
			break;
		case "isMinor" + "6":
			areMinors[5] = (boolean) evt.getNewValue();
			break;
		}
		
		// fires from PassengerDetails
		switch(evt.getPropertyName()) {
			case "passengerName" + "1":
				passengerNames.set(0, ((String) evt.getNewValue()));
				break;
			case "passengerName" + "2":
				passengerNames.set(1, ((String) evt.getNewValue()));
				break;
			case "passengerName" + "3":
				passengerNames.set(2, ((String) evt.getNewValue()));
				break;
			case "passengerName" + "4":
				passengerNames.set(3, ((String) evt.getNewValue()));
				break;
			case "passengerName" + "5":
				passengerNames.set(4, ((String) evt.getNewValue()));
				break;
			case "passengerName" + "6":
				passengerNames.set(5, ((String) evt.getNewValue()));
				break;
		}
		
		// fires from PassengerDetails
		if ((evt.getPropertyName().equals("sumRunningTotal"))) {
			BigDecimal passengerAmount = new BigDecimal(String.valueOf(selectedPassengerAmount));
			switch (selectedCabin) {
				case "Economy":
					runningTotal = selectedFlight.getEconomyPricing().multiply(passengerAmount);
					btnPay.setText("PAY $" + runningTotal);
					break;
				case "Business":
					runningTotal = selectedFlight.getBusinessPricing().multiply(passengerAmount);
					btnPay.setText("PAY: $" + runningTotal);
					break;
				case "First Class":
					runningTotal = selectedFlight.getFirstClassPricing().multiply(passengerAmount);
					btnPay.setText("PAY: $" + runningTotal);
					break;
			}
    	}
		
	}
	
	/**
	 * Validates that reservation for selected flight does not already exist for this account.
	 * @param active account
	 * @param pending reservation
	 * @return true if is a unique reservation
	 */
	@Deprecated
	private static boolean isUniqueReservation(Account account, int flightID) {
		Iterator<Reservation> iter;
		iter = account.getReservationHistory().iterator();
		while (iter.hasNext()) {
			if (iter.next().getFlight().getID() == flightID) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checks whether flight, if on same day, will be booked within three hours of departure.
	 * @return true if flight would be within three hours of booking
	 */
	private boolean threeHoursBefore() {
		LocalDateTime ldt = LocalDateTime.of(selectedFlight.getdateDeparture(), selectedFlight.gettimeDeparture());
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZonedDateTime.now().getZone());
		if (ChronoUnit.MINUTES.between(zdt, ZonedDateTime.now()) <= 0 && ChronoUnit.MINUTES.between(zdt, ZonedDateTime.now()) > -180) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks whether the card has expired.
	 * @param month of expiry
	 * @param year expiry
	 * @return true if expired
	 */
	private boolean cardIsExpired(int month, int year) {
		if (YearMonth.of(year, month).compareTo(YearMonth.now()) < 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks whether flight on departure date would have already departed.
	 * @return true if flight would have already departed
	 */
	private boolean flightHasPassed() {
		LocalDateTime ldt = LocalDateTime.of(selectedFlight.getdateDeparture(), selectedFlight.gettimeDeparture());
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZonedDateTime.now().getZone());
		if (ChronoUnit.MINUTES.between(zdt, ZonedDateTime.now()) >= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Calls corresponding method to update passenger count of selected cabin.
	 * @param selectedFlight
	 * @param selectedCabin
	 * @return whether sucessful
	 */
	private boolean updatePassengerCount() {
		switch (selectedCabin) {
			case "Economy":
				return selectedFlight.addEconomyPassengerCount(selectedPassengerAmount);
			case "Business":
				return selectedFlight.addBusinessPassengerCount(selectedPassengerAmount);
			case "First Class":
				return selectedFlight.addFirstClassPassengerCount(selectedPassengerAmount);
			default:
				return false;
		}
	}
	
	/**
	 * Changes error message and visibility.
	 * @param message to display
	 */
	private void setError(String message) {
		lblError.setVisible(true);
		lblError.setText(message);
	}
}

