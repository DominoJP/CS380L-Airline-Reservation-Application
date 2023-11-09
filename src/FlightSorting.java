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
	
	private int totalFlightAvailable;
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
	
	public FlightSorting(Flight first) {
		root1 = new Airport(first);
	}
	
	public void initialize(ArrayList<Flight> list) {
		for(int i = 0; i < list.size(); i++) {
			this.addFlight(list.get(i));
		}
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
	public ArrayList<Flight> getFlights() {
		return flights;
	}

	/**
	 * Sets the list of flights. 
	 * @param flights
	 */
	public void setFlights(ArrayList<Flight> flights) {
		this.flights = flights;
	}

	/**
	 * Adds a flight to the list of managed flights. 
	 * @param flight
	 */
	public void addFlight(Flight flight) {
		/*
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
		this.destination= findFlights(origin, arrival, date);
		this.flights = destination.getFlights();
		this.totalFlightAvailable = flights.size();
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
	 */
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
	 * findFlights utilizes the search method of the AirportFlights class by giving it @param type and @param origin to find
	 * an Airport being searched for by the user, then it uses the search method of the found Airport to find an instance of AirportFlights given
	 * @param destination and @param date
	 */
	
	public AirportFlights findFlights(String origin, String destination, String date) {
		return this.search(origin).search(destination, date);
	}
	
	/**
	 * In the case that a list of two-way flights is being searched for, the findArrivalFlights method will sort the list of Flights found
	 * using the @param arrival to sort the list by which flights will return at a specific date
	 * @param arrival
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
	
	/**
	 * the getList method will @return an array of String that consists of the departure and arrival times of a 
	 * list of Flights that are found using the findFlights method with @param type, @param origin, @param destination,
	 * @param date, and if the Flight is two-way it uses @param arrival and the findArrivalFLights method to sort the list
	 */
	
	public String[] getList(String arrival) {
		LocalDate depart;
		LocalDate arrive;
		
		/*
		if(type.equals("Two-way")) {
			this.findArrivalFlights(LocalDate.parse(arrival));
		}
		*/
		
		String[] list = new String[(flights.size())];

		for(int i = 0; i < list.length; i++) {

			depart = flights.get(i).getdateDeparture();
			arrive = flights.get(i).getDateArrival();
			list[i] = depart.toString() + " - " + arrive.toString();

		}
		
		return list;
	}
}

