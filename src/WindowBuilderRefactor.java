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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		AccountSignInPane SignInPane = new AccountSignInPane(contentPane);
		AccountSignUpPane SignUpPane = new AccountSignUpPane(contentPane);
		OptionSelectionPane SelectionPane = new OptionSelectionPane(contentPane);
		FlightFilterPane FilterPane = new FlightFilterPane(contentPane);
		FlightFilterListScrollPane FilterListPane = new FlightFilterListScrollPane(contentPane);
		TravellerInformationPane TravellerInfoPane = new TravellerInformationPane(contentPane);
		contentPane.add(SignInPane, "SIGNIN");
		contentPane.add(SignUpPane, "SIGNUP");
		contentPane.add(SelectionPane, "SELECT");
		contentPane.add(FilterPane, "FILTER");
		contentPane.add(FilterListPane, "FILTER_LIST");
		contentPane.add(TravellerInfoPane, "TRAVELLER_INFO");
	}

}