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
	 * Updates flight passenger count on new reservation booking.
	 * @see class ReservationPaymentPane.java
	 * @param selectedFlight
	 * @param selectedPassengerAmount
	 * @param selectedCabin
	 */
	public static boolean updatePassengerCount(String filePath, Flight selectedFlight, int selectedPassengerAmount, String selectedCabin) {
		ArrayList<String> lines = new ArrayList<>();
		int passengerCountIndex;
		int newPassengerCount;
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(", ");
		        StringBuilder str =  new StringBuilder();
		        if (Integer.parseInt(parts[0]) == selectedFlight.getID()) { // if flight on current line is selected flight
		        	switch (selectedCabin) {
						case "Economy":
							passengerCountIndex = ECONOMY_COUNT_INDEX;
							newPassengerCount = selectedFlight.getEconomyPassengerCount() + selectedPassengerAmount; // calculate new passenger count
							selectedFlight.addEconomyPassengerCount(selectedPassengerAmount);
							break;
						case "Business":
							passengerCountIndex = BUSINESS_COUNT_INDEX;
							newPassengerCount = selectedFlight.getBusinessPassengerCount() + selectedPassengerAmount;
							selectedFlight.addBusinessPassengerCount(selectedPassengerAmount);
							break;
						case "First Class":
							passengerCountIndex = FIRST_CLASS_COUNT_INDEX;
							newPassengerCount = selectedFlight.getFirstClassPassengerCount() + selectedPassengerAmount;
							selectedFlight.addFirstClassPassengerCount(selectedPassengerAmount);
							break;
						default:
							return false;
		        	}
		        	str = rewriteLine(parts, str, passengerCountIndex, newPassengerCount);
		        	lines.add(str.toString()); // add re-built line
		        } else { // if not the Flight to update
		        	lines.add(line); // re-add line unchanged
		        }
		    }
		    reader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		rewrite(filePath, lines);
		return true;
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
	 * Writes
	 * @param parts String array of elements in a line from a delimiter.
	 * @param str Line to be re-built.
	 * @param passengerCountIndex Changes depending on cabin class.
	 * @param newPassengerCount Passenger count of selected flight added to selectedPassengerAmount;
	 */
	private static StringBuilder rewriteLine(String[] parts, StringBuilder str, int passengerCountIndex, int newPassengerCount) {
		for (int i = 0; i <= LAST_INDEX; i++) {
			if (i == passengerCountIndex) {
				str.append(newPassengerCount + ", "); // copy revised passengerCount
			} else if (i == LAST_INDEX) {
				str.append(parts[i]); // copy without delimiter
			} else {
				str.append(parts[i] + ", "); // copy in full
			}
		}
		return str;
	}
	
	/**
	 * Calculates new passenger count for selected flight's cabin class given the new selectedPassengerAmount.
	 * @param selectedFlight
	 * @param selectedPassengerAmount
	 * @param selectedCabin
	 * @return newPassengerCount
	 */
	private int calculateNewPassengerCount(Flight selectedFlight, int selectedPassengerAmount, String selectedCabin) {
		
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
