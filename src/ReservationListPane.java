import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JList;
import javax.swing.JToolBar;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
 * Design Documentation: "ReservationListUI."
 * Description: JPanel subclass in BorderLayout with JScrollPane.
 * Pane displays the list of active reservations associated with current account.
 * <p>
 * Functions: PropertyChangeListener for "newReservation" allows reservations booked in the current JVM
 * instance to appear in the list alongside reservations instantiated from .txt at log in.
 * Reservation selected on 'Review' JButton press has details shown in another Pane.
 * <p>
 * Data Structures: N/A.
 * Algorithms: N/A.
 * @version 1.2, Last Modified: November 8, 2023
 * @author Jevy Miranda
 */

public class ReservationListPane extends JPanel implements PropertyChangeListener {
	private ArrayList<Reservation> reservations;
	
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
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, "MENU");
			}
		});
		toolBar.add(btnReturn);
		
		JComboBox comboBox = new JComboBox();
		toolBar.add(comboBox);
		
		JButton btnReview = new JButton("Review Selected Reservation");
		btnReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// FIXME: CANCELLATION PANE
				((CardLayout) contentPane.getLayout()).show(contentPane, "Cancel");
			}
		});
		
		JButton btnSort = new JButton("Sort");
		toolBar.add(btnSort);
		toolBar.add(btnReview);

	}

	/**
	 * Listens for PropertyChangeEvent.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ((evt.getPropertyName()).equals("newReservation")) {
			// this.reservations = ((ArrayList<Reservation>) evt.getNewValue());
			model.addElement((Reservation) evt.getNewValue());
		}

	}

}
