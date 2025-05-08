import java.lang.annotation.*;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RoleAllowed {
    String value();
}

class SecureService {
    @RoleAllowed("ADMIN")
    public void deleteUser() {
        System.out.println("User deleted");
    }

    @RoleAllowed("USER")
    public void viewProfile() {
        System.out.println("Profile viewed");
    }
}

class AccessController {
    private final String currentUserRole;

    public AccessController(String role) {
        this.currentUserRole = role;
    }

    public void invokeIfAllowed(Object obj, String methodName) {
        try {
            Method method = obj.getClass().getMethod(methodName);
            if (method.isAnnotationPresent(RoleAllowed.class)) {
                RoleAllowed roleAllowed = method.getAnnotation(RoleAllowed.class);
                if (roleAllowed.value().equals(currentUserRole)) {
                    method.invoke(obj);
                } else {
                    System.out.println("Access Denied!");
                }
            } else {
                method.invoke(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class RoleBasedAccessExample {
    public static void main(String[] args) {
        SecureService service = new SecureService();
        AccessController adminAccess = new AccessController("ADMIN");
        AccessController userAccess = new AccessController("USER");

        adminAccess.invokeIfAllowed(service, "deleteUser");
        userAccess.invokeIfAllowed(service, "deleteUser");
        userAccess.invokeIfAllowed(service, "viewProfile");
    }
}
