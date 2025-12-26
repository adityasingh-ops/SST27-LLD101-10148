# Polymorphism

## Definition

Polymorphism means "many forms." It allows objects of different types to be treated as objects of a common parent type. The same method call can produce different behavior depending on the object type.

## Core Principle

One interface, multiple implementations.

## Types of Polymorphism

### 1. Compile-Time Polymorphism (Static/Early Binding)
Resolved at compile time.

#### Method Overloading
Same method name, different parameters.

```java
public class Calculator {
    // Same method name, different parameters
    public int add(int a, int b) {
        return a + b;
    }
    
    public double add(double a, double b) {
        return a + b;
    }
    
    public int add(int a, int b, int c) {
        return a + b + c;
    }
    
    public String add(String a, String b) {
        return a + b;
    }
}

// Usage
Calculator calc = new Calculator();
calc.add(5, 10);           // Calls int version
calc.add(5.5, 10.5);       // Calls double version
calc.add(1, 2, 3);         // Calls three-parameter version
calc.add("Hello", "World"); // Calls String version
```

#### Operator Overloading
Java does not support operator overloading (except + for String concatenation).

### 2. Runtime Polymorphism (Dynamic/Late Binding)
Resolved at runtime based on actual object type.

#### Method Overriding
Child class provides specific implementation of parent method.

```java
class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks: Woof!");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Cat meows: Meow!");
    }
}

// Polymorphic behavior
Animal animal1 = new Dog();
Animal animal2 = new Cat();

animal1.makeSound();  // Output: Dog barks: Woof!
animal2.makeSound();  // Output: Cat meows: Meow!
```

## Polymorphism in Action

### Example 1: Payment Processing

```java
// Parent type
interface Payment {
    void processPayment(double amount);
}

// Different implementations
class CreditCardPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
        // Credit card specific logic
    }
}

class PayPalPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
        // PayPal specific logic
    }
}

class CryptoPayment implements Payment {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing crypto payment: $" + amount);
        // Cryptocurrency specific logic
    }
}

// Polymorphic usage
public class PaymentProcessor {
    public void executePayment(Payment payment, double amount) {
        payment.processPayment(amount);  // Works with any Payment type
    }
}

// Usage
PaymentProcessor processor = new PaymentProcessor();
processor.executePayment(new CreditCardPayment(), 100.0);
processor.executePayment(new PayPalPayment(), 50.0);
processor.executePayment(new CryptoPayment(), 75.0);
```

### Example 2: Shape Area Calculation

```java
abstract class Shape {
    abstract double calculateArea();
    
    public void printArea() {
        System.out.println("Area: " + calculateArea());
    }
}

class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }
}

class Rectangle extends Shape {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    double calculateArea() {
        return width * height;
    }
}

class Triangle extends Shape {
    private double base;
    private double height;
    
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    
    @Override
    double calculateArea() {
        return 0.5 * base * height;
    }
}

// Polymorphic usage
Shape[] shapes = {
    new Circle(5),
    new Rectangle(4, 6),
    new Triangle(3, 8)
};

for (Shape shape : shapes) {
    shape.printArea();  // Different calculation for each shape
}
```

## Method Overloading Rules

### Valid Overloading
Different number of parameters:
```java
void print(int x) { }
void print(int x, int y) { }
```

Different parameter types:
```java
void print(int x) { }
void print(String x) { }
```

Different parameter order:
```java
void print(int x, String y) { }
void print(String x, int y) { }
```

### Invalid Overloading
Only return type differs:
```java
int calculate() { }
double calculate() { }  // Error: same signature
```

Only parameter names differ:
```java
void print(int x) { }
void print(int y) { }  // Error: same signature
```

## Method Overriding Rules

### Requirements
1. Same method name
2. Same parameter list
3. Same or covariant return type
4. Same or less restrictive access modifier
5. Cannot throw broader checked exceptions

### Example
```java
class Parent {
    protected Number getValue() {
        return 10;
    }
}

class Child extends Parent {
    @Override
    public Integer getValue() {  // Covariant return type (Integer is Number)
        return 20;
    }
}
```

## Polymorphism with Collections

