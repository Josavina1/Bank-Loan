# 🏦 Bank Loan Management System

A Java-based desktop application built with NetBeans and MariaDB that allows users to manage bank accounts, process transactions, and handle loans. 
The system features a clean graphical user interface (GUI) built using Java Swing.

---

## ✅ Features

- Create, view, and delete bank accounts
- Perform deposits and withdrawals
- Log transactions with date and type
- Request and manage loans
- CRUD functionality for all major components
- Real-time data updates with MySQL integration
- Clean user-friendly GUI

---

## 🛠️ Technologies Used

- **Programming Language:** Java
- **IDE:** NetBeans
- **Database:** MariaDB / MySQL (via HeidiSQL)
- **UI Framework:** Java Swing (GUI Builder)
- **Database Access:** JDBC

---

 🗂️ Database Tables

--- `accounts`
- `account_id` (PK)
- `account_type`
- `balance`

--- `user_info`
- `user_id` (PK)
- `full_name`
- `email`
- `phone`
- `account_id` (FK)

--- `transactions`
- `transaction_id` (PK)
- `account_id` (FK)
- `transaction_type` (deposit / withdraw)
- `amount`
- `transaction_date`

--- `loans`
- `loan_id` (PK)
- `account_id` (FK)
- `loan_type`
- `amount`
- `interest`
- `duration`

---

 🔄 CRUD Operations Implemented

- **Create:** Add new accounts, transactions, and loans
- **Read:** View accounts, transaction history, and loan details
- **Update:** Balance updates during transactions
- **Delete**: Remove accounts, transactions, and loans

---

 🧑‍💻 How to Run

1. Clone the repository or download the source code.
2. Open the project in NetBeans.
3. Create the required database using the schema.
4. Configure `DatabaseConnection.java` with your DB credentials.
5. Build and run the project.



---

👤 Author

[Josavina Abbey] 
IT Student. 
*This project was created as part of a school project defense.*

---

📝 License

This project is for educational and demonstration purposes only.
