/**
 * Demonstrates INTERFACES in Java.
 *
 * Interface:
 * - A contract that defines *what* a class must do, not *how*.
 * - Can have:
 *      - abstract methods (implicitly public & abstract)
 *      - default methods (with body)
 *      - static methods (with body)
 *      - public static final constants
 *
 * This file shows:
 * 1. Basic interface and implementation
 * 2. Multiple interface implementation
 * 3. Default and static methods
 * 4. Interface constants
 */

 // ------------------------------------------------------------
 // 1. Basic Interface
 // ------------------------------------------------------------

interface Playable {

    // Implicitly: public static final
    int DEFAULT_VOLUME = 5;

    // Implicitly: public abstract
    void play();

    // Default method (Java 8+)
    default void stop() {
        System.out.println("Stopping playback...");
    }

    // Static method in interface
    static void showDescription() {
        System.out.println("Playable: anything that can be played.");
    }
}


class Guitar implements Playable {

    private String brand;

    Guitar(String brand) {
        this.brand = brand;
    }

    @Override
    public void play() {
        System.out.println("Playing guitar of brand: " + brand + " at volume " + DEFAULT_VOLUME);
    }
}


class Piano implements Playable {

    private String type;

    Piano(String type) {
        this.type = type;
    }

    @Override
    public void play() {
        System.out.println("Playing a " + type + " piano at volume " + DEFAULT_VOLUME);
    }
}


 // ------------------------------------------------------------
 // 2. Multiple Interface Implementation
 // ------------------------------------------------------------

interface Recordable {
    void record();
}

class MusicStudio implements Playable, Recordable {

    @Override
    public void play() {
        System.out.println("Studio is playing background music...");
    }

    @Override
    public void record() {
        System.out.println("Studio is recording audio...");
    }
}


 // ------------------------------------------------------------
 // DRIVER CLASS
 // ------------------------------------------------------------

public class InterfacesExample {

    public static void main(String[] args) {

        System.out.println("=== Interfaces Example ===\n");

        // Calling static method of interface
        Playable.showDescription();

        System.out.println("\n--- Playable Implementations ---");

        Playable guitar = new Guitar("Fender");
        Playable piano = new Piano("Grand");

        guitar.play();
        guitar.stop(); // default method

        piano.play();
        piano.stop(); // default method

        System.out.println("\n--- Multiple Interfaces Implementation ---");

        MusicStudio studio = new MusicStudio();
        studio.play();
        studio.record();

        // Interface reference for multiple implementations
        Playable playableStudio = studio;
        Recordable recordableStudio = studio;

        playableStudio.play();
        recordableStudio.record();

        /*
            Notice:
            - We use the same interface type (Playable) for Guitar, Piano, MusicStudio.
            - Each class gives its own behavior for play().
            - Interfaces support MULTIPLE IMPLEMENTATION:
              class MusicStudio implements Playable, Recordable
        */
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF INTERFACES
====================================================================================

1. WHAT IS AN INTERFACE?
------------------------
An interface is like a CONTRACT.

It says:
"Any class that implements me MUST provide these methods."

Example:
interface Playable {
    void play();
}

class Guitar implements Playable {
    public void play() { ... }
}

You cannot create objects of an interface:
Playable p = new Playable(); // ❌ not allowed


2. INTERFACE VS ABSTRACT CLASS (HIGH LEVEL)
-------------------------------------------
Interface:
- Represents a pure contract
- All methods are abstract by default (except default/static)
- No instance fields (only constants)
- A class can implement MULTIPLE interfaces

Abstract class:
- Can have state (fields)
- Can have both implemented & abstract methods
- Single inheritance only (one parent class)


3. INTERFACE CONSTANTS
-----------------------
In an interface:

int DEFAULT_VOLUME = 5;

is actually:
public static final int DEFAULT_VOLUME = 5;

So:
- You access it like: Playable.DEFAULT_VOLUME
- It cannot be changed


4. DEFAULT METHODS (Java 8+)
----------------------------
Default methods allow interfaces to have a method with a body.

Why?
- To add new methods to interfaces without breaking old code

Example:
default void stop() {
    System.out.println("Stopping...");
}

Any class that implements the interface:
- can use stop() as-is
- OR override it


5. STATIC METHODS IN INTERFACES
-------------------------------
Static methods belong to the interface, not to instances.

Example:
Playable.showDescription();

They are used for helper/utility methods related to the interface.


6. MULTIPLE INTERFACES IMPLEMENTATION
-------------------------------------
Java does NOT allow:
class C extends A, B  // ❌ no multiple inheritance with classes

But ALLOWS:
class C implements AInterface, BInterface  // ✔ multiple interfaces

This gives:
- Multiple behavior types
- No state conflicts (interfaces have no instance fields)


7. REAL-WORLD ANALOGY
----------------------
Think of interfaces like ROLES:

Playable = "can be played"
Recordable = "can be recorded"

A class can have multiple roles:

MusicStudio implements Playable, Recordable


8. SUMMARY
----------
✔ Interface = contract of methods  
✔ A class implements an interface and must define all methods  
✔ Interfaces support multiple inheritance (types)  
✔ Default and static methods add behavior inside interfaces  
✔ Used heavily in frameworks, APIs, and clean architecture  

====================================================================================
*/
