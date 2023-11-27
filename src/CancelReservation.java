import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Core logic for cancel reservation functionality.
 * Utilizes DatabaseHandler for file I/O operations.
 * 
 * @author Joshua Planovsky
 * @version 8.0, last updated: 11/27/2023
 */
public class CancelReservation {

    /**
     * The default reservation file path.
     */
    private String reservationFilePath = "src/Database/Reservation.txt";

    /**
     * Constructor to set the reservation file path.
     * @param reservationFilePath The path of the reservation file.
     */
    public CancelReservation(String reservationFilePath) {
        this.reservationFilePath = reservationFilePath;
    }

    /**
     * Method to cancel a reservation by its ID.
     * @param reservationID The ID of the reservation to be canceled.
     * @return true if the reservation was successfully canceled, false if not found or an error occurred.
     */
    public boolean cancelReservationAction(String reservationID) {
        if (reservationID == null) {
            throw new IllegalArgumentException("Reservation ID cannot be null");
        }

        List<String> reservations = new ArrayList<>();
        boolean found = false;

        try {
            List<String> lines = DatabaseHandler.readFile(this.reservationFilePath);
            StringBuilder currentReservation = new StringBuilder();
            boolean reservationFound = false;

            // Read through the reservation file line by line
            for (String line : lines) {
                if (line.contains(reservationID)) {
                    found = true;
                    reservationFound = true;

                    // Skip the current reservation by removing lines until the end marker
                    while (!(line = lines.remove(0)).contains("--Reservation End--")) {
                        // Skip the current reservation by removing lines until the end marker
                    }
                }

                // Build the current reservation content
                currentReservation.append(line).append("\n");

                if (line.contains("--Reservation End--")) {
                    reservationFound = false;
                }

                // If not inside a reservation block, add the current reservation to the list
                if (!reservationFound) {
                    reservations.add(currentReservation.toString());
                    currentReservation.setLength(0); // Clear StringBuilder for the next reservation
                }
            }
        } catch (IOException e) {
            handleIOException(e);
            return false;
        }

        if (found) {
            // Write the updated reservation data back to the file
            try {
                DatabaseHandler.writeFile(this.reservationFilePath, reservations);
            } catch (IOException e) {
                handleIOException(e);
                return false;
            }

            return true; // Reservation was successfully canceled
        }

        return false; // Reservation was not found
    }

    /**
     * Handles IOException by printing the stack trace.
     * @param e The IOException to be handled.
     */
    private void handleIOException(IOException e) {
        // Handle or log the exception as needed
        e.printStackTrace();
    }
}