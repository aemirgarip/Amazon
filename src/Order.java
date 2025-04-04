
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Order {

    private static int nextOrderID = 1;
    private final int orderID;
    private Customer customer;
    private Date orderDate;
    private String shippingAddress;
    private String paymentMethod;
    private List<Product> orderedProducts;
    private List<Integer> quantities;
    private double totalAmount;
    private String orderStatus;

    public Order(Customer customer, String shippingAddress, String paymentMethod) {
        this.orderID = nextOrderID++;
        this.customer = customer;
        this.orderDate = new Date();
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.orderedProducts = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.orderStatus = "Processing";
        
        if (customer.getAddress() == null || customer.getPaymentDetails() == null) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter shipping address: ");
            String address = scanner.nextLine();
            if (address.isEmpty()) {
                throw new IllegalArgumentException("Shipping address cannot be empty!");
            }
            customer.setAddress(address);

            System.out.print("Enter payment method: ");
            String payment = scanner.nextLine();
            if (payment.isEmpty()) {
                throw new IllegalArgumentException("Payment method cannot be empty!");
            }
            customer.setPaymentDetails(payment);
        }

        this.shippingAddress = customer.getAddress();
        this.paymentMethod = customer.getPaymentDetails();
    }

    public void addProduct(Product product, int quantity) {
        if (product != null && quantity > 0) {
            orderedProducts.add(product);
            quantities.add(quantity);
            totalAmount += product.getPrice() * quantity;
        }
    }

    public void completeOrder() {
        this.orderStatus = "Completed";
    }

    public void cancelOrder() {
        this.orderStatus = "Cancelled";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderID).append("\n");
        sb.append("Customer: ").append(customer.getName()).append("\n");
        sb.append("Order Date: ").append(orderDate).append("\n");
        sb.append("Shipping Address: ").append(shippingAddress).append("\n");
        sb.append("Payment Method: ").append(paymentMethod).append("\n");
        sb.append("Status: ").append(orderStatus).append("\n\n");
        sb.append("Order Details:\n");

        for (int i = 0; i < orderedProducts.size(); i++) {
            sb.append(orderedProducts.get(i).getName())
                    .append(" x").append(quantities.get(i))
                    .append(" = $").append(orderedProducts.get(i).getPrice() * quantities.get(i))
                    .append("\n");
        }

        sb.append("\nTotal Amount: $").append(totalAmount);
        return sb.toString();
    }

    public int getOrderID() {
        return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}
