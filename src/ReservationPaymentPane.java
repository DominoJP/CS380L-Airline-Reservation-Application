import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JButton;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import java.awt.Font;

public class ReservationPaymentPane extends JPanel implements PropertyChangeListener {
	private int selectedPassengerAmount;
	private Flight selectedFlight;
	private String selectedCabin;
	private Reservation reservation;
	private BigDecimal runningTotal = new BigDecimal("0.00");
	// private BigDecimal[] fares = {new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00"), 
	//							  new BigDecimal("0.00"), new BigDecimal("0.00"), new BigDecimal("0.00")};
	
	private JLabel lblAmountDue;

	private static final long serialVersionUID = 1L;
	private JTextField textFirstName;
	private JTextField textCardNumber;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	public ReservationPaymentPane(JPanel contentPane, Account account, Flight flight) {
		selectedPassengerAmount = 1;
		selectedCabin = "Economy";
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblAmountDue = new JLabel(" Amount Due: _");
		GridBagConstraints gbc_lblAmountDue = new GridBagConstraints();
		gbc_lblAmountDue.anchor = GridBagConstraints.WEST;
		gbc_lblAmountDue.gridwidth = 3;
		gbc_lblAmountDue.insets = new Insets(0, 0, 5, 5);
		gbc_lblAmountDue.gridx = 0;
		gbc_lblAmountDue.gridy = 0;
		add(lblAmountDue, gbc_lblAmountDue);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 0;
		gbc_separator_1.gridy = 1;
		add(separator_1, gbc_separator_1);
		
		JLabel lblCardType = new JLabel(" Card Type");
		GridBagConstraints gbc_lblCardType = new GridBagConstraints();
		gbc_lblCardType.insets = new Insets(0, 0, 5, 5);
		gbc_lblCardType.anchor = GridBagConstraints.WEST;
		gbc_lblCardType.gridx = 0;
		gbc_lblCardType.gridy = 2;
		add(lblCardType, gbc_lblCardType);
		
		JLabel lblCardNumber = new JLabel(" Card Number");
		GridBagConstraints gbc_lblCardNumber = new GridBagConstraints();
		gbc_lblCardNumber.anchor = GridBagConstraints.WEST;
		gbc_lblCardNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblCardNumber.gridx = 2;
		gbc_lblCardNumber.gridy = 2;
		add(lblCardNumber, gbc_lblCardNumber);
		
		JLabel lblExpirationDate = new JLabel(" Expiration Date (MM/YYYY)");
		GridBagConstraints gbc_lblExpirationDate = new GridBagConstraints();
		gbc_lblExpirationDate.gridwidth = 2;
		gbc_lblExpirationDate.anchor = GridBagConstraints.WEST;
		gbc_lblExpirationDate.insets = new Insets(0, 0, 5, 0);
		gbc_lblExpirationDate.gridx = 4;
		gbc_lblExpirationDate.gridy = 2;
		add(lblExpirationDate, gbc_lblExpirationDate);
		
		JComboBox comboBoxCardType = new JComboBox();
		GridBagConstraints gbc_comboBoxCardType = new GridBagConstraints();
		gbc_comboBoxCardType.gridwidth = 2;
		gbc_comboBoxCardType.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCardType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCardType.gridx = 0;
		gbc_comboBoxCardType.gridy = 3;
		add(comboBoxCardType, gbc_comboBoxCardType);
		
		textCardNumber = new JTextField();
		textCardNumber.setColumns(10);
		GridBagConstraints gbc_textCardNumber = new GridBagConstraints();
		gbc_textCardNumber.insets = new Insets(0, 0, 5, 5);
		gbc_textCardNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCardNumber.gridx = 2;
		gbc_textCardNumber.gridy = 3;
		add(textCardNumber, gbc_textCardNumber);
		
		JComboBox comboBoxMonth = new JComboBox();
		GridBagConstraints gbc_comboBoxMonth = new GridBagConstraints();
		gbc_comboBoxMonth.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxMonth.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxMonth.gridx = 4;
		gbc_comboBoxMonth.gridy = 3;
		add(comboBoxMonth, gbc_comboBoxMonth);
		
		JComboBox comboBoxYear = new JComboBox();
		GridBagConstraints gbc_comboBoxYear = new GridBagConstraints();
		gbc_comboBoxYear.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxYear.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxYear.gridx = 5;
		gbc_comboBoxYear.gridy = 3;
		add(comboBoxYear, gbc_comboBoxYear);
		
		JLabel lblFirstName = new JLabel(" First Name");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.anchor = GridBagConstraints.WEST;
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 0;
		gbc_lblFirstName.gridy = 4;
		add(lblFirstName, gbc_lblFirstName);
		
		JLabel lblLastName = new JLabel(" Last Name");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.anchor = GridBagConstraints.WEST;
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 4;
		gbc_lblLastName.gridy = 4;
		add(lblLastName, gbc_lblLastName);
		
		textFirstName = new JTextField();
		GridBagConstraints gbc_textFirstName = new GridBagConstraints();
		gbc_textFirstName.gridwidth = 3;
		gbc_textFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_textFirstName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFirstName.gridx = 0;
		gbc_textFirstName.gridy = 5;
		add(textFirstName, gbc_textFirstName);
		textFirstName.setColumns(10);
		
		textField = new JTextField();
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 4;
		gbc_textField.gridy = 5;
		add(textField, gbc_textField);
		
		JLabel lblCountry = new JLabel(" Country");
		GridBagConstraints gbc_lblCountry = new GridBagConstraints();
		gbc_lblCountry.anchor = GridBagConstraints.WEST;
		gbc_lblCountry.insets = new Insets(0, 0, 5, 5);
		gbc_lblCountry.gridx = 0;
		gbc_lblCountry.gridy = 7;
		add(lblCountry, gbc_lblCountry);
		
		JLabel lblBillingAddress = new JLabel(" Billing Address");
		GridBagConstraints gbc_lblBillingAddress = new GridBagConstraints();
		gbc_lblBillingAddress.gridwidth = 3;
		gbc_lblBillingAddress.anchor = GridBagConstraints.WEST;
		gbc_lblBillingAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblBillingAddress.gridx = 2;
		gbc_lblBillingAddress.gridy = 7;
		add(lblBillingAddress, gbc_lblBillingAddress);
		
		JComboBox comboBoxCountry = new JComboBox();
		GridBagConstraints gbc_comboBoxCountry = new GridBagConstraints();
		gbc_comboBoxCountry.gridwidth = 2;
		gbc_comboBoxCountry.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCountry.gridx = 0;
		gbc_comboBoxCountry.gridy = 8;
		add(comboBoxCountry, gbc_comboBoxCountry);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 8;
		add(textField_1, gbc_textField_1);
		
		JLabel lblCity = new JLabel(" City");
		GridBagConstraints gbc_lblCity = new GridBagConstraints();
		gbc_lblCity.anchor = GridBagConstraints.WEST;
		gbc_lblCity.insets = new Insets(0, 0, 5, 5);
		gbc_lblCity.gridx = 0;
		gbc_lblCity.gridy = 9;
		add(lblCity, gbc_lblCity);
		
		JLabel lblState = new JLabel(" State");
		GridBagConstraints gbc_lblState = new GridBagConstraints();
		gbc_lblState.anchor = GridBagConstraints.WEST;
		gbc_lblState.insets = new Insets(0, 0, 5, 5);
		gbc_lblState.gridx = 2;
		gbc_lblState.gridy = 9;
		add(lblState, gbc_lblState);
		
		JLabel lblPostalCode = new JLabel(" Postal Code");
		GridBagConstraints gbc_lblPostalCode = new GridBagConstraints();
		gbc_lblPostalCode.anchor = GridBagConstraints.WEST;
		gbc_lblPostalCode.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostalCode.gridx = 4;
		gbc_lblPostalCode.gridy = 9;
		add(lblPostalCode, gbc_lblPostalCode);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 0;
		gbc_textField_2.gridy = 10;
		add(textField_2, gbc_textField_2);
		
		JComboBox comboBoxState = new JComboBox();
		GridBagConstraints gbc_comboBoxState = new GridBagConstraints();
		gbc_comboBoxState.gridwidth = 2;
		gbc_comboBoxState.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxState.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxState.gridx = 2;
		gbc_comboBoxState.gridy = 10;
		add(comboBoxState, gbc_comboBoxState);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 10;
		add(textField_3, gbc_textField_3);
		
		JButton btnPay = new JButton("Pay");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isUniqueReservation(account, selectedFlight.getID())) {
					IDGenerator reservationIDFactory = new IDGenerator("RESERVATIONS");
					reservation = new Reservation(reservationIDFactory.generateID(), account, flight, selectedCabin, null, runningTotal, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
					// Update reservation history in active account.
					account.addReservationHistory(reservation);
					ReservationIO.writeReservation(account, reservation);
					FlightIO.updatePassengerCount(selectedFlight, selectedPassengerAmount, selectedCabin);
				}
				((CardLayout) contentPane.getLayout()).show(contentPane, "CONFIRM");
			}
		});
		GridBagConstraints gbc_btnPay = new GridBagConstraints();
		gbc_btnPay.insets = new Insets(0, 0, 5, 0);
		gbc_btnPay.gridx = 5;
		gbc_btnPay.gridy = 10;
		add(btnPay, gbc_btnPay);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 5;
		gbc_separator.gridy = 11;
		add(separator, gbc_separator);

	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		// fires from FilterListPane
		if ((evt.getPropertyName()).equals("selectedFlight")) {
			this.selectedFlight = ((Flight) evt.getNewValue());
			System.out.println("selectedFlight PropertyChangeEvent");
		}
		
		// fires from FilterPane
		if ((evt.getPropertyName()).equals("passengerAmount")) {
			this.selectedPassengerAmount = ((int) evt.getNewValue());
			System.out.println("passengerAmt PropertyChangeEvent" + this.selectedPassengerAmount);
		}
		
		// fires from CabinClassPane
		if ((evt.getPropertyName()).equals("selectedCabin")) {
			this.selectedCabin = ((String) evt.getNewValue());
			System.out.println("selectedCabin PropertyChangeEvent");
			lblAmountDue.setText(" Amount Due: $" + "TEST");
		}
		
		// fires from PassengerDetails
		/*
		switch(evt.getPropertyName()) {
			case "selectedCabin" + "1":
				fares[0] = selectedFlight.getpricing();
				System.out.println("cabin PropertyChangeEvent");
				break;
			case "selectedCabin" + "2":
				fares[1] = selectedFlight.getpricing();
				break;
			case "selectedCabin" + "3":
				fares[2] = selectedFlight.getpricing();
				break;
			case "selectedCabin" + "4":
				fares[3] = selectedFlight.getpricing();
				break;
			case "selectedCabin" + "5":
				fares[4] = selectedFlight.getpricing();
				break;
			case "selectedCabin" + "6":
				fares[5] = selectedFlight.getpricing();
				break;
		}
		*/
		
		// fires from PassengerDetails
		
		if ((evt.getPropertyName().equals("sumRunningTotal"))) {
			BigDecimal passengerAmount = new BigDecimal(String.valueOf(selectedPassengerAmount));
			switch (selectedCabin) {
				case "Economy":
					runningTotal = selectedFlight.getEconomyPricing().multiply(passengerAmount);
					lblAmountDue.setText(" Amount Due: $" + runningTotal);
					break;
				case "Business":
					runningTotal = selectedFlight.getBusinessPricing().multiply(passengerAmount);
					lblAmountDue.setText(" Amount Due: $" + runningTotal);
					break;
				case "First Class":
					runningTotal = selectedFlight.getFirstClassPricing().multiply(passengerAmount);
					lblAmountDue.setText(" Amount Due: $" + runningTotal);
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

}