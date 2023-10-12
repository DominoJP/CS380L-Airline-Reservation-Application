import java.util.ArrayList;

public class AirportFlights {
	private String destination;
	private AirportFlights child1;
	private AirportFlights child2;
	private ArrayList<Flight> timeDeparture;
	
	public AirportFlights() {
		this.destination = null;
		this.child1 = null;
		this.child2 = null;
		this.timeDeparture = null;
	}
	
	public AirportFlights(String a) {
		this.destination = a;
		this.child1 = null;
		this.child2 = null;
		this.timeDeparture = null;
	}
	
	public void addFlight(Flight f) {
		Flight curr = f;
		Flight next = null;
		if(this.destination == f.getcityArrival()) {
			if(this.timeDeparture == null) {
				this.timeDeparture.add(f);
			}else {
				for(int i = 0; i < this.timeDeparture.size(); i++){
					if(curr.gettimeDeparture().compareTo(this.timeDeparture.get(i).gettimeDeparture()) >= 0) {
						next = this.timeDeparture.get(i);
						this.timeDeparture.set(i, curr);
						curr = next;
					}
				}
				
				this.timeDeparture.add(curr);
			}
		}else {
			AirportFlights newChild = new AirportFlights(f.getcityArrival());
			newChild.addFlight(f);
			
			this.addChild(this, newChild);
		}
	}
	
	public void addChild(AirportFlights curr,AirportFlights n) {
		if(curr.destination.compareTo(n.destination) > 0) {
			if(curr.child1 != null) {
				curr.addChild(curr.child1, n);
				return;
			}
			
			curr.child1 = n;
		}else {
			if(curr.child2 != null) {
				curr.addChild(curr.child2, n);
				return;
			}
			
			curr.child2 = n;
		}
	}
}
