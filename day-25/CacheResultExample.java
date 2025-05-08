import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface CacheResult {
}

class ExpensiveService {
    private final Map<Integer, Integer> cache = new HashMap<>();

    @CacheResult
    public int computeSquare(int x) {
        if (cache.containsKey(x)) {
            System.out.println("Returning cached result for " + x);
            return cache.get(x);
        }

        System.out.println("Computing square for " + x);
        int result = x * x;

        cache.put(x, result);
        return result;
    }
}

public class CacheResultExample {
    public static void main(String[] args) throws Exception {
        ExpensiveService service = new ExpensiveService();
        Method method = ExpensiveService.class.getMethod("computeSquare", int.class);

        if (method.isAnnotationPresent(CacheResult.class)) {
            System.out.println(service.computeSquare(5));
            System.out.println(service.computeSquare(5));
            System.out.println(service.computeSquare(10));
            System.out.println(service.computeSquare(10));
        }
    }
}
