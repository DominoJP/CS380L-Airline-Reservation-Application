import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class from which new unique IDs are generated, reading from/writing to IDGeneratorHelper.txt.
 */
public class IDGenerator {
	private final String filePath = "src/Database/IDGeneratorHelper.txt";
	private String idType;
	private int lastID;
	ArrayList<String> lines = new ArrayList<>();
	StringBuilder rewrittenFile;
	
	/**
	 * Constructor.
	 * @param type of Class for which to generate ID
	 */
	public IDGenerator(String type) {
		idType = type;
		lines = new ArrayList<>();
		rewrittenFile = new StringBuilder();
		setLastID();
	}
	
	/**
	 * Method which generates ID based on running count, updated into IDGeneratorHelper.txt.
	 * @return newly-generated unique ID
	 */
	public int generateID() {
		lastID++;
		updateFile();
		return lastID;
	}
	
	/**
	 * Method to read the last ID generated and assign it as local variable int lastID.
	 */
	private void setLastID() {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(": ");
				switch (idType) {
					case "RESERVATIONS":
						if (parts[0].equals("Reservations"))
							this.lastID = Integer.parseInt(parts[1]);
						break;
					// case ...
				}
		    }
			reader.close();
		} catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Method to update the IDGeneratorHelper.txt with newly-generated lastID.
	 */
	private void updateFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(": ");
				switch (idType) {
					case "RESERVATIONS":
						if (parts[0].equals("Reservations")) {
							StringBuilder str = new StringBuilder();
							// substitutes in newly-generated ID
							str.append(parts[0] + ": " + this.lastID);
							lines.add(str.toString());
						} else {
							// otherwise, copies line to be re-added
							lines.add(line);
						}
						break;
					// case ...
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
