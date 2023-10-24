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
		root1.addFlight(flight);
	}
	
	public void sortFlights(String origin, String destination) {
		 Airport curr = root1;
		 curr = curr.search(origin);
		 AirportFlights root2 = curr.findFlights(destination);
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
	
	public Flight findFlight(String date, String time) {
		for(int i = 0; i < flights.size(); i++) {
			if(flights.get(i).getdateDeparture() == date && flights.get(i).gettimeDeparture() == time)
				return flights.get(i);
		}
		
		return null;
	}
}

