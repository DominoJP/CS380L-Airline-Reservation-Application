import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
  Class that uses BufferedReader to instantiate reservations to attribute List<Reservation> in Account Object.
  @author Jevy Miranda
  @version 1.0
*/

public class ReservationsReader {
	private Account account;
	private FlightSorting sort;
	private ArrayList<Integer> reservationIDs;
	private TreeSet<String> flightIDs;
	private ArrayList<BigDecimal> fares;
	private ArrayList<Reservation> reservations;
	
	/**
	  Constructor.
	  @param : account
	*/
	public ReservationsReader(Account account) {
		this.account = account;
	}
	
	/**
	 * Method to instantiate reservations associated with account to ArrayList<Reservation> attribute of Account Object.
	 */
	
	public void instantiateReservations() {
		reservationIDs = new ArrayList<>();
		flightIDs = new TreeSet<String>();
		fares = new ArrayList<>();
		Iterator<Flight> iter;
		// reservations = new ArrayList<Reservation>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader("src/Database/Reservations.txt"))) {
		    String line;
		    
		    // Generate ArrayList of flight IDs matching reservations associated with account.
		    while ((line = reader.readLine()) != null) {
		    	String[] parts = line.split(", ");
		    	if (Integer.parseInt(parts[1]) == (account.getAccountNumber())) {
		    		reservationIDs.add(Integer.parseInt(parts[0]));
		    		//assumes flights.txt is in ascending order of ids
		    		flightIDs.add(parts[2]);
		    		fares.add(new BigDecimal(parts[3]));
		    	}
		    }
		    
		    // Pass ArrayList of flight IDs, compared against .txt to identify matching flights.
		    FlightsTestReader flightsReader = new FlightsTestReader(flightIDs.toArray());
		    sort = flightsReader.getFlightSorting();
		    // instantiate reservations linked w/ account using found flights
		    iter = flightsReader.getFoundFlights().iterator();
		    int i = 0;
		    while (iter.hasNext()) {
		    	account.addReservationHistory(new Reservation(reservationIDs.get(i), account, iter.next(), null, fares.get(i), LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES)));
		    	i++;
		    }
		    
		    reader.close();
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public boolean writeReservation(Reservation reservation) {
		Iterator<Reservation> iter;
		iter = account.getReservationHistory().iterator();
		boolean validReservation = true;
		// Validate that reservation for selected flight does not already exist for this account.
		while (iter.hasNext()) {
			if (iter.next().getFlight().getID() == reservation.getFlight().getID()) {
			validReservation = false;
			return false;
			}
		}
		
		if (validReservation) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Database/Reservations.txt", true))) {
				writer.write("\n");
                writer.write(reservation.getID() + ", " + account.getAccountNumber() + ", " + reservation.getFlight().getID() + ", " + reservation.getTotalPrice() + ", " +
                			 reservation.getDateTimeAtBooking());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
		return true;
	}
	
}
