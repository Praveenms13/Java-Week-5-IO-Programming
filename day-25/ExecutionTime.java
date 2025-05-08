import java.lang.reflect.*;

class MethodExecutionTimer {

    public static void measureExecutionTime(Object obj, String methodName, Object... args) {
        try {
            Method method = obj.getClass().getDeclaredMethod(methodName, getParameterTypes(args));
            method.setAccessible(true);

            long startTime = System.nanoTime();
            method.invoke(obj, args);
            long endTime = System.nanoTime();

            System.out.println("Execution time of " + methodName + ": " + (endTime - startTime) + " nanoseconds.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Class<?>[] getParameterTypes(Object... args) {
        Class<?>[] parameterTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        return parameterTypes;
    }
}

class Calculator {

    public void add(int a, int b) {
        try {
            Thread.sleep(500);  // Simulating some delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Addition result: " + (a + b));
    }

    public void multiply(int a, int b) {
        try {
            Thread.sleep(200);  // Simulating some delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Multiplication result: " + (a * b));
    }
}

public class ExecutionTime {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        MethodExecutionTimer.measureExecutionTime(calculator, "add", 5, 3);
        MethodExecutionTimer.measureExecutionTime(calculator, "multiply", 4, 2);
    }
}
