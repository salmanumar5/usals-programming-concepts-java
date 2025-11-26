/**
 * Demonstrates:
 * 1. Upcasting (Parent reference pointing to Child object)
 * 2. Downcasting (Converting Parent reference back to Child)
 * 3. Why upcasting is safe
 * 4. Why downcasting is dangerous without instanceof
 * 5. Method access & behavior during casting
 */

class Shape {

    void draw() {
        System.out.println("Drawing a generic shape");
    }

    void shapeMethod() {
        System.out.println("Method of Shape");
    }
}

class Circle extends Shape {

    @Override
    void draw() {
        System.out.println("Drawing Circle");
    }

    void circleOnlyMethod() {
        System.out.println("Circle-specific method");
    }
}

class Square extends Shape {

    @Override
    void draw() {
        System.out.println("Drawing Square");
    }

    void squareOnlyMethod() {
        System.out.println("Square-specific method");
    }
}


public class UpcastingDowncastingExample {

    public static void main(String[] args) {

        System.out.println("=== Upcasting ===");

        // -----------------------------
        // 1. Upcasting (Safe)
        // -----------------------------
        Shape s = new Circle();  // Upcasting: Child → Parent reference

        s.draw();        // Circle's overridden method runs (runtime polymorphism)
        s.shapeMethod(); // Parent method

        // s.circleOnlyMethod();  // ❌ Not allowed: reference decides visible methods

        System.out.println("\n=== Downcasting ===");

        // -----------------------------
        // 2. Downcasting (Potentially Unsafe)
        // -----------------------------

        Shape shapeRef = new Circle();   // Upcast
        Circle c = (Circle) shapeRef;    // Downcast back to Circle

        c.circleOnlyMethod();            // Allowed now
        c.draw();                        // Circle method

        System.out.println("\n=== Unsafe Downcasting Example ===");

        Shape shape2 = new Square();     // Upcast to Shape

        // ❌ This will throw: ClassCastException
        // Circle wrongCast = (Circle) shape2;

        // Correct way: Use instanceof
        if (shape2 instanceof Circle) {
            Circle circleObj = (Circle) shape2;
            circleObj.circleOnlyMethod();
        } else {
            System.out.println("shape2 is NOT a Circle → downcasting prevented");
        }
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF UPCASTING & DOWNCASTING
====================================================================================

1. WHAT IS UPCASTING?
----------------------
Upcasting = Assigning a child object to a parent reference.

Example:
Shape s = new Circle();

This is SAFE and allowed automatically by Java.

Why use upcasting?
✔ Enables runtime polymorphism  
✔ Allows writing generic code  
✔ Helps store different child objects in one array/list  


2. WHAT METHODS CAN BE ACCESSED AFTER UPCASTING?
------------------------------------------------
Only methods defined in the PARENT CLASS.

Example:
Shape s = new Circle();
s.draw();        ✔ allowed (overridden method runs)
s.circleOnlyMethod(); ❌ NOT allowed (ref type = Shape)


3. METHOD BEHAVIOR IN UPCASTING
-------------------------------
The reference type decides WHAT YOU CAN CALL.
The object type decides WHICH IMPLEMENTATION RUNS.

Shape s = new Circle();
s.draw();  // Circle's draw() runs (runtime polymorphism)


4. WHAT IS DOWNCASTING?
------------------------
Downcasting = Converting parent reference back to child type.

Shape s = new Circle();  // upcast
Circle c = (Circle) s;   // downcast

Now:
c.circleOnlyMethod();  // allowed


5. WHY DOWNCASTING IS DANGEROUS?
--------------------------------
If the object is NOT actually the child type you're casting to —  
Java throws:

ClassCastException

Example:
Shape s = new Square();
Circle c = (Circle) s;   // ❌ runtime error


6. SAFE WAY TO DOWNCAST: instanceof
-----------------------------------
if (s instanceof Circle) {
    Circle c = (Circle) s;
}

Always use instanceof before downcasting.


7. MEMORY DIAGRAM
-----------------

Shape s = new Circle();

STACK:
    s → reference

HEAP:
    Circle object
        draw() [Circle version]
        circleOnlyMethod()

Upcast only restricts access; it does NOT change the object.


8. SUMMARY
----------
✔ Upcasting → safe, automatic, enables runtime polymorphism  
✔ Downcasting → risky, must use instanceof  
✔ Reference type decides accessible methods  
✔ Object type decides which overridden method runs  


====================================================================================
*/
