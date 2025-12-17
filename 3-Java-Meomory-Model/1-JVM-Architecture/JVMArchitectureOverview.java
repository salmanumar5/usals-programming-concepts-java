/**
 * JVMArchitectureOverview.java
 *
 * A focused, beginner-friendly overview of JVM architecture, JRE vs JDK,
 * and the main subsystems inside the JVM: ClassLoader subsystem,
 * Runtime Data Areas, and the Execution Engine.
 *
 * This file contains:
 *  - Small runnable examples to inspect runtime environment and class loading
 *  - ASCII diagrams for the JVM architecture and runtime data areas
 *  - Detailed comments explaining each component and the execution flow
 *
 * Usage:
 *  - Compile: javac JVMArchitectureOverview.java
 *  - Run    : java JVMArchitectureOverview
 *
 * NOTE:
 *  - This file is an educational overview — not a substitute for reading
 *    the JVM specification, but it will give you a practical mental model.
 *
 * Contents overview (quick):
 *  1) JDK vs JRE vs JVM (what each provides)
 *  2) From .java to running program (compile -> bytecode -> classloader -> runtime)
 *  3) ClassLoader subsystem (bootstrap, extension, app, delegation)
 *  4) Runtime Data Areas (method area, heap, stacks, PC, native)
 *  5) Execution Engine (interpreter, JIT, garbage collector interaction)
 *  6) Small runnable examples: environment info and Class.forName demo
 *  7) Practical tips & summary
 */

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class JVMArchitectureOverview {

    /**
     * ============================
     * 1) JDK vs JRE vs JVM (short)
     * ============================
     *
     * JDK (Java Development Kit)
     *  - Full development kit delivered by vendors (Oracle, OpenJDK, etc.)
     *  - Contains javac (compiler), jar tools, javadoc, and a JRE for running code.
     *  - You install JDK to compile & run Java programs during development.
     *
     * JRE (Java Runtime Environment)
     *  - Contains the components required to run Java applications.
     *  - Includes JVM + standard class libraries (rt.jar or modules in modern Java).
     *  - You install JRE on machines where you only need to run Java apps (no compilation).
     *
     * JVM (Java Virtual Machine)
     *  - The actual runtime that loads class files (bytecode) and executes them.
     *  - JVM is an abstract specification; multiple implementations exist (HotSpot, OpenJ9).
     *  - Runs inside the JRE/JDK distribution. JVM provides:
     *       Class loading, bytecode verification, runtime data areas, execution engine, GC.
     *
     * Real short: JDK contains JRE; JRE contains JVM.
     *
     * Why this separation matters:
     *  - As a developer: you need JDK to compile; target machines need JRE to run.
     *  - JVM is where the program actually executes; understanding it helps debugging,
     *    tuning GC, and writing high-performance Java.
     */


    /**
     * ===========================================
     * 2) High-level flow: .java -> .class -> JVM
     * ===========================================
     *
     * 1. You write Java source (.java)
     * 2. Java compiler (javac) compiles to bytecode (.class)
     * 3. JVM loads .class files (ClassLoader subsystem)
     * 4. Bytecode verifier checks the code for security/sanity
     * 5. Execution Engine interprets or JIT-compiles bytecode
     * 6. Runtime Data Areas are used during execution (heap, method area, stacks...)
     *
     * ASCII Flow:
     *
     *   [Source: MyClass.java]  --javac-->  [Bytecode: MyClass.class]
     *                                         |
     *                                         v
     *   [Operating System & JRE] --> JVM starts and:
     *       -> ClassLoader (loads MyClass.class into Method Area / runtime metadata)
     *       -> Verifier (ensures bytecode constraints)
     *       -> Interpreter/JIT in Execution Engine (executes code)
     *       -> Uses Heap/Stack/MethodArea for memory
     *       -> GC reclaims unreachable Heap objects
     *
     *
     * Example concept: Class.forName("com.foo.MyClass")
     *  - Forces the ClassLoader to load the class (and initialize it)
     *  - Useful to demonstrate class loading at runtime (see runnable example below)
     */


    /**
     * =================================
     * 3) ClassLoader subsystem (concept)
     * =================================
     *
     * Main responsibilities:
     *  - Locate bytecode (from file system, module path, network, custom sources)
     *  - Convert bytecode into in-memory Class objects (metadata in method area)
     *  - Enforce parent-delegation model (Bootstrap -> Extension -> Application)
     *
     * Typical loaders (HotSpot example):
     *  - Bootstrap ClassLoader (native code) loads core JDK classes (java.*)
     *  - Platform/Extension ClassLoader loads platform libraries (jdk.* / ext)
     *  - Application (System) ClassLoader loads classes from classpath (user code)
     *
     * Parent delegation model:
     *  - A class loader first asks its parent to load a class.
     *  - If parent can't find it, the loader attempts to load it itself.
     *
     * Why delegation?
     *  - Security: ensures core classes are loaded by trusted bootstrap loader
     *  - Avoids duplicate copies of core classes loaded by separate loaders
     *
     * Note: You can implement custom ClassLoaders (e.g., for plugins, hot deploy).
     *       Custom loaders can break delegation intentionally, but do so carefully.
     */


    /**
     * =========================================
     * 4) Runtime Data Areas (per JVM process)
     * =========================================
     *
     * JVM runtime data areas (main ones):
     *
     *  - Method Area (a.k.a. Class Area)
     *      * Stores class metadata: class definitions, method bytecode, static variables,
     *        runtime constant pool, and method metadata.
     *      * In HotSpot this is part of the "Metaspace" (modern JVMs move it out of the
     *        old PermGen area).
     *
     *  - Heap
     *      * All objects (instances) and arrays are allocated here.
     *      * Garbage collected; divided into generations (Young/Old) and spaces
     *        (Eden, Survivor, Tenured) depending on GC algorithm.
     *
     *  - Java Stacks (per thread)
     *      * Each thread has its own stack containing stack frames (method calls).
     *      * Local variables, operand stack, return addresses live here.
     *      * If a method is recursive, each call gets a new frame.
     *
     *  - PC Register (per thread)
     *      * Holds address (pointer) of current instruction being executed for the thread.
     *
     *  - Native Method Stack / Native Memory
     *      * Used when Java calls native methods (JNI) or runtime uses C/C++ code.
     *
     * ASCII Memory picture (simplified):
     *
     *  +-----------------------------------------------------------+
     *  |                      METHOD AREA / METASPACE              |
     *  |  (class metadata, method bytecode, static fields, consts) |
     *  +-----------------------------------------------------------+
     *  |                           HEAP                            |
     *  |  (objects -> Person, String instances, arrays, etc.)      |
     *  +----------------------+----------------+-------------------+
     *  | Thread-1 Stack Frame | Thread-2 Stack |   PC Registers    |
     *  | (local vars, ops)    | (frames...)    |   (one per thread)|
     *  +-----------------------------------------------------------+
     *  |                    Native Method Area                     |
     *  +-----------------------------------------------------------+
     *
     * Important: The JVM implements synchronization, memory model (JMM),
     * and happens-before semantics on top of these data areas.
     */


    /**
     * ============================
     * 5) Execution Engine (concept)
     * ============================
     *
     * Components:
     *  - Interpreter
     *      * Reads bytecode and executes instructions one-by-one.
     *      * Low startup cost; slower for hot loops.
     *
     *  - Just-In-Time (JIT) Compiler
     *      * Monitors running code, identifies "hot" methods (frequently called),
     *        and compiles those bytecode sequences into native machine code.
     *      * Two-tier compilers in HotSpot: C1 (fast) and C2 (optimizing).
     *
     *  - Native Interface (JNI)
     *      * Allows invocation of native code (C/C++) from Java.
     *
     *  - Garbage Collector integration
     *      * Execution engine cooperates with GC to pause/resume threads for GC,
     *        apply barriers, and perform safepoint operations.
     *
     * Execution strategies:
     *  - Interpret first, profile execution, JIT compile hot code (adaptive optimization)
     *  - Use inlining, escape analysis, scalar replacement, and other optimizations
     *    to reduce allocation and method call overhead.
     *
     * Safety: Bytecode verifier runs before execution to guarantee certain properties
     * (type safety, stack depth correctness), reducing risk of bad native calls.
     */


    /**
     * ============================================
     * 6) Small runnable examples (inspect runtime)
     * ============================================
     *
     * Example 1: Show basic runtime information (JVM name, version, arguments)
     * Example 2: Demonstrate class loading with Class.forName() and a small custom
     *            nested class to show the initialization side effects.
     *
     * These examples are small and safe — they help you see some runtime details.
     */

    // A tiny nested class to demonstrate class loading/initialization side effects
    static class LoaderDemo {
        static {
            // This static block runs when the class is initialized (loaded + linked + initialized)
            System.out.println("LoaderDemo static initializer executed (class initialized).");
        }

        public static void sayHi() {
            System.out.println("Hello from LoaderDemo.sayHi()");
        }
    }

    public static void main(String[] args) {
        printJvmInfo();
        demonstrateClassLoading();
    }

    /**
     * Print JVM runtime info using standard APIs.
     */
    static void printJvmInfo() {
        RuntimeMXBean mx = ManagementFactory.getRuntimeMXBean();
        String spec = System.getProperty("java.specification.version");
        String vmName = System.getProperty("java.vm.name");
        String vmVendor = System.getProperty("java.vm.vendor");
        String vmVersion = System.getProperty("java.vm.version");

        System.out.println("\n=== JVM / JRE Info ===");
        System.out.println("Java VM Name    : " + vmName);
        System.out.println("Java VM Vendor  : " + vmVendor);
        System.out.println("Java VM Version : " + vmVersion);
        System.out.println("Java Spec Ver   : " + spec);
        System.out.println("JVM Arguments   : " + mx.getInputArguments());
        System.out.println("Class Path      : " + System.getProperty("java.class.path"));
        System.out.println("Available procs : " + Runtime.getRuntime().availableProcessors());
        System.out.println("Max memory (bytes): " + Runtime.getRuntime().maxMemory());
    }

    /**
     * Demonstrate class loading WITHOUT and WITH explicit initialization.
     *
     * Notes:
     *  - Class.forName("...") loads and initializes by default.
     *  - ClassLoader.loadClass("...") loads the class but does not initialize it
     *    until it is first actively used (depends on the exact call).
     *
     * We'll use the nested LoaderDemo class to show when static initializer executes.
     */
    static void demonstrateClassLoading() {
        System.out.println("\n=== Class Loading Demonstration ===");

        try {
            System.out.println("1) Using ClassLoader.loadClass() (no active init by this call):");

            // Use the system class loader to load the nested class by name
            ClassLoader system = ClassLoader.getSystemClassLoader();
            Class<?> c = system.loadClass(JVMArchitectureOverview.LoaderDemo.class.getName());

            System.out.println("  - Class object loaded: " + c.getName());
            System.out.println("  - But static initializer hasn't run yet (no initialization).");

            System.out.println("\n2) Forcing initialization using Class.forName():");
            // Class.forName will load AND initialize (runs static blocks)
            Class.forName(JVMArchitectureOverview.LoaderDemo.class.getName());

            System.out.println("\n3) Calling a method on LoaderDemo (class already initialized):");
            LoaderDemo.sayHi();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * ============================================
     * 7) Practical tips & common interview points
     * ============================================
     *
     * - Understand the differences between Stack and Heap: primitives/local refs on
     *   stack frames vs objects on heap.
     * - Know where static fields and method metadata live (Method Area / Metaspace).
     * - Remember ClassLoader parent delegation — prevents loading core classes from untrusted sources.
     * - JIT compiles hot code; interpreter runs everything first. Profiling drives optimization.
     * - GC types matter: some focus on throughput, some on low pause times. Tunable with flags.
     * - "Stop-the-world" pauses are due to safepoints; modern GCs minimize pause duration.
     * - Escape Analysis and scalar replacement: JIT can eliminate allocations of short-lived objects.
     * - TLAB (Thread Local Allocation Buffer): each thread gets a small bump-pointer area in Eden for fast allocation.
     *
     * Quick memory cheat:
     *  - Local variables & call frames → Thread stack
     *  - Objects & arrays → Heap
     *  - Class metadata & static fields → Method Area / Metaspace
     *
     * When you tune or debug:
     *  - Use -Xms/-Xmx for heap sizing
     *  - Use -XX:+UseG1GC or other flags to pick collectors (on modern JDKs G1 is default)
     *  - Use JFR / jmap / jstack / jstat / VisualVM / Java Mission Control for real investigations
     *
     * Closing summary:
     *  - JVM is a powerful runtime with layered subsystems.
     *  - Understanding where code lives and how it's executed helps write efficient Java,
     *    debug performance, and reason about GC behavior.
     */
}
