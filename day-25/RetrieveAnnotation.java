import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Author {
    String name();
}

@Author(name = "John Doe")
class Book {
}

public class RetrieveAnnotation {
    public static void main(String[] args) {
        Class<Book> bookClass = Book.class;

        if (bookClass.isAnnotationPresent(Author.class)) {
            Author author = bookClass.getAnnotation(Author.class);
            System.out.println("Author: " + author.name());
        } else {
            System.out.println("No @Author annotation present.");
        }
    }
}
