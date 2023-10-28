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

public class WindowBuilderRefactor extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	


	public static void main(String[] args) {
		
		// FIXME: remove with implementation of file reader for instantiation of flights
		Flight test = new Flight("One Way","LA", "NYC", "2023-10-24", "05:30", "2023-10-25", "02:30", 50, 700.0);
		FlightSorting sort = new FlightSorting(test);
		Flight test2 = new Flight("One Way","LA", "NYC", "2023-10-24", "07:30", "2023-10-24", "09:30", 50, 700.0);
		sort.addFlight(test2);

		Flight test3 = new Flight("One Way", "LA", "NYC", "2023-10-24", "03:30", "2023-10-27", "02:45", 50, 700.00);
		sort.addFlight(test3);
		
		LocalDate time = LocalDate.of(2023, 10, 20);
		LocalTime time2 = LocalTime.of(10, 30);

		time = time.plusDays(2);
		time = time.plusMonths(4);

		System.out.println(time.toString() + " " + time2.toString());

		LocalDate time3 = LocalDate.parse("2023-03-14");

		time3 = time3.plusDays(5);

		System.out.println(time3.toString());

		sort.sortFlights("LA", "NYC", "2023-10-24");

		String[] list = sort.getList("LA", "NYC", "2023-10-24");
		System.out.println(list.length);

		for(int i = 0; i < list.length; i++) {
			System.out.println(list[i] + "\n");
		}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowBuilderRefactor frame = new WindowBuilderRefactor(sort);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WindowBuilderRefactor(FlightSorting sort) {
		
		setDefaultCloseOperation(WindowBuilderRefactor.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane = (JPanel) new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		AccountSignInPane SignInPane = new AccountSignInPane(contentPane);
		AccountSignUpPane SignUpPane = new AccountSignUpPane(contentPane);
		OptionSelectionPane SelectionPane = new OptionSelectionPane(contentPane);
		FlightFilterPane FilterPane = new FlightFilterPane(contentPane, sort);
		// Instantiation of FLightFilterListScrollPane must happen at ActionLister of FLightFilterPane, AFTER the instantiation of the sorted list for the JList
		// FlightFilterListScrollPane FilterListPane = new FlightFilterListScrollPane(contentPane);
		PassengerDetailsPane PassengerOnePane = new PassengerDetailsPane(contentPane, 1, FilterPane.getPassengerAmount(), "PASSENGER2_DETAILS");
		// FIXME, add up until 9
		PassengerDetailsPane PassengerTwoPane = new PassengerDetailsPane(contentPane, 2, FilterPane.getPassengerAmount(), "NULL");
		TripContactPane TripContactPane =  new TripContactPane(contentPane);
		// FIXME: temp. commented out
		// ReservationCancellationPane ReservationCancellationPane = new ReservationCancellationPane(contentPane, cancelReservation);
		
		contentPane.add(SignInPane, "SIGNIN");
		contentPane.add(SignUpPane, "SIGNUP");
		contentPane.add(SelectionPane, "SELECT");
		
		// select "Reserve"
		contentPane.add(FilterPane, "FILTER");
		// In FlightFilterPane
		// contentPane.add(FilterListPane, "FILTER_LIST");
		contentPane.add(PassengerOnePane, "PASSENGER1_DETAILS");
		contentPane.add(PassengerTwoPane, "PASSENGER2_DETAILS");
		contentPane.add(TripContactPane, "TRIP_CONTACT");
	
		// FIXME: temp. commented out
		// contentPane.add(ReservationCancellationPane, "Cancel");
		
	}

}