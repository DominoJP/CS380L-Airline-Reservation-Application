import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Core logic for cancel reservation functionality.
 * Utilizes BufferedReader/BufferedWriter to scan and rewrite the txt file containing reservation information.
 * 
 * @author Joshua Planovsky
 * @version 5.0
 */
public class CancelReservation {
    private String reservationFilePath = "src/Database/Reservation.txt"; // Default reservation file path

    // Constructor to set the reservation file path
    public CancelReservation(String reservationFilePath) {
        this.reservationFilePath = reservationFilePath;
    }

 // Method to cancel a reservation by its ID
    public boolean cancelReservationAction(String reservationID) {
        List<String> reservations = new ArrayList<>();
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(this.reservationFilePath))) {
            String line;
            StringBuilder currentReservation = new StringBuilder();
            boolean reservationFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains("Reservation ID: " + reservationID)) {
                    found = true;
                    reservationFound = true;
                    // Skip the current reservation
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("--Reservation End--")) {
                            break;
                        }
                    }
                }

                currentReservation.append(line).append("\n");

                if (line.contains("--Reservation End--")) {
                    reservationFound = false;
                }

                if (!reservationFound) {
                    reservations.add(currentReservation.toString());
                    currentReservation = new StringBuilder();
                }
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
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return true; // Reservation was successfully canceled
        }

        return false; // Reservation was not found
    }

    // Other methods for the reservation logic
}