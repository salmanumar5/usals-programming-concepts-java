/**
 * Demonstrates all major operator categories in Java:
 *
 * 1. Arithmetic Operators (+, -, *, /, %)
 * 2. Unary Operators (++ , -- , + , - , !)
 * 3. Relational Operators (>, < , >= , <= , == , !=)
 * 4. Logical Operators (&&, ||, !)
 * 5. Assignment Operators (=, +=, -=, *=, /=, %=)
 * 6. Ternary Operator (condition ? true : false)
 * 7. Bitwise Operators (&, |, ^, ~)
 * 8. Shift Operators (<<, >>, >>>)
 * 9. Operator Precedence Examples
 *
 * Includes detailed comments and pitfalls (like i++ vs ++i).
 */

public class OperatorsExample {

    // ------------------------------------------------------------
    // 1. Arithmetic Operators
    // ------------------------------------------------------------
    public void demonstrateArithmetic() {
        int a = 10;
        int b = 3;

        System.out.println("=== Arithmetic Operators ===");
        System.out.println("a + b = " + (a + b));  // 13
        System.out.println("a - b = " + (a - b));  // 7
        System.out.println("a * b = " + (a * b));  // 30
        System.out.println("a / b = " + (a / b));  // 3 → integer division
        System.out.println("a % b = " + (a % b));  // 1 (remainder)
    }


    // ------------------------------------------------------------
    // 2. Unary Operators
    // ------------------------------------------------------------
    public void demonstrateUnary() {
        int x = 5;

        System.out.println("\n=== Unary Operators ===");
        System.out.println("Initial x = " + x);

        System.out.println("x++ (post-increment) = " + (x++));  // prints 5, then x becomes 6
        System.out.println("After x++ → x = " + x);             // 6

        System.out.println("++x (pre-increment) = " + (++x));  // increments first → prints 7
        System.out.println("After ++x → x = " + x);            // 7

        System.out.println("x-- (post-decrement) = " + (x--)); // prints 7, then x = 6
        System.out.println("--x (pre-decrement) = " + (--x)); // x becomes 5 → prints 5

        boolean flag = true;
        System.out.println("!flag = " + (!flag)); // false
    }


    // ------------------------------------------------------------
    // 3. Relational Operators
    // ------------------------------------------------------------
    public void demonstrateRelational() {
        int a = 10, b = 20;

        System.out.println("\n=== Relational Operators ===");
        System.out.println("a > b = " + (a > b));     // false
        System.out.println("a < b = " + (a < b));     // true
        System.out.println("a >= b = " + (a >= b));   // false
        System.out.println("a <= b = " + (a <= b));   // true
        System.out.println("a == b = " + (a == b));   // false
        System.out.println("a != b = " + (a != b));   // true
    }


    // ------------------------------------------------------------
    // 4. Logical Operators
    // ------------------------------------------------------------
    public void demonstrateLogical() {
        boolean x = true;
        boolean y = false;

        System.out.println("\n=== Logical Operators ===");
        System.out.println("x && y = " + (x && y)); // false
        System.out.println("x || y = " + (x || y)); // true
        System.out.println("!x = " + (!x));         // false
    }


    // ------------------------------------------------------------
    // 5. Assignment Operators
    // ------------------------------------------------------------
    public void demonstrateAssignment() {
        int a = 10;

        System.out.println("\n=== Assignment Operators ===");
        System.out.println("a = " + a);

        a += 5; // a = a + 5
        System.out.println("a += 5 → " + a);

        a -= 3; // a = a - 3
        System.out.println("a -= 3 → " + a);

        a *= 2; // a = a * 2
        System.out.println("a *= 2 → " + a);

        a /= 4; // a = a / 4
        System.out.println("a /= 4 → " + a);

        a %= 3; // a = a % 3
        System.out.println("a %= 3 → " + a);
    }


    // ------------------------------------------------------------
    // 6. Ternary Operator
    // ------------------------------------------------------------
    public void demonstrateTernary() {
        int age = 18;

        String result = (age >= 18)
                ? "Eligible to vote"
                : "Not eligible";

        System.out.println("\n=== Ternary Operator ===");
        System.out.println("Result: " + result);
    }


    // ------------------------------------------------------------
    // 7. Bitwise Operators
    // ------------------------------------------------------------
    public void demonstrateBitwise() {
        int a = 5;  // 0101
        int b = 3;  // 0011

        System.out.println("\n=== Bitwise Operators ===");
        System.out.println("a & b = " + (a & b)); // 0001 → 1
        System.out.println("a | b = " + (a | b)); // 0111 → 7
        System.out.println("a ^ b = " + (a ^ b)); // 0110 → 6
        System.out.println("~a = " + (~a));       // bitwise NOT
    }


    // ------------------------------------------------------------
    // 8. Shift Operators
    // ------------------------------------------------------------
    public void demonstrateShift() {
        int x = 8; // 1000 in binary

        System.out.println("\n=== Shift Operators ===");
        System.out.println("x << 1 = " + (x << 1));  // 10000 (16)
        System.out.println("x >> 1 = " + (x >> 1));  // 0100 (4)
        System.out.println("x >>> 1 = " + (x >>> 1)); // unsigned shift
    }


    // ------------------------------------------------------------
    // 9. Precedence Example
    // ------------------------------------------------------------
    public void demonstratePrecedence() {
        int result = 10 + 5 * 2; // * has higher precedence → 10 + 10 = 20

        System.out.println("\n=== Operator Precedence ===");
        System.out.println("10 + 5 * 2 = " + result);

        int withBrackets = (10 + 5) * 2;
        System.out.println("(10 + 5) * 2 = " + withBrackets);
    }


    // ------------------------------------------------------------
    // MAIN: Run all demonstrations
    // ------------------------------------------------------------
    public static void main(String[] args) {
        OperatorsExample ex = new OperatorsExample();

        ex.demonstrateArithmetic();
        ex.demonstrateUnary();
        ex.demonstrateRelational();
        ex.demonstrateLogical();
        ex.demonstrateAssignment();
        ex.demonstrateTernary();
        ex.demonstrateBitwise();
        ex.demonstrateShift();
        ex.demonstratePrecedence();
    }
}
