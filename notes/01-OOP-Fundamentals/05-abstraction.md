# Abstraction

## Definition

Abstraction is the process of hiding complex implementation details and showing only essential features of an object. It focuses on what an object does rather than how it does it.

## Core Principle

Hide complexity, expose simplicity.

## Why Abstraction Matters

1. **Simplicity:** Reduces complexity for users
2. **Focus:** Users focus on what to do, not how
3. **Flexibility:** Implementation can change without affecting users
4. **Maintainability:** Changes isolated to implementation
5. **Security:** Internal details remain hidden

## Ways to Achieve Abstraction

### 1. Abstract Classes
### 2. Interfaces

## Abstract Classes

Cannot be instantiated. May contain abstract and concrete methods.

### Syntax
```java
public abstract class Animal {
    // Concrete field
    protected String name;
    
    // Constructor
    public Animal(String name) {
        this.name = name;
    }
    
    // Abstract method - no implementation
    public abstract void makeSound();
    
    // Concrete method - has implementation
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
}
```

### Implementation
```java
public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " barks: Woof!");
    }
}

public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }
    
    @Override
    public void makeSound() {
        System.out.println(name + " meows: Meow!");
    }
}

// Usage
Animal dog = new Dog("Buddy");
dog.makeSound();  // Output: Buddy barks: Woof!
dog.sleep();      // Output: Buddy is sleeping
```

## Interfaces

Contract defining behavior. All methods are abstract by default (before Java 8).

### Syntax
```java
public interface Vehicle {
    // Abstract methods (implicitly public abstract)
    void start();
    void stop();
    void accelerate(double speed);
    
    // Default method (Java 8+)
    default void honk() {
        System.out.println("Honk! Honk!");
    }
    
    // Static method (Java 8+)
    static void printType() {
        System.out.println("This is a vehicle");
    }
}
```

### Implementation
```java
public class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car is starting");
    }
    
    @Override
    public void stop() {
        System.out.println("Car is stopping");
    }
    
    @Override
    public void accelerate(double speed) {
        System.out.println("Car accelerating to " + speed + " km/h");
    }
}

// Usage
Vehicle car = new Car();
car.start();
car.accelerate(60);
car.honk();  // Uses default implementation
```

## Abstract Class vs Interface

### Abstract Class
- Can have constructors
- Can have instance variables
- Can have concrete methods
- Can have abstract methods
- Single inheritance only (extends one class)
- Use when classes share common implementation

### Interface
- No constructors
- Only constants (public static final)
- All methods abstract (before default methods)
- Can have default and static methods (Java 8+)
- Multiple inheritance allowed (implements multiple interfaces)
- Use when defining a contract

### When to Use Which

**Use Abstract Class when:**
- Classes share common implementation
- Need to define non-static fields
- Need constructors
- Want to provide default behavior

**Use Interface when:**
- Defining capability or behavior
- Need multiple inheritance
- Unrelated classes should share methods
- Defining a contract

## Real-World Example: Database Connection

```java
// Abstract class with common functionality
public abstract class DatabaseConnection {
    protected String connectionString;
    protected boolean connected;
    
    public DatabaseConnection(String connectionString) {
        this.connectionString = connectionString;
        this.connected = false;
    }
    
    // Abstract methods - must be implemented
    public abstract void connect();
    public abstract void disconnect();
    public abstract ResultSet executeQuery(String query);
    
    // Concrete method - common for all databases
    public boolean isConnected() {
        return connected;
    }
    
    public void logQuery(String query) {
        System.out.println("Executing: " + query);
    }
}

// MySQL implementation
public class MySQLConnection extends DatabaseConnection {
    public MySQLConnection(String connectionString) {
        super(connectionString);
    }
    
    @Override
    public void connect() {
        System.out.println("Connecting to MySQL: " + connectionString);
        connected = true;
    }
    
    @Override
    public void disconnect() {
        System.out.println("Disconnecting from MySQL");
        connected = false;
    }
    
    @Override
    public ResultSet executeQuery(String query) {
        logQuery(query);
        // MySQL-specific query execution
        return null;
    }
}

// PostgreSQL implementation
public class PostgreSQLConnection extends DatabaseConnection {
    public PostgreSQLConnection(String connectionString) {
        super(connectionString);
    }
    
    @Override
    public void connect() {
        System.out.println("Connecting to PostgreSQL: " + connectionString);
        connected = true;
    }
    
    @Override
    public void disconnect() {
        System.out.println("Disconnecting from PostgreSQL");
        connected = false;
    }
    
    @Override
    public ResultSet executeQuery(String query) {
        logQuery(query);
        // PostgreSQL-specific query execution
        return null;
    }
}

// Usage - abstraction in action
DatabaseConnection db = new MySQLConnection("localhost:3306/mydb");
db.connect();
db.executeQuery("SELECT * FROM users");
db.disconnect();
// Can switch to PostgreSQL without changing client code
```

## Interface Example: Notification System

