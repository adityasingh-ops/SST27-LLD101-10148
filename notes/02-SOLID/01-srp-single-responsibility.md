# Single Responsibility Principle (SRP)

## Definition

A class should have only one reason to change. Each class should have one job or responsibility.

## The Principle

"A class should have one, and only one, reason to change."  
- Robert C. Martin

## Core Concept

When a class handles multiple responsibilities, changes to one responsibility can affect or break the others. Keeping responsibilities separate makes code more maintainable and less prone to bugs.

## Simple Explanation

Think of a Swiss Army knife versus separate tools. While a Swiss Army knife does many things, when the knife blade breaks, you lose the whole tool. Separate tools mean one breaking does not affect others.

## Identification of Violations

### Ask These Questions
1. Does this class have more than one responsibility?
2. Can I describe the class purpose in one sentence without using "and" or "or"?
3. Would changes in different parts of the system require changes to this class?
4. Does this class have methods that operate on completely different data?

### Common Violations
- Class handling both business logic and data persistence
- Class managing UI and calculations
- Class doing validation and sending notifications
- Class with multiple unrelated methods

## Example: Violation

```java
// BAD: Multiple responsibilities in one class
public class Employee {
    private String name;
    private double salary;
    
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    // Responsibility 1: Calculate pay
    public double calculatePay() {
        return salary * 1.1;  // 10% bonus
    }
    
    // Responsibility 2: Generate report
    public void generateReport() {
        System.out.println("Employee Report");
        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
    }
    
    // Responsibility 3: Save to database
    public void save() {
        // Database connection code
        // SQL insert statement
        System.out.println("Saving to database: " + name);
    }
}
```

### Problems with Above Code
1. Payroll policy changes require modifying Employee class
2. Report format changes require modifying Employee class
3. Database changes require modifying Employee class
4. Class is hard to test (database dependency)
5. Class violates SRP - has 3 reasons to change

## Example: Solution

```java
// GOOD: Each class has single responsibility

// Responsibility: Hold employee data
public class Employee {
    private String name;
    private double salary;
    
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public String getName() {
        return name;
    }
    
    public double getSalary() {
        return salary;
    }
}

// Responsibility: Calculate payroll
public class PayrollCalculator {
    public double calculatePay(Employee employee) {
        return employee.getSalary() * 1.1;  // 10% bonus
    }
}

// Responsibility: Generate reports
public class EmployeeReportGenerator {
    public void generateReport(Employee employee) {
        System.out.println("Employee Report");
        System.out.println("Name: " + employee.getName());
        System.out.println("Salary: " + employee.getSalary());
    }
}

// Responsibility: Database operations
public class EmployeeRepository {
    public void save(Employee employee) {
        // Database connection code
        // SQL insert statement
        System.out.println("Saving to database: " + employee.getName());
    }
    
    public Employee findById(String id) {
        // Database query
        return null;
    }
}
```

### Benefits of Solution
1. Payroll changes only affect PayrollCalculator
2. Report changes only affect EmployeeReportGenerator
3. Database changes only affect EmployeeRepository
4. Each class is easy to test independently
5. Each class has one reason to change

## Real-World Example: Order Processing

### Violation
```java
// BAD: OrderService doing too much
public class OrderService {
    public void checkout(String userEmail, double amount) {
        // Validate email
        if (!userEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid email");
        }
        
        // Process payment
        System.out.println("Processing payment of $" + amount);
        
        // Update inventory
        System.out.println("Updating inventory");
        
        // Send email notification
        System.out.println("Sending email to: " + userEmail);
        
        // Log transaction
        System.out.println("Logging transaction");
    }
}
```

