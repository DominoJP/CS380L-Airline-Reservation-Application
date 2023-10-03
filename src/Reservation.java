
import java.util.Scanner;

public class Reservation {
	private Account customer;
	private Flight flight;
	private String[] passengers;
	private int[] seatNumbers;
	
	public Reservation() {
		name = null;
		flight = null;
	}
	
	 public Reservation(Account n, Flight f) {
		 this.custeomer = n;
		 this.flight = f;
		 
	 }
	 
	 public void setReservation() {
		 Scanner scan = new Scanner(System.in);
		 int numPassengers;
		 int seat;
		 
		 System.out.print("How many other people is going one this trip (include yourself in the total): ");
		 numPassengers = Integer.parseInt(scan.nextLine());
		 System.out.println("");
		 
		 this.passengers = new String[numPassengers];
		 this.passengers[0] = this.customer.name;
		 
		 for(int i = 1; i <= numPassengers; i++) {
			 System.out.print("What is the name of this passenger: ");
			 passengers[i] = scan.nextLine();
			 System.out.println("");
		 }
		 
		 for (int i = 1; i <= numPassengers; i++) {
			 if(i == 1) {
				 System.out.print("What seat would you like: ");
				 seat = Integer.parseInt(scan.nextLine());
				 System.out.println("");
				 if(seat >= flight.totalPassengerCapacity || flight.passengers[seat][1] != null) {
					 System.out.println("Sorry but that is not an available seat");
					 i--;
				 }else {
					 flight.passengers[seat][1] = this.customer.name;
					 flight.passengers[seat][2] = this.customer.accountnumber;
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
		 
		 return;
	 }
	 
	 public void getReservation() {
		 
		 return;
	 }
	 
	 public void modifyReservation() {
		 
		 
		 return;
	 }
	
}
