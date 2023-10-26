/**
 * The Flight class represents a flight in a flight reservation such as 
 * the type of flight (round trip or one way), departure and arrival cities,
 * departure and arrival dates and times, 
 * total passenger capacity, pricing and passenger information. 
 *@author Sayra Reyes
 *@version 1.0 
 */

public class Flight {
	
	private String type; 
	private String cityDeparture;   
	private String cityArrival;      
	private String dateDeparture;      
	private String timeDeparture;    
	private String dateArrival;     
	private String timeArrival;   
	private int totalPassengerCapacity;  
	private String[][] passengers; 
	private double pricing; 
	

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
	public Flight(String type, String cityDeparture, String cityArrival, String dateDeparture,
			String timeDeparture, String dateArrival, String timeArrival, int totalPassengerCapacity, double pricing) {
		
	this.type = type; 
	this.cityDeparture = cityDeparture;
	this.cityArrival = cityArrival;
	this.dateDeparture = dateDeparture;
	this.timeDeparture = timeDeparture; 
	this.dateArrival = dateArrival;
	this.timeArrival = timeArrival;
	this.totalPassengerCapacity = totalPassengerCapacity;
	this.pricing = pricing; 
	passengers = new String[totalPassengerCapacity][2];
	
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
	public String getdateDeparture() {
		return dateDeparture;
	}
	
	/**
	 * Getter method to retrieve the time of departure.
	 * @return returns the time of departure 
	 */
	public String gettimeDeparture() {
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
	public String getDateArrival() {
		return dateArrival;
	}

	/**
	 * Setter to set the date of the arrival 
	 * @param dateArrival
	 */
	public void setDateArrival(String dateArrival) {
		this.dateArrival = dateArrival;
	}

	/**
	 * Getter to get the time of the arrival
	 * @return
	 */
	public String getTimeArrival() {
		return timeArrival;
	}

	/**
	 * Setter to get the time of the arrival
	 * @param timeArrival
	 */
	public void setTimeArrival(String timeArrival) {
		this.timeArrival = timeArrival;
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

	public String getpassenger() {
		return null;
	}
	
}
