import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FilterStudentCSV {
    public static void main(String[] args) {
        String file = "students.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int marks = Integer.parseInt(data[3]);

                if (marks > 80) {
                    System.out.println("ID: " + data[0]);
                    System.out.println("Name: " + data[1]);
                    System.out.println("Age: " + data[2]);
                    System.out.println("Marks: " + data[3]);
                    System.out.println("-------------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
