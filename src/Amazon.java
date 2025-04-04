
import java.util.Scanner;

public class Amazon {

    private String systemID;
    private String systemPassword;
    private Customers customers;
    private Producers producers;
    private Scanner sc;
    private AuthManager authManager;
    private Menu menu;
    private Administrator admin;

    public Amazon(String systemID, String systemPassword) {
        this.systemID = systemID;
        this.systemPassword = systemPassword;
        this.customers = new Customers();
        this.producers = new Producers();
        this.sc = new Scanner(System.in);
        this.admin = new Administrator(systemID, systemPassword, "admin@amazon.com");
        this.authManager = new AuthManager(customers, producers, admin, sc);
        this.menu = new Menu(authManager, sc, this);
    }

    public static void main(String[] args) {
        Amazon amazon = new Amazon("admin", "admin123");
        amazon.menu.startSystem();
    }

    public String getSystemID() {
        return systemID;
    }

    public String getSystemPassword() {
        return systemPassword;
    }

    public void setSystemPassword(String systemPassword) {
        this.systemPassword = systemPassword;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public Customers getCustomers() {
        return customers;
    }

    public Producers getProducers() {
        return producers;
    }

}
