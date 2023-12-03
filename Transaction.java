class Transaction {
    public int transactionId;
    public String type;
    public double amount;

    public Transaction(int transactionId, String type, double amount) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
    }
}