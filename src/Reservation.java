import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Owner: Logan Langewisch
 * The Reservation class handles the creation of a reservation for a customer with an account
 */

public class Reservation {
	private int id;
	private Account customer; //whoever has the account and is making the reservation
	private Flight flight;
	private ArrayList<String> passengers; //total list of passengers in case if the reservation includes more than just the customer
	private ArrayList<Integer> seatNumbers; //an array containing the list of chosen seat numbers for the flight
	private BigDecimal totalPrice; // a BigDecimal keeps track of the total cost of this reservation since multiple tickets may be ordered
	private LocalDateTime dateOfDeparture;
	private String origin;
	private String destination;
	
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
	 * @param f is the flight that the reservation is for
	 * and also calls the method setReservation()
	 */
	 public Reservation(Account n, Flight f) {
		 this.customer = n;
		 this.flight = f;
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
	 public Reservation(int id, Account a, Flight f, ArrayList<String> p, BigDecimal t, LocalDateTime b) {
		 this.id = id;
		 this.customer = a;
		 this.flight = f;
		 this.passengers = p;
		 this.totalPrice = t;
		 this.dateOfDeparture = b;
	 }
	 
	 /**
	  * the method that finishes the creation of the reservation by allowing the customer
	  * to add all the people will be on the reservation(including themselves) and also to select the seats
	  * that all passengers want
	  */
	 public void setReservation(int id, ArrayList<String> people, ArrayList<Integer> seats, String departure, String arrival, LocalDateTime depart) {
		 this.id = id;
		 this.passengers = people;
		 this.seatNumbers = seats;
		 this.origin = departure;
		 this.destination = arrival;
		 this.dateOfDeparture = depart;
		 
		 for(int i = 0; i < passengers.size(); i++) {
			 if(this.customer != null && i == 0) {
				 String customerNumber = "" + this.customer.getAccountNumber();
				 this.flight.setpassenger(this.seatNumbers.get(i).intValue(), customerNumber, this.customer.getName());
			 }else
				 this.flight.setpassenger(this.seatNumbers.get(i).intValue(), null, this.passengers.get(i));
		 }
		 
		 return;
	 }
	 
	 /**
	  * Method returning unique reservation ID.
	  */
	 public int getID() {
		 return this.id;
	 }
	 
	 /**
	  * Method returning total pricing of reservation.
	  */
	 public BigDecimal getTotalPrice() {
		 return this.totalPrice;
		 
	 }
	 
	 
	 /**
	  * method that gives the total price of the reservation using the price per ticket of the flight
	  * and the number of passengers that was given
	  * @param p is the number of passengers for the reservation
	  * @return returns the total price that was calculated by the method
	  */
	 
	 public void setTotalPrice() {
		 
		 for(int i = 0; i < this.passengers.size(); i++) {
			 this.totalPrice = this.totalPrice.add(this.flight.getpricing());
		 }
		 
		 return;
		 
	 }
	 
	 /**
	  * This method allows the user to change the flight that is being reserved
	  * and allows recalculates the total price for the new flight
	  * @param f is the new flight that is going to be added to the reservation
	  */
	 
	 public void setFlight(Flight f) {
		 this.flight = f;
		 this.setTotalPrice();
	 }
	 
	 public Flight getFlight() {
		 return this.flight;
	 }
	 
	 public void addPassenger(String p) {
		 
	 }
	 
	 /**
	  * Method returning LocalDateTime at booking.
	  */
	 public LocalDateTime getDateTimeAtBooking() {
		 return this.dateOfDeparture;
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
				 this.flight.setpassenger(this.seatNumbers.get(i), null, null);
				 this.totalPrice = this.totalPrice.add(flight.getpricing().negate());
				 exist = true;
			 }
		 }
		 
		 if(exist)
			 System.out.println(p + " has been removed from the passenger list\n");
		 else
			 System.out.println("Sorry but we could not find that passenger on the list\n");
	 }
	 
	 /**
	  * this method prints all the set values for the reservation so that the customer is notified of their reservation
	  */
	 public void getReservation() {
		 System.out.println("Type of flight: " + this.flight.gettype());
		 System.out.println("Destination: " + this.flight.getcityArrival());
		 System.out.println("City of Departure: " + this.flight.getcityDeparture());
		 System.out.println("Date and time of departure: " + this.flight.getdateDeparture()
				 + " " + this.flight.gettimeDeparture());
		 
		 System.out.println("Number of Passengers(including customer): " + this.passengers.size());
		 
		 System.out.println("List of Passengers: \n");
		 for(int i = 0; i < this.passengers.size(); i++) {
			 System.out.println("\t" + this.passengers.get(i));
		 }
		 
		 System.out.println("Total cost of reservation: " + this.totalPrice);
		 
		 return;
	 }
	 
	@Override
    public String toString() {
        return this.flight.getcityDeparture() + " to "  + this.flight.getcityArrival() + 
        ", DEPARTS " + this.flight.getdateDeparture() + " " + this.flight.gettimeDeparture() +
        ", ARRIVES " + this.flight.getDateArrival() + " " + this.flight.getTimeArrival();
    }
	 
	
}
