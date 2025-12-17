/**
 * ExecutionEngineExample.java
 *
 * This file explains the JVM EXECUTION ENGINE in depth.
 *
 * The Execution Engine is the part of the JVM that:
 * - Takes bytecode (.class instructions)
 * - Executes them using Interpreter or JIT Compiler
 * - Cooperates with Garbage Collector
 *
 * Covered in this file:
 * 1. Where Execution Engine fits in JVM architecture
 * 2. Interpreter (how bytecode is executed line-by-line)
 * 3. JIT Compiler (why & how native code is generated)
 * 4. HotSpot JVM & profiling
 * 5. Tiered compilation (C1, C2)
 * 6. How execution actually happens at runtime
 * 7. A runnable demo to trigger JIT behavior
 *
 * NOTE:
 * - JIT behavior is adaptive and JVM-dependent
 * - You cannot "force" JIT directly, but you can design code that becomes hot
 */

public class ExecutionEngineExample {

    /*
     * ============================================================
     * 1. WHERE DOES EXECUTION ENGINE FIT?
     * ============================================================
     *
     * JVM Architecture (simplified):
     *
     *  +------------------------------+
     *  |        ClassLoader           |
     *  +------------------------------+
     *               |
     *               v
     *  +------------------------------+
     *  |     Runtime Data Areas       |
     *  |  (Heap, Stack, Method Area)  |
     *  +------------------------------+
     *               |
     *               v
     *  +------------------------------+
     *  |       Execution Engine       |
     *  |  - Interpreter               |
     *  |  - JIT Compiler              |
     *  |  - GC interaction            |
     *  +------------------------------+
     *
     * The Execution Engine is responsible for actually RUNNING your code.
     */


    /*
     * ============================================================
     * 2. INTERPRETER
     * ============================================================
     *
     * What the Interpreter does:
     * - Reads bytecode instruction-by-instruction
     * - Executes each instruction
     *
     * Pros:
     * - Very fast startup
     * - No compilation overhead initially
     *
     * Cons:
     * - Slower execution for frequently executed code
     *
     * Important:
     * - ALL Java code starts execution in the interpreter
     * - JVM profiles code during interpretation
     *
     * Example bytecode (conceptual):
     *   iload_1
     *   iload_2
     *   iadd
     *   istore_3
     *
     * Interpreter executes these one by one.
     */


    /*
     * ============================================================
     * 3. JIT (JUST-IN-TIME) COMPILER
     * ============================================================
     *
     * JIT exists to solve the interpreter performance problem.
     *
     * How it works:
     * - JVM observes which methods/loops run frequently ("hot")
     * - Hot code is compiled into native machine code
     * - Native code is stored in Code Cache
     * - Future executions jump directly to native code
     *
     * Result:
     * - Java can reach near C/C++ performance
     *
     * Important:
     * - JIT compilation happens DURING program execution
     * - Not ahead-of-time (like C++)
     */


    /*
     * ============================================================
     * 4. HOTSPOT JVM (WHY IT IS CALLED HOTSPOT)
     * ============================================================
     *
     * The JVM is called "HotSpot" because:
     * - It detects HOT SPOTS in code (frequently executed paths)
     * - Applies aggressive optimizations to those spots
     *
     * Examples of hot spots:
     * - Tight loops
     * - Frequently called methods
     *
     * JVM maintains counters:
     * - Method invocation count
     * - Loop back-edge count
     *
     * When threshold is crossed → JIT kicks in
     */


    /*
     * ============================================================
     * 5. TIERED COMPILATION (C1 & C2)
     * ============================================================
     *
     * Modern JVMs use TIERED COMPILATION:
     *
     * Tier 0: Interpreter
     * Tier 1: C1 Compiler (fast compilation, basic optimizations)
     * Tier 2: C2 Compiler (slow compilation, aggressive optimizations)
     *
     * Flow:
     * - Code starts in interpreter
     * - Gets compiled by C1 quickly
     * - If still hot → recompiled by C2
     *
     * C2 optimizations include:
     * - Method inlining
     * - Loop unrolling
     * - Escape analysis
     * - Dead code elimination
     */


    /*
     * ============================================================
     * 6. EXECUTION FLOW (STEP-BY-STEP)
     * ============================================================
     *
     * 1. main() bytecode loaded into Method Area
     * 2. Interpreter starts executing bytecode
     * 3. JVM profiles execution
     * 4. Hot method detected
     * 5. JIT compiles method to native code
     * 6. Execution switches to native code
     *
     * IMPORTANT:
     * - JVM can DEOPTIMIZE if assumptions break
     * - It may revert back to interpreter
     */


    /*
     * ============================================================
     * 7. RUNTIME DEMO: HOT METHOD
     * ============================================================
     *
     * The following method is intentionally simple and
     * called MANY times to make it "hot".
     */

    static long hotMethod(int n) {
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += i;
        }
        return sum;
    }


    /*
     * ============================================================
     * 8. MAIN METHOD
     * ============================================================
     */

    public static void main(String[] args) {

        System.out.println("=== Execution Engine Demo ===");

        /*
         * First few calls:
         * - Executed by Interpreter
         *
         * Later calls:
         * - Method becomes HOT
         * - JIT compiles it to native code
         */

        long result = 0;

        for (int i = 0; i < 50_000; i++) {
            result = hotMethod(10_000);
        }

        System.out.println("Result: " + result);

        /*
         * To OBSERVE JIT behavior (optional):
         *
         * Run with JVM flags:
         *   -XX:+PrintCompilation
         *   -XX:+UnlockDiagnosticVMOptions
         *   -XX:+PrintInlining
         *
         * Example:
         * java -XX:+PrintCompilation ExecutionEngineExample
         *
         * You will see lines indicating:
         * - Which method was compiled
         * - Which compiler (C1 / C2)
         */
    }


    /*
     * ============================================================
     * 9. GC & EXECUTION ENGINE INTERACTION
     * ============================================================
     *
     * Execution Engine cooperates with GC:
     * - Inserts GC safepoints
     * - Pauses threads during Stop-The-World
     * - Resumes execution after GC
     *
     * JIT-generated code includes:
     * - Write barriers
     * - Read barriers
     *
     * These help GC track object references correctly.
     */


    /*
     * ============================================================
     * 10. COMMON MISCONCEPTIONS
     * ============================================================
     *
     * ❌ "Java is slow because it is interpreted"
     * ✔ Java is adaptive: interpreted first, compiled later
     *
     * ❌ "JIT compiles everything"
     * ✔ Only hot code is compiled
     *
     * ❌ "Once compiled, code never changes"
     * ✔ JVM can deoptimize and recompile
     */


    /*
     * ============================================================
     * 11. FINAL SUMMARY (MEMORY ANCHORS)
     * ============================================================
     *
     * - Interpreter executes bytecode line-by-line
     * - JIT compiles hot code to native machine code
     * - HotSpot JVM profiles runtime behavior
     * - Tiered compilation balances startup & performance
     * - Execution Engine + GC work together
     *
     * Understanding Execution Engine explains:
     * - Why Java gets faster over time
     * - Why warm-up matters
     * - Why benchmarks need proper warm-up
     */
}
