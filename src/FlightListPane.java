import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JComboBox;

/**
 * a) 'FlightListUI'
 * b) Date of Creation: October 11, 2023
 * c) @author Jevy Miranda
 * d) Description: JPanel subclass in BorderLayout with JScrollPane, 
 *    allowing use of JList for flight display and selection.
 * e) Functions: Invokes a Flight method which fires the PropertyChangeEvent for "selectedFlight"
 *    based on user selection.
 * f) Data Structures: N/A
 * g) Algorithms: N/A	  
 */
public class FlightListPane extends JPanel {
	private static final long serialVersionUID = 1L;

	public FlightListPane(JPanel contentPane, Account account, ArrayList<Flight> flightArray, Flight flight) {
		
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		JList listFlights = new JList(flightArray.toArray());
		scrollPane.setViewportView(listFlights);
		listFlights.setSelectedIndex(0);
		
		JToolBar toolBar = new JToolBar();
		scrollPane.setColumnHeaderView(toolBar);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, "FILTER");
			}
		});
		toolBar.add(btnReturn);
		
		JComboBox comboBox = new JComboBox();
		toolBar.add(comboBox);
		
		JButton btnSort = new JButton("Sort");
		toolBar.add(btnSort);
		
		JButton btnContinue = new JButton("Continue to Cabin Selection");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Flight tempFlight = flightArray.get(listFlights.getSelectedIndex());
				//
				// flight.assign(tempFlight);
				flight.assign(flightArray.get(listFlights.getSelectedIndex()));
				// flight.setDateArrival(tempFlight.getDateArrival().toString());
				// flight.setTimeArrival(tempFlight.getTimeArrival().toString());
				// ReservationPaymentPane PaymentPane = new ReservationPaymentPane(contentPane, account, tempFlight);
				// contentPane.add(PaymentPane, "PAY");
				((CardLayout) contentPane.getLayout()).show(contentPane, "CABIN");
			}
		});
		toolBar.add(btnContinue);
				
	}

}
