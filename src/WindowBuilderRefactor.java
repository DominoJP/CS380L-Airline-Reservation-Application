import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

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
		
		// FIXME: commented-out blocks below are to be removed once changes communicated
		// block moved into WindowBuilderRefactor() below
		/*
		Flight test = new Flight("One Way","LA", "NYC", "24/10/2023", "5:30", "25/10/2023", "2:30", 50, 700.0);
		FlightSorting sort = new FlightSorting(test);
		Flight test2 = new Flight("One Way","LA", "NYC", "24/10/2023", "7:30", "24/10/2023", "9:30", 50, 700.0);
		sort.addFlight(test2);
		*/
		
		// sort Obj passed to FlightFilterPane to allow .sortFlights() method call based on user input
		// sort.sortFlights("LA", "NYC", "24/10/2023");
		
		// block moved into WindowBuilderRefactor() below
		/*
		String[] list = sort.getList("LA", "NYC", "24/10/2023");
		for(int i = 0; i < list.length; i+=2) {
			System.out.println("Departure: " + list[i] + "\n");
			System.out.println("Arrival: " + list[i+1] + "\n");
		}
		*/
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowBuilderRefactor frame = new WindowBuilderRefactor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WindowBuilderRefactor() {
		
		// FIXME: temp. test for instantiation of flights, FlightSorting object
		/*
		Flight test = new Flight("One Way","LA", "NYC", "24/10/2023", "5:30", "25/10/2023", "2:30", 50, 700.0);
		FlightSorting sort = new FlightSorting(test);
		Flight test2 = new Flight("One Way","LA", "NYC", "24/10/2023", "7:30", "24/10/2023", "9:30", 50, 700.0);
		sort.addFlight(test2);
		*/
		
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
		FlightFilterPane FilterPane = new FlightFilterPane(contentPane);
		// FIXME: shuffling instantiation of FlightSorting object to FlightFilterPane
		// FlightFilterPane FilterPane = new FlightFilterPane(contentPane, sort);
		// FIXME: to be removed, instantiated instead in FlightFilterPane to allow passing of reference to FlightSorting Obj
		// FlightFilterListScrollPane FilterListPane = new FlightFilterListScrollPane(contentPane, sort, FilterPane);
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
		// contentPane.add(FilterListPane, "FILTER_LIST");
		contentPane.add(PassengerOnePane, "PASSENGER1_DETAILS");
		contentPane.add(PassengerTwoPane, "PASSENGER2_DETAILS");
		contentPane.add(TripContactPane, "TRIP_CONTACT");
	
		// FIXME: temp. commented out
		// contentPane.add(ReservationCancellationPane, "Cancel");
		
		// FIXME: temp. Console test case, to be removed
		/*
		String[] list = sort.getList("LA", "NYC", "24/10/2023");
		for(int i = 0; i < list.length; i+=2) {
			System.out.println("Departure: " + list[i] + "\n");
			System.out.println("Arrival: " + list[i+1] + "\n");
		}
		*/
		
	}

}