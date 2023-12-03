class WithdrawalTransaction implements BankingTransaction {
    private double amount;

    public WithdrawalTransaction(double amount) {
        this.amount = amount;
    }

    @Override
    public void execute(Customer customer) {
        if (amount <= customer.savings) {
            customer.savings -= amount;
            System.out.println("Withdraw successful. New savings balance: $" + customer.savings);
            customer.transactionHistory.add(new Transaction(customer.transactionHistory.size() + 1, "Withdrawal", amount));
        }
        else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }
}
