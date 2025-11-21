/**
 * Demonstrates:
 * 1. Wrapper Classes in Java
 * 2. Autoboxing and Unboxing
 * 3. Common methods in wrapper classes
 * 4. Wrapper immutability
 * 5. Parsing numbers from Strings
 * 6. Comparison pitfalls (== vs equals)
 * 7. Integer caching (important interview topic)
 */

public class WrapperExample {

    // ------------------------------------------------------------
    // 1. Basic Wrapper Class Usage
    // ------------------------------------------------------------
    public void demonstrateWrappers() {
        System.out.println("=== Wrapper Classes Basic Usage ===");

        Integer intObj = Integer.valueOf(10);
        Double doubleObj = Double.valueOf(3.14);
        Boolean boolObj = Boolean.valueOf(true);

        System.out.println("Integer: " + intObj);
        System.out.println("Double: " + doubleObj);
        System.out.println("Boolean: " + boolObj);
    }


    // ------------------------------------------------------------
    // 2. Autoboxing and Unboxing
    // ------------------------------------------------------------
    public void demonstrateAutoboxing() {
        System.out.println("\n=== Autoboxing & Unboxing ===");

        // Autoboxing → primitive -> wrapper automatically
        Integer a = 10;               // Integer.valueOf(10)

        // Unboxing → wrapper -> primitive automatically
        int b = a;                    // a.intValue()

        System.out.println("Autoboxed Integer a = " + a);
        System.out.println("Unboxed int b = " + b);
    }


    // ------------------------------------------------------------
    // 3. Useful Wrapper Class Methods
    // ------------------------------------------------------------
    public void demonstrateUsefulMethods() {
        System.out.println("\n=== Useful Wrapper Methods ===");

        Integer num = 42;

        System.out.println("num.toString(): " + num.toString());
        System.out.println("Integer.parseInt(\"123\"): " + Integer.parseInt("123"));
        System.out.println("Double.parseDouble(\"3.99\"): " + Double.parseDouble("3.99"));
        System.out.println("Boolean.parseBoolean(\"true\"): " + Boolean.parseBoolean("true"));

        // Converting to different primitives
        System.out.println("num.byteValue(): " + num.byteValue());
        System.out.println("num.floatValue(): " + num.floatValue());
    }


    // ------------------------------------------------------------
    // 4. Comparison Pitfall: == vs equals()
    // ------------------------------------------------------------
    public void demonstrateComparisonPitfall() {
        System.out.println("\n=== == vs equals() with Wrappers ===");

        Integer x = 100;
        Integer y = 100;

        Integer p = 200;
        Integer q = 200;

        System.out.println("x == y (100) → " + (x == y));  // true due to caching
        System.out.println("x.equals(y) → " + x.equals(y)); // true

        System.out.println("p == q (200) → " + (p == q));  // false (outside cache range)
        System.out.println("p.equals(q) → " + p.equals(q)); // true

        /*
            IMPORTANT:
            Integer caches values from -128 to 127
            For values in this range, == works because objects are reused.
            Outside this range, new objects are created → == becomes false.
        */
    }


    // ------------------------------------------------------------
    // 5. Wrapper Immutability
    // ------------------------------------------------------------
    public void demonstrateWrapperImmutability() {
        System.out.println("\n=== Wrapper Immutability ===");

        Integer a = 10;
        Integer b = a;

        a = a + 5; // creates NEW Integer object (immutable behavior)

        System.out.println("a = " + a); // 15
        System.out.println("b = " + b); // 10 (unchanged)
    }


    // ------------------------------------------------------------
    // MAIN
    // ------------------------------------------------------------
    public static void main(String[] args) {

        WrapperExample ex = new WrapperExample();

        ex.demonstrateWrappers();
        ex.demonstrateAutoboxing();
        ex.demonstrateUsefulMethods();
        ex.demonstrateComparisonPitfall();
        ex.demonstrateWrapperImmutability();
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF WRAPPER CLASSES
====================================================================================

1. WHAT ARE WRAPPER CLASSES?
----------------------------
Primitive types (int, double, boolean) are NOT objects.
Wrapper classes allow primitives to be treated as objects:

Primitive     Wrapper Class
---------     -------------
int           Integer
double        Double
boolean       Boolean
char          Character

You need wrappers when:
- Using Collections (like ArrayList<Integer>)
- Converting strings to numbers
- Using generics
- Using methods that require objects


2. WHAT IS AUTOBOXING?
----------------------
Automatic conversion from primitive to object wrapper.

Example:
Integer x = 10;  
→ the compiler converts to  
Integer x = Integer.valueOf(10);


3. WHAT IS UNBOXING?
--------------------
Automatic conversion from wrapper to primitive.

Example:
int y = x;  
→ becomes  
int y = x.intValue();


4. WHY ARE WRAPPER CLASSES IMMUTABLE?
-------------------------------------
Wrapper classes behave like primitives:
- predictable
- thread-safe
- simple
- shareable (Integer caching)

Changing a wrapper ALWAYS creates a new object.


5. INTEGER CACHING (VERY IMPORTANT)
-----------------------------------
Integer caches values from **-128 to 127**
For these numbers → same object reused.

So:

Integer a = 100;
Integer b = 100;

a == b → true

But:

Integer x = 200;
Integer y = 200;

x == y → false (new object created)


6. ALWAYS USE equals() FOR COMPARISON
-------------------------------------
Do NOT use == unless comparing primitives.

Use:
a.equals(b)


7. WHEN WRAPPERS ARE USED YOU GET:
-----------------------------------
✔ nullability  
✔ ability to be stored in collections  
✔ many helper methods  
✔ type conversions  


====================================================================================
*/
