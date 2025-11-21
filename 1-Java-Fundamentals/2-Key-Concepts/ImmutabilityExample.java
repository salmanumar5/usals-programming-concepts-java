/**
 * Demonstrates the concept of IMMUTABILITY in Java:
 *
 * 1. What is immutability?
 *    - An object whose state cannot be changed after creation.
 *
 * 2. Immutable built-in types:
 *    - String
 *    - Wrapper classes (Integer, Double, etc.)
 *    - BigInteger, BigDecimal
 *
 * 3. Why immutability is used?
 *    - Thread-safety
 *    - Security
 *    - Caching / performance (String pool)
 *    - Predictable behavior
 *
 * 4. THIS FILE SHOWS:
 *    - String immutability
 *    - Wrapper immutability
 *    - How to create a custom immutable class
 *    - Defensive copying for mutable fields
 */

public class ImmutabilityExample {

    // ------------------------------------------------------------
    // 1. STRING IMMUTABILITY
    // ------------------------------------------------------------
    public void demonstrateStringImmutability() {
        System.out.println("=== String Immutability ===");

        String s1 = "Hello";
        String s2 = s1;

        s1 = s1 + " World"; // creates NEW String object

        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2); // unchanged

        /*
            Explanation:
            s1 originally -> "Hello"
            After s1 = s1 + " World";
            s1 -> "Hello World" (a new object)
            s2 still -> "Hello"
        */
    }


    // ------------------------------------------------------------
    // 2. WRAPPER IMMUTABILITY
    // ------------------------------------------------------------
    public void demonstrateWrapperImmutability() {
        System.out.println("\n=== Wrapper Classes are Immutable ===");

        Integer x = 10;
        Integer y = x;

        x = x + 5; // creates a NEW Integer object

        System.out.println("x = " + x);
        System.out.println("y = " + y); // still 10

        /*
            Integer, Double, Boolean, etc., are all immutable.
            Any operation creates a NEW object.
        */
    }


    // ------------------------------------------------------------
    // 3. CUSTOM IMMUTABLE CLASS EXAMPLE
    // ------------------------------------------------------------
    static final class Person {

        private final String name;
        private final int age;

        // Constructor initializes fields ONCE
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        // Getter methods only (no setters)
        public String getName() { return name; }
        public int getAge() { return age; }

        // No method modifies state -> IMMUTABLE CLASS
    }


    public void demonstrateCustomImmutableClass() {
        System.out.println("\n=== Custom Immutable Class ===");

        Person p1 = new Person("Umar", 23);
        System.out.println("Name: " + p1.getName());
        System.out.println("Age: " + p1.getAge());

        // p1.setName(...) DOES NOT exist
        // p1.age = 30 → impossible, variables are final
    }


    // ------------------------------------------------------------
    // 4. IMMUTABLE CLASS WITH DEFENSIVE COPYING
    // ------------------------------------------------------------
    static final class Student {

        private final String name;

        // MUTABLE FIELD (dangerous!)
        private final int[] scores;

        public Student(String name, int[] scores) {
            this.name = name;

            // Defensive copy → prevents outside modification
            this.scores = scores.clone();
        }

        public String getName() { return name; }

        public int[] getScores() {
            return scores.clone(); // return copy, not original
        }
    }


    public void demonstrateDefensiveCopying() {
        System.out.println("\n=== Defensive Copying Example ===");

        int[] marks = {90, 80, 70};
        Student s = new Student("Ali", marks);

        // Trying to modify external array
        marks[0] = 0;

        System.out.println("Student scores (still safe):");
        for (int score : s.getScores()) {
            System.out.println(score);
        }
    }


    // ------------------------------------------------------------
    // MAIN METHOD
    // ------------------------------------------------------------
    public static void main(String[] args) {
        ImmutabilityExample ex = new ImmutabilityExample();

        ex.demonstrateStringImmutability();
        ex.demonstrateWrapperImmutability();
        ex.demonstrateCustomImmutableClass();
        ex.demonstrateDefensiveCopying();
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF IMMUTABILITY
====================================================================================

1. WHAT IS AN IMMUTABLE OBJECT?
-------------------------------
It is an object whose internal state (its fields) **cannot change** after creation.

Once created → forever fixed.

Example:
String s = "Hello";
s.concat("World"); // returns NEW String, original unchanged


2. WHY ARE STRINGS IMMUTABLE?
-----------------------------
- Security: important for URLs, class names, database credentials.
- Thread-safety: multiple threads can share the same string safely.
- String Pool: saves memory by reusing string objects.


3. WHY WRAPPER CLASSES ARE IMMUTABLE?
-------------------------------------
Integer, Double, Boolean, etc. behave like primitives and must be predictable.
So they cannot be changed.


4. HOW TO CREATE A CUSTOM IMMUTABLE CLASS?
------------------------------------------
You must ensure three rules:
1. Class is marked final (cannot be extended)
2. Fields are private + final
3. No setters
4. Defensive copies for mutable fields


5. WHY DEFENSIVE COPYING?
-------------------------
If the object contains a mutable field like an array:

- If you store reference directly → external code can modify it.
- So you clone it in constructor and in getter.


6. SUMMARY IN SIMPLE WORDS:
---------------------------
Immutable = cannot change once created.
Mutable = can change anytime.

Immutable objects are safer, easier to think about, and thread-safe.
====================================================================================
*/
