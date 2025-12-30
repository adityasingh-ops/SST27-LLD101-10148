# Dependency Inversion Principle (DIP)

## Definition

High-level modules should not depend on low-level modules. Both should depend on abstractions. Abstractions should not depend on details. Details should depend on abstractions.

## The Principle

"Depend upon abstractions, not concretions."  
- Robert C. Martin

## Core Concept

Instead of high-level classes depending directly on low-level classes, both should depend on abstract interfaces or abstract classes. This inverts the traditional dependency direction.

## Simple Explanation

Do not create dependencies directly on concrete classes. Use interfaces or abstract classes as the dependency contract. This makes code flexible and easy to change.

## Why DIP Matters

1. **Flexibility:** Easy to swap implementations
2. **Testability:** Can mock dependencies
3. **Reduced Coupling:** Changes in low-level modules do not affect high-level
4. **Reusability:** High-level logic reusable with different implementations
5. **Maintainability:** Changes localized to specific implementations

## Example: Violation

```java
// BAD: High-level class depends on low-level concrete class
public class EmailNotifier {
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}

public class OrderService {
    private EmailNotifier emailNotifier;
    
    public OrderService() {
        this.emailNotifier = new EmailNotifier();  // Direct dependency!
    }
    
    public void placeOrder(String order) {
        System.out.println("Order placed: " + order);
        emailNotifier.send("Order confirmation");
    }
}
```

### Problems
1. OrderService tightly coupled to EmailNotifier
2. Cannot change notification method without modifying OrderService
3. Hard to test OrderService in isolation
4. Cannot add SMS or Push notifications easily

## Example: Solution

```java
// GOOD: Both depend on abstraction
public interface Notifier {
    void send(String message);
}

public class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}

public class SMSNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

public class PushNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending push notification: " + message);
    }
}

// High-level class depends on abstraction
public class OrderService {
    private Notifier notifier;
    
    // Dependency injection through constructor
    public OrderService(Notifier notifier) {
        this.notifier = notifier;
    }
    
    public void placeOrder(String order) {
        System.out.println("Order placed: " + order);
        notifier.send("Order confirmation");
    }
}

// Usage - can easily switch implementations
OrderService emailService = new OrderService(new EmailNotifier());
OrderService smsService = new OrderService(new SMSNotifier());
OrderService pushService = new OrderService(new PushNotifier());
```

## Dependency Injection

DIP is often implemented using Dependency Injection (DI).

### Constructor Injection
```java
public class ReportGenerator {
    private final DataSource dataSource;
    
    public ReportGenerator(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
```

### Setter Injection
```java
public class ReportGenerator {
    private DataSource dataSource;
    
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
```

### Method Injection
```java
public class ReportGenerator {
    public void generate(DataSource dataSource) {
        // Use dataSource
    }
}
```

## Real-World Example: Payment Processing

### Violation
```java
// BAD: Direct dependency on concrete payment processor
public class PayPalProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing via PayPal: $" + amount);
    }
}

public class ShoppingCart {
    private PayPalProcessor paymentProcessor;
    
    public ShoppingCart() {
        this.paymentProcessor = new PayPalProcessor();  // Tight coupling!
    }
    
    public void checkout(double total) {
        paymentProcessor.processPayment(total);
    }
}
```

### Solution
```java
// GOOD: Depend on abstraction
public interface PaymentProcessor {
    void processPayment(double amount);
}

public class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing via PayPal: $" + amount);
    }
}

public class StripeProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing via Stripe: $" + amount);
    }
}

public class CryptoProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing via Cryptocurrency: $" + amount);
    }
}

public class ShoppingCart {
    private PaymentProcessor paymentProcessor;
    
    // Inject dependency
    public ShoppingCart(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }
    
    public void checkout(double total) {
        paymentProcessor.processPayment(total);
    }
}

// Easy to switch payment methods
ShoppingCart cart1 = new ShoppingCart(new PayPalProcessor());
ShoppingCart cart2 = new ShoppingCart(new StripeProcessor());
ShoppingCart cart3 = new ShoppingCart(new CryptoProcessor());
```

## Example: Database Access

