import java.io.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
   Class with static method for validation of login credentials.
  @author Jevy Miranda
  @version 1.0
*/

public class AccountSignIn {
	private int id;
	private String email;
	private char[] password;
	
	/**
	   Constructor.
	*/
	
	public AccountSignIn() {
		
	}
	
	public static boolean validateCredentials(String email, char[] password) {
		// FIXME: temp file
		String file = "src/Database/AccountsTemp";
		
		// FIXME: close BufferedReader
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			 String line;
			 while ((line = reader.readLine()) != null) {
				 String[] parts = line.split(", ");
				 // compare email & password
				 if (parts[2].equals(email) && parts[3].equals(String.valueOf(password))) {
					 reader.close();
					 return true;
				 }
                line = reader.readLine();

            }
            reader.close();
            return false;
	    } catch (IOException e) {
	    	e.printStackTrace();
        }
		
		return false;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public char[] getPassword() {
		return this.password;
	}
		
}
