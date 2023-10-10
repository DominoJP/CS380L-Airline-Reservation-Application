/**
 * 
 * @author 
 * @version
 */


import java.util.ArrayList;
import java.util.List;

public class FlightSorting{
	
	private int totalFlightAvailable;
	private List<Flight> flights;
	
	
	public FlightSorting() {
		setFlights(new ArrayList<>());
	}


	public int getTotalFlightAvailable() {
		return totalFlightAvailable;
	}


	public void setTotalFlightAvailable(int totalFlightAvailable) {
		this.totalFlightAvailable = totalFlightAvailable;
	}


	public List<Flight> getFlights() {
		return flights;
	}


	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public void addFlight(Flight flight) {
		flights.add(flight);
		totalFlightAvailable++;
	}
	
	public void sortFlights() {
		//Implement sorting 
		
		
		
		
	}
	
	public String[] displayAvaiableSeating(Flight f) {
		String[] passengers = new String[f.gettotalpassengercapacity()];
		
		for(int i = 0; i < f.gettotalpassengercapacity(); i++) {
			passengers[i] = f.getPassenger(i);
		}
		
		
		return passengers;
	}
}

