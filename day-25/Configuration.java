import java.lang.reflect.Field;

public class Configuration {
    private static String apiKey = "12345_initial_key";

    public static void main(String[] args) {
        try {
            Field field = Configuration.class.getDeclaredField("apiKey");
            field.setAccessible(true);

            System.out.println("Original API Key: " + field.get(null));

            field.set(null, "67890_new_key");

            System.out.println("Modified API Key: " + field.get(null));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
