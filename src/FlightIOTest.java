import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FlightIOTest {
	private static String FILE_PATH = "FlightsTest.txt";
	private static final int ECONOMY_COUNT_INDEX = 10;
	private static final int BUSINESS_COUNT_INDEX = 13;
	private static final int FIRST_CLASS_COUNT_INDEX = 16;

	/*
	@BeforeEach
	void setUp() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
		writer.write("4, One Way, JFK, LAX, 2023-10-24, 05:35, 2023-10-24, 13:15, America/New_York, " +
				     "100, 10, 300.00, 50, 40, 450.00, 30, 5, 650.00");
		// writer.newLine();
		// writer.write("5, One Way, JFK, LAX, 2023-10-24, 05:35, 2023-10-24, 13:15, America/New_York, " +
			     // "100, 10, 300.00, 50, 40, 450.00, 30, 5, 650.00");
	  	writer.close();
	}
	*/

	@AfterEach
	void tearDown() throws IOException {
		Files.deleteIfExists(Paths.get(FILE_PATH));
	}
	
	@Test
	void testRewritePassengerCountInvalidCabin() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {// prepare file
			writer.write("0000000004, One Way, JFK, LAX, 2023-12-22, 05:35, 2023-12-22, 13:15, America/New_York, " +
				     	 "100, 10, 300.00, 50, 40, 450.00, 30, 5, 650.00");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// prepare selectedFlight
		Flight selectedFlight = new Flight(0000000004, "One Way", "JFK", "LAX", "2023-12-22", "05:35", "2023-12-22", "13:15", "America/New_York");
		selectedFlight.setEconomy(100, new BigDecimal("300.00"));
		selectedFlight.addEconomyPassengerCount(10);
		String selectedCabin = "Main"; // prepare selectedCabin
		selectedFlight.addEconomyPassengerCount(2); // prepare selectedPassengerAmount
		
		try { // invoke method with prepared parameters
			FlightIO.rewritePassengerCount(FILE_PATH, selectedFlight, selectedCabin);
		} catch (IOException e) {
	        e.printStackTrace();
	    }
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line = reader.readLine();
			String[] parts = line.split(", ");
			if (Integer.parseInt(parts[0]) == selectedFlight.getID())
				assertEquals(10, Integer.parseInt(parts[ECONOMY_COUNT_INDEX]));
				assertEquals(40, Integer.parseInt(parts[BUSINESS_COUNT_INDEX]));
				assertEquals(5, Integer.parseInt(parts[FIRST_CLASS_COUNT_INDEX]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testRewritePassengerCountNoSelectedFlightSingleLineFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) { // prepare file
			writer.write("0000000003, One Way, LAX, JFK, 2023-12-22, 05:35, 2023-12-22, 13:15, America/New_York, " +
						 "100, 10, 300.00, 50, 40, 450.00, 30, 5, 650.00");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// prepare selectedFlight
		Flight selectedFlight = new Flight(0000000004, "One Way", "JFK", "LAX", "2023-12-22", "05:35", "2023-12-22", "13:15", "America/New_York");
		selectedFlight.setEconomy(100, new BigDecimal("300.00"));
		selectedFlight.addEconomyPassengerCount(12);
		String selectedCabin = "Economy"; // prepare selectedCabin
		selectedFlight.addEconomyPassengerCount(2); // prepare selectedPassengerAmount
		
		try { // invoke method with prepared parameters
			FlightIO.rewritePassengerCount(FILE_PATH, selectedFlight, selectedCabin);
		} catch (IOException e) {
	        e.printStackTrace();
	    }
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line = reader.readLine();
			String[] parts = line.split(", ");
			if (Integer.parseInt(parts[0]) == selectedFlight.getID())
				assertEquals(10, Integer.parseInt(parts[ECONOMY_COUNT_INDEX]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testRewritePassengerCountLineMissingElements() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) { // prepare file
			writer.write("0000000004");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// prepare selectedFlight
		Flight selectedFlight = new Flight(0000000004, "One Way", "JFK", "LAX", "2023-12-22", "05:35", "2023-12-22", "13:15", "America/New_York");
		selectedFlight.setEconomy(100, new BigDecimal("300.00"));
		selectedFlight.addEconomyPassengerCount(12);
		String selectedCabin = "Economy"; // prepare selectedCabin
		selectedFlight.addEconomyPassengerCount(2); // prepare selectedPassengerAmount
		
		try { // invoke method with prepared parameters
			FlightIO.rewritePassengerCount(FILE_PATH, selectedFlight, selectedCabin);
		} catch (IOException e) {
	        e.printStackTrace();
	    }
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line = reader.readLine();
			assertEquals("0000000004", line);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testRewritePassengerCountHasSelectedFlightSingleLineFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {// prepare file
			writer.write("0000000004, One Way, JFK, LAX,, 2023-12-22, 05:35, 2023-12-22, 13:15, America/New_York, " +
						 "100, 10, 300.00, 50, 40, 450.00, 30, 5, 650.00");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// prepare selectedFlight
		Flight selectedFlight = new Flight(0000000004, "One Way", "JFK", "LAX", "2023-12-22", "05:35", "2023-12-22", "13:15", "America/New_York");
		selectedFlight.setEconomy(100, new BigDecimal("300.00"));
		selectedFlight.addEconomyPassengerCount(10);
		String selectedCabin = "Economy"; // prepare selectedCabin
		selectedFlight.addEconomyPassengerCount(2); // prepare selectedPassengerAmount
		
		try { // invoke method with prepared parameters
			FlightIO.rewritePassengerCount(FILE_PATH, selectedFlight, selectedCabin);
		} catch (IOException e) {
	        e.printStackTrace();
	    }
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line = reader.readLine();
			String[] parts = line.split(", ");
			if (Integer.parseInt(parts[0]) == selectedFlight.getID())
				assertEquals(12, Integer.parseInt(parts[ECONOMY_COUNT_INDEX]));
				assertEquals(40, Integer.parseInt(parts[BUSINESS_COUNT_INDEX]));
				assertEquals(5, Integer.parseInt(parts[FIRST_CLASS_COUNT_INDEX]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testRewritePassengerCountHasSelectedFlightMultipleLineFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {// prepare file
			writer.write("0000000003, One Way, LAX, JFK, 2023-10-24, 05:35, 2023-10-24, 13:15, America/New_York, " +
				         "100, 20, 300.00, 50, 30, 450.00, 30, 10, 650.00");
			writer.newLine();
			writer.write("0000000004, One Way, JFK, LAX,, 2023-12-22, 05:35, 2023-12-22, 13:15, America/New_York, " +
						 "100, 10, 300.00, 50, 40, 450.00, 30, 5, 650.00");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// prepare selectedFlight
		Flight selectedFlight = new Flight(0000000004, "One Way", "JFK", "LAX", "2023-12-22", "05:35", "2023-12-22", "13:15", "America/New_York");
		selectedFlight.setEconomy(100, new BigDecimal("300.00"));
		selectedFlight.addEconomyPassengerCount(10);
		String selectedCabin = "Economy"; // prepare selectedCabin
		selectedFlight.addEconomyPassengerCount(3); // prepare selectedPassengerAmount
		
		try { // invoke method with prepared parameters
			FlightIO.rewritePassengerCount(FILE_PATH, selectedFlight, selectedCabin);
		} catch (IOException e) {
	        e.printStackTrace();
	    }
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			String line = reader.readLine();
			String[] parts = line.split(", ");
			assertEquals(20, Integer.parseInt(parts[ECONOMY_COUNT_INDEX]));
			assertEquals(30, Integer.parseInt(parts[BUSINESS_COUNT_INDEX]));
			assertEquals(10, Integer.parseInt(parts[FIRST_CLASS_COUNT_INDEX]));
			
			line = reader.readLine();
			parts = line.split(", ");
			if (Integer.parseInt(parts[0]) == selectedFlight.getID()) { // if line is selected flight
				assertEquals(13, Integer.parseInt(parts[ECONOMY_COUNT_INDEX]));
				assertEquals(40, Integer.parseInt(parts[BUSINESS_COUNT_INDEX]));
				assertEquals(5, Integer.parseInt(parts[FIRST_CLASS_COUNT_INDEX]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
