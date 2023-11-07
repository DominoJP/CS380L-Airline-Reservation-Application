// import Database.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
  Class that uses BufferedReader to instantiate flights line-by-line from .txt file.
  Instantiates a FlightSorting Object to which instantiated flights are added.
  @author Jevy Miranda
  @version 1.0
*/

public class FlightsTestReader {
	private FlightSorting sort;
	private Flight instantiatedFlight;
	private ArrayList<Flight> foundFlights;
	
	public FlightsTestReader() {
		
		try (BufferedReader reader = new BufferedReader(new FileReader("src/Database/FlightsTest.txt"))) {
		    String line;
		    Iterator<String> iter;
		    foundFlights = new ArrayList<>();
		    
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(", ");
		        // Instantiate flight with parameters corresponding to String[] indices generated from the current line
		        instantiatedFlight = new Flight(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], 
		        		                        parts[6], parts[7], Integer.parseInt(parts[8]), 
		        		                        Double.parseDouble(parts[9]));
		        
		        //FlightSorting instantiation requires a flight as parameter
		        if (sort == null) {
		        	sort = new FlightSorting(instantiatedFlight);
		        } else {
	        	    sort.addFlight(instantiatedFlight);
		        }
		    }
		    
		    reader.close();
		    
		} catch (IOException e) {
		    e.printStackTrace();
		
		}
	}
	
	/**
	  Constructor.
	*/
	public FlightsTestReader(ArrayList<String> reservationFlightIDs) {
		
		try (BufferedReader reader = new BufferedReader(new FileReader("src/Database/FlightsTest.txt"))) {
		    String line;
		    Iterator<String> iter;
		    foundFlights = new ArrayList<>();
		    
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(", ");
		        // Instantiate flight with parameters corresponding to String[] indices generated from the current line
		        instantiatedFlight = new Flight(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], 
		        		                        parts[6], parts[7], Integer.parseInt(parts[8]), 
		        		                        Double.parseDouble(parts[9]));
		        
		        // Add flight to foundFlights for reservations associated with active account.
		        iter = reservationFlightIDs.iterator();
		        while (iter.hasNext()) {
		        	if (instantiatedFlight.getID().equals(iter.next())) {
		        		foundFlights.add(instantiatedFlight);
		        	}
 		        }
		        
		    }
		    
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
	
	/**
	  Method returning ArrayList of flights with IDs corresponding to reservations of active account.
	  @return : ArrayList of Flight Objects.
	 */
	public ArrayList<Flight> getFoundFlights() {
		return foundFlights;
	}
	
}
