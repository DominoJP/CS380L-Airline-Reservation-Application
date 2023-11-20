import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Owner: Logan Langewisch
 * The AirportFlights class sorts all the flights that take off at an airport
 * in order of what date the flights and leaves and then from what time on that date
 * they take off
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
	 * location all flights in this instance of the class are heading towards
	 * @param a
	 */
	
	public AirportFlights(String a) {
		this.date = LocalDate.parse(a);
		this.child1 = null;
		this.child2 = null;
		this.destination = null;
		this.timeDeparture = null;
	}
	
	public AirportFlights(Flight f) {
		this.date = f.getdateDeparture();
		this.child1 = null;
		this.child2 = null;
		this.destination = f.getcityArrival();
		this.timeDeparture = new ArrayList<Flight>();
		timeDeparture.add(f);
	}
	
	/**
	 * the addFlight method for the AirportFlights class adds a flight into the list of planes from
	 * for the airport and sorts them based on what time they leave
	 * @param f
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
	 * The method addToList takes @param flight, and instance of the Flight class, and adds it to the
	 * ArrayList<Flight> timeDeparture which is an ArrayList that is sorted based on what time the flight
	 * leaves
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
	 * and returns the flight that matches the time of departure that was given
	 * @param t
	 * @return
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
	 * the addChild method will take two instances of the AirportFlights class and will recursively add
	 * and new list of flights when a flight is leaving to a city that has not been added to the tree
	 * @param curr
	 * @param n
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
	 * @return
	 */
	
	public LocalDate getDate() {
		return this.date;
	}
	
	public String getDestination() {
		return this.destination;
	}
	
	/**
	 * this method returns a String array that possesses the time of departure for each flight that
	 * is heading towards this instance of the AirportFlights' destination
	 * @return
	 */
	
	public String[] getTimes() {
		String[] times = new String[this.timeDeparture.size()];
		
		for(int i = 0; i < this.timeDeparture.size(); i++) {
			times[i] = this.timeDeparture.get(i).gettimeDeparture().toString();
		}
		
		return times;
	}
	
	public ArrayList<Flight> getFlights(){
		return timeDeparture;
	}
	
	public AirportFlights getChild1() {
		return child1;
	}
	
	public AirportFlights getChild2() {
		return child2;
	}
}
