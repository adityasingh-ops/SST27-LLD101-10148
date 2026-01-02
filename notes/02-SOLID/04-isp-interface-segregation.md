# Interface Segregation Principle (ISP)

## Definition

No client should be forced to depend on methods it does not use. Create small, specific interfaces rather than large, general-purpose ones.

## The Principle

"Clients should not be forced to depend upon interfaces they do not use."  
- Robert C. Martin

## Core Concept

Break large interfaces into smaller, more specific ones so that clients only need to know about methods relevant to them.

## Simple Explanation

Do not force a class to implement methods it does not need. Better to have multiple small interfaces than one large interface with unused methods.

## Why ISP Matters

1. **Reduced Coupling:** Classes depend only on what they need
2. **Better Organization:** Clear separation of concerns
3. **Easier Implementation:** Smaller interfaces are simpler
4. **Flexibility:** Mix and match interfaces as needed
5. **Maintainability:** Changes affect fewer classes

## Example: Violation

```java
// BAD: Fat interface forcing all implementations
public interface Worker {
    void work();
    void eat();
    void getSalary();
    void attendMeeting();
    void writeCode();
}

public class Manager implements Worker {
    public void work() { /* code */ }
    public void eat() { /* code */ }
    public void getSalary() { /* code */ }
    public void attendMeeting() { /* code */ }
    
    // Forced to implement but does not need
    public void writeCode() {
        throw new UnsupportedOperationException();
    }
}
```

## Example: Solution

```java
// GOOD: Segregated interfaces
public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public interface Payable {
    double getSalary();
}

public interface Attendee {
    void attendMeeting();
}

public interface Programmer {
    void writeCode();
}

// Implement only what is needed
public class Manager implements Workable, Eatable, Payable, Attendee {
    public void work() { /* code */ }
    public void eat() { /* code */ }
    public double getSalary() { return 100000; }
    public void attendMeeting() { /* code */ }
}

public class Developer implements Workable, Eatable, Payable, Programmer {
    public void work() { /* code */ }
    public void eat() { /* code */ }
    public double getSalary() { return 80000; }
    public void writeCode() { /* code */ }
}
```

## Real-World Example: Animals

### Violation
```java
// BAD: All animals forced to implement all behaviors
public interface Animal {
    void walk();
    void fly();
    void swim();
}

public class Dog implements Animal {
    public void walk() { System.out.println("Dog walking"); }
    public void fly() { throw new UnsupportedOperationException(); }
    public void swim() { System.out.println("Dog swimming"); }
}
```

### Solution
```java
// GOOD: Separate capability interfaces
public interface Walkable {
    void walk();
}

public interface Flyable {
    void fly();
}

public interface Swimmable {
    void swim();
}

public class Dog implements Walkable, Swimmable {
    public void walk() { System.out.println("Dog walking"); }
    public void swim() { System.out.println("Dog swimming"); }
}

public class Duck implements Walkable, Flyable, Swimmable {
    public void walk() { System.out.println("Duck walking"); }
    public void fly() { System.out.println("Duck flying"); }
    public void swim() { System.out.println("Duck swimming"); }
}
```

## Benefits of ISP

1. **Flexibility:** Classes can implement multiple small interfaces
2. **Decoupling:** Changes to one interface do not affect unrelated classes
3. **Clarity:** Interfaces clearly define specific capabilities
4. **Easier Testing:** Test specific capabilities independently
5. **Better Design:** Forces thinking about real requirements

## When to Apply

### Apply When:
- Interface has many methods
- Classes implement interface with many unused methods
- Throwing UnsupportedOperationException in implementations
- Unrelated methods grouped in one interface

### May Skip For:
- Interfaces with only 1-2 methods
- All implementations need all methods
- Simple, focused interfaces already

## Summary

- No client should be forced to depend on unused methods
- Break large interfaces into smaller, specific ones
- Classes implement only what they need
- Reduces coupling and improves flexibility
- Makes code easier to understand and maintain

---

Refer to: [ISP Example Illustration](illustrations/isp-example.md)

Last Updated: February 4, 2026
