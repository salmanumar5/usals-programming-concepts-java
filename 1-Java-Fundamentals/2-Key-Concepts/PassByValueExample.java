/**
 * PASS-BY-VALUE IN JAVA
 *
 * Java is ALWAYS pass-by-value.
 *
 * For primitives:
 *      - The actual value is copied.
 *
 * For objects:
 *      - The REFERENCE is copied (not the object itself).
 *      - Both variables point to the same object until one is reassigned.
 *
 * This file demonstrates:
 * 1. Pass-by-value with primitive types
 * 2. Pass-by-value with objects
 * 3. Modifying object fields vs reassigning reference
 */

public class PassByValueExample {

    // ------------------------------------------------------------
    // 1. Primitive types (simple copy of value)
    // ------------------------------------------------------------
    public void changePrimitive(int num) {
        num = 100; // modifies ONLY local copy
    }


    // ------------------------------------------------------------
    // 2. Object Example
    // ------------------------------------------------------------
    static class Person {
        String name;

        Person(String name) {
            this.name = name;
        }
    }

    // Modifies object field → reflects outside
    public void modifyObject(Person p) {
        p.name = "Changed Inside Method";
    }

    // Reassigns reference → does NOT affect original reference
    public void reassignObject(Person p) {
        p = new Person("New Person Object"); // local reference only
    }


    // ------------------------------------------------------------
    // MAIN
    // ------------------------------------------------------------
    public static void main(String[] args) {

        PassByValueExample ex = new PassByValueExample();

        // ------------------ Primitives ------------------
        int number = 50;
        System.out.println("Before changePrimitive(): " + number);

        ex.changePrimitive(number);
        System.out.println("After changePrimitive(): " + number);
        // number remains 50 because only a copy was changed


        // ------------------ Objects ------------------
        Person person = new Person("Original Name");

        System.out.println("\nBefore modifyObject(): " + person.name);
        ex.modifyObject(person);
        System.out.println("After modifyObject(): " + person.name);
        // name is changed → same object was referenced


        // ------------------ Reassign Object Reference ------------------
        System.out.println("\nBefore reassignObject(): " + person.name);
        ex.reassignObject(person);
        System.out.println("After reassignObject(): " + person.name);
        // name remains SAME → reference was copied and reassigned LOCALLY

        /*
         Summary:
         - Java passes EVERYTHING by value.
         - For objects, the value being passed is the REFERENCE.
         - Changing the object -> visible outside.
         - Reassigning parameter -> NOT visible outside.
         */
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF PASS-BY-VALUE IN JAVA
====================================================================================

1. WHAT DOES PASS-BY-VALUE MEAN?
--------------------------------
It means that whenever you pass something to a method, Java makes a COPY of it.
The method works on the copy, not the original.

So:
- For primitives → the value is copied.
- For objects → the reference (address) is copied.

IMPORTANT:
Java NEVER passes the actual object to a method.
It only passes a copy of the reference (pointer).


2. PRIMITIVES → SIMPLE COPY
---------------------------
int x = 50;

When you call:
changePrimitive(x);

Inside the method, a COPY of 50 is received.
Changing that copy does NOT affect the original variable.

So:
x remains 50.


3. OBJECTS → COPY OF REFERENCE
------------------------------
Person p = new Person("Original");

When you call:
modifyObject(p);

Java passes the REFERENCE (address of the object) BY VALUE.

So:
Both 'p' (outside) and 'p' (inside method) point to the SAME OBJECT.

This means:
- If you change object fields, the changes reflect outside.
  Because both references point to the same object.


4. WHY REASSIGNING A PARAMETER DOES NOT AFFECT ORIGINAL?
--------------------------------------------------------
Look at this:

void reassignObject(Person p) {
    p = new Person("New Name");
}

Here is what happens:

Outside:   p → [Object1]

Inside:    p (copy) → [Object1] initially  
Then reassign: p (copy) → [Object2]

The ORIGINAL 'p' outside still points to Object1.
Only the local copy was changed.


5. EASY VISUAL MEMORY DIAGRAM
------------------------------

Before method call:
p ---> [name = "Original"]

Inside method (copy of reference):
p_copy ---> [name = "Original"]

If you modify fields:
[ name ] changes → visible outside.

If you reassign:
p_copy ---> [New Object]
p outside still points to original.


6. SUMMARY IN ONE LINE:
-----------------------
**Java always passes by value. For objects, the value passed is the reference.**
====================================================================================
*/
