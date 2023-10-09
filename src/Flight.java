
import java.util.List;

public class Flight {
	
	private String type; //(round trip/one way)
	private String cityDeparture;     // (and/or airport)
	private String cityArrival;        //(and/or airport)
	private String dateDeparture;      //date of departure
	private String timeDeparture;    //time of departure 
	private String dateArrival;      //date of arrival
	private String timeArrival;     //time of arrival
	private int totalPassengerCapacity;  //total of passenger capacity
	private String[][] passengers; 
	private double pricing; //pricing 
	

	/**
	 * 
	 * @param type
	 * @param cityDeparture
	 * @param cityArrival
	 * @param dateDeparture
	 * @param timeDeparture
	 * @param totalPassengerCapacity
	 * @param pricing
	 */
	public Flight(String type, String cityDeparture, String cityArrival, String dateDeparture,
			String timeDeparture, int totalPassengerCapacity, double pricing) {
		
	this.type = type; 
	this.cityDeparture = cityDeparture;
	this.cityArrival = cityArrival;
	this.dateDeparture = dateDeparture;
	this.timeDeparture = timeDeparture; 
	this.totalPassengerCapacity = totalPassengerCapacity;
	this.pricing = pricing; 
	
	passengers = new String[totalPassengerCapacity][2];
	
	}
	
	/**
	 * 
	 * @return
	 */
	public String gettype(){
		return type;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getcityDeparture() {
		return cityDeparture;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getcityArrival() {
		return cityArrival;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getdateDeparture() {
		return dateDeparture;
	}
	
	/**
	 * 
	 * @return
	 */
	public String gettimeDeparture() {
		return timeDeparture;
	}
	
	/**
	 * 
	 * @return
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
     * 
     * @return
     */
	public double getpricing() {
		return pricing;
	}

	/**
	 * 
	 * @return
	 */
	public List<Passenger> getPassenger() {
		return null;
	}

	/**
	 * 
	 * @return
	 */
	public String getDateArrival() {
		return dateArrival;
	}

	/**
	 * 
	 * @param dateArrival
	 */
	public void setDateArrival(String dateArrival) {
		this.dateArrival = dateArrival;
	}

	/**
	 * 
	 * @return
	 */
	public String getTimeArrival() {
		return timeArrival;
	}

	/**
	 * 
	 * @param timeArrival
	 */
	public void setTimeArrival(String timeArrival) {
		this.timeArrival = timeArrival;
	}

	/**
	 * 
	 * @return
	 */
	public int gettotalpassengercapacity() {
		return totalPassengerCapacity;
		
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isFull() {
		return false;
	}
	
	public  void setpassenger(int location, String accountnumber, String name) {
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
	
	
}
