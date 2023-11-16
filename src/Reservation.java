import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * Owner: Logan Langewisch
 * The Reservation class handles the creation of a reservation for a customer with an account
 */

public class Reservation {
	private int reservationId;
	private Account customer;//whoever has the account and is making the reservation
	private int customerId;
	private Flight flight;
	private int flightId;
	private String cabin;
	private ArrayList<String> passengers; //total list of passengers in case if the reservation includes more than just the customer
	//private ArrayList<String> passports; //variable in case if we decide to add passports to the reservations
	//private int[] seatNumbers; //an array containing the list of chosen seat numbers for the flight
	private BigDecimal totalPrice; // keeps track of the total cost of this reservation since multiple tickets may be ordered
	private LocalDateTime dateTimeAtBooking;
	
	/**
	 * a constructor that accepts nothing
	 */
	public Reservation() {
		this.customer = null;
		this.flight = null;
	}
	
	/**
	 * a constructor that adds the customer account making the reservation and the flight the reservation applies to
	 * @param n is the Account making the reservation
	 */
	 public Reservation(Account n) {
		 this.customer = n;
		 this.customerId = customer.getAccountNumber();
	 }
	 
	 /**
	   Constructor that adds the customer account making the reservation and the flight the reservation applies to
	   @param i is the id
	   @param n is the Account making the reservation
	   @param f is the flight that the reservation is for
	   @param p is the List of passenger names
	   @param t is total price
	   @param b is the date&time at booking
	   and also calls the method setReservation()
	 */
	 
	 public Reservation(int reservationID, Account account, Flight matchingFlight, String cabin, ArrayList<String> passengers, BigDecimal totalPricing, 
			 LocalDateTime bookingDateTime) {
		 this.reservationId = reservationID;
		 this.customer = account;
		 this.customerId = account.getAccountNumber();
		 this.flight = matchingFlight;
		 this.flightId = matchingFlight.getID();
		 this.cabin = cabin;
		 this.passengers = passengers;
		 this.totalPrice = totalPricing;
	 }
	 
	 /*
	 public Reservation(int id, Account a, Flight f, String c, ArrayList<String> p, BigDecimal t, LocalDateTime b) {
		 this.id = id; 
		 this.customer = a;
		 this.flight = f;
		 this.cabin = c;
		 this.passengers = p;
		 this.totalPrice = t;
		 this.dateTimeAtBooking = b;
	 }
	 */
	 
	 /**
	  * the method that finishes the creation of the reservation by allowing the customer
	  * to add all the people will be on the reservation(including themselves) and also to select the seats
	  * that all passengers want
	  */
	 public void setReservation(Flight f, String type, ArrayList<String> people) {
		 this.dateTimeAtBooking = LocalDateTime.now();
		 this.flight = f;
		 this.flightId = f.getID();
		 this.cabin = type;
		 this.passengers = people;
		 //this.passports = documentation;
		 
		 switch(this.cabin) {
		 	case "Economy":
		 		this.flight.addEconomyPassengerCount(this.passengers.size());
		 		break;
		 	case "Business":
		 		this.flight.addBusinessPassengerCount(this.passengers.size());
		 		break;
		 	case"First Class":
		 		this.flight.addFirstClassPassengerCount(this.passengers.size());
		 		break;
		 	default:
		 }
		 
		 this.setTotalPrice();
	 }
	 
	 public void setId(int identification) {
		 this.reservationId = identification;
	 }
	 
	 public void setFlightId(int identification) {
		 this.flightId = identification;
	 }
	 
	 public void setCustomerId(int identification) {
		 this.customerId = identification;
	 }
	 
	 public void setPassengers(ArrayList<String> people) {
		 this.passengers = people;
		 this.setTotalPrice();
	 }
	 
	 /**
	 public void setPassports(ArrayList<String> documentation) {
		 this.passports = documentation;
	 }
	 */
	 
	 public void setBooking(LocalDateTime current) {
		 this.dateTimeAtBooking = current;
	 }
	 
	 /**
	  * Method returning unique reservation ID.
	  */
	 public int getID() {
		 return this.reservationId;
	 }
	 
	 /**
	  * Method returning total pricing of reservation.
	  */
	 public BigDecimal getTotalPrice() {
		 return this.totalPrice;
		 
	 }
	 
	 public void setTotalPrice(BigDecimal price) {
		 this.totalPrice = price;
	 }
	 
