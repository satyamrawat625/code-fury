package com.ecommerce;

import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.model.Subscription;
import com.ecommerce.service.CustomerService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.SubscriptionService;
import com.ecommerce.service.impl.CustomerServiceImpl;
import com.ecommerce.service.impl.ProductServiceImpl;
import com.ecommerce.service.impl.SubscriptionServiceImpl;

public class App {
    public static void main(String[] args) {
        try {
            // Initialize services
            CustomerService customerService = new CustomerServiceImpl();
            ProductService productService = new ProductServiceImpl();
            SubscriptionService subscriptionService = new SubscriptionServiceImpl();

            // Adding a customer
            Customer customer = new Customer("Vikas Singh", "987.s345ingh@gmail.com", "hc1234","Pune","1234567890");
            customerService.registerCustomer(customer);

            customerService.activateSubscription(10);
            customerService.deactivateSubsciption(10);
            boolean status=customerService.placeOrder(1,"21-08-2024","23-08-2024","PENDING",20,21);


            // Adding a product
            Product product = new Product(1,"Coffee","Coffee from CCD",30,true);
            productService.addProduct(product);

            // Subscribing to a product
            Subscription subscription = new Subscription();
//            subscription.setCustomer(customer);
//            subscription.setProduct(product);
//            subscription.setFrequency("biweekly");
//            subscription.setActive(true);
            subscriptionService.activateSubscription(10);

            // Finding a subscription by ID
//            Subscription foundSubscription = subscriptionService.getSubscriptionById(subscription.getId());
//            System.out.println(foundSubscription);
//            System.out.println("Subscription ID: " + foundSubscription.getId());
//            System.out.println("Customer: " + foundSubscription.getCustomer().getName());
//            System.out.println("Product: " + foundSubscription.getProduct().getName());
//            System.out.println("Frequency: " + foundSubscription.getFrequency());
//            System.out.println("Active: " + foundSubscription.isActive());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
