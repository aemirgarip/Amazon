// Producer.java

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Producer extends User {

    private String name;
    private String address;
    private List<Product> producedProducts;
    private Scanner sc;

    public Producer(String name, String userName, String password, String email) {
        super(userName, password, email);
        this.name = name;
        this.producedProducts = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    public void addProductToCatalog() {

        Product newProduct = createProduct();
        if (newProduct != null) {
            Catalog.getInstance().addProduct(newProduct);
        }

    }

    public void editProductInCatalog() {
        listProducedProducts();
        System.out.print("Enter product ID to edit: ");
        int id = sc.nextInt();
        sc.nextLine();

        Product product = findProductById(id);
        if (product != null) {
            System.out.print("Enter new price (current: " + product.getPrice() + "): ");
            double newPrice = sc.nextDouble();
            System.out.print("Enter new amount (current: " + product.getAmount() + "): ");
            int newAmount = sc.nextInt();
            sc.nextLine();

            product.setPrice(newPrice);
            product.setAmount(newAmount);
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found!");
        }
    }

    private Product findProductById(int id) {
        for (Product p : producedProducts) {
            if (p.getProductID() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String addressStr = (this.address == null) ? "" : this.address;
        return "Name: " + name + "\n"
                + super.toString() + "\n"
                + "Address: " + addressStr;
    }

    public boolean changeAddress() {
        while (true) {
            try {
                System.out.println("\nHello " + this.getUserName()
                        + ", please enter your new address"
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
                    this.name = newName;
                    System.out.println("Name changed successfully.");
                    return true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                sc.nextLine();
            }
        }
    }

    public Product createProduct() {
        System.out.print("Enter your product name: ");
        String name = sc.nextLine();
        System.out.print("Enter your product price: ");
        double price = sc.nextDouble();
        sc.nextLine();
        System.out.print("Enter your product amount: ");
        int amount = sc.nextInt();
        sc.nextLine();
        Product newProduct = new Product(name, price, amount);
        producedProducts.add(newProduct);
        return newProduct;
    }

    public void listProducedProducts() {
        System.out.println("\n" + name + " products produced by: ");
        for (Product product : producedProducts) {
            System.out.println(product);
        }
    }

    public String getName() {
        return name;
    }
}
