package bank.loan;



import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;  // <-- Add this import
import java.sql.*;
import javax.swing.JOptionPane;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author HP
 */
public class MainDashboard extends javax.swing.JFrame {
    
    Connection conn;


    /**
     * Creates new form MainDashboard
     */
    public MainDashboard() {
        initComponents();
        accountTypeComboBox.addItem("Savings");
        accountTypeComboBox.addItem("Checking");
        conn = DatabaseConnection.getConnection();
        loadLoans();
        cmbTransactionType.addItem("deposit");
        cmbTransactionType.addItem("withdraw");
        setLocationRelativeTo(null);
    }

    private void loadAccountsIntoTable() {
    try {
        // Establish a connection to the database
        Connection conn = DatabaseConnection.getConnection();

        // SQL query to join the accounts and user_info tables
        String sql = "SELECT a.account_id, a.account_type, a.balance, u.full_name, u.email, u.phone "
                + "FROM accounts a "
                + "JOIN user_info u ON a.account_id = u.account_id";

        // Prepare and execute the query
        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        // Get the table model to add data
        DefaultTableModel model = (DefaultTableModel) accountsTable.getModel();
        model.setRowCount(0); // Clear existing rows (optional)

        // Loop through the result set and add each row to the table
        while (rs.next()) {
            Object[] row = {
                rs.getInt("account_id"),
                rs.getString("account_type"),
                rs.getDouble("balance"),
                rs.getString("full_name"),  // Change 'name' to 'full_name'
                rs.getString("email"),
                rs.getString("phone")
            };
            model.addRow(row);  // Add row to the table model
        }

        // Close the ResultSet and PreparedStatement
        rs.close();
        pst.close();
        conn.close();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading accounts: " + e.getMessage());
    }
}


