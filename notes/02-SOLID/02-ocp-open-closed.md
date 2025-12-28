# Open/Closed Principle (OCP)

## Definition

Software entities (classes, modules, functions) should be open for extension but closed for modification. You should be able to add new functionality without changing existing code.

## The Principle

"Software entities should be open for extension, but closed for modification."  
- Bertrand Meyer

## Core Concept

Once a class is written, tested, and working, you should not modify it to add new features. Instead, extend it through inheritance, interfaces, or composition to add new behavior.

## Simple Explanation

Think of a power outlet. You can plug in different devices (extension) without modifying the outlet itself (closed for modification). Each device works differently, but the outlet interface remains the same.

## Why OCP Matters

1. **Stability:** Existing tested code remains unchanged
2. **Safety:** Less risk of breaking working functionality
3. **Extensibility:** Easy to add new features
4. **Maintainability:** New code is separate from old code
5. **Testing:** No need to retest unchanged code

## Example: Violation

```java
// BAD: Modifying existing class for each new type
public class PaymentProcessor {
    public void processPayment(String paymentType, double amount) {
        if (paymentType.equals("credit_card")) {
            System.out.println("Processing credit card payment: $" + amount);
            // Credit card logic
        } else if (paymentType.equals("paypal")) {
            System.out.println("Processing PayPal payment: $" + amount);
            // PayPal logic
        } else if (paymentType.equals("bitcoin")) {
            System.out.println("Processing Bitcoin payment: $" + amount);
            // Bitcoin logic
        }
        // Need to modify this method for each new payment type!
    }
}
```

### Problems
1. Must modify existing code for new payment types
2. Risk of breaking existing payment methods
3. Violates Open/Closed Principle
4. Method grows indefinitely
5. Hard to test each payment type independently

## Example: Solution

```java
// GOOD: Closed for modification, open for extension

// Define abstraction
public interface PaymentMethod {
    void process(double amount);
}

// Concrete implementations - no modification needed to add new ones
public class CreditCardPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
        // Credit card specific logic
    }
}

public class PayPalPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
        // PayPal specific logic
    }
}

public class BitcoinPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Processing Bitcoin payment: $" + amount);
        // Bitcoin specific logic
    }
}

// Adding new payment method - just create new class, no modification!
public class ApplePayPayment implements PaymentMethod {
    @Override
    public void process(double amount) {
        System.out.println("Processing Apple Pay payment: $" + amount);
        // Apple Pay specific logic
    }
}

// Processor works with abstraction - never needs modification
public class PaymentProcessor {
    public void processPayment(PaymentMethod paymentMethod, double amount) {
        paymentMethod.process(amount);
    }
}

// Usage
PaymentProcessor processor = new PaymentProcessor();
processor.processPayment(new CreditCardPayment(), 100.0);
processor.processPayment(new PayPalPayment(), 50.0);
processor.processPayment(new BitcoinPayment(), 75.0);
processor.processPayment(new ApplePayPayment(), 25.0);  // New type, no code changes!
```

## Real-World Example: Shipping Cost Calculator

### Violation
```java
// BAD: Must modify for each new shipping type
public class ShippingCostCalculator {
    public double calculateCost(String shippingType, double weight) {
        if (shippingType.equals("standard")) {
            return weight * 5.0;
        } else if (shippingType.equals("express")) {
            return weight * 10.0;
        } else if (shippingType.equals("overnight")) {
            return weight * 20.0;
        } else if (shippingType.equals("international")) {
            return weight * 50.0;
        }
        return 0;
    }
}
```

### Solution
```java
// GOOD: Extension through new classes

public interface ShippingCostStrategy {
    double calculateCost(double weight);
}

public class StandardShipping implements ShippingCostStrategy {
    @Override
    public double calculateCost(double weight) {
        return weight * 5.0;
    }
}

public class ExpressShipping implements ShippingCostStrategy {
    @Override
    public double calculateCost(double weight) {
        return weight * 10.0;
    }
}

public class OvernightShipping implements ShippingCostStrategy {
    @Override
    public double calculateCost(double weight) {
        return weight * 20.0;
    }
}

public class InternationalShipping implements ShippingCostStrategy {
    @Override
    public double calculateCost(double weight) {
        return weight * 50.0;
    }
}

// Calculator is closed for modification
public class ShippingCostCalculator {
    public double calculateCost(ShippingCostStrategy strategy, double weight) {
        return strategy.calculateCost(weight);
    }
}

// Adding new shipping type requires no modification
public class SameDayShipping implements ShippingCostStrategy {
    @Override
    public double calculateCost(double weight) {
        return weight * 30.0 + 10.0;  // Extra fee for same day
    }
}
```

## Techniques to Achieve OCP

### 1. Inheritance
Extend base class with new functionality.

```java
public abstract class DiscountCalculator {
    public abstract double calculate(double price);
}

public class SeasonalDiscount extends DiscountCalculator {
    @Override
    public double calculate(double price) {
        return price * 0.9;  // 10% off
    }
}

public class MemberDiscount extends DiscountCalculator {
    @Override
    public double calculate(double price) {
        return price * 0.8;  // 20% off
    }
}
```

