import java.util.ArrayList;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;


/**
 * A class that represents a manager with the ability to store employee ID
 * and password as well as access and manage reservation information.
 * @author Sayra Reyes and Logan Langewisch
 * @version 2.0, last updated November 21, 2023
 */

public class Manager {
	 
	private int employeeID;   /** stores the employee's ID */
	private String employeepassword; /** Stores the employee password */
	private String employeeEmail;
	private String employeeUsername;
	private String employeePhoneNumber;
	
	private ArrayList<Reservation> reservations; /** Stores a list of reservations */

	private FlightSorting sorted;
	private Account customer;
	private Flight flight;
	private ArrayList<Flight> flightList;
	private String reservationPath = "Database/Reservations.txt";
	private String flightPath = "Database/Flights.txt";
	private FlightIO finder;
	private ArrayList<Account> accounts;

/**
 * Constructor that creates a manager instance with the specified employee ID and password.
 * It also initializes this instance of the Manager class with an ArrayList<Reservation>
 * @param int employeeID
 * @param String employeepassword
 * @param ArrayList<Reservation> reservations 
 */


	public Manager(int employeeID, String employeepassword) {
		this.employeeID = employeeID;
		this.employeepassword = employeepassword;
		this.sorted = null;
		this.reservations = null;
		this.customer = null;
		this.flight = null;
		this.flightList = null;
	}
	
	public Manager(String employeeEmail, String employeepassword) {
		this.employeeID = -1;
		this.employeepassword = employeepassword;
		this.employeeEmail = employeeEmail;
		this.sorted = null;
		this.reservations = null;
		this.customer = null;
		this.flight = null;
		this.flightList = null;
	}
	
	public Manager(int employeeID, String employeepassword, ArrayList<Reservation> reservations) {

	 this.employeeID = employeeID;
	 this.employeepassword = employeepassword;
	 this.sorted = null;
	 this.reservations = null;
	 this.customer = null;
	 this.flight = null;
	 this.flightList = null;
	 
	 this.reservations = reservations;
	 this.accounts = new ArrayList<Account>();
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
		this.reservations = new ArrayList<Reservation>();
		
		int reservationID = -1;
		int accountID = -1;
		int flightID = -1;
		ArrayList<String> people = new ArrayList<String>();
		String type = null;
		BigDecimal price = new BigDecimal(0);
		ZonedDateTime current = null;
		
		try(BufferedReader in = new BufferedReader(new FileReader(this.reservationPath))){
			while(in.ready()) {
				String line = in.readLine();
				if(!line.equals(null)) {
					String[] r = line.split(": ");
					
					switch(r[0]){
					case "Reservation ID":
						reservationID = Integer.parseInt(r[1]);
						break;
					case "Account ID":
						accountID = Integer.parseInt(r[1]);
						break;
					case "Flight Number":
						flightID = Integer.parseInt(r[1]);
						break;
					case "Date of Booking":
						current = ZonedDateTime.parse(r[1]);
						break;
					case "Total Pricing":
						price = BigDecimal.valueOf(Double.parseDouble(r[1]));
						break;
					case "Cabin Class":
						type = r[1];
						break;
					case "\tPassenger Name":
						people.add(r[1]);
						break;
					case "--Reservation End--":
						if(reservationID != -1) {
							Reservation store = new Reservation();
							store.setId(reservationID);
							store.setCustomerId(accountID);
							store.setFlightId(flightID);
							store.setBooking(current);
							store.setTotalPrice(price);
							store.setCabin(type);
							
							this.reservations.add(store);
							break;
						}else
							break;
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
	 * The getAllAccounts() method uses the AccountIO class to set the ArrayList of accounts to all accounts stored
	 * in the system
	 */
	
	public void getAllAccounts() {
		AccountIO data = new AccountIO();
		
		data.readAccounts();
		
		this.accounts = data.getAccounts();
	}
	
	/**
	 * The getTotalRevenue method takes the ArrayList<Reservation> reservations and calculates the total revenue made from all reservations that are stored
	 * @return BigDecimal
	 */
	
	public BigDecimal getTotalRevenue() {
		BigDecimal revenue = new BigDecimal(0);
		
		for(int i = 0; i < this.reservations.size(); i++) {
			 revenue = revenue.add(this.reservations.get(i).getTotalPrice());
		}
		
		return revenue;
	}
	
	/**
	 * the getAccountRevenue() method allows a manager to calculate the total revenue made by one account that is searched
	 * for using @param name, an instance of a String that represents the name of a customer being searched for,
	 * and @return a BigDecimal that represents the total amount of money made by that customer
	 */
	
	public BigDecimal getAccountRevenue(int id) {
		BigDecimal revenue = new BigDecimal(0);
		
		for(int i = 0; i < accounts.size(); i++) {
			if(accounts.get(i).getAccountNumber() == id) {
				revenue = accounts.get(i).totalBalance();
				return revenue;
			}
		}
				
		return revenue;
	}
	
	/*
	 * This getRevenue method is similar to the getTotalRevenue method except it restricts what revenue made from the reservations depending on @param start
	 * and @param end, instances of LocalDateTime, which are dates that will be compared to each reservation on the list to determine revenue made within a certain
	 * time frame
	 * Currently not in use
	 * 
	 */
	public BigDecimal getPartialRevenue(ZonedDateTime start, ZonedDateTime end) {
		BigDecimal revenue = new BigDecimal(0);
		ZonedDateTime current;
		
		for(int i = 0; i < this.reservations.size(); i++) {
			current = this.reservations.get(i).getDateTimeAtBooking();
			
			if(current.equals(start) || current.equals(end)) {
				revenue = revenue.add(this.reservations.get(i).getTotalPrice());
			}else if(current.isAfter(start) && current.isBefore(end)) {
				revenue = revenue.add(this.reservations.get(i).getTotalPrice());
			}
		}
		
		return revenue;
	}
	
	/**
     * Compares user inputs for email and password against email-password pairs stored in .txt.
     * @param email
     * @param password
     * @return whether sign in successful
     */
    public static boolean signIn(String email, char[] password) {
   	 final String FILE_PATH = "src/Database/Managers.txt";
   	 try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			 String line;
			 while ((line = reader.readLine()) != null) {
				 String[] parts = line.split(", ");
				 // compare email-password pairs
				 if (parts[0].equals(email) && parts[1].equals(String.valueOf(password))) {
					 reader.close();
					 return true;
				 }
           }
           reader.close();
	    } catch (IOException e) {
	    	e.printStackTrace();
       }
		return false;
    }
 
}