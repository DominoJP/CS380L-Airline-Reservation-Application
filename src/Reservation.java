
import java.util.Scanner;

public class Reservation {
	private Account customer; //whoever has the account and is making the reservation
	private Flight flight;
	private String[] passengers; //total list of passengers in case if the reservation includes more than just the customer
	private int[] seatNumbers; //an array containing the list of chosen seat numbers for the flight
	private double totalPrice; //a double that keeps track of the total cost of this reservation since multiple tickets may be ordered
	
	//a constructor that accepts nothing
	public Reservation() {
		this.customer = null;
		this.flight = null;
	}
	
	//a constructor that adds the customer making the reservation and the flight the reservation applies to
	 public Reservation(Account n, Flight f) {
		 this.customer = n;
		 this.flight = f;
		 
		 this.setReservation();
	 }
	 
	 //the method that sets all other values for the reservation
	 public void setReservation() {
		 Scanner scan = new Scanner(System.in);
		 int numPassengers;
		 int seat;
		 
		 System.out.print("How many other people is going one this trip (include yourself in the total): ");
		 numPassengers = Integer.parseInt(scan.nextLine());
		 System.out.println("");
		 
		 this.passengers = new String[numPassengers];
		 this.passengers[0] = this.customer.getName();
		 
		 for(int i = 1; i <= numPassengers; i++) {
			 System.out.print("What is the name of this passenger: ");
			 passengers[i] = scan.nextLine();
			 System.out.println("");
		 }
		 
		 //a for loop that is meant to allow each passenger to select what seat they want from what seats are still available on the flight
		 for (int i = 1; i <= numPassengers; i++) {
			 if(i == 1) { //I thought it might be good to give a different prompt to the customer making the reservation from the other passengers
				 System.out.print("What seat would you like: ");
				 seat = Integer.parseInt(scan.nextLine());
				 System.out.println("");
				 if(seat >= this.flight.gettotalpassengercapacity() || this.flight.getPassenger(seat) != null) {
					 System.out.println("Sorry but that is not an available seat");
					 i--;
				 }else {
					 this.flight.setpassenger(seat, this.customer.getAccountNumber(), this.passengers[i-1]);
					 seatNumbers[i-1] = seat;
					 
				 }
					 
			 }else {
				 System.out.print("What seat would " + this.passengers[i-1] + " like: ");
				 seat = Integer.parseInt(scan.nextLine());
				 System.out.println("");
				 if(seat >= this.flight.gettotalpassengercapacity() || this.flight.getPassenger(seat) != null) {
					 System.out.println("Sorry but that is not an available seat");
					 i--;
				 }else {
					 this.flight.setpassenger(seat, null, this.passengers[i-1]);
				 }
			 }
		 }
		 
		 //print out the total price where the total is the price per ticket for the flight multiplied by the number of passengers on the reservation
		 System.out.println("The total for this reservation is: " + this.setTotalPrice(numPassengers));
		 
		 return;
	 }
	 
	 public double setTotalPrice(int p) {
		 for(int i = 0; i < p; i++) {
			 this.totalPrice = this.totalPrice + this.flight.getpricing();
		 }
		 
		 return this.totalPrice;
	 }
	 
	 public void setFlight(Flight f) {
		 this.flight = f;
	 }
	 
	 public void removePassenger(String p) {
		 boolean exist = false;
		 
		 for(int i = 0; i < this.passengers.length; i++) {
			 if(this.passengers[i] == p) {
				 this.passengers[i] = null;
				 this.flight.setpassenger(this.seatNumbers[i], null, null);
				 this.totalPrice = this.totalPrice - this.flight.getpricing();
				 exist = true;
			 }
		 }
		 
		 if(exist)
			 System.out.println(p + " has been removed from the passenger list\n");
		 else
			 System.out.println("Sorry but we could not find that passenger on the list\n");
	 }
	 
	 //prints all the set values for the reservation so that the customer is notified of their reservation
	 public void getReservation() {
		 System.out.println("Type of flight: " + this.flight.gettype());
		 System.out.println("Destination: " + this.flight.getcityArrival());
		 System.out.println("City of Departure: " + this.flight.getcityDeparture());
		 System.out.println("Date and time of departure: " + this.flight.getdateDeparture()
				 + " " + this.flight.gettimeDeparture());
		 
		 System.out.println("Number of Passengers(including customer): " + this.passengers.length);
		 
		 System.out.println("List of Passengers: \n");
		 for(int i = 0; i < this.passengers.length; i++) {
			 System.out.println("\t" + this.passengers[i]);
		 }
		 
		 System.out.println("Total cost of reservation: " + this.totalPrice);
		 
		 return;
	 }
	 
	
}
