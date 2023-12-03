import java.util.*;

class BankApplication {
    public static Bank bank;
    private Scanner scanner;

    public BankApplication(Bank bank) {
        this.bank = bank;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the Bank Application!");

        while (true) {
            System.out.println("\n1. Register\n2. Login\n3. Exit");
            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Exiting the Bank Application. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void registerCustomer() {
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        Customer customer = new Customer(name, username, password);
        bank.registerCustomer(customer);
        System.out.println("Registration successful for customer: " + customer.name);
    }

    private void login() {  
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        User user = bank.login(username, password);

        if (user != null) {
            if (user instanceof Admin) {
                adminMenu((Admin) user);
            } else if (user instanceof Customer) {
                customerMenu((Customer) user);
            }
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void adminMenu(Admin admin) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. View Customer Info");
            System.out.println("2. Approve Loan");
            System.out.println("3. Logout");

            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewCustomerInfo();
                    break;
                case 2:
                    approveLoan();
                    break;
                case 3:
                    System.out.println("Logging out as admin.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void customerMenu(Customer customer) {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. View Account Details");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Apply for Loan");
            System.out.println("6. Logout");

            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAccountDetails(customer);
                    break;
                case 2:
                    deposit(customer);
                    break;
                case 3:
                    withdraw(customer);
                    break;
                case 4:
                    transfer(customer);
                    break;
                case 5:
                    applyForLoan(customer);
                    break;
                case 6:
                    System.out.println("Logging out as customer: " + customer.name);
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewAccountDetails(Customer customer) {
        System.out.println("\nAccount Details for customer: " + customer.name);
        System.out.println("Savings: $" + customer.savings);
        System.out.println("Loan Amount: $" + customer.loanAmount);
        System.out.println("Loan Status: " + (customer.loanApproved ? "Approved" : "Not Approved"));
        System.out.println("Transaction History:");
        for (Transaction transaction : customer.transactionHistory) {
            System.out.println("ID: " + transaction.transactionId + ", Type: " + transaction.type + ", Amount: $" + transaction.amount);
        }
    }

    private void deposit(Customer customer) {
        System.out.print("Enter deposit amount: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            BankingTransaction transaction = new DepositTransaction(amount);
            performTransaction(customer, transaction);
        } 
    }

    private void withdraw(Customer customer) {
        System.out.print("Enter withdrawal amount: $");
        double amount = scanner.nextDouble();

        if (amount > 0) {
            BankingTransaction transaction = new WithdrawalTransaction(amount);
            performTransaction(customer, transaction);
        } 
    }

    private void transfer(Customer customer) {
        System.out.print("Enter recipient username: ");
        String recipientUsername = scanner.next();

        Customer recipient = findCustomerByUsername(recipientUsername);

        if (recipient != null) {
            System.out.print("Enter transfer amount: $");
            double amount = scanner.nextDouble();

            if (amount > 0) {
                BankingTransaction transaction = new TransferTransaction(recipientUsername, amount);
                performTransaction(customer, transaction);
            } else {
                System.out.println("Invalid amount. Please enter a positive value.");
            }
        } else {
            System.out.println("Recipient not found. Please enter a valid username.");
        }
    }

    private void applyForLoan(Customer customer) {
        System.out.print("Enter loan amount: $");
        double loanAmount = scanner.nextDouble();

        if (loanAmount > 0) {
            BankingTransaction transaction = new LoanTransaction(loanAmount);
            performTransaction(customer, transaction);
            System.out.println("Loan request submitted. Waiting for approval.");
        } 
    }

    private void performTransaction(Customer customer, BankingTransaction transaction) {
        transaction.execute(customer);
        
        customer.transactions.add(transaction);
    }

    private void viewCustomerInfo() {
        System.out.println("\nCustomer Information:");
        for (Customer customer : bank.customers) {
            System.out.println("Name: " + customer.name + ", Username: " + customer.username);
            System.out.println("Transaction History:");
            for (Transaction transaction : customer.transactionHistory) {
                System.out.println("ID: " + transaction.transactionId + ", Type: " + transaction.type + ", Amount: $" + transaction.amount);
            }
            System.out.println("Loan Amount: $" + customer.loanAmount);
            System.out.println("Loan Status: " + (customer.loanApproved ? "Approved" : "Not Approved"));
            System.out.println("--------------");
        }
    }

    private void approveLoan() {
        System.out.print("Enter username of customer to approve loan: ");
        String customerUsername = scanner.next();

        Customer customer = findCustomerByUsername(customerUsername);

        if (customer != null) {
            if (!customer.loanApproved) {
                Admin admin = new Admin("Admin", "admin", "adminpass");
                admin.approveLoan(customer);
            } else {
                System.out.println("This customer already has an approved loan.");
            }
        } else {
            System.out.println("Customer not found. Please enter a valid username.");
        }
    }

    private Customer findCustomerByUsername(String username) {
        for (Customer customer : bank.customers) {
            if (customer.username.equals(username)) {
                return customer;
            }
        }
        return null;
    }
}