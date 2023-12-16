import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


/**
 * 
 * This class is responsible for displaying the details of a list of reservations. It uses a JPanel and a JList to display
 * the list of reservations and the details of the selected reservation. Additionally, it uses a DefaultListModel to hold the reservations
 * and a JList to display them. Passing the data to the JPanel allows for the display of the selected reservation which should coincide with
 * the users AccountID. 
 * 
 * The details of the displayed reservation include the reservation ID, account ID, date of departure, departure
 * airport, arrival airport, total pricing, and the names of the passengers.
 * 
 * 
 * The class uses the getPassengers() method of the Reservation class to get the names of the passengers.
 * Module Name: ReviewPane
 * @author Joshua Planovsky
 * @version 4.0 last updated: 11/21/2023
 *
 *
 */


public class ReviewPane extends JPanel implements PropertyChangeListener {
	   private ArrayList<Reservation> reservations;
	   private DefaultListModel model = new DefaultListModel();
	   private JList list;
	   private JPanel detailsPanel = new JPanel(new GridLayout(0, 2)); // Panel to hold the JLabel
	   private CancelReservation cancelReservation; // Instance of CancelReservation class
	   private JButton returnButton; // Return button
	   private JButton cancelButton;
	   private static final long serialVersionUID = 1L;
	   private Account reviewer;
	   private Reservation selectedReservation;// Added field to store the currently selected reservation

