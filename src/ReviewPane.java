import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	  private JPanel detailsPanel = new JPanel(new GridLayout(0, 2)); // Panel to hold the JLabels

	  private static final long serialVersionUID = 1L;

	  /**
	   * Constructor to initialize the ReviewPane.
	   *
	   * @param JPanel contentPane The content pane.
	   * @param Account account The account.
	   * @param List<> reservations The list of reservations.
	   */
	  public ReviewPane(JPanel contentPane, Account account, List<Reservation> reservations) {
	      this.reservations = new ArrayList<>(reservations);

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

	      list.addListSelectionListener(new ListSelectionListener() {
	          public void valueChanged(ListSelectionEvent event) {
	              if (!event.getValueIsAdjusting()) {
	                 Reservation selectedReservation = reservations.get(list.getSelectedIndex());
	                 displayReservation(selectedReservation);
	              }
	          }
	      });

	      detailsPanel = new JPanel(new GridLayout(0, 2)); // Panel to hold the JLabels
	      add(detailsPanel, BorderLayout.SOUTH);
	  }

	  /**
	    * Method to display the details of a reservation.
	    * @param Reservation reservation The reservation to be displayed.
	    */
	  public void displayReservation(Reservation reservation) {
	      detailsPanel.removeAll(); // Clear the panel

	      detailsPanel.add(new JLabel("Reservation ID:"));
	      detailsPanel.add(new JLabel(reservation.getID()));
	      detailsPanel.add(new JLabel("Account ID:"));
	      detailsPanel.add(new JLabel(reservation.getID()));
	      detailsPanel.add(new JLabel("Date of Departure:"));
	      detailsPanel.add(new JLabel(reservation.getFlight().getdateDeparture()));
	      detailsPanel.add(new JLabel("Departure Airport:"));
	      detailsPanel.add(new JLabel(reservation.getFlight().getcityDeparture()));
	      detailsPanel.add(new JLabel("Arrival Airport:"));
	      detailsPanel.add(new JLabel(reservation.getFlight().getcityArrival()));
	      detailsPanel.add(new JLabel("Total Pricing:"));
	      
	      for (String passengerName : reservation.getPassengers()) {
	          detailsPanel.add(new JLabel(""));
	          detailsPanel.add(new JLabel(passengerName));
	      }

	      revalidate(); // Refresh the panel
	      repaint(); // Repaint the panel
	  }
}

