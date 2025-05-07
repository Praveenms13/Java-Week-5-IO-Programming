import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class JsonCsvConverter {

    public static void main(String[] args) {
        try {
            // Choose operation
            convertJsonToCsv("students.json", "students.csv");
            convertCsvToJson("students.csv", "students_out.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void convertJsonToCsv(String jsonFilePath, String csvFilePath) throws Exception {
        String jsonText = Files.readString(Path.of(jsonFilePath));
        JSONArray jsonArray = new JSONArray(jsonText);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            JSONObject first = jsonArray.getJSONObject(0);
            List<String> headers = new ArrayList<>(first.keySet());
            writer.write(String.join(",", headers));
            writer.newLine();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                List<String> row = new ArrayList<>();
                for (String header : headers) {
                    row.add(obj.optString(header));
                }
                writer.write(String.join(",", row));
                writer.newLine();
            }

            System.out.println("JSON to CSV conversion done.");
        }
    }

    public static void convertCsvToJson(String csvFilePath, String jsonFilePath) throws Exception {
        List<String> lines = Files.readAllLines(Path.of(csvFilePath));
        if (lines.isEmpty()) return;

        String[] headers = lines.get(0).split(",");
        JSONArray jsonArray = new JSONArray();

        for (int i = 1; i < lines.size(); i++) {
            String[] values = lines.get(i).split(",");
            JSONObject obj = new JSONObject();
            for (int j = 0; j < headers.length; j++) {
                obj.put(headers[j], values[j]);
            }
            jsonArray.put(obj);
        }

        Files.writeString(Path.of(jsonFilePath), jsonArray.toString(2));
        System.out.println("CSV to JSON conversion done.");
    }
}
