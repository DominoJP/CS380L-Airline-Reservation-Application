import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JComboBox;

/**
   JPanel in BorderLayout with JScrollPane, allowing use of JList to display and select flights.
   @author Jevy Miranda
   @version 1.0
*/

public class FlightFilterListScrollPane extends JPanel {
	private Flight selectedFlight;

	private static final long serialVersionUID = 1L;

	public FlightFilterListScrollPane(JPanel contentPane, Account account, ArrayList<Flight> flightArray, Flight flight) {
		
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		// SHOW: FLIGHT IDENTIFIER, PLACE OF DEPARTURE/ARRIVAL, TIME OF DEPARTURE/ARRIVAL, DURATION, CLASS COST
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
