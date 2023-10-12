/**
 * A class that represents a managet with the ability to store employee ID
 * and password as well as access and manage reservation information. 
 * @author
 * @version
 */

import java.util.ArrayList;
import java.util.List;


class Manager {
	 
	private int employeeID;   /** stores the employee's ID */
	private String employeepassword; /** Stores the employee password */
	private List<Reservation> reservations; /** Stores a list of reservations */
	

public void accessReservationInfo() {
	//need to be implemented. to be done.. 
	
  }

/**
 * Constructor that creates a manager instance with the specified employee ID and password
 * @param employeeID : unique ID
 * @param employeepassword : the password associated with the employee ID. 
 */
public Manager(int employeeID, String employeepassword, List<Reservation> reservations) {
	 this.employeeID = employeeID;
	 this.employeepassword = employeepassword;
	 this.setReservations(new ArrayList<Reservation>());
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
public List<Reservation> getReservations() {
	return reservations;
}

/**
 * Setter method to set reservations. 
 * @param reservations
 */
public void setReservations(List<Reservation> reservations) {
	this.reservations = reservations;
}
 
 

 
}
