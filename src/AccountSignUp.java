import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Account Sign Up - a program to read and write.
 * This program checks if exists in a text file and ensures that the same is not 
 * added multiple times.
 * @author: Sayra Reyes
 * @version: 1.0
 */
public class AccountSignUp {

	public static void main(String[] args) throws IOException {


	//check if the "email password" pair already exists.
	boolean pairExists = false;

	//Create a BufferedReader to read from a file text.
	 try(BufferedReader reader1 = new BufferedReader(new FileReader("TestAccountSignUp.txt"))){
		String line;

		while((line = reader1.readLine()) != null) {
			if(line.equals("0001, name, @email, password, CUSTOMER")) { //checks if line matches.
				pairExists = true;
				break;
			}
		}
	} catch(IOException e) {
		e.printStackTrace();
	   }

	//If the pair does not exist, then add it to the file.
	if(!pairExists) {

	//Create a BufferedWriter to write email and password.
	 try(BufferedWriter writer = new BufferedWriter(new FileWriter("TestAccountSignUp.txt", true))){
			String str = "\t0001" + "," + "\tname" + "," + "\t@email.com" + "," + "\tpassword" + "," + "\tCUSTOMER";

			//Write the pair to the file and move to the next line.
			writer.write(str);
			writer.close();

	} catch(IOException e) {
		e.printStackTrace();
	     }
      }
   }
}
