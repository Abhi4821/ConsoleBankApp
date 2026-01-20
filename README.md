# 🏦 Console Bank Application (Core Java)

A **long-running, terminal-based banking application** built using **Core Java**.  
The project is designed to behave like **Kali Linux / Termux command-line tools**,  
where the application keeps running continuously and handles errors without crashing.

---

## 📌 Project Description

This project simulates a basic banking system that runs entirely on the **terminal**.
It uses **in-memory storage (Map / List)** and is intended for:

- Learning Core Java
- Understanding layered architecture
- Practicing exception handling
- Demonstrating long-running console applications

The application **does not use a database** and **does not terminate on validation errors**.

---

## 🖥️ Console Output (Live Terminal Screen)


=============================<({[   Welcome to Console Bank App   ]})>=============================
╔════════════════════════════════════════════════════════════════════════════════════╗
║                     <==   --  CONSOLE BANK APP  --    ==>                          ║
╠════════════════════════════════════════════════════════════════════════════════════╣
║  1️⃣ Open Account   ║  2️⃣ Deposit        ║  3️⃣ Withdraw       ║  4️⃣ Transfer       ║
║  5️⃣ Statement      ║  6️⃣ List Accounts  ║  7️⃣ Search Account ║  0️⃣ Exit           ║
╠════════════════════════════════════════════════════════════════════════════════════╣
║               Secure • Simple • Fast • Trusted Banking System                      ║
╚════════════════════════════════════════════════════════════════════════════════════╝
Select  :

⚙️ Application Behavior (Termux Style)
Runs inside an infinite loop
Accepts input from terminal
Exceptions are handled gracefully
Data stays in memory as long as the JVM process is alive

📁 Project Structure
console-bank-app/
│
├── app/
│   └── Main.java
│
├── service/
│   ├── BankService.java
│   └── BankServImple.java
│
├── repository/
│   ├── AccountRepo.java
│   ├── CustomerRepo.java
│   └── TransectionRepo.java
│
├── domain/
│   ├── Account.java
│   ├── Customer.java
│   ├── Transaction.java
│   └── Type.java
│
├── CustomException/
│   ├── AccountNotFound.java
│   ├── InsufficientFundException.java
│   └── ValidationException.java
│
├── util/
│   └── Validation.java
│
└── README.md


🚀 Features
Open new bank account
Deposit money
Withdraw money
Transfer funds
View account statement
List all accounts
Search accounts by customer name
Custom validations
Custom exception handling

🧠 Technologies & Concepts Used
Core Java
OOP (Encapsulation, Abstraction)
Interfaces & Implementations
Enums
Lambda expressions
Functional interfaces
Collections (Map, List)
Design & Best Practices
Layered architecture
Separation of concerns
Custom exception classes
Centralized exception handling
Long-running console process
No forced termination (System.exit() not used)

🔐 Exception Handling Strategy
Service layer throws custom exceptions
Main/UI layer catches all exceptions
Errors are printed to console
Application continues running

💾 Data Storage Strategy
Uses in-memory storage
Data stored using HashMap and ArrayList
Data remains available until JVM process stops
No database dependency

▶️ How to Run

Follow the steps below to run the application locally.

1️⃣ Clone the repository
git clone https://github.com/Abhi4821/ConsoleBankApp.git

2️⃣ Navigate to the project directory
cd ConsoleBankApp

3️⃣ Compile the application
javac app/Main.java

4️⃣ Run the application
java app.Main

▶️ Run using JAR (Optional)

If a packaged JAR file is available, you can run the application using:

java -jar ConsoleBankApp.jar

🖥️ Platform Support

Linux (Kali Linux, Ubuntu, EC2, VPS)
macOS
Windows (CMD / PowerShell / Git Bash)

This project is designed for learning, demonstration, and understanding real-world Java application structure
using terminal-based execution.
