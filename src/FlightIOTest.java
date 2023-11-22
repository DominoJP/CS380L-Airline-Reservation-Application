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

	@BeforeEach
	void setUp() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
		writer.write("4, One Way, JFK, LAX, 2023-10-24, 05:35, 2023-10-24, 13:15, America/New_York, " +
				     "100, 10, 300.00, 50, 40, 450.00, 30, 5, 650.00");
		writer.newLine();
	  	writer.close();
	}

	@AfterEach
	void tearDown() throws IOException {
		Files.deleteIfExists(Paths.get(FILE_PATH));
	}

	@Test
	void testUpdatePassengerCountAction() {
		Flight selectedFlight = new Flight(4, null, null, null, "2000-01-01", "12:00", "2000-01-01", "12:00", "UTC",
										   0, 0, new BigDecimal("0.00"), 0, 0, new BigDecimal("0.00"), 0, 0, new BigDecimal("0.00"));
		int selectedPassengerAmount = 1;
		String selectedCabin = "Economy";
		assertTrue(FlightIO.updatePassengerCount(FILE_PATH, selectedFlight, selectedPassengerAmount, selectedCabin));
	}
	
	@Test
	void testUpdatePassengerCountActionIDNotFound() {
		Flight selectedFlight = new Flight(3, null, null, null, "2000-01-01", "12:00", "2000-01-01", "12:00", "UTC",
										   0, 0, new BigDecimal("0.00"), 0, 0, new BigDecimal("0.00"), 0, 0, new BigDecimal("0.00"));
		int selectedPassengerAmount = 1;
		String selectedCabin = "Economy";
		
		FlightIO.updatePassengerCount(FILE_PATH, selectedFlight, selectedPassengerAmount, selectedCabin);
		// read updated line
		String line;
		String[] parts;
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			line = reader.readLine();
			parts = line.split(", ");
			assertEquals(10, Integer.parseInt(parts[ECONOMY_COUNT_INDEX]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}