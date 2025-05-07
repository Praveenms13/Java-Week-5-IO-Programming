import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SortEmployeeCSV {
    public static void main(String[] args) {
        String file = "employees.csv";
        List<String[]> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                employees.add(data);
            }
            employees.sort((a, b) -> Double.compare(Double.parseDouble(b[3]), Double.parseDouble(a[3])));
            System.out.println("Top 5 Highest-Paid Employees:");
            for (int i = 0; i < Math.min(5, employees.size()); i++) {
                String[] employee = employees.get(i);
                System.out.println("Name: " + employee[1]);
                System.out.println("Department: " + employee[2]);
                System.out.println("Salary: " + employee[3]);
                System.out.println("-------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
