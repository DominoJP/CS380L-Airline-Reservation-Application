import java.util.ArrayList;


public class Airport {
	private String origin;
	private String name;
	private Airport child1;
	private Airport child2;
	private AirportFlights flights;
	private ArrayList<String> airports;
	
	public Airport() {
		this.origin = null;
		child1 = null;
		child2 = null;
		flights = null;
	}
	
	public Airport(String d, String n) {
		this.origin = d;
		this.name = n;
		child1 = null;
		child2 = null;
		flights = null;
	}
	
	public Airport(String d, String n, Flight f) {
		this.origin = d;
		this.name = n;
		child1 = null;
		child2 = null;
		flights = null;
	}
	
	public String findAirport(String o) {
		String airport = null;
		
		for(int i = 0; i < airports.size(); i++) {
			if(airports.get(i) == o)
				airport = airports.get(i);
		}
		
		return airport;
	}
	
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
			Airport newChild = new Airport(f.getcityDeparture(), this.findAirport(f.getcityDeparture()));
			newChild.flights = new AirportFlights(f.getcityDeparture());
			newChild.flights.addFlight(f);
			
			this.addChild(this, newChild);
		}
	}
	
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
	
	public String getOrigin() {
		return this.origin;
	}
}
