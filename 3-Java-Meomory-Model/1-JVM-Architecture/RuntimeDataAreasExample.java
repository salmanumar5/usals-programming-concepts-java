/**
 * RuntimeDataAreasExample.java
 *
 * This file explains JVM RUNTIME DATA AREAS in depth.
 *
 * Runtime Data Areas are memory regions created by the JVM
 * when a Java program starts.
 *
 * Covered in this file:
 * 1. What Runtime Data Areas are
 * 2. Method Area (Metaspace)
 * 3. Heap
 * 4. Java Stack (per thread)
 * 5. PC Register
 * 6. Native Method Stack
 * 7. How real Java code maps to these areas
 *
 * This file is intentionally rich in comments and explanations.
 * Treat it as JVM memory notes + runnable demo.
 */

public class RuntimeDataAreasExample {

    /*
     * ============================================================
     * 1. WHAT ARE RUNTIME DATA AREAS?
     * ============================================================
     *
     * When a Java program starts:
     * - JVM is created
     * - JVM allocates several memory areas
     * - These areas are used during program execution
     *
     * Some areas are:
     * - Shared across all threads
     * - Some are created per thread
     *
     * JVM Runtime Data Areas:
     *
     * 1. Method Area        (shared)
     * 2. Heap               (shared)
     * 3. Java Stack         (per thread)
     * 4. PC Register        (per thread)
     * 5. Native Method Stack (per thread)
     *
     * Understanding these areas is CRITICAL for:
     * - Performance tuning
     * - Debugging memory leaks
     * - Understanding GC
     * - StackOverflowError vs OutOfMemoryError
     */


    /*
     * ============================================================
     * 2. METHOD AREA (a.k.a METASPACE)
     * ============================================================
     *
     * Method Area stores:
     * - Class metadata
     * - Method bytecode
     * - Runtime constant pool
     * - Static variables
     *
     * Characteristics:
     * - Shared by ALL threads
     * - Created when JVM starts
     * - In modern JVMs → implemented as Metaspace (native memory)
     *
     * Example:
     * - Class name
     * - Method definitions
     * - static fields
     */

    static int staticCounter = 100; // stored in Method Area (class-level data)


    /*
     * ============================================================
     * 3. HEAP
     * ============================================================
     *
     * Heap stores:
     * - Objects
     * - Arrays
     *
     * Characteristics:
     * - Shared across all threads
     * - Managed by Garbage Collector
     * - Largest memory region in JVM
     *
     * Heap is typically divided into:
     * - Young Generation
     *     - Eden
     *     - Survivor (S0, S1)
     * - Old Generation
     *
     * Objects are usually created in Eden space.
     */

    static class Person {
        String name;
        int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }


    /*
     * ============================================================
     * 4. JAVA STACK (PER THREAD)
     * ============================================================
     *
     * Each thread has its OWN Java Stack.
     *
     * Stack stores:
     * - Stack frames
     * - Local variables
     * - Method parameters
     * - Intermediate calculations
     *
     * Stack frames are created:
     * - When a method is called
     * - Destroyed when method returns
     *
     * Stack is NOT shared between threads.
     *
     * Errors related to stack:
     * - StackOverflowError (deep recursion)
     */

    static void stackMethodA() {
        int a = 10; // stored in stack frame of stackMethodA
        stackMethodB(a);
    }

    static void stackMethodB(int value) {
        int b = value + 5; // stored in stack frame of stackMethodB
        System.out.println("Value in stackMethodB: " + b);
    }


    /*
     * ============================================================
     * 5. PC REGISTER (PROGRAM COUNTER)
     * ============================================================
     *
     * Each thread has its OWN PC Register.
     *
     * PC Register:
     * - Stores address of the current instruction being executed
     * - Helps JVM know where to resume execution after context switch
     *
     * You CANNOT access PC Register directly from Java code.
     * It is fully managed by JVM internally.
     *
     * Why it matters:
     * - Enables multithreading
     * - Each thread resumes from correct instruction
     */