```java
// Interface defining notification capability
public interface Notifier {
    void send(String message);
    void sendTo(String recipient, String message);
}

// Email notification
public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
    
    @Override
    public void sendTo(String recipient, String message) {
        System.out.println("Sending email to " + recipient + ": " + message);
    }
}

// SMS notification
public class SMSNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
    
    @Override
    public void sendTo(String recipient, String message) {
        System.out.println("Sending SMS to " + recipient + ": " + message);
    }
}

// Push notification
public class PushNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending push notification: " + message);
    }
    
    @Override
    public void sendTo(String recipient, String message) {
        System.out.println("Sending push to " + recipient + ": " + message);
    }
}

// Service using abstraction
public class NotificationService {
    private List<Notifier> notifiers;
    
    public NotificationService() {
        notifiers = new ArrayList<>();
    }
    
    public void addNotifier(Notifier notifier) {
        notifiers.add(notifier);
    }
    
    public void notifyAll(String message) {
        for (Notifier notifier : notifiers) {
            notifier.send(message);  // Works with any Notifier
        }
    }
}

// Usage
NotificationService service = new NotificationService();
service.addNotifier(new EmailNotifier());
service.addNotifier(new SMSNotifier());
service.addNotifier(new PushNotifier());
service.notifyAll("Important update!");
```

## Multiple Interfaces

```java
interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

interface Walkable {
    void walk();
}

// Duck can fly, swim, and walk
class Duck implements Flyable, Swimmable, Walkable {
    @Override
    public void fly() {
        System.out.println("Duck is flying");
    }
    
    @Override
    public void swim() {
        System.out.println("Duck is swimming");
    }
    
    @Override
    public void walk() {
        System.out.println("Duck is walking");
    }
}

// Fish can only swim
class Fish implements Swimmable {
    @Override
    public void swim() {
        System.out.println("Fish is swimming");
    }
}
```

## Abstraction Levels

### High-Level Abstraction
User sees simple interface:
```java
car.start();
car.drive();
car.stop();
```

### Low-Level Implementation
Complex internal details:
```java
class Car {
    private Engine engine;
    private Transmission transmission;
    private FuelSystem fuelSystem;
    
    public void start() {
        fuelSystem.pump();
        engine.ignite();
        transmission.engage();
        // Many more internal operations
    }
}
```

## Real-World Example: File Operations

```java
// Abstract base class
public abstract class FileProcessor {
    protected String filename;
    
    public FileProcessor(String filename) {
        this.filename = filename;
    }
    
    // Template method - defines algorithm
    public final void processFile() {
        openFile();
        readData();
        processData();
        closeFile();
    }
    
    // Abstract methods - subclasses implement
    protected abstract void readData();
    protected abstract void processData();
    
    // Concrete methods - common implementation
    private void openFile() {
        System.out.println("Opening file: " + filename);
    }
    
    private void closeFile() {
        System.out.println("Closing file: " + filename);
    }
}

// CSV file processor
public class CSVFileProcessor extends FileProcessor {
    public CSVFileProcessor(String filename) {
        super(filename);
    }
    
    @Override
    protected void readData() {
        System.out.println("Reading CSV data");
    }
    
    @Override
    protected void processData() {
        System.out.println("Processing CSV data");
    }
}

// JSON file processor
public class JSONFileProcessor extends FileProcessor {
    public JSONFileProcessor(String filename) {
        super(filename);
    }
    
    @Override
    protected void readData() {
        System.out.println("Reading JSON data");
    }
    
    @Override
    protected void processData() {
        System.out.println("Processing JSON data");
    }
}

// Usage
FileProcessor processor = new CSVFileProcessor("data.csv");
processor.processFile();  // Uses common algorithm, specific implementation
```

## Benefits of Abstraction

1. **Reduced Complexity:** Users work with simple interfaces
2. **Code Reusability:** Abstract classes provide common implementation
3. **Maintainability:** Implementation changes do not affect users
4. **Testability:** Easy to mock abstract types
5. **Flexibility:** Switch implementations easily
6. **Security:** Hide sensitive implementation details

## Common Patterns Using Abstraction

### Template Method Pattern
Abstract class defines algorithm skeleton, subclasses implement steps.

### Strategy Pattern
Interface defines behavior, concrete classes implement strategies.

### Factory Pattern
Interface defines product creation, concrete factories create specific products.

## Rules and Restrictions

### Abstract Class Rules
1. Cannot instantiate directly
2. Can have constructor
3. Can have static methods
4. Can have final methods
5. Can extend one class only
6. Can implement multiple interfaces

### Interface Rules
1. Cannot instantiate
2. No constructors
3. All fields are public static final
4. Can extend multiple interfaces
5. Can have default methods (Java 8+)
6. Can have static methods (Java 8+)
7. Can have private methods (Java 9+)

## Common Mistakes

1. **Creating abstract classes unnecessarily**
2. **Not making abstract classes truly abstract**
3. **Too many levels of abstraction**
4. **Interfaces with single implementation**
5. **Not using abstraction where needed**

## Best Practices

1. Design for abstraction from the start
2. Keep abstractions simple and focused
3. Use meaningful names for abstract types
4. Provide clear documentation for abstract methods
5. Use abstract classes for common behavior
6. Use interfaces for contracts
7. Avoid deep abstraction hierarchies
8. Follow Interface Segregation Principle

## Summary

- Abstraction hides complexity, shows essential features
- Achieved through abstract classes and interfaces
- Abstract classes: partial implementation with shared code
- Interfaces: pure contract definition
- Enables loose coupling and flexibility
- Essential for maintainable and extensible code
- Use appropriately - do not over-abstract

---

Last Updated: February 4, 2026
