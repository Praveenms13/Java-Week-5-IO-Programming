import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Todo {
    String task();
    String assignedTo();
    String priority() default "MEDIUM";
}

class ProjectModule {
    @Todo(task = "Implement login functionality", assignedTo = "Alice", priority = "HIGH")
    public void loginFeature() {
        System.out.println("Login feature");
    }

    @Todo(task = "Add search filter", assignedTo = "Bob")
    public void searchFeature() {
        System.out.println("Search feature");
    }

    public void completedFeature() {
        System.out.println("This feature is complete");
    }
}

public class TodoChecker {
    public static void main(String[] args) {
        Method[] methods = ProjectModule.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Todo.class)) {
                Todo todo = method.getAnnotation(Todo.class);
                System.out.println("Method: " + method.getName());
                System.out.println("  Task: " + todo.task());
                System.out.println("  Assigned To: " + todo.assignedTo());
                System.out.println("  Priority: " + todo.priority());
            }
        }
    }
}
