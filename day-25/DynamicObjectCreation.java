import java.lang.reflect.Constructor;

class Student {
    String name;

    public Student() {
        this.name = "Default Student";
    }

    public void display() {
        System.out.println("Student Name: " + name);
    }
}

public class DynamicObjectCreation {
    public static void main(String[] args) {
        try {
            Class<?> cls = Class.forName("Student");
            Constructor<?> constructor = cls.getConstructor();
            Object studentObject = constructor.newInstance();

            Method displayMethod = cls.getMethod("display");
            displayMethod.invoke(studentObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
