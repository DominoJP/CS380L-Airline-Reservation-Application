import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Cancel reservation class
 * Allows canceling reservations and displays reservation data on a JPanel for WindowBuilder
 *
 * @author Planovsky Joshua
 * Version 2.2
 */
public class CancelReservation extends javax.swing.JFrame {
    private JTextField reservationIDField;
    private JButton cancelReservationButton;
    private String reservationFilePath;
    private ReservationCancellationPane cancellationPane; // Add a reference to ReservationCancellationPane

    /**
     * Constructor for CancelReservation
     *
     * @param reservationFilePath Path to the reservations data file
     */
    public CancelReservation(String reservationFilePath) {
        this.reservationFilePath = reservationFilePath;

        setTitle("Reservation System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        dataTextArea = new JTextArea();
        add(new JScrollPane(dataTextArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        reservationIDField = new JTextField(10);
        cancelReservationButton = new JButton("Cancel Reservation");

        // Add an action listener to the cancel button
        cancelReservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the reservation ID to cancel from the text field
                String reservationIDToCancel = reservationIDField.getText();

                // Call the cancelReservation method from the CancelReservation class
                if (cancelReservationAction(reservationIDToCancel)) {
                    // Update the text area with a success message
                    dataTextArea.setText("Reservation with ID " + reservationIDToCancel + " has been canceled.");
                } else {
                    // Update the text area with a failure message
                    dataTextArea.setText("Reservation with ID " + reservationIDToCancel + " was not found or could not be canceled.");
                }
            }
        });

        // Add components to the input panel
        inputPanel.add(new JLabel("Enter Reservation ID:"));
        inputPanel.add(reservationIDField);
        inputPanel.add(cancelReservationButton);
        add(inputPanel, BorderLayout.SOUTH);

        
        // Initialize ReservationCancellationPane and add it to the CancelReservation frame
        cancellationPane = new ReservationCancellationPane(inputPanel, this);
        add(cancellationPane, BorderLayout.NORTH);
    }

    /**
     * Load data from the file and display it in the text area
     */
    

    /**
     * Implement your reservation cancellation logic here
     *
     * @param reservationID Reservation ID to be canceled
     * @return true if the cancellation is successful; otherwise, return false
     */
    public boolean cancelReservationAction(String reservationID) {
        List<String> reservations = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(this.reservationFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(reservationID)) {
                    // Skip the line to "cancel" the reservation
                    found = true;
                    continue;
                }
                reservations.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (found) {
            // Write the updated reservation data back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.reservationFilePath))) {
                for (String reservation : reservations) {
                    writer.write(reservation);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return true; // Reservation was successfully canceled
        }

        return false; // Reservation was not found
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create and configure the reservation system application
            CancelReservation app = new CancelReservation("Reservation.txt");
            app.setVisible(true);
        });
    }
}