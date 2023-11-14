import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Author: Joshua Planovsky
 * ReviewPane class (current implementation assumes Reservation refactor)
 */
public class ReviewPane extends JPanel implements PropertyChangeListener {
	 private ArrayList<Reservation> reservations;
	 private DefaultListModel model = new DefaultListModel();
	 private JList list;

	 private static final long serialVersionUID = 1L;

	 public ReviewPane(JPanel contentPane, Account account) {
	     setLayout(new BorderLayout(0, 0));

	     list = new JList(model);
	     //FIXME: ListSelectionListener errors
	     list.addListSelectionListener(new ListSelectionListener() {
	         public void rListValueChanged(ListSelectionEvent event) {
	             if (!event.getValueIsAdjusting()) {
	               // Get the selected reservation
	               Reservation selectedReservation = reservations.get(list.getSelectedIndex());
	               // Display the reservation
	               System.out.println(selectedReservation);
	             }
	         }
	     });
	     add(list, BorderLayout.CENTER);

	     JToolBar toolBar = new JToolBar();
	     add(toolBar, BorderLayout.NORTH);

	     JButton btnReturn = new JButton("Return");
	     toolBar.add(btnReturn);

	     JComboBox comboBox = new JComboBox();
	     toolBar.add(comboBox);

	     JButton btnReview = new JButton("Cancel Selected Reservation");
	     btnReview.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	             // FIXME: CANCELLATION PANE
	             ((CardLayout) contentPane.getLayout()).show(contentPane, "Cancel");
	         }
	     });
	     toolBar.add(btnReview);

	     reservations = new ArrayList<>();
	     String file = "src/Database/Reservation.txt";
	     try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	         String line;
	         String reservationId = "";
	         String accountId = "";
	         String flightNumber = "";
	         String dateOfBooking = "";
	         String dateOfDeparture = "";
	         String departureAirport = "";
	         String arrivalAirport = "";
	         double totalPricing = 0.0;
	         String cabinClass = "";
	         String passengerName = "";
	         while ((line = br.readLine()) != null) {
	             if (line.startsWith("Reservation ID:")) {
	               reservationId = line.substring("Reservation ID:".length()).trim();
	             } else if (line.startsWith("Account ID:")) {
	               accountId = line.substring("Account ID:".length()).trim();
	             } else if (line.startsWith("Flight Number:")) {
	               flightNumber = line.substring("Flight Number:".length()).trim();
	             } else if (line.startsWith("Date of Booking:")) {
	               dateOfBooking = line.substring("Date of Booking:".length()).trim();
	             } else if (line.startsWith("Date of Departure:")) {
	               dateOfDeparture = line.substring("Date of Departure:".length()).trim();
	             } else if (line.startsWith("Departure Airport:")) {
	               departureAirport = line.substring("Departure Airport:".length()).trim();
	             } else if (line.startsWith("Arrival Airport:")) {
	               arrivalAirport = line.substring("Arrival Airport:".length()).trim();
	             } else if (line.startsWith("Total Pricing:")) {
	               totalPricing = Double.parseDouble(line.substring("Total Pricing:".length()).trim());
	             } else if (line.startsWith("Cabin Class:")) {
	               cabinClass = line.substring("Cabin Class:".length()).trim();
	             } else if (line.startsWith("Passenger Name:")) {
	               passengerName = line;
	               
               }
           }
       } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}
}
              
