import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class IplCensorAnalyzer {

    public static void main(String[] args) throws Exception {
        String jsonInputFilePath = "ipl_matches.json";
        String csvInputFilePath = "ipl_matches.csv";
        String jsonOutputFilePath = "censored_ipl_matches.json";
        String csvOutputFilePath = "censored_ipl_matches.csv";

        processJsonData(jsonInputFilePath, jsonOutputFilePath);
        processCsvData(csvInputFilePath, csvOutputFilePath);
    }

    public static void processJsonData(String inputFile, String outputFile) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> matches = mapper.readValue(new File(inputFile), List.class);
        for (Map<String, Object> match : matches) {
            censorMatchData(match);
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), matches);
    }

    public static void processCsvData(String inputFile, String outputFile) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(inputFile));
        List<String[]> records = reader.readAll();
        for (int i = 1; i < records.size(); i++) {
            String[] record = records.get(i);
            censorCsvRecord(record);
        }
        CSVWriter writer = new CSVWriter(new FileWriter(outputFile));
        writer.writeAll(records);
        writer.close();
    }

    private static void censorMatchData(Map<String, Object> match) {
        String team1 = (String) match.get("team1");
        String team2 = (String) match.get("team2");
        match.put("team1", maskTeamName(team1));
        match.put("team2", maskTeamName(team2));
        match.put("player_of_match", "REDACTED");
    }

    private static void censorCsvRecord(String[] record) {
        record[1] = maskTeamName(record[1]);
        record[2] = maskTeamName(record[2]);
        record[6] = "REDACTED";
    }

    private static String maskTeamName(String teamName) {
        if (teamName != null && teamName.contains(" ")) {
            int spaceIndex = teamName.indexOf(" ");
            return teamName.substring(0, spaceIndex) + " ***";
        }
        return teamName;
    }
}
