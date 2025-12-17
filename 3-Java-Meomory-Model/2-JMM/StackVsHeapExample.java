/**
 * StackVsHeapExample.java
 *
 * This file explains the difference between STACK and HEAP memory in Java.
 *
 * This is one of the MOST IMPORTANT JVM concepts because it directly impacts:
 * - Performance
 * - Garbage Collection
 * - Thread safety
 * - Memory leaks
 * - StackOverflowError vs OutOfMemoryError
 *
 * Covered in this file:
 * 1. What Stack and Heap are
 * 2. What goes into Stack
 * 3. What goes into Heap
 * 4. How references connect Stack → Heap
 * 5. Stack vs Heap lifetime
 * 6. Thread safety implications
 * 7. Real code mapping to memory
 * 8. Common errors and misconceptions
 */

public class StackVsHeapExample {

    /*
     * ============================================================
     * 1. STACK MEMORY (PER THREAD)
     * ============================================================
     *
     * Stack memory characteristics:
     * - Each thread has its OWN stack
     * - Stores:
     *     - Method call frames
     *     - Local variables
     *     - Method parameters
     *     - References to objects
     * - Memory allocation is FAST
     * - Memory is automatically freed when method returns
     *
     * Stack memory is:
     * - Small
     * - Fast
     * - NOT shared between threads
     *
     * Errors related to stack:
     * - StackOverflowError (deep or infinite recursion)
     */


    /*
     * ============================================================
     * 2. HEAP MEMORY (SHARED)
     * ============================================================
     *
     * Heap memory characteristics:
     * - Shared by ALL threads
     * - Stores:
     *     - Objects
     *     - Arrays
     * - Managed by Garbage Collector
     * - Slower than stack (but flexible)
     *
     * Heap memory is:
     * - Large
     * - GC-managed
     * - Shared (needs synchronization in multithreading)
     *
     * Errors related to heap:
     * - OutOfMemoryError
     */


    /*
     * ============================================================
     * 3. SIMPLE CLASS FOR DEMONSTRATION
     * ============================================================
     */

    static class User {
        String name;
        int age;

        User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }


    /*
     * ============================================================
     * 4. STACK METHOD EXAMPLE
     * ============================================================
     */

    static void stackMethod() {
        int localValue = 10;     // stored in STACK
        calculate(localValue);
    }

    static void calculate(int value) {
        int result = value * 2; // stored in STACK
        System.out.println("Result: " + result);
    }


    /*
     * ============================================================
     * 5. HEAP OBJECT EXAMPLE
     * ============================================================
     */

    static void heapMethod() {
        User user = new User("Umar", 23);
        // 'user' reference → STACK
        // User object      → HEAP

        System.out.println("User name: " + user.name);
    }


    /*
     * ============================================================
     * 6. MAIN METHOD – REAL EXECUTION FLOW
     * ============================================================
     */

    public static void main(String[] args) {

        System.out.println("=== Stack vs Heap Demo ===");

        /*
         * Stack:
         * - main() stack frame created
         * - args reference stored in stack
         */

        int x = 5;  // primitive → STACK
        System.out.println("x = " + x);

        /*
         * Heap:
         * - new User(...) creates object in HEAP
         * - reference stored in STACK
         */

        User u1 = new User("Alice", 30);
        User u2 = u1; // both references point to SAME heap object

        System.out.println("u1.name = " + u1.name);
        System.out.println("u2.name = " + u2.name);

        // Modify object using one reference
        u2.name = "Bob";

        System.out.println("After modification:");
        System.out.println("u1.name = " + u1.name);
        System.out.println("u2.name = " + u2.name);

        /*
         * Demonstrates:
         * - Stack stores references
         * - Heap stores actual objects
         */

        stackMethod();
        heapMethod();

        /*
         * When main() ends:
         * - Stack frame is destroyed
         * - Heap objects become eligible for GC (if no references exist)
         */
    }


    /*
     * ============================================================
     * 7. MEMORY DIAGRAM (MENTAL MODEL)
     * ============================================================
     *
     * During execution:
     *
     * STACK (Thread-1):
     * ┌─────────────────────────────┐
     * │ main() frame                 │
     * │  x = 5                       │
     * │  u1 ───────────────┐         │
     * │  u2 ─────────────┐ │         │
     * └──────────────────│─│─────────┘
     *                    │ │
     * HEAP               │ │
     * ┌──────────────────▼─▼──────────┐
     * │ User{name="Bob", age=30}       │
     * └───────────────────────────────┘
     *
     * STACK (Thread-1):
     * ┌─────────────────────────────┐
     * │ stackMethod() frame          │
     * │  localValue = 10             │
     * └─────────────────────────────┘
     *
     * Each method call adds a new frame.
     */


    /*
     * ============================================================
     * 8. STACK vs HEAP – THREAD SAFETY
     * ============================================================
     *
     * Stack:
     * - Each thread has its own stack
     * - Local variables are THREAD-SAFE by default
     *
     * Heap:
     * - Shared across threads
     * - Objects need synchronization if shared
     *
     * Example:
     * - local int x → thread-safe
     * - shared User object → NOT thread-safe without locking
     */


    /*
     * ============================================================
     * 9. COMMON ERRORS & INTERVIEW TRAPS
     * ============================================================
     *
     * StackOverflowError:
     * - Too deep recursion
     * - Example:
     *   void recurse() { recurse(); }
     *
     * OutOfMemoryError:
     * - Heap exhausted
     * - Caused by memory leaks or large allocations
     *
     * Misconception:
     * ❌ "Objects are stored in stack"
     * ✔ Objects are ALWAYS stored in heap
     *
     * Misconception:
     * ❌ "References are objects"
     * ✔ References are variables stored in stack
     */


    /*
     * ============================================================
     * 10. FINAL SUMMARY (VERY IMPORTANT)
     * ============================================================
     *
     * STACK:
     * - Stores method calls & local variables
     * - Fast
     * - Per thread
     * - Auto cleanup
     *
     * HEAP:
     * - Stores objects & arrays
     * - GC-managed
     * - Shared
     * - Needs synchronization
     *
     * If you understand Stack vs Heap clearly,
     * - GC
     * - JMM
     * - Multithreading
     * - Performance tuning
     * all become MUCH easier.
     */
}
