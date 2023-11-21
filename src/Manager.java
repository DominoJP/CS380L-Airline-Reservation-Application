/**
 * Owner: Sayra Reyes
 * Modified by Logan Langewisch
 * Date Last Modified: November 21, 2023
 * A class that represents a managet with the ability to store employee ID
 * and password as well as access and manage reservation information.
 */

import java.util.ArrayList;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.io.BufferedReader;


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

/**
 * Constructor that creates a manager instance with the specified employee ID and password.
 * It also initializes this instance of the Manager class with an ArrayList<Reservation>
 * @param int employeeID
 * @param String employeepassword
 * @param ArrayList<Reservation> reservations 
 */


	public Manager(int employeeID, String employeepassword, ArrayList<Reservation> reservations) {

	 this.employeeID = employeeID;
	 this.employeepassword = employeepassword;
	 this.sorted = null;
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
	* @return String
	*/
	public String getemployeepassword() {
		return employeepassword;
	}
	 
	/**
	 * Retrieves a list of reservations managed by the manager. 
	 * @return ArrayList<Reservation>
	 */
	public ArrayList<Reservation> getReservations() {
		return reservations;
	}
	
	/**
	 * Setter method to set reservations. 
	 * @param ArrayList<Reservation> reservations
	 */
	public void setReservations(ArrayList<Reservation> reservations) {
		this.reservations = reservations;
	}
	
	/**
	 * The accessReservationInfo() method sets the list of reservations currently viewed by the manager
	 * to the reservation history of the customer the manager is currently viewing
	 */
	
	public void accessReservationInfo() {
		this.reservations = customer.getReservationHistory();
	}
	
	/**
	 * The totalReservation class reads from the Reservations.txt file to create and ArrayList<Reservation> that can be used to create documentation
	 * on all reservations that have been saved.
	 * Each reservation read from the Reservations.txt file will then be added to the reservations variable
	 */
	
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
					case "Total Pricing:":
						price = new BigDecimal(Integer.parseInt(r[1]));
						break;
					case "Cabin Class:":
						type = r[1];
						break;
					case "--Reservation End--":
						Reservation store = new Reservation();
						store.setId(reservationID);
						store.setCustomerId(accountID);
						store.setFlightId(flightID);
						store.setBooking(current);
						store.setTotalPrice(price);
						store.setCabin(type);
						
						this.reservations.add(store);
					default:
					}
				}
			}
			in.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * The getTotalRevenue method takes the ArrayList<Reservation> reservations and calculates the total revenue made from all reservations that are stored
	 * @return BigDecimal
	 */
	
	public BigDecimal getTotalRevenue() {
		BigDecimal revenue = new BigDecimal(0);
		
		for(int i = 0; i < this.reservations.size(); i++) {
			revenue.add(this.reservations.get(i).getTotalPrice());
		}
		
		return revenue;
	}
	
	/*
	 * This getRevenue method is similar to the getTotalRevenue method except it restricts what revenue made from the reservations depending on @param start
	 * and @param end, instances of LocalDateTime, which are dates that will be compared to each reservation on the list to determine revenue made within a certain
	 * time frame
	 * Currently not in use
	 * 
	public BigDecimal getRevenue(LocalDateTime start, LocalDateTime end) {
		BigDecimal revenue = new BigDecimal(0);
		
		for(int i = 0; i < this.reservations.size(); i++) {
			LocalDateTime current = this.reservations.get(i).getDateTimeAtBooking();
			
			if(current.equals(start) || current.equals(end)) {
				revenue.add(this.reservations.get(i).getTotalPrice());
			}else if(current.isAfter(start) && current.isBefore(end)) {
				revenue.add(this.reservations.get(i).getTotalPrice());
			}
		}
		
		return revenue;
	}
	*/
 
}
