import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseHandler {

	   public List<String> readFromFile(String filePath) throws IOException {
	       List<String> lines = new ArrayList<>();
	       try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	           String line;
	           while ((line = reader.readLine()) != null) {
	               lines.add(line);
	           }
	       }
	       return lines;
	   }

	   public boolean writeToFile(String filePath, List<Reservation> reservations) {
	       try {
	           // Read the existing lines from the file
	           List<String> lines = readFromFile(filePath);

	           // Filter out the lines that belong to cancelled reservations
	           List<String> nonCancelledLines = new ArrayList<>();
	           boolean isCancelled = false;
	           for (String line : lines) {
	               if (line.contains("--Reservation End--")) {
	                  if (isCancelled) {
	                      // Skip the line if the reservation is cancelled
	                      continue;
	                  }
	                  // Otherwise, add the line to the nonCancelledLines list
	                  nonCancelledLines.add(line);
	                  isCancelled = false;
	               } else {
	                  // Check if the line belongs to a cancelled reservation
	                  for (Reservation reservation : reservations) {
	                      if (line.contains(String.valueOf(reservation.getID())) && reservation.isCancelled()) {
	                          isCancelled = true;
	                          break;
	                      }
	                  }
	                  // Add the line to the nonCancelledLines list
	                  nonCancelledLines.add(line);
	               }
	           }

	           // Write the non-cancelled lines to the file
	           BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false)); // Open the file in overwrite mode
	           for (String line : nonCancelledLines) {
	               writer.write(line);
	               writer.newLine();
	           }
	           writer.close();

	           return true;
	       } catch (IOException e) {
	           e.printStackTrace();
	           return false;
	       }
	   }
	}