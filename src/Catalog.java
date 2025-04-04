
import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private static Catalog instance;
    private List<Product> products;

    private Catalog() {
        products = new ArrayList<>();
    }

    public static Catalog getInstance() {
        if (instance == null) {
            instance = new Catalog();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added to catalog: " + product.getName());
    }

    public void displayProducts() {
        System.out.println("\n--- CATALOG ---");
        for (Product product : products) {
            System.out.println(product + " | Stock: " + product.getAmount());
        }
        System.out.println("---------------");
    }

    public Product getProductById(int id) {
        for (Product product : products) {
            if (product.getProductID() == id) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }
}
