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
	public static void rewritePassengerCount(String filePath, Flight selectedFlight, String selectedCabin) throws IOException {
		if (invalidCabin(selectedCabin)) { // if cabin does not exist
			return;
		}
		ArrayList<String> lines = new ArrayList<>();
		int passengerCountIndex = getPassengerCountIndex(selectedCabin);
		int updatedPassengerCount = getUpdatedPassengerCount(selectedFlight, selectedCabin);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line;
	    while ((line = reader.readLine()) != null) { // read through the file line-by-line
			String[] parts  = line.split(", ");
			StringBuilder str = new StringBuilder();
			if (Integer.parseInt(parts[0]) == selectedFlight.getID()) { // if line is flight selected for booking
				for (int i = 0; i < parts.length; i++) { // iterate through the parts of the line, determined by delimiter
					str.append(rewritePart(parts, i, passengerCountIndex, updatedPassengerCount)); // rewrite for updatedPassengerCount
				}
				lines.add(str.toString()); // add updated flight
			} else { // if line is not flight selected for booking
				lines.add(line); // re-add unchanged flight
			}
	    }
	    reader.close();
	    rewriteFile(filePath, lines);
	}
	
	/**
	 * Checks whether the selected cabin is available from the airline.
	 * @param selectedCabin
	 * @return true if cabin not an available option
	 */
	private static boolean invalidCabin(String selectedCabin) {
		if (selectedCabin.equals("Economy") || selectedCabin.equals("Business") || selectedCabin.equals("First Class")) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Retrieves the corresponding cabin passenger count index for Flights.txt.
	 * @param selectedCabin by the user
	 * @return index of passenger count for the corresponding cabin
	 */
	private static int getPassengerCountIndex(String selectedCabin) {
		if (selectedCabin.equals("Business")) {
			return BUSINESS_COUNT_INDEX;
		} else if (selectedCabin.equals("First Class")) {
			return FIRST_CLASS_COUNT_INDEX;
		} else {
			return ECONOMY_COUNT_INDEX;
		}
	}
	
	/**
	 * Updates passenger count to reflect the already-updated flight object.
	 * @param selectedFlight by the user
	 * @param selectedCabin by the user
	 * @return updated passenger count from flight object
	 */
	private static int getUpdatedPassengerCount(Flight selectedFlight, String selectedCabin) {
		if (selectedCabin.equals("Business")) {
			return selectedFlight.getBusinessPassengerCount();
		} else if (selectedCabin.equals("First Class")) {
			return selectedFlight.getFirstClassPassengerCount();
		} else {
			return selectedFlight.getEconomyPassengerCount();
		}
	}
	
	/**
	 * Rewrites Flights.txt with re-built file.
	 * @param lines ArrayList<String> of lines that comprise the file
	 */
	private static void rewriteFile(String filePath, ArrayList<String> lines) {
		// Iterator<String> iter;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			// re-write lines into file
			/*
			iter = lines.iterator();
			while (iter.hasNext()) {
				writer.write(iter.next());
				writer.newLine();
			}
			*/
            for (int i = 0; i < lines.size(); i++) {
            	if (i == (lines.size() - 1)) {
            		writer.write(lines.get(i));
            	} else {
            		writer.write(lines.get(i));
            		writer.newLine();
            	}
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private static String rewritePart(String[] parts, int i, int passengerCountIndex, int updatedPassengerCount) {
		if (i == passengerCountIndex) {
			return updatedPassengerCount + ", "; // insert updated passengerCount, include delimiter
		} else if (i == (parts.length - 1)) {
			return parts[i]; // copy without delimiter
		} else {
			return parts[i] + ", "; // copy with delimiter
		}
	}
	
	/**
	 * Rewrites current line with updated passenger count.
	 * @param line current
	 * @param selectedFlight by user
	 * @param passengerCountIndex in .txt
	 * @param updatedPassengerCount of selected flight
	 * @return String of re-built line
	 */
	/*
	private static String rewriteLine(String line, Flight selectedFlight, int passengerCountIndex, int updatedPassengerCount) {
		StringBuilder str = new StringBuilder();
		String[] parts  = line.split(", ");
		int i = 0;
		if (Integer.parseInt(parts[0]) == selectedFlight.getID()) { // flight selected for booking
			for (String part : parts) {
				if (i == passengerCountIndex) {
					str.append(updatedPassengerCount); // insert updated passengerCount
					str.append(", "); // include delimiter
				} else if (i == (parts.length - 1)) {
					str.append(part); // copy without delimiter
				} else {
					str.append(part); // copy with delimiter
					str.append(", ");
				}
				i++;
			}
		} else { // not flight selected for booking
			return line; // leave line unchanged
		}
		return str.toString();
	}
	*/
	
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
