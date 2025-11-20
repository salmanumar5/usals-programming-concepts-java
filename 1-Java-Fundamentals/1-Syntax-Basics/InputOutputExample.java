import java.util.Scanner;

/**
 * Demonstrates different ways of performing Input and Output in Java:
 *
 * OUTPUT:
 *  - System.out.print()
 *  - System.out.println()
 *  - System.out.printf()
 *  - Escape sequences
 *
 * INPUT:
 *  - Using Scanner class
 *  - Reading strings, integers, doubles, booleans
 *  - Handling nextLine() after numeric input (common pitfall)
 *
 * BEST PRACTICES:
 *  - Scanner should be closed only at end of program
 *  - Use printf for formatted output
 */

public class InputOutputExample {

    // ------------------------------------------------------------
    // 1. Output methods: print, println, printf
    // ------------------------------------------------------------
    public void demonstrateOutput() {

        System.out.println("=== Output Methods ===");

        // println → prints + new line
        System.out.println("Hello, Java!");

        // print → prints without a new line
        System.out.print("This is printed on one line... ");
        System.out.print("and continues here.\n");

        // printf → formatted printing
        String name = "Umar";
        int age = 23;
        System.out.printf("Name: %s | Age: %d%n", name, age);

        // Escape sequences
        System.out.println("Line1\nLine2");          // new line
        System.out.println("Column1\tColumn2");      // tab
        System.out.println("Using quotes: \"Java\""); // double quote
        System.out.println("Backslash: \\");          // backslash
    }


    // ------------------------------------------------------------
    // 2. Input using Scanner
    // ------------------------------------------------------------
    public void demonstrateInput() {

        System.out.println("\n=== Input Methods Using Scanner ===");

        // Scanner reads input from keyboard (System.in)
        Scanner sc = new Scanner(System.in);

        // ----- Reading String -----
        System.out.print("Enter your name: ");
        String name = sc.nextLine(); // reads entire line including spaces

        // ----- Reading integer -----
        System.out.print("Enter your age: ");
        int age = sc.nextInt();

        // ----- Reading double -----
        System.out.print("Enter your height (in meters): ");
        double height = sc.nextDouble();

        // ----- Reading boolean -----
        System.out.print("Are you a student (true/false)? ");
        boolean isStudent = sc.nextBoolean();

        // Important Pitfall:
        // If you call nextLine() AFTER nextInt() or nextDouble(),
        // the leftover newline will be consumed unexpectedly.
        // Demonstration:
        sc.nextLine(); // consume the leftover newline

        System.out.print("Enter your city: ");
        String city = sc.nextLine(); // now works correctly

        System.out.println("\n=== You Entered ===");
        System.out.println("Name      : " + name);
        System.out.println("Age       : " + age);
        System.out.println("Height    : " + height);
        System.out.println("Student   : " + isStudent);
        System.out.println("City      : " + city);

        // Best practice: Close Scanner at END of program only
        // sc.close();  // Not closing because this is demonstration code
    }


    // ------------------------------------------------------------
    // MAIN Method
    // ------------------------------------------------------------
    public static void main(String[] args) {
        InputOutputExample ex = new InputOutputExample();

        ex.demonstrateOutput();

        // Uncomment to test input (avoid running auto in some environments)
        ex.demonstrateInput();
    }
}
