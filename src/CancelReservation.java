import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Core logic for cancel reservation functionality.
 * Utilizes BufferedReader/BufferedWriter to scan and rewrite the txt file containing reservation information.
 * 
 * @author Joshua Planovsky
 * @version 8.0, last updated: 11/21/2023
 */
public class CancelReservation {
	
	 /**
	  * The default reservation file path.
	  */
    private String reservationFilePath = "Database/Reservation.txt";

    /**
     * Constructor to set the reservation file path.
     * @param string reservationFilePath The path of the reservation file.
     */
    public CancelReservation(String reservationFilePath) {
        this.reservationFilePath = reservationFilePath;
    }

    /**
     * Method to cancel a reservation by its ID.
     * @param string reservationID The ID of the reservation to be canceled.
     * @return true if the reservation was successfully canceled, false if not found or an error occurred.
     */
    public boolean cancelReservationAction(String reservationID) {
        List<String> reservations = new ArrayList<>();
        boolean found = false;
        
        if (reservationID == null) {
            throw new IllegalArgumentException("Reservation ID cannot be null");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(this.reservationFilePath))) {
            String line;
            StringBuilder currentReservation = new StringBuilder();
            boolean reservationFound = false;

            // Read through the reservation file line by line
            while ((line = reader.readLine()) != null) {
                if (line.contains("Reservation ID: " + reservationID)) {
                    found = true;
                    reservationFound = true;
                    
                    // Skip the current reservation by reading until the end marker
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("--Reservation End--")) {
                            break;
                        }
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
                    currentReservation = new StringBuilder();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Return false in case of an error
        }

        if (found) {
            // Write the updated reservation data back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.reservationFilePath))) {
                for (String reservation : reservations) {
                    writer.write(reservation);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false; // Return false in case of an error
            }

            return true; // Reservation was successfully canceled
        }

        return false; // Reservation was not found
    }
}
