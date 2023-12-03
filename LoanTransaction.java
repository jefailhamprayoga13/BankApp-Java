class LoanTransaction implements BankingTransaction {
    private double amount;

    public LoanTransaction(double amount) {
        this.amount = amount;
    }

    @Override
    public void execute(Customer customer) {
        if (!customer.loanApproved) {
            Loan loan = new Loan(amount);
            customer.loanAmount = amount;
            customer.transactionHistory.add(new Transaction(customer.transactionHistory.size() + 1, "Loan Approval", amount));

            customer.transactions.add(this);
        }else {
            System.out.println("Invalid loan amount. Please enter a positive value.");
        }
    }
}