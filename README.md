# Java-Launcher

# UML Relationships: Association, Directed Association, Aggregation, and Composition

This document explains the differences between **Association**, **Directed Association**, **Aggregation**, **Dependency**, **Multiplicity**, **Realization**, and **Inheritance** in UML with short Java examples.

---

## Table of Contents
- [Association](#association)
- [Directed Association](#directed-association)
- [Aggregation](#aggregation)
- [Composition](#composition)
- [Dependency](#dependency)
- [Multiplicity](#multiplicity)
- [Inheritance](#inheritance)
- [Realization](#realization)
- [Coupling](#Coupling)

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


### Dependency
Dependency represents a temporary relationship where one class uses another class but does not own it. It is the weakest type of relationship in UML and is shown with a dashed arrow (`---->`).

<details>
<summary>Java Example</summary>

```java
class Payment {
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount);
    }
}

class Order {
    public void completeOrder(double amount, Payment payment) {
        payment.processPayment(amount);
        System.out.println("Order completed.");
    }
}

public class Main {
    public static void main(String[] args) {
        Payment payment = new Payment();
        Order order = new Order();

        order.completeOrder(50.0, payment); // Temporary dependency
    }
}
```
</details>

---

### Multiplicity
Multiplicity defines the number of instances that can participate in a relationship between two classes. It specifies **how many objects** are involved in the association and is typically shown near the association lines in UML diagrams.

#### Common Multiplicity Examples

| Multiplicity     | Description                           | Example                          |
|------------------|---------------------------------------|----------------------------------|
| `1`              | Exactly one instance                 | A `Person` has exactly one `Passport`. |
| `0..1`           | Zero or one (optional)               | A `Person` may or may not have a `Car`. |
| `*` or `0..*`    | Zero or more                         | A `Library` has many `Books`.      |
| `1..*`           | One or more                          | A `Team` has at least one `Player`. |
| `3..5`           | A specific range of instances        | A `Group` has 3 to 5 `Members`.    |

<details>
<summary>Java Examples</summary>

#### **1. One-to-One (1:1)**
```java
class Person {
    private Passport passport;

    public Person(Passport passport) {
        this.passport = passport;
    }

    public Passport getPassport() {
        return passport;
    }
}

class Passport {
    private String number;

    public Passport(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
```

#### **2. One-to-Many (1:*)**
```java
import java.util.ArrayList;
import java.util.List;

class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }
}

class Book {
    private String title;

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
```

#### **3. Many-to-Many (*:*)**
```java
import java.util.ArrayList;
import java.util.List;

class Student {
    private String name;
    private List<Course> courses = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public void enroll(Course course) {
        courses.add(course);
        course.addStudent(this);
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }
}

class Course {
    private String courseName;
    private List<Student> students = new ArrayList<>();

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }
}
```
</details>

### Inheritance
Inheritance represents a relationship where a class derives from another class, inheriting its properties and methods. It is shown in UML with a solid line and a hollow triangle (`-----▷`).

#### Example:
```
        Animal
         ▲
         │
 ┌───────┴───────┐
 │               │
 Dog            Cat
```

- **Animal**: Base class (parent class).
- **Dog** and **Cat**: Subclasses (child classes).

<details>
<summary>Java Example</summary>

```java
// Base Class
class Animal {
    public void eat() {
        System.out.println("This animal eats food.");
    }
}

// Derived Class 1
class Dog extends Animal {
    public void bark() {
        System.out.println("The dog barks.");
    }
}

// Derived Class 2
class Cat extends Animal {
    public void meow() {
        System.out.println("The cat meows.");
    }
}

public class Main {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();

        dog.eat(); // Inherited method
        dog.bark();

        cat.eat(); // Inherited method
        cat.meow();
    }
}
```
</details>

### Realization
Realization represents the relationship between a class and an interface. A class implements an interface by providing concrete definitions for its methods. In UML, realization is shown with a **dashed line** and a **hollow triangle** (`- - - ▷`).

#### Example:
```
        <<interface>>
          Vehicle
             ▲
             │
  - - - - - - - - - -
   |                 |
  Car              Bike
```

### Coupling
What is Loose Coupling?
Loose Coupling is when two classes are independent and interact through an interface or an external method call, minimizing dependencies. This ensures flexibility, better testability, and improved code reusability.

What is Tight Coupling?
Tight Coupling occurs when a class is highly dependent on another class, often requiring direct instantiation or hardcoded dependencies. This makes code less flexible and harder to modify.

<details>
Disadvantages of Tight Coupling:

❌ Harder to modify since changes in one class may break dependent classes.
❌ Difficult to test because dependencies are fixed.
❌ Low reusability as tightly coupled components are difficult to separate.

Advantages of Loose Coupling:

✅ Improves testability as dependencies are injected when needed.
✅ Flexible and extendable, allowing different implementations.
✅ Encourages Dependency Injection, a key principle in software design.

<summary>Loose - Tight Coupling Example</summary>

```java
public abstract class Flyable {
    protected WeatherTower weatherTower;

    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        weatherTower.register(this);
    }

    public abstract void updateConditions();
}
```
Why is this Loose Coupling?

The Flyable class does not require WeatherTower at construction.
registerTower() allows dynamic assignment of the weather tower.
Flyable instances can exist independently before registering.


```java
public abstract class Flyable {
    protected WeatherTower weatherTower;

    public Flyable(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        weatherTower.register(this);
    }

    public abstract void updateConditions();
}
```
Why is this Tight Coupling?

Flyable objects cannot exist without a WeatherTower.
Hard to test Flyable objects independently.
No flexibility, all Flyable objects must be registered immediately.

![image](https://github.com/user-attachments/assets/caeeb815-3784-43cb-95df-20f2e9e0ed3c)

</details>


---

### Summary of Differences

| Relationship       | Dependency Type  | Lifetime of Part   | UML Symbol        | Arrow Example |
|--------------------|------------------|--------------------|-------------------| --------------|
| **Association**    | Weak             | Independent        | Simple Line       | Student ↔ Course |
| **Directed Association** | Directional     | Independent        | Line with Arrow   | Driver → Car |
| **Aggregation**    | Whole-Part       | Independent        | Hollow Diamond    | Library ◇→ Book |
| **Composition**    | Whole-Part       | Dependent          | Filled Diamond    | Car ◆→ Engine |
| **Dependency**     | Temporary        | Independent        | Dashed Arrow      | Order ----> Payment |
| **Multiplicity**   | Defines Quantity | Independent        | Numbers/Ranges    |
| **Inheritance**    | Generalization   | Dependent          | Hollow Triangle   | -▷, ----▷ |

---

Feel free to contribute or suggest improvements by submitting a pull request!
