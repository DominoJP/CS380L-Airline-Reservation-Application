import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for loading reservations from a file. It reads the file line by line, parses the data,
 * and creates a list of Reservation objects. It only includes reservations that belong to a specific customer.
 * 
 * The class uses a BufferedReader to read the file and processes each line based on its content. It reads the 
 * reservation details, flight details, and passenger data from the file and uses this information to create 
 * Flight and Reservation objects.
 *
 * Finally, the algorithm used in the method uses a sequential process to run through the file. 
 * As of the current update this class is non functional.
 * Module Name: LoadReservation
 * @author Joshua Planovsky 
 * @version 2.0 11/21/2023
 * 
 */
public class LoadReservation {

   // The Account object representing the customer whose reservations are to be loaded
   private Account customer;
   
   // The path to the file containing the reservation data
   private String reservationsFilePath;
   
   // The DatabaseHandler object used to read from the file
   private DatabaseHandler databaseHandler = new DatabaseHandler();

   /**
    * Constructor for the LoadReservation class. 
    * @param Account customer is the Account object representing the customer whose reservations are to be loaded
    * @param String reservationsFilePath is the path to the file containing the reservation data
    */
   public LoadReservation(Account customer, String reservationsFilePath) {
       this.customer = customer;
       this.reservationsFilePath = reservationsFilePath;
   }
   
   /**
    * Method to load reservations from the file.
    * @return a list of Reservation objects representing the customer's reservations
    * @throws IOException if an error occurs while reading the file
    */
   public List<Reservation> loadReservations() {
       // Create a new list of Reservation objects
       List<Reservation> reservations = new ArrayList<>();
       
       // Try to read the reservations from the file
       try {
           // Read the reservations from the file
           List<String> lines = databaseHandler.readFromFile(this.reservationsFilePath);
           Reservation reservation = null;
           
           // Process each line in the file
           for (String line : lines) {
               // If the line starts with "Reservation ID:", create a new Reservation object
               if (line.startsWith("Reservation ID:")) {
                  if (reservation != null && reservation.getID().equals(customer.getAccountNumber())) {
                      reservations.add(reservation);
                  }
                  reservation = new Reservation(0, customer, null, line, null, null, null);
                  reservation.setId(Integer.parseInt(line.split(":")[1].trim()));
               } 
               // If the line starts with "Account ID:", set the account ID of the current Reservation object
               else if (line.startsWith("Account ID:")) {
                  Account.getAccountNumber(line.split(":")[1].trim());
               } 
               // If the line starts with "Flight Number:", create a new Flight object and set it in the current Reservation object
               else if (line.startsWith("Flight Number:")) {
                  Flight flight = new Flight(0, line, line, line, line, line, line, line, line);
                  flight.gettype();
                  reservation.setFlight(flight);
               } 
               // If the line starts with "Date of Booking:", parse the booking date and set it in the current Reservation object
               else if (line.startsWith("Date of Booking:")) {
                  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                  LocalDateTime bookingDate = LocalDateTime.parse(line.split(":")[1].trim(), formatter);
                  reservation.getDateTimeAtBooking();
               } 
               // If the line starts with "Date of Departure:", parse the departure date and set it in the current Reservation object
               else if (line.startsWith("Date of Departure:")) {
                  LocalDate departureDate = LocalDate.parse(line.split(":")[1].trim());
                  reservation.getFlight().getdateDeparture();
               } 
               // If the line starts with "Departure Airport:", set the departure city in the current Reservation object
               else if (line.startsWith("Departure Airport:")) {
                  reservation.getFlight().getcityDeparture();
               } 
               // If the line starts with "Arrival Airport:", set the arrival city in the current Reservation object
               else if (line.startsWith("Arrival Airport:")) {
                  reservation.getFlight().getcityArrival();
               } 
               // If the line starts with "Total Pricing:", set the total price in the current Reservation object
               else if (line.startsWith("Total Pricing:")) {
                  reservation.setTotalPrice(new BigDecimal(line.split(":")[1].trim()));
               } 
               // If the line starts with "Cabin Class:", set the cabin class in the current Reservation object
               else if (line.startsWith("Cabin Class:")) {
                  reservation.setCabin(line.split(":")[1].trim());
               } 
               // If the line starts with "Passenger Name:", add a passenger to the current Reservation object
               else if (line.startsWith("Passenger Name:")) {
                  reservation.addPassenger(line.split(":")[1].trim());
               }
           }
           
           // If the current Reservation object is not null and belongs to the customer, add it to the list of reservations
           if (reservation != null && Account.getAccountNumber().equals(customer.getAccountNumber())) {
               reservations.add(reservation);
           }
       } 
       // If an error occurs while reading the file, print the stack trace
       catch (IOException e) {
          e.printStackTrace();

        }
        return reservations;
    }
}
