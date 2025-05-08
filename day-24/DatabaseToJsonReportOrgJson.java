import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class DatabaseToJsonReportOrgJson {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/my_database";
        String user = "root";
        String password = "password123";

        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "SELECT id, name, age, email FROM users";
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(sql);

        JSONArray jsonArray = new JSONArray();

        while (resultSet.next()) {
            JSONObject userObject = new JSONObject();
            userObject.put("id", resultSet.getInt("id"));
            userObject.put("name", resultSet.getString("name"));
            userObject.put("age", resultSet.getInt("age"));
            userObject.put("email", resultSet.getString("email"));
            jsonArray.put(userObject);
        }

        System.out.println(jsonArray.toString(4));

        resultSet.close();
        stmt.close();
        connection.close();
    }
}
