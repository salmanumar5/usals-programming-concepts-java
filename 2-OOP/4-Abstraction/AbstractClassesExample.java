/**
 * Demonstrates ABSTRACTION using ABSTRACT CLASSES in Java.
 *
 * Abstraction idea:
 * - Show only essential features
 * - Hide unnecessary details
 *
 * Abstract class:
 * - Cannot be instantiated directly
 * - Can have abstract methods (no body)
 * - Can also have normal methods (with body)
 *
 * This file shows:
 * 1. Abstract class Shape with abstract and concrete methods
 * 2. Concrete subclasses implementing abstract methods
 * 3. Using abstraction via parent reference
 */

abstract class Shape {

    // Common field for all shapes
    String color;

    Shape(String color) {
        this.color = color;
    }

    // Abstract method - no body, must be implemented by subclasses
    abstract double calculateArea();

    // Concrete method - same for all shapes
    void displayColor() {
        System.out.println("Shape color: " + color);
    }

    // Another concrete method using abstract method
    void printArea() {
        System.out.println("Area: " + calculateArea());
    }
}


class CircleShape extends Shape {

    double radius;

    CircleShape(String color, double radius) {
        super(color);     // call Shape constructor
        this.radius = radius;
    }

    // Implementing abstract method
    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }
}


class RectangleShape extends Shape {

    double width;
    double height;

    RectangleShape(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }

    // Implementing abstract method
    @Override
    double calculateArea() {
        return width * height;
    }
}


public class AbstractClassesExample {

    public static void main(String[] args) {

        System.out.println("=== Abstract Classes Example ===\n");

        // Shape s = new Shape("Red"); // ❌ Not allowed: cannot instantiate abstract class

        Shape circle = new CircleShape("Blue", 5.0);
        Shape rectangle = new RectangleShape("Green", 4.0, 6.0);

        System.out.println("--- Circle ---");
        circle.displayColor();
        circle.printArea();

        System.out.println("\n--- Rectangle ---");
        rectangle.displayColor();
        rectangle.printArea();

        /*
            Notice:
            - We are using Shape reference for both objects
            - calculateArea() is implemented differently in each subclass
            - Abstraction allows us to use a common type (Shape)
              while hiding implementation details
        */
    }
}


/*
====================================================================================
BEGINNER FRIENDLY EXPLANATION OF ABSTRACT CLASSES
====================================================================================

1. WHAT IS ABSTRACTION?
------------------------
Abstraction means:
- Showing only what is necessary
- Hiding the internal working details

Real life example:
- You drive a car using steering, brake, accelerator.
- You do not need to know how the engine internally burns fuel.


2. WHAT IS AN ABSTRACT CLASS?
------------------------------
An abstract class in Java:
- Can have abstract methods (no body)
- Can also have normal methods
- Cannot be instantiated directly

Example:
abstract class Shape {
    abstract double calculateArea();
    void displayColor() { ... }
}

You cannot do:
Shape s = new Shape();  // not allowed


3. WHY USE ABSTRACT CLASSES?
-----------------------------
Use them when:
- You want to provide a common base for related classes
- You want to force child classes to implement some methods
- You want shared code plus specific behavior

In this file:

Shape = abstract base
CircleShape, RectangleShape = concrete implementations


4. HOW DOES IT HELP?
---------------------
- All shapes must implement calculateArea()
- Common methods like displayColor() live in Shape
- You can write code that works with Shape, not with each specific subclass

Example:
Shape s = new CircleShape(...);
s.printArea(); // works because Shape defines printArea()


5. DIFFERENCE BETWEEN ABSTRACT METHOD AND NORMAL METHOD
--------------------------------------------------------
Abstract method:
- Declared with "abstract"
- No body
- Must be implemented in subclasses

Normal method:
- Has body
- Can be directly used by subclasses


6. WHY NOT JUST USE NORMAL CLASSES?
------------------------------------
Abstract classes allow you to:
- Prevent direct creation of incomplete objects
- Force a structure on child classes

For example:
Every shape must be able to calculateArea()
You do not want a plain Shape instance without a real area formula.


7. MEMORY OVERVIEW
-------------------
Shape circle = new CircleShape("Blue", 5.0);

STACK:
    circle → reference of type Shape

HEAP:
    CircleShape object
      - color = "Blue"
      - radius = 5.0

When you call:
circle.calculateArea()
JVM runs CircleShape's implementation.


8. SUMMARY
----------
- abstract class = partial implementation, cannot be directly used
- abstract method = must be implemented by child
- Abstraction hides details and enforces structure
- Very common in framework and library design

====================================================================================
*/
