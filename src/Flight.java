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
import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
	
	private int id;
	private String type; 
	private String airportName;
	private String cityDeparture;   
	private String cityArrival;      
	private LocalDate dateDeparture;      
	private LocalTime timeDeparture;    
	private LocalDate dateArrival;     
	private LocalTime timeArrival;   
	private int totalPassengerCapacity;  
	private String[][] passengers; 
	private double pricing; 
	
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
	public Flight(int id, String type, String airportName, String cityDeparture, String cityArrival, String dateDeparture,
			String timeDeparture, String dateArrival, String timeArrival, int totalPassengerCapacity, double pricing) {
		this.id = id;
		this.type = type; 
		this.airportName = airportName;
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
	  @param pricing
	 */
	/*
	public Flight(int id, String type, String cityDeparture, String cityArrival, String dateDeparture,
				  String timeDeparture, String dateArrival, String timeArrival, int totalPassengerCapacity, double pricing) {
		this.id = id;
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
	*/
	
	
	/**
	 * Method that fires PropertyChange event when "assigning" to a Flight Object. For use with FlightFilterList.
	 * @param selectedFlight
	 */	
	public void assign(Flight selectedFlight) {
		support.firePropertyChange("selectedFlight", this, selectedFlight);
		this.id = selectedFlight.getID();
		this.type = selectedFlight.gettype();
		this.airportName = selectedFlight.getAirportName();
		this.cityDeparture = selectedFlight.getcityDeparture();
		this.cityArrival = selectedFlight.getcityArrival();
		this.dateDeparture = selectedFlight.getdateDeparture();
		this.timeDeparture = selectedFlight.gettimeDeparture();
		this.dateArrival = selectedFlight.getDateArrival();
		this.timeArrival = selectedFlight.getTimeArrival();
		this.totalPassengerCapacity = selectedFlight.gettotalpassengercapacity();
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
	
	public String getAirportName() {
		return airportName;
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
	public double getpricing() {
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
	public int gettotalpassengercapacity() {
		return totalPassengerCapacity;
		
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
	
}
