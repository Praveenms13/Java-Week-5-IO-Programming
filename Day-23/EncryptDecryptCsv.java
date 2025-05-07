import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class EncryptDecryptCsv {

    private static final String ALGORITHM = "AES";
    private static final String ENCRYPTION_KEY = "1234567890123456";

    public static void main(String[] args) {
        try {
            String csvFilePath = "employees.csv";
            String encryptedCsvFilePath = "employees_encrypted.csv";
            String decryptedCsvFilePath = "employees_decrypted.csv";

            List<String[]> employeeData = Arrays.asList(
                    new String[]{"ID", "Name", "Email", "Salary"},
                    new String[]{"1", "Alice", "alice@example.com", "50000"},
                    new String[]{"2", "Bob", "bob@example.com", "60000"}
            );
            writeEncryptedCsv(employeeData, encryptedCsvFilePath);

            List<String[]> decryptedData = readDecryptedCsv(encryptedCsvFilePath);
            writeCsv(decryptedData, decryptedCsvFilePath);
            System.out.println("Encryption and Decryption Done!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String encrypt(String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }
    private static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }
    public static void writeEncryptedCsv(List<String[]> data, String csvFilePath) throws Exception {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvFilePath))) {
            for (String[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    if (i == 2) {
                        row[i] = encrypt(row[i]);
                    }
                    if (i == 3) {
                        row[i] = encrypt(row[i]);
                    }
                }
                writer.write(String.join(",", row));
                writer.newLine();
            }
            System.out.println("Encrypted CSV written.");
        }
    }

    public static List<String[]> readDecryptedCsv(String csvFilePath) throws Exception {
        List<String[]> decryptedData = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(csvFilePath));

        for (String line : lines) {
            String[] row = line.split(",");
            for (int i = 0; i < row.length; i++) {
                if (i == 2) {
                    row[i] = decrypt(row[i]);
                }
                if (i == 3) {
                    row[i] = decrypt(row[i]);
                }
            }
            decryptedData.add(row);
        }
        System.out.println("Decrypted CSV read.");
        return decryptedData;
    }

    public static void writeCsv(List<String[]> data, String csvFilePath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvFilePath))) {
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
        }
        System.out.println("Decrypted CSV written.");
    }
}
