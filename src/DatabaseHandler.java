import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    public List<String> readFromFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public boolean writeToFile(String filePath, List<Reservation> reservations) {
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            
            for(Reservation res : reservations) {
                writer.write(res.toString()); 
                writer.newLine();
            }
            
            return true;
            
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