    private void loadLoansIntoTable() {
        DefaultTableModel model = (DefaultTableModel) loanTable.getModel();
        model.setRowCount(0);  // clear table

        String sql = "SELECT * FROM loans";

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("loan_id"),
                    rs.getInt("account_id"),
                    rs.getDouble("amount"),
                    rs.getDouble("interest_rate"),
                    rs.getInt("duration_months"),
                    rs.getString("status"),
                    rs.getTimestamp("timestamp")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
        }
    }
    
    
    private void loadLoans() {
    try {
        String query = "SELECT * FROM loans";
        PreparedStatement pst = conn.prepareStatement(query);
        ResultSet rs = pst.executeQuery();
        DefaultTableModel model = (DefaultTableModel) loanTable.getModel();
        model.setRowCount(0); // clear table

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("loan_id"),
                rs.getInt("account_id"),
                rs.getDouble("amount"),
                rs.getDouble("interest_rate"),
                rs.getInt("duration_months"),
                rs.getString("status"),
                rs.getTimestamp("timestamp")
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    private void loadTransactions() {
    DefaultTableModel model = (DefaultTableModel) transactionsTable.getModel(); // Replace 'transactionsTable' with your actual table variable
    model.setRowCount(0); // clear table first

    String query = "SELECT * FROM transactions";

    try (Connection conn = DatabaseConnection.getConnection(); 
         PreparedStatement stmt = conn.prepareStatement(query); 
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int transactionId = rs.getInt("transaction_id");
            int accountId = rs.getInt("account_id");
            String type = rs.getString("transaction_type");
            double amount = rs.getDouble("amount");
            Timestamp timestamp = rs.getTimestamp("transaction_date");

            model.addRow(new Object[]{transactionId, accountId, type, amount, timestamp});
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Failed to load transactions: " + e.getMessage());
    }
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        fullNameField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        phoneField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        startingBalanceField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        accountTypeComboBox = new javax.swing.JComboBox<>();
        createAccountButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSearchID = new javax.swing.JTextField();
        btnSearchAccount = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtBalance = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtAccountId = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        cmbTransactionType = new javax.swing.JComboBox<>();
        btnPerformTransaction = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        transactionsTable = new javax.swing.JTable();
        btnDeleteTransaction = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        accountsTable = new javax.swing.JTable();
        btnDeleteAccount = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        loanAccountIdField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        amountField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        interestRateField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        durationField = new javax.swing.JTextField();
        submitLoanButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        loanTable = new javax.swing.JTable();
        btnApproveLoan = new javax.swing.JButton();
        btnRejectLoan = new javax.swing.JButton();
        btnDeleteLoan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "BANK LOAN MANAGEMENT", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Sitka Text", 0, 24))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane3.setForeground(new java.awt.Color(102, 0, 255));
        jTabbedPane3.setFont(new java.awt.Font("Sitka Text", 0, 18)); // NOI18N
        jTabbedPane3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane3StateChanged(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 204, 255));

        jLabel6.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel6.setText("Full Name");

        jLabel7.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel7.setText("Email");

        jLabel8.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel8.setText("Phone");

        jLabel9.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel9.setText("Starting Balance");

        jLabel10.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel10.setText("Account Type");

        accountTypeComboBox.setEditable(true);
        accountTypeComboBox.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        accountTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Savings", "Checking", " " }));
        accountTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountTypeComboBoxActionPerformed(evt);
            }
        });

        createAccountButton.setBackground(new java.awt.Color(102, 255, 102));
        createAccountButton.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        createAccountButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add-user.png"))); // NOI18N
        createAccountButton.setText("Create Account");
        createAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createAccountButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 850, Short.MAX_VALUE)
                .addComponent(createAccountButton)
                .addGap(37, 37, 37))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fullNameField)
                        .addComponent(emailField, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                        .addComponent(phoneField))
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accountTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startingBalanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fullNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phoneField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(startingBalanceField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accountTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(createAccountButton)
                .addGap(28, 28, 28))
        );

        jTabbedPane3.addTab("Create Account", new javax.swing.ImageIcon(getClass().getResource("/add-user.png")), jPanel5); // NOI18N

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel2.setText("Account ID");

        txtSearchID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchIDActionPerformed(evt);
            }
        });

        btnSearchAccount.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        btnSearchAccount.setText("Search ID");
        btnSearchAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchAccountActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel1.setText("Full Name");

        txtName.setEditable(false);
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel3.setText("Email");

        txtEmail.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel4.setText("Phone");

        txtPhone.setEditable(false);

        jLabel5.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel5.setText("Balance");

        txtBalance.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtBalance, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPhone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearchID, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSearchAccount)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 120, Short.MAX_VALUE)))
                        .addGap(804, 804, 804))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearchAccount)
                .addGap(37, 37, 37)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Account Info", new javax.swing.ImageIcon(getClass().getResource("/personal-data.png")), jPanel2); // NOI18N

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));

        jLabel15.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel15.setText("Account ID");

        txtAccountId.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel16.setText("Amount");

        txtAmount.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel17.setText("Type");

        cmbTransactionType.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        cmbTransactionType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Deposit", "Withdraw" }));
        cmbTransactionType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTransactionTypeActionPerformed(evt);
            }
        });

        btnPerformTransaction.setBackground(new java.awt.Color(102, 255, 102));
        btnPerformTransaction.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        btnPerformTransaction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/transaction.png"))); // NOI18N
        btnPerformTransaction.setText("PerformTransaction");
        btnPerformTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerformTransactionActionPerformed(evt);
            }
        });

        transactionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Transaction ID", "Amount ID", "Type", "Amount", "Timestamp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(transactionsTable);

        btnDeleteTransaction.setBackground(new java.awt.Color(255, 51, 51));
        btnDeleteTransaction.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        btnDeleteTransaction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bank/loan/delete (1).png"))); // NOI18N
        btnDeleteTransaction.setText("Delete");
        btnDeleteTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTransactionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbTransactionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAccountId, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                        .addComponent(txtAmount))
                    .addComponent(btnPerformTransaction))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 758, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeleteTransaction)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAccountId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbTransactionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnPerformTransaction)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteTransaction)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Transactions", new javax.swing.ImageIcon(getClass().getResource("/transaction.png")), jPanel3); // NOI18N

        jPanel6.setBackground(new java.awt.Color(102, 153, 255));

        accountsTable.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        accountsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Account ID", "Account Type", "Balance", "Full Name", "Email", "Phone"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(accountsTable);

        btnDeleteAccount.setBackground(new java.awt.Color(255, 51, 51));
        btnDeleteAccount.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        btnDeleteAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bank/loan/delete (1).png"))); // NOI18N
        btnDeleteAccount.setText("Delete");
        btnDeleteAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAccountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1035, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDeleteAccount)
                .addGap(16, 16, 16))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteAccount)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Account List", jPanel6);

        jPanel4.setBackground(new java.awt.Color(153, 255, 255));

        jLabel11.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel11.setText("Account ID");

        jLabel12.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel12.setText("Loan Amount");

        jLabel13.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel13.setText("Interest Rate");

        jLabel14.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel14.setText("Duration(Months)");

        submitLoanButton.setBackground(new java.awt.Color(0, 255, 0));
        submitLoanButton.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        submitLoanButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hand.png"))); // NOI18N
        submitLoanButton.setText("Apply for Loan");
        submitLoanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitLoanButtonActionPerformed(evt);
            }
        });

        loanTable.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        loanTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Loan ID", "Account ID", "Amount", "Interest Rate", "Duration", "Status", "Timestamp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(loanTable);

        btnApproveLoan.setBackground(new java.awt.Color(204, 204, 204));
        btnApproveLoan.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        btnApproveLoan.setForeground(new java.awt.Color(0, 204, 0));
        btnApproveLoan.setText("Approve Loan");
        btnApproveLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveLoanActionPerformed(evt);
            }
        });

        btnRejectLoan.setBackground(new java.awt.Color(204, 204, 204));
        btnRejectLoan.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        btnRejectLoan.setForeground(new java.awt.Color(204, 0, 0));
        btnRejectLoan.setText("Reject Loan");
        btnRejectLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRejectLoanActionPerformed(evt);
            }
        });

        btnDeleteLoan.setBackground(new java.awt.Color(255, 51, 51));
        btnDeleteLoan.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        btnDeleteLoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bank/loan/delete (1).png"))); // NOI18N
        btnDeleteLoan.setText("Delete");
        btnDeleteLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteLoanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13)))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(submitLoanButton)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(loanAccountIdField)
                                        .addComponent(amountField)
                                        .addComponent(interestRateField, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE))
                                    .addComponent(jLabel14)
                                    .addComponent(durationField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnApproveLoan)
                        .addGap(18, 18, 18)
                        .addComponent(btnRejectLoan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDeleteLoan)))
                .addGap(22, 22, 22))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loanAccountIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(interestRateField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(durationField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(submitLoanButton))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnApproveLoan)
                    .addComponent(btnRejectLoan)
                    .addComponent(btnDeleteLoan))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Loans", new javax.swing.ImageIcon(getClass().getResource("/loan.png")), jPanel4); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane3)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchIDActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void btnSearchAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchAccountActionPerformed
        String accountId = txtSearchID.getText();

