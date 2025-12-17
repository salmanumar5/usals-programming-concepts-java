/**
 * ClassLoaderSubsystemExample.java
 *
 * This file explains the CLASS LOADER SUBSYSTEM in the JVM in depth.
 *
 * The goal of this file:
 * - Build an intuitive mental model of how classes are loaded
 * - Explain WHY class loaders exist
 * - Explain HOW parent delegation works
 * - Show WHAT happens at runtime using small runnable examples
 *
 * Topics covered:
 * 1. Where ClassLoader fits in JVM / JRE / JDK
 * 2. Why class loading is needed
 * 3. Types of ClassLoaders (Bootstrap, Platform, Application)
 * 4. Parent Delegation Model
 * 5. Class loading phases (Load → Link → Initialize)
 * 6. Custom ClassLoader (conceptual example)
 * 7. Runtime inspection of class loaders
 *
 * This file is intentionally comment-heavy to serve as reference material.
 */

public class ClassLoaderSubsystemExample {

    /*
     * ============================================================
     * 1. CONTEXT: JVM vs JRE vs JDK (Quick Recap)
     * ============================================================
     *
     * JVM (Java Virtual Machine)
     * - Executes Java bytecode
     * - Provides memory areas, GC, execution engine
     * - Includes the ClassLoader Subsystem
     *
     * JRE (Java Runtime Environment)
     * - JVM + standard Java class libraries
     * - Needed to RUN Java programs
     *
     * JDK (Java Development Kit)
     * - JRE + development tools (javac, javadoc, etc.)
     * - Needed to DEVELOP Java programs
     *
     * IMPORTANT:
     * The ClassLoader subsystem is PART OF THE JVM.
     * Its job is to load .class files into memory at runtime.
     */


    /*
     * ============================================================
     * 2. WHY DO WE NEED A CLASS LOADER?
     * ============================================================
     *
     * Java programs are NOT fully loaded at startup.
     *
     * Instead:
     * - Classes are loaded ON DEMAND
     * - Only when they are first referenced
     *
     * Benefits:
     * - Faster startup
     * - Lower memory usage
     * - Dynamic loading (plugins, reflection, frameworks)
     *
     * Example:
     * If your program has 100 classes but only uses 10,
     * JVM will load only those 10.
     *
     * ClassLoader responsibilities:
     * - Locate bytecode (.class)
     * - Load it into JVM memory
     * - Create Class<?> objects in Method Area / Metaspace
     */


    /*
     * ============================================================
     * 3. TYPES OF CLASS LOADERS IN JVM
     * ============================================================
     *
     * There are three main built-in class loaders:
     *
     * 1. Bootstrap ClassLoader
     *    - Loads core Java classes (java.lang.*, java.util.*, etc.)
     *    - Written in native code (C/C++)
     *    - Not a Java object (returns null in Java)
     *
     * 2. Platform ClassLoader (Extension in older Java)
     *    - Loads platform-level modules (jdk.*)
     *    - Sits between Bootstrap and Application loader
     *
     * 3. Application (System) ClassLoader
     *    - Loads classes from classpath (your application code)
     *    - This is what loads YOUR classes
     *
     * ClassLoader hierarchy (conceptual):
     *
     *   Bootstrap ClassLoader
     *          ↑
     *   Platform ClassLoader
     *          ↑
     *   Application ClassLoader
     */


    /*
     * ============================================================
     * 4. PARENT DELEGATION MODEL (VERY IMPORTANT)
     * ============================================================
     *
     * When a class needs to be loaded:
     *
     * 1. Application ClassLoader asks its PARENT
     * 2. Platform ClassLoader asks its PARENT
     * 3. Bootstrap ClassLoader tries to load the class
     *
     * If parent CAN load it → child does NOT load it
     * If parent CANNOT load it → child tries
     *
     * Why this exists:
     * - Security: prevents overriding core Java classes
     * - Consistency: avoids duplicate class definitions
     *
     * Example:
     * You create a class named java.lang.String
     * JVM will IGNORE it and load the real String class
     * from Bootstrap ClassLoader.
     */


