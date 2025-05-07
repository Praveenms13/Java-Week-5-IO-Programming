import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConvertCSVToObjects {
    public static void main(String[] args) {
        String file = "students.csv";
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0].trim());
                String name = data[1].trim();
                int age = Integer.parseInt(data[2].trim());
                int marks = Integer.parseInt(data[3].trim());

                Student student = new Student(id, name, age, marks);
                students.add(student);
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Print all Student objects
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
