import java.io.BufferedReader;
import java.io.FileReader;
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
 *
 */
public class LoadReservation {

	
    private Account customer;
    private String reservationsFilePath;
    
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
        List<Reservation> reservations = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(reservationsFilePath))) {
            String line;
            Reservation reservation = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Reservation ID:")) {
                    if (reservation != null && reservation.getAccountID().equals(customer.getAccountNumber())) {
                        reservations.add(reservation);
                    }
                    reservation = new Reservation(customer, null);
                    reservation.getID(Integer.parseInt(line.split(":")[1].trim()));
                } else if (line.startsWith("Account ID:")) {
                    reservation.getAccountID(line.split(":")[1].trim());
                } else if (line.startsWith("Flight Number:")) {
                    Flight flight = new Flight();
                    flight.gettype();
                    reservation.setFlight(flight);
                } else if (line.startsWith("Date of Booking:")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                    LocalDateTime bookingDate = LocalDateTime.parse(line.split(":")[1].trim(), formatter);
                    reservation.getDateTimeAtBooking();
                } else if (line.startsWith("Date of Departure:")) {
                    LocalDate departureDate = LocalDate.parse(line.split(":")[1].trim());
                    reservation.getFlight().getdateDeparture();
                } else if (line.startsWith("Departure Airport:")) {
                    reservation.getFlight().getcityDeparture();
                } else if (line.startsWith("Arrival Airport:")) {
                    reservation.getFlight().getcityArrival();
                } else if (line.startsWith("Total Pricing:")) {
                    reservation.setTotalPrice(new BigDecimal(line.split(":")[1].trim()));
                } else if (line.startsWith("Cabin Class:")) {
                    reservation.setCabin(line.split(":")[1].trim());
                } else if (line.startsWith("Passenger Name:")) {
                    reservation.addPassenger(line.split(":")[1].trim());
                }
            }
            if (reservation != null && reservation.getAccountID().equals(customer.getAccountNumber())) {
                reservations.add(reservation);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservations;
    }
}
