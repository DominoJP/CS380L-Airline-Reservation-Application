import java.io.*;
import java.util.ArrayList;

public class ManagerIO {
	private ArrayList<Manager> managers;
	private String managerPath = "src/sample.database/ManagerAccount.txt";
	
	public ManagerIO() {
		managers = new ArrayList<Manager>();
	}
	
	public void getManagers() {
		int managerID = -1;
		String manager = null;
		String managerPassword = null;
		
		try(BufferedReader in = new BufferedReader(new FileReader(managerPath))){
			while(in.ready()) {
				String line = in.readLine();
				String[] managerInfo = line.split(": ");
			}
		}catch(IOException e){
			e.printStackTrace();
			return;
		}
	}
}
