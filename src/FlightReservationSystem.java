
import java.awt.EventQueue;
import java.math.BigDecimal;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.time.LocalDate;
import java.time.LocalTime;

/**
   Swing JFrame in which initial JPanel is instantiated.
   Instantiates and adds JPanels to CardLayout, allowing switching between JPanels.
   @author Jevy Miranda, Joshua Planovsky
   @version 3.0
 */
public class FlightReservationSystem extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public static void main(String[] args) {	
		
		//Instantiation of cancel reservation object.
		 CancelReservation cancelReservation = new CancelReservation("src/Database/Reservation.txt");
		
		// (Test cases transcribed to .txt)
		/*
		FlightSorting sort;
		Flight test = new Flight("One Way","LA", "NYC", "2023-10-24", "05:30", "2023-10-25", "02:30", 50, 700.0);
		sort = new FlightSorting(test);
		Flight test2 = new Flight("One Way","LA", "NYC", "2023-10-24", "07:30", "2023-10-24", "09:30", 50, 700.0);
		sort.addFlight(test2);
		Flight test3 = new Flight("One Way", "LA", "NYC", "2023-10-24", "03:30", "2023-10-27", "02:45", 50, 700.00);
		sort.addFlight(test3);
		*/
		
		
		/*
		LocalDate time = LocalDate.of(2023, 10, 20);
		LocalTime time2 = LocalTime.of(10, 30);
		time = time.plusDays(2);
		time = time.plusMonths(4);
		System.out.println(time.toString() + " " + time2.toString());
		LocalDate time3 = LocalDate.parse("2023-03-14");
		time3 = time3.plusDays(5);
		System.out.println(time3.toString());
		*/
		/*
		sort.sortFlights("LA", "NYC", "2023-10-24");
		String[] list = sort.getList("LA", "NYC", "2023-10-24");
		System.out.println(list.length);
		for(int i = 0; i < list.length; i++) {
			System.out.println(list[i] + "\n");
		}
		*/
		
		
		
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
		Flight selectedFlight = new Flight(0, null, null, null, "2000-01-01", "12:00", "2000-01-01", "12:00", "UTC");
		
		// FlightsTestReader flightsReader = new FlightsTestReader(); 
		// FlightSorting sort = flightsReader.getFlightSorting();
		FlightSorting sort = FlightIO.instantiateFlights();
		
		// Instantiation of JPanels
		AccountSignInPane SignInPane = new AccountSignInPane(contentPane, account);
		AccountSignUpPane SignUpPane = new AccountSignUpPane(contentPane);
		OptionSelectionPane SelectionPane = new OptionSelectionPane(contentPane, account);
		FlightFilterPane FilterPane = new FlightFilterPane(contentPane, account, sort, selectedFlight);
		// Instantiation of FlightFilterListScrollPane must happen at ActionLister of FlightFilterPane, AFTER the instantiation of the sorted list for the JList
		// FlightFilterListScrollPane FilterListPane = new FlightFilterListScrollPane(contentPane, account, flightListSorted, flightArray, flight);
		CabinClassPane CabinClassPane = new CabinClassPane(contentPane, selectedFlight);
		PassengerDetailsPane PassengerOnePane = new PassengerDetailsPane(contentPane, 1, "CABIN", "PASSENGER2_DETAILS", selectedFlight);
		PassengerDetailsPane PassengerTwoPane = new PassengerDetailsPane(contentPane, 2, "PASSENGER1_DETAILS", "PASSENGER3_DETAILS", selectedFlight);
		PassengerDetailsPane PassengerThreePane = new PassengerDetailsPane(contentPane, 3, "PASSENGER2_DETAILS", "PASSENGER4_DETAILS", selectedFlight);
		PassengerDetailsPane PassengerFourPane = new PassengerDetailsPane(contentPane, 4, "PASSENGER3_DETAILS", "PASSENGER5_DETAILS", selectedFlight);
		PassengerDetailsPane PassengerFivePane = new PassengerDetailsPane(contentPane, 5, "PASSENGER4_DETAILS", "PASSENGER6_DETAILS", selectedFlight);
		PassengerDetailsPane PassengerSixPane = new PassengerDetailsPane(contentPane, 6, "PASSENGER5_DETAILS", "NULL", selectedFlight);
		// TripContactPane TripContactPane = new TripContactPane(contentPane);
		ReservationPaymentPane PaymentPane = new ReservationPaymentPane(contentPane, account, selectedFlight);
		ReservationConfirmationPane ConfirmationPane = new ReservationConfirmationPane(contentPane);
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
		contentPane.add(ConfirmationPane, "CONFIRM");
		
		// Select "Review"
		contentPane.add(ReservationListPane, "REVIEW_LIST");
		
		// Select "Cancel"
		contentPane.add(ReservationCancellationPane, "Cancel");
		
	}
}