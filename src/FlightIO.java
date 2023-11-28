import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Design Documentation: "FlightIO."
 * Description: Utility class for reading from/writing to Flights.txt. Uses BufferedReader and BufferedWriter.
 * <p>
 * Functions: Method instantiateFlights() reads from .txt file at program execution and outputs a FlightSorting object.
 * Method updatePassengerCount() updates the passenger count in the .txt of the corresponding cabin class on 
 * successful reservation booking, taking selected flight, selected passenger amount, and selected cabin class as inputs.
 * <p>
 * Data Structures: ArrayList stores re-built lines to write back into .txt. 
 * Array takes elements of a line, separated by delimiter.
 * Algorithms: N/A.
 * @version 2.3, Last Modified: Nov 16, 2023
 * @author Jevy Miranda
 */
public final class FlightIO {
	private static final String FILE_PATH = "src/Database/Flights.txt";
	private static final int ECONOMY_COUNT_INDEX = 10;
	private static final int BUSINESS_COUNT_INDEX = 13;
	private static final int FIRST_CLASS_COUNT_INDEX = 16;
	private static final int LAST_INDEX = 17;
	
	/**
	 * Private Constructor.
	 */
	private FlightIO() {
		
	}
	
	/**
	 * Returns a FlightSorting object to which instantiated flights are added.
	 * @return FlightSorting object
	 */
	public static FlightSorting instantiateFlights() {
		FlightSorting sort = new FlightSorting();
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		    	Flight instantiatedFlight;
		    	if (line.length() > 0) {	
		    		String[] parts = line.split(", ");
			        // Instantiate flight with parameters corresponding to String[] indices generated from the current line
			        instantiatedFlight = new Flight(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], 
			        									   parts[4], parts[5], parts[6], parts[7], parts[8]);
			        
       	     		instantiatedFlight.setEconomy(Integer.parseInt(parts[9]), new BigDecimal(parts[11]));
       	     	   	instantiatedFlight.setBusiness(Integer.parseInt(parts[12]), new BigDecimal(parts[14]));
       	     	   	instantiatedFlight.setFirstClass(Integer.parseInt(parts[15]), new BigDecimal(parts[17]));
       	     	   	
       	     	   	instantiatedFlight.addEconomyPassengerCount(Integer.parseInt(parts[10]));
       	     	   	instantiatedFlight.addBusinessPassengerCount(Integer.parseInt(parts[13]));
       	     	   	instantiatedFlight.addFirstClassPassengerCount(Integer.parseInt(parts[16]));
		    	
			        //FlightSorting instantiation requires a flight as parameter
			        if (sort == null) {
			        	sort = new FlightSorting(instantiatedFlight);
			        } else {
		        	    sort.addFlight(instantiatedFlight);
			        }
		    	}
		    }
		    reader.close();  
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return sort;
	}
	
	/**
	 * Updates cabin passenger count in .txt upon reservation booking to reflect already-updated flight object.
	 * @see class ReservationPaymentPane.java
	 * @param filePath to read to and write from
	 * @param selectedFlight by user
	 * @param selectedCabin by user
	 */
	public static void rewritePassengerCount(String filePath, Flight selectedFlight, String selectedCabin) {
		ArrayList<String> lines = new ArrayList<>();
		int passengerCountIndex;
		int updatedPassengerCount;
		if (selectedCabin.equals("Economy")) {
			passengerCountIndex = ECONOMY_COUNT_INDEX;
			updatedPassengerCount = selectedFlight.getEconomyPassengerCount();
		} else if (selectedCabin.equals("Business")) {
			passengerCountIndex = BUSINESS_COUNT_INDEX;
			updatedPassengerCount = selectedFlight.getBusinessPassengerCount();
		} else if (selectedCabin.equals("First Class")) {
			passengerCountIndex = FIRST_CLASS_COUNT_INDEX;
			updatedPassengerCount = selectedFlight.getFirstClassPassengerCount();
		} else {
			return;
		}
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(", ");
		        StringBuilder str =  new StringBuilder();
		        lines.add(rewriteLine(lines, line, selectedFlight, passengerCountIndex, updatedPassengerCount));
		    }
		    reader.close();
		} catch (IOException e) {
		    handleIOException(e);
		}
		rewrite(filePath, lines);
	}
	
	/**
	 * Rewrites Flights.txt with re-built file.
	 * @param lines ArrayList<String> of lines that comprise the file
	 */
	private static void rewrite(String filePath, ArrayList<String> lines) {
		Iterator<String> iter;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
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
	 * Rewrites current line with updated passenger count.
	 * @param lines saved
	 * @param line current
	 * @param selectedFlight by user
	 * @param passengerCountIndex in .txt
	 * @param updatedPassengerCount of selectedFlight
	 * @return String of re-built line
	 */
	private static String rewriteLine(ArrayList<String> lines, String line, Flight selectedFlight, int passengerCountIndex, int updatedPassengerCount) {
		StringBuilder str = new StringBuilder();
		String[] parts  = line.split(", ");
		if (Integer.parseInt(parts[0]) == selectedFlight.getID()) {
			for (int i = 0; i <= LAST_INDEX; i++) {
				if (i == passengerCountIndex) {
					str.append(updatedPassengerCount + ", "); // copy revised passengerCount
				} else if (i == LAST_INDEX) {
					str.append(parts[i]); // copy without delimiter
				} else {
					str.append(parts[i] + ", "); // copy in full
				}
			}
		} else {
			return line;
		}
		return str.toString();
	}
	
	/**
     * Handles IOException by printing the stack trace.
     * @param e The IOException to be handled.
     */
	private static void handleIOException(IOException e) {
		e.printStackTrace();
	}
	
	/**
	 * Returns Flight object with corresponding @param ID.
	 * @param flightID
	 * @return Flight
	 */
	public static Flight findFlight(int flightID) {
		Flight foundFlight = new Flight(0, null, null, null, "2000-01-01", "12:00", "2000-01-01", "12:00", "UTC");
		
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		    	String[] parts = line.split(", ");
		    	if (line.length() > 0 && Integer.parseInt(parts[0]) == flightID) {
		        	// Instantiate flight with parameters corresponding to String[] indices generated from the current line
		        	foundFlight = new Flight(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], 
							   				 parts[4], parts[5], parts[6], parts[7], parts[8]);
		        	foundFlight.setEconomy(Integer.parseInt(parts[9]), new BigDecimal(parts[11]));
       	     	   	foundFlight.setBusiness(Integer.parseInt(parts[12]), new BigDecimal(parts[14]));
       	     	   	foundFlight.setFirstClass(Integer.parseInt(parts[15]), new BigDecimal(parts[17]));
       	     	   	
       	     	   	foundFlight.addEconomyPassengerCount(Integer.parseInt(parts[10]));
       	     	   	foundFlight.addBusinessPassengerCount(Integer.parseInt(parts[13]));
       	     	   	foundFlight.addFirstClassPassengerCount(Integer.parseInt(parts[16]));
		        }
		    }
		    reader.close();   
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return foundFlight;
	}
	
}
