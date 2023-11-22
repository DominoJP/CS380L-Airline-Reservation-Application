import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The AirportFlights class sorts all the flights that take off at an airport
 * in order of what date the flights depart using a binary tree
 * A binary tree was chosen because it was easy to implement, it would allow for more efficient
 * sorting and searching, and it did not seem that a more complex data structure was required
 * The class also possesses an array list of flights that is sorted by what time the flight
 * leaves by
 * @author Logan Langewisch
 * @version 3.0, last updated November 21, 2023
 */

public class AirportFlights {
	private LocalDate date;
	private String destination;
	private AirportFlights child1;
	private AirportFlights child2;
	private ArrayList<Flight> timeDeparture;
	
	/**
	 * null constructor for the AirportFlights class
	 */
	public AirportFlights() {
		this.date = null;
		this.child1 = null;
		this.child2 = null;
		this.destination = null;
		this.timeDeparture = null;
	}
	
	/**
	 * a constructor for the AirportFlights class that accepts the name of the
	 * location all flights in this instance of the class are heading towards, String a
	 * @param String a
	 */
	
	public AirportFlights(String a) {
		this.date = LocalDate.parse(a);
		this.child1 = null;
		this.child2 = null;
		this.destination = null;
		this.timeDeparture = null;
	}
	
	/**
	 * a constructor that accepts an instance of the Flight class, f, in order to initialize
	 * some of its variables
	 * @param Flight f
	 */
	
	public AirportFlights(Flight f) {
		this.date = f.getdateDeparture();
		this.child1 = null;
		this.child2 = null;
		this.destination = f.getcityArrival();
		this.timeDeparture = new ArrayList<Flight>();
		timeDeparture.add(f);
	}
	
	/**
	 * the addFlight method for the AirportFlights class adds a Flight, f, into the list of planes from
	 * for the airport and sorts them based on what time they leave
	 * @param Flight f
	 */
	
	public void addFlight(Flight f) {
		if(timeDeparture == null) {
			if(date == null) {
				date = f.getdateDeparture();
				destination = f.getcityArrival();
			}
			timeDeparture = new ArrayList<Flight>();
			timeDeparture.add(f);
			return;
		}
		
		if(date.equals(f.getdateDeparture())) {
			this.addToList(f);
		}else {
			this.addChild(this, f);
		}
	}
	
	/**
	 * The method addToList takes flight, and instance of the Flight class, and adds it to the
	 * ArrayList<Flight> timeDeparture which is an ArrayList that is sorted based on what time the flight
	 * leaves
	 * @param Flight flight
	 */
	
	public void addToList(Flight flight) {
		if(this.timeDeparture == null) {
			this.timeDeparture.add(flight);
		}else {
			for(int i = 0; i < this.timeDeparture.size(); i++){
				if(flight.gettimeDeparture().isBefore(timeDeparture.get(i).gettimeDeparture())) {
					timeDeparture.add(i, flight);
					return;
				}
			}
			timeDeparture.add(flight);
		}
	}
	
	/**
	 * the findFlight method searches through the list of flights that leave to a specific destination
	 * and returns the flight that matches the time of departure that was given, String t
	 * @param String t
	 * @return Flight
	 */
	
	public Flight findFlight(String t) {
		Flight flight = null;
		LocalTime time = LocalTime.parse(t);
		
		for(int i = 0; i < this.timeDeparture.size(); i++) {
			if(this.timeDeparture.get(i).gettimeDeparture().equals(time)) {
				flight = this.timeDeparture.get(i);
			}
		}
		
		if(flight == null) {
			System.out.println("No Results \n");
			return null;
		}
		
		return flight;
	}
	
	/**
	 * the addChild method will take an instance of the Flight class, n, and will attempt to recursively
	 * add it into the binary tree by keeping track of its current location in the tree with
	 * an instance of the AirportFlights class, curr 
	 * @param AirportFlights curr
	 * @param Flight n
	 */
	
	public void addChild(AirportFlights curr, Flight n) {
		if(curr.date.isAfter(n.getdateDeparture())) {
			if(curr.child1 != null) {
				if(curr.child1.getDate().equals(n.getdateDeparture())) {
					curr.child1.addFlight(n);
					return;
				}
				curr.addChild(curr.child1, n);
				return;
			}
			
			AirportFlights newChild = new AirportFlights(n);
			
			curr.child1 = newChild;
		}else {
			if(curr.child2 != null) {
				if(curr.child2.getDate().equals(n.getdateDeparture())) {
					curr.child2.addFlight(n);
					return;
				}
				curr.addChild(curr.child2, n);
				return;
			}
			
			AirportFlights newChild = new AirportFlights(n);
			
			curr.child2 = newChild;
		}
	}
	
	/**
	 * the getDestination method returns the destination that the list of flights heads to
	 * @return LocalDate
	 */
	
	public LocalDate getDate() {
		return this.date;
	}
	
	/**
	 * The getDestination() method will return the destination that this list of flights flies towards
	 * @return String
	 */
	
	public String getDestination() {
		return this.destination;
	}
	
	/**
	 * this method returns a String array that possesses the time of departure for each flight that
	 * is heading towards this instance of the AirportFlights' destination
	 * @return String[]
	 */
	
	public String[] getTimes() {
		String[] times = new String[this.timeDeparture.size()];
		
		for(int i = 0; i < this.timeDeparture.size(); i++) {
			times[i] = this.timeDeparture.get(i).gettimeDeparture().toString();
		}
		
		return times;
	}
	
	/**
	 * this method gets the list of flights this instance of AirportFlights stores
	 * @return ArrayList<Flight>
	 */
	
	public ArrayList<Flight> getFlights(){
		return timeDeparture;
	}
	
	/**
	 * this method returns the left child of this current instance of AirportFlights
	 * @return AirportFlights
	 */
	
	public AirportFlights getChild1() {
		return child1;
	}
	
	/**
	 * this method returns the right child of this current instance of AirportFlights
	 * @return AirportFlights
	 */
	
	public AirportFlights getChild2() {
		return child2;
	}
}
