import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

/**
   Swing JFrame in which initial JPanel is instantiated.
   Instantiates and adds JPanels to CardLayout, allowing switching between JPanels.
   @author
   @version
 */

public class WindowBuilderRefactor extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
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
		
		setDefaultCloseOperation(WindowBuilderRefactor.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = (JPanel) new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		AccountSignInPane SignInPane = new AccountSignInPane(contentPane);
		AccountSignUpPane SignUpPane = new AccountSignUpPane(contentPane);
		OptionSelectionPane SelectionPane = new OptionSelectionPane(contentPane);
<<<<<<< Updated upstream
		FlightFilterPane FilterPane = new FlightFilterPane(contentPane);
		FlightFilterListScrollPane FilterListPane = new FlightFilterListScrollPane(contentPane);
		TravellerInformationPane TravellerInfoPane = new TravellerInformationPane(contentPane);
		ReservationCancellationPane ReservationCancellationPane = new ReservationCancellationPane(contentPane, cancelReservation);
=======
		FlightFilterPane FilterPane = new FlightFilterPane(contentPane, sort);
		// Instantiation of FLightFilterListScrollPane must happen at ActionLister of FLightFilterPane, AFTER the instantiation of the sorted list for the JList
		// FlightFilterListScrollPane FilterListPane = new FlightFilterListScrollPane(contentPane);
		PassengerDetailsPane PassengerOnePane = new PassengerDetailsPane(contentPane, 1, FilterPane.getPassengerAmount(), "PASSENGER2_DETAILS");
		// FIXME, add up until 9
		PassengerDetailsPane PassengerTwoPane = new PassengerDetailsPane(contentPane, 2, FilterPane.getPassengerAmount(), "NULL");
		TripContactPane TripContactPane =  new TripContactPane(contentPane);
		// FIXME: temp. commented out
		 ReservationCancellationPane ReservationCancellationPane = new ReservationCancellationPane();
		
>>>>>>> Stashed changes
		contentPane.add(SignInPane, "SIGNIN");
		contentPane.add(SignUpPane, "SIGNUP");
		contentPane.add(SelectionPane, "SELECT");
		contentPane.add(FilterPane, "FILTER");
<<<<<<< Updated upstream
		contentPane.add(FilterListPane, "FILTER_LIST");
		contentPane.add(TravellerInfoPane, "TRAVELLER_INFO");		
=======
		// In FlightFilterPane
		// contentPane.add(FilterListPane, "FILTER_LIST");
		contentPane.add(PassengerOnePane, "PASSENGER1_DETAILS");
		contentPane.add(PassengerTwoPane, "PASSENGER2_DETAILS");
		contentPane.add(TripContactPane, "TRIP_CONTACT");
	
		// FIXME: temp. commented out
>>>>>>> Stashed changes
		contentPane.add(ReservationCancellationPane, "Cancel");
		
	}

}