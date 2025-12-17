/**
 * MethodAreaExample.java
 *
 * This file explains the JVM METHOD AREA (a.k.a Metaspace in modern JVMs).
 *
 * The Method Area is one of the MOST misunderstood JVM memory regions.
 * It does NOT store objects — it stores CLASS-LEVEL information.
 *
 * Covered in this file:
 * 1. What the Method Area is
 * 2. What is stored inside Method Area
 * 3. Static variables vs instance variables
 * 4. Runtime Constant Pool
 * 5. Class metadata & bytecode
 * 6. Lifetime of Method Area data
 * 7. Common misconceptions & interview traps
 *
 * IMPORTANT:
 * - In Java 8+, Method Area is implemented as METASPACE
 * - Metaspace uses native memory (not heap)
 */

public class MethodAreaExample {

    /*
     * ============================================================
     * 1. WHAT IS THE METHOD AREA?
     * ============================================================
     *
     * Method Area is a JVM memory region that stores:
     * - Class metadata
     * - Method bytecode
     * - Static variables
     * - Runtime Constant Pool
     *
     * Characteristics:
     * - ONE per JVM (shared across all threads)
     * - Created when JVM starts
     * - Destroyed when JVM shuts down
     *
     * Method Area is NOT per object and NOT per thread.
     */


    /*
     * ============================================================
     * 2. STATIC VARIABLES (LIVE IN METHOD AREA)
     * ============================================================
     *
     * Static variables belong to the CLASS, not to instances.
     * Only ONE copy exists, shared across all objects.
     */

    static int staticCounter = 0; // stored in Method Area


    /*
     * ============================================================
     * 3. INSTANCE VARIABLES (DO NOT LIVE IN METHOD AREA)
     * ============================================================
     *
     * Instance variables live inside OBJECTS.
     * Objects live in the HEAP.
     */

    int instanceValue; // stored in HEAP (inside object)


    /*
     * ============================================================
     * 4. CONSTRUCTOR (BYTECODE STORED IN METHOD AREA)
     * ============================================================
     *
     * Method definitions (including constructors)
     * are stored in the Method Area as bytecode.
     */

    MethodAreaExample(int value) {
        this.instanceValue = value;
    }


    /*
     * ============================================================
     * 5. STATIC METHOD (BYTECODE STORED IN METHOD AREA)
     * ============================================================
     */

    static void incrementCounter() {
        staticCounter++;
    }


    /*
     * ============================================================
     * 6. INSTANCE METHOD (BYTECODE STORED IN METHOD AREA)
     * ============================================================
     */

    void printInstanceValue() {
        System.out.println("Instance value: " + instanceValue);
    }


    /*
     * ============================================================
     * 7. RUNTIME CONSTANT POOL
     * ============================================================
     *
     * Runtime Constant Pool is part of Method Area.
     *
     * It stores:
     * - String literals
     * - Numeric constants
     * - Symbolic references (class names, method names)
     *
     * Example:
     * String s = "Hello";
     * → "Hello" literal goes to the constant pool
     */

    static void constantPoolDemo() {

        String s1 = "Hello";        // String literal → constant pool
        String s2 = "Hello";        // points to SAME constant pool entry

        String s3 = new String("Hello"); // new object in HEAP

        System.out.println("s1 == s2 : " + (s1 == s2)); // true
        System.out.println("s1 == s3 : " + (s1 == s3)); // false
    }


    /*
     * ============================================================
     * 8. CLASS METADATA (WHAT JVM STORES ABOUT A CLASS)
     * ============================================================
     *
     * Method Area stores metadata like:
     * - Fully qualified class name
     * - Parent class
     * - Implemented interfaces
     * - Field names and types
     * - Method signatures
     * - Access modifiers
     *
     * JVM uses this metadata for:
     * - Method dispatch
     * - Reflection
     * - Verification
     */


    /*
     * ============================================================
     * 9. MAIN METHOD – REAL EXECUTION FLOW
     * ============================================================
     */

    public static void main(String[] args) {

        System.out.println("=== Method Area Demo ===");

        /*
         * Method Area:
         * - MethodAreaExample.class metadata
         * - staticCounter
         * - incrementCounter() bytecode
         */

        MethodAreaExample.incrementCounter();
        MethodAreaExample.incrementCounter();

        System.out.println("Static Counter: " + staticCounter);

        /*
         * Heap:
         * - Two objects created
         * - Each has its own instanceValue
         */

        MethodAreaExample obj1 = new MethodAreaExample(10);
        MethodAreaExample obj2 = new MethodAreaExample(20);

        obj1.printInstanceValue();
        obj2.printInstanceValue();

        /*
         * staticCounter is SHARED
         * instanceValue is PER OBJECT
         */

        constantPoolDemo();
    }


    /*
     * ============================================================
     * 10. MEMORY DIAGRAM (MENTAL MODEL)
     * ============================================================
     *
     * METHOD AREA (Metaspace):
     * ┌──────────────────────────────────────────┐
     * │ MethodAreaExample.class metadata          │
     * │ staticCounter                             │
     * │ incrementCounter() bytecode               │
     * │ printInstanceValue() bytecode             │
     * │ Runtime Constant Pool ("Hello")           │
     * └──────────────────────────────────────────┘
     *
     * HEAP:
     * ┌─────────────────────────────┐
     * │ obj1 → { instanceValue=10 } │
     * │ obj2 → { instanceValue=20 } │
     * └─────────────────────────────┘
     *
     * STACK:
     * ┌─────────────────────────────┐
     * │ main() frame                 │
     * │ obj1, obj2 references        │
     * └─────────────────────────────┘
     */


    /*
     * ============================================================
     * 11. COMMON MISCONCEPTIONS & INTERVIEW TRAPS
     * ============================================================
     *
     * ❌ "Static variables are stored in heap"
     * ✔ Static variables are stored in Method Area
     *
     * ❌ "Each object has its own copy of static fields"
     * ✔ ONE copy per class
     *
     * ❌ "Method Area is garbage collected like heap"
     * ✔ Metaspace is GC-managed but uses native memory
     *
     * ❌ "Method Area is per thread"
     * ✔ Method Area is shared across ALL threads
     */


    /*
     * ============================================================
     * 12. METASPACE VS PERMGEN (IMPORTANT HISTORY)
     * ============================================================
     *
     * Java 7 and earlier:
     * - Method Area implemented as PermGen (fixed size)
     *
     * Java 8+:
     * - PermGen removed
     * - Replaced by Metaspace
     * - Uses native memory
     * - Grows dynamically
     *
     * You can tune Metaspace using:
     * -XX:MetaspaceSize
     * -XX:MaxMetaspaceSize
     */


    /*
     * ============================================================
     * 13. FINAL SUMMARY (MEMORY ANCHORS)
     * ============================================================
     *
     * - Method Area stores CLASS-LEVEL information
     * - Static fields live in Method Area
     * - Method bytecode lives in Method Area
     * - Runtime Constant Pool is part of Method Area
     * - Objects NEVER live in Method Area
     * - One Method Area per JVM
     *
     * If you clearly understand Method Area,
     * reflection, class loading, GC roots, and JIT
     * will make much more sense.
     */
}
