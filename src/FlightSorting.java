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
	private List<Flight> flights;

	private Airport root1;
	private Airport root2;
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
		if(flight.gettype() == "One-way") {	
			if(root1 == null) {
				root1 = new Airport(flight);
				return;
			}
			
			root1.addFlight(flight);
		}else {
			if(root2 == null) {
				root2 = new Airport(flight);
				return;
			}
			
			root2.addFlight(flight);
		}
	}
	
	/**
	 * Sorts the list of flights 
	 */
	public void sortFlights(String type, String origin, String arrival, String date, LocalDate arrivalTime) {

		 Airport curr = this.search(type, origin);
		 AirportFlights search = curr.search(arrival, date);
		 this.setFlights(search.getFlights());
		 this.setTotalFlightAvailable(search.getFlights().size());


		 destination = findFlights(type, origin, arrival, date);
		 flights = search.getFlights();
		 totalFlightAvailable = flights.size();
		 
		 if(type == "Two-way")
			 this.findArrivalFlights(arrivalTime);
		 
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
	
	public Airport search(String type, String origin) {
		Airport curr = null;
		if(type == "One-way") {
			curr = root1;
		}else
			curr = root2;
		
		while(curr.getOrigin().compareTo(origin) != 0) {
			
			if(origin.compareTo(curr.getOrigin()) < 0)
				curr = curr.getChild1();
			else
				curr = curr.getChild2();
		}
		
		return curr;
	}
	
	public AirportFlights findFlights(String type, String origin, String destination, String date) {
		return this.search(type, origin).search(destination, date);
	}
	
	public void findArrivalFlights(LocalDate arrival) {
		ArrayList<Flight> list = new ArrayList<Flight>();
		for(int i = 0; i < flights.size(); i++) {
			if(flights.get(i).getDateArrival().equals(arrival))
				list.add(flights.get(i));
		}
		
		flights = list;
		totalFlightAvailable = list.size();
	}
	
	public String[] getList(String type, String origin, String destination, String date) {
		AirportFlights curr = findFlights(type, origin, destination, date);
		LocalDate depart;
		LocalDate arrive;
		
		
		ArrayList<Flight> flights = curr.getFlights();
		String[] list = new String[(flights.size())];

		for(int i = 0; i < list.length; i++) {

			depart = flights.get(i).getdateDeparture();
			arrive = flights.get(i).getDateArrival();
			list[i] = depart.toString() + " - " + arrive.toString();

		}
		
		return list;
	}
	
	public ArrayList<Flight> getFlightList(String origin, String destination, String date) {
		AirportFlights curr = findFlights(origin, destination, date);

		ArrayList<Flight> flights = curr.getFlights();
		return flights;
		
	}
}

