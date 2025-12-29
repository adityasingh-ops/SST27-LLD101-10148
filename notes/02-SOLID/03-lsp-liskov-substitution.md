# Liskov Substitution Principle (LSP)

## Definition

Objects of a superclass should be replaceable with objects of a subclass without breaking the application. Subtypes must be substitutable for their base types.

## The Principle

"Subtypes must be substitutable for their base types."  
- Barbara Liskov

## Core Concept

If class B is a subtype of class A, then objects of type A can be replaced with objects of type B without altering the correctness of the program.

## Simple Explanation

If you have a function that works with a parent class, it should work equally well with any child class without knowing which specific child it is.

## Why LSP Matters

1. **Correctness:** Ensures inheritance is used properly
2. **Reliability:** Prevents unexpected behavior
3. **Polymorphism:** Enables true polymorphic behavior
4. **Maintainability:** Makes code predictable

## Classic Example: Rectangle and Square

### Violation
```java
// BAD: Square violates LSP when extending Rectangle
public class Rectangle {
    protected int width;
    protected int height;
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public int getArea() {
        return width * height;
    }
}

public class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width;  // Must keep width = height
    }
    
    @Override
    public void setHeight(int height) {
        this.width = height;  // Must keep width = height
        this.height = height;
    }
}

// This breaks LSP
public class Test {
    public static void testRectangle(Rectangle r) {
        r.setWidth(5);
        r.setHeight(10);
        // Expected: 50, but if r is Square, actual: 100
        assert r.getArea() == 50;  // Fails for Square!
    }
}
```

### Problems
1. Square changes expected Rectangle behavior
2. Cannot substitute Square for Rectangle reliably
3. Violates LSP
4. Tests fail when using Square

### Solution
```java
// GOOD: Use interface, not inheritance
public interface Shape {
    int getArea();
}

public class Rectangle implements Shape {
    private int width;
    private int height;
    
    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    @Override
    public int getArea() {
        return width * height;
    }
}

public class Square implements Shape {
    private int side;
    
    public Square(int side) {
        this.side = side;
    }
    
    public void setSide(int side) {
        this.side = side;
    }
    
    @Override
    public int getArea() {
        return side * side;
    }
}
```

## Real-World Example: Bird Hierarchy

### Violation
```java
// BAD: Not all birds can fly
public class Bird {
    public void fly() {
        System.out.println("Flying");
    }
}

public class Sparrow extends Bird {
    // Works fine
}

public class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins cannot fly!");
    }
}

// This breaks LSP
public void makeBirdFly(Bird bird) {
    bird.fly();  // Throws exception for Penguin!
}
```

### Solution
```java
// GOOD: Separate flying capability
public abstract class Bird {
    public abstract void eat();
}

public interface Flyable {
    void fly();
}

public class Sparrow extends Bird implements Flyable {
    @Override
    public void eat() {
        System.out.println("Sparrow eating");
    }
    
    @Override
    public void fly() {
        System.out.println("Sparrow flying");
    }
}

public class Penguin extends Bird {
    @Override
    public void eat() {
        System.out.println("Penguin eating");
    }
    
    public void swim() {
        System.out.println("Penguin swimming");
    }
}
```

## LSP Rules

### 1. Preconditions cannot be strengthened
Subclass cannot require more than parent.

```java
// BAD
class Parent {
    void process(int value) {
        // Accepts any int
    }
}

class Child extends Parent {
    @Override
    void process(int value) {
        if (value < 0) {
            throw new IllegalArgumentException();  // Stricter than parent!
        }
    }
}
```

### 2. Postconditions cannot be weakened
Subclass must provide at least what parent promises.

```java
// BAD
class Parent {
    // Returns non-null value
    String getName() {
        return "Name";
    }
}

class Child extends Parent {
    @Override
    String getName() {
        return null;  // Weaker than parent promise!
    }
}
```

### 3. Invariants must be preserved
Subclass must maintain parent's invariants.

```java
// BAD
class Account {
    protected double balance;
    
    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        }
        // Invariant: balance >= 0
    }
}

class OverdraftAccount extends Account {
    @Override
    void withdraw(double amount) {
        balance -= amount;  // Can make balance negative - breaks invariant!
    }
}
```

### 4. History constraint
Subclass cannot modify immutable parent properties.

## Example: Stack and Queue

### Violation
```java
// BAD: Stack is not a Queue
public class Queue {
    public void enqueue(Object item) {
        // Add to rear
    }
    
    public Object dequeue() {
        // Remove from front (FIFO)
        return null;
    }
}

public class Stack extends Queue {
    @Override
    public Object dequeue() {
        // Remove from rear (LIFO) - violates Queue contract!
        return null;
    }
}
```

### Solution
```java
// GOOD: Separate abstractions
public interface Collection {
    void add(Object item);
    Object remove();
    boolean isEmpty();
}

public class Queue implements Collection {
    @Override
    public void add(Object item) {
        // Add to rear
    }
    
    @Override
    public Object remove() {
        // Remove from front (FIFO)
        return null;
    }
    
    @Override
    public boolean isEmpty() {
        return false;
    }
}

public class Stack implements Collection {
    @Override
    public void add(Object item) {
        // Push to top
    }
    
    @Override
    public Object remove() {
        // Pop from top (LIFO)
        return null;
    }
    
    @Override
    public boolean isEmpty() {
        return false;
    }
}
```

## How to Ensure LSP Compliance

### 1. Behavioral Compatibility
Subclass behavior must match parent expectations.

### 2. Design by Contract
Define and follow contracts (preconditions, postconditions, invariants).

### 3. Avoid Type Checking
If you need instanceof to handle subclasses differently, LSP is violated.

### 4. Think "IS-A" Carefully
Does subclass truly behave like parent in all contexts?

## Benefits of LSP

1. **Reliable Polymorphism:** Can safely use parent type references
2. **Predictable Behavior:** No surprises with subclasses
3. **Easier Testing:** Test parent, trust subclasses
4. **Better Design:** Forces thinking about true relationships
5. **Reduced Bugs:** Prevents inheritance-related errors

## Common Violations

1. Empty or exception-throwing method overrides
2. Changing method behavior unexpectedly
3. Strengthening preconditions
4. Weakening postconditions
5. Breaking parent invariants

## Signs of LSP Violation

1. instanceof or type checking in client code
2. Catching exceptions only for specific subclasses
3. Subclass methods that throw UnsupportedOperationException
4. Unit tests that fail when using subclasses
5. Need to know specific subclass type to use it correctly

## Refactoring to LSP

### Before
```java
class Vehicle {
    void refuel() {
        // Refuel with gasoline
    }
}

class ElectricCar extends Vehicle {
    @Override
    void refuel() {
        throw new UnsupportedOperationException("Use recharge() instead");
    }
    
    void recharge() {
        // Charge battery
    }
}
```

### After
```java
interface Vehicle {
    void replenishEnergy();
}

class GasolineCar implements Vehicle {
    @Override
    public void replenishEnergy() {
        System.out.println("Refueling with gasoline");
    }
}

class ElectricCar implements Vehicle {
    @Override
    public void replenishEnergy() {
        System.out.println("Recharging battery");
    }
}
```

## Summary

- Subtypes must be substitutable for base types
- Subclass behavior must not break parent's contract
- Avoid strengthening preconditions or weakening postconditions
- Preserve parent invariants
- Use composition over inheritance when behavior differs significantly
- Think carefully about IS-A relationships
- Enable reliable polymorphism

---

Refer to: [LSP Example Illustration](illustrations/lsp-example.md)

Last Updated: February 4, 2026
