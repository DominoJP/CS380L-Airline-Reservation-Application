import java.awt.EventQueue;
import java.math.BigDecimal;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

/**
 * a) Design Documentation: 'FlightReservationSystem'
 * b) Date of Creation: October 8, 2023
 * c) @author Jevy Miranda, Joshua Planovsky
 * d) Description: JFrame subclass in which initial JPanel is instantiated.
 * 	  Containing main method, it is also the control class in which most JPanels are instantiated.
 * e) Functions: Instantiation of JPanels passes the current JPanel contentPane, allowing switching via CardLayout.
 * 	  Dummy objects for the observable Account and Flight class are also passed to observer panes.
 * f) Data Structures: N/A
 * g) Algorithms: N/A
 */
public class FlightReservationSystem extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {	
		
		//Instantiation of cancel reservation object.
		CancelReservation cancelReservation = new CancelReservation("src/Database/Reservations.txt");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlightReservationSystem frame = new FlightReservationSystem( cancelReservation);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FlightReservationSystem(CancelReservation CancelReservation) {
		
		setDefaultCloseOperation(FlightReservationSystem.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane = (JPanel) new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		// Placeholder Account object to be reassigned at sign in.
		Account account = new Account(null, null, null, 0000);
		
		// Placeholder Flight object to be reassigned at flight selection.
		Flight selectedFlight = new Flight(0, null, null, null, "2000-01-01", "12:00", "2000-01-01", "12:00", "UTC",
										   0, 0, new BigDecimal("0.00"), 0, 0, new BigDecimal("0.00"), 0, 0, new BigDecimal("0.00"));
		
		// FlightsTestReader flightsReader = new FlightsTestReader(); 
		// FlightSorting sort = flightsReader.getFlightSorting();
		FlightSorting sort = FlightIO.instantiateFlights();
		
		// Instantiation of JPanels
		SignInPane SignInPane = new SignInPane(contentPane, account);
		SignUpPane SignUpPane = new SignUpPane(contentPane);
		MenuPane SelectionPane = new MenuPane(contentPane, account);
		FilterPane FilterPane = new FilterPane(contentPane, account, sort, selectedFlight);
		// Instantiation of FlightFilterListScrollPane must happen at ActionLister of FlightFilterPane, AFTER the instantiation of the sorted list for the JList
		// FlightFilterListScrollPane FilterListPane = new FlightFilterListScrollPane(contentPane, account, flightListSorted, flightArray, flight);
		CabinPane CabinClassPane = new CabinPane(contentPane, selectedFlight);
		PassengerPane PassengerOnePane = new PassengerPane(contentPane, 1, "CABIN", "PASSENGER2_DETAILS", selectedFlight);
		PassengerPane PassengerTwoPane = new PassengerPane(contentPane, 2, "PASSENGER1_DETAILS", "PASSENGER3_DETAILS", selectedFlight);
		PassengerPane PassengerThreePane = new PassengerPane(contentPane, 3, "PASSENGER2_DETAILS", "PASSENGER4_DETAILS", selectedFlight);
		PassengerPane PassengerFourPane = new PassengerPane(contentPane, 4, "PASSENGER3_DETAILS", "PASSENGER5_DETAILS", selectedFlight);
		PassengerPane PassengerFivePane = new PassengerPane(contentPane, 5, "PASSENGER4_DETAILS", "PASSENGER6_DETAILS", selectedFlight);
		PassengerPane PassengerSixPane = new PassengerPane(contentPane, 6, "PASSENGER5_DETAILS", "NULL", selectedFlight);
		// TripContactPane TripContactPane = new TripContactPane(contentPane);
		PaymentPane PaymentPane = new PaymentPane(contentPane, account, selectedFlight);
		ReservationListPane ReservationListPane = new ReservationListPane(contentPane, account);
		 ReservationCancellationPane ReservationCancellationPane = new ReservationCancellationPane(CancelReservation, contentPane);
		
		
		// in pattern observable.addPropertyChangeListener(observer)
		account.addPropertyChangeListener(ReservationListPane);
		account.addPropertyChangeListener(PaymentPane);
		
		// keep track of user-selected flight
		selectedFlight.addPropertyChangeListener(PaymentPane);
		selectedFlight.addPropertyChangeListener(CabinClassPane);
		selectedFlight.addPropertyChangeListener(PassengerOnePane);
		selectedFlight.addPropertyChangeListener(PassengerTwoPane);
		selectedFlight.addPropertyChangeListener(PassengerThreePane);
		selectedFlight.addPropertyChangeListener(PassengerFourPane);
		selectedFlight.addPropertyChangeListener(PassengerFivePane);
		selectedFlight.addPropertyChangeListener(PassengerSixPane);
		
		// calculate running totals
		CabinClassPane.addPropertyChangeListener(PaymentPane);
		PassengerOnePane.addPropertyChangeListener(PaymentPane);
		PassengerTwoPane.addPropertyChangeListener(PaymentPane);
		PassengerThreePane.addPropertyChangeListener(PaymentPane);
		PassengerFourPane.addPropertyChangeListener(PaymentPane);
		PassengerFivePane.addPropertyChangeListener(PaymentPane);
		PassengerSixPane.addPropertyChangeListener(PaymentPane);
		
		// For passenger amount comparison
		FilterPane.addPropertyChangeListener(PaymentPane);
		FilterPane.addPropertyChangeListener(CabinClassPane);
		FilterPane.addPropertyChangeListener(PassengerOnePane);
		FilterPane.addPropertyChangeListener(PassengerTwoPane);
		FilterPane.addPropertyChangeListener(PassengerThreePane);
		FilterPane.addPropertyChangeListener(PassengerFourPane);
		FilterPane.addPropertyChangeListener(PassengerFivePane);
		FilterPane.addPropertyChangeListener(PassengerSixPane);
		
		// For confirmation message
		PaymentPane.addPropertyChangeListener(SelectionPane);
		SignUpPane.addPropertyChangeListener(SelectionPane);
		
		
		// Program start
		contentPane.add(SignInPane, "SIGNIN");
		contentPane.add(SignUpPane, "SIGNUP");
		contentPane.add(SelectionPane, "SELECT");
		
		// Select "Reserve"
		contentPane.add(FilterPane, "FILTER");
		// In FlightFilterPane
		// contentPane.add(FilterListPane, "FILTER_LIST");
		contentPane.add(CabinClassPane, "CABIN");
		contentPane.add(PassengerOnePane, "PASSENGER1_DETAILS");
		contentPane.add(PassengerTwoPane, "PASSENGER2_DETAILS");
		contentPane.add(PassengerThreePane, "PASSENGER3_DETAILS");
		contentPane.add(PassengerFourPane, "PASSENGER4_DETAILS");
		contentPane.add(PassengerFivePane, "PASSENGER5_DETAILS");
		contentPane.add(PassengerSixPane, "PASSENGER6_DETAILS");
		// contentPane.add(TripContactPane, "TRIP_CONTACT");
		contentPane.add(PaymentPane, "PAY");

		// Select "Review"
		contentPane.add(ReservationListPane, "REVIEW_LIST");
		
		// Select "Cancel"
		contentPane.add(ReservationCancellationPane, "Cancel");
		
	}
}