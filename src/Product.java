
public class Product {

    private static int nextProductID = 1;

    private final int productID;
    private String name;
    private double price;
    private int amount;

    public Product(String name, double price, int amount) {
        this.productID = nextProductID++;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getProductID() {
        return productID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " (ID: " + productID + ") - $" + price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
