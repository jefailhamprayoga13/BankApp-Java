import java.util.*;

class Customer extends User {
    public List<Transaction> transactionHistory;
    public List<BankingTransaction> transactions;
    public double savings;
    public double loanAmount;
    public boolean loanApproved;

    public Account account;

    public Customer(String name, String username, String password) {
        super(name, username, password);
        this.transactionHistory = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.savings = 0.0;
        this.loanAmount = 0.0;
        this.loanApproved = false;
        
        this.account = new Account(1, 0.0);
    }
}