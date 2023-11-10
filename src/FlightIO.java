import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class to instantiate or update flights line-by-line from .txt file via BufferedReader & BufferedWriter.
 * @author Jevy Miranda
 */

public class FlightIO {
	private static final String FILE_PATH = "src/Database/FlightsTest.txt";
	
	/**
	 * Constructor.
	 */
	public FlightIO() {
		
	}
	
	/**
	 * Instantiates a FlightSorting Object to which instantiated flights are added.
	 * @return FlightSorting Object 
	 */
	public static FlightSorting instantiateFlights() {
		FlightSorting sort = new FlightSorting();
		try (BufferedReader reader = new BufferedReader(new FileReader("src/Database/FlightsTest.txt"))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(", ");
		        // Instantiate flight with parameters corresponding to String[] indices generated from the current line
		        Flight instantiatedFlight = new Flight(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], 
		        		                        parts[6], parts[7], Integer.parseInt(parts[8]), Integer.parseInt(parts[9]),
		        		                        new BigDecimal(parts[10]));
		        
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
		return sort;
	}
	
}
