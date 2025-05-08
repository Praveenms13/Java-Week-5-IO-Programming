import java.lang.reflect.Field;
import java.util.Map;

public class ObjectMapper {

    public static <T> T toObject(Class<T> clazz, Map<String, Object> properties) throws Exception {
        T instance = clazz.getDeclaredConstructor().newInstance();

        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            String fieldName = entry.getKey();
            Object fieldValue = entry.getValue();

            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(instance, fieldValue);
            } catch (NoSuchFieldException e) {
                System.out.println("Field not found: " + fieldName);
            }
        }

        return instance;
    }

    public static void main(String[] args) {
        try {
            Map<String, Object> properties = Map.of(
                    "name", "John Doe",
                    "age", 30
            );

            Person person = toObject(Person.class, properties);
            System.out.println(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Person {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + '}';
    }
}
