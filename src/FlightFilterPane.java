import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;

/**
   JPanel accepting user input (round trip/oneway, airport/city of departure/arrival, date of departure and/or arrival, number of passengers)
   as criteria for filtering flights.
   @author Jevy Miranda
   @version 1.0
*/

public class FlightFilterPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroupTripType = new ButtonGroup();
	private JTextField textReturn;
	private JLabel lblReturn;
	private JLabel lblDepartInvalidDate;
	private JComboBox comboBoxPassengerAmount;
	private JComboBox comboBoxFrom;
	private JLabel lblNoFlights;
	
	private String airportDepartInput;
	private String airportArriveInput;
	private String dateDepartingInput;
	
	private int passengerAmount = 1;
	private int selectedPassengerAmount;
	
	private PropertyChangeSupport support;
	
	// for use with comboBoxFrom
	private String[] airportFromList = {"LA", "BUR"};
	// for use with comboBoxTo
	private String[] airportToList = {"NYC", "DAL"};
	// for use with comboBoxMonthD
	private String[] monthList = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	// for use with comboBoxDayD
	private String[] dayList = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	// for use with comboBoxYearD
	private String[] yearList = {"2023", "2024"};
	// for use with comboBoxPassengerAmount
	Integer[] passengersAmountArray = {
			1, 2, 3, 4, 5, 6, 7, 8, 9
	};
	
	String[] flightListSorted;
	
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		 support.addPropertyChangeListener(pcl);
	}
	
	public FlightFilterPane(JPanel contentPane, Account account, FlightSorting sort, Flight flight) {
		
		support = new PropertyChangeSupport(this);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JRadioButton rdbtnRoundTrip = new JRadioButton("Round Trip");
		buttonGroupTripType.add(rdbtnRoundTrip);
		rdbtnRoundTrip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblReturn.setVisible(true);
				textReturn.setVisible(true);
			}
		});
		
		JButton btnBack = new JButton("Go Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, "SELECT");
			}
		});
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.anchor = GridBagConstraints.WEST;
		gbc_btnBack.gridwidth = 2;
		gbc_btnBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack.gridx = 1;
		gbc_btnBack.gridy = 1;
		add(btnBack, gbc_btnBack);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 2;
		add(separator, gbc_separator);
		GridBagConstraints gbc_rdbtnRoundTrip = new GridBagConstraints();
		gbc_rdbtnRoundTrip.anchor = GridBagConstraints.WEST;
		gbc_rdbtnRoundTrip.gridwidth = 2;
		gbc_rdbtnRoundTrip.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnRoundTrip.gridx = 1;
		gbc_rdbtnRoundTrip.gridy = 3;
		add(rdbtnRoundTrip, gbc_rdbtnRoundTrip);
		
		JRadioButton rdbtnOneWay = new JRadioButton("One Way");
		buttonGroupTripType.add(rdbtnOneWay);
		rdbtnOneWay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblReturn.setVisible(false);
				textReturn.setVisible(false);
			}
		});
		rdbtnOneWay.setSelected(true);
		
		GridBagConstraints gbc_rdbtnOneWay = new GridBagConstraints();
		gbc_rdbtnOneWay.anchor = GridBagConstraints.WEST;
		gbc_rdbtnOneWay.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnOneWay.gridx = 3;
		gbc_rdbtnOneWay.gridy = 3;
		add(rdbtnOneWay, gbc_rdbtnOneWay);
		
		JLabel lblFrom = new JLabel(" From");
		GridBagConstraints gbc_lblFrom = new GridBagConstraints();
		gbc_lblFrom.gridwidth = 2;
		gbc_lblFrom.anchor = GridBagConstraints.WEST;
		gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrom.gridx = 1;
		gbc_lblFrom.gridy = 4;
		add(lblFrom, gbc_lblFrom);
		
		JLabel lblTo = new JLabel(" To");
		GridBagConstraints gbc_lblTo = new GridBagConstraints();
		gbc_lblTo.anchor = GridBagConstraints.WEST;
		gbc_lblTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTo.gridx = 3;
		gbc_lblTo.gridy = 4;
		add(lblTo, gbc_lblTo);
		
		comboBoxFrom = new JComboBox(airportFromList);
		GridBagConstraints gbc_comboBoxFrom = new GridBagConstraints();
		gbc_comboBoxFrom.gridwidth = 2;
		gbc_comboBoxFrom.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFrom.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFrom.gridx = 1;
		gbc_comboBoxFrom.gridy = 5;
		add(comboBoxFrom, gbc_comboBoxFrom);
		
		JComboBox comboBoxTo = new JComboBox(airportToList);
		GridBagConstraints gbc_comboBoxTo = new GridBagConstraints();
		gbc_comboBoxTo.gridwidth = 2;
		gbc_comboBoxTo.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTo.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTo.gridx = 3;
		gbc_comboBoxTo.gridy = 5;
		add(comboBoxTo, gbc_comboBoxTo);
		
		JLabel lblDepart = new JLabel(" Depart (Month, Day, Year)");
		GridBagConstraints gbc_lblDepart = new GridBagConstraints();
		gbc_lblDepart.gridwidth = 3;
		gbc_lblDepart.anchor = GridBagConstraints.WEST;
		gbc_lblDepart.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepart.gridx = 1;
		gbc_lblDepart.gridy = 6;
		add(lblDepart, gbc_lblDepart);
		
		JLabel lblPassengerAmount = new JLabel("Passengers");
		GridBagConstraints gbc_lblPassengerAmount = new GridBagConstraints();
		gbc_lblPassengerAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassengerAmount.gridx = 5;
		gbc_lblPassengerAmount.gridy = 6;
		add(lblPassengerAmount, gbc_lblPassengerAmount);
		
		JComboBox comboBoxMonthD = new JComboBox(monthList);
		GridBagConstraints gbc_comboBoxMonthD = new GridBagConstraints();
		gbc_comboBoxMonthD.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxMonthD.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxMonthD.gridx = 1;
		gbc_comboBoxMonthD.gridy = 7;
		add(comboBoxMonthD, gbc_comboBoxMonthD);
		
		JComboBox comboBoxDayD = new JComboBox(dayList);
		GridBagConstraints gbc_comboBoxDayD = new GridBagConstraints();
		gbc_comboBoxDayD.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDayD.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDayD.gridx = 2;
		gbc_comboBoxDayD.gridy = 7;
		add(comboBoxDayD, gbc_comboBoxDayD);
		
		JComboBox comboBoxYearD = new JComboBox(yearList);
		GridBagConstraints gbc_comboBoxYearD = new GridBagConstraints();
		gbc_comboBoxYearD.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxYearD.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxYearD.gridx = 3;
		gbc_comboBoxYearD.gridy = 7;
		add(comboBoxYearD, gbc_comboBoxYearD);
		
		comboBoxPassengerAmount = new JComboBox(passengersAmountArray);
		GridBagConstraints gbc_comboBoxPassengerAmount = new GridBagConstraints();
		gbc_comboBoxPassengerAmount.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPassengerAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPassengerAmount.gridx = 5;
		gbc_comboBoxPassengerAmount.gridy = 7;
		add(comboBoxPassengerAmount, gbc_comboBoxPassengerAmount);
		
		lblDepartInvalidDate = new JLabel("Invalid date.");
		lblDepartInvalidDate.setForeground(Color.RED);
		lblDepartInvalidDate.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblDepartInvalidDate = new GridBagConstraints();
		gbc_lblDepartInvalidDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartInvalidDate.gridx = 1;
		gbc_lblDepartInvalidDate.gridy = 8;
		add(lblDepartInvalidDate, gbc_lblDepartInvalidDate);
		lblDepartInvalidDate.setVisible(false);
		
		lblReturn = new JLabel(" Return");
		GridBagConstraints gbc_lblReturn = new GridBagConstraints();
		gbc_lblReturn.anchor = GridBagConstraints.WEST;
		gbc_lblReturn.insets = new Insets(0, 0, 5, 5);
		gbc_lblReturn.gridx = 1;
		gbc_lblReturn.gridy = 9;
		add(lblReturn, gbc_lblReturn);
		lblReturn.setVisible(false);
		
		textReturn = new JTextField();
		textReturn.setColumns(10);
		GridBagConstraints gbc_textReturn = new GridBagConstraints();
		gbc_textReturn.gridwidth = 2;
		gbc_textReturn.insets = new Insets(0, 0, 5, 5);
		gbc_textReturn.fill = GridBagConstraints.HORIZONTAL;
		gbc_textReturn.gridx = 1;
		gbc_textReturn.gridy = 10;
		add(textReturn, gbc_textReturn);
		textReturn.setVisible(false);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// update selected passengerAmount, fire PropertyChangeEvent
				setPassengerAmount(Integer.parseInt(comboBoxPassengerAmount.getSelectedItem().toString()));
				
				airportDepartInput = comboBoxFrom.getSelectedItem().toString();
				airportArriveInput = comboBoxTo.getSelectedItem().toString();
				dateDepartingInput = comboBoxYearD.getSelectedItem().toString() + "-" +
									 comboBoxMonthD.getSelectedItem().toString() + "-" +
									 comboBoxDayD.getSelectedItem().toString();
				
				// sort.sortFlights("LA", "NYC", "2023-10-24");
				try {
					// hide Component for 'no flights found'
					lblNoFlights.setVisible(false);
					
					// sort flights per user input
					sort.sortFlights(airportDepartInput, airportArriveInput, dateDepartingInput);
					flightListSorted = sort.getList(airportDepartInput, airportArriveInput, dateDepartingInput);
					ArrayList<Flight> flightArray = sort.getFlightList(airportDepartInput, airportArriveInput, dateDepartingInput);
					
					// instantiate a FlightFilterScrollPane with generated flightListSorted as a parameter
					FlightFilterListScrollPane FilterListPane = new FlightFilterListScrollPane(contentPane, account, flightListSorted, flightArray, flight);
					contentPane.add(FilterListPane, "FILTER_LIST");
					
					// proceed to filtered list of flights, sorted by date of departure by default
					((CardLayout) contentPane.getLayout()).show(contentPane, "FILTER_LIST");
				} catch (NullPointerException ex) {
					// show Component 'no flights found'
					lblNoFlights.setVisible(true);
				}
			}
		});
		
		lblNoFlights = new JLabel("No flights found. Please choose another set of options.");
		lblNoFlights.setForeground(Color.RED);
		lblNoFlights.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblNoFlights = new GridBagConstraints();
		gbc_lblNoFlights.gridwidth = 4;
		gbc_lblNoFlights.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoFlights.gridx = 1;
		gbc_lblNoFlights.gridy = 11;
		add(lblNoFlights, gbc_lblNoFlights);
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 5;
		gbc_btnSearch.gridy = 11;
		add(btnSearch, gbc_btnSearch);
		lblNoFlights.setVisible(false);
		
	}
	
	public int getPassengerAmount() {
		// FIXME: temp solution
		return Integer.parseInt(comboBoxPassengerAmount.getSelectedItem().toString());
	}
	
	public void setPassengerAmount(int selectedPassengerAmount) {
		support.firePropertyChange("passengerAmount", passengerAmount, selectedPassengerAmount);
		this.selectedPassengerAmount = selectedPassengerAmount;
	}

}
