import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
   JPanel in BorderLayout with JScrollPane, allowing use of JList to display and select flights.
   @author Jevy Miranda
   @version 1.0
*/

public class FlightFilterListScrollPane extends JPanel {

	private static final long serialVersionUID = 1L;

	public FlightFilterListScrollPane(JPanel contentPane) {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		
		JButton btnHeader = new JButton("Reserve Selected Flight");
		btnHeader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// FIXME
				// use listScrollPaneTest.getSelectedValue();
				((CardLayout) contentPane.getLayout()).show(contentPane, "PASSENGER1_DETAILS");
			}
		});
		scrollPane.setColumnHeaderView(btnHeader);
		
		// SHOW: FLIGHT IDENTIFIER, PLACE OF DEPARTURE/ARRIVAL, TIME OF DEPARTURE/ARRIVAL, DURATION, CLASS COSt
		// (DISREGARD) ADD CASE FOR NO FLIGHTS FOUND: RETOOL "RESERVE SELECTED FLIGHT" BUTTON INTO "GO BACK BUTTON"
			// NEW DIRECTION: display "No Matches" message on previous JPanel (FlightFilterPane) and prompt to "Please try different search options."
		String[] testArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s"};
		JList listFlights = new JList(testArray);
		scrollPane.setViewportView(listFlights);
		listFlights.setSelectedIndex(0);
		// returns Obj
				
	}

}
