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
	
	public static boolean validateCredentials(String email, char[] password) {
		// FIXME: temp file
		String file = "TestAccounts";
		
		// FIXME: close BufferedReader
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            
            while (line != null) {
                if (line.equals(email)) {
                	line = reader.readLine();
                	if (line.equals(String.valueOf(password))) {
                		reader.close();
                		return true;
                	}
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
		
}