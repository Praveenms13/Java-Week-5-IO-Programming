import com.fasterxml.jackson.databind.ObjectMapper;
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

public class ListToJsonArrayJackson {
    public static void main(String[] args) throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 1200.50));
        products.add(new Product("Phone", 699.99));

        ObjectMapper mapper = new ObjectMapper();
        String jsonArray = mapper.writeValueAsString(products);

        System.out.println(jsonArray);
    }
}
