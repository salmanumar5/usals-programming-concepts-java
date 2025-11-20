/**
 * This file explains the most basic structure of a Java program.
 * 
 * Concepts Covered:
 * - Why class name must match file name
 * - What 'public' means
 * - Why 'static' is used in main method
 * - Why main returns void
 * - Meaning of String[] args
 * - Program execution flow: JVM → class loader → main method
 */

public class SimpleCodeStructure {

    /**
     * MAIN METHOD: The entry point of every Java application.
     * Note: Read the Syntac Basics Readme to understand a bit context about Java, JVM, JRE and JDK
     * Breakdown of: public static void main(String[] args)
     *
     * 1. public
     *    - The method must be accessible to the JVM which resides outside this class.
     *    - JVM calls main(), so it must be public.
     *
     * 2. static
     *    - JVM calls main() without creating an object of this class.
     *    - static allows calling the method directly using the class name.
     *
     * 3. void
     *    - main() does not return anything to the JVM.
     *
     * 4. main
     *    - Method name fixed by Java specifications.
     *    - JVM looks for this exact signature to start the program.
     *
     * 5. String[] args
     *    - Used to take command-line arguments.
     *    - Example: java SimpleCodeStructure Hello World
     *      args[0] = "Hello", args[1] = "World"
     *
     * NOTE:
     * - The signature must be EXACTLY this for JVM to execute it:
     *     public static void main(String[] args)
     */
    public static void main(String[] args) {

        // Print a simple message to show program execution.
        System.out.println("Hello, Java! This is the basic program structure.");

        // Accessing command-line arguments (if provided)
        if (args.length > 0) {
            System.out.println("You passed " + args.length + " arguments:");

            for (String s : args) {
                System.out.println("Argument: " + s);
            }
        }

        // Call another method (basic example of flow)
        greet();
    }

    /**
     * A simple method to demonstrate calling another function from main().
     */
    private static void greet() {
        System.out.println("Greetings from another method!");
    }
}


/*
 ----------------------------------------------------------
 WHY CLASS NAME == FILE NAME?
 ----------------------------------------------------------

 In Java:
 - Each public class must be inside a file with the SAME NAME.
 - Example:
      public class SimpleCodeStructure { ... }
      File must be: SimpleCodeStructure.java

 If the names differ:
 - Compiler Error: "class SimpleCodeStructure is public, should be declared in a file named SimpleCodeStructure.java"

 Reason:
 - JVM loads classes by name using the ClassLoader.
 - Consistent naming ensures predictable loading and avoids ambiguity.


 ----------------------------------------------------------
 HIGH-LEVEL EXECUTION FLOW
 ----------------------------------------------------------

 1. You run: java SimpleCodeStructure
 2. JVM starts
 3. ClassLoader loads SimpleCodeStructure.class into memory
 4. JVM looks for:
        public static void main(String[] args)
 5. Execution begins from main()
 6. Program ends when main() finishes or System.exit() is called.


 ----------------------------------------------------------
 SUMMARY
 ----------------------------------------------------------

 - "public class" name must match the filename.
 - JVM begins execution from the main() method.
 - main() must be public, static, void.
 - args[] holds command-line inputs.
 - Understanding this structure is essential before learning any Java topic.
 */
