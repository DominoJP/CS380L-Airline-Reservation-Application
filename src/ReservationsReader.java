import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
  Class that uses BufferedReader to instantiate reservations to attribute List<Reservation> in Account Object.
  @author Jevy Miranda
  @version 1.0
*/

public class ReservationsReader {
	private FlightSorting sort;
	private ArrayList<String> flightIDs;
	private ArrayList<Reservation> reservations;
	
	/**
	  Constructor.
	  @param : account
	*/
	public ReservationsReader(Account account) {
		flightIDs = new ArrayList<String>();
		Iterator<Flight> iter;
		reservations = new ArrayList<Reservation>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader("src/Database/Reservations.txt"))) {
		    String line;
		    
		    // Generate ArrayList of flight IDs matching reservations associated with account.
		    while ((line = reader.readLine()) != null) {
		    	String[] parts = line.split(", ");
		    	if (parts[0].equals(account.getAccountNumber())) {
		    		flightIDs.add(parts[1]);
		    	}
		    }
		    
		    // Pass ArrayList of flight IDs, compared against .txt to identify matching flights.
		    FlightsTestReader flightsReader = new FlightsTestReader(flightIDs);
		    sort = flightsReader.getFlightSorting();
		    // instantiate reservations linked w/ account using found flights
		    iter = flightsReader.getFoundFlights().iterator();
		    while (iter.hasNext()) {
		    	reservations.add(new Reservation(account, iter.next(), null));
		    }
		    account.setReservationHistory(reservations);
		    
		    reader.close();
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
}
