import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Core logic for cancel reservation functionality.
 * Utilizes DatabaseHandler to read and write the txt file containing reservation information.
 * 
 * @author Joshua Planovsky
 * @version 8.0, last updated: 11/21/2023
 */
public class CancelReservation {

   /**
    * The default reservation file path.
    */
   private String reservationFilePath = "src/Database/Reservation.txt";
   private DatabaseHandler databaseHandler = new DatabaseHandler();

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

       try {
           // Read through the reservation file line by line
           List<String> lines = databaseHandler.readFromFile(this.reservationFilePath);
           StringBuilder currentReservation = new StringBuilder();
           boolean reservationFound = false;

           for (String line : lines) {
               if (line.contains("Reservation ID: " + reservationID)) {
                  found = true;
                  reservationFound = true;

                  // Skip the current reservation by reading until the end marker
                  for (int i = lines.indexOf(line); i < lines.size(); i++) {
                      line = lines.get(i);
                      if (line.contains("--Reservation End--")) {
                          break;
                      }
                  }
               }

               // If not inside a reservation block, add the current reservation to the list
               if (!reservationFound) {
                  // Build the current reservation content
                  currentReservation.append(line).append("\n");
               }

               if (line.contains("--Reservation End--")) {
                  reservationFound = false;
                  if (!found) {
                      reservations.add(currentReservation.toString());
                  }
                  currentReservation = new StringBuilder();
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
           return false; // Return false in case of an error
       }

       if (found) {
           // Write the updated reservation data back to the file
           return databaseHandler.writeToFile(this.reservationFilePath, reservations);
       }

       return false; // Reservation was not found
   }
}
