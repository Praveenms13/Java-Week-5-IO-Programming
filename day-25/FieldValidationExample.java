import java.lang.annotation.*;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class User {
    @MaxLength(10)
    private String username;

    public User(String username) {
        this.username = username;
        validateMaxLength();
    }

    private void validateMaxLength() {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(MaxLength.class)) {
                MaxLength maxLength = field.getAnnotation(MaxLength.class);
                field.setAccessible(true);
                try {
                    Object value = field.get(this);
                    if (value instanceof String && ((String) value).length() > maxLength.value()) {
                        throw new IllegalArgumentException("Field " + field.getName() + " exceeds max length of " + maxLength.value());
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

public class FieldValidationExample {
    public static void main(String[] args) {
        new User("JohnDoe");           // Valid
        new User("VeryLongUsername");  // Throws exception
    }
}
