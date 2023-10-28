import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;

/**
   JPanel accepting user input (round trip/oneway, airport/city of departure/arrival, date of departure and/or arrival, number of passengers)
   as criteria for filtering flights.
   @author Jevy Miranda
   @version 1.0
*/

public class FlightFilterPane extends JPanel {

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroupTripType = new ButtonGroup();
	private JTextField textFrom;
	private JTextField textTo;
	private JTextField textDepart;
	private JTextField textReturn;
	private JLabel lblReturn;
	private JLabel lblDepartInvalidDateFormat;
	private JLabel lblReturnInvalidDateFormat;
	private JComboBox comboBoxPassengerAmount;
	
	private String airportDepartInput;
	private String airportArriveInput;
	private String dateDepartingInput;
	private JComboBox comboBoxTest;

	public FlightFilterPane(JPanel contentPane) {
		
		//FIXME: REMOVE
		String[] test = {"LA", "BUR"};
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{78, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JRadioButton rdbtnRoundTrip = new JRadioButton("Round Trip");
		buttonGroupTripType.add(rdbtnRoundTrip);
		rdbtnRoundTrip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblReturn.setVisible(true);
				textReturn.setVisible(true);
			}
		});
		GridBagConstraints gbc_rdbtnRoundTrip = new GridBagConstraints();
		gbc_rdbtnRoundTrip.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnRoundTrip.gridx = 1;
		gbc_rdbtnRoundTrip.gridy = 0;
		add(rdbtnRoundTrip, gbc_rdbtnRoundTrip);
		
		JRadioButton rdbtnOneWay = new JRadioButton("One Way");
		buttonGroupTripType.add(rdbtnOneWay);
		rdbtnOneWay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblReturn.setVisible(false);
				textReturn.setVisible(false);
			}
		});
		
		GridBagConstraints gbc_rdbtnOneWay = new GridBagConstraints();
		gbc_rdbtnOneWay.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnOneWay.gridx = 3;
		gbc_rdbtnOneWay.gridy = 0;
		add(rdbtnOneWay, gbc_rdbtnOneWay);
		
		JLabel lblFrom = new JLabel(" From");
		GridBagConstraints gbc_lblFrom = new GridBagConstraints();
		gbc_lblFrom.anchor = GridBagConstraints.WEST;
		gbc_lblFrom.insets = new Insets(0, 0, 5, 5);
		gbc_lblFrom.gridx = 1;
		gbc_lblFrom.gridy = 1;
		add(lblFrom, gbc_lblFrom);
		
		JLabel lblTo = new JLabel(" To");
		GridBagConstraints gbc_lblTo = new GridBagConstraints();
		gbc_lblTo.anchor = GridBagConstraints.WEST;
		gbc_lblTo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTo.gridx = 3;
		gbc_lblTo.gridy = 1;
		add(lblTo, gbc_lblTo);
		
		textFrom = new JTextField();
		GridBagConstraints gbc_textFrom = new GridBagConstraints();
		gbc_textFrom.insets = new Insets(0, 0, 5, 5);
		gbc_textFrom.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFrom.gridx = 1;
		gbc_textFrom.gridy = 2;
		add(textFrom, gbc_textFrom);
		textFrom.setColumns(10);
		
		textTo = new JTextField();
		GridBagConstraints gbc_textTo = new GridBagConstraints();
		gbc_textTo.insets = new Insets(0, 0, 5, 5);
		gbc_textTo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textTo.gridx = 3;
		gbc_textTo.gridy = 2;
		add(textTo, gbc_textTo);
		textTo.setColumns(10);
		
		comboBoxTest = new JComboBox(test);
		GridBagConstraints gbc_comboBoxTest = new GridBagConstraints();
		gbc_comboBoxTest.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTest.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTest.gridx = 1;
		gbc_comboBoxTest.gridy = 3;
		add(comboBoxTest, gbc_comboBoxTest);
		
		JLabel lblDepart = new JLabel(" Depart (dd/mm/yyyy)");
		GridBagConstraints gbc_lblDepart = new GridBagConstraints();
		gbc_lblDepart.anchor = GridBagConstraints.WEST;
		gbc_lblDepart.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepart.gridx = 1;
		gbc_lblDepart.gridy = 4;
		add(lblDepart, gbc_lblDepart);
		
		lblReturn = new JLabel(" Return (dd/mm/yyyy)");
		GridBagConstraints gbc_lblReturn = new GridBagConstraints();
		gbc_lblReturn.anchor = GridBagConstraints.WEST;
		gbc_lblReturn.insets = new Insets(0, 0, 5, 5);
		gbc_lblReturn.gridx = 3;
		gbc_lblReturn.gridy = 4;
		add(lblReturn, gbc_lblReturn);
		
		textDepart = new JTextField();
		textDepart.setColumns(10);
		GridBagConstraints gbc_textDepart = new GridBagConstraints();
		gbc_textDepart.insets = new Insets(0, 0, 5, 5);
		gbc_textDepart.fill = GridBagConstraints.HORIZONTAL;
		gbc_textDepart.gridx = 1;
		gbc_textDepart.gridy = 5;
		add(textDepart, gbc_textDepart);
		
		textReturn = new JTextField();
		textReturn.setColumns(10);
		GridBagConstraints gbc_textReturn = new GridBagConstraints();
		gbc_textReturn.insets = new Insets(0, 0, 5, 5);
		gbc_textReturn.fill = GridBagConstraints.HORIZONTAL;
		gbc_textReturn.gridx = 3;
		gbc_textReturn.gridy = 5;
		add(textReturn, gbc_textReturn);
		
		lblDepartInvalidDateFormat = new JLabel("Invalid date format");
		lblDepartInvalidDateFormat.setForeground(Color.RED);
		lblDepartInvalidDateFormat.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblDepartInvalidDateFormat = new GridBagConstraints();
		gbc_lblDepartInvalidDateFormat.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartInvalidDateFormat.gridx = 1;
		gbc_lblDepartInvalidDateFormat.gridy = 7;
		add(lblDepartInvalidDateFormat, gbc_lblDepartInvalidDateFormat);
		lblDepartInvalidDateFormat.setVisible(false);
		
		lblReturnInvalidDateFormat = new JLabel("Invalid date format");
		lblReturnInvalidDateFormat.setForeground(Color.RED);
		lblReturnInvalidDateFormat.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GridBagConstraints gbc_lblReturnInvalidDateFormat = new GridBagConstraints();
		gbc_lblReturnInvalidDateFormat.insets = new Insets(0, 0, 5, 5);
		gbc_lblReturnInvalidDateFormat.gridx = 3;
		gbc_lblReturnInvalidDateFormat.gridy = 7;
		add(lblReturnInvalidDateFormat, gbc_lblReturnInvalidDateFormat);
		lblReturnInvalidDateFormat.setVisible(false);
		
		JLabel lblPassengerAmount = new JLabel("Passengers");
		GridBagConstraints gbc_lblPassengerAmount = new GridBagConstraints();
		gbc_lblPassengerAmount.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassengerAmount.gridx = 1;
		gbc_lblPassengerAmount.gridy = 8;
		add(lblPassengerAmount, gbc_lblPassengerAmount);
		
		Integer[] passengersAmountArray = {
				1, 2, 3, 4, 5, 6, 7, 8, 9
		};
		comboBoxPassengerAmount = new JComboBox(passengersAmountArray);
		GridBagConstraints gbc_comboBoxPassengerAmount = new GridBagConstraints();
		gbc_comboBoxPassengerAmount.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxPassengerAmount.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPassengerAmount.gridx = 1;
		gbc_comboBoxPassengerAmount.gridy = 9;
		add(comboBoxPassengerAmount, gbc_comboBoxPassengerAmount);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// FIXME: remove with implementation of file reader for instantiation of flights
				Flight test = new Flight("One Way","LA", "NYC", "24/10/2023", "5:30", "25/10/2023", "2:30", 50, 700.0);
				FlightSorting sort = new FlightSorting(test);
				Flight test2 = new Flight("One Way","LA", "NYC", "24/10/2023", "7:30", "24/10/2023", "9:30", 50, 700.0);
				sort.addFlight(test2);
				
				airportDepartInput = comboBoxTest.getSelectedItem().toString();
				airportArriveInput = "NYC";
				dateDepartingInput = "24/10/2023";
				
				
				sort.sortFlights(airportDepartInput, airportArriveInput, dateDepartingInput);
			

				String[] list = sort.getList(airportDepartInput, airportArriveInput, dateDepartingInput);
				
				
				FlightFilterListScrollPane FilterListPane = new FlightFilterListScrollPane(contentPane, list, airportDepartInput, airportArriveInput, dateDepartingInput);
				contentPane.add(FilterListPane, "FILTER_LIST");
				((CardLayout) contentPane.getLayout()).show(contentPane, "FILTER_LIST");
				
				
			}
		});
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 0, 5);
		gbc_btnSearch.gridx = 3;
		gbc_btnSearch.gridy = 9;
		add(btnSearch, gbc_btnSearch);
		
	}
	
	public int getPassengerAmount() {
		// FIXME: temp solution
		return Integer.parseInt(comboBoxPassengerAmount.getSelectedItem().toString());
	}
	
	public String getAirportDepartInput() {
		return this.airportDepartInput;
	}
	
	public String getAirportArriveInput() {
		return this.airportArriveInput;
	}
	
	public String getDateDeparture() {
		return this.dateDepartingInput;
	}

}
