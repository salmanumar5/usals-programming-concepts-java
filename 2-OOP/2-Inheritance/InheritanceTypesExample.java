/**
 * Demonstrates TYPES OF INHERITANCE in Java:
 *
 * 1. Single Inheritance
 * 2. Multilevel Inheritance
 * 3. Hierarchical Inheritance
 * 4. Why MULTIPLE inheritance is not allowed for classes
 * 5. How interfaces provide a workaround
 *
 * Java supports:
 * ✔ Single
 * ✔ Multilevel
 * ✔ Hierarchical
 *
 * Java does NOT support:
 * ✘ Multiple inheritance using classes
 */

 // ------------------------------------------------------------
 // 1. Single Inheritance
 // ------------------------------------------------------------

class Animal {
    void eat() {
        System.out.println("Animal eats food");
    }
}

class DogSingle extends Animal {
    void bark() {
        System.out.println("Dog barks");
    }
}


 // ------------------------------------------------------------
 // 2. Multilevel Inheritance
 // ------------------------------------------------------------

class LivingBeing {
    void breathe() {
        System.out.println("LivingBeing breathes");
    }
}

class Human extends LivingBeing {
    void speak() {
        System.out.println("Human speaks");
    }
}

class Programmer extends Human {
    void writeCode() {
        System.out.println("Programmer writes code");
    }
}


 // ------------------------------------------------------------
 // 3. Hierarchical Inheritance
 // ------------------------------------------------------------

class Vehicle {
    void start() {
        System.out.println("Vehicle starts");
    }
}

class Car extends Vehicle {
    void drive() {
        System.out.println("Car drives");
    }
}

class Bike extends Vehicle {
    void ride() {
        System.out.println("Bike rides");
    }
}


 // ------------------------------------------------------------
 // 4. Multiple Inheritance PROBLEM (Diamond Problem)
 // ------------------------------------------------------------

// Uncommenting the below will cause compilation error.
/*
class A {
    void show() { System.out.println("From A"); }
}

class B {
    void show() { System.out.println("From B"); }
}

// ERROR: Java does not allow class C to extend both A and B
class C extends A, B { }
*/

 // ------------------------------------------------------------
 // 5. Interfaces as a SOLUTION to multiple inheritance
 // ------------------------------------------------------------

interface AInterface {
    void show();
}

interface BInterface {
    void display();
}

class Demo implements AInterface, BInterface {
    public void show() {
        System.out.println("Show() from AInterface implemented");
    }

    public void display() {
        System.out.println("Display() from BInterface implemented");
    }
}


 // ------------------------------------------------------------
 // DRIVER CLASS
 // ------------------------------------------------------------

public class InheritanceTypesExample {

    public static void main(String[] args) {

        System.out.println("=== Single Inheritance ===");
        DogSingle d = new DogSingle();
        d.eat();
        d.bark();

        System.out.println("\n=== Multilevel Inheritance ===");
        Programmer p = new Programmer();
        p.breathe();
        p.speak();
        p.writeCode();

        System.out.println("\n=== Hierarchical Inheritance ===");
        Car c = new Car();
        c.start();
        c.drive();

        Bike b = new Bike();
        b.start();
        b.ride();

        System.out.println("\n=== Multiple Inheritance using Interfaces ===");
        Demo obj = new Demo();
        obj.show();
        obj.display();
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF INHERITANCE TYPES IN JAVA
====================================================================================

1. WHAT IS INHERITANCE?
------------------------
Inheritance allows one class to acquire properties & methods of another class.

Parent class = superclass
Child class = subclass

It helps achieve:
✔ code reuse
✔ hierarchy
✔ cleaner structure


2. SINGLE INHERITANCE
----------------------
One parent → one child  
Example:
Animal → Dog


3. MULTILEVEL INHERITANCE
--------------------------
Parent → child → grandchild  
Example:
LivingBeing → Human → Programmer

Useful in modeling layered relationships.


4. HIERARCHICAL INHERITANCE
---------------------------
One parent → multiple children  
Example:
Vehicle → Car, Bike

Common in real-world modeling.


5. WHY MULTIPLE INHERITANCE IS NOT ALLOWED?
-------------------------------------------
Java forbids:
class C extends A, B

Reason: The DIAMOND PROBLEM.

Example:
A and B both have method show()
If C extends both, which show() should it use?

Java avoids this confusion entirely by disallowing multiple class inheritance.


6. HOW INTERFACES SOLVE THIS PROBLEM?
-------------------------------------
Java allows:
class Demo implements AInterface, BInterface

Because interfaces have **no state**, only method signatures → no conflict.

The class MUST implement all methods → clear & predictable.


7. SUMMARY
----------
✔ Java supports: single, multilevel, hierarchical  
✔ Java does NOT support multiple inheritance with classes  
✔ Interfaces allow type-based multiple inheritance safely


====================================================================================
*/
