// import Database.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
  Class that uses BufferedReader to instantiate flights line-by-line from .txt file.
  Instantiates a FlightSorting Object to which instantiated flights are added.
  @author Jevy Miranda
  @version 1.0
*/

public class FlightsTestReader {
	private FlightSorting sort;
	private Flight instantiatedFlight;
	
	/**
	  Constructor.
	*/
	
	public FlightsTestReader() {
		
		try (BufferedReader reader = new BufferedReader(new FileReader("src/Database/FlightsTest.txt"))) {
		    String line;
		    
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(", ");
		        // Instantiate flight with parameters corresponding to String[] indices generated from the current line
		        instantiatedFlight = new Flight(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], 
		        		                        parts[6], parts[7], Integer.parseInt(parts[8]), 
		        		                        Double.parseDouble(parts[9]));
		        // System.out.println(instantiatedFlight.getdateDeparture());
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
	  Method getFlightSorting() returns the FlightSorting Object, 
	  allowing invocation of methods sortFlights() and getList().
	 */
	
	public FlightSorting getFlightSorting() {
		return sort;
	}
	
}
