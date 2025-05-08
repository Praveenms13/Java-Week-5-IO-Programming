import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonToXml {
    public static void main(String[] args) throws IOException {
        String jsonString = "{ \"name\": \"Alice\", \"age\": 30, \"email\": \"alice@example.com\" }";
        ObjectMapper jsonMapper = new ObjectMapper();
        JsonNode jsonNode = jsonMapper.readTree(jsonString);
        XmlMapper xmlMapper = new XmlMapper();
        String xmlString = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        System.out.println(xmlString);
    }
}
