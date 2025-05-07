import java.io.*;
import java.util.*;

public class MergeCSVFiles {

    public static void main(String[] args) {
        String file1 = "students1.csv";
        String file2 = "students2.csv";
        String outputFile = "merged_students.csv";

        Map<String, String[]> map1 = new HashMap<>();
        Map<String, String[]> map2 = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                map1.put(data[0].trim(), new String[]{data[1].trim(), data[2].trim()});
            }
        } catch (IOException e) {
            System.err.println("Error reading file1: " + e.getMessage());
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                map2.put(data[0].trim(), new String[]{data[1].trim(), data[2].trim()});
            }
        } catch (IOException e) {
            System.err.println("Error reading file2: " + e.getMessage());
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            bw.write("ID,Name,Age,Marks,Grade\n");

            for (String id : map1.keySet()) {
                String[] part1 = map1.get(id);
                String[] part2 = map2.get(id);
                if (part2 != null) {
                    bw.write(String.format("%s,%s,%s,%s,%s\n", id, part1[0], part1[1], part2[0], part2[1]));
                } else {
                    System.out.println("Warning: ID " + id + " not found in second file.");
                }
            }

            System.out.println("Merged file written to " + outputFile);
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
        }
    }
}
