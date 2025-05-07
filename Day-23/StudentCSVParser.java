import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentCSVParser {
    static class Student {
        private int id;
        private String name;
        private int age;
        private int marks;

        public Student(int id, String name, int age, int marks) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.marks = marks;
        }

        @Override
        public String toString() {
            return "Student [ID=" + id + ", Name=" + name + ", Age=" + age + ", Marks=" + marks + "]";
        }
    }

    public static void main(String[] args) {
        String file = "students.csv";
        List<Student> studentList = new ArrayList<>();

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
                studentList.add(student);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error reading or parsing the file: " + e.getMessage());
        }

        // Print all students
        for (Student s : studentList) {
            System.out.println(s);
        }
    }
}
