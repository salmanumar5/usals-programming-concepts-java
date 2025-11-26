/**
 * Demonstrates RUNTIME POLYMORPHISM in Java using Method Overriding.
 *
 * Runtime Polymorphism (Dynamic Polymorphism):
 * - Decision of "which method to run" happens at RUNTIME
 * - Achieved using METHOD OVERRIDING
 * - Parent reference refers to child object
 *
 * Key Idea:
 * Reference type decides WHAT methods you can call.
 * Object type decides WHICH implementation runs.
 */

class Animal {

    void makeSound() {
        System.out.println("Animal makes a generic sound");
    }
}

class Dog extends Animal {

    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {

    @Override
    void makeSound() {
        System.out.println("Cat meows");
    }
}


public class RuntimePolymorphismExample {

    public static void main(String[] args) {

        System.out.println("=== Runtime Polymorphism Example ===\n");

        // Parent reference → Child objects
        Animal a1 = new Dog();
        Animal a2 = new Cat();

        // Method calls resolved at RUNTIME based on OBJECT type
        a1.makeSound();   // Dog's version
        a2.makeSound();   // Cat's version

        /*
            Even though reference type is Animal,
            JVM looks at the OBJECT TYPE (Dog, Cat)
            to decide which method to run.
        */

        System.out.println("\n=== Storing Different Objects in an Array ===");

        Animal[] animals = { new Dog(), new Cat(), new Animal() };

        for (Animal a : animals) {
            a.makeSound();  // prints Dog/Cat/Animal sounds respectively
        }
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF RUNTIME POLYMORPHISM
====================================================================================

1. WHAT IS RUNTIME POLYMORPHISM?
--------------------------------
The ability of the same method call to behave differently based on the object type.

Example:
Animal ref = new Dog();
ref.makeSound();  // Dog's version runs

Because the JVM decides at RUNTIME which method to call.


2. HOW IS IT ACHIEVED?
-----------------------
✔ By using method overriding  
✔ Parent reference → child object  
✔ Call overridden methods  


3. WHY USE RUNTIME POLYMORPHISM?
--------------------------------
✔ Write general code that works for many types  
✔ Extensible and flexible  
✔ Common in frameworks and enterprise apps  

Example:
Payment payment = new CreditCardPayment();
payment.pay();  // runtime decision


4. IMPORTANT RULE:
------------------
Reference type decides:
✔ What methods are accessible

Object type decides:
✔ Which implementation runs at runtime  


Example:
Animal a = new Dog();
a.makeSound();       // Dog's method runs
a.dogOnlyMethod();   ❌ not allowed (not visible in Animal reference)


5. MEMORY BEHAVIOR
-------------------
Animal a = new Dog();

STACK:
   a → reference

HEAP:
   Dog object
   (with overridden methods)

Method call goes to object → Dog.


6. REAL WORLD ANALOGY
---------------------
Think of a remote TV control:
Remote = reference type
TV brand = object type

Clicking “volume up” runs:
- Samsung implementation in a Samsung TV
- LG implementation in an LG TV


7. SUMMARY
----------
✔ Overriding enables runtime polymorphism  
✔ Parent reference, child object  
✔ Method choice happens at runtime  
✔ Improves flexibility, extensibility, cleaner design  

====================================================================================
*/