    /*
     * ============================================================
     * 5. CLASS LOADING PHASES
     * ============================================================
     *
     * Class loading happens in 3 main phases:
     *
     * 1. LOADING
     *    - Bytecode (.class) is read
     *    - Class object is created
     *
     * 2. LINKING
     *    - Verification: bytecode safety checks
     *    - Preparation: static fields get default values
     *    - Resolution: symbolic references resolved
     *
     * 3. INITIALIZATION
     *    - Static variables assigned actual values
     *    - Static blocks executed
     *
     * Important:
     * - Loading does NOT mean initialization
     * - Initialization happens only when class is actively used
     */


    /*
     * ============================================================
     * 6. SMALL DEMO CLASS FOR LOADING BEHAVIOR
     * ============================================================
     */

    static class DemoClass {
        static {
            System.out.println("DemoClass static block executed (INITIALIZATION)");
        }

        static void sayHello() {
            System.out.println("Hello from DemoClass");
        }
    }


    /*
     * ============================================================
     * 7. RUNTIME DEMONSTRATION
     * ============================================================
     */

    public static void main(String[] args) throws Exception {

        System.out.println("=== ClassLoader Subsystem Demo ===\n");

        // --------------------------------------------------------
        // Which class loader loaded THIS class?
        // --------------------------------------------------------
        ClassLoader appLoader = ClassLoaderSubsystemExample.class.getClassLoader();
        System.out.println("ClassLoaderSubsystemExample loaded by: " + appLoader);

        // Parent of Application ClassLoader
        System.out.println("Parent (Platform ClassLoader): " + appLoader.getParent());

        // Parent of Platform ClassLoader (Bootstrap)
        System.out.println("Bootstrap ClassLoader: " + appLoader.getParent().getParent());

        /*
         * Bootstrap ClassLoader prints null
         * because it is implemented in native code,
         * not as a Java ClassLoader object.
         */


        // --------------------------------------------------------
        // Class loaded by Bootstrap ClassLoader
        // --------------------------------------------------------
        ClassLoader stringLoader = String.class.getClassLoader();
        System.out.println("\nString class loaded by: " + stringLoader);

        /*
         * String is part of core Java
         * → loaded by Bootstrap ClassLoader
         * → returns null
         */


        // --------------------------------------------------------
        // Demonstrating loadClass vs Class.forName
        // --------------------------------------------------------
        System.out.println("\n--- loadClass() (NO initialization) ---");

        ClassLoader loader = ClassLoader.getSystemClassLoader();
        Class<?> cls = loader.loadClass(
                ClassLoaderSubsystemExample.DemoClass.class.getName()
        );

        System.out.println("DemoClass loaded, but static block NOT executed yet");

        System.out.println("\n--- Class.forName() (WITH initialization) ---");

        Class.forName(ClassLoaderSubsystemExample.DemoClass.class.getName());

        System.out.println("\n--- Using class method (already initialized) ---");
        DemoClass.sayHello();
    }


    /*
     * ============================================================
     * 8. CUSTOM CLASS LOADER (CONCEPT)
     * ============================================================
     *
     * Java allows you to create your own ClassLoader by extending ClassLoader.
     *
     * Why?
     * - Plugin systems
     * - Application servers
     * - Hot reloading
     * - Isolated class loading
     *
     * Skeleton:
     *
     * class MyClassLoader extends ClassLoader {
     *     @Override
     *     protected Class<?> findClass(String name) {
     *         byte[] bytes = loadBytecodeSomehow(name);
     *         return defineClass(name, bytes, 0, bytes.length);
     *     }
     * }
     *
     * WARNING:
     * - Breaking parent delegation can cause:
     *   ClassCastException
     *   LinkageError
     *   Security issues
     *
     * Frameworks like:
     * - Tomcat
     * - Spring Boot
     * - OSGi
     * use custom class loaders internally.
     */


    /*
     * ============================================================
     * 9. KEY TAKEAWAYS (MEMORY ANCHORS)
     * ============================================================
     *
     * - ClassLoader subsystem is part of JVM
     * - Classes are loaded lazily (on demand)
     * - Parent delegation ensures security
     * - Bootstrap loader loads core Java classes
     * - loadClass() ≠ initialization
     * - Class.forName() triggers initialization
     * - Class metadata lives in Method Area / Metaspace
     *
     * If you understand this file deeply,
     * frameworks, reflection, GC roots, and JIT behavior
     * will make MUCH more sense later.
     */
}
