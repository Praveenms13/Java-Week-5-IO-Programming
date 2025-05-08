import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadJsonExample {
    public static void main(String[] args) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get("data.json")));
        JSONObject obj = new JSONObject(content);

        String name = obj.getString("name");
        String email = obj.getString("email");

        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }
}
