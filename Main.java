public class Main {
    public static void main(String[] args) {
        
        Bank bank = new Bank();
        Customer customer1 = new Customer("Prayoga", "prayoga", "1234");
        Customer customer2 = new Customer("Krisma", "krisma", "1234");
        Admin admin = new Admin("Jefa Ilham", "jefa", "123456789");

        bank.registerCustomer(customer1);
        bank.registerCustomer(customer2);
        bank.registerAdmin(admin);

        BankApplication bankApplication = new BankApplication(bank);
        bankApplication.start();
    }
}