import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToolBar;
import javax.swing.JComboBox;

/**
   JPanel in BorderLayout with JScrollPane, allowing use of JList to display and select flights.
   @author Jevy Miranda
   @version 1.0
*/

public class FlightFilterListScrollPane extends JPanel {

	private static final long serialVersionUID = 1L;

	public FlightFilterListScrollPane(JPanel contentPane, String[] flightListSorted, String airportDepartingInput, String airportArrivalInput, String dateDepartingInput) {
		// uses user inputs passed in the constructor from previous FlightFilterPane as parameters for FlightSorting .getList() method
		// String[] flightListSorted = sort.getList(airportDepartingInput, airportArrivalInput, dateDepartingInput);
		
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		// SHOW: FLIGHT IDENTIFIER, PLACE OF DEPARTURE/ARRIVAL, TIME OF DEPARTURE/ARRIVAL, DURATION, CLASS COSt
		JList listFlights = new JList(flightListSorted);
		scrollPane.setViewportView(listFlights);
		listFlights.setSelectedIndex(0);
		
		JToolBar toolBar = new JToolBar();
		scrollPane.setColumnHeaderView(toolBar);
		
		JButton btnReturn = new JButton("Return");
		toolBar.add(btnReturn);
		
		JComboBox comboBox = new JComboBox();
		toolBar.add(comboBox);
		
		JButton btnSort = new JButton("Sort");
		toolBar.add(btnSort);
		
		JButton btnBook = new JButton("Book Selected Flight");
		toolBar.add(btnBook);
		// returns Obj
				
	}

}
