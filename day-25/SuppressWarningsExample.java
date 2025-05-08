import java.util.ArrayList;

public class SuppressWarningsExample {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("Hello");
        list.add(123);

        for (Object item : list) {
            System.out.println(item);
        }
    }
}
