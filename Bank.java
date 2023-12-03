import java.util.*;

class Bank {
    public List<Customer> customers;
    public List<Transaction> allTransactions;
    public List<User> users;

    public Bank() {
        this.customers = new ArrayList<>();
        this.users = new ArrayList<>();
        this.allTransactions = new ArrayList<>();
    }

    public void registerCustomer(Customer customer) {
        users.add(customer);
        customers.add(customer);
    }

    public void registerAdmin(Admin admin) {
        users.add(admin);
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user instanceof Admin && user.username.equals(username) && user.password.equals(password)) {
                return user;
            } else if (user instanceof Customer && user.username.equals(username) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void recordTransaction(Transaction transaction) {
        allTransactions.add(transaction);
    }
}