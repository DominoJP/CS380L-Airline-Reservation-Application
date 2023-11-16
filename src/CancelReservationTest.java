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
 * This class contains unit tests for the CancelReservation class.
 * It tests the cancelReservationAction method under various scenarios.
 */
class CancelReservationTest {
  private static String testReservationFilePath = "src/test/resources/TestReservation.txt";

  @BeforeEach
  void setUp() throws IOException {
      // Create a test reservation file
      BufferedWriter writer = new BufferedWriter(new FileWriter(testReservationFilePath));
      writer.write("Reservation ID: 123\n");
      writer.write("--Reservation End--\n");
      writer.close();
  }

  @AfterEach
  void tearDown() throws IOException {
      // Delete the test reservation file
      Files.deleteIfExists(Paths.get(testReservationFilePath));
  }

  @Test
  void testCancelReservationAction() {
      // Test when the reservation ID exists
      CancelReservation cancelReservation = new CancelReservation(testReservationFilePath);
      assertTrue(cancelReservation.cancelReservationAction("123"));
  }

  @Test
  void testCancelReservationActionNotFound() {
      // Test when the reservation ID does not exist
      CancelReservation cancelReservation = new CancelReservation(testReservationFilePath);
      assertFalse(cancelReservation.cancelReservationAction("456"));
  }

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

  @Test
  void testCancelReservationActionNullId() {
      // Test when the reservation ID is null
      CancelReservation cancelReservation = new CancelReservation(testReservationFilePath);
      assertThrows(IllegalArgumentException.class, () -> cancelReservation.cancelReservationAction(null));
  }

  @Test
  void testCancelReservationActionEmptyId() {
      // Test when the reservation ID is empty
      CancelReservation cancelReservation = new CancelReservation(testReservationFilePath);
      assertThrows(IllegalArgumentException.class, () -> cancelReservation.cancelReservationAction(""));
  }
}
