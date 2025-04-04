
public class Administrator extends User {

    public Administrator(String userName, String password, String email) {
        super(userName, password, email);
    }

    public void changeSystemPassword(Amazon amazon, String newPassword) {
        amazon.setSystemPassword(newPassword);
        System.out.println("System password changed successfully!");
    }

    public void viewAllUsers(Customers customers, Producers producers) {
        System.out.println("\n--- ALL CUSTOMERS ---");
        for (Customer customer : customers.getCustomerList()) {
            System.out.println(customer);
            System.out.println("------------------");
        }

        System.out.println("\n--- ALL PRODUCERS ---");
        for (Producer producer : producers.getProducerList()) {
            System.out.println(producer);
            System.out.println("------------------");
        }
    }
}
