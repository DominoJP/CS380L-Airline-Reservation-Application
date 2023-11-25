import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests for the Flight class. 
 *
 */
class FlightTest {

	private Flight flight;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		
		 
		 
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}


	
	@Test
	void testToString1() {
		flight = new Flight(1, "One Way", "LAX", "JFK", "2023-10-24", "05:00", "2023-10-24", "07:00", "America/Los_Angeles"); 
		
		String test = flight.toString();
	   
		if(!test.equals("DEPARTS: 5:00 AM - ARRIVES: 7:00 AM, OCTOBER 24")) {
			fail("Not the correct period");
		}
	}
	
	@Test
	void testToString2() {
			flight = new Flight(1, "One-Way", "LAX", "JFK", "2023-10-24", "17:00", "2023-10-24", "19:00", "America/Los_Angeles"); 
		
				String test = flight.toString();
	   
				if(!test.equals("DEPARTS: 5:00 PM - ARRIVES: 7:00 PM, OCTOBER 24")) {
					fail("Not the correct period");
		}	
	}
	
	@Test
	void testToString3() {
		flight = new Flight(1, "One-Way", "LAX", "JFK", "2023-10-24", "05:00", "2023-10-24", "19:00", "America/Los_Angeles"); 
		
		String test = flight.toString();
	   
		if(!test.equals("DEPARTS: 5:00 AM - ARRIVES: 7:00 PM, OCTOBER 24")) {
			fail("Not the correct period");
		}
	}
	
	@Test
	void testToString4() {
		flight = new Flight(1, "One-Way", "LAX", "JFK", "2023-10-24", "17:00", "2023-10-24", "01:00", "America/Los_Angeles"); 
		
		String test = flight.toString();
	   
		if(!test.equals("DEPARTS: 5:00 PM - ARRIVES: 1:00 AM, OCTOBER 24")) {
			fail("Not the correct period");
		}	
	}
	
	@Test
	void testToString5() {
		flight = new Flight(1, "One-Way", "LAX", "JFK", "2023-10-24", "12:00", "2023-10-24", "12:00", "America/Los_Angeles"); 
		
		String test = flight.toString();
	   
		if(!test.equals("DEPARTS: 12:00 PM - ARRIVES: 12:00 PM, OCTOBER 24")) {
			fail("Not the correct period");
		}
	}

}
