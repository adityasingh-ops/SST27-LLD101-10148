# Inheritance

## Definition

Inheritance is a mechanism where a new class (child/subclass/derived class) acquires properties and behaviors of an existing class (parent/superclass/base class). It establishes an "IS-A" relationship between classes.

## Core Principle

Code reuse and hierarchical classification.

## Basic Syntax

```java
// Parent class (superclass)
public class Animal {
    protected String name;
    protected int age;
    
    public void eat() {
        System.out.println(name + " is eating");
    }
    
    public void sleep() {
        System.out.println(name + " is sleeping");
    }
}

// Child class (subclass)
public class Dog extends Animal {
    private String breed;
    
    public void bark() {
        System.out.println(name + " is barking");
    }
}

// Usage
Dog dog = new Dog();
dog.name = "Buddy";
dog.eat();    // Inherited from Animal
dog.bark();   // Defined in Dog
```

## Types of Inheritance

### 1. Single Inheritance
One child inherits from one parent.

```java
class Vehicle { }
class Car extends Vehicle { }
```

### 2. Multilevel Inheritance
Chain of inheritance.

```java
class Vehicle { }
class Car extends Vehicle { }
class SportsCar extends Car { }
```

### 3. Hierarchical Inheritance
Multiple children inherit from one parent.

```java
class Animal { }
class Dog extends Animal { }
class Cat extends Animal { }
class Bird extends Animal { }
```

### 4. Multiple Inheritance
Java does not support multiple inheritance with classes (avoided to prevent diamond problem). Use interfaces instead.

```java
// Not allowed in Java:
// class C extends A, B { }

// Use interfaces instead:
interface A { }
interface B { }
class C implements A, B { }
```

## Method Overriding

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
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Cat meows");
    }
}
```

## The super Keyword

Refers to parent class. Used to:

### 1. Call parent constructor
```java
class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
}

class Dog extends Animal {
    private String breed;
    
    public Dog(String name, String breed) {
        super(name);  // Call parent constructor
        this.breed = breed;
    }
}
```

### 2. Access parent methods
```java
class Animal {
    public void eat() {
        System.out.println("Eating");
    }
}

class Dog extends Animal {
    @Override
    public void eat() {
        super.eat();  // Call parent method
        System.out.println("Dog is eating bones");
    }
}
```

### 3. Access parent fields
```java
class Parent {
    protected int value = 10;
}

class Child extends Parent {
    private int value = 20;
    
    public void display() {
        System.out.println(super.value);  // Parent's value: 10
        System.out.println(this.value);   // Child's value: 20
    }
}
```

## Constructor Chaining

Parent constructor always called before child constructor.

```java
class Grandparent {
    public Grandparent() {
        System.out.println("Grandparent constructor");
    }
}

class Parent extends Grandparent {
    public Parent() {
        super();  // Implicit if not specified
        System.out.println("Parent constructor");
    }
}

class Child extends Parent {
    public Child() {
        super();  // Implicit if not specified
        System.out.println("Child constructor");
    }
}

// Creating Child:
// Output:
// Grandparent constructor
// Parent constructor
// Child constructor
```

## Access Control in Inheritance

### Private Members
Not inherited, not accessible in child class.

```java
class Parent {
    private int secret = 42;
}

class Child extends Parent {
    public void display() {
        // System.out.println(secret);  // Error: cannot access
    }
}
```

### Protected Members
Inherited and accessible in child class.

```java
class Parent {
    protected int value = 42;
}

class Child extends Parent {
    public void display() {
        System.out.println(value);  // OK: protected is accessible
    }
}
```

### Public Members
Inherited and accessible everywhere.

```java
class Parent {
    public int value = 42;
}

class Child extends Parent {
    // value is fully accessible
}
```

## Real-World Example: Employee Hierarchy

```java
// Base class
public class Employee {
    protected String name;
    protected String id;
    protected double baseSalary;
    
    public Employee(String name, String id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }
    
    public double calculateSalary() {
        return baseSalary;
    }
    
    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Salary: " + calculateSalary());
    }
}

