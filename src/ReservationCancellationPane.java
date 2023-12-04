import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EtchedBorder;

/**
Swing JPanel in which initial CancelReservation object is instantiated.
Instantiates and adds elements to JPanel including data text fields and functional button
@author  Joshua Planovsky
@version 4.0
*/

public class ReservationCancellationPane extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextArea dataTextArea;             // Text area for displaying reservation data
    private JTextField reservationIDField;      // Text field for entering reservation ID
    private JButton cancelReservationButton;    // Button for canceling a reservation

    public ReservationCancellationPane(CancelReservation cancelReservation, JPanel contentPane) {
        setLayout(new BorderLayout());  // Set the layout of this panel to BorderLayout

        dataTextArea = new JTextArea(2,5);
        dataTextArea.setTabSize(5);
        dataTextArea.setEditable(false);

        // Create a panel for the label "Enter Reservation ID:"
        JPanel labelPanel = new JPanel();

        // Add UI components to this panel
        JScrollPane scrollPane = new JScrollPane(dataTextArea);
        scrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);  // Add a scrollable text area in the center
        add(labelPanel, BorderLayout.NORTH);
        
        JButton btnReturn = new JButton("Return");
        btnReturn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		((CardLayout) contentPane.getLayout()).show(contentPane, "MENU");
        	}
        });
        labelPanel.add(btnReturn);
        JLabel lblReservationId = new JLabel("ID:");
        labelPanel.add(lblReservationId);
        lblReservationId.setHorizontalAlignment(SwingConstants.LEFT);
        reservationIDField = new JTextField(10);
        labelPanel.add(reservationIDField);
        cancelReservationButton = new JButton("Cancel Reservation");  // Create a button
        labelPanel.add(cancelReservationButton);

        // Add an action listener for the cancel button
        cancelReservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the reservation ID to cancel from the text field
                String reservationIDToCancel = reservationIDField.getText();

                // Call the cancelReservation method from the CancelReservation class
                if (cancelReservation.cancelReservationAction(reservationIDToCancel)) {
                    // Update the text area with a success message
                    dataTextArea.setText("Reservation with ID " + reservationIDToCancel + " has been canceled.");
                } else {
                    // Update the text area with a failure message
                    dataTextArea.setText("Reservation with ID " + reservationIDToCancel + " was not found or could not be canceled.");
                }
            }
        });
    }
}
	