    /*
     * ============================================================
     * 6. NATIVE METHOD STACK
     * ============================================================
     *
     * Native Method Stack is used when:
     * - Java calls native code (C/C++) via JNI
     *
     * Example native methods:
     * - Object.clone()
     * - Thread.start()
     * - System.arraycopy()
     *
     * Native stack stores:
     * - Native method call frames
     *
     * This stack is separate from Java Stack.
     */


    /*
     * ============================================================
     * 7. RUNTIME EXECUTION FLOW (REAL CODE MAPPING)
     * ============================================================
     *
     * We will now run code that touches:
     * - Method Area
     * - Heap
     * - Stack
     *
     * And explain where each part lives.
     */

    public static void main(String[] args) {

        /*
         * Method Area:
         * - RuntimeDataAreasExample class metadata
         * - staticCounter
         */

        System.out.println("=== Runtime Data Areas Demo ===");

        /*
         * Stack:
         * - main() stack frame created
         * - args reference stored in stack
         */

        int localValue = 42; // primitive → stored in stack
        System.out.println("Local value: " + localValue);

        /*
         * Heap:
         * - new Person(...) creates object in heap
         * - personRef stored in stack
         */

        Person personRef = new Person("Umar", 23);
        System.out.println("Person name: " + personRef.name);

        /*
         * Stack:
         * - method calls create new stack frames
         */

        stackMethodA();

        /*
         * Heap:
         * - Arrays are objects too
         */

        int[] numbers = new int[3];
        numbers[0] = 1;
        numbers[1] = 2;
        numbers[2] = 3;

        System.out.println("Array element: " + numbers[1]);

        /*
         * When main() ends:
         * - Stack frame is destroyed
         * - Objects in heap become eligible for GC (if no references)
         */
    }


    /*
     * ============================================================
     * 8. MEMORY DIAGRAM (MENTAL MODEL)
     * ============================================================
     *
     * When main() is running:
     *
     * STACK (Thread-1):
     * ┌─────────────────────────────┐
     * │ main() frame                 │
     * │  localValue = 42             │
     * │  personRef ───────────────┐ │
     * │  numbers   ─────────────┐ │ │
     * └─────────────────────────│─│─┘
     *                           │ │
     * HEAP                      │ │
     * ┌─────────────────────────▼─▼──────────┐
     * │ Person{name="Umar", age=23}           │
     * │ int[] {1,2,3}                          │
     * └──────────────────────────────────────┘
     *
     * METHOD AREA (Metaspace):
     * - RuntimeDataAreasExample.class
     * - Person.class
     * - staticCounter
     *
     * PC REGISTER:
     * - Points to current instruction per thread
     *
     * NATIVE STACK:
     * - Used when native methods are invoked
     */


    /*
     * ============================================================
     * 9. COMMON INTERVIEW / REAL-WORLD POINTS
     * ============================================================
     *
     * StackOverflowError:
     * - Too deep recursion
     * - Stack memory exhausted
     *
     * OutOfMemoryError:
     * - Heap or Metaspace exhausted
     * - Objects not freed / memory leak
     *
     * Static variables:
     * - Stored in Method Area
     * - Live as long as class is loaded
     *
     * Local variables:
     * - Stored in stack
     * - Destroyed when method returns
     *
     * Objects:
     * - Stored in heap
     * - GC-managed
     */


    /*
     * ============================================================
     * 10. FINAL SUMMARY (VERY IMPORTANT)
     * ============================================================
     *
     * - Method Area → class metadata, static fields
     * - Heap → objects & arrays (GC managed)
     * - Stack → method calls & local variables (per thread)
     * - PC Register → current instruction (per thread)
     * - Native Stack → native method execution
     *
     * If you deeply understand this file,
     * GC, JMM, JIT, and performance tuning will feel NATURAL.
     */
}
