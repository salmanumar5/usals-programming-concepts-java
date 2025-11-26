/**
 * Demonstrates:
 * 1. Method Overloading (Compile-time Polymorphism)
 * 2. Rules of overloading
 * 3. Type promotion in overloading
 * 4. Ambiguity issues
 * 5. Constructor overloading
 */

class MathOperations {

    // Same method name → different parameter lists

    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }

    String add(String a, String b) {
        return a + b;
    }

    // Overloading with type promotion
    long add(long a, int b) {
        return a + b;
    }
}


class ConstructorExample {

    int x;
    String name;

    ConstructorExample() {
        this.x = 0;
        this.name = "Default";
    }

    ConstructorExample(int x) {
        this.x = x;
        this.name = "Unknown";
    }

    ConstructorExample(int x, String name) {
        this.x = x;
        this.name = name;
    }
}


public class MethodOverloadingExample {

    public static void main(String[] args) {

        MathOperations m = new MathOperations();

        System.out.println("=== Method Overloading ===");

        System.out.println("add(int, int) = " + m.add(5, 10));
        System.out.println("add(double, double) = " + m.add(2.5, 3.7));
        System.out.println("add(int, int, int) = " + m.add(1, 2, 3));
        System.out.println("add(String, String) = " + m.add("Hello ", "World"));

        System.out.println("\n=== Type Promotion in Overloading ===");
        System.out.println("add(long, int) = " + m.add(10L, 5));

        System.out.println("\n=== Constructor Overloading ===");
        ConstructorExample c1 = new ConstructorExample();
        ConstructorExample c2 = new ConstructorExample(10);
        ConstructorExample c3 = new ConstructorExample(10, "Umar");

        System.out.println("c1: x=" + c1.x + ", name=" + c1.name);
        System.out.println("c2: x=" + c2.x + ", name=" + c2.name);
        System.out.println("c3: x=" + c3.x + ", name=" + c3.name);
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF METHOD OVERLOADING
====================================================================================

1. WHAT IS METHOD OVERLOADING?
-------------------------------
Method Overloading = Same method name, different parameter list.

Example:
add(int, int)
add(double, double)
add(String, String)

This is **compile-time polymorphism** because the compiler decides
which method to call based on argument types.


2. RULES OF OVERLOADING
------------------------
✔ Same method name  
✔ Different parameter count OR parameter types  
✔ Return type can be same or different  
✔ Access modifiers don’t matter  
✔ Overloading happens in the same class or subclass  


3. WHY DO WE USE OVERLOADING?
------------------------------
✔ Cleaner API design  
✔ Convenience for users  
✔ Same behavior with different inputs  
✔ Makes code intuitive  

Example: println() has 18 overloaded versions.


4. TYPE PROMOTION IN OVERLOADING
--------------------------------
If exact match not found, Java promotes types.

Example:
add(long, int) gets called when passing (10L, 5)

If you overload:
add(int)
add(long)

Then:
add(5) → calls add(int)
add(5L) → calls add(long)


5. AMBIGUITY EXAMPLE (IMPORTANT)
--------------------------------
If two overloaded methods match equally well → compilers throws error.

Example:
void test(int a, long b)
void test(long a, int b)

Calling test(5, 5) → ambiguous


6. CONSTRUCTOR OVERLOADING
--------------------------
Same idea applies to constructors.

Helps create objects in different ways:
- Default constructor
- Constructor with only ID
- Constructor with full details


7. SUMMARY
----------
Overloading = compile-time → based on method signature  
Overriding = runtime → based on object type  


====================================================================================
*/