### Solution
```java
// GOOD: Separated responsibilities

public class EmailValidator {
    public boolean isValid(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
}

public class PaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount);
    }
}

public class InventoryManager {
    public void updateInventory() {
        System.out.println("Updating inventory");
    }
}

public class EmailNotifier {
    public void sendEmail(String recipient, String message) {
        System.out.println("Sending email to: " + recipient);
        System.out.println("Message: " + message);
    }
}

public class TransactionLogger {
    public void log(String message) {
        System.out.println("Logging: " + message);
    }
}

// OrderService now coordinates - still single responsibility
public class OrderService {
    private EmailValidator emailValidator;
    private PaymentProcessor paymentProcessor;
    private InventoryManager inventoryManager;
    private EmailNotifier emailNotifier;
    private TransactionLogger logger;
    
    public OrderService(EmailValidator emailValidator, 
                       PaymentProcessor paymentProcessor,
                       InventoryManager inventoryManager,
                       EmailNotifier emailNotifier,
                       TransactionLogger logger) {
        this.emailValidator = emailValidator;
        this.paymentProcessor = paymentProcessor;
        this.inventoryManager = inventoryManager;
        this.emailNotifier = emailNotifier;
        this.logger = logger;
    }
    
    public void checkout(String userEmail, double amount) {
        if (!emailValidator.isValid(userEmail)) {
            throw new IllegalArgumentException("Invalid email");
        }
        
        paymentProcessor.processPayment(amount);
        inventoryManager.updateInventory();
        emailNotifier.sendEmail(userEmail, "Order confirmed");
        logger.log("Order completed for " + userEmail);
    }
}
```

## Benefits of SRP

1. **Easier to Understand:** Each class has clear purpose
2. **Easier to Test:** Test one responsibility at a time
3. **Easier to Modify:** Changes are localized
4. **Better Organization:** Code is well-structured
5. **Reduced Coupling:** Classes depend on specific responsibilities
6. **Improved Reusability:** Single-purpose classes are more reusable

## How to Apply SRP

### Step 1: Identify Responsibilities
List all things a class does.

### Step 2: Group Related Behavior
Group methods that work with same data or serve same purpose.

### Step 3: Extract Classes
Create new classes for each responsibility.

### Step 4: Use Dependency Injection
Connect classes through constructor or method parameters.

## Common Mistakes

1. **Too Fine-Grained:** Creating class for every single method
2. **Misidentifying Responsibilities:** Treating every method as separate responsibility
3. **Over-Engineering:** Applying SRP to simple classes unnecessarily
4. **Ignoring Context:** Not considering domain and use cases

## When to Apply

### Apply When:
- Class has multiple unrelated methods
- Changes in different areas require modifying same class
- Class is hard to test due to multiple dependencies
- Class is growing large and complex

### May Skip For:
- Very simple classes (DTOs, value objects)
- Classes with naturally related responsibilities
- Temporary or prototype code

## Related Concepts

### Cohesion
SRP promotes high cohesion - related functionality grouped together.

### Coupling
SRP reduces coupling - changes in one class do not ripple to others.

### Separation of Concerns
SRP is specific application of broader separation of concerns principle.

## Practical Tips

1. Use meaningful class names that describe single responsibility
2. If class name contains "And", "Or", "Manager", reconsider design
3. Keep classes small (typically under 200-300 lines)
4. Methods in class should operate on same data
5. Apply SRP iteratively through refactoring

## Testing Benefits

### Before SRP
```java
// Hard to test - requires mocking database, email, etc.
@Test
public void testCheckout() {
    // Setup database mock
    // Setup email server mock
    // Test entire checkout process
}
```

### After SRP
```java
// Easy to test - each class tested independently
@Test
public void testPaymentProcessing() {
    PaymentProcessor processor = new PaymentProcessor();
    processor.processPayment(100.0);
    // Test only payment logic
}

@Test
public void testEmailValidation() {
    EmailValidator validator = new EmailValidator();
    assertTrue(validator.isValid("user@example.com"));
    assertFalse(validator.isValid("invalid"));
}
```

## Summary

- One class, one responsibility, one reason to change
- Makes code easier to understand, test, and maintain
- Identify responsibilities by analyzing what class does
- Extract separate classes for each responsibility
- Use dependency injection to connect classes
- Do not over-apply - use judgment based on context
- Core principle for clean, maintainable code

## Exercise Questions

1. Does your class do more than one thing?
2. Can you describe the class in one sentence?
3. How many reasons does this class have to change?
4. Are there unrelated methods in this class?
5. Can this class be split into smaller, focused classes?

---

Refer to: [SRP Example Illustration](illustrations/srp-example.md)

Last Updated: February 4, 2026
