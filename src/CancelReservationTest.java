import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * This class contains unit tests for the CancelReservation class. It tests the cancelReservationAction method under various scenarios.
 * The tests include cases where the reservation ID exists, does not exist, and when IOExceptions occur during file reading and writing.
 * The tests also include cases where the reservation ID is null or empty. This class also utilizes the Files class to create and delete a test reservation file. 
 * In addition, we use the Files.setAttribute() method to make the file unreadable or unwritable
 * 
 * We must use the JUnit framework for testing. This means we must include BeforeEach and AfterEach annotations to set up and tear down the testing environment.
 * Methods for test include the Test annotation to define the test methods. It uses the assertTrue(), assertFalse(), and assertThrows() methods to check the results of the methods under test.
 * 
 * Module Name: CancelReservationTest
 * @author Joshua Planovsky
 * @version 2.0 last updated: 11/21/2023
 * 
 *
 */
class CancelReservationTest {
  private static String testReservationFilePath = "src/test/resources/TestReservation.txt";

  /**
   * Set up method to create a test reservation file before each test.
   * @throws IOException if an error occurs while creating the file
   */
  @BeforeEach
  void setUp() throws IOException {
      // Create a test reservation file
      BufferedWriter writer = new BufferedWriter(new FileWriter(testReservationFilePath));
      writer.write("Reservation ID: 123\n");
      writer.write("--Reservation End--\n");
      writer.close();
  }

  /**
   * Tear down method to delete the test reservation file after each test.
   * @throws IOException if an error occurs while deleting the file
   */
  @AfterEach
  void tearDown() throws IOException {
      // Delete the test reservation file
      Files.deleteIfExists(Paths.get(testReservationFilePath));
  }

  /**
   * Method to test when the reservation ID exists.
   */
  @Test
  void testCancelReservationAction() {
      // Test when the reservation ID exists
      CancelReservation cancelReservation = new CancelReservation(testReservationFilePath);
      assertTrue(cancelReservation.cancelReservationAction("123"));
  }
  
  /**
   * Method to test when the reservation ID does not exist.
   */
  @Test
  void testCancelReservationActionNotFound() {
      // Test when the reservation ID does not exist
      CancelReservation cancelReservation = new CancelReservation(testReservationFilePath);
      assertFalse(cancelReservation.cancelReservationAction("456"));
  }

  /**
   * Test method to test when an IOException occurs during file reading.
   * @throws IOException if an error occurs while reading the file
   */
  @Test
  void testCancelReservationActionReadIOException() throws IOException {
      // Test when an IOException occurs during file reading
      // Make the test reservation file unreadable
      Files.setAttribute(Paths.get(testReservationFilePath), "dos:readonly", true);

      CancelReservation cancelReservation = new CancelReservation(testReservationFilePath);
      assertFalse(cancelReservation.cancelReservationAction("123"));

      // Reset file permissions after the test
      Files.setAttribute(Paths.get(testReservationFilePath), "dos:readonly", false);
  }
  

  /**
   * Test method to test when an IOException occurs during file reading.
   * @throws IOException if an error occurs while reading the file
   */
  @Test
  void testCancelReservationActionWriteIOException() throws IOException {
      // Test when an IOException occurs during file writing
      // Make the test reservation file unwritable
      Files.setAttribute(Paths.get(testReservationFilePath), "dos:readonly", true);

      CancelReservation cancelReservation = new CancelReservation(testReservationFilePath);
      assertFalse(cancelReservation.cancelReservationAction("123"));

      // Reset file permissions after the test
      Files.setAttribute(Paths.get(testReservationFilePath), "dos:readonly", false);
  }

  /**
   * Test method to test if the Reservation ID is null
   */
  @Test
  void testCancelReservationActionNullId() {
      // Test when the reservation ID is null
      CancelReservation cancelReservation = new CancelReservation(testReservationFilePath);
      assertThrows(IllegalArgumentException.class, () -> cancelReservation.cancelReservationAction(null));
  }

  /**
   * Test method to test if the Reservation ID is empty
   */
  @Test
  void testCancelReservationActionEmptyId() {
      // Test when the reservation ID is empty
      CancelReservation cancelReservation = new CancelReservation(testReservationFilePath);
      assertThrows(IllegalArgumentException.class, () -> cancelReservation.cancelReservationAction(""));
  }
}
