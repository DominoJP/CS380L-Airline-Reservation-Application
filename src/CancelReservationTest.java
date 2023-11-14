import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class CancelReservationTest {
   private static String testReservationFilePath = "src/test/resources/TestReservation.txt";

   @BeforeAll
   static void setUpBeforeClass() throws IOException {
       // Create a test reservation file
       BufferedWriter writer = new BufferedWriter(new FileWriter(testReservationFilePath));
       writer.write("Reservation ID: 123\n");
       writer.write("--Reservation End--\n");
       writer.close();
   }

   @AfterAll
   static void tearDownAfterClass() throws IOException {
       // Delete the test reservation file
       Files.deleteIfExists(Paths.get(testReservationFilePath));
   }

   @Test
   void testCancelReservationAction() {
       CancelReservation cancelReservation = new CancelReservation(testReservationFilePath);
       assertTrue(cancelReservation.cancelReservationAction("123"));
   }

   @Test
   void testCancelReservationActionNotFound() {
       CancelReservation cancelReservation = new CancelReservation(testReservationFilePath);
       assertFalse(cancelReservation.cancelReservationAction("456"));
   }
}