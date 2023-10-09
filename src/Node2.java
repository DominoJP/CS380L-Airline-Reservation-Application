import java.util.ArrayList;

public class Node2 {
	private String arrival;
	private Node child1;
	private Node child2;
	private ArrayList<Flight> timeDeparture;
	
	public Node2() {
		this.arrival = null;
		this.child1 = null;
		this.child2 = null;
		this.timeDeparture = null;
	}
	
	public Node2(String a) {
		this.arrival = a;
		this.child1 = null;
		this.child2 = null;
		this.timeDeparture = null;
	}
	
	public void addFlight(Flight f) {
		Flight curr;
		Flight next;
		
		if(this.timeDeparture == null) {
			this.timeDeparture.add(f);
		}else {
			for(int i = 0; i < this.timeDeparture.size(); i++){
				if(f.gettimeDeparture().compareTo(this.timeDeparture.get(i).gettimeDeparture()) >= 0) {
					
				}
			}
		}
	}
}
