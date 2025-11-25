/**
 * Demonstrates:
 * 1. What constructors are
 * 2. Default constructor (implicit & explicit)
 * 3. Parameterized constructors
 * 4. Constructor overloading
 * 5. Constructor chaining using this()
 * 6. Differences between constructor & method
 */

class Student {

    String name;
    int age;

    // ------------------------------------------------------------
    // 1. Default Constructor (explicit)
    // ------------------------------------------------------------
    // This is called when no arguments are passed.
    Student() {
        System.out.println("Default Constructor Called");
        this.name = "Unknown";
        this.age = 0;
    }

    // ------------------------------------------------------------
    // 2. Parameterized Constructor
    // ------------------------------------------------------------
    Student(String name, int age) {
        System.out.println("Parameterized Constructor Called");
        this.name = name;
        this.age = age;
    }

    // ------------------------------------------------------------
    // 3. Constructor Overloading with Different Signatures
    // ------------------------------------------------------------
    Student(String name) {
        System.out.println("Overloaded Constructor Called");
        this.name = name;
        this.age = -1;
    }

    // ------------------------------------------------------------
    // 4. Constructor Chaining using this()
    // ------------------------------------------------------------
    Student(int age) {
        // Call another constructor FIRST (must be first line)
        this("DefaultName", age);
        System.out.println("Constructor chaining executed");
    }

    void display() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}


public class ConstructorsExample {

    public static void main(String[] args) {

        System.out.println("=== Constructors Example ===");

        // Calling default constructor
        Student s1 = new Student();
        s1.display();

        System.out.println();

        // Calling parameterized constructor
        Student s2 = new Student("Umar", 23);
        s2.display();

        System.out.println();

        // Calling overloaded constructor
        Student s3 = new Student("Alice");
        s3.display();

        System.out.println();

        // Calling constructor with chaining
        Student s4 = new Student(25);
        s4.display();
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF CONSTRUCTORS
====================================================================================

1. WHAT IS A CONSTRUCTOR?
-------------------------
A constructor is a special method that is called **when an object is created**.

Example:
Student s = new Student();

Constructor initializes the object:
- sets default values
- allocates memory
- prepares the object to be used


2. KEY RULES ABOUT CONSTRUCTORS
-------------------------------
✔ Constructor name MUST match the class name  
✔ Constructors have NO return type  
✔ They are called automatically when using `new`  
✔ If you do NOT define any constructor, Java creates a default one


3. DEFAULT CONSTRUCTOR
----------------------
Java automatically creates this if no constructors exist:

Student() { }

It sets fields to default values:
- objects → null
- numbers → 0
- boolean → false


4. PARAMETERIZED CONSTRUCTOR
----------------------------
Used when you want to pass values while creating an object:

Student s = new Student("Umar", 23);


5. CONSTRUCTOR OVERLOADING
--------------------------
Multiple constructors with different parameter lists:

Student()
Student(String name)
Student(String name, int age)
Student(int age)


6. CONSTRUCTOR CHAINING (this())
--------------------------------
Used to call one constructor from another.

this(parameters) MUST be the **first statement**.

Why use it?
✔ Avoid duplicate code  
✔ Centralize initialization logic


7. CONSTRUCTOR vs METHOD
------------------------
Constructor:
- No return type
- Same name as class
- Called automatically on object creation

Method:
- Has return type
- Can have any name
- Called manually


8. MEMORY MODEL DURING OBJECT CREATION
--------------------------------------
Student s = new Student("Umar", 23);

STACK:
    s → reference variable

HEAP:
    Student object with:
        name = "Umar"
        age  = 23

Constructor initializes the fields inside the object.


9. WHY CONSTRUCTORS ARE IMPORTANT?
----------------------------------
They guarantee that every object:
✔ Starts with valid data  
✔ Is ready to use  
✔ Has its fields initialized properly

====================================================================================
*/
