# Java-Launcher

# UML Relationships: Association, Directed Association, Aggregation, and Composition

This document explains the differences between **Association**, **Directed Association**, **Aggregation**, and **Composition** in UML with short Java examples.

---

## Table of Contents
- [Association](#association)
- [Directed Association](#directed-association)
- [Aggregation](#aggregation)
- [Composition](#composition)

---

### Association
Association is the most basic relationship in UML. It represents a connection between two classes. Both classes can exist independently of each other.

<details>
<summary>Java Example</summary>

```java
class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Course {
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
}

public class Main {
    public static void main(String[] args) {
        Student student = new Student("John");
        Course course = new Course("Mathematics");

        System.out.println(student.getName() + " is taking " + course.getCourseName());
    }
}
```
</details>

---

### Directed Association
Directed Association shows a relationship where one class knows about or uses another class. The direction is indicated with an arrow.

<details>
<summary>Java Example</summary>

```java
class Car {
    private String model;

    public Car(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }
}

class Driver {
    private Car car;

    public Driver(Car car) {
        this.car = car;
    }

    public void drive() {
        System.out.println("Driving a " + car.getModel());
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new Car("Tesla Model 3");
        Driver driver = new Driver(car);

        driver.drive();
    }
}
```
</details>

---

### Aggregation
Aggregation represents a "whole-part" relationship where the parts can exist independently of the whole. The relationship is indicated with a hollow diamond.

<details>
<summary>Java Example</summary>

```java
import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void showBooks() {
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book("1984");
        Book book2 = new Book("Brave New World");

        Library library = new Library();
        library.addBook(book1);
        library.addBook(book2);

        library.showBooks();
    }
}
```
</details>

---

### Composition
Composition is a strong "whole-part" relationship where the parts cannot exist without the whole. The relationship is indicated with a filled diamond.

<details>
<summary>Java Example</summary>

```java
class Engine {
    private String type;

    public Engine(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class Car {
    private Engine engine;

    public Car(String engineType) {
        this.engine = new Engine(engineType);
    }

    public void showCarDetails() {
        System.out.println("Car with " + engine.getType() + " engine.");
    }
}

public class Main {
    public static void main(String[] args) {
        Car car = new Car("V8");
        car.showCarDetails();
    }
}
```
</details>

---

### Summary of Differences

| Relationship       | Dependency Type  | Lifetime of Part   | UML Symbol        | Arrow Example |
|--------------------|------------------|--------------------|-------------------| --------------|
| **Association**    | Weak             | Independent        | Simple Line       | Student ↔ Course |
| **Directed Association** | Directional     | Independent        | Line with Arrow   | Driver → Car |
| **Aggregation**    | Whole-Part       | Independent        | Hollow Diamond    | Library ◇→ Book |
| **Composition**    | Whole-Part       | Dependent          | Filled Diamond    | Car ◆→ Engine |

---

Feel free to contribute or suggest improvements by submitting a pull request!
