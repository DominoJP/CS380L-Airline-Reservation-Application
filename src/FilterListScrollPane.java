import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;

/**
   JPanel in BorderLayout with JScrollPane, allowing use of JList to display and select flights.
   @author
   @verison
*/

public class FilterListScrollPane extends JPanel {

	private static final long serialVersionUID = 1L;

	public FilterListScrollPane(JPanel contentPane) {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		
		JButton btnHeader = new JButton("Reserve Selected Flight");
		scrollPane.setColumnHeaderView(btnHeader);
		
		// ADD CASE FOR NO FLIGHTS FOUND: RETOOL "RESERVE SELECTED FLIGHT" BUTTON INTO "GO BACK BUTTON"
		String[] testArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s"};
		JList listFlights = new JList(testArray);
		scrollPane.setViewportView(listFlights);
		listFlights.setSelectedIndex(0);
		// returns Obj
		// listScrollPaneTest.getSelectedValue();
				
	}

}
