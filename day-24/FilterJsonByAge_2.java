import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class FilterJsonByAge_2 {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File("users.json"));
        for (JsonNode user : root) {
            if (user.has("age") && user.get("age").asInt() > 25) {
                // Print the user's details if their age is greater than 25
                System.out.println("Name: " + user.get("name").asText());
                System.out.println("Age: " + user.get("age").asInt());
                System.out.println("Email: " + user.get("email").asText());
                System.out.println();
            }
        }
    }
}
