# Low Level Design Course - Complete Repository

## Overview

This repository contains comprehensive notes and exercises for Low Level Design (LLD) concepts including Object-Oriented Programming fundamentals, SOLID principles, and Design Patterns.

## Repository Structure

```
/notes/                          # Comprehensive documentation
  /01-OOP-Fundamentals/         # OOP concepts with examples
  /02-SOLID/                     # SOLID principles detailed notes
    /illustrations/              # Practical examples for each principle
  /03-Design-Patterns/           # Design pattern implementations
    /01-Creational/              # Singleton, Builder patterns
    /02-Structural/              # Adapter, Decorator, Facade, Flyweight
  /04-Immutability/              # Immutability concepts

/SOLID/                          # SOLID exercises (ex01-ex10)
/SOLID_Solutions/                # Solutions to SOLID exercises

/singleton-config/               # Singleton pattern exercise
/builder-orders/                 # Builder pattern exercise
/immutable-profiles/             # Immutability exercise

/design-pattern-assignments/     # Design pattern assignments
  /adapter-payments/             # Adapter pattern - payments
  /adapter-csv-import/           # Adapter pattern - CSV import
  /facade-report-bundle/         # Facade pattern - reports
  /facade-video/                 # Facade pattern - video
  /flyweight-glyphs/             # Flyweight pattern - text editor

/beverages_decorator/            # Decorator pattern example
/decorator-exercises-starter/    # Decorator pattern exercises
/employee-adapter-java-sources/  # Adapter pattern with Maven
```

## Quick Start

### 1. Study the Notes
Start with the comprehensive notes in the `/notes` folder:
- [notes/README.md](notes/README.md) - Main navigation

### 2. Practice SOLID Principles
Work through exercises in `/SOLID/` folder (ex01 through ex10)

### 3. Implement Design Patterns
Complete assignments in `/design-pattern-assignments/`

## Learning Path

### Week 1: OOP Fundamentals
1. Classes and Objects
2. Encapsulation
3. Inheritance
4. Polymorphism
5. Abstraction

### Week 2: SOLID Principles
1. Single Responsibility Principle (SRP)
2. Open/Closed Principle (OCP)
3. Liskov Substitution Principle (LSP)
4. Interface Segregation Principle (ISP)
5. Dependency Inversion Principle (DIP)

### Week 3-4: Design Patterns
1. Creational: Singleton, Builder
2. Structural: Adapter, Decorator, Facade, Flyweight
3. Immutability concepts

## Building and Running

### Java Projects
```bash
cd <project>/src
javac com/example/**/*.java
java com.example.<package>.Main
```

### Maven Projects
```bash
cd <project>
mvn clean compile
mvn test
```

## Key Concepts

### SOLID Quick Reference
- **SRP:** One class, one responsibility
- **OCP:** Open for extension, closed for modification
- **LSP:** Subtypes must be substitutable
- **ISP:** Small, focused interfaces
- **DIP:** Depend on abstractions

### Design Patterns Quick Reference
- **Singleton:** Single instance globally
- **Builder:** Complex object construction
- **Adapter:** Interface compatibility
- **Decorator:** Dynamic behavior addition
- **Facade:** Simplified complex subsystem
- **Flyweight:** Memory optimization

## Documentation

All exercises include:
- README with problem description
- Starter code
- Build and run instructions
- Acceptance criteria

Solutions demonstrate:
- SOLID principles application
- Clean code practices
- Proper abstraction
- Testable design

## Best Practices

1. Read the notes before attempting exercises
2. Try solving exercises yourself first
3. Compare your solution with provided solutions
4. Refactor your code to apply principles
5. Write tests for your implementations

## Resources

- [notes/](notes/) - Complete course notes
- [SOLID/](SOLID/) - SOLID principle exercises
- [SOLID_Solutions/](SOLID_Solutions/) - Exercise solutions

## Progress Tracking

- [ ] Complete OOP Fundamentals notes
- [ ] Complete all SOLID exercises (ex01-ex10)
- [ ] Implement Singleton pattern
- [ ] Implement Builder pattern
- [ ] Complete Adapter pattern assignments
- [ ] Complete Decorator pattern exercises
- [ ] Complete Facade pattern assignments
- [ ] Implement Flyweight pattern
- [ ] Practice immutability concepts

## Contributing

This is a learning repository. Feel free to:
- Add your own examples
- Create additional exercises
- Improve documentation
- Share insights in code comments

## Tips for Success

1. **Understand before coding:** Read and understand the principle/pattern first
2. **Start simple:** Begin with basic examples, then tackle complex scenarios
3. **Refactor iteratively:** Apply one principle at a time
4. **Test your code:** Write tests to ensure correctness
5. **Review regularly:** Revisit concepts to reinforce learning

## Common Pitfalls to Avoid

1. Over-engineering simple solutions
2. Applying patterns where not needed
3. Ignoring SOLID principles
4. Not testing code thoroughly
5. Copying solutions without understanding

## Assessment Criteria

Good implementations demonstrate:
- Correct application of principles/patterns
- Clean, readable code
- Proper separation of concerns
- Appropriate abstraction levels
- Comprehensive testing

---

Last Updated: February 4, 2026
Author: Course Participant
Repository: SST27-LLD101
