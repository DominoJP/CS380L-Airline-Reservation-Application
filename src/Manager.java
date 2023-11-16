/**
 * A class that represents a managet with the ability to store employee ID
 * and password as well as access and manage reservation information. 
 * @author
 * @version
 */

import java.util.ArrayList;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

class Manager {
	 
	private int employeeID;   /** stores the employee's ID */
	private String employeepassword; /** Stores the employee password */
	private ArrayList<Reservation> reservations; /** Stores a list of reservations */
	private FlightSorting sorted;
	private Account customer;
	private Flight flight;
	private ArrayList<Flight> flightList;
	private String reservationPath = "src/Resrvation.txt";
	private String flightPath = "src/FlightsTest.txt";
	private FlightIO finder;
	

public void accessReservationInfo() {
	this.reservations = customer.getReservationHistory();
  }

/**
 * Constructor that creates a manager instance with the specified employee ID and password
 * @param employeeID : unique ID
 * @param employeepassword : the password associated with the employee ID. 
 */
public Manager(int employeeID, String employeepassword, FlightSorting sortedFlights) {
	 this.employeeID = employeeID;
	 this.employeepassword = employeepassword;
	 this.sorted = sortedFlights;
	 this.reservations = null;
	 this.customer = null;
	 this.flight = null;
	 this.flightList = null;
   }
 
/**
 * Getter to get the employee ID 
 * @return; returns employee ID 
 */
 public int getemployeeID() {
	 return employeeID;
 }
 
 /**
  * Getter to get employee password 
  * @return: returns employee password. 
  */
 public String getemployeepassword() {
	 return employeepassword;
 }
 
 /*
 public void initialize() {
	 sorted = new FlightSorting();
	 
	 try(BufferedReader in = new BufferedReader(new FileReader(this.flightPath))){
		 String line;
		 while((line = in.readLine()) != null) {
			 if(line.length() > 0) {
			 	String[] description = line.split(", ");
			 	Flight newF = new Flight();
			 }
			 
		 }
		 
		 
	 }catch(IOException e) {
		 e.printStackTrace();
		 return;
	 }
 }
*/
/**
 * Retrieves a list of reservations managed by the manager. 
 * @return ; returns the list of reservations. 
 */
public ArrayList<Reservation> getReservations() {
	return reservations;
}

/**
 * Setter method to set reservations. 
 * @param reservations
 */
public void setReservations(ArrayList<Reservation> reservations) {
	this.reservations = reservations;
}

public void totalReservations(){	
	int reservationID = -1;
	int accountID = -1;
	int flightID = -1;
	ArrayList<String> people = new ArrayList<String>();
	String type = null;
	BigDecimal price = new BigDecimal(0);
	LocalDateTime current = null;
	
	try(BufferedReader in = new BufferedReader(new FileReader(this.reservationPath))){
		while(in.ready()) {
			String line = in.readLine();
			if(!line.equals(null)) {
				String[] r = line.split(": ");
				
				switch(r[0]){
				case "Reservation ID:":
					reservationID = Integer.parseInt(r[1]);
					break;
				case "Account ID:":
					accountID = Integer.parseInt(r[1]);
					break;
				case "Flight Number:":
					flightID = Integer.parseInt(r[1]);
					break;
				case "Date of Booking:":
					current = LocalDateTime.parse(r[1]);
					break;
				}
			}
		}
	}catch(IOException e){
		e.printStackTrace();
	}
}
/**
public void createFlight(int id, String type, String cityDeparture, String cityArrival, String dateDeparture,
							String timeDeparture, String dateArrival, String timeArrival, int totalPassengerCapacity, int passengerCount, BigDecimal pricing) {
	
	Flight newFlight = new Flight(id, type, cityDeparture, cityArrival, dateDeparture, timeDeparture, dateArrival, timeArrival, totalPassengerCapacity, 
			passengerCount, pricing);
	this.sorted.addFlight(newFlight);
	
}

public Flight modifyFlightSchedule(Flight f, String departDate, String departTime, String arriveDate, String arriveTime) {
	f.setDateDeparture(departDate);
	f.setTimeDeparture(departTime);
	f.setDateArrival(arriveDate);
	f.setTimeArrival(arriveTime);
	
	return f;
}

public void manageCustomer() {
	
}

public void refund(BigDecimal amount) {
	this.customer.removeFromBalance(amount);
}
*/



 
}
