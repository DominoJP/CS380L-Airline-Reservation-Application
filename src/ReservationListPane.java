import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.JButton;

//FIXME: ONLY DISPLAYS RESERVATIONS BOOKED ON CURRENT EXECUTION
public class ReservationListPane extends JPanel {
	private ArrayList<Reservation> reservations;
	private String[] reservationArray;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ReservationListPane(JPanel contentPane, Account account) {
		this.instantiateReservationList(account);
		
		setLayout(new BorderLayout(0, 0));
		
		JList list = new JList(reservationArray);
		add(list, BorderLayout.CENTER);
		
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("New button");
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		toolBar.add(btnNewButton_1);

	}
	
	public void instantiateReservationList(Account account) {
		Iterator<Reservation> iter = account.getReservationHistory().iterator();
		reservationArray = new String[account.getReservationHistory().size()];
		int i = 0;
		while (iter.hasNext()) {
			reservationArray[i] = iter.next().toString();
			i++;
		}
		System.out.println(reservationArray[0]);
	}

}
