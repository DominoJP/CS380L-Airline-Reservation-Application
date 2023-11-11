import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

import java.time.LocalDate;
import java.time.LocalTime;

/**
   Swing JFrame in which initial JPanel is instantiated.
   Instantiates and adds JPanels to CardLayout, allowing switching between JPanels.
   @author Jevy Miranda, Joshua Planovsky
   @version 1.0
 */

public class Main extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	


	public static void main(String[] args) {
		
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
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		
		setDefaultCloseOperation(Main.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane = (JPanel) new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		
		
		// Placeholder Account Object to be reassigned.
		Account account = new Account(null, null, null, 0000);
		
		// Placeholder Flight Object to be reassigned.
<<<<<<< HEAD
		Flight flight = new Flight(0, null, null, null, null, "2023-10-24", "12:00", "2000-01-01", "12:00", 0, 0.0);
=======
		Flight flight = new Flight(null, null, null, "2023-10-24", "12:00", "2000-01-01", "12:00", 0, 0.0);
>>>>>>> parent of d2601a7 (Merge branch 'feature/payment')
		
		FlightsTestReader flightsReader = new FlightsTestReader(); 
		FlightSorting sort = flightsReader.getFlightSorting();
		
		
		
		
		AccountSignInPane SignInPane = new AccountSignInPane(contentPane, account);
		AccountSignUpPane SignUpPane = new AccountSignUpPane(contentPane);
		OptionSelectionPane SelectionPane = new OptionSelectionPane(contentPane, account);
		FlightFilterPane FilterPane = new FlightFilterPane(contentPane, account, sort, flight);
		// Instantiation of FlightFilterListScrollPane must happen at ActionLister of FlightFilterPane, AFTER the instantiation of the sorted list for the JList
		// FlightFilterListScrollPane FilterListPane = new FlightFilterListScrollPane(contentPane, account, flightListSorted, flightArray, flight);
<<<<<<< HEAD
		PassengerDetailsPane PassengerOnePane = new PassengerDetailsPane(contentPane, 1, "PASSENGER2_DETAILS", flight);
		// FIXME, add up until 9
		PassengerDetailsPane PassengerTwoPane = new PassengerDetailsPane(contentPane, 2, "PASSENGER3_DETAILS", flight);
		PassengerDetailsPane PassengerThreePane = new PassengerDetailsPane(contentPane, 3, "PASSENGER4_DETAILS", flight);
		PassengerDetailsPane PassengerFourPane = new PassengerDetailsPane(contentPane, 4, "PASSENGER5_DETAILS", flight);
		PassengerDetailsPane PassengerFivePane = new PassengerDetailsPane(contentPane, 5, "PASSENGER6_DETAILS", flight);
		PassengerDetailsPane PassengerSixPane = new PassengerDetailsPane(contentPane, 6, "NULL", flight);
=======
		PassengerDetailsPane PassengerOnePane = new PassengerDetailsPane(contentPane, 1, "PASSENGER2_DETAILS");
		// FIXME, add up until 9
		PassengerDetailsPane PassengerTwoPane = new PassengerDetailsPane(contentPane, 2, "PASSENGER3_DETAILS");
		PassengerDetailsPane PassengerThreePane = new PassengerDetailsPane(contentPane, 3, "PASSENGER4_DETAILS");
		PassengerDetailsPane PassengerFourPane = new PassengerDetailsPane(contentPane, 4, "PASSENGER5_DETAILS");
		PassengerDetailsPane PassengerFivePane = new PassengerDetailsPane(contentPane, 5, "PASSENGER6_DETAILS");
		PassengerDetailsPane PassengerSixPane = new PassengerDetailsPane(contentPane, 6, "NULL");
>>>>>>> parent of d2601a7 (Merge branch 'feature/payment')
		// FIXME: POSSIBLY REMOVE
		TripContactPane TripContactPane = new TripContactPane(contentPane);
		ReservationPaymentPane PaymentPane = new ReservationPaymentPane(contentPane, account, flight);
		ReservationConfirmationPane ConfirmationPane = new ReservationConfirmationPane(contentPane);
		ReservationListPane ReviewPane = new ReservationListPane(contentPane, account);

		// FIXME: temp. commented out
		// ReservationCancellationPane ReservationCancellationPane = new ReservationCancellationPane(contentPane, cancelReservation);
		
		
		// Observers
		account.addPropertyChangeListener(ReviewPane);
		flight.addPropertyChangeListener(PaymentPane);
<<<<<<< HEAD
		flight.addPropertyChangeListener(PassengerOnePane);
		flight.addPropertyChangeListener(PassengerTwoPane);
		flight.addPropertyChangeListener(PassengerThreePane);
		flight.addPropertyChangeListener(PassengerFourPane);
		flight.addPropertyChangeListener(PassengerFivePane);
		flight.addPropertyChangeListener(PassengerSixPane);
		
		// calculate running totals
		PassengerOnePane.addPropertyChangeListener(PaymentPane);
		PassengerTwoPane.addPropertyChangeListener(PaymentPane);
		PassengerThreePane.addPropertyChangeListener(PaymentPane);
		PassengerFourPane.addPropertyChangeListener(PaymentPane);
		PassengerFivePane.addPropertyChangeListener(PaymentPane);
		PassengerSixPane.addPropertyChangeListener(PaymentPane);
		
		// For passenger amount comparison
=======
>>>>>>> parent of d2601a7 (Merge branch 'feature/payment')
		FilterPane.addPropertyChangeListener(PassengerOnePane);
		FilterPane.addPropertyChangeListener(PassengerTwoPane);
		FilterPane.addPropertyChangeListener(PassengerThreePane);
		FilterPane.addPropertyChangeListener(PassengerFourPane);
		FilterPane.addPropertyChangeListener(PassengerFivePane);
		FilterPane.addPropertyChangeListener(PassengerSixPane);
		
		
		contentPane.add(SignInPane, "SIGNIN");
		contentPane.add(SignUpPane, "SIGNUP");
		contentPane.add(SelectionPane, "SELECT");
		
		// select "Reserve"
		contentPane.add(FilterPane, "FILTER");
		// In FlightFilterPane
		// contentPane.add(FilterListPane, "FILTER_LIST");
		contentPane.add(PassengerOnePane, "PASSENGER1_DETAILS");
		contentPane.add(PassengerTwoPane, "PASSENGER2_DETAILS");
		contentPane.add(PassengerThreePane, "PASSENGER3_DETAILS");
		contentPane.add(PassengerFourPane, "PASSENGER4_DETAILS");
		contentPane.add(PassengerFivePane, "PASSENGER5_DETAILS");
		contentPane.add(PassengerSixPane, "PASSENGER6_DETAILS");
		// FIXME: POSSIBLY REMOVE
		contentPane.add(TripContactPane, "TRIP_CONTACT");
		contentPane.add(PaymentPane, "PAY");
		contentPane.add(ConfirmationPane, "CONFIRM");
		contentPane.add(ReviewPane, "REVIEW");
	
		// FIXME: temp. commented out
		// contentPane.add(ReservationCancellationPane, "Cancel");
		
	}

}