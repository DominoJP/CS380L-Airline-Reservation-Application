import java.util.ArrayList;
import java.util.List;

public class FlightSorting{
	
	private int totalFlightAvailable;
	private List<Flight> flights;
	private Airport root1;
	
	public FlightSorting() {
		root1 = null;
		flights = null;
	}
	
	public FlightSorting(Flight first) {
		root1 = new Airport(first);
	}


	public int getTotalFlightAvailable() {
		return totalFlightAvailable;
	}


	public void setTotalFlightAvailable(int totalFlightAvailable) {
		this.totalFlightAvailable = totalFlightAvailable;
	}


	public List<Flight> getFlights() {
		return flights;
	}


	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public void addFlight(Flight flight) {
		if(root1 == null) {
			root1 = new Airport(flight);
			return;
		}
		
		root1.addFlight(flight);
	}
	
	public void sortFlights(String origin, String destination, String date) {
		 Airport curr = root1;
		 curr = this.search(origin);
		 AirportFlights root2 = curr.search(destination, date);
		 this.setFlights(root2.getFlights());
		 this.setTotalFlightAvailable(root2.getFlights().size());
	}
	
	public String[] displayAvaiableSeating(Flight f) {
		String[] passengers = new String[f.gettotalpassengercapacity()];
		
		for(int i = 0; i < f.gettotalpassengercapacity(); i++) {
			passengers[i] = f.getPassenger(i);
		}
		
		
		return passengers;
	}
	
	public Flight findFlight(String time) {
		
		for(int i = 0; i < flights.size(); i++) {
			if(flights.get(i).gettimeDeparture() == time)
				return flights.get(i);
		}
		
		return null;
	}
	
	public Airport search(String o) {
		Airport curr = root1;
		
		while(curr.getOrigin() != o) {
			if(o.compareTo(curr.getOrigin()) < 0)
				curr = curr.getChild1();
			else
				curr = curr.getChild2();
		}
		
		return curr;
	}
	
	public AirportFlights findFlights(String destination, String date) {
		return this.search(destination).search(destination, date);
	}
}

