# Classes and Objects

## Definition

**Class:** A blueprint or template for creating objects. It defines properties (attributes) and behaviors (methods) that objects will have.

**Object:** An instance of a class. A concrete entity created from a class blueprint.

## Analogy

Think of a class as a cookie cutter and objects as the actual cookies. The cookie cutter defines the shape, but each cookie is a separate entity.

## Basic Structure

```java
// Class definition
public class Car {
    // Properties (attributes/fields)
    private String brand;
    private String model;
    private int year;
    
    // Constructor - special method to create objects
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
    
    // Methods (behaviors)
    public void start() {
        System.out.println(brand + " " + model + " is starting");
    }
    
    public void stop() {
        System.out.println(brand + " " + model + " is stopping");
    }
    
    // Getters
    public String getBrand() {
        return brand;
    }
}
```

## Creating Objects

```java
// Creating objects from the Car class
Car car1 = new Car("Toyota", "Camry", 2023);
Car car2 = new Car("Honda", "Accord", 2024);

// Using objects
car1.start();  // Output: Toyota Camry is starting
car2.start();  // Output: Honda Accord is starting
```

## Key Concepts

### 1. Properties (Fields/Attributes)
Variables that hold the state of an object. Each object has its own copy of instance variables.

```java
private String name;  // Instance variable
private int age;      // Instance variable
```

### 2. Methods
Functions defined inside a class that describe behaviors. Methods can access and modify object properties.

```java
public void walk() {
    System.out.println("Walking");
}
```

### 3. Constructors
Special methods called when creating new objects. Used to initialize object state.

```java
// Default constructor (no parameters)
public Person() {
    name = "Unknown";
    age = 0;
}

// Parameterized constructor
public Person(String name, int age) {
    this.name = name;
    this.age = age;
}
```

### 4. The 'this' Keyword
Refers to the current object instance. Used to distinguish between instance variables and parameters.

```java
public void setName(String name) {
    this.name = name;  // this.name is instance variable, name is parameter
}
```

## Multiple Objects

Each object is independent with its own state:

```java
Person person1 = new Person("Alice", 25);
Person person2 = new Person("Bob", 30);

person1.setAge(26);  // Only affects person1, not person2
```

## Static vs Instance Members

### Instance Members
Belong to each object. Each object has its own copy.

```java
private String name;  // Each object has its own name

public void displayName() {
    System.out.println(name);
}
```

### Static Members
Belong to the class itself, not to any specific object. Shared among all objects.

```java
private static int count = 0;  // Shared by all Person objects

public static int getCount() {
    return count;
}

// Access: Person.getCount() - no object needed
```

## Object Lifecycle

### 1. Creation
```java
Person p = new Person("John", 25);
// Memory allocated, constructor called
```

### 2. Usage
```java
p.setAge(26);
String name = p.getName();
```

### 3. Destruction
```java
p = null;  // Object eligible for garbage collection
// Java's garbage collector will free memory automatically
```

## Benefits

1. **Organization:** Related data and behavior grouped together
2. **Reusability:** Create multiple objects from one class
3. **Modularity:** Changes to class affect all its objects
4. **Maintenance:** Easier to update and fix bugs
5. **Real-world modeling:** Objects represent real entities

## Common Mistakes

1. **Forgetting 'new' keyword:** Cannot create object without 'new'
2. **Null pointer errors:** Accessing methods on null objects
3. **Confusing class and object:** Class is template, object is instance
4. **Not initializing objects:** Using objects before proper initialization

## Best Practices

1. Keep class focused on single responsibility
2. Use meaningful class and variable names
3. Initialize all fields in constructor
4. Make fields private, provide public methods
5. Override toString() for better object representation
6. Override equals() and hashCode() when needed

## Example: Bank Account

```java
public class BankAccount {
    private String accountNumber;
    private double balance;
    private String owner;
    
    public BankAccount(String accountNumber, String owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = 0.0;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
    
    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    public double getBalance() {
        return balance;
    }
}

// Usage
BankAccount account = new BankAccount("123456", "John Doe");
account.deposit(1000.0);
account.withdraw(500.0);
System.out.println("Balance: " + account.getBalance());
```

## Summary

- Classes define structure and behavior
- Objects are instances of classes
- Each object has its own state
- Methods define object behavior
- Constructors initialize objects
- Objects model real-world entities

---

Last Updated: February 4, 2026
