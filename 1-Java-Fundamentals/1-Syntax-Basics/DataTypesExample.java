/**
 * Demonstrates all Java data types:
 *
 * 1. Primitive Data Types (8 types)
 *    - byte, short, int, long
 *    - float, double
 *    - char
 *    - boolean
 *
 * 2. Reference Types
 *    - String, arrays, objects
 *
 * 3. Wrapper Classes for primitives
 *
 * 4. Literal rules + underscores in numbers
 *
 * 5. Default values when used as instance variables
 *
 * NOTE:
 * Local variables do NOT have default values → must be initialized before use.
 */

public class DataTypesExample {

    // ------------------------------------------------------------
    // 1. Default values for instance variables
    // ------------------------------------------------------------
    byte defaultByte;          // 0
    short defaultShort;        // 0
    int defaultInt;            // 0
    long defaultLong;          // 0L

    float defaultFloat;        // 0.0f
    double defaultDouble;      // 0.0d

    char defaultChar;          // '\u0000' → null character
    boolean defaultBoolean;    // false

    String defaultString;      // null (reference type)
    int[] defaultArray;        // null (reference type)


    // ------------------------------------------------------------
    // 2. Method demonstrating primitive types
    // ------------------------------------------------------------
    public void demonstratePrimitives() {

        // -------- Integers --------
        byte b = 10;          // 1 byte  → range: -128 to 127
        short s = 300;        // 2 bytes → range: -32,768 to 32,767
        int i = 10000;        // 4 bytes → most common
        long l = 10000000000L;  // 8 bytes → must add 'L' suffix

        // -------- Floating-point --------
        float f = 3.14f;      // 4 bytes, must use 'f' suffix
        double d = 3.14159265359; // 8 bytes, default for decimals

        // -------- Character --------
        char c1 = 'A';        // stores a single character
        char c2 = 65;         // also valid → ASCII value for 'A'
        char c3 = '\u0041';   // Unicode for 'A'

        // -------- Boolean --------
        boolean isJavaFun = true;

        System.out.println("=== Primitive Types ===");
        System.out.println("byte: " + b);
        System.out.println("short: " + s);
        System.out.println("int: " + i);
        System.out.println("long: " + l);
        System.out.println("float: " + f);
        System.out.println("double: " + d);
        System.out.println("char1: " + c1);
        System.out.println("char2 (ASCII): " + c2);
        System.out.println("char3 (Unicode): " + c3);
        System.out.println("boolean: " + isJavaFun);
    }


    // ------------------------------------------------------------
    // 3. Reference Types
    // ------------------------------------------------------------
    public void demonstrateReferenceTypes() {

        String name = "Java Programming";  // reference type
        int[] numbers = {1, 2, 3, 4, 5};   // array reference

        System.out.println("\n=== Reference Types ===");
        System.out.println("String: " + name);
        System.out.println("Array length: " + numbers.length);
    }


    // ------------------------------------------------------------
    // 4. Wrapper Classes
    // ------------------------------------------------------------
    public void demonstrateWrappers() {

        Integer wrappedInt = 10;       // auto-boxing
        int primitiveInt = wrappedInt; // auto-unboxing

        Double wrappedDouble = Double.valueOf(3.14);

        System.out.println("\n=== Wrapper Classes ===");
        System.out.println("Wrapped Integer: " + wrappedInt);
        System.out.println("Unboxed int: " + primitiveInt);
        System.out.println("Wrapped Double: " + wrappedDouble);
    }


    // ------------------------------------------------------------
    // 5. Literal Examples + formatting numbers
    // ------------------------------------------------------------
    public void demonstrateLiterals() {

        // Decimal literals with underscores (Java 7+)
        int million = 1_000_000;
        long creditCard = 1234_5678_9012_3456L;

        // Binary literal
        int binary = 0b1010; // 10 in decimal

        // Hexadecimal literal
        int hex = 0xFF;      // 255 in decimal

        // Scientific notation
        double scientific = 1.23e4; // 12300.0

        System.out.println("\n=== Literals ===");
        System.out.println("million: " + million);
        System.out.println("creditCard: " + creditCard);
        System.out.println("binary (0b1010): " + binary);
        System.out.println("hex (0xFF): " + hex);
        System.out.println("scientific notation: " + scientific);
    }


    // ------------------------------------------------------------
    // 6. Display default values of instance variables
    // ------------------------------------------------------------
    public void showDefaultValues() {

        System.out.println("\n=== Default Values (Instance Variables) ===");
        System.out.println("byte: " + defaultByte);
        System.out.println("short: " + defaultShort);
        System.out.println("int: " + defaultInt);
        System.out.println("long: " + defaultLong);

        System.out.println("float: " + defaultFloat);
        System.out.println("double: " + defaultDouble);

        System.out.println("char (as int): " + (int) defaultChar);
        System.out.println("boolean: " + defaultBoolean);

        System.out.println("String (reference type): " + defaultString);
        System.out.println("Array (reference type): " + defaultArray);
    }


    // ------------------------------------------------------------
    // MAIN method to run the examples
    // ------------------------------------------------------------
    public static void main(String[] args) {

        DataTypesExample ex = new DataTypesExample();

        ex.demonstratePrimitives();
        ex.demonstrateReferenceTypes();
        ex.demonstrateWrappers();
        ex.demonstrateLiterals();
        ex.showDefaultValues();
    }
}
