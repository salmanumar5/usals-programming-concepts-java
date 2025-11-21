/**
 * Demonstrates everything important about:
 * 1. String (Immutable)
 * 2. StringBuilder (Mutable, not thread-safe)
 * 3. StringBuffer (Mutable, thread-safe)
 *
 * This file covers:
 * - String immutability
 * - String Pool vs Heap
 * - == vs equals()
 * - Common String operations
 * - Why StringBuilder is faster
 * - Why StringBuffer exists
 */

public class StringExamples {

    // ------------------------------------------------------------
    // 1. String Immutability & String Pool
    // ------------------------------------------------------------
    public void demonstrateStringBasics() {
        System.out.println("=== String Basics and Immutability ===");

        String s1 = "Java";   // goes into String Pool
        String s2 = "Java";   // reused from pool (same object)
        String s3 = new String("Java"); // new object in heap

        System.out.println("s1 == s2: " + (s1 == s2)); // true → same pool object
        System.out.println("s1 == s3: " + (s1 == s3)); // false → different objects
        System.out.println("s1.equals(s3): " + s1.equals(s3)); // true → content same

        /*
            "Java" literals go into the STRING POOL.
            new String("Java") creates a SEPARATE object in heap.
        */

        // Immutability demonstration
        s1 = s1 + " Programming";  // NEW object created
        System.out.println("s1 after change: " + s1);
    }


    // ------------------------------------------------------------
    // 2. Common Useful String Operations
    // ------------------------------------------------------------
    public void demonstrateStringMethods() {
        System.out.println("\n=== Common String Methods ===");

        String s = "  Hello Java World  ";

        System.out.println("Length: " + s.length());
        System.out.println("Uppercase: " + s.toUpperCase());
        System.out.println("Lowercase: " + s.toLowerCase());
        System.out.println("Trim: '" + s.trim() + "'");
        System.out.println("Substring(2, 7): " + s.substring(2, 7));
        System.out.println("Contains 'Java': " + s.contains("Java"));
        System.out.println("Replace 'Java' -> 'Python': " + s.replace("Java", "Python"));
        System.out.println("Index of 'Java': " + s.indexOf("Java"));

        // Split example
        String[] parts = s.trim().split(" ");
        System.out.println("Split words:");
        for (String word : parts) {
            System.out.println(word);
        }
    }


    // ------------------------------------------------------------
    // 3. StringBuilder (Mutable, Faster)
    // ------------------------------------------------------------
    public void demonstrateStringBuilder() {
        System.out.println("\n=== StringBuilder (Mutable) ===");

        StringBuilder sb = new StringBuilder("Hello");

        sb.append(" World");
        sb.append("!");
        sb.insert(5, " Java");
        sb.delete(5, 10);

        System.out.println("StringBuilder result: " + sb.toString());
    }


    // ------------------------------------------------------------
    // 4. StringBuffer (Thread-safe version of StringBuilder)
    // ------------------------------------------------------------
    public void demonstrateStringBuffer() {
        System.out.println("\n=== StringBuffer (Thread-safe) ===");

        StringBuffer sb = new StringBuffer("Hello");

        sb.append(" World");
        sb.append("!");
        sb.insert(5, " Java");
        sb.delete(5, 10);

        System.out.println("StringBuffer result: " + sb.toString());
    }


    // ------------------------------------------------------------
    // 5. Performance: String vs StringBuilder
    // ------------------------------------------------------------
    public void demonstratePerformance() {
        System.out.println("\n=== Performance Comparison ===");

        // Very slow: String in loop (because immutable)
        String slow = "";
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            slow += "a";  // creates new object every time
        }
        long end1 = System.currentTimeMillis();
        System.out.println("String time: " + (end1 - start1) + " ms");

        // Fast: StringBuilder in loop
        StringBuilder fast = new StringBuilder();
        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            fast.append("a");
        }
        long end2 = System.currentTimeMillis();
        System.out.println("StringBuilder time: " + (end2 - start2) + " ms");

        /*
            StringBuilder is MUCH faster because:
            - It modifies the existing buffer
            - Does not create new objects each time
        */
    }


    // ------------------------------------------------------------
    // MAIN
    // ------------------------------------------------------------
    public static void main(String[] args) {

        StringExamples ex = new StringExamples();

        ex.demonstrateStringBasics();
        ex.demonstrateStringMethods();
        ex.demonstrateStringBuilder();
        ex.demonstrateStringBuffer();
        ex.demonstratePerformance();
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF STRING, STRINGBUILDER, STRINGBUFFER
====================================================================================

1. WHY STRING IS IMMUTABLE?
---------------------------
- Stored in STRING POOL to save memory.
- Immutable → safe for threads.
- Immutable → can be cached and reused.

Every modification creates a NEW object.


2. STRING POOL vs HEAP
-----------------------
String s1 = "Java";  
→ Goes to STRING POOL.

String s2 = "Java";  
→ Reuses same object from pool.

String s3 = new String("Java");  
→ Always creates a NEW object in HEAP.


3. == VS equals()
------------------
== compares reference (address)
equals() compares content.

Example:
"Java" == new String("Java") → false  
"Java".equals(new String("Java")) → true


4. STRINGBUILDER
----------------
- Mutable
- Fast
- Not thread-safe
- BEST for most use cases where string modification is needed.

Example:
StringBuilder sb = new StringBuilder();
sb.append("Hello");


5. STRINGBUFFER
---------------
- Similar to StringBuilder
- BUT methods are synchronized → thread-safe
- Slower
- Rarely needed unless working with shared threads.


6. WHEN TO USE WHAT?
---------------------
String → when immutability or fixed text  
StringBuilder → for heavy text modification  
StringBuffer → when thread safety is required


7. PERFORMANCE SUMMARY
----------------------
Slowest: String in loops  
Fastest: StringBuilder


====================================================================================
*/
