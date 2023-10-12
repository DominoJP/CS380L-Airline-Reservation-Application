import java.util.ArrayList;

/**
 * The Airport Class holds a list of all airports that are saved in the software and also sorts the airports
 * depending on where they are located by using a binary tree.
 * Each Airport also contains a list of the flights that leave it.
 */

public class Airport {
	private String origin;
	private String name;
	private Airport child1;
	private Airport child2;
	private AirportFlights flights;
	private ArrayList<String> airports;
	
	/**
	 * a constructor for the Airport class that accepts no input
	 */
	
	public Airport() {
		this.origin = null;
		this.name = null;
		child1 = null;
		child2 = null;
		flights = null;
	}
	
	/**
	 * a constructor for the Airport class that takes a String input to set the city the airport resides in
	 * and also finds the name of the airport depending on what city was inputed
	 * @param d
	 */
	
	public Airport(String d) {
		this.origin = d;
		this.name = findAirport(d);
		child1 = null;
		child2 = null;
		flights = null;
	}
	
	/**
	 * a constructor for the Airport that takes a String and a Flight input to set the city the airport
	 * exists in and also sets a flight that takes off at the airport
	 * @param d
	 * @param f
	 */
	
	public Airport(String d, Flight f) {
		this.origin = d;
		this.name = findAirport(d);
		child1 = null;
		child2 = null;
		flights = null;
	}
	
	/**
	 * the findAirport method searches through the list of airports that have been saved and returns the
	 * name of an airport depending on what city was given
	 * @param o
	 * @return
	 */
	
	public String findAirport(String o) {
		String airport = null;
		
		for(int i = 0; i < airports.size(); i++) {
			if(airports.get(i) == o)
				airport = airports.get(i);
		}
		
		return airport;
	}
	
	/**
	 * the addFlights method either adds a new flight into the list of flights for an airport
	 * or it adds a new airport into the binary tree and creates its list of flights starting
	 * with the flight that was given to the airport
	 * @param f
	 */
	
	public void addFlight(Flight f) {
		
		if(this.getOrigin() == null) {
			if(this.flights == null) {
				this.flights = new AirportFlights(f.getcityDeparture());
			}
			
			flights.addFlight(f);;
		}else if(this.getOrigin() == f.getcityArrival()) {
			if(this.flights == null) {
				this.flights = new AirportFlights(f.getcityDeparture());
			}
			
			flights.addFlight(f);
		}else {
			Airport newChild = new Airport(f.getcityDeparture());
			newChild.flights = new AirportFlights(f.getcityDeparture());
			newChild.flights.addFlight(f);
			
			this.addChild(this, newChild);
		}
	}
	
	/**
	 * the addChild method adds a new airport to the binary tree and sorts it depending on the
	 * city the airport resides in
	 * @param curr
	 * @param n
	 */
	
	public void addChild(Airport curr, Airport n) {
		if(curr.getOrigin().compareTo(n.getOrigin()) > 0) {
			if(curr.child1 != null) {
				curr.addChild(curr.child1, n);
				return;
			}
			
			curr.child1 = n;
		}else {
			if(curr.child2 != null) {
				curr.addChild(curr.child2, n);
				return;
			}
			
			curr.child2 = n;
		}
	}
	
	/**
	 * the getOrigin methods return the city this instance of the airport resides in
	 * @return
	 */
	
	public String getOrigin() {
		return this.origin;
	}
}
