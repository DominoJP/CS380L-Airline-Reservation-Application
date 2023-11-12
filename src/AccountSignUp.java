import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * AccountSignUp is a java program that allows the user to input their email and password,
 * writes to the text file, and later reads and displays from the text file using BufferedReader and BufferedWriter.
 * 
 * Note: This is a simple example for educational purposes. In a real-world scenario, consider more robust practices for security.
 * @author Sayra Reyes 
 * @version 1.0
 */

public class AccountSignUp {
    
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private int id;
    

    public AccountSignUp() {

    }

 /*
    public static void main(String[] args) {
        
    	Scanner scanner = new Scanner(System.in);

        // Get email, password, name, and id from the user
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Id: ");
        int id = scanner.nextInt();

        // Create an instance of the class to utilize instance methods
        AccountSignUp accountSignUp = new AccountSignUp();
        accountSignUp.setEmail(email);
        accountSignUp.setPassword(password);
        accountSignUp.setName(name);
        accountSignUp.setId(id);

        // Check for duplicates
        if (!accountSignUp.isEmailDuplicate()) {
            // Write email and password to a text file
            accountSignUp.writeToFile();
        } else {
            System.out.println("Email already exists. Email is not saved."); //displays message 
        }

        // Read and display email and password from the text file
        accountSignUp.readFromFile();
        
        // Close scanner
        scanner.close();
    }
*/
    public static void writeToFile(int id, String email, String password, String firstname, String lastname) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("TestAccountSignUp.txt", true))) {
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

           // System.out.println("Account information saved to " + "TestAccountSignUp.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*
    private void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("TestAccountSignUp.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
    
    
    
   /*
    private boolean isEmailDuplicate() {
        try (BufferedReader reader = new BufferedReader(new FileReader("TestAccountSignUp.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    */
    
    
    
    

    // Getter and Setter methods
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void FirstName(String firstname) {
        this.firstname = firstname;
    }
    
    public void setLastName(String lastname) {
        this.lastname = lastname;
    }


    public void setId(int id) {
        this.id = id;
    }
    
    public void getEmail(String email) {
    	return; 
    }
    
    public void getPassword(String password) {
    	return;
    }
    
    public void getName(String name) {
    	return; 
    }
    
    public void getId(int id) {
    	return; 
    }
}