```java
List<Animal> animals = new ArrayList<>();
animals.add(new Dog());
animals.add(new Cat());
animals.add(new Bird());

// Polymorphic iteration
for (Animal animal : animals) {
    animal.makeSound();  // Each calls its own implementation
}
```

## instanceof and Type Checking

Check object type at runtime:

```java
Animal animal = new Dog();

if (animal instanceof Dog) {
    Dog dog = (Dog) animal;  // Safe cast
    dog.fetch();  // Dog-specific method
}

// Better: Pattern matching (Java 16+)
if (animal instanceof Dog dog) {
    dog.fetch();  // Automatic cast
}
```

## Benefits of Polymorphism

1. **Flexibility:** Add new types without changing existing code
2. **Extensibility:** Easy to extend system with new implementations
3. **Code Reusability:** Write generic code that works with many types
4. **Maintainability:** Changes localized to specific classes
5. **Loose Coupling:** Code depends on abstractions, not concrete classes

## Real-World Example: Logger

```java
interface Logger {
    void log(String message);
}

class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("[CONSOLE] " + message);
    }
}

class FileLogger implements Logger {
    private String filename;
    
    public FileLogger(String filename) {
        this.filename = filename;
    }
    
    @Override
    public void log(String message) {
        // Write to file
        System.out.println("[FILE: " + filename + "] " + message);
    }
}

class DatabaseLogger implements Logger {
    @Override
    public void log(String message) {
        // Write to database
        System.out.println("[DATABASE] " + message);
    }
}

// Application uses Logger interface
class Application {
    private Logger logger;
    
    public Application(Logger logger) {
        this.logger = logger;
    }
    
    public void run() {
        logger.log("Application started");
        // Application logic
        logger.log("Application finished");
    }
}

// Different logger at runtime
Application app1 = new Application(new ConsoleLogger());
Application app2 = new Application(new FileLogger("app.log"));
Application app3 = new Application(new DatabaseLogger());

app1.run();  // Logs to console
app2.run();  // Logs to file
app3.run();  // Logs to database
```

## Polymorphism with Interfaces

```java
// Multiple interfaces
interface Drawable {
    void draw();
}

interface Resizable {
    void resize(double factor);
}

// Class implementing multiple interfaces
class Image implements Drawable, Resizable {
    @Override
    public void draw() {
        System.out.println("Drawing image");
    }
    
    @Override
    public void resize(double factor) {
        System.out.println("Resizing image by " + factor);
    }
}

// Polymorphic usage
Drawable drawable = new Image();
drawable.draw();

Resizable resizable = new Image();
resizable.resize(1.5);
```

## Dynamic Method Dispatch

JVM determines which method to call at runtime based on actual object type:

```java
class Parent {
    public void display() {
        System.out.println("Parent display");
    }
}

class Child extends Parent {
    @Override
    public void display() {
        System.out.println("Child display");
    }
}

Parent obj = new Child();  // Parent reference, Child object
obj.display();  // Output: "Child display" - runtime polymorphism
```

## Common Mistakes

1. **Confusing overloading and overriding**
2. **Forgetting @Override annotation**
3. **Narrowing access in override (making more restrictive)**
4. **Changing method signature accidentally**
5. **Not understanding dynamic dispatch**

## Best Practices

1. Use @Override annotation for clarity
2. Program to interfaces, not implementations
3. Use polymorphism for extensibility
4. Avoid instanceof checks when possible
5. Design for polymorphism from the start
6. Keep polymorphic hierarchies shallow
7. Document expected behavior in parent class/interface

## Advantages

1. One interface, multiple implementations
2. Easy to add new types
3. Reduces code duplication
4. Improves code organization
5. Enables frameworks and libraries
6. Supports design patterns

## Disadvantages

1. Can make code harder to trace
2. Runtime overhead for method lookup
3. May reduce performance slightly
4. Can complicate debugging

## Summary

- Polymorphism allows same interface with different implementations
- Compile-time: method overloading
- Runtime: method overriding
- Enables flexible and extensible code
- Use interfaces and abstract classes for polymorphism
- Follow Liskov Substitution Principle
- Essential for object-oriented design

---

Last Updated: February 4, 2026
