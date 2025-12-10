/**
 * Demonstrates:
 * 1. DEFAULT methods in interfaces
 * 2. STATIC methods in interfaces
 * 3. Why default methods were introduced (Java 8)
 * 4. How default methods help backward compatibility
 * 5. Overriding default methods
 * 6. Calling interface static methods
 * 7. Conflict resolution when multiple interfaces have same default method
 */

 // ------------------------------------------------------------
 // 1. Interface with Default & Static Methods
 // ------------------------------------------------------------

interface SmartDevice {

    // Abstract method (must be implemented)
    void turnOn();

    // DEFAULT method (has a body)
    default void showStatus() {
        System.out.println("Device is in default operational mode.");
    }

    // STATIC method (belongs to interface only)
    static void deviceInfo() {
        System.out.println("SmartDevice: Interface for all smart electronic devices.");
    }
}


class SmartLight implements SmartDevice {

    @Override
    public void turnOn() {
        System.out.println("Smart Light is turned ON.");
    }

    // Optional: override default method
    @Override
    public void showStatus() {
        System.out.println("Smart Light is glowing brightly.");
    }
}


class SmartFan implements SmartDevice {

    @Override
    public void turnOn() {
        System.out.println("Smart Fan is turned ON.");
    }

    // Does NOT override showStatus()
    // So it will use the DEFAULT implementation from interface
}


 // ------------------------------------------------------------
 // 2. Multiple Interfaces with Same Default Method (Conflict Case)
 // ------------------------------------------------------------

interface WifiEnabled {

    default void connect() {
        System.out.println("Connecting to WiFi...");
    }
}

interface BluetoothEnabled {

    default void connect() {
        System.out.println("Connecting to Bluetooth...");
    }
}

// MUST override connect() to resolve conflict
class SmartSpeaker implements WifiEnabled, BluetoothEnabled {

    @Override
    public void connect() {
        System.out.println("SmartSpeaker choosing best available connection...");
        
        // You can still call individual interface versions explicitly:
        WifiEnabled.super.connect();
        BluetoothEnabled.super.connect();
    }
}


 // ------------------------------------------------------------
 // DRIVER CLASS
 // ------------------------------------------------------------

public class InterfaceDefaultStaticMethodsExample {

    public static void main(String[] args) {

        System.out.println("=== Default & Static Methods in Interfaces ===\n");

        // Calling STATIC method of interface
        SmartDevice.deviceInfo();

        System.out.println("\n--- SmartLight ---");
        SmartDevice light = new SmartLight();
        light.turnOn();
        light.showStatus(); // overridden default method

        System.out.println("\n--- SmartFan ---");
        SmartDevice fan = new SmartFan();
        fan.turnOn();
        fan.showStatus();   // uses DEFAULT method from interface

        System.out.println("\n--- Default Method Conflict Resolution ---");
        SmartSpeaker speaker = new SmartSpeaker();
        speaker.connect();

        /*
            Notice:
            - SmartLight overrides the default method
            - SmartFan uses interface's default implementation
            - SmartSpeaker MUST resolve conflict between two default methods
        */
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF DEFAULT & STATIC INTERFACE METHODS
====================================================================================

1. WHY WERE DEFAULT METHODS INTRODUCED? (JAVA 8)
-------------------------------------------------
Before Java 8:
- Interfaces could have ONLY abstract methods.
- If you added a new method to an existing interface,
  ALL implementing classes would break.

To fix this problem:
→ DEFAULT methods were introduced.


2. WHAT IS A DEFAULT METHOD?
-----------------------------
A default method:
- Is defined inside an interface
- Has a body
- Is inherited automatically by implementing classes

Example:
default void showStatus() {
    System.out.println("Default behavior");
}

A class can:
✔ Use it as-is  
✔ Override it if needed  


3. WHY STATIC METHODS IN INTERFACES?
------------------------------------
Static methods:
- Belong to the interface itself
- Are called using interface name
- Are NOT inherited by implementing classes

Example:
SmartDevice.deviceInfo();


4. DIFFERENCE BETWEEN DEFAULT & STATIC METHODS
-----------------------------------------------

Default Method:
✔ Belongs to object  
✔ Can be overridden  
✔ Called using object reference  

Static Method:
✔ Belongs to interface  
✔ Cannot be overridden  
✔ Called using interface name  


5. MULTIPLE DEFAULT METHOD CONFLICT
------------------------------------
If two interfaces have the SAME default method:

interface A { default void show() {} }
interface B { default void show() {} }

class C implements A, B { }  ❌ ERROR

Java forces you to override and resolve the conflict:

class C implements A, B {
    public void show() {
        A.super.show();
        B.super.show();
    }
}


6. WHEN SHOULD YOU USE DEFAULT METHODS?
----------------------------------------
✔ To evolve old interfaces without breaking code  
✔ To provide optional behavior  
✔ To add helper functionality to interfaces  


7. REAL-WORLD USAGE
--------------------
Default methods are heavily used in:
✔ Java Collections API
✔ Streams
✔ Functional interfaces
✔ Framework extension points


8. SUMMARY
----------
✔ Default methods allow behavior in interfaces  
✔ They prevent breaking older code  
✔ Static methods belong only to the interface  
✔ Conflicts must be resolved manually  
✔ Introduced in Java 8  

====================================================================================
*/
