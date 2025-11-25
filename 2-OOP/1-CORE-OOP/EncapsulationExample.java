/**
 * Demonstrates the concept of ENCAPSULATION in Java:
 *
 * Encapsulation means:
 * - Keeping fields private
 * - Providing controlled access via getters & setters
 * - Protecting the internal state of the object
 *
 * This file includes:
 * 1. Simple encapsulated class (Person)
 * 2. Validation logic inside setters
 * 3. Read-only and Write-only fields
 * 4. Real-world example (BankAccount)
 */

// ------------------------------------------------------------
// 1. Simple Encapsulated Class
// ------------------------------------------------------------
class Person {

    // private fields (data hiding)
    private String name;
    private int age;

    // Getter (allows read access)
    public String getName() {
        return name;
    }

    // Setter (allows controlled write access)
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            System.out.println("Invalid name!");
            return;
        }
        this.name = name;
    }

    // Getter
    public int getAge() {
        return age;
    }

    // Setter with VALIDATION
    public void setAge(int age) {
        if (age < 0 || age > 130) {
            System.out.println("Invalid age!");
            return;
        }
        this.age = age;
    }
}


// ------------------------------------------------------------
// 2. Real-world Example: Bank Account
// ------------------------------------------------------------
class BankAccount {

    private String holderName;
    private double balance;

    // Constructor using encapsulation
    public BankAccount(String holderName, double balance) {
        this.holderName = holderName;
        this.balance = balance;
    }

    // Getter
    public double getBalance() {
        return balance;
    }

    // Deposit method (controlled update)
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive!");
            return;
        }
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    // Withdraw method with validation
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        if (amount > balance) {
            System.out.println("Not enough balance!");
            return;
        }

        balance -= amount;
        System.out.println("Withdrew: " + amount);
    }

    // Read-only getter
    public String getHolderName() {
        return holderName;
    }
}


// ------------------------------------------------------------
// 3. Demonstration Class
// ------------------------------------------------------------
public class EncapsulationExample {

    public static void main(String[] args) {

        System.out.println("=== Encapsulation Example ===\n");

        // Person example
        Person p = new Person();
        p.setName("Umar");
        p.setAge(23);

        System.out.println("Name: " + p.getName());
        System.out.println("Age: " + p.getAge());

        System.out.println("\n--- Testing Validation ---");
        p.setAge(-5);  // invalid

        // BankAccount example
        System.out.println("\n=== BankAccount Example ===");
        BankAccount acc = new BankAccount("Alice", 5000);

        System.out.println("Holder: " + acc.getHolderName());
        System.out.println("Balance: " + acc.getBalance());

        acc.deposit(2000);
        acc.withdraw(1000);
        acc.withdraw(10000); // invalid
    }
}


/*
====================================================================================
BEGINNER-FRIENDLY EXPLANATION OF ENCAPSULATION
====================================================================================

1. WHAT IS ENCAPSULATION?
-------------------------
Encapsulation = Wrapping data + methods into a single unit (class)  
AND restricting direct access to the data.

Put simply:
- Keep fields PRIVATE
- Access fields via public GETTERS & SETTERS


2. WHY DO WE NEED ENCAPSULATION?
--------------------------------
✔ Protects data from misuse  
✔ Prevents invalid values (age cannot be -5)  
✔ Allows controlled updates (e.g., bank withdrawals)  
✔ Increases security  
✔ Hides internal implementation  
✔ Makes code more maintainable  


3. REAL-LIFE ANALOGY
--------------------
You cannot directly access a bank's database.

You interact through:
- ATM machine
- Mobile app
- Teller

These are methods controlling access → like getters/setters.


4. READ-ONLY AND WRITE-ONLY FIELDS
----------------------------------
Read-only: Provide only getter  
Write-only: Provide only setter

Example (Read-only):
public String getName() { return name; }

Example (Write-only):
public void setPassword(String pwd) { ... }


5. DATA HIDING vs ENCAPSULATION
-------------------------------
They are related but not the same:

Data Hiding:
- Making fields private
- Preventing direct access

Encapsulation:
- Combining data hiding + controlled access using methods  


6. BEST PRACTICES
------------------
✔ Keep fields private  
✔ Use getters & setters  
✔ Add validation inside setters  
✔ Avoid exposing internal mutable objects directly  
✔ Use defensive copying for arrays & lists  


7. MEMORY AND FLOW WHEN USING ENCAPSULATION
--------------------------------------------
Person p = new Person();
p.setAge(23);

Stack:
    p → reference

Heap:
    Person object {
        name = "Umar"
        age = 23
    }

Setter modifies the object's internal state in a SAFE and CONTROLLED way.


====================================================================================
*/
