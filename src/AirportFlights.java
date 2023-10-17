import java.util.ArrayList;

/**
 * Owner: Logan Langewisch
 * The AirportFlights class sorts all the flights that take off at an airport
 * in order of where the flights are heading to and then what time they
 * leave using a binary tree
 */

public class AirportFlights {
	private String destination;
	private AirportFlights child1;
	private AirportFlights child2;
	private ArrayList<Flight> timeDeparture;
	
	/**
	 * null constructor for the AirportFlights class
	 */
	public AirportFlights() {
		this.destination = null;
		this.child1 = null;
		this.child2 = null;
		this.timeDeparture = null;
	}
	
	/**
	 * a constructor for the AirportFlights class that accepts the name of the
	 * location all flights in this instance of the class are heading towards
	 * @param a
	 */
	
	public AirportFlights(String a) {
		this.destination = a;
		this.child1 = null;
		this.child2 = null;
		this.timeDeparture = null;
	}
	
	/**
	 * the addFlight method for the AirportFlights class adds a flight into the list of planes from
	 * for the airport and sorts them based on what time they leave
	 * @param f
	 */
	
	public void addFlight(Flight f) {
		Flight curr = f;
		Flight next = null;
		if(this.destination == f.getcityArrival()) {
			if(this.timeDeparture == null) {
				this.timeDeparture.add(f);
			}else {
				for(int i = 0; i < this.timeDeparture.size(); i++){
					if(curr.gettimeDeparture().compareTo(this.timeDeparture.get(i).gettimeDeparture()) >= 0) {
						next = this.timeDeparture.get(i);
						this.timeDeparture.set(i, curr);
						curr = next;
					}
				}
				
				this.timeDeparture.add(curr);
			}
		}else {
			AirportFlights newChild = new AirportFlights(f.getcityArrival());
			newChild.addFlight(f);
			
			this.addChild(this, newChild);
		}
	}
	
	/**
	 * the addChild method will take two instances of the AirportFlights class and will recursively add
	 * and new list of flights when a flight is leaving to a city that has not been added to the tree
	 * @param curr
	 * @param n
	 */
	
	public void addChild(AirportFlights curr, AirportFlights n) {
		if(curr.destination.compareTo(n.destination) > 0) {
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
	 * the getDestination method returns the destination that the list of flights heads to
	 * @return
	 */
	
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
			times[i] = this.timeDeparture.get(i).gettimeDeparture();
		}
		
		return times;
	}
	
	public ArrayList<Flight> getFlights(){
		return timeDeparture;
	}
	
	/**
	 * the search method searches through the binary tree for the list of flights
	 * an airport possesses that heads toward a specific destination
	 * @param d
	 * @return
	 */
	
	public AirportFlights search(String d) {
		AirportFlights curr = this;
		
		while(curr.destination != d) {
			if(d.compareTo(curr.destination) < 0)
				curr = curr.child1;
			else
				curr = curr.child2;
		}
		
		return curr;
		
	}
	
	/**
	 * the findFlight method searches through the list of flights that leave to a specific destination
	 * and returns the flight that matches the time of departure that was given
	 * @param t
	 * @return
	 */
	
	public Flight findFlight(String t) {
		Flight flight = null;
		
		for(int i = 0; i < this.timeDeparture.size(); i++) {
			if(this.timeDeparture.get(i).gettimeDeparture() == t) {
				flight = this.timeDeparture.get(i);
			}
		}
		
		if(flight == null) {
			System.out.println("No Results \n");
			return null;
		}
		
		return flight;
	}
}
