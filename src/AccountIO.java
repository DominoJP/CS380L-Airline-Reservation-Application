import java.io.*;
import java.util.ArrayList;
import java.math.BigDecimal;

/**
 * The AccountIO class is meant to read from the Customers.txt file, create a list of Accounts from the file, then be able to give this information
 * in case if a manager wishes to view it or to open a pre-existing account
 * @author Logan Langewisch
 * @version 1.3, Last Modified November 30, 2023
 */


public class AccountIO {
	private ArrayList<Account> accounts;
	private static final String accountsPath = "src/Database/Customers.txt";
	private ReservationIO reservations;
	
	/**
	 * A constructor for the AccountIO class that initializes the ArrayList<Account> accounts;
	 */
	
	public AccountIO() {
		this.accounts = new ArrayList<Account>();
	}
	
	/**
	 * The readAccounts() method reads from the Customers.txt file to add all stored accounts in the ArrayList<Account> accounts
	 * variable
	 */
	
	public void readAccounts() {
		String name = null;
		String email = null;
		String password = null;
		int id = -1;
		
		try(BufferedReader in = new BufferedReader(new FileReader(this.accountsPath))){
			while(in.ready()) {
				String line = in.readLine();
				if(!line.equals(null)) {
					String[] account = line.split(", ");
					
					id = Integer.parseUnsignedInt(account[0]);
					name = account[1];
					email = account[2];
					password = account[3];
					
					Account customer = new Account(name, email, password, id);
					
					this.accounts.add(customer);
				}
			}
			in.close();
			
			this.sortAccounts();
			this.addReservations();
		}catch(IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	/**
	 * The sortAccounts() method uses a nested for-loop in order to sort the ArrayList of accounts 
	 * for easier reading of the stored accounts
	 */
	
	public void sortAccounts() {
		Account curr;
		int lowest;
		
		for(int i = 0; i < accounts.size(); i++) {
			curr = accounts.get(i);
			lowest = i;
			for(int j = i+1; j < accounts.size(); j++) {
				if(curr.getAccountNumber() < accounts.get(i).getAccountNumber()) {
					curr = accounts.get(j);
					lowest = j;
				}
			}
			
			accounts.set(i, accounts.get(lowest));
			accounts.set(lowest, curr);
		}
	}
	
	/**
	 * The addReservations() method uses the ReservationIO class to add all of the stored reservations into the individual accounts that
	 * were pulled from the file they were stored on
	 */
	
	public void addReservations() {
		for(int i = 0; i < accounts.size(); i++) {
			reservations.instantiateReservations(this.accounts.get(i));
		}
	}
	
	public ArrayList<Account> getAccounts(){
		return this.accounts;
	}
	
	/**
	 * The writeAccount method stores a new account that was made into the file were all accounts
	 * are stored
	 * @param account, an instance of Account that represents a new account that was created
	 * and is not going to be added in the file where all accounts are stored
	 */
	
	public void writeAccount(Account account) {
		try(BufferedWriter write = new BufferedWriter(new FileWriter(accountsPath, true))){
			write.write("\n");
			
			write.write(account.getAccountNumber() + ", " + account.getName()
				+ ", " + account.getEmail() + ", " + account.getPassword() + ", CUSTOMER");
			
			write.close();
		}catch(IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
}
