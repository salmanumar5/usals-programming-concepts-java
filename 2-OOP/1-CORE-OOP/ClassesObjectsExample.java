/**
 * Demonstrates the concept of:
 * 1. What is a CLASS?
 * 2. What is an OBJECT?
 * 3. How objects are created in Java (new keyword)
 * 4. How methods & fields work inside classes
 * 5. Memory model (Stack + Heap)
 *
 * A class is a blueprint.
 * An object is a real-world instance created from the class.
 */

// Blueprint class
class Car {

    // Fields / attributes
    String brand;
    String color;
    int year;

    // Method / behavior
    void start() {
        System.out.println(brand + " is starting...");
    }

    void displayInfo() {
        System.out.println("Brand: " + brand + ", Color: " + color + ", Year: " + year);
    }
}

public class ClassesObjectsExample {

    public static void main(String[] args) {

        System.out.println("=== Classes & Objects Example ===");

        // Creating object #1
        Car car1 = new Car();
        car1.brand = "Tesla";
        car1.color = "Red";
        car1.year = 2022;

        car1.displayInfo();
        car1.start();


        // Creating object #2
        Car car2 = new Car();
        car2.brand = "BMW";
        car2.color = "Black";
        car2.year = 2024;

        car2.displayInfo();
        car2.start();

        /*
            Explanation of Memory:

            Car car1 = new Car();
            ---------------------------------------
            Stack: car1 (reference variable)
            Heap : [Car object #1]

            car1.brand = "Tesla"
            ⇒ brand value stored inside the object in HEAP.

            Same for car2 → separate object created.
            Classes are BLUEPRINTS, objects are INSTANCES.
        */
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF CLASS & OBJECT
====================================================================================

1. WHAT IS A CLASS?
-------------------
A class is a TEMPLATE or BLUEPRINT.

Example in real-life:
Class = "Car blueprint"
Object = "My Tesla", "My BMW"

Class defines:
- What data an object will have → fields/attributes
- What actions an object can perform → methods


2. WHAT IS AN OBJECT?
---------------------
An object is created from a class.
It has its own separate data.

Example:
Car car1 = new Car();
Car car2 = new Car();

car1 and car2 are DIFFERENT objects with their own fields.


3. MEMORY MODEL (VERY IMPORTANT)
--------------------------------
Car car1 = new Car();

STACK:
    car1 → (reference)

HEAP:
    Car object with its fields:
        brand
        color
        year


4. WHY DO WE NEED OBJECTS?
--------------------------
Objects help us model real-life things in code.

Cars, Animals, Bank Accounts, Users → all objects.


5. IMPORTANT NOTES:
-------------------
- Object creation uses `new` keyword
- Each object has its own copy of fields
- Methods operate on the object’s fields
- Multiple objects can be created from the same class


====================================================================================
*/
