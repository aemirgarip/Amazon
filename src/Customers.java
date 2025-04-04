
import java.util.ArrayList;
import java.util.List;

public class Customers {

    private List<Customer> customerList;

    public Customers() {
        this.customerList = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        customerList.add(customer);
    }

    public Customer getCustomer(String username) {
        for (Customer customer : customerList) {
            if (customer.getUserName().equalsIgnoreCase(username)) {
                return customer;
            }
        }
        return null;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

}
