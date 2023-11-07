import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
Core logic for cancel reservation functionality.
Utilizes BufferedReader/BufferedWriter to scan and rewrite txt file containining reservation information
@author  Joshua Planovsky
@version 5.0
*/
public class CancelReservation {
    private String reservationFilePath = "Reservation.txt"; // Default reservation file path

    // Constructor to set the reservation file path
    public CancelReservation(String reservationFilePath) {
        this.reservationFilePath = reservationFilePath;
    }

    // Method to cancel a reservation by its ID
    public boolean cancelReservationAction(String reservationID) {
        List<String> reservations = new ArrayList<>(); // List to store reservation data
        boolean found = false; // Flag to indicate if the reservation was found

        try (BufferedReader reader = new BufferedReader(new FileReader(this.reservationFilePath))) {
            String line;
            StringBuilder currentReservation = new StringBuilder(); // StringBuilder to store the current reservation
            boolean reservationFound = false; // Flag to indicate if the current reservation is the one to cancel

            while ((line = reader.readLine()) != null) {
                // Check if the line contains the Reservation ID
                if (line.contains("Reservation ID:")) {
                    if (reservationFound) {
                        // Check if this is the reservation you want to cancel
                        if (currentReservation.toString().contains("Reservation ID: " + reservationID)) {
                            found = true;
                            continue; // Skip this reservation
                        }
                    }
                    reservationFound = false; // Reset the flag for a new reservation
                    currentReservation = new StringBuilder(); // Start a new current reservation
                }
                currentReservation.append(line).append("\n"); // Append the current line

                // Check if this is the reservation you want to cancel
                if (line.contains("Reservation ID: " + reservationID)) {
                    found = true;
                    reservationFound = true; // Mark the current reservation for cancellation
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Handle any IO exceptions
        }

        if (found) {
            // Write the updated reservation data back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.reservationFilePath))) {
                for (String reservation : reservations) {
                    writer.write(reservation); // Write each reservation back to the file
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false; // Handle any IO exceptions
            }

            return true; // Reservation was successfully canceled
        }

        return false; // Reservation was not found
    }

    // Other methods for the reservation logic
}

  