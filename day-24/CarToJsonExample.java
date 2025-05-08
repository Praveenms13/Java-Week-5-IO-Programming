import com.google.gson.Gson;

class Car {
    String brand;
    String model;
    int year;

    Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
}

public class CarToJsonExample {
    public static void main(String[] args) {
        Car car = new Car("Toyota", "Camry", 2022);

        Gson gson = new Gson();
        String json = gson.toJson(car);

        System.out.println(json);
    }
}