	   /**
	    * Constructor to initialize the ReviewPane.
	    *
	    * @param JPanel contentPane The content pane.
	    * @param Account account The account.
	    * @param String reservationsFilePath The file path for reservations.
	    */
	   public ReviewPane(JPanel contentPane, Account account, String reservationsFilePath, ReservationListPane reservationListPane) {
	       this.reservations = new ArrayList<>();
	       this.cancelReservation = new CancelReservation(reservationsFilePath, this.reservations);
	       this.reviewer = account;
	       this.selectedReservation = null;
	       reservationListPane.addPropertyChangeListener(this);

	       setLayout(new BorderLayout());
	       setPreferredSize(new Dimension(500, 300));

	       list = new JList(model);
	       list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	       list.setLayoutOrientation(JList.VERTICAL);
	       list.setVisibleRowCount(-1);
	       JScrollPane listScrollPane = new JScrollPane(list);
	       listScrollPane.setPreferredSize(new Dimension(250, 100));
	       listScrollPane.setAlignmentX(LEFT_ALIGNMENT);

	       add(listScrollPane, BorderLayout.WEST);
	       System.out.println("Model Size: " + model.size());
	       System.out.println("Reservations Size: " + reservations.size());

	       list.addListSelectionListener(new ListSelectionListener() {
	    	   @Override
	           public void valueChanged(ListSelectionEvent event) {
	              if (!event.getValueIsAdjusting()) {
	            	   Reservation selectedReservation = reservations.get(list.getSelectedIndex());
	                  System.out.println("Selected Reservation: " + selectedReservation);
	               }
	           }
	       });

	       //Initialize return button
	       JToolBar toolBar = new JToolBar();
	       add(toolBar, BorderLayout.NORTH);

	       JButton btnReturn = new JButton("Return");
	       btnReturn.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	               ((CardLayout) contentPane.getLayout()).show(contentPane, "REVIEW_LIST");
	           }
	       });
	       toolBar.add(btnReturn);
	       // Add PropertyChangeListener to listen for the selectedReservation property
	       reservationListPane.addPropertyChangeListener("selectedReservation", new PropertyChangeListener() {
	           @Override
	           public void propertyChange(PropertyChangeEvent evt) {
	                selectedReservation = (Reservation) evt.getNewValue();
	               System.out.println("Received PropertyChangeEvent with selected reservation: " + selectedReservation);
	               // Display the selected reservation
	               displayReservation(selectedReservation, reviewer);
	               add(detailsPanel, BorderLayout.CENTER);
	               revalidate();
	               repaint();
	           }
	       });
	       
	       //Initialize cancel reservation button.
	       JButton btnCancel = new JButton("Cancel Reservation");
	       btnCancel.addActionListener(new ActionListener() {
	           @Override
	           public void actionPerformed(ActionEvent e) {
	        	   System.out.println("Current Reservation: " + selectedReservation);
	        	   int reservationID = selectedReservation.getID();
	               boolean result = cancelReservation.cancelReservationAction(reservationID);
	               if (result) {
	                  // Successfully cancelled the reservation
	                  JOptionPane.showMessageDialog(ReviewPane.this, "Reservation cancelled successfully.");
	                  reservations.remove(selectedReservation); // remove reservation from the list
	                  model.removeElement(selectedReservation); // remove reservation from the model
	                  selectedReservation = null; // clear the currently selected reservation
	               } else {
	                  // Failed to cancel the reservation
	                  JOptionPane.showMessageDialog(ReviewPane.this, "Failed to cancel the reservation.", "Error", JOptionPane.ERROR_MESSAGE);
	               }
	           }
	       });
	       toolBar.add(btnCancel); // Add cancel button to the tool bar

	      
	   }

	   /**
	    * Method to display the details of a reservation.
	    * @param Reservation reservation The reservation to be displayed.
	    */
	   public void displayReservation(Reservation reservation, Account account) {
	       // Clear the details panel to ensure that it only displays the details of the current reservation
	       detailsPanel.removeAll();

	       // Log the reservation object to the console for debugging purposes
	       System.out.println("Displaying reservation: " + reservation);

	       // Add the reservation ID to the details panel
	       detailsPanel.add(new JLabel("Reservation ID:"));
	       detailsPanel.add(new JLabel("" + reservation.getID()));
	       System.out.println("Added reservation ID to detailsPanel");

	       // Add the account ID to the details panel
	       detailsPanel.add(new JLabel("Account ID:"));
	       detailsPanel.add(new JLabel("" + account.getAccountNumber()));

	       // Add the flight number to the details panel
	       detailsPanel.add(new JLabel("Flight Number:"));
	       detailsPanel.add(new JLabel("" + reservation.getFlight()));

	       // Add the date of booking to the details panel
	       detailsPanel.add(new JLabel("Date of Booking:"));
	       detailsPanel.add(new JLabel("" + reservation.getDateTimeAtBooking()));

	       // Add the cabin class to the details panel
	       detailsPanel.add(new JLabel("Cabin Class:"));
	       detailsPanel.add(new JLabel("" + reservation.getCabin()));

		   // Add the total pricing to the details panel
		   detailsPanel.add(new JLabel("Total Pricing:"));
		   detailsPanel.add(new JLabel("" + reservation.getTotalPrice()));

		   // Add the passenger names to the details panel
		   detailsPanel.add(new JLabel("Passenger Name:"));
		   // Retrieve the passenger names from the reservation object
		   ArrayList<String> passengers = reservation.getPassengers();
		   // Iterate over the passenger names and add each one to the details panel
		   for (String passengerName : passengers) {
		      detailsPanel.add(new JLabel(passengerName));
		      // Log the passenger name to the console for debugging purposes
		      System.out.println("Passenger name: " + passengerName);
		   }

	       revalidate(); // Refresh the panel
	       repaint(); // Repaint the panel
	   }
	   
	   /**
	    * Listens for PropertyChangeEvent.
	    */
	   @Override
	   public void propertyChange(PropertyChangeEvent evt) {
	       if ((evt.getPropertyName()).equals("accountNumber")) {
	           this.reviewer.setaccountNumber(((Integer) evt.getNewValue()));
	       	
	           AccountIO getter = new AccountIO();
	           getter.readAccounts();
	           
	           this.reviewer = getter.findAccount(reviewer.getAccountNumber());
	       }

	   }
	}
