class Admin extends User {
    public Admin(String name, String username, String password) {
        super(name, username, password);
    }

    public void approveLoan(Customer customer) {
        customer.loanApproved = true;
        System.out.println("Loan approved for customer: " + customer.name);
    }
}