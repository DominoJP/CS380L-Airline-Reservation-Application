
//Define Flight class 

import java.util.Date;

class Flight {
	
	private String type; //(round trip/one way)
	private String cityDeparture;     // (and/or airport)
	private String cityArrival;        //(and/or airport)
	private Date dateDeparture;      //date of departure
	private Date timeDeparture;    //time of departure 
	private int totalPassengerCapacity;  //total of passenger capacity
	private String[][] passengers; 
	private double pricing; //pricing 
	
	
	//Constructor 
	public Flight(String type, String cityDeparture, String cityArrival, Date dateDeparture,
			Date timeDeparture, int totalPassengerCapacity, double pricing) {
		
	this.type = type; 
	this.cityDeparture = cityDeparture;
	this.cityArrival = cityArrival;
	this.dateDeparture = dateDeparture;
	this.timeDeparture = timeDeparture; 
	this.totalPassengerCapacity = totalPassengerCapacity;
	this.pricing = pricing; 
		
	passengers = new String[totalPassengerCapacity][2];
	
	}
	
	//Getters - Flight 
	public String gettype(){
		return type;
	}
	
	public String getcityDeparture() {
		return cityDeparture;
	}
	
	public String cityArrival() {
		return cityArrival;
	}
	
	public Date dateDeparture() {
		return dateDeparture;
	}
	
	public Date timeDeparture() {
		return timeDeparture;
	}
	
	public int totalPassengerCapacity() {
		int available = 0; 
		for(int i=0; i<totalPassengerCapacity; i++) {
			if(passengers[i][1] == null ) {
				available++;
			}
		}
		return available;
	}
	
	public double pricing() {
		return pricing;
	}
}
