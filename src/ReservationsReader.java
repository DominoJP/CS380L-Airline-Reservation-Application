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
	*/
	public ReservationsReader(Account account) {
		flightIDs = new ArrayList<String>();
		Iterator<Flight> iter;
		reservations = new ArrayList<Reservation>();
		
		//FIXME
		System.out.println("inst");
		
		try (BufferedReader reader = new BufferedReader(new FileReader("src/Database/Reservations.txt"))) {
		    String line;
		    
		    while ((line = reader.readLine()) != null) {
		    	System.out.println("read");
		    	String[] parts = line.split(", ");
		    	if (parts[0].equals(account.getAccountNumber())) {
		    		System.out.println(parts[1]);
		    		flightIDs.add(parts[1]);
		    		// get list of flight numbers needed, then, instantiate all flights, passing a reference to flight obj
		    		// with correct ids
		    	}
		    }
		    
		    FlightsTestReader flightsReader = new FlightsTestReader(flightIDs);
		    // (to return to main method)
		    sort = flightsReader.getFlightSorting();
		    // instantiate reservations linked w/ account using found matching flights
		    iter = flightsReader.getFoundFlights().iterator();
		    while (iter.hasNext()) {
		    	reservations.add(new Reservation(account, iter.next(), null));
		    	System.out.println("SSS");
		    }
		    account.setReservationHistory(reservations);
		    // System.out.println("SSSS" + account.getReservationHistory().getFirst().getFlight().getDateArrival());
		    // System.out.println("SSSS" + account.getReservationHistory().getLast().getFlight().getDateArrival());
		    
		    reader.close();
		    
		} catch (IOException e) {
		    e.printStackTrace();
		
		}
	}
	
	/**
	  Method returning the FlightSorting Object, allowing invocation of methods sortFlights() and getList().
	  @return : FlightSorting Object
	 */
	public FlightSorting getFlightSorting() {
		return sort;
	}
	
}
