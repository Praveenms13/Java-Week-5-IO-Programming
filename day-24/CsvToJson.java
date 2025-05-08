import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.File;
import java.io.IOException;

public class CsvToJson {
    public static void main(String[] args) throws IOException {
        File csvFile = new File("data.csv");

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        JsonNode jsonNode = csvMapper.readerFor(JsonNode.class).with(schema).readTree(csvFile);

        System.out.println(jsonNode.toString());
    }
}
