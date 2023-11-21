/**
 * a) Design documentation: "Flight" 
 * b) Date of creation: October 3, 2023
 * c) Programmer's name: Sayra (Original),  Logan Lagewisch (Modified).
 * d) Description: This class represents a flight in a flight reservation system. It encopasses flight details 
 * 	  such as ID, type, departure/arrival cities, date/times, passenger capacity, pricing and 
 * 	  methods to manage passenger booking. 
 * e) Functions: Methods like flight details, capacity, pricing, booking status and specify information. 
 * f) Data Structures: There are several used to manage and store information related to flights and passengers methods like: 
 * 	  Managing Booking, DataTime Handiling and Support for Listeners. 
 * g) Algorithm: N/A
 *
 */

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class Flight {
	
	private int id;
	private String type; 
	private String cityDeparture;   
	private String cityArrival;      
	private LocalDate dateDeparture;      
	private LocalTime timeDeparture;    
	private LocalDate dateArrival;     
	private LocalTime timeArrival;   
	private ZoneId zone;
	private ZonedDateTime dateTimeDeparture;
	private ZonedDateTime dateTimeArrival;
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
	/*
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
	
	// support = new PropertyChangeSupport(this);
	}
	*/
	
	/**
	 * Constructor.
	 * @param id
	 * @param type
	 * @param cityDeparture
	 * @param cityArrival
	 * @param dateDeparture
	 * @param timeDeparture
	 * @param dateArrival
	 * @param timeArrival
	 * @param economyCapacity
	 * @param economyPassengerCount
	 * @param economyPricing
	 * @param businessCapacity
	 * @param businessPassengerCount
	 * @param businessPricing
	 * @param firstClassCapacity
	 * @param firstClassPassengerCount
	 * @param firstClassPricing
	 */
	public Flight(int id, String type, String cityDeparture, String cityArrival, 
				  String dateDeparture, String timeDeparture, String dateArrival, String timeArrival, String zone) 
	{
		this.id = id;
		this.type = type; 
		this.cityDeparture = cityDeparture;
		this.cityArrival = cityArrival;
		
		this.dateDeparture = LocalDate.parse(dateDeparture);
		this.timeDeparture = LocalTime.parse(timeDeparture); 
		this.dateArrival = LocalDate.parse(dateArrival);
		this.timeArrival = LocalTime.parse(timeArrival);
		
		this.zone = ZoneId.of(zone);
		this.dateTimeDeparture = ZonedDateTime.of(this.dateDeparture, this.timeDeparture, ZonedDateTime.now().getZone()).withZoneSameInstant(this.zone);
		this.dateTimeArrival = ZonedDateTime.of(this.dateArrival, this.timeArrival, ZonedDateTime.now().getZone()).withZoneSameInstant(this.zone);
		
		this.economyCapacity = 0;
		this.economyPassengerCount = 0;
		this.economyPricing = new BigDecimal(0); 
		this.businessCapacity = 0;
		this.businessPassengerCount = 0;
		this.businessPricing = new BigDecimal(0);
		this.firstClassCapacity = 0;
		this.firstClassPassengerCount = 0;
		this.firstClassPricing = new BigDecimal(0);
		
		// passengers = new String[totalPassengerCapacity][2];
		
		support = new PropertyChangeSupport(this);
	}
	
	public void setEconomy(int capacity, BigDecimal price) {
		this.economyCapacity = capacity;
		this.economyPricing = price;
	}
	
	public void setBusiness(int capacity, BigDecimal price) {
		this.businessCapacity = capacity;
		this.businessPricing = price;
	}
	
	public void setFirstClass(int capacity, BigDecimal price) {
		this.firstClassCapacity = capacity;
		this.firstClassPricing = price;
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
		this.zone = selectedFlight.getZone();
		// ZonedDateTime
		// ZonedDateTime
		// this.totalPassengerCapacity = selectedFlight.gettotalpassengercapacity();
		// this.passengerCount = selectedFlight.getPassengerCount();
		// this.pricing = selectedFlight.getpricing();
		this.economyCapacity = selectedFlight.getEconomyCapacity();
		this.economyPassengerCount = selectedFlight.getEconomyPassengerCount();
		this.economyPricing = selectedFlight.getEconomyPricing();
		this.businessCapacity = selectedFlight.getBusinessCapacity();
		this.businessPassengerCount = selectedFlight.getBusinessPassengerCount();
		this.businessPricing = selectedFlight.getBusinessPricing();
		this.firstClassCapacity = selectedFlight.getFirstClassCapacity();
		this.firstClassPassengerCount = selectedFlight.getFirstClassPassengerCount();
		this.firstClassPricing = selectedFlight.getFirstClassPricing();
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
	 * Setter method to set the date of departure for a flight using @param depart
	 */
	
	public void setDateDeparture(String depart) {
		this.dateDeparture = LocalDate.parse(depart);
	}
	
	/**
	 * Setter method to set the time of departure for a flight using @param depart
	 */
	
	public void setTimeDeparture(String depart) {
		this.timeDeparture = LocalTime.parse(depart);
	}
	
	/**
	 * Getter method to retrieve the total remaining pasenger capacity
	 * @return ; returns the total remaining passenger capacity
	 */
	@Deprecated
	public int gettotalremainingpassengercapacity() {
		int available = 0; 
		for(int i = 0; i < gettotalpassengercapacity(); i++) {
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
	@Deprecated
	public BigDecimal getpricing() {
		return getpricing();
	}

	/**
	 * Getter method to retrieve passenger information at a specified location. 
	 * @param location : the location for setting passenger information. 
	 * @return 
	 */
	@Deprecated
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
	 * Returns time zone of LocalDate departure & arrival, LocalTime departure & arrival
	 * @return time zone of departure
	 */
	public ZoneId getZone() {
		return this.zone;
	}
	
	/**
	 * Returns ZonedDateTime from LocalDate of departure, LocalTime of departure, and zone of machine compared against zone of departure.
	 * @return ZonedDateTime of departure
	 */
	public ZonedDateTime getZonedDateTimeDeparture() {
		return this.dateTimeDeparture;
	}
	
	/**
	 * Returns ZonedDateTime from LocalDate of arrival, LocalTime of arrival, and zone of machine  compared against zone of departure.
	 * @return ZonedDateTime of departure
	 */
	public ZonedDateTime getZonedDateTimeArrival() {
		return this.dateTimeArrival;
	}

	/**
	 * Getter method to retrieve the total passenger capacity of the flight.
	 * @return ; returns the total passenger capacity of the flight. 
	 */
	@Deprecated
	public int gettotalpassengercapacity() {
		return gettotalpassengercapacity();
		
	}
	
	/**
	 * Getter method to retrieve the passenger count booked for the flight.
	 * @return returns count of booked passengers
	 */
	@Deprecated
	public int getPassengerCount() {
		return getPassengerCount();
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
	 * Adds passenger amount booked.
	 * @param passenger amount as selected by customer
	 */
	public void addEconomyPassengerCount(int selectedPassengerCount) {
		if(!this.isFull("Economy") && (this.economyPassengerCount + selectedPassengerCount) <= this.economyCapacity) {
			economyPassengerCount += selectedPassengerCount;
		}
	}
	
	/**
	 * Adds passenger amount booked.
	 * @param passenger amount as selected by customer
	 */
	public void addBusinessPassengerCount(int selectedPassengerCount) {
		if(!this.isFull("Business") && (this.businessPassengerCount + selectedPassengerCount) <= this.businessCapacity) {
			businessPassengerCount += selectedPassengerCount;
		}
	}
	
	/**
	 * Adds passenger amount booked.
	 * @param passenger amount as selected by customer
	 */
	public void addFirstClassPassengerCount(int selectedPassengerCount) {
		if(!this.isFull("First Class") && (this.firstClassPassengerCount + selectedPassengerCount) <= this.firstClassCapacity) {
			firstClassPassengerCount += selectedPassengerCount;
		}
	}
	
	public void removeEconomyPassenger() {
		if(this.economyPassengerCount != 0)
			this.economyPassengerCount--;
	}
	
	public void removeBusinessPassenger() {
		if(this.businessPassengerCount != 0)
			this.businessPassengerCount--;
	}
	
	public void removeFirstClassPassenger() {
		if(this.firstClassPassengerCount != 0)
			this.firstClassPassengerCount--;
	}
	

	/**
	 * Method to check if the flight is full
	 * @return , returns true if the flight is full, otherwise false. 
	 */
	public boolean isFull(String type) {
		switch(type) {
		case "Economy":
			return this.economyCapacity == this.economyPassengerCount;
		case "Business":
			return this.businessCapacity == this.businessPassengerCount;
		case "First Class":
			return this.firstClassCapacity == this.firstClassPassengerCount;
		}
		
		return true;
	}
	
	
	/**
	 * Method to set the passenger information at a specified location. 
	 * @param location : the location for setting passenger information
	 * @param accountnumber : the account number of the passenger. 
	 * @param name : the name of the passenger. 
	 */
	@Deprecated
	public  void setpassenger(int location, String accountnumber, String name) {
		//if the statement checks whether location is valid seat on the flight and if
		// it is then it assigns a passenger and if the seat does not exist then it provided 
		//an error to the user. 
		if(location <= gettotalpassengercapacity()) {
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
		String departPeriod = "";
		int timeDepartureHour = dateTimeDeparture.getHour();
		if (timeDepartureHour < 12) {
			departPeriod = "AM";
		} else {
			departPeriod = "PM";
			timeDepartureHour -= 12;
			if (timeDepartureHour == 0)
				timeDepartureHour = 12;
		}
		
		String arrivePeriod = "";
		int timeArrivalHour = dateTimeArrival.getHour();
		if (timeArrivalHour < 12) {
			arrivePeriod = "AM";
		} else {
			arrivePeriod = "PM";
			timeArrivalHour -= 12;
			if (timeArrivalHour == 0)
				timeArrivalHour = 12;
		}
		
		return "DEPARTS: " + timeDepartureHour + ":" + String.format("%02d", dateTimeDeparture.getMinute()) + " " + departPeriod + " " +
			   " - ARRIVES: " + timeArrivalHour + ":" + String.format("%02d", dateTimeArrival.getMinute()) + " " + arrivePeriod + ", " +
			   dateTimeArrival.getMonth() + " " +  dateTimeArrival.getDayOfMonth();
    }
	
}
