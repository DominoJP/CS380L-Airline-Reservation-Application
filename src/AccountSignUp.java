import  java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * a) Design Documentation: "AccountSignUp"
 * b) Date of Creation: November 3, 2023
 * c) @author Sayra Reyes 
 * d) Description: this class enables users to input their first and last name, email, password and id, 
 * 	  writes this information to a text file and later reads and displays the contents of the file
 * 	  using the BufferedReader and BufferedWriter. 
 * e) Function: writeToFile method, readFromFile, Setters and Getters these functions enable users to
 *	  to store their personal information and later retrieve and display. 
 * f) Data Structures: N/A
 * g) Algorithms: N/A 
 * 
 * Note: This is a simple example for educational purposes. 
 * In a real-world scenario, consider more robust practices for security.
 */

public class AccountSignUp {
    
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private int id;
    

    public AccountSignUp() {

    }


    public static void writeToFile(String email, String password, String firstname, String lastname) {
    	
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Database/Customers.txt", true))) {
        	IDGenerator idGen = new IDGenerator();
        	int id = idGen.generateAccountID();
        	// + "" typecasts int to String
        	writer.write(id + "");
        	writer.write(", ");
        	writer.write(firstname + " " + lastname);
            writer.write(", ");
            writer.write(email);
            writer.write(", ");
            writer.write(password);
            writer.write(", ");
            writer.write("CUSTOMER");
            // , on last index reads into String[] parts last index, causing read error
            // writer.write(", ");
            
           // writer.write(Integer.toString(id));
            writer.newLine();     // Add a newline character to separate entries

           System.out.println("Account information saved to " + "Customers.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Database/Customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Setter method to set email. 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter method to set password. 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter method to set first name.
     * @param firstname
     */
    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Setter method to set last name. 
     * @param lastname
     */
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Settet method to set id. 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Getter method 
     * @param email
     */
    public void getEmail(String email) {
    	return;
    }

    /**
     * Getter method 
     * @param password
     */
    public void getPassword(String password) {
    	return;
    }

    /**
     * Getter method 
     * @param name
     */
    public void getName(String name) {
    	return;
    }

    /**
     * Getter method 
     * @param id
     */
    public void getId(int id) {
    	return;
    }

	
}

