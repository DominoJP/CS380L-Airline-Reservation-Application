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
	private static final String FILE_PATH = "Database/TestAccountSignUp.txt";
	private int id;
	private String email;
	private char[] password;
	
	/**
	   Constructor.
	*/
	
	public AccountSignIn() {
		
	}
	
	public boolean validateCredentials(String email, char[] password) {

		try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
			 String line;
			 
			 while ((line = reader.readLine()) != null) {
				 String[] parts = line.split(", ");
				 // compare email & password
				 if (parts[2].equals(email) && parts[3].equals(String.valueOf(password))) {
					 id = Integer.parseInt(parts[0]);
					 email = parts[2];
					 reader.close();
					 return true;
				 }

            }
			 
            reader.close();
            
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
