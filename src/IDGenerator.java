import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * a) Design Documentation: 'IDGenerator'
 * b) Date of Creation: November 11, 2023
 * c) @author Jevy Miranda
 * d) Description: Solution to generate user-readable unique ID generation across JVM instances.
 * e) Functions: setLastID() reads the last IDs generated (as saved in IDGeneratorHelper.txt) into local variables.
 *    generateAccountID() and generateReservationID() increment their respective lastID local variables and
 *    return a new unique ID of data type int.
 * f) Data Structures: N/A
 * g) Algorithms: N/A
 */
public class IDGenerator {
	private final String filePath = "src/Database/IDGeneratorHelper.txt";
	private int lastReservationID;
	private int lastAccountID;
	
	/**
	 * Class Constructor.
	 */
	public IDGenerator() {
		setLastID();
	}
	
	/**
	 * Generates ID based on running count, updated into IDGeneratorHelper.txt.
	 * @return newly-generated unique ID
	 */
	public int generateAccountID() {
		lastAccountID++;
		updateFile("Accounts");
		return lastAccountID;
	}
	
	/**
	 * Generates ID based on running count, updated into IDGeneratorHelper.txt.
	 * @return newly-generated unique ID
	 */
	public int generateReservationID() {
		lastReservationID++;
		updateFile("Reservations");
		return lastReservationID;
	}
	
	/**
	 * Read the last IDs generated and assign them as local variables.
	 */
	private void setLastID() {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(": ");
				if (parts[0].equals("Reservations")) {
					this.lastReservationID = Integer.parseInt(parts[1]);
				} else if (parts[0].equals("Accounts")) {
					this.lastAccountID = Integer.parseInt(parts[1]);
				}
		    }
			reader.close();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Updates IDGeneratorHelper.txt with newly-generated lastID.
	 */
	private void updateFile(String type) {
		ArrayList<String> lines = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(": ");
				if (type.equals("Reservations") && parts[0].equals("Reservations")) {
					lines.add(parts[0] + ": " + this.lastReservationID);
				} else  if (type.equals("Accounts") && parts[0].equals("Accounts")){
					lines.add(parts[0] + ": " + this.lastAccountID);
				} else {
					// otherwise, copies line to be re-added
					lines.add(line);
				}
		    }
			reader.close();	
		} catch (IOException e) {
            e.printStackTrace();
        }
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			Iterator<String> iter = lines.iterator();
			// copy lines back in, with adjustment
			while (iter.hasNext()) {
				writer.write(iter.next());
				writer.newLine();
			}
			writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}
	
}