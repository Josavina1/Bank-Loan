USE bank_db;

-- Create accounts table
CREATE TABLE accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    account_type ENUM('Savings', 'Checking') NOT NULL,
    balance DECIMAL(10, 2) NOT NULL 
);

-- Create user_info table (separating account info from personal details)
CREATE TABLE user_info (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

-- Create loans table
CREATE TABLE loans (
    loan_id INT AUTO_INCREMENT PRIMARY KEY,bank_dbbank_db
    account_id INT,
    amount DECIMAL(10, 2) NOT NULL,
    interest_rate DECIMAL(4, 2) NOT NULL,
    duration_months INT NOT NULL,
    status ENUM('pending', 'approved', 'rejected') DEFAULT 'pending',
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);



-- Create transactions table (to track account transactions)
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    transaction_type ENUM('deposit', 'withdraw') NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);




ALTER TABLE accounts MODIFY account_type ENUM('Savings', 'Checking') NOT NULL;
DESCRIBE accounts;

DESCRIBE transactions;
ALTER TABLE transactions MODIFY transaction_type ENUM('deposit', 'withdraw') NOT NULL;


DROP TABLE IF EXISTS transactions;

CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT,
    transaction_type ENUM('deposit','withdraw') NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
);

ALTER TABLE transactions
MODIFY transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

DESCRIBE transactions;

ALTER TABLE user_info
ADD CONSTRAINT fk_account
FOREIGN KEY (account_id)
REFERENCES accounts(account_id)
ON DELETE CASCADE;



