
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private static int nextID = 1;
    private final int cartID;
    private List<Product> products;
    private List<Integer> quantities;

    public ShoppingCart() {
        this.cartID = nextID++;
        this.products = new ArrayList<>();
        this.quantities = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        if (product != null && quantity > 0) {
            Catalog catalog = Catalog.getInstance();

            if (product.getAmount() >= quantity) {
                products.add(product);
                quantities.add(quantity);
                product.setAmount(product.getAmount() - quantity);
                System.out.println(quantity + " " + product.getName() + " added to cart.");
            } else {
                System.out.println("Not enough stock for " + product.getName());
            }
        }
    }

    public void removeProduct(Product product) {
        int index = products.indexOf(product);
        if (index != -1) {
            products.remove(index);
            quantities.remove(index);
            System.out.println(product.getName() + " removed from the cart.");
        }
    }

    public void clearCart() {
        products.clear();
        quantities.clear();
        System.out.println("Your cart is emptied.");
    }

    public Order giveOrder(Customer customer, String shippingAddress, String paymentMethod) {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty, no order can be placed.");
            return null;
        }

        Order newOrder = new Order(customer, shippingAddress, paymentMethod);

        for (int i = 0; i < products.size(); i++) {
            newOrder.addProduct(products.get(i), quantities.get(i));
        }

        newOrder.completeOrder();
        clearCart();
        System.out.println("Your order has been created!\n" + newOrder);
        return newOrder;
    }

    public void updateQuantity(Product product, int newQuantity) {
        int index = products.indexOf(product);
        if (index != -1 && newQuantity > 0) {
            Catalog catalog = Catalog.getInstance();
            int currentQuantity = quantities.get(index);
            int stockAvailable = product.getAmount() + currentQuantity;

            if (newQuantity <= stockAvailable) {
                product.setAmount(stockAvailable - newQuantity);
                quantities.set(index, newQuantity);
                System.out.println("Updated " + product.getName() + " quantity to " + newQuantity);
            } else {
                System.out.println("Not enough stock to update quantity.");
            }
        }
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public double calculateTotal() {
        double total = 0;
        for (int i = 0; i < products.size(); i++) {
            total += products.get(i).getPrice() * quantities.get(i);
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Your Shopping Cart: ").append("\n");
        for (int i = 0; i < products.size(); i++) {
            sb.append(products.get(i).getName())
                    .append(" (#")
                    .append(products.get(i).getProductID())
                    .append(")")
                    .append(" x").append(quantities.get(i))
                    .append(" = ").append(products.get(i).getPrice() * quantities.get(i))
                    .append(" $\n");
        }
        sb.append("Total: ").append(calculateTotal()).append(" $");
        return sb.toString();
    }

    public int getCartID() {
        return cartID;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }
}
