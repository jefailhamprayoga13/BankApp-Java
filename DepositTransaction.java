class DepositTransaction implements BankingTransaction {
    private double amount;

    public DepositTransaction(double amount) {
        this.amount = amount;
    }

    @Override
    public void execute(Customer customer) {
        if(amount > 0){
            customer.savings += amount;
            System.out.println("Deposit successful. New savings balance: $" + customer.savings);
            customer.transactionHistory.add(new Transaction(customer.transactionHistory.size() + 1, "Deposit", amount));
        }
        else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }
}