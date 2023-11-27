import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler {

    public static List<String> readFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }

    public static void writeFile(String filePath, List<String> content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : content) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}