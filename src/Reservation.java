
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
		 this.passengers[0] = customer.getname();
		 
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
				 if(seat >= flight.totalPassengerCapacity || flight.passengers[seat][1] != null) {
					 System.out.println("Sorry but that is not an available seat");
					 i--;
				 }else {
					 flight.passengers[seat][1] = this.customer.getname();
					 flight.passengers[seat][2] = this.customer.accountnumber();
					 seatNumbers[i-1] = seat;
					 
				 }
					 
			 }else {
				 System.out.print("What seat would " + this.passengers[i-1] + " like: ");
				 seat = Integer.parseInt(scan.nextLine());
				 System.out.println("");
				 if(seat >= flight.totalPassengerCapacity || flight.passengers[seat][1] != null) {
					 System.out.println("Sorry but that is not an available seat");
					 i--;
				 }else {
					 flight.passengers[seat][1] = this.passengers[i-1];
				 }
			 }
		 }
		 
		 //print out the total price where the total is the price per ticket for the flight multiplied by the number of passengers on the reservation
		 System.out.println("The total for this reservation is: " + this.setTotalPrice(numPassengers));
		 
		 return;
	 }
	 
	 public double setTotalPrice(int p) {
		 for(int i = 0; i < p; i++) {
			 this.totalPrice = this.totalPrice + this.flight.pricing();
		 }
		 
		 return this.totalPrice;
	 }
	 
	 //prints all the set values for the reservation so that the customer is notified of their reservation
	 public void getReservation() {
		 System.out.println("Type of flight: " + this.flight.gettype());
		 System.out.println("Destination: " + this.flight.cityArrival());
		 System.out.println("City of Departure: " + this.flight.getcityDeparture());
		 System.out.println("Date and time of departure: " + this.flight.dateDeparture()
				 + " " + this.flight.timeDeparture());
		 
		 System.out.println("Number of Passengers(including customer): " + this.passengers.length);
		 
		 System.out.println("List of Passengers: \n");
		 for(int i = 0; i < this.passengers.length; i++) {
			 System.out.println("\t" + this.passengers[i]);
		 }
		 
		 System.out.println("Total cost of reservation: " + this.totalPrice);
		 
		 return;
	 }
	 
	 public void modifyReservation() {
		 
		 
		 return;
	 }
	
}
