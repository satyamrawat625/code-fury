package com.ecommerce;

import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.exception.QtyNotValidException;
import com.ecommerce.exception.SubscriptionNotFoundException;
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

            // Adding a customer
            addCustomer(customerService);

            // Managing customer subscriptions and orders
            manageCustomerSubscriptions(customerService);

            // Adding a product
            addProduct(productService);

            // Managing product subscriptions
            manageProductSubscription(subscriptionService);
        System.out.println("hii");
    }

    private static void addCustomer(CustomerService customerService) {
        // Create a new customer
        Customer customer = new Customer("Vikas Singh", "987.s345ingh@gmail.com", "hc1234", "Pune", "1234567890");
        // Register the customer

        try {
            customerService.registerCustomer(customer);
            System.out.println("Customer added: " + customer.getName());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void manageCustomerSubscriptions(CustomerService customerService) {
//        // Activate a subscription
        try{
            customerService.activateSubscription(10);
            System.out.println("Subscription activated");
        }catch (SubscriptionNotFoundException e){
            e.getMessage();
        }

//        // Deactivate a subscription
        try{
            customerService.deactivateSubsciption(12);
            System.out.println("Subscription deactivated");
        }catch (SubscriptionNotFoundException e){
            e.getMessage();
        }
        // Place an order and check the status
        boolean status = false;
        try {
            status = customerService.placeOrder(1, 5,"21-08-2024", "23-08-2024", "PENDING", 20, 21);
            System.out.println("Order placed with status: " + status);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void addProduct(ProductService productService) {
        // Create a new product
        Product product = new Product(1, "Coffee", "Coffee from CCD", 30, true,4);
        // Add the product

        try{
            productService.addProduct(product);
            System.out.println("Product added: " + product.getName());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        // Update the product
        try {
            productService.updateProduct(product);
            System.out.println("Product " + product.getName()+ " updated");
        } catch (QtyNotValidException ex) {
            throw new RuntimeException(ex);
        }

        // Retrieve and print all products
        List<Product> plist = productService.getAllProducts();
        System.out.println("All products are: ");
        plist.forEach(p -> System.out.println(p));

        // Retrieve and print a product by its ID
        try {
            Product productWithPid12 = productService.getProductById(12);
            System.out.println(productWithPid12);
        } catch (ProductNotFoundException e) {
            System.err.println(e.getMessage());
        }

        // Delete a product
        try {
            productService.deleteProduct(20);
            System.out.println("Product deleted" );
        } catch (ProductNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void manageProductSubscription(SubscriptionService subscriptionService) {
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
