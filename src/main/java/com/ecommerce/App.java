package com.ecommerce;

import com.ecommerce.factory.ServiceFactory;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.model.Subscription;
import com.ecommerce.service.CustomerService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.SubscriptionService;

import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) {
        // Initialize services using the factory
        CustomerService customerService = ServiceFactory.getCustomerService();
        ProductService productService = ServiceFactory.getProductService();
        SubscriptionService subscriptionService = ServiceFactory.getSubscriptionService();

        try {
            // Adding a customer
            addCustomer(customerService);

            // Managing customer subscriptions and orders
            manageCustomerSubscriptions(customerService);

            // Adding a product
            addProduct(productService);

            // Managing product subscriptions
            manageProductSubscription(subscriptionService);

        } catch (Exception e) {
            // Print error message in case of an exception
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static void addCustomer(CustomerService customerService) {
        // Create a new customer
        Customer customer = new Customer("Vikas Singh", "987.s345ingh@gmail.com", "hc1234", "Pune", "1234567890");
        // Register the customer
        customerService.registerCustomer(customer);
        System.out.println("Customer added: " + customer.getName());
    }

    private static void manageCustomerSubscriptions(CustomerService customerService) throws Exception {
        // Activate a subscription
        customerService.activateSubscription(10);
        // Deactivate a subscription
        customerService.deactivateSubsciption(12);
        // Place an order and check the status
        boolean status = customerService.placeOrder(1, "21-08-2024", "23-08-2024", "PENDING", 20, 21);
        System.out.println("Order placed with status: " + status);
    }

    private static void addProduct(ProductService productService) {
        // Create a new product
        Product product = new Product(1, "Coffee", "Coffee from CCD", 30, true);
        // Add the product
        productService.addProduct(product);
        System.out.println("Product added: " + product.getName());

        // Update the product
        productService.updateProduct(product);

        // Retrieve and print all products
        List<Product> plist = productService.getAllProducts();
        System.out.println("All products are: ");
        plist.forEach(p -> System.out.println(p));

        // Retrieve and print a product by its ID
        Product productWithPid12 = productService.getProductById(12);
        System.out.println(productWithPid12);

        // Delete a product
        productService.deleteProduct(20);
    }

    private static void manageProductSubscription(SubscriptionService subscriptionService) throws Exception {
        // Subscribe a customer to a product
        Subscription s = subscriptionService.subscribeProduct(1, 2, "weekly");
        System.out.println(s);

        // Activate a subscription
        subscriptionService.activateSubscription(10);
        // Deactivate a subscription
        subscriptionService.deactivateSubscription(10);

        // Retrieve and print active subscriptions
        List<Subscription> activeSubscriptionList = subscriptionService.getActiveSubscriptions();
        System.out.println("Active subscriptions:");
        activeSubscriptionList.forEach(System.out::println);

        // Retrieve and print inactive subscriptions
        List<Subscription> inActiveSubscriptionList = subscriptionService.getInactiveSubscriptions();
        System.out.println("Inactive subscriptions:");
        inActiveSubscriptionList.forEach(System.out::println);
    }
}
