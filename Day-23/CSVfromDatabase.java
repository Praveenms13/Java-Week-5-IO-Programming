import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CSVfromDatabase {

    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
        BufferedWriter bw = new BufferedWriter(new FileWriter("report.csv"));
        bw.write("Employee ID,Name,Department,Salary");
        bw.newLine();

        while (rs.next()) {
            String row = rs.getInt("id") + "," + rs.getString("name") + "," + rs.getString("dept") + "," + rs.getDouble("salary");
            bw.write(row);
            bw.newLine();
        }

        bw.close();
        rs.close();
        stmt.close();
        conn.close();
    }
}