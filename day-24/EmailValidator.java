import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.IOException;

public class EmailValidator {
    public static void main(String[] args) throws IOException {
        File schemaFile = new File("email-validation-schema.json");
        JSONObject schemaJson = new JSONObject(new JSONTokener(schemaFile.toURI().toURL().openStream()));
        Schema schema = SchemaLoader.load(schemaJson);
        String json = "{ \"email\": \"invalid-email@domain\" }";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(json);

        try {
            schema.validate(new JSONObject(jsonNode.toString()));
            System.out.println("Email is valid.");
        } catch (org.everit.json.schema.ValidationException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }
}
