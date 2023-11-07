import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class ReservationListPane extends JPanel implements PropertyChangeListener {
	private ArrayList<Reservation> reservations;
	private String[] reservationArray;
	
	private DefaultListModel model = new DefaultListModel();
	private JList list;

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ReservationListPane(JPanel contentPane, Account account) {
		
		setLayout(new BorderLayout(0, 0));
		
		list = new JList(model);
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
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		this.reservations = ((ArrayList<Reservation>) evt.getNewValue());

		model.addAll(reservations);

	}
	
	private void addE(String e) {
		model.addElement(e);
	}

}
