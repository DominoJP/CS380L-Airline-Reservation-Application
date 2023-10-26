import java.util.ArrayList;
import java.util.List;

public class FlightSorting{
	
	private int totalFlightAvailable;
	private List<Flight> flights;
	private Airport root1;
	private AirportFlights root2;
	
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
		 Airport curr = search(origin);
		 root2 = findFlights(origin, destination, date);
		 flights = root2.getFlights();
		 totalFlightAvailable = flights.size();
	}
	
	public String[] displayAvaiableSeating(Flight f) {
		String[] passengers = new String[f.gettotalpassengercapacity()];
		
		for(int i = 0; i < f.gettotalpassengercapacity(); i++) {
			passengers[i] = f.getPassenger(i);
		}
		
		
		return passengers;
	}
	
	public Flight findFlight(String time) {
		flights = root2.getFlights();
		
		//System.out.println(flights.size());
		
		for(int i = 0; i < flights.size(); i++) {
			if(flights.get(i).gettimeDeparture() == time)
				return flights.get(i);
		}
		
		return null;
	}
	
	public Airport search(String origin) {
		Airport curr = root1;
		//System.out.println(curr.getOrigin());
		
		while(curr.getOrigin() != origin) {
			
			if(origin.compareTo(curr.getOrigin()) < 0)
				curr = curr.getChild1();
			else
				curr = curr.getChild2();
		}
		
		return curr;
	}
	
	public AirportFlights findFlights(String origin, String destination, String date) {
		return this.search(origin).search(destination, date);
	}
}

