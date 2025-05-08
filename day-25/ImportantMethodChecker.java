import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface ImportantMethod {
    String level() default "HIGH";
}

class FeatureSet {
    @ImportantMethod
    public void coreLogic() {
        System.out.println("Executing core logic");
    }

    @ImportantMethod(level = "MEDIUM")
    public void helperFunction() {
        System.out.println("Executing helper function");
    }

    public void regularMethod() {
        System.out.println("Just a regular method");
    }
}

public class ImportantMethodChecker {
    public static void main(String[] args) throws Exception {
        Method[] methods = FeatureSet.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(ImportantMethod.class)) {
                ImportantMethod annotation = method.getAnnotation(ImportantMethod.class);
                System.out.println("Method: " + method.getName() + ", Level: " + annotation.level());
            }
        }
    }
}
