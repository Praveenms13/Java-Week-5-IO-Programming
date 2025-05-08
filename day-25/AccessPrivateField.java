import java.lang.reflect.Field;

class Person {
    private int age;

    public Person(int age) {
        this.age = age;
    }
}

public class AccessPrivateField {
    public static void main(String[] args) {
        try {
            Person person = new Person(25);

            Field ageField = Person.class.getDeclaredField("age");
            ageField.setAccessible(true);

            int ageValue = (int) ageField.get(person);
            System.out.println("Original age: " + ageValue);

            ageField.set(person, 35);
            int newAgeValue = (int) ageField.get(person);
            System.out.println("Modified age: " + newAgeValue);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
