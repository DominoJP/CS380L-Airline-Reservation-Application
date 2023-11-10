// import Database.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

/**
  Class that uses BufferedReader to instantiate flights line-by-line from .txt file.
  Instantiates a FlightSorting Object to which instantiated flights are added.
  @author Jevy Miranda
  @version 1.0
*/

//FIXME: DUE FOR REFACTORING

@Deprecated
public class FlightsTestReader {
	private FlightSorting sort;
	private Flight instantiatedFlight;
	private ArrayList<Flight> foundFlights;
	private final int PASSENGER_COUNT_INDEX = 9;
	private final int FINAL_INDEX = 10;
	
	public FlightsTestReader() {
		
		try (BufferedReader reader = new BufferedReader(new FileReader("src/Database/FlightsTest.txt"))) {
		    String line;
		    Iterator<String> iter;
		    foundFlights = new ArrayList<>();
		    
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(", ");
		        // Instantiate flight with parameters corresponding to String[] indices generated from the current line
		        instantiatedFlight = new Flight(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], 
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
	}
	
	/**
	  Constructor.
	*/
	public FlightsTestReader(Object[] reservationFlightIDs) {
		
		try (BufferedReader reader = new BufferedReader(new FileReader("src/Database/FlightsTest.txt"))) {
		    String line;
		    Iterator<String> iter;
		    foundFlights = new ArrayList<>();
		    
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(", ");
		        // Instantiate flight with parameters corresponding to String[] indices generated from the current line
		        instantiatedFlight = new Flight(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], 
		        		                        parts[6], parts[7], Integer.parseInt(parts[8]), Integer.parseInt(parts[9]),
		        		                        new BigDecimal(parts[10]));
		        
		        // Add flight to foundFlights for reservations associated with active account.
		        /*
		        iter = reservationFlightIDs.iterator();
		        while (iter.hasNext()) {
		        	if (instantiatedFlight.getID() == Integer.parseInt(iter.next())) {
		        		foundFlights.add(instantiatedFlight);
		        	}
 		        }
 		        */
		        for (Object id : reservationFlightIDs) {
		        	if (instantiatedFlight.getID() == Integer.parseInt((String) id)) {
		        		foundFlights.add(instantiatedFlight);
		        	}
		        }
		        
		    }
		    
		    reader.close();
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public FlightsTestReader(Flight selectedFlight, int selectedPassengerAmount) {
		ArrayList<String> lines = new ArrayList<>();
		Iterator<String> iter;
		try (BufferedReader reader = new BufferedReader(new FileReader("src/Database/FlightsTest.txt"))) {
		    String line;
		    
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(", ");
		        StringBuilder str =  new StringBuilder();
		        // if flight on line is selected flight
		        if (Integer.parseInt(parts[0]) == selectedFlight.getID()) {
		        	// calculate new passengerCount
		        	int newPassengerCount = selectedFlight.getPassengerCount() + selectedPassengerAmount;
		        	// add flight with revised passengerCount
		        	for (int i = 0; i <= FINAL_INDEX; i++) {
		        		if (i == PASSENGER_COUNT_INDEX) {
		        			// revise passengerCount
		        			str.append(newPassengerCount + ", ");
		        		} else {
		        			// copy
		        			str.append(parts[i] + ", ");
		        		}
		        	}
		        	// add re-built line
		        	lines.add(str.toString());
		        	
		        } else {
		        	// re-add line
		        	lines.add(line);
		        }
		        
		    }
		    
		    reader.close();
		    
		} catch (IOException e) {
		    e.printStackTrace();
		
		}
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Database/FlightsTest.txt"))) {
			// re-write lines into file
			iter = lines.iterator();
			while (iter.hasNext()) {
				writer.write(iter.next());
				writer.newLine();
			}
			
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
	// used by reservation IO, refactor
	public FlightsTestReader(int id) {
		try (BufferedReader reader = new BufferedReader(new FileReader("src/Database/FlightsTest.txt"))) {
		    String line;
		    
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(", ");
		        if (Integer.parseInt(parts[0]) == id) {
		        	// Instantiate flight with parameters corresponding to String[] indices generated from the current line
		        	instantiatedFlight = new Flight(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], 
		        		          	     parts[6], parts[7], Integer.parseInt(parts[8]), Integer.parseInt(parts[9]),
		        		          	     new BigDecimal(parts[10]));
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
	
	public Flight getFlight() {
		return instantiatedFlight;
	}
	
}
