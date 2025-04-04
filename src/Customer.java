// Customer.java

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Customer extends User {

    private List<Order> orderHistory = new ArrayList<>();
    private String name;
    private String address;
    private String paymentDetails;
    private ShoppingCart shoppingCart;
    private Scanner sc;

    public Customer(String name, String userName, String password, String email) {
        super(userName, password, email);
        this.name = name;
        this.sc = new Scanner(System.in);
        this.shoppingCart = new ShoppingCart();
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    public void placeOrder(ShoppingCart cart, String address, String paymentMethod) {
        Order newOrder = new Order(this, address, paymentMethod);
        for (int i = 0; i < cart.getProducts().size(); i++) {
            newOrder.addProduct(cart.getProducts().get(i), cart.getQuantities().get(i));
        }
        newOrder.completeOrder();
        orderHistory.add(newOrder);
        cart.clearCart();
        System.out.println("Order placed successfully! Order ID: " + newOrder.getOrderID());
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void viewCart() {
        System.out.println("\n--- YOUR SHOPPING CART ---");
        System.out.println(shoppingCart);
        System.out.println("--------------------------");
    }

    @Override
    public String toString() {
        String addressStr = (this.address == null) ? "" : this.address;
        String paymentStr = (paymentDetails == null) ? "" : paymentDetails;
        return "Name: " + name + "\n"
                + super.toString() + "\n"
                + "Address: " + addressStr + "\n"
                + "Payment Details: " + paymentStr;
    }

    public boolean addToCart(Product product, int quantity) {
        if (product != null && quantity > 0 && product.getAmount() >= quantity) {
            shoppingCart.addProduct(product, quantity);
            System.out.println(product.getName() + " added to cart.");
            return true;
        } else {
            System.out.println("Failed to add product to cart.");
            return false;
        }
    }

    public boolean removeFromCart(Product product) {
        if (shoppingCart.getProducts().contains(product)) {
            shoppingCart.removeProduct(product);
            System.out.println(product.getName() + " removed from cart.");
            return true;
        } else {
            System.out.println("Product not found in cart.");
            return false;
        }
    }

    public boolean changeName() {
        while (true) {
            try {
                System.out.println("\nHello " + this.getUserName()
                        + ", how would you prefer your name to be updated? "
                        + "\n(Enter 'exit' to quit.)");
                String newName = sc.nextLine();
                if (newName.equalsIgnoreCase("exit")) {
                    return false;
                } else {
                    System.out.println("Name changed successfully.");
                    this.name = newName;
                    return true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                sc.nextLine();
            }
        }
    }

    public boolean changeAddress() {

        while (true) {
            try {
                System.out.println("\nHello " + this.getUserName()
                        + ", please enter your new address:"
                        + "\n(Enter 'exit' to quit.)");
                String newAddress = sc.nextLine();
                if (newAddress.equalsIgnoreCase("exit")) {
                    return false;
                } else {
                    this.address = newAddress;
                    return true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                sc.nextLine();
            }
        }
    }

    public boolean changePayment() {

        while (true) {
            try {
                System.out.println("\nHello " + this.getUserName()
                        + ", please enter your payment details:"
                        + "\n(Enter 'exit' to quit.)");
                String newPayment = sc.nextLine();
                if (newPayment.equalsIgnoreCase("exit")) {
                    return false;
                } else {
                    this.paymentDetails = newPayment;
                    return true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                sc.nextLine();
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }
    
    

}
