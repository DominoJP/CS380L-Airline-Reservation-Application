import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * The Reservation class handles the creation of a reservation for a customer and also holds information
 * on the reservation in case if a manager chooses to view it
 * @author Logan Langewisch
 * @version 3.0, last updated November 21, 2023
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
	 * @param bookingDate 
	 * @param cabinClass 
	 * @param flight2 
	 * @param accountId 
	 * @param i 
	 */
	public Reservation(int i, String accountId, Flight flight2, String cabinClass, LocalDateTime bookingDate) {
		this.customer = null;
		this.flight = null;
	}
	
	/**
	 * a constructor that takes an instance of the Account class, n, to assign who is making the reservation
	 * @param Account n
	 */
	 public Reservation(Account n) {
		 this.customer = n;
		 this.customerId = customer.getAccountNumber();
	 }
	 
	 /**
	  * A constructor that takes individually inputed customer information to set the reservation to
	  * @param int reservationID
	  * @param Account account
	  * @param Flight matchingFlight
	  * @param String cabin
	  * @param ArrayList<String> passengers
	  * @param BigDecimal totalPricing
	  * @param LocalDateTime bookingDateTime
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
	  * This method allows the user to manually set what Flight, f, is being reserved
	  * @param Flight f
	  */
	 
	 public void setFlight(Flight f) {
		 this.flight = f;
	 }
	 
	 /**
	  * This method returns the flight being reserved
	  * @return Flight
	  */
	 
	 public Flight getFlight() {
		 return this.flight;
	 }
	 
	 /**
	  * This method returns what type of ticket is being purchased
	  * @return String
	  */
	 
	 public String getCabin() {
		 return this.cabin;
	 }
	 
	 /**
	  * This method sets what type of ticket is being purchased
	  * @param String cabin
	  */
	 
	 public void setCabin(String cabin) {
		 this.cabin = cabin;
	 }
	 
	 /**
	  * This method adds a person to the list of passengers for this reservation by adding their name, String p
	  * @param String p
	  */
	 
	 public void addPassenger(String p) {
		 passengers.add(p);
	 }
	 
	 /**
	  * This method returns the list of people that have been saved onto the reservation
	  * @return ArrayList<String>
	  */
	 
	 public ArrayList<String> getPassengers(){
		 return this.passengers;
	 }
	 
	 /**
	  * Method returning LocalDateTime at booking.
	  * @return LocalDateTime
	  */
	 public LocalDateTime getDateTimeAtBooking() {
		 return this.dateTimeAtBooking;
	 }
	 
	 
	 /**
	  * this method allows the user to remove a passenger from the reservation by inputting their name, String p
	  * @param String p
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
	  * this method gives and ArrayList<String> that is composed of all information that had been stored by this instance of
	  * the Reservation class
	  * @return ArrayList<String>
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
	 
	 /**
	  * The toString() method takes all information that has been stored on this instance of the Reservation class
	  * and converts it all into one String
	  * @return String
	  */
	 
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
 	   		   timeDepartureHour + ":" + String.format("%02d", this.flight.getZonedDateTimeDeparture().getMinute()) + " " + departPeriod + " - " +
 	   		   this.flight.getZonedDateTimeArrival().getMonth() + " " +  this.flight.getZonedDateTimeArrival().getDayOfMonth() + " " + 
 	   		   timeArrivalHour + ":" + String.format("%02d", this.flight.getZonedDateTimeArrival().getMinute()) + " " + arrivePeriod;
    }
	 
	
}
