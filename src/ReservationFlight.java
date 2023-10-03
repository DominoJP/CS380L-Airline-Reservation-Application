import java.util.List;


public class ReservationFlight {
	//Define Reservation class
	
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
		
		
	}
