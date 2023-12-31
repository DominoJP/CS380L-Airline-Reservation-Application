import java.util.List;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.*;

/**
 * This class represents a flight sorting system for managing flights and their information. 
 * It allows the system to add flights, sort them, and also retrieve flights that were sorted by using
 * the Airport and AirportFlights classes
 * @author Sayra Reyes and Logan Langewisch
 * @version 2.0, last updated November 21, 2023
 * 
 */


public class FlightSorting{
	
	private int totalFlightsAvailable;
	private ArrayList<Flight> flights;

	private Airport root1;
	/*
	private Airport root2;
	*/
	private AirportFlights destination;
	
	/**
	 * A null constructor for the FlightSorting class
	 */
	
	public FlightSorting() {
		root1 = null;
		flights = null;
	}
	
	/**
	 * A constructor that initializes the list with first, an instance of the Flight class
	 * @param Flight first
	 */
	
	public FlightSorting(Flight first) {
		root1 = new Airport(first);
	}
	
	/**
	 * The initialize method takes list, and ArrayList<Flight>, that will be used to create a binary tree
	 * when there is already a stored list of flights that needs to be added back into the sorting system
	 * @param ArrayList<Flight> list
	 */
	
	public void initialize(ArrayList<Flight> list) {
		for(int i = 0; i < list.size(); i++) {
			this.addFlight(list.get(i));
		}
	}

	/**
	 * Adds flight, an instance of the Flight class, to the list of sorted flights
	 * @param Flight flight
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
	 * this version of the sortFlights method was made to sort flights for both One-Way and Round-Trip flights
	 *  
	 *
	/*
	public void sortFlights(String type, String origin, String arrival, String date, String arrivalTime) {

		 this.destination = findFlights(type, origin, arrival, date);
		 this.flights = destination.getFlights();
		 this.totalFlightAvailable = flights.size();
	}
	*/
	
	/**
	 * The sortFlights method uses origin, a String that is meant to be the name of the location
	 * that the flight originates from, arrival, a String that represents the area the flight is heading
	 * towards, date, a String that is meant to be the date when the flight leaves, this date must
	 * be written in a fashion that can be accepted by the LocalDate API
	 * @param String origin
	 * @param String arrival
	 * @param String date
	 */
	
	public void sortFlights(String origin, String arrival, String date) {
		this.destination = findAirportFlights(origin, arrival, date);
		this.flights = destination.getFlights();
		this.totalFlightsAvailable = flights.size();
	}
	
	/**
	 * After the FlightSorting class has found a list of flights, the findFlight method can be used to find a specific flight that the user
	 * wants to view. The flight is found by comparing the times of each one on the list with an instance of LocalTime made 
	 */
	
	public Flight findFlight(String time) {
		try {	
			if(destination == null) {
				throw new IOException("There is no destination");
			}
			flights = destination.getFlights();
			LocalTime find = LocalTime.parse(time);
			
			for(int i = 0; i < flights.size(); i++) {
				if(flights.get(i).gettimeDeparture().equals(find))
					return flights.get(i);
			}
			
			return null;
		}catch(IOException e) {
			return null;
		}
	}
	
	/**
	 * findFlights utilizes the search method of the AirportFlights class by giving it type and origin, two Strings that represent
	 * where the flight originates from and where it is heading towards, to find
	 * an Airport being searched for by the user, then it uses the search method of the found Airport to find an instance of AirportFlights given
	 * a specific destination and a specific date
	 * @param String origin
	 * @param String destination
	 * @param String date
	 * 
	 */
	
	public AirportFlights findAirportFlights(String origin, String destination, String date) {
		return this.findAirport(origin).search(destination, date);
		
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
	 * The search method goes through the tree that contains all Airports and uses origin, a string, to find a specific Airport and the method then returns
	 * that Airport.
	 * @param String origin
	 * @return Airport
	 */
	
	public Airport findAirport(String origin) {
		Airport curr = root1;
		
		while(curr != null && curr.getOrigin().compareTo(origin) != 0) {
			
			if(origin.compareTo(curr.getOrigin()) < 0)
				curr = curr.getChild1();
			else
				curr = curr.getChild2();
		}
		
		return curr;
	}

	/**
	 * Sets the list of flights using flightList, and ArrayList<Flight> that represents a list of flights. 
	 * @param ArrayList<Flight> flightList
	 */
	public void setFlights(ArrayList<Flight> flightList) {
		this.flights = flightList;
	}

	/**
	 * Sets the total number of available flights with totalFlights, an integer that represents the total number of flights
	 * that were found by the sorting system
	 * @param int totalFlights
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

