/**
 * Demonstrates all important aspects of Type Casting in Java:
 *
 * 1. Implicit Casting (Widening)
 * 2. Explicit Casting (Narrowing)
 * 3. char ↔ int casting
 * 4. Casting floating numbers to integers
 * 5. Overflow during narrowing
 * 6. Type promotion in expressions
 * 7. Casting in arithmetic operations
 */

public class TypeCastingExample {

    // ------------------------------------------------------------
    // 1. Implicit Casting (Widening)
    // ------------------------------------------------------------
    public void demonstrateImplicitCasting() {
        System.out.println("=== Implicit Casting (Widening) ===");

        // Smaller → larger type (NO data loss)
        byte b = 10;
        int i = b;           // byte → int
        long l = i;          // int → long
        float f = l;         // long → float
        double d = f;        // float → double

        System.out.println("byte b = " + b);
        System.out.println("int i = " + i);
        System.out.println("long l = " + l);
        System.out.println("float f = " + f);
        System.out.println("double d = " + d);
    }


    // ------------------------------------------------------------
    // 2. Explicit Casting (Narrowing)
    // ------------------------------------------------------------
    public void demonstrateExplicitCasting() {
        System.out.println("\n=== Explicit Casting (Narrowing) ===");

        double d = 9.78;
        int i = (int) d;      // double → int (decimal lost)

        long l = 30000L;
        short s = (short) l;  // long → short (may overflow)

        System.out.println("double d = " + d + " → int = " + i);
        System.out.println("long l = " + l + " → short = " + s);
    }


    // ------------------------------------------------------------
    // 3. char ↔ int casting
    // ------------------------------------------------------------
    public void demonstrateCharCasting() {
        System.out.println("\n=== char <-> int Casting ===");

        char c = 'A';
        int ascii = c;      // char → int (ASCII/Unicode value)

        int code = 66;
        char letter = (char) code;

        System.out.println("char c = " + c + " → ASCII = " + ascii);
        System.out.println("int code = " + code + " → char = " + letter);
    }


    // ------------------------------------------------------------
    // 4. Floating → int truncation
    // ------------------------------------------------------------
    public void demonstrateTruncation() {
        System.out.println("\n=== Floating to int Truncation ===");

        double value = 5.99;
        int truncated = (int) value; // fractional part removed

        System.out.println("double = " + value + " → truncated int = " + truncated);
    }


    // ------------------------------------------------------------
    // 5. Overflow in Narrowing (Critical Example)
    // ------------------------------------------------------------
    public void demonstrateOverflow() {
        System.out.println("\n=== Overflow During Narrowing ===");

        int big = 130;            // byte range is -128 to 127
        byte small = (byte) big;  // overflow occurs

        System.out.println("Original int = " + big);
        System.out.println("After casting to byte = " + small);
        // 130 → binary wraps around → output: -126
    }


    // ------------------------------------------------------------
    // 6. Type Promotion in Expressions
    // ------------------------------------------------------------
    public void demonstrateTypePromotion() {
        System.out.println("\n=== Type Promotion in Expressions ===");

        byte a = 10;
        byte b = 20;

        // Java automatically promotes byte → int during arithmetic
        int result = a + b;

        System.out.println("byte a + byte b → int result = " + result);

        // If you want result in byte, cast explicitly
        byte c = (byte) (a + b);
        System.out.println("Explicit cast back to byte → " + c);
    }


    // ------------------------------------------------------------
    // 7. Casting in Arithmetic Expressions
    // ------------------------------------------------------------
    public void demonstrateArithmeticCasting() {
        System.out.println("\n=== Casting in Arithmetic Expressions ===");

        int x = 5;
        int y = 2;

        double result1 = x / y;           // integer division → 2.0
        double result2 = (double) x / y;  // correct division → 2.5
        double result3 = x / (double) y;  // also correct → 2.5
        double result4 = (double) (x / y); // WRONG → 2.0 (int division done first)

        System.out.println("x / y = " + result1);
        System.out.println("(double)x / y = " + result2);
        System.out.println("x / (double)y = " + result3);
        System.out.println("(double)(x / y) = " + result4 + "   // WRONG approach");
    }


    // ------------------------------------------------------------
    // MAIN: Run all examples
    // ------------------------------------------------------------
    public static void main(String[] args) {

        TypeCastingExample ex = new TypeCastingExample();

        ex.demonstrateImplicitCasting();
        ex.demonstrateExplicitCasting();
        ex.demonstrateCharCasting();
        ex.demonstrateTruncation();
        ex.demonstrateOverflow();
        ex.demonstrateTypePromotion();
        ex.demonstrateArithmeticCasting();
    }
}
