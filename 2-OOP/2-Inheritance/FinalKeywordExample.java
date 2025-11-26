/**
 * Demonstrates the usage of the 'final' keyword in Java:
 *
 * 1. final variables  → cannot be reassigned
 * 2. final methods    → cannot be overridden
 * 3. final classes    → cannot be inherited
 * 4. final references → reference cannot change, object CAN change
 *
 * final enforces:
 * - Non-modifiability
 * - Safety
 * - Readability
 * - Preventing accidental changes
 */

// ------------------------------------------------------------
// 1. final VARIABLE (constant)
// ------------------------------------------------------------
class ConstantsDemo {
    final int SPEED_LIMIT = 120;        // cannot be reassigned
    static final double PI = 3.14159;   // static constant (common pattern)
}


// ------------------------------------------------------------
// 2. final METHOD (cannot be overridden)
// ------------------------------------------------------------
class Vehicle {
    final void showType() {
        System.out.println("This is a Vehicle");
    }
}

class Car extends Vehicle {

    // ❌ ERROR: Cannot override a final method
    // @Override
    // void showType() { }
}


// ------------------------------------------------------------
// 3. final CLASS (cannot be inherited)
// ------------------------------------------------------------
final class Utility {

    void display() {
        System.out.println("Utility class method");
    }

}

// ❌ ERROR: Cannot inherit from final class
// class ExtendedUtility extends Utility { }


// ------------------------------------------------------------
// 4. final reference vs final object
// ------------------------------------------------------------
class Person {

    String name;

    Person(String name) {
        this.name = name;
    }
}

class FinalReferenceDemo {

    final Person p = new Person("John");

    void test() {
        // p = new Person("Alice"); // ❌ Not allowed: cannot change reference

        p.name = "Updated Name";     // ✔ Allowed: object fields CAN change

        System.out.println("Updated person name: " + p.name);
    }
}


// ------------------------------------------------------------
// DRIVER CLASS
// ------------------------------------------------------------
public class FinalKeywordExample {

    public static void main(String[] args) {

        System.out.println("=== Final Keyword Example ===");

        ConstantsDemo cd = new ConstantsDemo();
        System.out.println("Constant SPEED_LIMIT = " + cd.SPEED_LIMIT);
        System.out.println("Static constant PI = " + ConstantsDemo.PI);

        System.out.println("\n=== Final Method Example ===");
        Car car = new Car();
        car.showType(); // from Vehicle

        System.out.println("\n=== Final Reference Example ===");
        FinalReferenceDemo frd = new FinalReferenceDemo();
        frd.test();
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF 'final' IN JAVA
====================================================================================

1. WHAT DOES final MEAN?
--------------------------
final = cannot change  
Java uses final to enforce restrictions and safety.

There are **3 main uses**:

- final variable     → cannot reassign
- final method       → cannot override
- final class        → cannot inherit


2. final VARIABLE
------------------
final int x = 10;
x = 20;   ❌ NOT allowed

Once assigned → cannot change.

Commonly used for:
✔ Constants  
✔ Configuration values  
✔ Read-only fields  


3. final METHOD
----------------
class A {
    final void show() { }
}

class B extends A {
    // void show() {} ❌ forbidden!
}

Makes method "locked".  
Ensures child classes cannot change critical behavior.


4. final CLASS
---------------
final class Utility { }

class X extends Utility { } ❌ error

Useful when:
✔ You want to prevent inheritance  
✔ Utility/helper classes  


5. final REFERENCE vs final OBJECT
----------------------------------
VERY IMPORTANT:

final Person p = new Person("John");

You cannot do:
p = new Person("Alice"); ❌ (reference cannot change)

But you CAN do:
p.name = "Alice"; ✔ (object fields can change)

Meaning:
- final freezes the reference
- NOT the object  


6. WHY DO WE NEED final?
------------------------
✔ Avoid accidental changes  
✔ Prevent unsafe inheritance  
✔ Improve code clarity  
✔ Enable certain compiler optimizations  
✔ Support immutability patterns  


7. REAL-WORLD USE CASES
------------------------
- final constants (PI, TAX_RATE)  
- final classes like `String`, `Integer`  
- Preventing overriding (for security-sensitive methods)  


====================================================================================
*/
