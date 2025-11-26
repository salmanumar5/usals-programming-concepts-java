/**
 * Full Real-World Example of Polymorphism:
 *
 * A Payment Processing System
 *
 * Demonstrates:
 * 1. Parent class "Payment"
 * 2. Child classes overriding "pay()" method
 * 3. Runtime polymorphism via method overriding
 * 4. Using parent references for flexible code
 * 5. Polymorphic arrays and methods
 *
 * This is how polymorphism is used in real enterprise apps.
 */

// ------------------------------------------------------------
// PARENT CLASS
// ------------------------------------------------------------
abstract class Payment {

    double amount;

    Payment(double amount) {
        this.amount = amount;
    }

    // Abstract method → must be overridden by child classes
    abstract void pay();

    void paymentInfo() {
        System.out.println("Processing payment of: ₹" + amount);
    }
}


// ------------------------------------------------------------
// CHILD CLASS #1
// ------------------------------------------------------------
class CardPayment extends Payment {

    private String cardNumber;

    CardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    void pay() {
        System.out.println("Paid ₹" + amount + " using Card: " + maskCard(cardNumber));
    }

    private String maskCard(String num) {
        // Returns **** **** **** 1234
        return "**** **** **** " + num.substring(num.length() - 4);
    }
}


// ------------------------------------------------------------
// CHILD CLASS #2
// ------------------------------------------------------------
class UpiPayment extends Payment {

    private String upiId;

    UpiPayment(double amount, String upiId) {
        super(amount);
        this.upiId = upiId;
    }

    @Override
    void pay() {
        System.out.println("Paid ₹" + amount + " using UPI ID: " + upiId);
    }
}


// ------------------------------------------------------------
// CHILD CLASS #3
// ------------------------------------------------------------
class PaypalPayment extends Payment {

    private String email;

    PaypalPayment(double amount, String email) {
        super(amount);
        this.email = email;
    }

    @Override
    void pay() {
        System.out.println("Paid ₹" + amount + " using PayPal account: " + email);
    }
}


// ------------------------------------------------------------
// PAYMENT PROCESSOR (uses polymorphism)
// ------------------------------------------------------------
class PaymentProcessor {

    // A single method that handles ALL payment types
    void processPayment(Payment p) {
        p.paymentInfo();  // common behavior
        p.pay();          // overridden behavior (polymorphic)
        System.out.println("Payment successful.\n");
    }
}


// ------------------------------------------------------------
// DRIVER CLASS
// ------------------------------------------------------------
public class PolymorphismFullExample {

    public static void main(String[] args) {

        System.out.println("=== Polymorphism Full Example (Payment System) ===\n");

        Payment p1 = new CardPayment(1500, "1234567812345678");
        Payment p2 = new UpiPayment(500, "umar@upi");
        Payment p3 = new PaypalPayment(2500, "user@example.com");

        PaymentProcessor processor = new PaymentProcessor();

        // Polymorphic processing
        processor.processPayment(p1);
        processor.processPayment(p2);
        processor.processPayment(p3);

        System.out.println("=== Polymorphic Array ===");

        Payment[] payments = {
            new CardPayment(1999, "4321432143214321"),
            new UpiPayment(299, "salman@upi"),
            new PaypalPayment(1000, "someone@paypal.com")
        };

        for (Payment p : payments) {
            processor.processPayment(p); // same method, multiple behaviors
        }
    }
}



/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF FULL POLYMORPHISM EXAMPLE
====================================================================================

1. WHAT ARE WE ACHIEVING?
--------------------------
We model a PAYMENT SYSTEM with:
- Different types of payments  
- One common interface (Payment)  
- Each payment behaves differently  

This is EXACTLY how real-world enterprise apps work.


2. WHY USE AN ABSTRACT CLASS?
------------------------------
Payment is abstract because:
- We should NOT create a generic "Payment" object
- Each payment must define its own way of paying

So "pay()" is abstract → forces overriding.


3. RUNTIME POLYMORPHISM IN ACTION
----------------------------------
Payment p = new CardPayment(...);
p.pay();      // CardPayment version runs

Payment p = new UpiPayment(...);
p.pay();      // UpiPayment version runs

Same reference type  
Same method call  
Different behavior  
→ polymorphism.


4. POLYMORPHIC PROCESSOR
-------------------------
PaymentProcessor.processPayment(Payment p)

This method works for:
- CardPayment  
- UpiPayment  
- PaypalPayment  
- Any future payment type  

No code changes needed!

The code becomes:
✔ Extensible  
✔ Maintainable  
✔ Loosely coupled  


5. WHY POLYMORPHIC ARRAYS ARE POWERFUL?
----------------------------------------
Payment[] payments = {new CardPayment(), new UpiPayment(), ...}

Loop once → handle all payment types.

This is extremely common in production apps.


6. MEMORY DIAGRAM
------------------

Payment p = new CardPayment();

STACK:
    p → reference to Payment (parent type)

HEAP:
    CardPayment object (child type)

Call p.pay()
→ JVM checks object type (CardPayment)
→ calls overridden CardPayment.pay()


7. REAL-WORLD USE CASES
------------------------
This pattern is used in:
✔ Payment gateways  
✔ Notification systems (SMS/Email/Push)  
✔ Shipping (DHL/UPS/FedEx)  
✔ Authentication providers  
✔ Logging (Console/File/DB)  
✔ Strategy pattern implementations  

Polymorphism gives flexibility and scalability.


8. SUMMARY
-----------
✔ One parent type, many child types  
✔ Override methods to provide different behavior  
✔ Parent reference → Child object = runtime polymorphism  
✔ Process many types with a single method  
✔ Most powerful concept in OOP  

====================================================================================
*/
