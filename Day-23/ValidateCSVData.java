import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class ValidateCSVData {
    public static void main(String[] args) {
        String file = "employees.csv";
        Pattern emailPattern = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,6}$");
        Pattern phonePattern = Pattern.compile("^\\d{10}$");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String header = br.readLine();
            int lineNum = 1;

            while ((line = br.readLine()) != null) {
                lineNum++;
                String[] data = line.split(",");

                if (data.length < 3) {
                    System.out.println("Line " + lineNum + ": Incomplete record -> " + line);
                    continue;
                }

                String name = data[0].trim();
                String email = data[1].trim();
                String phone = data[2].trim();

                boolean valid = true;

                if (!emailPattern.matcher(email).matches()) {
                    System.out.println("Line " + lineNum + ": Invalid email format -> " + email);
                    valid = false;
                }

                if (!phonePattern.matcher(phone).matches()) {
                    System.out.println("Line " + lineNum + ": Invalid phone number -> " + phone);
                    valid = false;
                }

                if (!valid) {
                    System.out.println("Invalid Record: " + line);
                    System.out.println("-----------------------------------");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
