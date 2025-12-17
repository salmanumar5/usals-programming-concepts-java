/**
 * ThreadStackBehaviorExample.java
 *
 * This file explains how THREAD STACKS work in Java.
 *
 * This is crucial for understanding:
 * - Multithreading
 * - Method calls
 * - Recursion
 * - StackOverflowError
 * - Why threads do not interfere with each other's local variables
 *
 * Covered in this file:
 * 1. What a thread stack is
 * 2. How stack frames are created and destroyed
 * 3. Thread isolation of stacks
 * 4. Recursion and StackOverflowError
 * 5. How JVM switches between thread stacks
 */

public class ThreadStackBehaviorExample {

    /*
     * ============================================================
     * 1. WHAT IS A THREAD STACK?
     * ============================================================
     *
     * In Java:
     * - EACH THREAD has its OWN stack
     * - Stack stores method call frames
     *
     * Stack frame contains:
     * - Local variables
     * - Method parameters
     * - Operand stack
     * - Return address
     *
     * Thread stacks are:
     * - Created when thread starts
     * - Destroyed when thread ends
     *
     * VERY IMPORTANT:
     * - Stack memory is NOT shared between threads
     * - This is why local variables are thread-safe
     */


    /*
     * ============================================================
     * 2. SIMPLE METHOD CALL STACK DEMO
     * ============================================================
     */

    static void methodA() {
        int a = 10;           // stored in methodA stack frame
        methodB(a);
    }

    static void methodB(int value) {
        int b = value * 2;   // stored in methodB stack frame
        methodC(b);
    }

    static void methodC(int value) {
        int c = value + 5;   // stored in methodC stack frame
        System.out.println("Final value: " + c);
    }


    /*
     * ============================================================
     * 3. RECURSION AND STACKOVERFLOWERROR
     * ============================================================
     *
     * Recursion:
     * - Method calling itself
     * - Each call creates a NEW stack frame
     *
     * If recursion is too deep or infinite:
     * - Stack memory is exhausted
     * - JVM throws StackOverflowError
     */

    static void recursiveMethod(int depth) {
        System.out.println("Recursion depth: " + depth);
        recursiveMethod(depth + 1); // no base case → infinite recursion
    }


    /*
     * ============================================================
     * 4. THREAD STACK ISOLATION DEMO
     * ============================================================
     *
     * Two threads calling the SAME method:
     * - Each gets its OWN stack
     * - Local variables do NOT conflict
     */

    static void threadTask() {
        int counter = 0; // local variable → stack (per thread)

        for (int i = 0; i < 3; i++) {
            counter++;
            System.out.println(
                    Thread.currentThread().getName() +
                    " | counter = " + counter
            );

            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {}
        }
    }


    /*
     * ============================================================
     * 5. MAIN METHOD – THREAD EXECUTION
     * ============================================================
     */

    public static void main(String[] args) {

        System.out.println("=== Thread Stack Behavior Demo ===\n");

        /*
         * ----------------------------
         * Method Call Stack Example
         * ----------------------------
         */

        methodA();

        /*
         * Stack frames during methodA():
         *
         * TOP
         * ┌─────────────────────────┐
         * │ methodC(value)           │
         * │ methodB(value)           │
         * │ methodA()                │
         * │ main()                   │
         * └─────────────────────────┘
         * BOTTOM
         */

        /*
         * ----------------------------
         * Thread Stack Isolation
         * ----------------------------
         */

        Thread t1 = new Thread(ThreadStackBehaviorExample::threadTask, "Thread-1");
        Thread t2 = new Thread(ThreadStackBehaviorExample::threadTask, "Thread-2");

        t1.start();
        t2.start();

        /*
         * Output will interleave,
         * but each thread maintains its OWN stack.
         *
         * No synchronization is needed for 'counter'
         * because it lives in stack memory.
         */

        /*
         * ----------------------------
         * StackOverflowError Demo
         * ----------------------------
         *
         * Uncomment to see StackOverflowError
         * (DO NOT run unless you want to observe the error)
         */

        // recursiveMethod(1);
    }


    /*
     * ============================================================
     * 6. MEMORY DIAGRAM (VERY IMPORTANT)
     * ============================================================
     *
     * Two threads running threadTask():
     *
     * STACK (Thread-1):              STACK (Thread-2):
     * ┌───────────────────────┐     ┌───────────────────────┐
     * │ threadTask() frame     │     │ threadTask() frame     │
     * │  counter = 1 → 2 → 3   │     │  counter = 1 → 2 → 3   │
     * └───────────────────────┘     └───────────────────────┘
     *
     * HEAP (shared):
     * ┌─────────────────────────────┐
     * │ Thread objects               │
     * │ Class metadata               │
     * └─────────────────────────────┘
     *
     * Threads interleave execution,
     * but stacks NEVER overlap.
     */


    /*
     * ============================================================
     * 7. COMMON INTERVIEW & REAL-WORLD POINTS
     * ============================================================
     *
     * Why local variables are thread-safe:
     * - Stored in thread stack
     *
     * Why recursion is dangerous:
     * - Each call consumes stack memory
     *
     * Why StackOverflowError is not recoverable:
     * - Stack is exhausted; JVM cannot proceed safely
     *
     * Thread scheduling:
     * - JVM relies on OS scheduler
     * - PC Register stores current instruction per thread
     */


    /*
     * ============================================================
     * 8. FINAL SUMMARY (MEMORY ANCHORS)
     * ============================================================
     *
     * - Each thread has its OWN stack
     * - Stack frames are created per method call
     * - Local variables live in stack frames
     * - Recursion consumes stack memory
     * - Infinite recursion → StackOverflowError
     * - Stack isolation enables thread safety
     *
     * Understanding thread stacks is essential for:
     * - Multithreading correctness
     * - Debugging concurrency bugs
     * - Performance tuning
     */
}
