import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class to instantiate or update flights line-by-line from .txt file via BufferedReader & BufferedWriter.
 * @author Jevy Miranda
 */

public class FlightIO {
	private static final String FILE_PATH = "FlightsTest.txt";
	private static final int ECONOMY_COUNT_INDEX = 10;
	private static final int BUSINESS_COUNT_INDEX = 13;
	private static final int FIRST_CLASS_COUNT_INDEX = 16;
	private static final int LAST_INDEX = 17;
	
	/**
	 * Constructor.
	 */
	/*
	public FlightIO() {
		
	}
	*/
	
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
	 * Updates flight passenger count one new reservation booking.
	 * @see class ReservationPaymentPane.java
	 */
	public static void updatePassengerCount(Flight selectedFlight, int selectedPassengerAmount, String selectedCabin) {
		ArrayList<String> lines = new ArrayList<>();
		Iterator<String> iter;
		int passengerCountIndex = ECONOMY_COUNT_INDEX;
		int newPassengerCount = 0;
		
		
		
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
		    String line;
		    
		    while ((line = reader.readLine()) != null) {
		        String[] parts = line.split(", ");
		        StringBuilder str =  new StringBuilder();
		        // if flight on line is selected flight
		        if (Integer.parseInt(parts[0]) == selectedFlight.getID()) {
		        	switch (selectedCabin) {
						case "Economy":
							passengerCountIndex = ECONOMY_COUNT_INDEX;
							// calculate new passenger count
							newPassengerCount = selectedFlight.getEconomyPassengerCount() + selectedPassengerAmount;
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
		        	}
		        	// add flight with revised passengerCount
		        	for (int i = 0; i <= LAST_INDEX; i++) {
		        		if (i == passengerCountIndex) {
		        			// revise passengerCount
		        			str.append(newPassengerCount + ", ");
		        		} else if (i == LAST_INDEX) {
		        			// copy without delimiter
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
