import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Class to instantiate or update flights line-by-line from .txt file via BufferedReader & BufferedWriter.
 * @author Jevy Miranda
 */

public class FlightIO {
	private static final String FILE_PATH = "src/Database/FlightsTest.txt";
	private static final int AIRPORT_DEPARTING_INDEX = 2;
	private static final int AIRPORT_ARRIVING_INDEX = 3;
	private static final int PASSENGER_COUNT_INDEX = 9;
	private static final int LAST_INDEX = 10;
	private TreeSet<String> airportsDeparting;
	private TreeSet<String> airportsArriving;
	
	/**
	 * Constructor.
	 */
	
	public FlightIO() {
		airportsDeparting = new TreeSet<>();
		airportsArriving = new TreeSet<>();
	}
	
	
	/**
	 * Returns a FlightSorting object to which instantiated flights are added.
	 * @return FlightSorting object
	 */
	public FlightSorting instantiateFlights() {
		FlightSorting sort = new FlightSorting();
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(", ");
		        // Instantiate flight with parameters corresponding to String[] indices generated from the current line
		        Flight instantiatedFlight = new Flight(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], 
		        		                        parts[6], parts[7], Integer.parseInt(parts[8]), Integer.parseInt(parts[9]),
		        		                        new BigDecimal(parts[10]));
		        airportsDeparting.add(parts[AIRPORT_DEPARTING_INDEX]);
		        airportsArriving.add(parts[AIRPORT_ARRIVING_INDEX]);
		        
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
	
	/**
	 * Updates flight passenger count one new reservation booking.
	 * @see class ReservationPaymentPane.java
	 */
	public static void updatePassengerCount(Flight selectedFlight, int selectedPassengerAmount) {
		ArrayList<String> lines = new ArrayList<>();
		Iterator<String> iter;
		
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
		    String line;
		    
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(", ");
		        StringBuilder str =  new StringBuilder();
		        // if flight on line is selected flight
		        if (Integer.parseInt(parts[0]) == selectedFlight.getID()) {
		        	// calculate new passengerCount
		        	int newPassengerCount = selectedFlight.getPassengerCount() + selectedPassengerAmount;
		        	// add flight with revised passengerCount
		        	for (int i = 0; i <= LAST_INDEX; i++) {
		        		if (i == PASSENGER_COUNT_INDEX) {
		        			// revise passengerCount
		        			str.append(newPassengerCount + ", ");
		        		} else if (i == LAST_INDEX){
		        			// comma throws NumberFormatException
		        			str.append(parts[i]);
		        		} else {
		        			// copy
		        			str.append(parts[i] + ", ");
		        		}
		        	}
		        	// add re-built line
		        	lines.add(str.toString());
		        } else { // if not the Flight to update
		        	// re-add line unchanged
		        	lines.add(line);
		        }
		    }
		    reader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
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
	
	/**
	 * Returns Flight object with corresponding @param ID.
	 */
	public static Flight findFlight(int flightID) {
		Flight foundFlight = new Flight(0, null, null, null, "2000-01-01", "12:00", "2000-01-01", "12:00", 0, 0, new BigDecimal("0.00"));
		
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(", ");
		        if (Integer.parseInt(parts[0]) == flightID) {
		        	// Instantiate flight with parameters corresponding to String[] indices generated from the current line
		        	foundFlight = new Flight(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5], 
		        		          	     parts[6], parts[7], Integer.parseInt(parts[8]), Integer.parseInt(parts[9]),
		        		          	     new BigDecimal(parts[10]));
		        }
		    }
		    reader.close();   
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return foundFlight;
	}
	
	public TreeSet<String> getAirportsDeparting() {
		return airportsDeparting;
	}
	
	public TreeSet<String> getAirportsArriving() {
		return airportsArriving;
	}
	
}
