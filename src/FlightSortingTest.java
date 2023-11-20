import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.time.*;

class FlightSortingTest {
	private FlightSorting sort1;
	private FlightSorting sort2;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		sort1 = new FlightSorting();
		
		sort2 = new FlightSorting();
		
		ArrayList<Flight> test = new ArrayList<Flight>();
		
		Flight flight1 = new Flight(1, "One-Way", "LAX", "NYC", "2023-01-21", "16:00", "2023-05-10", "21:00", "America/Los_Angeles");
		
		test.add(flight1);
		
		Flight flight2 = new Flight(1, "One-Way", "LAX", "NYC", "2023-01-21", "09:00", "2023-05-10", "12:00", "America/Los_Angeles");
		
		test.add(flight2);
		
		Flight flight3 = new Flight(1, "One-Way", "LAX", "NYC", "2023-01-21", "14:00", "2023-05-10", "18:00", "America/Los_Angeles");
		
		test.add(flight3);
		
		sort2.initialize(test);
	}

	@AfterEach
	void tearDown() throws Exception {
		sort1 = new FlightSorting();
		
		sort2 = new FlightSorting();
	}


	@Test
	void testInitialize1() {
		ArrayList<Flight> test = new ArrayList<Flight>();
		
		sort1.initialize(test);
		
		if(sort1.getFlights() != null)
			fail("There should be no list");
	}
	
	@Test
	void testInitialize2() {
		ArrayList<Flight> test = new ArrayList<Flight>();
		
		Flight flight = new Flight(1, "One-Way", "LAX", "NYC", "2023-10-01", "16:00", "2023-10-01", "21:00", "America/Los_Angeles");
		
		test.add(flight);
		
		sort1.initialize(test);
		
		sort1.sortFlights("LAX", "NYC", "2023-10-01");
		
		if(sort1.getFlights().get(0) != flight)
			fail("The one flight was not added");
	}
	

	@Test
	void testInitialize3() {
		ArrayList<Flight> test = new ArrayList<Flight>();
		
		Flight flight1 = new Flight(1, "One-Way", "LAX", "NYC", "2023-10-01", "16:00", "2023-10-01", "21:00", "America/Los_Angeles");
		
		test.add(flight1);
		
		Flight flight2 = new Flight(1, "One-Way", "LAX", "NYC", "2023-12-12", "09:00", "2023-12-12", "12:00", "America/Los_Angeles");
		
		test.add(flight2);
		
		Flight flight3 = new Flight(1, "One-Way", "LAX", "NYC", "2023-06-20", "14:00", "2023-06-20", "18:00", "America/Los_Angeles");
		
		test.add(flight3);
		
		sort1.initialize(test);
		
		sort1.sortFlights("LAX", "NYC", "2023-10-01");
		
		if(sort1.getFlights().get(0) != flight1)
			fail("The one flight was not added");

		sort1.sortFlights("LAX", "NYC", "2023-12-12");
		
		if(sort1.getFlights().get(0) != flight2)
			fail("The one flight was not added");

		sort1.sortFlights("LAX", "NYC", "2023-06-20");
		
		if(sort1.getFlights().get(0) != flight3)
			fail("The one flight was not added");
	}
	
	
	@Test
	void testAddFlight1() {
		Flight flight = new Flight(1, "One-Way", "LAX", "NYC", "2023-10-01", "16:00", "2023-10-01", "21:00", "America/Los_Angeles");
		
		sort1.addFlight(flight);
		
		sort1.sortFlights("LAX", "NYC", "2023-10-01");
		
		if(sort1.getFlights().get(0) != flight)
			fail("The one flight was not added");
	} 
	
	@Test
	void testAddFlight2() {
		Flight flight1 = new Flight(1, "One-Way", "LAX", "NYC", "2023-12-12", "09:00", "2023-12-12", "12:00", "America/Los_Angeles");
		
		Flight flight2 = new Flight(1, "One-Way", "LAX", "NYC", "2023-06-20", "14:00", "2023-06-20", "18:00", "America/Los_Angeles");
		
		sort1.addFlight(flight1);
		
		sort1.addFlight(flight2);
		
		sort1.sortFlights("LAX", "NYC", "2023-12-12");
		
		if(sort1.getFlights().get(0) != flight1)
			fail("The one flight was not added");

		sort1.sortFlights("LAX", "NYC", "2023-06-20");
		
		if(sort1.getFlights().get(0) != flight2)
			fail("The one flight was not added");
	} 
	
	@Test
	void testFindFlight1() {
		if(sort1.findFlight("05:00") != null)
			fail("This is supposed to return nothing");
	}
	
	@Test
	void testFindFlight2() {
		sort2.sortFlights("LAX", "NYC", "2023-01-21");
		
		if(!sort2.findFlight("09:00").gettimeDeparture().equals(LocalTime.parse("09:00"))) {
			fail("This is supposed to return the first flight in the list");
		}
	}
	
	@Test
	void testFindFlight3() {
		sort2.sortFlights("LAX", "NYC", "2023-01-21");
		
		if(!sort2.findFlight("14:00").gettimeDeparture().equals(LocalTime.parse("14:00"))) {
			fail("This is supposed to return a flight from the middle of the list");
		}
	}
	
	@Test
	void testFindFlight4() {
		sort2.sortFlights("LAX", "NYC", "2023-01-21");
		
		if(!sort2.findFlight("16:00").gettimeDeparture().equals(LocalTime.parse("16:00"))) {
			fail("This is supposed to return the flight at the end of the list");
		}
	}
	
	@Test
	void testFindFlight5() {
		sort2.sortFlights("LAX", "NYC", "2023-01-21");
		
		if(sort2.findFlight("08:00") == null) {
			fail("This is supposed to return nothing");
		}
	}

	@Test
	void testSearch() {
		fail("Not yet implemented");
	}

}
