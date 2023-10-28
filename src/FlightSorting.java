/**
 * This class represents a flight sorting system for managing flights and their information. 
 * It allows to add flights, sort them, and display their available seating 
 * information
 * @author 
 * @versio 1.0
 */
import java.util.List;
import java.util.ArrayList;

public class FlightSorting{
	
	private int totalFlightAvailable;
	private List<Flight> flights;

	private Airport root1;
	private AirportFlights root2;
	
	public FlightSorting() {
		root1 = null;
		flights = null;
	}
	
	public FlightSorting(Flight first) {
		root1 = new Airport(first);
	}

	/**
	 * Get the total number available flights
	 * @return
	 */
	public int getTotalFlightAvailable() {
		return totalFlightAvailable;
	}

	/**
	 * Sets the total number of available flights. 
	 * @param totalFlightAvailable
	 */
	public void setTotalFlightAvailable(int totalFlightAvailable) {
		this.totalFlightAvailable = totalFlightAvailable;
	}

	/**
	 * Gets the list of flights managed by this flight sorting instance. 
	 * @return returns the list of flights 
	 */
	public List<Flight> getFlights() {
		return flights;
	}

	/**
	 * Sets the list of flights. 
	 * @param flights
	 */
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	/**
	 * Adds a flight to the list of managed flights. 
	 * @param flight
	 */
	public void addFlight(Flight flight) {
		if(root1 == null) {
			root1 = new Airport(flight);
			return;
		}
		
		root1.addFlight(flight);
	}
	
	/**
	 * Sorts the list of flights 
	 */
	public void sortFlights(String origin, String destination, String date) {

		 Airport curr = root1;
		 curr = this.search(origin);
		 AirportFlights root2 = curr.search(destination, date);
		 this.setFlights(root2.getFlights());
		 this.setTotalFlightAvailable(root2.getFlights().size());


		 Airport curr1 = search(origin);
		 root2 = findFlights(origin, destination, date);
		 flights = root2.getFlights();
		 totalFlightAvailable = flights.size();
	}
	
	/**
	 * Displays the available seating information for a specific flight. 
	 * @param f ; the flight object displays the seating information
	 * @return : returns an array of strings representing passenger names for available seats.
	 */
	public String[] displayAvaiableSeating(Flight f) {
		String[] passengers = new String[f.gettotalpassengercapacity()];
		
		for(int i = 0; i < f.gettotalpassengercapacity(); i++) {
			passengers[i] = f.getPassenger(i);
		}
		
		
		return passengers;
	}
	
	public Flight findFlight(String time) {
		flights = root2.getFlights();
		
		//System.out.println(flights.size());
		
		for(int i = 0; i < flights.size(); i++) {
			if(flights.get(i).gettimeDeparture() == time)
				return flights.get(i);
		}
		
		return null;
	}
	
	public Airport search(String origin) {
		Airport curr = root1;
		//System.out.println(curr.getOrigin());
		
		while(curr.getOrigin() != origin) {
			
			if(origin.compareTo(curr.getOrigin()) < 0)
				curr = curr.getChild1();
			else
				curr = curr.getChild2();
		}
		
		return curr;
	}
	
	public AirportFlights findFlights(String origin, String destination, String date) {
		return this.search(origin).search(destination, date);
	}
	
	public String[] getList(String origin, String destination, String date) {
		AirportFlights curr = findFlights(origin, destination, date);
		
		ArrayList<Flight> flights = curr.getFlights();
		String[] list = new String[(flights.size()-1)];
		
		//System.out.println(list.length);
		
		for(int i = 0; i < list.length; i++) {
			list[i] = flights.get(i).getdateDeparture() + " - " + flights.get(i).getDateArrival();
		}
		
		return list;
	}
}

