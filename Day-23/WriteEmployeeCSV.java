import java.io.FileWriter;
import java.io.IOException;

public class WriteEmployeeCSV {
    public static void main(String[] args) {
        String file = "employees.csv";

        try (FileWriter writer = new FileWriter(file)) {
            writer.append("ID,Name,Department,Salary\n");
            writer.append("101,John Smith,Engineering,70000\n");
            writer.append("102,Jane Doe,Marketing,65000\n");
            writer.append("103,David Brown,Finance,60000\n");
            writer.append("104,Linda White,HR,58000\n");
            writer.append("105,Mark Black,Engineering,72000\n");
            System.out.println("CSV file written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