### Violation
```java
// BAD: Business logic depends on specific database
public class MySQLDatabase {
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
    
    public String load(String id) {
        return "Data from MySQL";
    }
}

public class UserManager {
    private MySQLDatabase database;
    
    public UserManager() {
        this.database = new MySQLDatabase();  // Tight coupling!
    }
    
    public void saveUser(User user) {
        database.save(user.toString());
    }
}
```

### Solution
```java
// GOOD: Abstract database operations
public interface Database {
    void save(String data);
    String load(String id);
}

public class MySQLDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to MySQL: " + data);
    }
    
    @Override
    public String load(String id) {
        return "Data from MySQL";
    }
}

public class PostgreSQLDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to PostgreSQL: " + data);
    }
    
    @Override
    public String load(String id) {
        return "Data from PostgreSQL";
    }
}

public class MongoDBDatabase implements Database {
    @Override
    public void save(String data) {
        System.out.println("Saving to MongoDB: " + data);
    }
    
    @Override
    public String load(String id) {
        return "Data from MongoDB";
    }
}

public class UserManager {
    private Database database;
    
    // Dependency injected
    public UserManager(Database database) {
        this.database = database;
    }
    
    public void saveUser(User user) {
        database.save(user.toString());
    }
    
    public User loadUser(String id) {
        String data = database.load(id);
        return new User(data);
    }
}

// Flexible - can use any database
UserManager mysqlManager = new UserManager(new MySQLDatabase());
UserManager postgresManager = new UserManager(new PostgreSQLDatabase());
UserManager mongoManager = new UserManager(new MongoDBDatabase());
```

## Benefits of DIP

1. **Loose Coupling:** High and low-level modules independent
2. **Easy Testing:** Mock dependencies in tests
3. **Flexibility:** Swap implementations without changing code
4. **Parallel Development:** Teams work on interfaces and implementations independently
5. **Reusability:** High-level logic works with any implementation

## How to Apply DIP

### Step 1: Identify Dependencies
Find where high-level classes depend on low-level concrete classes.

### Step 2: Create Abstractions
Define interfaces or abstract classes for dependencies.

### Step 3: Implement Abstractions
Create concrete implementations of interfaces.

### Step 4: Inject Dependencies
Use constructor, setter, or method injection.

## Testing Benefits

### Before DIP
```java
// Hard to test - requires real database
@Test
public void testUserManager() {
    UserManager manager = new UserManager();
    // Uses real MySQLDatabase - slow and requires database setup
    manager.saveUser(user);
}
```

### After DIP
```java
// Easy to test - use mock
@Test
public void testUserManager() {
    Database mockDatabase = mock(Database.class);
    UserManager manager = new UserManager(mockDatabase);
    
    manager.saveUser(user);
    
    verify(mockDatabase).save(user.toString());
}
```

## Common Mistakes

1. **Not using interfaces:** Still depending on concrete classes
2. **Creating dependencies internally:** Using 'new' inside classes
3. **Interface for everything:** Over-abstracting simple classes
4. **Wrong abstraction level:** Abstractions too specific or too general

## When to Apply

### Apply When:
- Class has external dependencies
- Need flexibility to change implementations
- Want to test in isolation
- Multiple implementations exist or expected

### May Skip For:
- Value objects or DTOs
- Simple utility classes
- Framework-provided classes
- Performance-critical code where abstraction has cost

## Signs of DIP Violation

1. 'new' keyword used to create dependencies
2. Hard to test classes
3. Changes in low-level classes require high-level changes
4. Cannot swap implementations easily
5. Direct imports of concrete implementation classes

## Inversion of Control (IoC)

DIP is achieved through Inversion of Control - framework controls object creation and injection.

```java
// Spring Framework example
@Service
public class OrderService {
    private final Notifier notifier;
    
    @Autowired  // Spring injects dependency
    public OrderService(Notifier notifier) {
        this.notifier = notifier;
    }
}
```

## Summary

- High-level and low-level modules depend on abstractions
- Use interfaces or abstract classes as contracts
- Inject dependencies instead of creating them
- Enables loose coupling and flexibility
- Makes testing easier with mocks
- Essential for maintainable architecture
- Apply when dependencies exist and flexibility is needed

---

Refer to: [DIP Example Illustration](illustrations/dip-example.md)

Last Updated: February 4, 2026
