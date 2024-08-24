package com.ecommerce;

import com.ecommerce.factory.ServiceFactory;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.model.Subscription;
import com.ecommerce.service.CustomerService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.SubscriptionService;

import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        // Initialize services using the factory
        CustomerService customerService = ServiceFactory.getCustomerService();
        ProductService productService = ServiceFactory.getProductService();
        SubscriptionService subscriptionService = ServiceFactory.getSubscriptionService();

        try {
            // Adding a customer
            addCustomer(customerService);

            // Managing subscriptions and orders
            manageCustomerSubscriptions(customerService);

            // Adding a product
            addProduct(productService);

            // Managing subscriptions
            manageProductSubscription(subscriptionService);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private static void addCustomer(CustomerService customerService) {
        Customer customer = new Customer("Vikas Singh", "987.s345ingh@gmail.com", "hc1234", "Pune", "1234567890");
        customerService.registerCustomer(customer);
        System.out.println("Customer added: " + customer.getName());
    }

    private static void manageCustomerSubscriptions(CustomerService customerService) throws Exception {
        customerService.activateSubscription(10);
        customerService.deactivateSubsciption(10);
        boolean status = customerService.placeOrder(1, "21-08-2024", "23-08-2024", "PENDING", 20, 21);
        System.out.println("Order placed with status: " + status);
    }

    private static void addProduct(ProductService productService) {
        Product product = new Product(1, "Coffee", "Coffee from CCD", 30, true);
        productService.addProduct(product);
        System.out.println("Product added: " + product.getName());
    }

    private static void manageProductSubscription(SubscriptionService subscriptionService) throws Exception {
        Subscription subscription = new Subscription();
        // Example data to be set; uncomment and set appropriate values
        // subscription.setCustomer(customer);
        // subscription.setProduct(product);
        // subscription.setFrequency("biweekly");
        // subscription.setActive(true);
        subscriptionService.activateSubscription(10);

        // Uncomment if you need to find and print subscription details
        // Subscription foundSubscription = subscriptionService.getSubscriptionById(subscription.getId());
        // System.out.println("Subscription ID: " + foundSubscription.getId());
        // System.out.println("Customer: " + foundSubscription.getCustomer().getName());
        // System.out.println("Product: " + foundSubscription.getProduct().getName());
        // System.out.println("Frequency: " + foundSubscription.getFrequency());
        // System.out.println("Active: " + foundSubscription.isActive());
    }
}
