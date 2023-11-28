import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSeparator;

/**
 * Design Documentation: "FilterUI."
 * Description: JPanel subclass accepting user input as flight filtering criteria.
 * JComboBoxes (editable) for airport/city of departure arrival.
 * JComboBoxes for date of departure in MM/DD/YYYY format and passenger amount (up to 6).
 * <p>
 * Functions: Instantiates FlightListPane, passing in Flight ArrayList generated from FlightSorting object.
 * User passenger amount selection fires PropertyChangeEvent "passengerAmount" to determine amount of
 * passenger details Panes to be used.
 * <p>
 * Data Structures: Arrays as input for airport and date JComboBoxes. 
 * ArrayList of flights filtered by user-provided criteria.
 * Algorithms: N/A.
 * @version 4.3.1, Last Modified: November 27, 2023
 * @author Jevy Miranda
 */
public class FilterPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroupTripType = new ButtonGroup();
	private JComboBox comboBoxPassengerAmount;
	private JComboBox comboBoxDepart;
	private JComboBox comboBoxMonthD;
	private JComboBox comboBoxDayD;
	private JComboBox comboBoxYearD;
	
	private JLabel lblNoFlights;
	
	private String airportDepartInput;
	private String airportArriveInput;
	private String dateDepartingInput;
	
	private int passengerAmount = 1;
	private int selectedPassengerAmount;
	
	private PropertyChangeSupport support;
	
	// for use with comboBoxFrom
	private String[] airportFromList = {"LAX - Los Angeles International Airport",  
										"JFK - John F. Kennedy International Airport",
										"ORD - O'Hare International Airport",
										"SLC - Salt Lake City Airport"};
	// for use with comboBoxTo
	private String[] airportToList = {"JFK - John F. Kennedy International Airport",
									  "LAX - Los Angeles International Airport",
									  "ORD - O'Hare International Airport",
									  "SLC - Salt Lake City Airport"};
	// for use with comboBoxMonthD
	// private String[] monthList = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	private String[] monthList = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
	// for use with comboBoxDayD
	private String[] dayList = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", 
								"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	// for use with comboBoxYearD
	private String[] yearList = {"2023", "2024"};
	// for use with comboBoxPassengerAmount
	Integer[] passengersAmountArray = {
			1, 2, 3, 4, 5, 6
	};
	
	String[] flightListSorted;
	
	HashMap<String, String> airports = new HashMap<String, String>();
	
	/**
	 * Adds PropertyChangeListener.
	 */
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		 support.addPropertyChangeListener(pcl);
	}
	
	/**
	 * Constructor.
	 * @param contentPane
	 * @param account
	 * @param sort
	 * @param flight
	 */
	public FilterPane(JPanel contentPane, Account account, FlightSorting sort, Flight flight) {
		
		support = new PropertyChangeSupport(this);
		
		airports.put("New York", "JFK");
		airports.put("Los Angeles", "LAX");
		airports.put("Chicago", "ORD");
		airports.put("Salt Lake City", "SLC");
		airports.put("John F. Kennedy International Airport", "JFK");
		airports.put("Los Angeles International Airport", "LAX");
		airports.put("O'Hare International Airport", "ORD");
		airports.put("Salt Lake City Airport", "SLC");
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{30, 60, 66, 30, 30, 30, 30, 90, 30};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblDepart = new JLabel(" Depart");
		GridBagConstraints gbc_lblDepart = new GridBagConstraints();
		gbc_lblDepart.gridwidth = 2;
		gbc_lblDepart.anchor = GridBagConstraints.WEST;
		gbc_lblDepart.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepart.gridx = 1;
		gbc_lblDepart.gridy = 1;
		add(lblDepart, gbc_lblDepart);
		
		JLabel lblNotice = new JLabel("Select airport OR type city.  ");
		lblNotice.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblNotice = new GridBagConstraints();
		gbc_lblNotice.anchor = GridBagConstraints.EAST;
		gbc_lblNotice.gridwidth = 5;
		gbc_lblNotice.insets = new Insets(0, 0, 5, 5);
		gbc_lblNotice.gridx = 3;
		gbc_lblNotice.gridy = 1;
		add(lblNotice, gbc_lblNotice);
		
		comboBoxDepart = new JComboBox(airportFromList);
		GridBagConstraints gbc_comboBoxDepart = new GridBagConstraints();
		gbc_comboBoxDepart.gridwidth = 7;
		gbc_comboBoxDepart.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDepart.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDepart.gridx = 1;
		gbc_comboBoxDepart.gridy = 2;
		add(comboBoxDepart, gbc_comboBoxDepart);
		comboBoxDepart.setEditable(true);
		
		JLabel lblArrive = new JLabel(" Arrive");
		GridBagConstraints gbc_lblArrive = new GridBagConstraints();
		gbc_lblArrive.gridwidth = 7;
		gbc_lblArrive.anchor = GridBagConstraints.WEST;
		gbc_lblArrive.insets = new Insets(0, 0, 5, 5);
		gbc_lblArrive.gridx = 1;
		gbc_lblArrive.gridy = 3;
		add(lblArrive, gbc_lblArrive);
		
		JComboBox comboBoxArrive = new JComboBox(airportToList);
		GridBagConstraints gbc_comboBoxArrive = new GridBagConstraints();
		gbc_comboBoxArrive.gridwidth = 7;
		gbc_comboBoxArrive.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxArrive.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxArrive.gridx = 1;
		gbc_comboBoxArrive.gridy = 4;
		add(comboBoxArrive, gbc_comboBoxArrive);
		comboBoxArrive.setEditable(true);
		
		JSeparator separator_1 = new JSeparator();
		GridBagConstraints gbc_separator_1 = new GridBagConstraints();
		gbc_separator_1.gridwidth = 7;
		gbc_separator_1.insets = new Insets(0, 0, 5, 5);
		gbc_separator_1.gridx = 1;
		gbc_separator_1.gridy = 5;
		add(separator_1, gbc_separator_1);
		
		JLabel lblDateDepart = new JLabel(" Date of Departure (Month, Day, Year)");
		GridBagConstraints gbc_lblDateDepart = new GridBagConstraints();
		gbc_lblDateDepart.gridwidth = 6;
		gbc_lblDateDepart.anchor = GridBagConstraints.WEST;
		gbc_lblDateDepart.insets = new Insets(0, 0, 5, 5);
		gbc_lblDateDepart.gridx = 1;
		gbc_lblDateDepart.gridy = 6;
		add(lblDateDepart, gbc_lblDateDepart);
		
		JLabel lblPassengerAmount = new JLabel(" Passengers");
		GridBagConstraints gbc_lblPassengerAmount = new GridBagConstraints();
		gbc_lblPassengerAmount.anchor = GridBagConstraints.WEST;
		gbc_lblPassengerAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassengerAmount.gridx = 7;
		gbc_lblPassengerAmount.gridy = 6;
		add(lblPassengerAmount, gbc_lblPassengerAmount);
		
		comboBoxMonthD = new JComboBox(monthList);
		GridBagConstraints gbc_comboBoxMonthD = new GridBagConstraints();
		gbc_comboBoxMonthD.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxMonthD.gridx = 1;
		gbc_comboBoxMonthD.gridy = 7;
		add(comboBoxMonthD, gbc_comboBoxMonthD);
		
		comboBoxDayD = new JComboBox(dayList);
		GridBagConstraints gbc_comboBoxDayD = new GridBagConstraints();
		gbc_comboBoxDayD.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDayD.gridx = 2;
		gbc_comboBoxDayD.gridy = 7;
		add(comboBoxDayD, gbc_comboBoxDayD);
		
		comboBoxYearD = new JComboBox(yearList);
		GridBagConstraints gbc_comboBoxYearD = new GridBagConstraints();
		gbc_comboBoxYearD.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxYearD.gridwidth = 3;
		gbc_comboBoxYearD.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxYearD.gridx = 3;
		gbc_comboBoxYearD.gridy = 7;
		add(comboBoxYearD, gbc_comboBoxYearD);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, "MENU");
			}
		});
		
		comboBoxPassengerAmount = new JComboBox(passengersAmountArray);
		GridBagConstraints gbc_comboBoxPassengerAmount = new GridBagConstraints();
		gbc_comboBoxPassengerAmount.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPassengerAmount.gridx = 7;
		gbc_comboBoxPassengerAmount.gridy = 7;
		add(comboBoxPassengerAmount, gbc_comboBoxPassengerAmount);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 7;
		gbc_separator.insets = new Insets(0, 0, 5, 5);
		gbc_separator.gridx = 1;
		gbc_separator.gridy = 8;
		add(separator, gbc_separator);
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.anchor = GridBagConstraints.WEST;
		gbc_btnBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack.gridx = 1;
		gbc_btnBack.gridy = 9;
		add(btnBack, gbc_btnBack);
		
		lblNoFlights = new JLabel("No flights with selected criteria.");
		lblNoFlights.setForeground(Color.RED);
		lblNoFlights.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblNoFlights = new GridBagConstraints();
		gbc_lblNoFlights.gridwidth = 5;
		gbc_lblNoFlights.insets = new Insets(0, 0, 5, 5);
		gbc_lblNoFlights.gridx = 2;
		gbc_lblNoFlights.gridy = 9;
		add(lblNoFlights, gbc_lblNoFlights);
		
		JButton btnFilter = new JButton("Filter");
		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// update selected passengerAmount, fire PropertyChangeEvent
				setPassengerAmount(Integer.parseInt(comboBoxPassengerAmount.getSelectedItem().toString()));
				
				airportDepartInput = comboBoxDepart.getSelectedItem().toString();
				try {
					airportDepartInput = airports.get(airportDepartInput).substring(0,3);
				} catch (NullPointerException ex) {
					// lblNoFlights.setVisible(true);
					// lblNoFlights.setText("No flights from city of departure.");
					// return;
				}
				
				airportDepartInput = airportDepartInput.substring(0, 3);
				
				airportArriveInput = comboBoxArrive.getSelectedItem().toString();
				try {
					airportArriveInput = airports.get(airportArriveInput).substring(0,3);
				} catch (NullPointerException ex) {
					// lblNoFlights.setVisible(true);
					// lblNoFlights.setText("No flights to city of arrival.");
					// return;
				}
				
				airportArriveInput = airportArriveInput.substring(0, 3);
				
				dateDepartingInput = comboBoxYearD.getSelectedItem().toString() + "-" +
									 comboBoxMonthD.getSelectedItem().toString() + "-" +
									 comboBoxDayD.getSelectedItem().toString();
				
				// check if valid date
				try {
					LocalDateTime.parse(dateDepartingInput);
				} catch (DateTimeParseException ex) {
					// Display error message.
					lblNoFlights.setVisible(true);
					lblNoFlights.setText("Invalid date.");
				}
				
				// sort.sortFlights("LA", "NYC", "2023-10-24");
				try {
					if (afterDateTime()) {
						// hide Component for 'no flights found'
						lblNoFlights.setVisible(false);
						
						// sort flights per user input
						sort.sortFlights(airportDepartInput, airportArriveInput, dateDepartingInput);
						// flightListSorted = sort.getList(airportDepartInput, airportArriveInput, dateDepartingInput);
						ArrayList<Flight> flightArray = sort.getFlights();
						
						// instantiate a FlightFilterScrollPane with generated flightListSorted as a parameter
						FlightListPane FilterListPane = new FlightListPane(contentPane, account, flightArray, flight);
						contentPane.add(FilterListPane, "FILTER_LIST");
						
						// proceed to filtered list of flights, sorted by date of departure by default
						((CardLayout) contentPane.getLayout()).show(contentPane, "FILTER_LIST");
					} else {
						lblNoFlights.setVisible(true);
						lblNoFlights.setText("Date passed.");
						
					}
				} catch (NullPointerException ex) {
					// show Component 'no flights found'
					lblNoFlights.setVisible(true);
					lblNoFlights.setText("No flights with selected criteria.");
				}
			}
		});
		GridBagConstraints gbc_btnFilter = new GridBagConstraints();
		gbc_btnFilter.insets = new Insets(0, 0, 5, 5);
		gbc_btnFilter.gridx = 7;
		gbc_btnFilter.gridy = 9;
		add(btnFilter, gbc_btnFilter);
		
		lblNoFlights.setVisible(false);
		this.setToCurrentDate();
	}
	
	/**
	 * Fire PropertyChangeEvent for selected passenger amount.
	 * @param selectedPassengerAmount
	 */
	private void setPassengerAmount(int selectedPassengerAmount) {
		support.firePropertyChange("passengerAmount", passengerAmount, selectedPassengerAmount);
		this.selectedPassengerAmount = selectedPassengerAmount;
	}
	
	/**
	 * Sets JComboBoxes for Date of Departure (Month, Day, Year) to current date.
	 */
	private void setToCurrentDate() {
		comboBoxMonthD.setSelectedItem(String.format("%02d", ZonedDateTime.now().getMonthValue()));
		comboBoxDayD.setSelectedItem(String.format("%02d", ZonedDateTime.now().getDayOfMonth()));
		comboBoxYearD.setSelectedItem(String.valueOf(ZonedDateTime.now().getYear()));
	}
	
	/**
	 * Checks whether selected date and time of departure is after the current date.
	 * @return true if selected date and time is after the current date
	 */
	private boolean afterDateTime() {
		int year = Integer.parseInt(comboBoxYearD.getSelectedItem().toString());
		int month = Integer.parseInt(comboBoxMonthD.getSelectedItem().toString());
		int day = Integer.parseInt(comboBoxDayD.getSelectedItem().toString());
		LocalDateTime ldt = LocalDateTime.of(year, month, day, 00, 00);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZonedDateTime.now().getZone());
		if (ChronoUnit.DAYS.between(zdt, ZonedDateTime.now()) > 0) {
			return false;
		} else {
			return true;
		}
	}

}
