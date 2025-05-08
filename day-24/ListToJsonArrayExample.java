import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class ListToJsonArrayExample {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 1200.50));
        products.add(new Product("Phone", 699.99));

        Gson gson = new Gson();
        String jsonArray = gson.toJson(products);

        System.out.println(jsonArray);
    }
}