if (accountId.isEmpty()) {
    JOptionPane.showMessageDialog(null, "Please enter an Account ID.");
    return;
}

try {
    Connection conn = DatabaseConnection.getConnection();
    String sql = "SELECT a.account_id, a.account_type, a.balance, u.full_name, u.phone, u.email " +
                 "FROM accounts a " +
                 "JOIN user_info u ON a.account_id = u.account_id " +
                 "WHERE a.account_id = ?";

    try (PreparedStatement pst = conn.prepareStatement(sql)) {
        pst.setInt(1, Integer.parseInt(accountId));

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            txtName.setText(rs.getString("full_name"));
            txtPhone.setText(rs.getString("phone"));
            txtEmail.setText(rs.getString("email"));
            txtBalance.setText(String.valueOf(rs.getDouble("balance")));
        } else {
            JOptionPane.showMessageDialog(null, "Account not found.");
            txtName.setText("");
            txtPhone.setText("");
            txtEmail.setText("");
            txtBalance.setText("");
        }

        rs.close();
    }

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Account ID must be a number.");
} catch (HeadlessException | SQLException e) {
    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
}

    }//GEN-LAST:event_btnSearchAccountActionPerformed

    private void accountTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountTypeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accountTypeComboBoxActionPerformed

    private void createAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createAccountButtonActionPerformed
        String fullName = fullNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String accountType = (String) accountTypeComboBox.getSelectedItem();
        String balanceText = startingBalanceField.getText();

        if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || balanceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double balance = Double.parseDouble(balanceText);

            try (Connection conn = DatabaseConnection.getConnection()) {
                String sql = "INSERT INTO accounts (account_type, balance) VALUES (?, ?)";
                try (PreparedStatement pst = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                    pst.setString(1, accountType);
                    pst.setDouble(2, balance);

                    int rowsInserted = pst.executeUpdate();

                    if (rowsInserted > 0) {
                        // Get the generated account_id
                        ResultSet generatedKeys = pst.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int generatedAccountId = generatedKeys.getInt(1); // Get the generated account_id

                            // Insert into user_info table
                            String userInfoSql = "INSERT INTO user_info (account_id, full_name, email, phone) VALUES (?, ?, ?, ?)";
                            try (PreparedStatement pstUserInfo = conn.prepareStatement(userInfoSql)) {
                                pstUserInfo.setInt(1, generatedAccountId);
                                pstUserInfo.setString(2, fullName);
                                pstUserInfo.setString(3, email);
                                pstUserInfo.setString(4, phone);
                                pstUserInfo.executeUpdate();
                            }

                            JOptionPane.showMessageDialog(this, "Account created successfully with ID: " + generatedAccountId);
                            fullNameField.setText("");
                            emailField.setText("");
                            phoneField.setText("");
                            accountTypeComboBox.setSelectedItem("");
                            startingBalanceField.setText("");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to create account.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid balance amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_createAccountButtonActionPerformed

    private void jTabbedPane3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane3StateChanged
        // Check if the selected tab is the Account List tab (index 1)
        if (jTabbedPane3.getSelectedIndex() == 1) {
            // Call the method to load accounts into the table
            loadAccountsIntoTable();
            int selectedIndex = jTabbedPane3.getSelectedIndex();

            // assuming loans tab is index 3, change if necessary
            if (selectedIndex == 3) {
                loadLoansIntoTable();
            }
        }
        int selectedIndex = jTabbedPane3.getSelectedIndex();
        String selectedTitle = jTabbedPane3.getTitleAt(selectedIndex);

        if ("Transactions".equals(selectedTitle)) {
            loadTransactions();
        }
    }//GEN-LAST:event_jTabbedPane3StateChanged

    private void submitLoanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitLoanButtonActionPerformed
        int accountId = Integer.parseInt(loanAccountIdField.getText());
        double amount = Double.parseDouble(amountField.getText());
        double interestRate = Double.parseDouble(interestRateField.getText());
        int duration = Integer.parseInt(durationField.getText());

        String sql = "INSERT INTO loans (account_id, amount, interest_rate, duration_months) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountId);
            stmt.setDouble(2, amount);
            stmt.setDouble(3, interestRate);
            stmt.setInt(4, duration);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Loan Application Submitted!");
            loadLoansIntoTable();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to submit loan application.");
        }
    }//GEN-LAST:event_submitLoanButtonActionPerformed

    private void btnApproveLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveLoanActionPerformed
        int selectedRow = loanTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a loan to approve.");
            return;
        }

        int loanId = (int) loanTable.getValueAt(selectedRow, 0);
        int accountId = (int) loanTable.getValueAt(selectedRow, 1);
        double amount = (double) loanTable.getValueAt(selectedRow, 2);

        try {
            conn.setAutoCommit(false); // Start transaction

            // 1. Update loan status
            String updateLoan = "UPDATE loans SET status = 'approved' WHERE loan_id = ?";
            PreparedStatement pst1 = conn.prepareStatement(updateLoan);
            pst1.setInt(1, loanId);
            pst1.executeUpdate();

            // 2. Update account balance
            String updateBalance = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";
            PreparedStatement pst2 = conn.prepareStatement(updateBalance);
            pst2.setDouble(1, amount);
            pst2.setInt(2, accountId);
            pst2.executeUpdate();

            conn.commit(); // All done
            conn.setAutoCommit(true);

            JOptionPane.showMessageDialog(this, "Loan approved and balance updated.");
            loadLoans(); // Refresh table

        } catch (Exception e) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred. Try again.");
        }
    }//GEN-LAST:event_btnApproveLoanActionPerformed

    private void btnRejectLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRejectLoanActionPerformed
        int selectedRow = loanTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a loan to reject.");
            return;
        }

        int loanId = (int) loanTable.getValueAt(selectedRow, 0);

        try {
            String update = "UPDATE loans SET status = 'rejected' WHERE loan_id = ?";
            PreparedStatement pst = conn.prepareStatement(update);
            pst.setInt(1, loanId);
            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Loan rejected.");
            loadLoans(); // Refresh the table

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred. Try again.");
        }
    }//GEN-LAST:event_btnRejectLoanActionPerformed

    private void btnPerformTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerformTransactionActionPerformed
        String accountIdText = txtAccountId.getText().trim();
        String amountText = txtAmount.getText().trim();
        String type = cmbTransactionType.getSelectedItem().toString().toLowerCase();

        if (accountIdText.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        int accountId = Integer.parseInt(accountIdText);
        double amount = Double.parseDouble(amountText);

        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Database Connection not available");
            return;
        }

        try {
            conn.setAutoCommit(false); // Start transaction

            // Get current balance
            String balanceQuery = "SELECT balance FROM accounts WHERE account_id = ?";
            PreparedStatement pst1 = conn.prepareStatement(balanceQuery);
            pst1.setInt(1, accountId);
            ResultSet rs = pst1.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "Account not found.");
                return;
            }

            double currentBalance = rs.getDouble("balance");

            if (type.equals("withdraw") && currentBalance < amount) {
                JOptionPane.showMessageDialog(this, "Insufficient balance.");
                return;
            }

            // Update balance
            String updateBalance = "UPDATE accounts SET balance = balance "
                    + (type.equals("deposit") ? "+ ?" : "- ?") + " WHERE account_id = ?";
            PreparedStatement pst2 = conn.prepareStatement(updateBalance);
            pst2.setDouble(1, amount);
            pst2.setInt(2, accountId);
            pst2.executeUpdate();

            // Insert transaction record
            String insertTransaction = "INSERT INTO transactions (account_id, transaction_type, amount) VALUES (?, ?, ?)";
            PreparedStatement pst3 = conn.prepareStatement(insertTransaction);
            pst3.setInt(1, accountId);
            pst3.setString(2, type);
            pst3.setDouble(3, amount);
            pst3.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);

            JOptionPane.showMessageDialog(this, "Transaction successful!");
            loadTransactions(); // refresh table

        } catch (Exception e) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred. Please try again.");
    }

    }//GEN-LAST:event_btnPerformTransactionActionPerformed

    private void btnDeleteLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteLoanActionPerformed
        btnDeleteLoan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteLoan();
            }
        });

    }//GEN-LAST:event_btnDeleteLoanActionPerformed

    private void btnDeleteAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAccountActionPerformed
        btnDeleteAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteAccount();
            }
        });

    }//GEN-LAST:event_btnDeleteAccountActionPerformed

    private void btnDeleteTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTransactionActionPerformed
        btnDeleteTransaction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteTransaction();
            }
        });

    }//GEN-LAST:event_btnDeleteTransactionActionPerformed

    private void cmbTransactionTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTransactionTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTransactionTypeActionPerformed

    
    private void deleteTransaction() {
    int selectedRow = transactionsTable.getSelectedRow(); // Your transaction JTable name
    if (selectedRow != -1) {
        int transactionId = (int) transactionsTable.getValueAt(selectedRow, 0); // assuming ID in col 0

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM transactions WHERE transaction_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, transactionId);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Transaction deleted.");
                loadTransactions(); // Refresh
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Select a transaction to delete.");
    }
}

    
    private void deleteAccount() {
    int selectedRow = accountsTable.getSelectedRow(); // Your account JTable name
    if (selectedRow != -1) {
        int accountId = (int) accountsTable.getValueAt(selectedRow, 0);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM accounts WHERE account_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, accountId);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Account deleted.");
                loadAccountsIntoTable(); // Refresh
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Select an account to delete.");
    }
}

    
    private void deleteLoan() {
    int selectedRow = loanTable.getSelectedRow(); // Your loan JTable name
    if (selectedRow != -1) {
        int loanId = (int) loanTable.getValueAt(selectedRow, 0);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM loans WHERE loan_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, loanId);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Loan deleted.");
                loadLoans(); // Refresh
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } else {
        JOptionPane.showMessageDialog(this, "Select a loan to delete.");
    }
}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Set the look and feel (optional)
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainDashboard().setVisible(true);
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> accountTypeComboBox;
    private javax.swing.JTable accountsTable;
    private javax.swing.JTextField amountField;
    private javax.swing.JButton btnApproveLoan;
    private javax.swing.JButton btnDeleteAccount;
    private javax.swing.JButton btnDeleteLoan;
    private javax.swing.JButton btnDeleteTransaction;
    private javax.swing.JButton btnPerformTransaction;
    private javax.swing.JButton btnRejectLoan;
    private javax.swing.JButton btnSearchAccount;
    private javax.swing.JComboBox<String> cmbTransactionType;
    private javax.swing.JButton createAccountButton;
    private javax.swing.JTextField durationField;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField fullNameField;
    private javax.swing.JTextField interestRateField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField loanAccountIdField;
    private javax.swing.JTable loanTable;
    private javax.swing.JTextField phoneField;
    private javax.swing.JTextField startingBalanceField;
    private javax.swing.JButton submitLoanButton;
    private javax.swing.JTable transactionsTable;
    private javax.swing.JTextField txtAccountId;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearchID;
    // End of variables declaration//GEN-END:variables
}
