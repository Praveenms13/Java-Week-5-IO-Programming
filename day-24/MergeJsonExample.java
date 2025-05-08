import org.json.JSONObject;

public class MergeJsonExample {
    public static void main(String[] args) {
        JSONObject obj1 = new JSONObject();
        obj1.put("name", "Alice");
        obj1.put("age", 25);

        JSONObject obj2 = new JSONObject();
        obj2.put("email", "alice@example.com");
        obj2.put("city", "New York");
        for (String key : obj2.keySet()) {
            obj1.put(key, obj2.get(key));
        }

        System.out.println(obj1.toString(4));
    }
}
