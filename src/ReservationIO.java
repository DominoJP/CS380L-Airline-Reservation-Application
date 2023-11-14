import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
	private static final String FILE_PATH = "Database/Reservation.txt";
	
	/**
	 * Instantiate reservations associated with account to ArrayList<Reservation> attribute of Account Object.
	 * @param : active account
	 */
	public static void instantiateReservations(Account account) {
		boolean isAssociatedReservation = false;
		int reservationID = 0;
		int flightID = 0;
		String cabin = "";
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
		    			cabin = parts[1];
		    			break;
		    		case "Total Pricing":
		    			totalPricing = new BigDecimal(parts[1]);
		    			break;
		    		case "/t" + "Passenger Name":
		    			passengers.add(parts[1]);
		    			break;
		    		case "--Reservation End--":
		    			if (isAssociatedReservation) {
		    				Flight matchingFlight = FlightIO.findFlight(flightID);
		    				account.addReservationHistory(new Reservation(reservationID, account, matchingFlight, cabin, passengers, totalPricing, bookingDateTime));
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
	
	/**
	 * Writes the pending reservation to Reservation.txt
	 * @param active account
	 * @param pending reservation
	 * @return true if write successful
	 */
	public static void writeReservation(Account account, Reservation reservation) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
			writer.write("\n");
			// FIXME: System.out.println(String.format("%010d", Integer.valueOf("999999999999999"))); throws format exception error
			writer.write("Reservation ID: " + reservation.getID() + "\n");
			writer.write("Account ID: " + account.getAccountNumber() + "\n");
			writer.write("Flight Number: " + reservation.getFlight().getID() + "\n");
			writer.write("Date of Booking: " + reservation.getDateTimeAtBooking() + "\n");
			writer.write("Date of Departure: " + reservation.getFlight().getdateDeparture() + "\n");
			writer.write("Departure Airport: " + reservation.getFlight().getcityDeparture() + "\n");
			writer.write("Arrival Airport: " + reservation.getFlight().getcityArrival() + "\n");
			writer.write("Cabin Class: " + reservation.getCabin() + "\n");
			writer.write("Total Pricing: " + reservation.getTotalPrice() + "\n");
			writer.write("Booking Records: " + "\n");
			for (String passengerName : reservation.getPassengers()) {
				if (!passengerName.equals(""))
				writer.write("\t" + "Passenger Name: " + passengerName + "\n");
			}
            writer.write("--Reservation End--" + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Validates that reservation for selected flight does not already exist for this account.
	 * @param active account
	 * @param pending reservation
	 * @return true if is a unique reservation
	 */
	@Deprecated
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
