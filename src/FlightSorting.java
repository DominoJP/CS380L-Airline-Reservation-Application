/**
 * This class represents a flight sorting system for managing flights and their information. 
 * It allows to add flights, sort them, and display their available seating 
 * information
 * @author 
 * @versio 1.0
 */
import java.util.List;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class FlightSorting{
	
	private int totalFlightsAvailable;
	private ArrayList<Flight> flights;

	private Airport root1;
	/*
	private Airport root2;
	*/
	private AirportFlights destination;
	
	public FlightSorting() {
		root1 = null;
		flights = null;
	}
	
	/**
	 * A constructor that initializes the list with @param first, an instance of the Flight class
	 */
	
	public FlightSorting(Flight first) {
		root1 = new Airport(first);
	}
	
	/**
	 * The initialize method takes @param list, and ArrayList<Flight>, that will be used to create a binary tree
	 * when there is already a stored list of flights that needs to be added back into the sorting system
	 */
	
	public void initialize(ArrayList<Flight> list) {
		for(int i = 0; i < list.size(); i++) {
			this.addFlight(list.get(i));
		}
	}

	/**
	 * Adds @param flight, an instance of the Flight class, to the list of sorted fliights
	 */
	public void addFlight(Flight flight) {
		/*
		 * This area of code is to allow FlightSorting to also sort flights classified as "Round-trop", or "Two-Way" depending
		 * on what we decide to call these flights when this gets fully implemented
		 * Currently not in use
		if(flight.gettype().equals("One-way")) {	
			if(root1 == null) {
				root1 = new Airport(flight);
				return;
			}
			
			root1.addFlight(flight);
		}else if(flight.gettype().equals("Two-way")) {
			if(root2 == null) {
				root2 = new Airport(flight);
				return;
			}
			
			
			root2.addFlight(flight);
		}
		*/
		
		if(root1 == null) {
			root1 = new Airport(flight);
			return;
		}
		
		root1.addFlight(flight);
	}
	
	/**
	 * Sorts the list of flights 
	 */
	/*
	public void sortFlights(String type, String origin, String arrival, String date, String arrivalTime) {

		 this.destination = findFlights(type, origin, arrival, date);
		 this.flights = destination.getFlights();
		 this.totalFlightAvailable = flights.size();
	}
	*/
	
	public void sortFlights(String origin, String arrival, String date) {
		this.destination = findFlights(origin, arrival, date);
		this.flights = destination.getFlights();
		this.totalFlightsAvailable = flights.size();
	}
	
	/**
	 * After the FlightSorting class has found a list of flights, the findFlight method can be used to find a specific flight that the user
	 * wants to view. The flight is found by comparing the times of each one on the list with an instance of LocalTime made 
	 */
	
	public Flight findFlight(String time) {
		flights = destination.getFlights();
		LocalTime find = LocalTime.parse(time);
		
		for(int i = 0; i < flights.size(); i++) {
			if(flights.get(i).gettimeDeparture().equals(find))
				return flights.get(i);
		}
		
		return null;
	}
	
	/**
	 * Searches through either tree created with root1 or root2 depending on @param type and @return an Airport given the @param origin
	 * Currently not in use
	/*
	public Airport search(String type, String origin) {
		Airport curr = null;
		if(type.equals("One-way")) {
			curr = this.searchOneWay(origin);
		}else if(type.equals("Two-way")) {
			curr = this.searchTwoWay(origin);
		}
		
		return curr;
	}
	*/
	
	/**
	 * The search method goes through the tree that contains all Aiports and uses the @param origin, a string, to find a specific Airprot and the method then returns
	 * that Airport.
	 */
	
	public Airport search(String origin) {
		Airport curr = root1;
		
		while(curr.getOrigin().compareTo(origin) != 0) {
			
			if(origin.compareTo(curr.getOrigin()) < 0)
				curr = curr.getChild1();
			else
				curr = curr.getChild2();
		}
		
		return curr;
	}
	
	/**
	 * findFlights utilizes the search method of the AirportFlights class by giving it @param type and @param origin to find
	 * an Airport being searched for by the user, then it uses the search method of the found Airport to find an instance of AirportFlights given
	 * @param destination and @param date
	 */
	
	public AirportFlights findFlights(String origin, String destination, String date) {
		return this.search(origin).search(destination, date);
		
	}

	/**
	 * Sets the list of flights. 
	 * @param flights
	 */
	public void setFlights(ArrayList<Flight> flightList) {
		this.flights = flightList;
	}

	/**
	 * Sets the total number of available flights with @param totalFlights, an integer
	 */
	public void setTotalFlightAvailable(int totalFlights) {
		this.totalFlightsAvailable = totalFlights;
	}
	
	/**
	 * The getTotalFlightAvailable method returns and integer value that represents the total number of flights
	 * that are available when a list has been found
	 */
	public int getTotalFlightAvailable() {
		return totalFlightsAvailable;
	}
	
	/**
	 * Gets the list of flights managed by this flight sorting instance. 
	 * @return returns the list of flights 
	 */
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	/**
	 * The searchtTwoWay method is used to search through the tree that contains all of the Round-trip flights for a specific instances of an Airport using the
	 * @param origin, a string, to compare the locations of each Airport.
	 * Currently not in use
	 */
	
	/*
	public Airport searchTwoWay(String origin) {
		Airport curr = root2;
		
		while(curr.getOrigin().compareTo(origin) != 0) {
			
			if(origin.compareTo(curr.getOrigin()) < 0)
				curr = curr.getChild1();
			else
				curr = curr.getChild2();
		}
		
		return curr;
	}
	*/

	
	/**
	 * In the case that a list of Round-trip flights is being searched for, the findArrivalFlights method will sort the list of Flights found
	 * using the @param arrival to sort the list by which flights will return at a specific date@param arrival
	 * Currently not in use
	 */
	
	/*
	public void findArrivalFlights(LocalDate arrival) {
		
		ArrayList<Flight> list = new ArrayList<Flight>();
		for(int i = 0; i < flights.size(); i++) {
			if(flights.get(i).getDateArrival().equals(arrival)) {
				list.add(flights.get(i));
			}
		}
		
		this.flights = list;
		this.totalFlightAvailable = list.size();
	}
	*/
}

