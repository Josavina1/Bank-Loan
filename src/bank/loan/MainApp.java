package bank.loan;

public class MainApp {
      public static void main(String[] args) {
        if (DatabaseConnection.getConnection() != null) {
            System.out.println("✅ Connected to the database!");
        } else {
            System.out.println("❌ Connection failed.");
        }
    
    
       java.awt.EventQueue.invokeLater(() -> {
           new MainDashboard().setVisible(true);
        });
}
}
