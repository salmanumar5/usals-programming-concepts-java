/**
 * Demonstrates:
 * 1. this keyword
 *    - Accessing fields
 *    - Calling methods
 *    - Calling constructors (this())
 *
 * 2. super keyword
 *    - Accessing parent class fields
 *    - Calling parent class methods
 *    - Calling parent constructor (super())
 *
 * 3. Constructor execution order
 */

class Animal {

    String type = "Animal";

    Animal() {
        System.out.println("Animal Constructor Called");
    }

    void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

// ------------------------------------------------------------
// CHILD CLASS
// ------------------------------------------------------------
class Dog extends Animal {

    String type = "Dog";
    String name;

    // 1. Default constructor
    Dog() {
        super(); // Calls parent constructor FIRST
        System.out.println("Dog Default Constructor Called");
    }

    // 2. Parameterized constructor demonstrating this()
    Dog(String name) {
        this(); // Calls Dog() first
        this.name = name;
    }

    // Method demonstrating 'this' and 'super'
    void showTypes() {
        System.out.println("this.type  = " + this.type);   // Dog's type
        System.out.println("super.type = " + super.type);  // Animal's type
    }

    void makeSound() {
        System.out.println("Dog barks");
    }

    void demonstrateMethodCalling() {
        System.out.println("Calling makeSound() using this:");
        this.makeSound(); // child's version

        System.out.println("Calling makeSound() using super:");
        super.makeSound(); // parent's version
    }
}


// ------------------------------------------------------------
// DRIVER CLASS
// ------------------------------------------------------------
public class ThisSuperExample {

    public static void main(String[] args) {

        System.out.println("=== this & super Example ===\n");

        Dog d1 = new Dog("Rocky");

        System.out.println("\n--- Demonstrating this vs super (fields) ---");
        d1.showTypes();

        System.out.println("\n--- Demonstrating this vs super (methods) ---");
        d1.demonstrateMethodCalling();
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF this & super
====================================================================================

1. THE 'this' KEYWORD
---------------------
`this` refers to the CURRENT object on which the method is called.

Use cases:
✔ Access current object’s fields  
✔ Call current object’s methods  
✔ Call current object's constructor using this()  
✔ Return the current object  


EXAMPLE:
this.name = name;
Meaning:
- Left side: current object's variable
- Right side: constructor parameter


2. the this() CONSTRUCTOR CALL
-------------------------------
Used to call ANOTHER constructor in the SAME class.

Rules:
✔ Must be the FIRST statement in the constructor  
✔ Helps avoid duplicate code  

Example:
Dog(String name) {
    this();   // calls Dog()
    this.name = name;
}


3. THE 'super' KEYWORD
----------------------
`super` refers to the PARENT class.

Use cases:
✔ Access parent class variables  
✔ Call parent class methods  
✔ Call parent constructor using super()  


4. the super() CONSTRUCTOR CALL
-------------------------------
Every child constructor AUTOMATICALLY calls super() if you don't write it.

Order of execution:
Parent constructor → Child constructor


5. this vs super SUMMARY
------------------------

| Keyword  | Refers To             | Used For |
|----------|------------------------|----------|
| this     | Current object         | fields, methods, this() |
| super    | Parent class object    | parent fields, methods, super() |


6. WHY DO WE NEED THIS & SUPER?
-------------------------------
✔ Avoid naming conflicts  
✔ Support inheritance  
✔ Reuse parent initialization logic  
✔ Maintain readability  

Example:
this.name = name  → resolves variable conflicts  
super(type)       → initializes parent fields  


7. MEMORY DIAGRAM (during Dog("Rocky"))
----------------------------------------

Stack:
    d1 ───► Dog object (in heap)

Heap (Dog object):
    type = "Dog"
    name = "Rocky"

Heap (Animal object part):
    type = "Animal"

Call order:
1. super() → Animal constructor
2. Dog() default constructor
3. Dog(name) parameterized constructor


====================================================================================
*/
