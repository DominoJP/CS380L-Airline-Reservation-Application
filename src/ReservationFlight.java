import java.util.List;


//Define Reservation Flight
public class ReservationFlight {
		
	    private Flight flight;
		private List<Passenger> passegers;
		
		public Flight getflight() {
			return flight; 
		}

		public List<Passenger> getPassegers() {
			return passegers;
		}

		public void setPassegers(List<Passenger> passegers) {
			this.passegers = passegers;
		}

		public void setFlight(Flight newflight) {
			this.flight = newflight;
		}
		

		public Flight getFlight() {
			return null;
		}
		
	}