### 2. Interfaces
Define contracts that can be implemented differently.

```java
public interface Logger {
    void log(String message);
}

public class FileLogger implements Logger {
    public void log(String message) {
        // Log to file
    }
}

public class DatabaseLogger implements Logger {
    public void log(String message) {
        // Log to database
    }
}
```

### 3. Composition
Compose objects to add behavior.

```java
public class Report {
    private ReportFormatter formatter;
    
    public Report(ReportFormatter formatter) {
        this.formatter = formatter;
    }
    
    public String generate(Data data) {
        return formatter.format(data);
    }
}

public interface ReportFormatter {
    String format(Data data);
}

public class PDFFormatter implements ReportFormatter {
    public String format(Data data) {
        return "PDF: " + data;
    }
}

public class ExcelFormatter implements ReportFormatter {
    public String format(Data data) {
        return "Excel: " + data;
    }
}
```

## Example: Video Player

### Violation
```java
// BAD: Modifying for each codec
public class VideoPlayer {
    public void play(String filename, String codecType) {
        if (codecType.equals("mp4")) {
            System.out.println("Playing MP4 video: " + filename);
            // MP4 decoding logic
        } else if (codecType.equals("avi")) {
            System.out.println("Playing AVI video: " + filename);
            // AVI decoding logic
        } else if (codecType.equals("mkv")) {
            System.out.println("Playing MKV video: " + filename);
            // MKV decoding logic
        }
    }
}
```

### Solution
```java
// GOOD: Extensible design

public interface VideoCodec {
    void decode(String filename);
}

public class MP4Codec implements VideoCodec {
    @Override
    public void decode(String filename) {
        System.out.println("Decoding MP4: " + filename);
    }
}

public class AVICodec implements VideoCodec {
    @Override
    public void decode(String filename) {
        System.out.println("Decoding AVI: " + filename);
    }
}

public class MKVCodec implements VideoCodec {
    @Override
    public void decode(String filename) {
        System.out.println("Decoding MKV: " + filename);
    }
}

public class VideoPlayer {
    public void play(String filename, VideoCodec codec) {
        codec.decode(filename);
        System.out.println("Playing: " + filename);
    }
}

// New codec - no modification to VideoPlayer
public class WebMCodec implements VideoCodec {
    @Override
    public void decode(String filename) {
        System.out.println("Decoding WebM: " + filename);
    }
}
```

## Benefits of OCP

1. **Reduced Risk:** Existing code is not modified
2. **Easier Testing:** New code tested independently
3. **Better Organization:** Each variant in separate class
4. **Improved Maintainability:** Clear structure
5. **Flexibility:** Easy to add or remove variants
6. **Reusability:** Each implementation is self-contained

## How to Apply OCP

### Step 1: Identify Variation Points
Find areas where new types or behaviors will be added.

### Step 2: Create Abstraction
Define interface or abstract class.

### Step 3: Implement Variants
Create concrete implementations.

### Step 4: Use Polymorphism
Work with abstraction, not concrete types.

## Common Mistakes

1. **Premature Abstraction:** Creating abstractions before needed
2. **Over-Engineering:** Too many layers of abstraction
3. **Wrong Abstractions:** Abstractions that do not fit domain
4. **Ignoring OCP:** Using if-else chains when abstraction is better

## When to Apply

### Apply When:
- Multiple variants of behavior exist
- New variants will be added frequently
- Variants have similar structure
- Code has many if-else or switch statements for types

### May Skip For:
- Code that rarely changes
- Simple, straightforward logic
- Performance-critical sections where abstraction has cost
- Prototypes or temporary code

## Related Principles

### Strategy Pattern
Different algorithms encapsulated and interchangeable.

### Template Method Pattern
Algorithm skeleton in base class, steps implemented by subclasses.

### Factory Pattern
Creating objects without specifying exact classes.

## Signs of OCP Violation

1. Frequent modifications to same class for new features
2. Long chains of if-else or switch statements on types
3. Testing requires modifying existing tests for new features
4. Fear of breaking existing functionality when adding features

## Refactoring to OCP

### Before
```java
public class ReportGenerator {
    public void generate(String type) {
        if (type.equals("pdf")) {
            // PDF generation
        } else if (type.equals("excel")) {
            // Excel generation
        }
    }
}
```

### After
```java
public interface ReportGenerator {
    void generate();
}

public class PDFReportGenerator implements ReportGenerator {
    public void generate() {
        // PDF generation
    }
}

public class ExcelReportGenerator implements ReportGenerator {
    public void generate() {
        // Excel generation
    }
}
```

## Summary

- Open for extension, closed for modification
- Add new features through new code, not by changing existing code
- Use abstraction (interfaces, abstract classes) to achieve OCP
- Reduces risk of breaking existing functionality
- Makes system more flexible and maintainable
- Apply when variation is expected
- Do not over-engineer with unnecessary abstractions

## Exercise Questions

1. Are you modifying existing classes to add new features?
2. Do you have long if-else chains checking types?
3. Can you add new behavior without touching existing code?
4. Have you identified abstraction points?
5. Is your code easy to extend?

---

Refer to: [OCP Example Illustration](illustrations/ocp-example.md)

Last Updated: February 4, 2026
