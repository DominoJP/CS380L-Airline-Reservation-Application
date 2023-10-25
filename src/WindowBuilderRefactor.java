import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;

/**
   Swing JFrame in which initial JPanel is instantiated.
   Instantiates and adds JPanels to CardLayout, allowing switching between.
   @author
   @version
 */

public class WindowBuilderRefactor extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	


	public static void main(String[] args) {
		Flight test = new Flight("One Way","LA", "NYC", "24/10/2023", "5:30", 50, 700.0);
		FlightSorting sort = new FlightSorting(test);
		Flight test2 = new Flight("One Way","LA", "NYC", "24/10/2023", "7:30", 50, 700.0);
		sort.addFlight(test2);
		
		sort.sortFlights("LA", "NYC", "24/10/2023");
		
		Flight found = sort.findFlight("5:30");
		
		System.out.println(found.getcityDeparture());
		
		found = sort.findFlight("7:30");
		
		System.out.println(found.gettimeDeparture());
		
		
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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		AccountSignInPane SignInPane = new AccountSignInPane(contentPane);
		AccountSignUpPane SignUpPane = new AccountSignUpPane(contentPane);
		OptionSelectionPane SelectionPane = new OptionSelectionPane(contentPane);
		FlightFilterPane FilterPane = new FlightFilterPane(contentPane);
		FilterListScrollPane FilterListPane = new FilterListScrollPane(contentPane);
		contentPane.add(SignInPane, "SIGNIN");
		contentPane.add(SignUpPane, "SIGNUP");
		contentPane.add(SelectionPane, "SELECT");
		contentPane.add(FilterPane, "FILTER");
		contentPane.add(FilterListPane, "FILTERLIST");
			
	}

}