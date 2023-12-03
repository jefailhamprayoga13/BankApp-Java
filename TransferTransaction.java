class TransferTransaction implements BankingTransaction {
    private String recipientUsername;
    private double amount;

    public TransferTransaction(String recipientUsername, double amount) {
        this.recipientUsername = recipientUsername;
        this.amount = amount;
    }

    @Override
    public void execute(Customer customer) {
        Customer recipient = findCustomerByUsername(recipientUsername);

        if (recipient != null && amount > 0 && amount <= customer.savings) {
            
            customer.savings -= amount;
            recipient.savings += amount;

            Transaction senderTransaction = new Transaction(customer.transactionHistory.size() + 1, "Transfer to " + recipient.name, amount);
            customer.transactionHistory.add(senderTransaction);

            Transaction recipientTransaction = new Transaction(recipient.transactionHistory.size() + 1, "Transfer from " + customer.name, amount);
            recipient.transactionHistory.add(recipientTransaction);

            System.out.println("Transfer successful. New savings balance: $" + customer.savings);
        } else {
            System.out.println("Invalid transfer. Please check the recipient username and ensure sufficient funds.");
        }
    }

    private Customer findCustomerByUsername(String username) {
        for (Customer customer : BankApplication.bank.customers) {
            if (customer.username.equals(username)) {
                return customer;
            }
        }
        return null;
    }
}
