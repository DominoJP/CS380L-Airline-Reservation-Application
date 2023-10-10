


public class Node {
	private String destination;
	private Node child1;
	private Node child2;
	private Node2 flights;
	
	public Node() {
		this.destination = null;
		child1 = null;
		child2 = null;
		flights = null;
	}
	
	public Node(String d) {
		this.destination = d;
		child1 = null;
		child2 = null;
		flights = null;
	}
	
	public Node(String d, Flight f) {
		this.destination = d;
		child1 = null;
		child2 = null;
		flights = null;
	}
	
	public void addFlight(Flight f) {
		
		if(this.getDestination() == null) {
			if(this.flights == null) {
				this.flights = new Node2(f.getcityDeparture());
			}
			
			flights.addFlight(f);;
		}else if(this.getDestination() == f.getcityArrival()) {
			if(this.flights == null) {
				this.flights = new Node2(f.getcityDeparture());
			}
			
			flights.addFlight(f);
		}else {
			Node newChild = new Node(f.getcityArrival());
			newChild.flights = new Node2(f.getcityDeparture());
			newChild.flights.addFlight(f);
			
			this.addChild(this, newChild);
		}
	}
	
	public void addChild(Node curr, Node n) {
		if(curr.getDestination().compareTo(n.getDestination()) > 0) {
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
	
	public String getDestination() {
		return this.destination;
	}
}
