/**
 * Demonstrates:
 * 1. Method Overriding
 * 2. Rules for overriding
 * 3. @Override annotation
 * 4. Dynamic Method Dispatch (runtime polymorphism)
 * 5. Access modifier rules
 * 6. Covariant return types
 * 7. Exception rules for overriding
 */

class Animal {

    // Base method to override
    public void makeSound() {
        System.out.println("Animal makes a sound");
    }

    // Covariant return example
    public Animal getInstance() {
        return new Animal();
    }

    // Exception rule example
    public void eat() throws Exception {
        System.out.println("Animal eats");
    }
}

class Dog extends Animal {

    // ------------------------------------------------------------
    // 1. Overriding makeSound()
    // ------------------------------------------------------------
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }

    // ------------------------------------------------------------
    // 2. Covariant return type
    // Parent returns Animal → Child can return Dog
    // ------------------------------------------------------------
    @Override
    public Dog getInstance() {
        return new Dog();
    }

    // ------------------------------------------------------------
    // 3. Exception rules
    // Parent throws Exception → Child can throw:
    // - same exception
    // - a subclass exception
    // - or no exception
    // ------------------------------------------------------------
    @Override
    public void eat() throws RuntimeException {
        System.out.println("Dog eats");
    }

    public void dogOnlyMethod() {
        System.out.println("Dog specific method");
    }
}


public class MethodOverridingExample {

    public static void main(String[] args) {

        System.out.println("=== Method Overriding ===");

        Animal animal = new Animal();
        animal.makeSound(); // Animal makes a sound

        Dog dog = new Dog();
        dog.makeSound();    // Dog barks

        System.out.println("\n=== Dynamic Method Dispatch ===");

        // Parent reference → Child object
        Animal ref = new Dog();

        ref.makeSound();  // Dog's version called at runtime
        // ref.dogOnlyMethod(); // ❌ Not allowed (reference type decides accessible methods)

        System.out.println("\n=== Covariant Return Type ===");
        Animal a1 = animal.getInstance();
        Animal a2 = dog.getInstance();   // returns Dog but stored in Animal reference

        System.out.println("a1 object: " + a1.getClass().getSimpleName());
        System.out.println("a2 object: " + a2.getClass().getSimpleName());

        System.out.println("\n=== Exception Rules Demonstration ===");
        try {
            ref.eat(); // calls Dog's eat()
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF METHOD OVERRIDING
====================================================================================

1. WHAT IS METHOD OVERRIDING?
-----------------------------
When a child class provides its **own version** of a method that is already defined
in the parent class → same name, same parameters.

Example:
Parent: makeSound()
Child : makeSound()


2. WHY DO WE OVERRIDE METHODS?
------------------------------
✔ To change/extend parent behavior  
✔ To implement runtime polymorphism  
✔ To give specialized behavior to child classes  


3. @Override ANNOTATION
------------------------
Not mandatory, but HIGHLY recommended.
It ensures:
- method signature matches parent
- avoids mistakes


4. DYNAMIC METHOD DISPATCH (VERY IMPORTANT)
-------------------------------------------
When parent reference refers to child object:

Animal a = new Dog();
a.makeSound();

Which method runs?
➡ Dog’s overridden method.

Why?
➡ Because overriding is decided at RUNTIME, not compile time.

This is runtime polymorphism.


5. RULES OF METHOD OVERRIDING
------------------------------
✔ Same method name  
✔ Same parameter list  
✔ Same return type  
✔ Access modifier cannot be more restrictive  
    - Parent public → Child must be public  
✔ Child can widen access modifier (protected → public)  
✔ @Override recommended  


6. COVARIANT RETURN TYPES
--------------------------
Parent returns Animal  
Child can return Dog (a subtype of Animal)

This is allowed.


7. EXCEPTION RULES
-------------------
Parent throws Exception → Child can throw:
✔ same exception  
✔ subclass exception  
✔ or no exception  

Parent throws RuntimeException → no restrictions.


8. WHY OVERRIDING ≠ OVERLOADING?
---------------------------------
Overloading = same method name, different parameters (compile-time)  
Overriding = same method, same signature (runtime)  


9. MEMORY + EXECUTION FLOW
---------------------------
Animal ref = new Dog();

Stack:
   ref → reference

Heap:
   Dog object

Method call resolved at runtime:
ref.makeSound()
→ JVM checks object type → Dog  
→ Dog’s method executes


====================================================================================
*/
