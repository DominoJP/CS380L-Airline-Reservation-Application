import java.util.ArrayList;

public class Node2 {
	private String departure;
	private Node2 child1;
	private Node2 child2;
	private ArrayList<Flight> timeDeparture;
	
	public Node2() {
		this.departure = null;
		this.child1 = null;
		this.child2 = null;
		this.timeDeparture = null;
	}
	
	public Node2(String a) {
		this.departure = a;
		this.child1 = null;
		this.child2 = null;
		this.timeDeparture = null;
	}
	
	public void addFlight(Flight f) {
		Flight curr = f;
		Flight next = null;
		if(this.departure == f.getcityDeparture()) {
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
			Node2 newChild = new Node2(f.getcityDeparture());
			newChild.addFlight(f);
			
			this.addChild(this, newChild);
		}
	}
	
	public void addChild(Node2 curr,Node2 n) {
		if(curr.departure.compareTo(n.departure) > 0) {
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
