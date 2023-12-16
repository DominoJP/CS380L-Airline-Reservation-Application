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
   private String reservationFilePath = "Database/Reservations.txt";
   private DatabaseHandler databaseHandler = new DatabaseHandler();
   private List<Reservation> reservations;

   /**
    * Constructor to set the reservation file path.
 * @param reservations 
    * @param string reservationFilePath The path of the reservation file.
    */
   public CancelReservation(String reservationFilePath, ArrayList<Reservation> reservations) {
	   this.reservations = reservations;
       this.reservationFilePath = reservationFilePath;
   }

   /**
    * Method to cancel a reservation by its ID.
    * @param string reservationID The ID of the reservation to be canceled.
    * @return true if the reservation was successfully canceled, false if not found or an error occurred.
    */
   public boolean cancelReservationAction(int reservationID) {
	    
	   if(reservationID < 0) { 
           throw new IllegalArgumentException("Invalid reservation ID");
       }

       boolean removed = false;

       for(Reservation reservation : this.reservations) {
           
           if(reservation.getID() == reservationID) {

               removed = this.reservations.remove(reservation); 
               break;

           }
       }

       if(removed) {

           DatabaseHandler handler = new DatabaseHandler();
           return handler.writeToFile(reservationFilePath, reservations);

       }

       return false;
   }
}
