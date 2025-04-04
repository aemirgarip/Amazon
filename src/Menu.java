
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private final AuthManager authManager;
    private final Scanner sc;
    private final Amazon amazon;

    public Menu(AuthManager authManager, Scanner sc, Amazon amazon) {
        this.authManager = authManager;
        this.sc = sc;
        this.amazon = amazon;
    }

    public void startSystem() {
        System.out.println("Amazon");
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n1. Register as Customer");
            System.out.println("2. Register as Producer");
            System.out.println("3. Login as Customer");
            System.out.println("4. Login as Producer");
            System.out.println("5. Login as Admin");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        authManager.registerCustomer();
                        break;
                    case 2:
                        authManager.registerProducer();
                        break;
                    case 3:
                        Customer customer = authManager.loginCustomer();
                        if (customer != null) {
                            menu_c(customer);
                        }
                        break;
                    case 4:
                        Producer producer = authManager.loginProducer();
                        if (producer != null) {
                            menu_p(producer);
                        }
                        break;
                    case 5:
                        Administrator admin = authManager.loginAdmin();
                        if (admin != null) {
                            menu_admin(admin);
                        }
                        break;
                    case 6:
                        System.out.println("Good bye...");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a number between 1-6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1-6.");
                sc.nextLine();
            }
        }
    }

    private void menu_p(Producer producer) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n1. Look Catalog");
            System.out.println("2. Add Your Product");
            System.out.println("3. Edit Your Products");
            System.out.println("4. Edit Profile");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        Catalog.getInstance().displayProducts();
                        break;
                    case 2:
                        producer.addProductToCatalog();
                        break;
                    case 3:
                        producer.editProductInCatalog();
                        break;
                    case 4:
                        menu_p_4(producer);
                        break;
                    case 5:
                        System.out.println("See you later!");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a number between 1-5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1-5.");
                sc.nextLine();
            }
        }
    }

    private void menu_p_4(Producer producer) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n1. Change Name");
            System.out.println("2. Change UserName");
            System.out.println("3. Change Address");
            System.out.println("4. Change Password");
            System.out.println("5. Change E-mail");
            System.out.println("6. Your Profile");
            System.out.println("7. Back");
            System.out.print("Choose an option: ");
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 7) {
                    isRunning = false;
                } else {
                    menu_p_4_action(producer, choice);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number between 1-7.");
                sc.nextLine();
            }
        }
    }

    private void menu_p_4_action(Producer producer, int choice) {
        switch (choice) {
            case 1:
                producer.changeName();
                break;
            case 2:
                producer.changeUserName();
                break;
            case 3:
                producer.changeAddress();
                break;
            case 4:
                System.out.println("\n1. Use old password");
                System.out.println("2. Use e-mail");
                System.out.println("3. Back");
                try {
                    int x = sc.nextInt();
                    switch (x) {
                        case 1:
                            producer.resetPassword_pw();
                            break;
                        case 2:
                            producer.resetPassword_mail();
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number between 1-3.");
                }
                break;
            case 5:
                producer.changeMail();
                break;
            case 6:
                System.out.println(producer.toString());
                ;
                break;
            default:
                System.out.println("Invalid option. Please enter a valid number between 1-7.");
        }
    }

    private void menu_c(Customer customer) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n1. Look Catalog");
            System.out.println("2. Shopping Cart");
            System.out.println("3. Orders");
            System.out.println("4. Edit Profile");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        displayCatalogAndAddToCart(customer);
                        break;
                    case 2:
                        handleShoppingCart(customer);
                        break;
                    case 3:
                        handleOrders(customer);
                        break;
                    case 4:
                        menu_c_4(customer);
                        break;
                    case 5:
                        System.out.println("See you later!");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please enter a number between 1-5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1-5.");
                sc.nextLine();
            }
        }
    }

    private void menu_c_4(Customer customer) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n1. Change Name");
            System.out.println("2. Change UserName");
            System.out.println("3. Change Address");
            System.out.println("4. Change Password");
            System.out.println("5. Change E-mail");
            System.out.println("6. Change Payment Details");
            System.out.println("7. Your Profile");
            System.out.println("8. Back");
            System.out.print("Choose an option: ");
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 8) {
                    isRunning = false;
                } else {
                    menu_c_4_action(customer, choice);
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number between 1-8.");
                sc.nextLine();
            }
        }
    }

    private void menu_c_4_action(Customer customer, int choice) {
        switch (choice) {
            case 1:
                customer.changeName();
                break;
            case 2:
                customer.changeUserName();
                break;
            case 3:
                customer.changeAddress();
                break;
            case 4:
                System.out.println("\n1. Use old password");
                System.out.println("2. Use e-mail");
                System.out.println("3. Back");
                try {
                    int x = sc.nextInt();
                    switch (x) {
                        case 1:
                            customer.resetPassword_pw();
                            break;
                        case 2:
                            customer.resetPassword_mail();
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number between 1-3.");
                }
                break;
            case 5:
                customer.changeMail();
                break;
            case 6:
                customer.changePayment();
                break;
            case 7:
                System.out.println(customer.toString());
                break;
            default:
                System.out.println("Invalid option. Please enter a valid number between 1-8.");
        }
    }

    private void displayCatalogAndAddToCart(Customer customer) {
        Catalog.getInstance().displayProducts();
        System.out.print("\nEnter product ID to add to cart (0 to cancel): ");
        int productId = sc.nextInt();
        if (productId == 0) {
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();

        Product product = Catalog.getInstance().getProductById(productId);
        if (product != null && product.getAmount() >= quantity) {
            customer.getShoppingCart().addProduct(product, quantity);
            System.out.println(quantity + " x " + product.getName() + " added to your cart.");
        } else {
            System.out.println("Invalid product or insufficient stock!");
        }
    }

    private void handleShoppingCart(Customer customer) {
        ShoppingCart cart = customer.getShoppingCart();
        boolean inCartMenu = true;

        while (inCartMenu) {
            System.out.println("\n--- YOUR SHOPPING CART ---");
            System.out.println(cart);
            System.out.println("\n1. Remove Product");
            System.out.println("2. Update Quantity");
            System.out.println("3. Proceed to Checkout");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose an option: ");

            try {
                int cartChoice = sc.nextInt();
                sc.nextLine();

                switch (cartChoice) {
                    case 1:
                        System.out.print("Enter product ID to remove: ");
                        int productId = sc.nextInt();
                        sc.nextLine();
                        Product productToRemove = Catalog.getInstance().getProductById(productId);
                        if (productToRemove != null) {
                            cart.removeProduct(productToRemove);
                        } else {
                            System.out.println("Product not found!");
                        }
                        break;
                    case 2:
                        System.out.print("Enter product ID to update: ");
                        int updateId = sc.nextInt();
                        System.out.print("Enter new quantity: ");
                        int newQty = sc.nextInt();
                        sc.nextLine();
                        cart.updateQuantity(Catalog.getInstance().getProductById(updateId), newQty);
                        break;
                    case 3:
                        if (!cart.getProducts().isEmpty()) {
                            System.out.print("Enter shipping address: ");
                            String address = sc.nextLine();
                            System.out.print("Enter payment method: ");
                            String payment = sc.nextLine();
                            customer.placeOrder(cart, address, payment);
                        } else {
                            System.out.println("Your cart is empty!");
                        }
                        break;
                    case 4:
                        inCartMenu = false;
                        break;
                    default:
                        System.out.println("Invalid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
                sc.nextLine();
            }
        }
    }

    private void handleOrders(Customer customer) {
        if (customer.getOrderHistory().isEmpty()) {
            System.out.println("\nYou have no orders yet.");
            return;
        }

        System.out.println("\n--- YOUR ORDER HISTORY ---");
        for (Order order : customer.getOrderHistory()) {
            System.out.println(order);
            System.out.println("----------------------");
        }

        System.out.println("\n1. View Order Details");
        System.out.println("2. Back to Main Menu");
        System.out.print("Choose an option: ");

        try {
            int orderChoice = sc.nextInt();
            sc.nextLine();

            if (orderChoice == 1) {
                System.out.print("Enter order ID: ");
                int orderId = sc.nextInt();
                sc.nextLine();
                Order selectedOrder = customer.getOrderHistory().stream()
                        .filter(o -> o.getOrderID() == orderId)
                        .findFirst()
                        .orElse(null);

                if (selectedOrder != null) {
                    System.out.println("\n--- ORDER DETAILS ---");
                    System.out.println(selectedOrder);
                } else {
                    System.out.println("Order not found!");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            sc.nextLine();
        }
    }

    private void menu_admin(Administrator admin) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n1. View All Users");
            System.out.println("2. Change System Password");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        admin.viewAllUsers(amazon.getCustomers(), amazon.getProducers());
                        break;
                    case 2:
                        System.out.print("Enter new system password: ");
                        String newPassword = sc.nextLine();
                        admin.changeSystemPassword(amazon, newPassword);
                        break;
                    case 3:
                        System.out.println("Logging out...");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input.");
                sc.nextLine();
            }
        }
    }
}
