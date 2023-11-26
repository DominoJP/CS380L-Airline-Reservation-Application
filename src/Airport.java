import java.util.ArrayList;
import java.time.LocalDate;

/**
 * The Airport Class holds a list of all airports that are saved in the software and also sorts the airports
 * depending on there names using a binary tree.
 * A binary tree was chosen because it was easy to create, would allow for more efficient sorting and searching
 * of flights, and it did not seem as if any more complex data structures were required
 * Each Airport also contains an array list of AiprotFlights that represents the list of flights that leave each airport
 * and is sorted by where the flights are heading towards.
 * An array list was chosen since there would only be a finite amount locations that flights from one location may head towards.
 * @author Logan Langewisch
 * @version 3.0, last updated November 21, 2023
 */

public class Airport {
	private String origin;
	private String name;
	private Airport child1;
	private Airport child2;
	private ArrayList<AirportFlights> destinations;
	//private ArrayList<String> airports;
	
	/**
	 * a constructor for the Airport class that accepts no input
	 */
	
	public Airport() {
		this.origin = null;
		this.name = null;
		child1 = null;
		child2 = null;
		destinations = null;
	}
	
	/**
	 * a constructor for the Airport class that takes a String input, d, to set the city the airport resides in
	 * and also finds the name of the airport depending on what city was inputed
	 * @param String d
	 */
	
	public Airport(String d) {
		this.origin = d;
		//this.name = findAirport(d);
		child1 = null;
		child2 = null;
		destinations = null;
	}
	
	/**
	 * a constructor for the Airport that takes an instance of Flight, f, and sets the information for
	 * this instance of Airport to some of the Flight information
	 * @param Flight f
	 */
	
	public Airport(Flight f) {
		this.origin = f.getcityDeparture();
		//this.name = findAirport(this.origin);
		child1 = null;
		child2 = null;
		AirportFlights flights = new AirportFlights(f);
		destinations = new ArrayList<AirportFlights>();
		destinations.add(flights);
	}
	/**
	 * the findAirport method searches through the list of airports that have been saved and returns the
	 * name of an airport depending on what city was given
	 * @param o
	 * @return
	 */
	
	//public String findAirport(String o) {
		//String airport = null;
		
		//for(int i = 0; i < airports.size(); i++) {
			//if(airports.get(i) == o)
				//airport = airports.get(i);
		//}
		
		//return airport;
	//}
	
	/**
	 * the addFlights method either adds a new flight, f, into the list of flights for an airport
	 * or it adds a new airport into the binary tree and creates its list of flights starting
	 * with the flight that was given to the airport
	 * @param Flight f
	 */
	
	public void addFlight(Flight f) {
		if(destinations == null)
			destinations = new ArrayList<AirportFlights>();
		
		if(this.origin == null) {
			origin = f.getcityDeparture();
			destinations.add(new AirportFlights(f));
		}else if(this.origin.compareTo(f.getcityDeparture()) == 0) {
			this.addToList(f);
		}else {
			this.addChild(this, f);
		}
		
	}
	
	/**
	 * The addToList method adds flight, an instance of the Flight class, into either the one of the instances of AiportFlights that is stored
	 * in the ArrayList<AirportFlights> destinations or it will be used to create a new instance of AiportFlights and is then added to destinations
	 * @param Flight flight
	 */
	
	public void addToList(Flight flight) {
		for(int i = 0; i < destinations.size(); i++) {
			if(flight.getcityArrival().compareTo(destinations.get(i).getDestination()) == 0) {
				destinations.get(i).addFlight(flight);
				return;
			}
		}
		
		AirportFlights flights = new AirportFlights(flight);
		
		for(int i = 0; i < destinations.size(); i++) {
			if(flight.getcityArrival().compareTo(destinations.get(i).getDestination()) < 0) {
				destinations.add(i, flights);
				return;
			}
		}
		
		destinations.add(flights);
	}
	
	/**
	 * the addChild method adds a new instance of Flight, n, by recursively going through the binary tree
	 * by keeping track of the current Airport the class is viewing, curr
	 * @param Airport curr
	 * @param Flight n
	 */
	
	public void addChild(Airport curr, Flight n) {
		
		if(curr.getOrigin().compareTo(n.getcityDeparture()) > 0) {
			if(curr.child1 != null) {
				if(curr.child1.getOrigin().equals(n.getcityDeparture())) {
					curr.child1.addFlight(n);
					return;
				}
				
				curr.addChild(curr.child1, n);
				return;
			}
			Airport newChild = new Airport(n);
			
			curr.child1 = newChild;
		}else {
			if(curr.child2 != null) {
				if(curr.child2.getOrigin().equals(n.getcityDeparture())) {
					curr.child2.addFlight(n);
					return;
				}
				
				curr.addChild(curr.child2, n);
				return;
			}
			Airport newChild = new Airport(n);
			
			curr.child2 = newChild;
		}
	}
	
	/**
	 * the getOrigin methods return the city this instance of the airport resides in
	 * @return String
	 */
	
	public String getOrigin() {
		return this.origin;
	}
	
	/**
	 * the search method takes the city you are going to leave from, String destination, and the date
	 * of departure, String date, then searches the binary tree and returns what airport you are looking for
	 * @param String destination
	 * @param String date
	 * @return AirportFlights
	 */
	
	public AirportFlights search(String destination, String date) {
		AirportFlights curr = null;
		LocalDate time = LocalDate.parse(date);
		
		for(int i = 0; i < destinations.size(); i++) {
			if(destinations.get(i).getDestination().compareTo(destination) == 0) {
				curr = destinations.get(i);
			}
		}
		
		while(!curr.getDate().equals(time)) {
			if(curr.getDate().isAfter(time))

				curr = curr.getChild1();
			else
				curr = curr.getChild2();
		}
		
		return curr;
		
	}
	
	/**
	 * The getChild1() method returns the left child of this instance of Airport
	 * @return Airport
	 */
	
	public Airport getChild1() {
		return child1;
	}
	
	/**
	 * The getChild2() method returns the right child of this instance of Airport
	 * @return Airport
	 */
	
	public Airport getChild2() {
		return child2;
	}
	
}
