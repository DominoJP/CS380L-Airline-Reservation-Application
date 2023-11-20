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
 * e) Functions: In instantiating the JPanels, the current JPanel contentPane is passed as a parameter, allowing switching via CardLayout.
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
		
		// Sorted Flights
		FlightSorting sort = FlightIO.instantiateFlights();
		
		// Instantiation of JPanels
		SignInPane SignInPane = new SignInPane(contentPane, account);
		SignUpPane SignUpPane = new SignUpPane(contentPane);
		NavigationPane NavigationPane = new NavigationPane(contentPane, account);
		FilterPane FilterPane = new FilterPane(contentPane, account, sort, selectedFlight);
		// Instantiation of FlightFilterListScrollPane must happen at ActionLister of FlightFilterPane, AFTER the instantiation of the sorted list for the JList
		// FlightFilterListScrollPane FilterListPane = new FlightFilterListScrollPane(contentPane, account, flightListSorted, flightArray, flight);
		CabinPane CabinClassPane = new CabinPane(contentPane, selectedFlight);
		PassengerPane PassengerOnePane = new PassengerPane(contentPane, 1, "CABIN", "PASSENGER2", selectedFlight);
		PassengerPane PassengerTwoPane = new PassengerPane(contentPane, 2, "PASSENGER1", "PASSENGER3", selectedFlight);
		PassengerPane PassengerThreePane = new PassengerPane(contentPane, 3, "PASSENGER2", "PASSENGER4", selectedFlight);
		PassengerPane PassengerFourPane = new PassengerPane(contentPane, 4, "PASSENGER3", "PASSENGER5", selectedFlight);
		PassengerPane PassengerFivePane = new PassengerPane(contentPane, 5, "PASSENGER4", "PASSENGER6", selectedFlight);
		PassengerPane PassengerSixPane = new PassengerPane(contentPane, 6, "PASSENGER5", "NULL", selectedFlight);
		PaymentPane PaymentPane = new PaymentPane(contentPane, account, selectedFlight);
		ReservationListPane ReservationListPane = new ReservationListPane(contentPane, account);
		ReservationCancellationPane ReservationCancellationPane = new ReservationCancellationPane(CancelReservation, contentPane);
		
		
		// Program start
		contentPane.add(SignInPane, "SIGNIN");
		contentPane.add(SignUpPane, "SIGNUP");
		contentPane.add(NavigationPane, "MENU");
		
		// Select "Reserve"
		contentPane.add(FilterPane, "FILTER");
		// In FlightFilterPane
		// contentPane.add(FilterListPane, "FILTER_LIST");
		contentPane.add(CabinClassPane, "CABIN");
		contentPane.add(PassengerOnePane, "PASSENGER1");
		contentPane.add(PassengerTwoPane, "PASSENGER2");
		contentPane.add(PassengerThreePane, "PASSENGER3");
		contentPane.add(PassengerFourPane, "PASSENGER4");
		contentPane.add(PassengerFivePane, "PASSENGER5");
		contentPane.add(PassengerSixPane, "PASSENGER6");
		// contentPane.add(TripContactPane, "TRIP_CONTACT");
		contentPane.add(PaymentPane, "PAY");

		// Select "Review"
		contentPane.add(ReservationListPane, "REVIEW_LIST");
		
		// Select "Cancel"
		contentPane.add(ReservationCancellationPane, "Cancel");
		
		
		// in pattern observable.addPropertyChangeListener(observer)
		// id & email
		account.addPropertyChangeListener(ReservationListPane);
		account.addPropertyChangeListener(PaymentPane);
		
		// user-selected flight
		selectedFlight.addPropertyChangeListener(PaymentPane);
		selectedFlight.addPropertyChangeListener(CabinClassPane);
		selectedFlight.addPropertyChangeListener(PassengerOnePane);
		selectedFlight.addPropertyChangeListener(PassengerTwoPane);
		selectedFlight.addPropertyChangeListener(PassengerThreePane);
		selectedFlight.addPropertyChangeListener(PassengerFourPane);
		selectedFlight.addPropertyChangeListener(PassengerFivePane);
		selectedFlight.addPropertyChangeListener(PassengerSixPane);
		
		// running total
		CabinClassPane.addPropertyChangeListener(PaymentPane);
		PassengerOnePane.addPropertyChangeListener(PaymentPane);
		PassengerTwoPane.addPropertyChangeListener(PaymentPane);
		PassengerThreePane.addPropertyChangeListener(PaymentPane);
		PassengerFourPane.addPropertyChangeListener(PaymentPane);
		PassengerFivePane.addPropertyChangeListener(PaymentPane);
		PassengerSixPane.addPropertyChangeListener(PaymentPane);
		
		// selected passenger amount
		FilterPane.addPropertyChangeListener(PaymentPane);
		FilterPane.addPropertyChangeListener(CabinClassPane);
		FilterPane.addPropertyChangeListener(PassengerOnePane);
		FilterPane.addPropertyChangeListener(PassengerTwoPane);
		FilterPane.addPropertyChangeListener(PassengerThreePane);
		FilterPane.addPropertyChangeListener(PassengerFourPane);
		FilterPane.addPropertyChangeListener(PassengerFivePane);
		FilterPane.addPropertyChangeListener(PassengerSixPane);
		
		// confirmation message
		PaymentPane.addPropertyChangeListener(NavigationPane);
		SignUpPane.addPropertyChangeListener(NavigationPane);
		
	}
}