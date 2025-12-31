# SOLID Principles - Detailed Notes

## Overview
SOLID is an acronym for five design principles that make software more maintainable, flexible, and scalable.

---

## 1. Single Responsibility Principle (SRP)

### Definition
A class should have only one reason to change. Each class should do one thing and do it well.

### Why It Matters
- Easier to understand and maintain
- Reduces coupling between different responsibilities
- Makes testing simpler
- Changes in one area do not affect others

### Violations to Avoid
- Class handling both business logic and data persistence
- Class managing both order processing and sending notifications
- Class doing both validation and formatting

### Example: Order Service (ex01)

**Problem:**
```java
class OrderService {
    void checkout() {
        // Process order
        // Send email directly
    }
}
```

**Solution:**
```java
class OrderService {
    private EmailClient emailClient;
    
    void checkout() {
        // Process order only
        emailClient.send();
    }
}

class EmailClient {
    void send() {
        // Handle email sending
    }
}
```

### How to Apply
1. Identify all responsibilities of a class
2. If class has multiple responsibilities, split them
3. Each class should have one clear purpose
4. Use dependency injection to connect classes

---

## 2. Open/Closed Principle (OCP)

### Definition
Software entities should be open for extension but closed for modification. Add new features without changing existing code.

### Why It Matters
- Prevents breaking existing functionality
- Makes code more stable
- Easier to add new features
- Reduces testing burden for unchanged code

### Violations to Avoid
- Using if-else or switch statements for type checking
- Modifying existing classes to add new behavior
- Hard-coded logic for different types

### Example: Video Player (ex02)

**Problem:**
```java
class Player {
    void play(String type) {
        if (type.equals("mp4")) {
            // mp4 logic
        } else if (type.equals("avi")) {
            // avi logic
        }
        // Need to modify this method for each new format
    }
}
```

**Solution:**
```java
interface Decoder {
    void decode();
}

class Mp4Decoder implements Decoder {
    void decode() { /* mp4 logic */ }
}

class AviDecoder implements Decoder {
    void decode() { /* avi logic */ }
}

class Player {
    void play(Decoder decoder) {
        decoder.decode(); // No modification needed for new formats
    }
}
```

### How to Apply
1. Use interfaces or abstract classes
2. Create new implementations instead of modifying existing ones
3. Use polymorphism to handle different behaviors
4. Design for extension points

---

## 3. Liskov Substitution Principle (LSP)

### Definition
Objects of a superclass should be replaceable with objects of subclasses without breaking the application. Subtypes must be behaviorally compatible.

### Why It Matters
- Ensures inheritance is used correctly
- Prevents unexpected behavior
- Makes code predictable and reliable
- Enables true polymorphism

### Violations to Avoid
- Subclass strengthening preconditions
- Subclass weakening postconditions
- Subclass throwing new exceptions
- Subclass changing expected behavior

### Example: Rectangle and Square (ex05)

**Problem:**
```java
class Rectangle {
    void setWidth(int w) { width = w; }
    void setHeight(int h) { height = h; }
}

class Square extends Rectangle {
    void setWidth(int w) { 
        width = w; 
        height = w; // Breaks expectation
    }
    void setHeight(int h) { 
        width = h; 
        height = h; // Breaks expectation
    }
}

// This breaks:
Rectangle r = new Square();
r.setWidth(5);
r.setHeight(10);
// Expected: 5x10, Got: 10x10
```

**Solution:**
```java
interface Shape {
    int getArea();
}

class Rectangle implements Shape {
    void setWidth(int w) { width = w; }
    void setHeight(int h) { height = h; }
    int getArea() { return width * height; }
}

class Square implements Shape {
    void setSide(int s) { side = s; }
    int getArea() { return side * side; }
}
```

### How to Apply
1. Ensure subclass behavior matches superclass contract
2. Do not strengthen preconditions in subclass
3. Do not weaken postconditions in subclass
4. Consider composition over inheritance if behavior differs
5. Use interfaces when classes are not truly substitutable

---

## 4. Interface Segregation Principle (ISP)

### Definition
Clients should not be forced to depend on interfaces they do not use. Create small, focused interfaces instead of large, general ones.

### Why It Matters
- Reduces coupling
- Makes interfaces easier to implement
- Prevents unnecessary method implementations
- Improves code clarity

### Violations to Avoid
- Large interfaces with many methods
- Classes implementing interfaces with unused methods
- Forcing implementations to throw UnsupportedOperationException

