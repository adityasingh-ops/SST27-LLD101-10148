# Low Level Design Course Notes

## Table of Contents
1. [OOP Fundamentals](#oop-fundamentals)
2. [SOLID Principles](#solid-principles)
3. [Design Patterns](#design-patterns)
4. [Immutability](#immutability)

---

## OOP Fundamentals

### Classes and Objects
A class is a blueprint for creating objects. An object is an instance of a class.

### Encapsulation
Hiding internal state and requiring interaction through methods. Use private fields with public getters/setters.

### Inheritance
Creating new classes from existing ones to reuse code. Child class inherits properties and methods from parent class.

### Polymorphism
Same method name behaving differently based on context. Achieved through method overriding and interfaces.

### Abstraction
Hiding complex implementation details and showing only necessary features. Use abstract classes and interfaces.

---

## SOLID Principles

### 1. Single Responsibility Principle (SRP)
A class should have only one reason to change. Each class should handle one specific responsibility.

**Example Problem:** A class handling both order processing and email sending.
**Solution:** Separate into OrderService and EmailClient classes.

**Location:** [SOLID/ex01](SOLID/ex01)

### 2. Open/Closed Principle (OCP)
Classes should be open for extension but closed for modification. Use inheritance or interfaces to add new features.

**Example:** Adding new video codecs without modifying existing Player class.

**Location:** [SOLID/ex02](SOLID/ex02)

### 3. Liskov Substitution Principle (LSP)
Subtypes must be substitutable for their base types without breaking functionality.

**Example Problem:** Square extending Rectangle and breaking expected behavior.
**Solution:** Create separate classes or use interfaces properly.

**Location:** [SOLID/ex05](SOLID/ex05)

### 4. Interface Segregation Principle (ISP)
Clients should not depend on interfaces they do not use. Create smaller, specific interfaces.

**Example:** Splitting large interfaces into focused ones like Flyable, Swimmable instead of one large Animal interface.

**Location:** [SOLID/ex06](SOLID/ex06)

### 5. Dependency Inversion Principle (DIP)
High-level modules should not depend on low-level modules. Both should depend on abstractions.

**Example:** ShippingCostCalculator depends on IShippingPolicy interface, not concrete shipment classes.

**Location:** [SOLID/ex03](SOLID/ex03), [SOLID/ex04](SOLID/ex04)

---

## Design Patterns

### Creational Patterns

#### 1. Singleton Pattern
Ensures only one instance of a class exists and provides global access to it.

**Use Case:** Configuration management, database connections, logging.

**Implementation:**
- Private constructor
- Static instance variable
- Public static getInstance() method
- Thread safety considerations
- Protection against reflection and serialization

**Location:** [singleton-config](singleton-config)

**Key Points:**
- Use lazy initialization for memory efficiency
- Implement readResolve() to prevent multiple instances during deserialization
- Throw exception in constructor if instance already exists to prevent reflection attacks

---

#### 2. Builder Pattern
Separates object construction from representation. Creates complex objects step by step.

**Use Case:** Creating objects with many optional parameters, enforcing object validity.

**Implementation:**
- Static nested Builder class
- Required fields in Builder constructor
- Optional fields via setter methods
- build() method validates and creates the object
- Make target class immutable

**Location:** [builder-orders](builder-orders)

**Key Points:**
- Validate in build() method
- Make main class constructor private
- Return Builder from setter methods for method chaining
- Use defensive copying for collections

---

### Structural Patterns

#### 3. Adapter Pattern
Converts interface of a class into another interface clients expect. Makes incompatible interfaces work together.

**Use Cases:**
- Integrating third-party payment gateways with different APIs
- Converting CSV data format to application format
- Wrapping legacy systems

**Implementation:**
- Create adapter interface matching client expectations
- Implement adapter class wrapping the adaptee
- Translate method calls between interfaces

**Location:** [design-pattern-assignments/adapter-payments](design-pattern-assignments/adapter-payments), [design-pattern-assignments/adapter-csv-import](design-pattern-assignments/adapter-csv-import), [employee-adapter-java-sources](employee-adapter-java-sources)

**Key Points:**
- Adapter holds reference to adaptee
- Does not modify adaptee code
- Can convert data formats and method signatures

---

#### 4. Decorator Pattern
Adds new functionality to objects dynamically without altering their structure. Alternative to subclassing.

**Use Cases:**
- Adding features to notifications (SMS, WhatsApp, Slack)
- Adding beverage toppings (chocolate, milk, whipped cream)
- Adding game character power-ups (speed, damage, visual effects)

**Implementation:**
- Create component interface
- Concrete component implements interface
- Abstract decorator implements interface and holds component reference
- Concrete decorators extend abstract decorator and add behavior

**Location:** [beverages_decorator](beverages_decorator), [decorator-exercises-starter](decorator-exercises-starter)

**Key Points:**
- Decorators wrap other decorators or components
- Preserves interface of wrapped object
- Can add behavior before or after delegating
- Stackable and removable at runtime

---

#### 5. Facade Pattern
Provides simplified interface to complex subsystem. Hides complexity from clients.

**Use Cases:**
- Report generation combining multiple exporters (PDF, Excel, CSV)
- Video processing pipeline with encoding, compression, quality adjustment
- Complex API simplification

**Implementation:**
- Create facade class with simple methods
- Facade coordinates multiple subsystem classes
- Clients interact only with facade

**Location:** [design-pattern-assignments/facade-report-bundle](design-pattern-assignments/facade-report-bundle), [design-pattern-assignments/facade-video](design-pattern-assignments/facade-video)

**Key Points:**
- Reduces coupling between clients and subsystems
- Does not prevent direct subsystem access if needed
- Simplifies common workflows

---

#### 6. Flyweight Pattern
Reduces memory usage by sharing common state between multiple objects.

**Use Case:** Text editor with many characters sharing font, size, color information.

**Implementation:**
- Separate intrinsic (shared) state from extrinsic (unique) state
- Create factory to manage flyweight objects
- Reuse existing flyweights instead of creating new ones

**Location:** [design-pattern-assignments/flyweight-glyphs](design-pattern-assignments/flyweight-glyphs)

**Key Points:**
- Intrinsic state stored in flyweight, extrinsic passed as parameters
- Factory ensures object reuse
- Significant memory savings with many similar objects

---

## Immutability

Making objects unchangeable after creation improves thread safety and prevents bugs.

**Implementation:**
- Make class final
- Make all fields private and final
- No setter methods
- Use defensive copying for mutable fields
- Initialize all fields in constructor

**Benefits:**
- Thread-safe without synchronization
- Easier to reason about
- Safe to share references
- Can be used as map keys

**Location:** [immutable-profiles](immutable-profiles)

**Key Points:**
- Collections should be unmodifiable (Collections.unmodifiableList)
- Defensive copy when returning mutable objects
- Validate in constructor
- Combine with Builder pattern for complex objects

---

## Exercise Summary

### SOLID Exercises
- **ex01-ex10:** Progressive exercises covering all SOLID principles with real scenarios

### Design Pattern Exercises
- **Singleton:** Thread-safe configuration management
- **Builder:** Order creation with validation
- **Adapter:** Payment gateway integration, CSV imports, employee data
- **Decorator:** Notifications, beverages, game characters
- **Facade:** Report bundles, video processing
- **Flyweight:** Text editor glyph optimization

### Solutions
Complete solutions available in [SOLID_Solutions](SOLID_Solutions) directory.

---

## Build and Run Commands

### Basic Java Project
```bash
cd <project>/src
javac com/example/**/*.java
java com.example.<package>.Main
```

### Maven Project
```bash
cd <project>
mvn clean compile
mvn test
```

### Single File
```bash
javac FileName.java
java FileName
```

---

## Best Practices

1. **Always follow SOLID principles** in design
2. **Use design patterns appropriately**, not forcefully
3. **Write tests** for your implementations
4. **Keep classes small** and focused
5. **Favor composition** over inheritance
6. **Program to interfaces**, not implementations
7. **Make objects immutable** when possible
8. **Validate inputs** early
9. **Use meaningful names** for classes and methods
10. **Document complex logic** with comments

---

## Study Tips

1. Understand the problem before applying patterns
2. Practice implementing patterns from scratch
3. Compare starter code with solution code
4. Refactor existing code to apply principles
5. Think about real-world applications
6. Review violations to understand principles better

---

Last Updated: February 4, 2026
