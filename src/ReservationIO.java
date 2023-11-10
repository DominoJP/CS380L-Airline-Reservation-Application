import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class with BufferedReader to instantiate reservations to attribute List<Reservation> of Account Object.
 * @author Jevy Miranda
 * @version 1.0
 */
public class ReservationIO {
	private static final String FILE_PATH = "src/Database/Reservation.txt";
	// private static final int RESERVATION_ID_INDEX = 0;
	private Account account;
	private boolean isAssociatedReservation;
	private int reservationID;
	// private ArrayList<Integer> reservationIDs;
	// private TreeSet<Integer> flightIDs;
	// private ArrayList<BigDecimal> pricing;
	private int accountID;
	private int flightID;
	private LocalDateTime bookingDateTime;
	private BigDecimal totalPricing;
	private ArrayList<String> passengers;
	
	/**
	 * Constructor.
	 * @param : active account
	 */
	public ReservationIO(Account account) {
		this.account = account;
		accountID = account.getAccountNumber();
	}
	
	/**
	 * Method to instantiate reservations associated with account to ArrayList<Reservation> attribute of Account Object.
	 */
	public void instantiateReservations() {
		// reservationIDs = new ArrayList<>();
		// flightIDs = new TreeSet<Integer>();
		// pricing = new ArrayList<>();
		passengers = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			// ArrayList<String> lines = new ArrayList<>();
		    String line;
		    while ((line = reader.readLine()) != null) {
		    	if(line.length() == 0) { // Skip whitespace.
		    		continue;
		    	}
		    	
		    	// switch-case for flexibility in event of changes to format
		    	// once format is finalized, can simply read line-by-line
		    	String[] parts = line.split(": ");
		    	switch (parts[0]) {
		    		case "Reservation ID":
		    			reservationID = Integer.parseInt(parts[1]);
		    			break;
		    		case "Account ID":
		    			if (Integer.parseInt(parts[1]) == account.getAccountNumber()) {
		    				isAssociatedReservation = true;
		    			} else {
		    				isAssociatedReservation = false;
		    			}
		    			break;
		    		case "Flight Number":
		    			flightID = Integer.parseInt(parts[1]);
		    			break;
		    		case "Date of Booking":
		    			bookingDateTime = LocalDateTime.parse(parts[1]);
		    			break;
		    		case "Cabin Class":
		    			//
		    			break;
		    		case "Total Pricing":
		    			totalPricing = new BigDecimal(parts[1]);
		    			break;
		    		case "/t" + "Name":
		    			passengers.add(parts[1]);
		    			break;
		    		case "--Reservation End--":
		    			if (isAssociatedReservation) {
		    			// BUILD RESERVATION & ADD TO RESERVATION HISTOY HERE
		    				FlightsTestReader flightsReader = new FlightsTestReader(flightID);
		    				account.addReservationHistory(new Reservation(reservationID, account, flightsReader.getFlight(), passengers, totalPricing, bookingDateTime));
		    				isAssociatedReservation = false;
		    			}
		    			break;
	    			default:
		    	}
		    	
		    }
		    reader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
}
