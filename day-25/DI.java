import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Inject {
}

class SimpleDIContainer {
    private final Map<Class<?>, Object> instances = new HashMap<>();

    public void register(Class<?> clazz) throws Exception {
        if (!instances.containsKey(clazz)) {
            instances.put(clazz, clazz.getDeclaredConstructor().newInstance());
        }
    }

    public <T> T getInstance(Class<T> clazz) {
        return clazz.cast(instances.get(clazz));
    }

    public void injectDependencies(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Object dependency = getInstance(field.getType());
                field.set(obj, dependency);
            }
        }
    }
}

class DatabaseService {
    public void connect() {
        System.out.println("Connecting to the database...");
    }
}

class UserService {
    @Inject
    private DatabaseService databaseService;

    public void performAction() {
        System.out.println("UserService is performing an action...");
        databaseService.connect();
    }
}

public class DI {
    public static void main(String[] args) {
        try {
            SimpleDIContainer diContainer = new SimpleDIContainer();

            diContainer.register(DatabaseService.class);
            diContainer.register(UserService.class);

            UserService userService = diContainer.getInstance(UserService.class);
            diContainer.injectDependencies(userService);

            userService.performAction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
