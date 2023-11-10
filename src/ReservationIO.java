import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class with BufferedReader to instantiate reservations to attribute List<Reservation> of Account Object.
 * @author Jevy Miranda
 * @version 1.0
 */
public class ReservationIO {
	private static final String FILE_PATH = "src/Database/Reservation.txt";
	
	/**
	 * Instantiate reservations associated with account to ArrayList<Reservation> attribute of Account Object.
	 * @param : active account
	 */
	public static void instantiateReservations(Account account) {
		boolean isAssociatedReservation = false;
		int reservationID = 0;
		int flightID = 0;
		LocalDateTime bookingDateTime = LocalDateTime.parse("2000-01-01T12:00");
		BigDecimal totalPricing = new BigDecimal("0.00");
		ArrayList<String> passengers = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		    	if(line.length() == 0) { // Skip whitespace.
		    		continue;
		    	}
		    	
		    	// switch-case for flexibility in event of changes to format
		    	// once format is finalized, can simply read line-by-line
		    	String[] parts = line.split(": ");
		    	switch (parts[0]) {
		    		case "Reservation ID":
		    			reservationID = Integer.parseInt(parts[1]);
		    			break;
		    		case "Account ID":
		    			if (Integer.parseInt(parts[1]) == account.getAccountNumber()) {
		    				isAssociatedReservation = true;
		    			}
		    			break;
		    		case "Flight Number":
		    			flightID = Integer.parseInt(parts[1]);
		    			break;
		    		case "Date of Booking":
		    			bookingDateTime = LocalDateTime.parse(parts[1]);
		    			break;
		    		case "Cabin Class":
		    			//
		    			break;
		    		case "Total Pricing":
		    			totalPricing = new BigDecimal(parts[1]);
		    			break;
		    		case "/t" + "Name":
		    			passengers.add(parts[1]);
		    			break;
		    		case "--Reservation End--":
		    			if (isAssociatedReservation) {
		    				Flight matchingFlight = FlightIO.findFlight(flightID);
		    				account.addReservationHistory(new Reservation(reservationID, account, matchingFlight, passengers, totalPricing, bookingDateTime));
		    				isAssociatedReservation = false;
		    			}
		    			break;
	    			default:
		    	}
		    	
		    }
		    reader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	

	public static void writeReservation(Account account, Reservation reservation) {
		if (isUniqueReservation(account, reservation)) {
			
		}
	}
	
	/**
	 * // Validates that reservation for selected flight does not already exist for this account.
	 * @param active account
	 * @param pending reservation
	 * @return true if is a unique reservation
	 */
	private static boolean isUniqueReservation(Account account, Reservation reservation) {
		Iterator<Reservation> iter;
		iter = account.getReservationHistory().iterator();
		while (iter.hasNext()) {
			if (iter.next().getFlight().getID() == reservation.getFlight().getID()) {
				return false;
			}
		}
		return true;
	}
 	
}