### Example: Bird Interface (ex06)

**Problem:**
```java
interface Bird {
    void fly();
    void swim();
    void walk();
}

class Penguin implements Bird {
    void fly() { throw new UnsupportedOperationException(); }
    void swim() { /* implementation */ }
    void walk() { /* implementation */ }
}
```

**Solution:**
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

class Penguin implements Swimmable, Walkable {
    void swim() { /* implementation */ }
    void walk() { /* implementation */ }
}

class Eagle implements Flyable, Walkable {
    void fly() { /* implementation */ }
    void walk() { /* implementation */ }
}
```

### How to Apply
1. Keep interfaces small and focused
2. Group related methods together
3. Allow classes to implement multiple interfaces
4. Avoid "fat" interfaces
5. Consider client needs when designing interfaces

---

## 5. Dependency Inversion Principle (DIP)

### Definition
High-level modules should not depend on low-level modules. Both should depend on abstractions. Abstractions should not depend on details.

### Why It Matters
- Reduces coupling between components
- Makes code more testable
- Enables flexibility to change implementations
- Facilitates dependency injection

### Violations to Avoid
- Direct instantiation of concrete classes
- High-level classes depending on low-level details
- Hard-coded dependencies

### Example: Shipping Calculator (ex03)

**Problem:**
```java
class ShippingCostCalculator {
    double calculate(Standard shipment) {
        // Depends on concrete Standard class
    }
}
```

**Solution:**
```java
interface IShippingPolicy {
    double calculateCost();
}

class Standard implements IShippingPolicy {
    double calculateCost() { /* implementation */ }
}

class Express implements IShippingPolicy {
    double calculateCost() { /* implementation */ }
}

class ShippingCostCalculator {
    double calculate(IShippingPolicy policy) {
        return policy.calculateCost(); // Depends on abstraction
    }
}
```

### Example: Payment Service (ex04)

**Problem:**
```java
class PaymentService {
    void process() {
        Card card = new Card(); // Direct dependency
        card.pay();
    }
}
```

**Solution:**
```java
interface Payment {
    void pay();
}

class Card implements Payment {
    void pay() { /* implementation */ }
}

class PaymentService {
    private Payment payment;
    
    PaymentService(Payment payment) {
        this.payment = payment; // Inject dependency
    }
    
    void process() {
        payment.pay(); // Use abstraction
    }
}
```

### How to Apply
1. Program to interfaces, not implementations
2. Use dependency injection (constructor, setter, or method)
3. Abstract dependencies behind interfaces
4. Let external configuration control which implementation to use
5. Avoid 'new' keyword for dependencies in high-level classes

---

## SOLID Benefits Summary

1. **Maintainability:** Easier to modify and fix bugs
2. **Scalability:** Simple to add new features
3. **Testability:** Components can be tested in isolation
4. **Reusability:** Components can be reused in different contexts
5. **Understandability:** Code is clearer and more organized
6. **Flexibility:** Easy to change implementations
7. **Robustness:** Less likely to break when changes are made

---

## Common Mistakes

1. **Over-engineering:** Applying principles where not needed
2. **Under-engineering:** Ignoring principles completely
3. **Premature optimization:** Applying patterns too early
4. **Not refactoring:** Leaving violations when you find them
5. **Inheritance abuse:** Using inheritance when composition is better

---

## When to Apply SOLID

### Apply when:
- Building medium to large applications
- Code will be maintained long-term
- Multiple developers working on codebase
- Requirements likely to change
- Testing is important

### May skip for:
- Quick prototypes or scripts
- Very small projects
- One-time use code
- Performance-critical code where abstraction costs too much

---

## Practice Approach

1. **Identify violations** in existing code
2. **Understand the problem** each principle solves
3. **Refactor step by step** one principle at a time
4. **Test after each change** to ensure behavior is preserved
5. **Compare before and after** to see improvements
6. **Apply to new code** from the start

---

## Exercise Mapping

- **ex01:** SRP - Separating email sending from order processing
- **ex02:** OCP - Extensible video decoder system
- **ex03:** DIP - Shipping cost calculation with abstractions
- **ex04:** DIP - Payment system with interface dependency
- **ex05:** LSP - Rectangle/Square inheritance problem
- **ex06:** ISP - Bird interface segregation
- **ex07-ex10:** Combined principles in realistic scenarios

---

Last Updated: February 4, 2026
