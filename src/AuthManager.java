
import java.util.HashSet;
import java.util.Scanner;

public class AuthManager {

    private static HashSet<String> allUsernames = new HashSet<>();
    private Customers customers;
    private Producers producers;
    private Administrator admin;
    private Scanner sc;

    public AuthManager(Customers customers, Producers producers, Administrator admin, Scanner sc) {
        this.customers = customers;
        this.producers = producers;
        this.admin = admin;
        this.sc = sc;
        allUsernames.add(admin.getUserName().toLowerCase());
    }

    public static boolean isUsernameTaken(String username) {
        return allUsernames.contains(username.toLowerCase());
    }

    public Administrator loginAdmin() {
        System.out.print("Enter admin username: ");
        String username = sc.nextLine();
        System.out.print("Enter admin password: ");
        String password = sc.nextLine();

        if (username.equals(admin.getUserName()) && password.equals(admin.getPassword())) {
            System.out.println("Admin login successful!");
            return admin;
        }
        System.out.println("Invalid admin credentials.");
        return null;
    }

    public Customer registerCustomer() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        if (isUsernameTaken(username)) {
            System.out.println("Username already taken.");
            return null;
        }
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();

        Customer customer = new Customer(name, username, password, email);
        customers.addCustomer(customer);
        allUsernames.add(username.toLowerCase());
        System.out.println("Customer registered successfully!");
        return customer;
    }

    public Producer registerProducer() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        if (isUsernameTaken(username)) {
            System.out.println("Username already taken.");
            return null;
        }
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        System.out.print("Enter email: ");
        String email = sc.nextLine();

        Producer producer = new Producer(name, username, password, email);
        producers.addProducer(producer);
        allUsernames.add(username.toLowerCase());
        System.out.println("Producer registered successfully!");
        return producer;
    }

    public Customer loginCustomer() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        Customer customer = customers.getCustomer(username);
        if (customer != null && customer.verify(username, password)) {
            System.out.println("Login successful!");
            return customer;
        }
        System.out.println("Invalid username or password.");
        return null;
    }

    public Producer loginProducer() {
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        Producer producer = producers.getProducer(username);
        if (producer != null && producer.verify(username, password)) {
            System.out.println("Login successful!");
            return producer;
        }
        System.out.println("Invalid username or password.");
        return null;
    }
}
