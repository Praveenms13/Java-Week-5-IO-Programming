import java.io.*;
import java.util.*;

public class DetectDuplicateCSV {

    public static void main(String[] args) {
        String filePath = "students.csv";
        Set<String> seenIds = new HashSet<>();
        List<String> duplicates = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String id = data[0].trim();

                if (seenIds.contains(id)) {
                    duplicates.add(line);
                } else {
                    seenIds.add(id);
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        if (duplicates.isEmpty()) {
            System.out.println("No duplicate records found.");
        } else {
            System.out.println("Duplicate records found:");
            for (String dup : duplicates) {
                System.out.println(dup);
            }
        }
    }
}
