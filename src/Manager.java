/**
 * A class that represents a managet with the ability to store employee ID
 * and password as well as access and manage reservation information. 
 * @author
 * @version
 */

import java.util.ArrayList;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

class Manager {
	 
	private int employeeID;   /** stores the employee's ID */
	private String employeepassword; /** Stores the employee password */
	private ArrayList<Reservation> reservations; /** Stores a list of reservations */
	private FlightSorting sorted;
	private Account customer;
	private Flight flight;
	private ArrayList<Flight> flightList;
	private String filePath = "src/Database/Resrvation.txt";
	

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

public ArrayList<String> totalReservations(){
	ArrayList<String> reservationList = new ArrayList<String>();
	
	try(BufferedReader in = new BufferedReader(new FileReader(this.filePath))){
		while(in.ready()) {
			reservationList.add(in.readLine());
		}
	}catch(IOException e){
		return null;
	}
	
	return reservationList;
}

public void setResrvations() {
	try(BufferedReader in = new BufferedReader(new FileReader(this.filePath))){
		Reservation curr = new Reservation();
		this.reservations = new ArrayList<Reservation>();
		
		
	}catch(IOException e) {
		return;
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
