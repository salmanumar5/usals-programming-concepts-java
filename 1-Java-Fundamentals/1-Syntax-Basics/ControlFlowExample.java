/**
 * Demonstrates all Java control flow constructs:
 *
 * 1. if, else-if, else
 * 2. Nested if
 * 3. switch (old + enhanced switch)
 * 4. for loop
 * 5. enhanced for loop
 * 6. while loop
 * 7. do-while loop
 * 8. break and continue
 * 9. Labeled loops (important for nested loops)
 *
 * This file is designed for beginners and intermediate learners
 * to understand HOW Java makes decisions and repeats tasks.
 */

public class ControlFlowExample {

    // ------------------------------------------------------------
    // 1. if, else-if, else
    // ------------------------------------------------------------
    public void demonstrateIfElse() {
        System.out.println("=== if / else-if / else ===");

        int marks = 85;

        if (marks >= 90) {
            System.out.println("Grade: A");
        } else if (marks >= 75) {
            System.out.println("Grade: B");
        } else if (marks >= 60) {
            System.out.println("Grade: C");
        } else {
            System.out.println("Grade: D");
        }
    }


    // ------------------------------------------------------------
    // 2. Nested if
    // ------------------------------------------------------------
    public void demonstrateNestedIf() {
        System.out.println("\n=== Nested if ===");

        int age = 20;
        boolean hasID = true;

        if (age >= 18) {
            if (hasID) {
                System.out.println("Entry allowed");
            } else {
                System.out.println("ID required");
            }
        } else {
            System.out.println("Under 18 - entry denied");
        }
    }


    // ------------------------------------------------------------
    // 3. switch statement (old and enhanced switch)
    // ------------------------------------------------------------
    public void demonstrateSwitch() {
        System.out.println("\n=== switch (old style) ===");

        int day = 3;
        switch (day) {
            case 1: System.out.println("Monday"); break;
            case 2: System.out.println("Tuesday"); break;
            case 3: System.out.println("Wednesday"); break;
            case 4: System.out.println("Thursday"); break;
            default: System.out.println("Other day");
        }

        // Enhanced switch (Java 14+)
        System.out.println("\n=== Enhanced switch (Java 14+) ===");

        int month = 2;

        String result = switch (month) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            default -> "Unknown month";
        };

        System.out.println("Month: " + result);
    }


    // ------------------------------------------------------------
    // 4. for loop
    // ------------------------------------------------------------
    public void demonstrateForLoop() {
        System.out.println("\n=== for loop ===");

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }


    // ------------------------------------------------------------
    // 5. enhanced for loop (for-each)
    // ------------------------------------------------------------
    public void demonstrateEnhancedFor() {
        System.out.println("\n=== Enhanced for loop ===");

        int[] numbers = {10, 20, 30, 40};

        for (int n : numbers) {
            System.out.println("Value: " + n);
        }
    }


    // ------------------------------------------------------------
    // 6. while loop
    // ------------------------------------------------------------
    public void demonstrateWhileLoop() {
        System.out.println("\n=== while loop ===");

        int count = 3;
        while (count > 0) {
            System.out.println("Countdown: " + count);
            count--;
        }
    }


    // ------------------------------------------------------------
    // 7. do-while loop
    // ------------------------------------------------------------
    public void demonstrateDoWhileLoop() {
        System.out.println("\n=== do-while loop ===");

        int x = 1;

        // Executes at least once
        do {
            System.out.println("x = " + x);
            x++;
        } while (x <= 3);
    }


    // ------------------------------------------------------------
    // 8. break and continue
    // ------------------------------------------------------------
    public void demonstrateBreakContinue() {
        System.out.println("\n=== break and continue ===");

        System.out.println("Numbers until break:");
        for (int i = 1; i <= 5; i++) {
            if (i == 3)
                break; // stops the loop entirely
            System.out.println(i);
        }

        System.out.println("Numbers skipping 3 using continue:");
        for (int i = 1; i <= 5; i++) {
            if (i == 3)
                continue; // skips the current iteration
            System.out.println(i);
        }
    }


    // ------------------------------------------------------------
    // 9. Labeled loops (for nested loops)
    // ------------------------------------------------------------
    public void demonstrateLabeledLoops() {
        System.out.println("\n=== Labeled Loops (important) ===");

        outerLoop: // label name
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {

                if (i == 2 && j == 2) {
                    System.out.println("Breaking OUTER loop at i=2, j=2");
                    break outerLoop; // break entire outer loop
                }

                System.out.println("i=" + i + ", j=" + j);
            }
        }
    }


    // ------------------------------------------------------------
    // MAIN: Run all demonstrations
    // ------------------------------------------------------------
    public static void main(String[] args) {
        ControlFlowExample ex = new ControlFlowExample();

        ex.demonstrateIfElse();
        ex.demonstrateNestedIf();
        ex.demonstrateSwitch();
        ex.demonstrateForLoop();
        ex.demonstrateEnhancedFor();
        ex.demonstrateWhileLoop();
        ex.demonstrateDoWhileLoop();
        ex.demonstrateBreakContinue();
        ex.demonstrateLabeledLoops();
    }
}
