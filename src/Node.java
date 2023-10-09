import java.util.ArrayList;


public class Node {
	private String destination;
	private Node child1;
	private Node child2;
	
	public Node() {
		this.destination = null;
		child1 = null;
		child2 = null;
	}
	
	public Node(String d) {
		this.destination = d;
		child1 = null;
		child2 = null;
	}
	
	public Node(String d, Flight f) {
		this.destination = d;
		child1 = null;
		child2 = null;
	}
	
	public void addFlight(Flight f) {
		
	}
	
}
