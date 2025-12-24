# Encapsulation

## Definition

Encapsulation is the bundling of data (fields) and methods that operate on that data within a single unit (class), and restricting direct access to some components. It hides internal implementation details from the outside world.

## Core Principle

Data hiding and controlled access through methods.

## Why Encapsulation Matters

1. **Data Protection:** Prevents unauthorized or invalid modifications
2. **Flexibility:** Internal implementation can change without affecting users
3. **Maintainability:** Changes are localized to the class
4. **Control:** Validates data before storing
5. **Security:** Sensitive data remains protected

## Access Modifiers

### Private
Only accessible within the same class.

```java
private String password;  // Can only be accessed inside this class
```

### Public
Accessible from anywhere.

```java
public String getName() {  // Can be called from anywhere
    return name;
}
```

### Protected
Accessible within same package and subclasses.

```java
protected int age;  // Accessible in subclasses
```

### Default (Package-Private)
No modifier, accessible within same package only.

```java
String address;  // Package-private
```

## Implementation Pattern

### Step 1: Make fields private
```java
public class Student {
    private String name;
    private int age;
    private double gpa;
}
```

### Step 2: Provide public getters
```java
public String getName() {
    return name;
}

public int getAge() {
    return age;
}

public double getGpa() {
    return gpa;
}
```

### Step 3: Provide public setters with validation
```java
public void setName(String name) {
    if (name != null && !name.isEmpty()) {
        this.name = name;
    }
}

public void setAge(int age) {
    if (age > 0 && age < 150) {
        this.age = age;
    }
}

public void setGpa(double gpa) {
    if (gpa >= 0.0 && gpa <= 4.0) {
        this.gpa = gpa;
    }
}
```

## Example: Bank Account

### Without Encapsulation (Bad)
```java
public class BankAccount {
    public double balance;  // Anyone can modify directly
}

// Problem:
BankAccount account = new BankAccount();
account.balance = -1000;  // Invalid but allowed!
```

### With Encapsulation (Good)
```java
public class BankAccount {
    private double balance;  // Hidden from outside
    
    public double getBalance() {
        return balance;
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
}

// Usage:
BankAccount account = new BankAccount();
account.deposit(1000);      // Controlled access
account.withdraw(500);      // Validated operation
// account.balance = -1000; // Compilation error - protected!
```

## Benefits Demonstrated

### 1. Validation
```java
private int age;

public void setAge(int age) {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative");
    }
    this.age = age;
}
```

### 2. Read-Only Properties
```java
private final String accountNumber;

public String getAccountNumber() {
    return accountNumber;  // No setter - read only
}
```

### 3. Computed Properties
```java
private String firstName;
private String lastName;

public String getFullName() {
    return firstName + " " + lastName;  // Computed, not stored
}
```

### 4. Logging and Monitoring
```java
private double balance;

public void withdraw(double amount) {
    if (amount > 0 && balance >= amount) {
        balance -= amount;
        logTransaction("Withdrawal", amount);  // Track changes
    }
}
```

### 5. Format Control
```java
private String phoneNumber;

public void setPhoneNumber(String phone) {
    // Remove non-digit characters
    this.phoneNumber = phone.replaceAll("[^0-9]", "");
}

public String getPhoneNumber() {
    // Format for display: (123) 456-7890
    return String.format("(%s) %s-%s", 
        phoneNumber.substring(0,3),
        phoneNumber.substring(3,6),
        phoneNumber.substring(6));
}
```

## Real-World Example: Email Class

```java
public class Email {
    private String address;
    private boolean verified;
    
    public Email(String address) {
        setAddress(address);  // Use setter for validation
        this.verified = false;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        if (!isValidEmail(address)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.address = address.toLowerCase();  // Normalize
    }
    
    public boolean isVerified() {
        return verified;
    }
    
    // No public setter for verified - controlled through verify method
    public void verify(String code) {
        if (isValidVerificationCode(code)) {
            verified = true;
        }
    }
    
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
    
    private boolean isValidVerificationCode(String code) {
        // Verification logic
        return true;
    }
}
```

## Encapsulation with Collections

### Wrong Way
```java
private List<String> items = new ArrayList<>();

public List<String> getItems() {
    return items;  // Exposes internal list - can be modified!
}
```

### Right Way
```java
private List<String> items = new ArrayList<>();

public List<String> getItems() {
    return Collections.unmodifiableList(items);  // Read-only view
}

public void addItem(String item) {
    if (item != null && !item.isEmpty()) {
        items.add(item);
    }
}

public boolean removeItem(String item) {
    return items.remove(item);
}
```

## Getter and Setter Best Practices

### When to Provide Getters
- Always provide getters for properties that should be readable
- Return copies or unmodifiable views for mutable objects

### When to Provide Setters
- Only if property should be changeable after object creation
- Always validate input in setters
- Consider immutable design instead

### When to Skip Getters/Setters
- Internal state not relevant to users
- Computed values (provide getter only)
- Write-only properties (provide setter only, rare)

## Common Mistakes

1. **Making everything public:** Defeats purpose of encapsulation
2. **Getters returning mutable objects:** Breaks encapsulation
3. **No validation in setters:** Allows invalid state
4. **Unnecessary getters/setters:** Creates noise
5. **Using getters/setters within the class:** Unnecessary indirection

## Advantages

1. **Flexibility:** Change implementation without affecting users
2. **Reusability:** Well-encapsulated classes easy to reuse
3. **Testing:** Easier to test with controlled access points
4. **Debugging:** Breakpoints in getters/setters track changes
5. **Documentation:** Method names describe intent

## Encapsulation Levels

### Full Encapsulation
All fields private, controlled access through methods.

### Partial Encapsulation
Some fields accessible directly (protected), others private.

### No Encapsulation
All fields public, no protection.

## Summary

- Hide internal data using private access modifier
- Provide public methods for controlled access
- Validate data in setters
- Return copies of mutable objects from getters
- Encapsulation protects object integrity
- Makes code more maintainable and flexible

---

Last Updated: February 4, 2026