	 /**
	  * method that gives the total price of the reservation using the price per ticket of the flight
	  * and the number of passengers that was given
	  * @param p is the number of passengers for the reservation
	  * @return returns the total price that was calculated by the method
	  */
	 
	 public void setTotalPrice() {
		 BigDecimal price = new BigDecimal(0);
		 
		 switch(this.cabin) {
			 case "Economy":
				 price = this.flight.getEconomyPricing();
				 break;
			 case "Business":
				 price = this.flight.getBusinessPricing();
				 break;
			 case "First Class":
				 price = this.flight.getFirstClassPricing();
				 break;
		 }
		 
		 for(int i = 0; i < this.passengers.size(); i++) {
			 this.totalPrice.add(price);
		 }
		 
	 }
	 
	 /**
	  * This method allows the user to change the flight that is being reserved
	  * and allows recalculates the total price for the new flight
	  * @param f is the new flight that is going to be added to the reservation
	  */
	 
	 public void setFlight(Flight f) {
		 this.flight = f;
	 }
	 
	 public Flight getFlight() {
		 return this.flight;
	 }
	 
	 public String getCabin() {
		 return this.cabin;
	 }
	 
	 public void setCabin(String cabin) {
		 this.cabin = cabin;
	 }
	 
	 public void addPassenger(String p) {
		 passengers.add(p);
	 }
	 
	 public ArrayList<String> getPassengers(){
		 return this.passengers;
	 }
	 
	 /**
	  * Method returning LocalDateTime at booking.
	  */
	 public LocalDateTime getDateTimeAtBooking() {
		 return this.dateTimeAtBooking;
	 }
	 
	 
	 /**
	  * this method allows the user to remove a passenger from the reservation
	  * @param p is the name of the passenger that is being removed
	  */
	 
	 public void removePassenger(String p) {
		 boolean exist = false;
		 
		 for(int i = 0; i < this.passengers.size(); i++) {
			 if(this.passengers.get(i) == p) {
				 this.passengers.remove(i);
				 exist = true;
			 }
		 }
		 
		 if(!exist)
			 return;
		 
	 }
	 
	 /**
	  * this method prints all the set values for the reservation so that the customer is notified of their reservation
	  */
	 
	 public ArrayList<String> getReservation() {
		 ArrayList<String> reservation = new ArrayList<String>();
		 
		 reservation.add("" + this.reservationId);
		 reservation.add("" + this.customerId);
		 reservation.add("" + this.flightId);
		 reservation.add("" + this.dateTimeAtBooking.getYear() + 
				 "-" + this.dateTimeAtBooking.getMonthValue() +
				 "-" + this.dateTimeAtBooking.getDayOfMonth() + 
				 "T" + this.dateTimeAtBooking.getHour() +
				 ":" + this.dateTimeAtBooking.getMinute());
		 reservation.add(this.toString());
		 reservation.add(this.totalPrice.toString());
		 reservation.add(this.cabin);
		 
		 for(int i = 0; i < passengers.size(); i++) {
			 reservation.add(passengers.get(i));
		 }
		 
		 
		 return reservation;
	 }
	 
	@Override
    public String toString() {
		String departPeriod = "";
		int timeDepartureHour = this.flight.getZonedDateTimeDeparture().getHour();
		if (timeDepartureHour < 12) {
			departPeriod = "AM";
		} else {
			departPeriod = "PM";
			timeDepartureHour -= 12;
			if (timeDepartureHour == 0)
				timeDepartureHour = 12;
		}
		
		String arrivePeriod = "";
		int timeArrivalHour = this.flight.getZonedDateTimeArrival().getHour();
		if (timeArrivalHour < 12) {
			arrivePeriod = "AM";
		} else {
			arrivePeriod = "PM";
			timeArrivalHour -= 12;
			if (timeArrivalHour == 0)
				timeArrivalHour = 12;
		}
		
        return this.flight.getcityDeparture() + " to "  + this.flight.getcityArrival() + ", " + 
        	   this.flight.getZonedDateTimeDeparture().getMonth() + " " +  this.flight.getZonedDateTimeDeparture().getDayOfMonth() + " " +
        	   timeDepartureHour + ":" + this.flight.getZonedDateTimeDeparture().getMinute() + " " + departPeriod + " - " +
        	   this.flight.getZonedDateTimeArrival().getMonth() + " " +  this.flight.getZonedDateTimeArrival().getDayOfMonth() + " " + 
               timeArrivalHour + ":" + this.flight.getZonedDateTimeArrival().getMinute() + " " + arrivePeriod;
    }
	 
	
}
