import java.util.ArrayList;
import java.util.List;

/**
   Account class represents a user account for a flight reservation system. 
   It stores information such as user's name, email, password, account number
   and reservation history. 
   @author 
   @version 
 */
public class Account{
	 private String name;
	 private String email;
	 private String password;
	 private int accountNumber;
	 private List<Reservation> reservationHistory;
	 
	
	 /**
	  * Constructor for Account class 
	  * @param name : the name of account holder
	  * @param email : the email associated with the account.
	  * @param password : the password for the account.
	  * @param accountNumber : the account number
	  */
	 
	 public Account(String name, String email, String password, int accountNumber) {
		 this.name = name;
		 this.email = email;
		 this.setPassword(password);
		 this.accountNumber = accountNumber;
		 this.reservationHistory = new ArrayList<>();
	 }
	 

	 /**
	  * Getter method to retrieve account holder's name
	  * @return : the name of the account holder. 
	  */
	 public String getName() {
		 return name;
		 
	 }
	 
	 /**
	  * Getter method to retrieve email associated with account. 
	  * @return : the email associated with the account.
	  */
	 public String getEmail() {
		 return email;
	 }
	 
	 /**
	  * Getter method to retrieve the account number. 
	  * @return : account number
	  */
	 public int getAccountNumber() {
		 return accountNumber;
	 }
	 
	 /**
	  * Setter method to set account number
	  * @param accountNumber : new account number to be set. 
	  */
	 public void setaccountNumber(int accountNumber) {
		 this.accountNumber = accountNumber;

	 }
	 
	 /**
	  * Boolean Method to check if provided password matches the account's password.
	  * @param input password : password to be checked.
	  * @return : returns true if the input password matches. 
	  */
	 public boolean checkpassword(String inputpassword) {
		 return inputpassword.equals(password);
		 
	   }
	 
	 /**
	  * Method to add a reservation to the customer's reservation history.
	  * @param reservation : reservation to be added. 
	  */
	 public void addReservationHistory(Reservation reservation) {
		reservationHistory.add(reservation);
	 }
	 
	 /**
	  * Method to retrieve customer's reservation history.
	  * @return The list of reservation made by the customer. 
	  */
	 public List<Reservation> getReservationHistory() {
		return reservationHistory;
	 }

	 /**
	  * Getter method to retrieve the account's password.
	  * @return : the account's password
	  */
	 public String getPassword() {
		return password;
	 }

	 /**
	  * Setter method to set the account's password.
	  * @param password : new password to be set. 
	  */
	 public void setPassword(String password) {
		this.password = password;
	 }

	 
	 
	 /**
	  * Method to reserve/make a flight reservation for the user. 
	  * @param flight : flight to be reserved.
	  * @param passengers : list of passengers for reservation.
	  */
	 public void reserve(Flight flight, List<Passenger> passengers) {
	 //Implementation to make a reservation flight 
	 if(flight.isFull()) {
		 System.out.println("Sorry, the flight is full!");
	 }
	 else {
		 Reservation reservation = new Reservation(this, flight);
		 reservationHistory.add(reservation);
		 System.out.println("Reservation sucessfully!");
	 }
 }
 

	/**
	 * Method to cancel reservation for the user. 
	 * @param reservation : reservation to be canceled. 
	 */
    public void cancelReservation(Reservation reservation) {
    	
    	if(reservation == null) {
		 System.out.println("Reservation cannot be cancel!");
	 }
	 else {
		 reservationHistory.remove(reservation);
		 System.out.println("Cancellation sucessful!");
	       }
    	}
 
  
    /**
     * Method to change a reservation to a new flight. 
     * @param reservation : reservation to  be changed. 
     * @param new flight : new flight for the reservation.
     */
     public void changeReservation(Reservation reservation, Flight newflight){
	 
      if(reservation != null & newflight != null) {
		 reservation.setFlight(newflight);
		 System.out.println("Reservation changed successfully!");
	 }
	 else {
		 System.out.println("Invalid reservation. Reservation is not to be changed!");
	  }
   }
 
     /**
      * Method to review flight details. 
      * @param flight : the flight for which details are to be reviewed. 
      */
     public void reviewFlightDetails(Flight flight) {

    	 if(flight != null) {
		  System.out.println("Flight Details: ");
		  System.out.println("Type of flight: " + flight.gettype());
		  System.out.println("Departure cty: " + flight.getcityDeparture());
		  System.out.println("Arrival city: " + flight.getcityArrival());
		  System.out.println("Departure date: " + flight.getdateDeparture());
		  System.out.println("Arrival date: " + flight.getTimeArrival());
		  System.out.println("Total Passenger Caapcity: " + flight.gettotalpassengercapacity());
		  System.out.println("Passengers on board: " + flight.getPassenger());
		  System.out.println("Price of flight: " + flight.getpricing());

	 }
	 else {
		 System.out.println("Invalid flight. You cannot review details of flight");

	 }
	 
	 
 }
 
 
 
}
 