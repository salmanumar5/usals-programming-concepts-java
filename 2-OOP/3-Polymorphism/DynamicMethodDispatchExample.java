/**
 * Demonstrates DYNAMIC METHOD DISPATCH (Runtime Method Dispatch)
 *
 * Dynamic Method Dispatch:
 * - Mechanism in Java where the method to be executed is determined at RUNTIME,
 *   NOT COMPILE TIME.
 *
 * Achieved through:
 * - Method Overriding
 * - Parent reference pointing to Child object
 *
 * JVM decides the method implementation based on OBJECT type,
 * even when reference type is parent.
 */

class Parent {

    void show() {
        System.out.println("Parent: show()");
    }
}

class ChildA extends Parent {

    @Override
    void show() {
        System.out.println("ChildA: show()");
    }
}

class ChildB extends Parent {

    @Override
    void show() {
        System.out.println("ChildB: show()");
    }
}

public class DynamicMethodDispatchExample {

    public static void main(String[] args) {

        System.out.println("=== Dynamic Method Dispatch Example ===\n");

        Parent ref;   // reference of parent class

        // Pointing to ChildA's object
        ref = new ChildA();
        ref.show();   // ChildA: show() → decided at RUNTIME

        // Pointing to ChildB's object
        ref = new ChildB();
        ref.show();   // ChildB: show() → decided at RUNTIME

        // Pointing to Parent's own object
        ref = new Parent();
        ref.show();   // Parent: show()

        /*
            Notice:
            Same reference type (Parent)
            Same method call (ref.show())
            Different method executed depending on OBJECT type
        */
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF DYNAMIC METHOD DISPATCH
====================================================================================

1. WHAT IS DYNAMIC METHOD DISPATCH?
-----------------------------------
Dynamic Method Dispatch = runtime selection of overridden method.

Meaning:
Which method runs is decided when the program is RUNNING —
not at compile time.

Example:
Parent ref = new ChildA();
ref.show();   // ChildA's version runs


2. WHY DOES IT HAPPEN?
----------------------
Because Java is OBJECT-ORIENTED:
✔ Object type decides actual behavior
✔ Reference type decides access limitations


3. HOW DOES JVM DECIDE METHOD?
-------------------------------
Step-by-step:

Parent ref = new ChildA();

- At compile time:
  Compiler checks if Parent has method show()

- At runtime:
  JVM checks actual object type (ChildA)
  and executes ChildA's overridden version.

This mechanism enables runtime polymorphism.


4. WHY DO WE NEED IT?
----------------------
✔ Write flexible and extensible code  
✔ Avoid hardcoding logic for each type  
✔ Support polymorphic behavior  

Example:
Parent ref;
if(input == A) ref = new ChildA();
else if(input == B) ref = new ChildB();

ref.show();  // Works for any child type


5. REFERENCE TYPE VS OBJECT TYPE
---------------------------------
Reference type (Parent):
✔ Controls what methods are accessible

Object type (ChildA / ChildB):
✔ Controls which implementation runs

This allows powerful behavior abstraction.


6. MEMORY DIAGRAM
------------------

Parent ref = new ChildA();

STACK:
    ref → reference

HEAP:
    ChildA object (with overridden show())

Call:
ref.show()

Resolution:
→ JVM checks object type = ChildA  
→ Calls ChildA.show()


7. REAL-WORLD ANALOGY
----------------------
Think of a remote TV control.

Remote (reference) = Parent  
TV brand (object) = ChildA, ChildB

Pressing “Volume Up”:
- Runs Samsung version in Samsung TV
- Runs LG version in LG TV

Same button → different execution  
based on actual device.


8. SUMMARY
----------
✔ Dynamic Method Dispatch = runtime polymorphism  
✔ Method overriding required  
✔ Parent reference can refer to any child  
✔ JVM decides method at runtime based on object  
✔ Reference type controls what you can access  
✔ Object type controls what actually runs  

====================================================================================
*/
