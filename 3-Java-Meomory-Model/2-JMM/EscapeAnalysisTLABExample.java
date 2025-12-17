/**
 * EscapeAnalysisTLABExample.java
 *
 * This file explains ESCAPE ANALYSIS and TLAB (Thread Local Allocation Buffer).
 *
 * These are JIT-level optimizations that dramatically affect:
 * - Object allocation cost
 * - GC pressure
 * - Performance of real-world Java applications
 *
 * Very important mindset shift:
 * - "Objects are always on the heap" is LOGICALLY true,
 *   but PHYSICALLY the JVM can optimize allocations away.
 *
 * Covered in this file:
 * 1. What Escape Analysis is
 * 2. Types of escape (No Escape, Method Escape, Global Escape)
 * 3. Stack allocation & Scalar Replacement
 * 4. What TLAB is and why it exists
 * 5. How allocation works with and without TLAB
 * 6. Real code examples that trigger these optimizations
 * 7. JVM flags to observe behavior
 */

public class EscapeAnalysisTLABExample {

    /*
     * ============================================================
     * 1. WHAT IS ESCAPE ANALYSIS?
     * ============================================================
     *
     * Escape Analysis is a JIT compiler optimization.
     *
     * Goal:
     * - Determine whether an object "escapes" the scope
     *   in which it is created.
     *
     * If the object DOES NOT escape:
     * - JVM can:
     *     - Allocate it on stack (conceptually)
     *     - Or eliminate it entirely (scalar replacement)
     *
     * This reduces:
     * - Heap allocations
     * - GC overhead
     *
     * IMPORTANT:
     * - Escape Analysis happens at RUNTIME
     * - Done by JIT (C2 mostly)
     * - Not visible directly in Java source
     */


    /*
     * ============================================================
     * 2. TYPES OF ESCAPE
     * ============================================================
     *
     * 1. NO ESCAPE
     *    - Object is used only inside a method
     *    - Never returned or shared
     *
     * 2. METHOD ESCAPE
     *    - Object is returned from a method
     *
     * 3. GLOBAL ESCAPE
     *    - Object stored in static field
     *    - Or shared across threads
     *
     * Only NO ESCAPE objects are candidates for full optimization.
     */


    /*
     * ============================================================
     * 3. SIMPLE CLASS FOR DEMO
     * ============================================================
     */

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int sum() {
            return x + y;
        }
    }


    /*
     * ============================================================
     * 4. NO ESCAPE EXAMPLE
     * ============================================================
     *
     * This object:
     * - Is created
     * - Used
     * - Destroyed
     * all within the SAME method.
     *
     * JIT can:
     * - Allocate it on stack
     * - Or remove it completely (scalar replacement)
     */

    static int noEscapeExample() {
        Point p = new Point(10, 20);
        return p.sum();
    }


    /*
     * ============================================================
     * 5. METHOD ESCAPE EXAMPLE
     * ============================================================
     *
     * Object escapes the method by being returned.
     * JIT CANNOT fully eliminate allocation.
     */

    static Point methodEscapeExample() {
        Point p = new Point(5, 15);
        return p; // escapes method
    }


    /*
     * ============================================================
     * 6. GLOBAL ESCAPE EXAMPLE
     * ============================================================
     *
     * Object escapes globally by being stored in static field.
     * Worst case for optimization.
     */

    static Point globalPoint;

    static void globalEscapeExample() {
        globalPoint = new Point(1, 2); // global escape
    }


    /*
     * ============================================================
     * 7. SCALAR REPLACEMENT
     * ============================================================
     *
     * Scalar replacement:
     * - Object is broken into individual fields
     * - Fields stored in registers or stack
     * - NO object is actually created
     *
     * Example:
     * Point p = new Point(10, 20);
     * return p.x + p.y;
     *
     * JIT can transform into:
     * int x = 10;
     * int y = 20;
     * return x + y;
     *
     * No heap allocation, no GC.
     */


    /*
     * ============================================================
     * 8. WHAT IS TLAB?
     * ============================================================
     *
     * TLAB = Thread Local Allocation Buffer
     *
     * Purpose:
     * - Make heap allocation FAST in multithreaded programs
     *
     * Each thread gets:
     * - A small chunk of Eden space
     * - Allocates objects via pointer bumping
     *
     * Benefits:
     * - No synchronization needed for allocation
     * - Extremely fast object creation
     *
     * If object doesn't fit in TLAB:
     * - Thread requests a new TLAB
     * - Or allocates directly in shared Eden
     */


    /*
     * ============================================================
     * 9. TLAB vs NO TLAB (CONCEPTUAL)
     * ============================================================
     *
     * Without TLAB:
     * - Threads must synchronize to allocate in heap
     *
     * With TLAB:
     *
     * Thread-1 Eden:
     * ┌───────────────┐
     * │  TLAB-1       │
     * └───────────────┘
     *
     * Thread-2 Eden:
     * ┌───────────────┐
     * │  TLAB-2       │
     * └───────────────┘
     *
     * Allocation = pointer++
     */


    /*
     * ============================================================
     * 10. MAIN METHOD – TRIGGERING OPTIMIZATIONS
     * ============================================================
     */

    public static void main(String[] args) {

        System.out.println("=== Escape Analysis & TLAB Demo ===");

        long sum = 0;

        /*
         * Repeated calls make method hot.
         * JIT may apply:
         * - Escape Analysis
         * - Scalar Replacement
         * - Stack allocation
         */
        for (int i = 0; i < 50_000_000; i++) {
            sum += noEscapeExample();
        }

        System.out.println("Sum (no escape): " + sum);

        /*
         * Method escape:
         * Allocation likely happens on heap
         */
        Point p = methodEscapeExample();
        System.out.println("Method escape point sum: " + p.sum());

        /*
         * Global escape:
         * Worst for optimization
         */
        globalEscapeExample();
        System.out.println("Global escape point sum: " + globalPoint.sum());
    }


    /*
     * ============================================================
     * 11. JVM FLAGS TO OBSERVE BEHAVIOR (OPTIONAL)
     * ============================================================
     *
     * To see escape analysis decisions:
     *
     * -XX:+UnlockDiagnosticVMOptions
     * -XX:+PrintEscapeAnalysis
     * -XX:+PrintEliminateAllocations
     *
     * To disable escape analysis (for comparison):
     *
     * -XX:-DoEscapeAnalysis
     *
     * To observe TLAB behavior:
     *
     * -XX:+PrintTLAB
     *
     * Example:
     * java -XX:+UnlockDiagnosticVMOptions \
     *      -XX:+PrintEscapeAnalysis \
     *      -XX:+PrintEliminateAllocations \
     *      EscapeAnalysisTLABExample
     */


    /*
     * ============================================================
     * 12. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * ❌ "Objects are always allocated on heap"
     * ✔ JVM may eliminate allocation entirely
     *
     * ❌ "new keyword always means heap allocation"
     * ✔ new is a semantic construct, allocation is optimized
     *
     * ❌ "TLAB replaces heap"
     * ✔ TLAB is PART of heap (Eden)
     */


    /*
     * ============================================================
     * 13. FINAL SUMMARY (VERY IMPORTANT)
     * ============================================================
     *
     * - Escape Analysis determines object lifetime & visibility
     * - No-escape objects can be stack-allocated or eliminated
     * - Scalar replacement removes object allocation entirely
     * - TLAB enables fast, lock-free heap allocation
     * - These optimizations reduce GC pressure
     *
     * This is why modern Java can be EXTREMELY fast.
     * Understanding this separates average Java devs
     * from JVM-level engineers.
     */
}