// Manager - adds management specific features
public class Manager extends Employee {
    private double bonus;
    private int teamSize;
    
    public Manager(String name, String id, double baseSalary, int teamSize) {
        super(name, id, baseSalary);
        this.teamSize = teamSize;
        this.bonus = 0;
    }
    
    @Override
    public double calculateSalary() {
        return baseSalary + bonus;
    }
    
    public void assignBonus(double bonus) {
        this.bonus = bonus;
    }
}

// Developer - adds technical skills
public class Developer extends Employee {
    private String primaryLanguage;
    private int experienceYears;
    
    public Developer(String name, String id, double baseSalary, 
                    String language, int experience) {
        super(name, id, baseSalary);
        this.primaryLanguage = language;
        this.experienceYears = experience;
    }
    
    @Override
    public double calculateSalary() {
        return baseSalary + (experienceYears * 1000);  // Experience bonus
    }
    
    public void code() {
        System.out.println(name + " is coding in " + primaryLanguage);
    }
}

// Usage
Manager manager = new Manager("Alice", "M001", 80000, 5);
manager.assignBonus(10000);
manager.displayInfo();

Developer dev = new Developer("Bob", "D001", 70000, "Java", 5);
dev.displayInfo();
dev.code();
```

## Abstract Classes

Cannot be instantiated. Used as base class for common behavior.

```java
public abstract class Shape {
    protected String color;
    
    public Shape(String color) {
        this.color = color;
    }
    
    // Abstract method - must be implemented by child
    public abstract double calculateArea();
    
    // Concrete method - inherited as-is
    public void displayColor() {
        System.out.println("Color: " + color);
    }
}

public class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

public class Rectangle extends Shape {
    private double width;
    private double height;
    
    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
}
```

## Final Keyword

### Final Class
Cannot be inherited.

```java
public final class ImmutableClass {
    // Cannot be extended
}
```

### Final Method
Cannot be overridden.

```java
public class Parent {
    public final void criticalMethod() {
        // Cannot be overridden by child
    }
}
```

## When to Use Inheritance

### Use When:
- Clear "IS-A" relationship exists
- Child is specialized version of parent
- Need to reuse common code
- Polymorphic behavior needed

### Avoid When:
- "HAS-A" relationship (use composition)
- No clear hierarchical relationship
- Would violate Liskov Substitution Principle
- Multiple inheritance needed (use interfaces)

## Inheritance vs Composition

### Inheritance (IS-A)
```java
class Car extends Vehicle {
    // Car IS-A Vehicle
}
```

### Composition (HAS-A)
```java
class Car {
    private Engine engine;  // Car HAS-AN Engine
    
    public Car() {
        engine = new Engine();
    }
}
```

Prefer composition over inheritance when possible.

## Advantages

1. **Code Reusability:** Avoid duplication
2. **Extensibility:** Easy to add new features
3. **Polymorphism:** Treat different objects uniformly
4. **Organization:** Clear hierarchical structure
5. **Maintenance:** Changes in parent affect all children

## Disadvantages

1. **Tight Coupling:** Changes in parent affect children
2. **Fragility:** Parent changes can break children
3. **Complexity:** Deep hierarchies hard to understand
4. **Inflexibility:** Cannot change parent at runtime

## Best Practices

1. Keep inheritance hierarchies shallow (2-3 levels maximum)
2. Use abstract classes for common behavior
3. Override methods with @Override annotation
4. Call super() explicitly when needed
5. Consider composition over inheritance
6. Follow Liskov Substitution Principle
7. Make classes final if not designed for inheritance
8. Document inheritance relationships clearly

## Common Mistakes

1. Using inheritance for code reuse without IS-A relationship
2. Creating deep inheritance hierarchies
3. Forgetting to call super() in constructors
4. Breaking parent class contract in child
5. Making everything inheritable

## Summary

- Inheritance enables code reuse through IS-A relationships
- Child class inherits parent properties and methods
- Use extends keyword for inheritance
- Override methods for specific behavior
- Use super to access parent members
- Prefer shallow hierarchies
- Consider composition as alternative

---

Last Updated: February 4, 2026
