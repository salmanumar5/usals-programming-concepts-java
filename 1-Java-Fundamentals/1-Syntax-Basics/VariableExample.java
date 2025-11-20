/**
 * Demonstrates all important concepts related to variables in Java:
 * - Declaration & initialization
 * - Local, instance, and static variables
 * - Naming conventions
 * - Constants (final)
 * - Scope rules
 * - Variable shadowing
 * - Best practices comments
 */
public class VariablesExample {

    // --------------------------------------------------------
    // 1. Instance Variable
    // --------------------------------------------------------
    // Belongs to each object of VariablesExample.
    // Stored in HEAP inside the object.
    int instanceCount = 5;

    // --------------------------------------------------------
    // 2. Static Variable
    // --------------------------------------------------------
    // Shared by all objects of this class.
    // Stored in METHOD AREA / CLASS AREA of JVM.
    static int totalObjectsCreated = 0;

    // --------------------------------------------------------
    // 3. Constant Variable
    // --------------------------------------------------------
    // final variables cannot be changed after initialization.
    // By convention, constants are written in UPPER_SNAKE_CASE.
    static final double PI = 3.14159;


    // Constructor
    public VariablesExample() {
        totalObjectsCreated++;
    }


    // --------------------------------------------------------
    // 4. Local Variables Example
    // --------------------------------------------------------
    void demonstrateLocalVariables() {

        // Local variables exist inside methods, blocks, or constructors.
        // They must be initialized before use.

        int x = 10; // local variable
        int y;      // declaration only

        // y must be initialized before usage
        y = 20;

        System.out.println("Local x = " + x);
        System.out.println("Local y = " + y);
    }


    // --------------------------------------------------------
    // 5. Variable Shadowing Example
    // --------------------------------------------------------
    int value = 100; // instance variable

    void demonstrateShadowing() {
        int value = 50; // local variable shadows instance variable

        System.out.println("Local variable value = " + value);
        System.out.println("Instance variable value = " + this.value);
        // "this.value" forces usage of instance variable
    }


    // --------------------------------------------------------
    // 6. Scope Example
    // --------------------------------------------------------
    void demonstrateScope() {

        int outer = 10;

        if (outer > 5) {
            int inner = 20; // inner variable scope is limited to this block
            System.out.println("Inner: " + inner);
        }

        // System.out.println(inner); // ❌ ERROR: inner is out of scope
    }


    // --------------------------------------------------------
    // 7. Naming Conventions (Illustrated Through Comments)
    // --------------------------------------------------------
    /*
        Valid Examples:
          int studentCount;
          double averageSalary;
          boolean isActive;
          String customerName;

        Invalid Examples (don't use these):
          int 1stValue;           // ❌ cannot start with a number
          String new;             // ❌ 'new' is a reserved keyword
          double @salary;         // ❌ cannot contain special characters except _ and $
    */


    // --------------------------------------------------------
    // Main Method to run all examples
    // --------------------------------------------------------
    public static void main(String[] args) {

        VariablesExample ex = new VariablesExample();

        System.out.println("Total Objects Created: " + VariablesExample.totalObjectsCreated);

        System.out.println("\n--- Local Variables Example ---");
        ex.demonstrateLocalVariables();

        System.out.println("\n--- Shadowing Example ---");
        ex.demonstrateShadowing();

        System.out.println("\n--- Scope Example ---");
        ex.demonstrateScope();
    }
}
