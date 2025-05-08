import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonField {
    String name();
}

class User {
    @JsonField(name = "user_name")
    private String username;

    @JsonField(name = "user_email")
    private String email;

    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

class JsonSerializer {
    public static String toJson(Object obj) {
        Map<String, String> jsonElements = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(JsonField.class)) {
                JsonField annotation = field.getAnnotation(JsonField.class);
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    jsonElements.put(annotation.name(), String.valueOf(value));
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<String, String> entry : jsonElements.entrySet()) {
            json.append("\"").append(entry.getKey()).append("\": \"").append(entry.getValue()).append("\", ");
        }

        if (!jsonElements.isEmpty()) {
            json.setLength(json.length() - 2);
        }

        json.append("}");
        return json.toString();
    }
}

public class JsonFieldExample {
    public static void main(String[] args) {
        User user = new User("john_doe", "john@example.com", "secret");
        String json = JsonSerializer.toJson(user);
        System.out.println(json);
    }
}
