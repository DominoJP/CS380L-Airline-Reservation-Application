import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * @author: Sayra Reyes 
 * @version: 1.0
 */
public class AccountSignUp {


	public static void main(String[] args) throws IOException {
			
	try(BufferedReader reader1 = new BufferedReader(new FileReader("TestAccountSignUp.txt"))){
		String line;
		
		while((line = reader1.readLine()) != null) {
			System.out.println(line);
		}
	} catch(IOException e) {
		e.printStackTrace();
	   }
	}
}
