/**
 * The Flight class represents a flight in a flight reservation such as 
 * the type of flight (round trip or one way), departure and arrival cities,
 * departure and arrival dates and times, 
 * total passenger capacity, pricing and passenger information. 
 *@author Sayra Reyes
 *@version 1.0 
 */

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
	
	private int id;
	private String type; 
	private String cityDeparture;   
	private String cityArrival;      
	private LocalDate dateDeparture;      
	private LocalTime timeDeparture;    
	private LocalDate dateArrival;     
	private LocalTime timeArrival;   
	private int economyCapacity;
	private int economyPassengerCount;
	private BigDecimal economyPricing;
	private int businessCapacity;
	private int businessPassengerCount;
	private BigDecimal businessPricing; 
	private int firstClassCapacity;
	private int firstClassPassengerCount;
	private BigDecimal firstClassPricing; 
	private String[][] passengers;  
	
	private PropertyChangeSupport support;

	/**
	 * Constructor for the flight class
	 * @param type
	 * @param cityDeparture
	 * @param cityArrival
	 * @param dateDeparture
	 * @param timeDeparture
	 * @param dateArrival
	 * @param timeArrival
	 * @param totalPassengerCapacity
	 * @param pricing
	 */
	@Deprecated
	public Flight(String type, String cityDeparture, String cityArrival, String dateDeparture,
			String timeDeparture, String dateArrival, String timeArrival, int totalPassengerCapacity, BigDecimal pricing) {
		
	this.type = type; 
	this.cityDeparture = cityDeparture;
	this.cityArrival = cityArrival;
	this.dateDeparture = LocalDate.parse(dateDeparture);
	this.timeDeparture = LocalTime.parse(timeDeparture); 
	this.dateArrival = LocalDate.parse(dateArrival);
	this.timeArrival = LocalTime.parse(timeArrival);
	this.totalPassengerCapacity = totalPassengerCapacity;
	this.pricing = pricing; 
	passengers = new String[totalPassengerCapacity][2];
	
	support = new PropertyChangeSupport(this);
	}
	
	/**
	  Constructor for the flight class
	  @param id
	  @param type
	  @param cityDeparture
	  @param cityArrival
	  @param dateDeparture
	  @param timeDeparture
	  @param dateArrival
	  @param timeArrival
	  @param totalPassengerCapacity
	  @param passengerCount
	  @param pricing
	 */
	
	public Flight(int id, String type, String cityDeparture, String cityArrival, String dateDeparture,
				  String timeDeparture, String dateArrival, String timeArrival, 
				  int totalPassengerCapacity, int passengerCount, BigDecimal pricing) {
		this.id = id;
		this.type = type; 
		this.cityDeparture = cityDeparture;
		this.cityArrival = cityArrival;
		this.dateDeparture = LocalDate.parse(dateDeparture);
		this.timeDeparture = LocalTime.parse(timeDeparture); 
		this.dateArrival = LocalDate.parse(dateArrival);
		this.timeArrival = LocalTime.parse(timeArrival);
		this.economyCapacity = totalPassengerCapacity;
		this.economyPassengerCount = passengerCount;
		this.pricing = pricing; 
		passengers = new String[totalPassengerCapacity][2];
		
		support = new PropertyChangeSupport(this);
	}
	
	
	/**
	 * Method that fires PropertyChange event when "assigning" to a Flight Object. For use with FlightFilterList.
	 * @param selectedFlight
	 */	
	public void assign(Flight selectedFlight) {
		support.firePropertyChange("selectedFlight", this, selectedFlight);
		this.id = selectedFlight.getID();
		this.type = selectedFlight.gettype();
		this.cityDeparture = selectedFlight.getcityDeparture();
		this.cityArrival = selectedFlight.getcityArrival();
		this.dateDeparture = selectedFlight.getdateDeparture();
		this.timeDeparture = selectedFlight.gettimeDeparture();
		this.dateArrival = selectedFlight.getDateArrival();
		this.timeArrival = selectedFlight.getTimeArrival();
		this.totalPassengerCapacity = selectedFlight.gettotalpassengercapacity();
		this.passengerCount = selectedFlight.getPassengerCount();
		this.pricing = selectedFlight.getpricing();
		// FIXME: update as necessary
		
	}
	
	/**
	 * Getter method to retrieve the flight id
	 * @return : returns the id of flight. 
	 */
	public int getID(){
		return this.id;
	}
	
	/**
	 * Getter method to retrieve the flight type (round trip or one way) 
	 * @return : returns the type of flight. 
	 */
	public String gettype(){
		return type;
	}
	
	/**
	 * Getter method to retrieve the departure city (and/or airport)
	 * @return : returns the departure city
	 */
	public String getcityDeparture() {
		return cityDeparture;
	}
	
	/**
	 * Getter method to retrieve arrival city. 
	 * @return : returns the city of arrival
	 */
	public String getcityArrival() {
		return cityArrival;
	}
	
	/**
	 * Getter method to retrieve the date of departure.
	 * @return : returns the date of departure 
	 */
	public LocalDate getdateDeparture() {
		return dateDeparture;
	}
	
	/**
	 * Getter method to retrieve the time of departure.
	 * @return returns the time of departure 
	 */
	public LocalTime gettimeDeparture() {
		return timeDeparture;
	}
	
	/**
	 * Getter method to retrieve the total remaining pasenger capacity
	 * @return ; returns the total remaining passenger capacity
	 */
	public int gettotalremainingpassengercapacity() {
		int available = 0; 
		for(int i = 0; i < totalPassengerCapacity; i++) {
			if(passengers[i][1] == null ) {
				available++;
			}
		}
		return available;
	}
	
	
    /**
     * Getter method to retrieve the pricing of the flight. 
     * @return : returns the pricing of the flight. 
     */
	public BigDecimal getpricing() {
		return pricing;
	}

	/**
	 * Getter method to retrieve passenger information at a specified location. 
	 * @param location : the location for setting passenger information. 
	 * @return 
	 */
	public String getPassenger(int location) {
		return passengers[location][1];
	}

	/**
	 * Getter method to return the date of the arrival
	 * @return
	 */
	public LocalDate getDateArrival() {
		return dateArrival;
	}

	/**
	 * Setter to set the date of the arrival 
	 * @param dateArrival
	 */
	public void setDateArrival(String dateArrival) {
		this.dateArrival = LocalDate.parse(dateArrival);
	}

	/**
	 * Getter to get the time of the arrival
	 * @return
	 */
	public LocalTime getTimeArrival() {
		return timeArrival;
	}

	/**
	 * Setter to get the time of the arrival
	 * @param timeArrival
	 */
	public void setTimeArrival(String timeArrival) {
		this.timeArrival = LocalTime.parse(timeArrival);
	}

	/**
	 * Getter method to retrieve the total passenger capacity of the flight.
	 * @return ; returns the total passenger capacity of the flight. 
	 */
	@Deprecated
	public int gettotalpassengercapacity() {
		return totalPassengerCapacity;
		
	}
	
	/**
	 * Getter method to retrieve the passenger count booked for the flight.
	 * @return returns count of booked passengers
	 */
	@Deprecated
	public int getPassengerCount() {
		return passengerCount;
	}
	
	/**
	 * Returns total amount of seats in 'Economy' cabin
	 * @return seating total in Economy
	 */
	public int getEconomyCapacity() {
		return this.economyCapacity;
	}
	
	/**
	 * Returns total amount of seats in 'Business' cabin
	 * @return seating total in Business
	 */
	public int getBusinessCapacity() {
		return this.businessCapacity;
	}
	
	/**
	 * Returns total amount of seats in 'First Class' cabin
	 * @return seating total in First Class
	 */
	public int getFirstClassCapacity() {
		return this.firstClassCapacity;
	}
	
	/**
	 * Returns booked passenger count in 'Economy' cabin
	 * @return booked seat count in Economy
	 */
	public int getEconomyPassengerCount() {
		return this.economyPassengerCount;
	}
	
	/**
	 * Returns booked passenger count in 'Business' cabin
	 * @return booked seat count in Business
	 */
	public int getBusinessPassengerCount() {
		return this.businessPassengerCount;
	}
	
	/**
	 * Returns booked passenger count in 'First Class' cabin
	 * @return booked seat count in First Class
	 */
	public int getFirstClassPassengerCount() {
		return this.firstClassPassengerCount;
	}
	
	/**
	 * Returns pricing for 'Economy' cabin
	 * @return pricing for Economy
	 */
	public BigDecimal getEconomyPricing() {
		return this.economyPricing;
	}
	
	/**
	 * Returns pricing for 'Business' cabin
	 * @return pricing for Business
	 */
	public BigDecimal getBusinessPricing() {
		return this.businessPricing;
	}
	
	/**
	 * Returns pricing for 'First Class' cabin
	 * @return pricing for First Class
	 */
	public BigDecimal getFirstClassPricing() {
		return this.firstClassPricing;
	}
	
	/**
	 * Method to check if the flight is full
	 * @return , returns true if the flight is full, otherwise false. 
	 */
	public boolean isFull() {
		return false;
	}
	
	/**
	 * Method to set the passenger information at a specified location. 
	 * @param location : the location for setting passenger information
	 * @param accountnumber : the account number of the passenger. 
	 * @param name : the name of the passenger. 
	 */
	public  void setpassenger(int location, String accountnumber, String name) {
		//if the statement checks whether location is valid seat on the flight and if
		// it is then it assigns a passenger and if the seat does not exist then it provided 
		//an error to the user. 
		if(location <= totalPassengerCapacity) {
			passengers[location][1] = name; 
			if(accountnumber != null) {
				passengers[location][2] = accountnumber;
			}
		}
		else {
			System.out.println("Sorry that is not a seat available on the flight");
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		 support.addPropertyChangeListener(pcl);
	}
	
	@Override
    public String toString() {
        return "Departs: " + timeDeparture +
        ", Arrives: " + dateArrival.getMonthValue() + "/" + dateArrival.getDayOfMonth() + " " + timeArrival +
        ", $" + pricing;
    }
	
}
