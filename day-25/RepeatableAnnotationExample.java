import java.lang.annotation.*;
import java.lang.reflect.Method;

@Repeatable(BugReports.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReport {
    String description();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BugReports {
    BugReport[] value();
}

class BugTracker {
    @BugReport(description = "NullPointerException when input is null")
    @BugReport(description = "ArrayIndexOutOfBoundsException in loop")
    public void process() {
        System.out.println("Processing...");
    }
}

public class RepeatableAnnotationExample {
    public static void main(String[] args) throws Exception {
        Method method = BugTracker.class.getMethod("process");

        if (method.isAnnotationPresent(BugReports.class)) {
            BugReports reports = method.getAnnotation(BugReports.class);
            for (BugReport report : reports.value()) {
                System.out.println("Bug: " + report.description());
            }
        }
    }
}
