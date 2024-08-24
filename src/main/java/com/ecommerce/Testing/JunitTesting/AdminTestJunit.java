package com.ecommerce.Testing.JunitTesting;

import com.ecommerce.model.Admin;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.model.Subscription;
import com.ecommerce.service.impl.AdminServiceImpl;
import com.ecommerce.service.impl.ProductServiceImpl;
import com.ecommerce.service.impl.SubscriptionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdminTestJunit
{
    private AdminServiceImpl adminService;
    private ProductServiceImpl productService;
    private SubscriptionServiceImpl subscriptionService;


    public AdminTestJunit() {
        adminService = new AdminServiceImpl();
        productService = new ProductServiceImpl();
        subscriptionService = new SubscriptionServiceImpl();
    }

    @Test
    void testRegisterAdmin()
    {
        Admin admin = new Admin("John Doe", "john.doe@example.com", "securePassword123", 1001, "Admin");

        // Call the service method
        Admin savedAdmin = adminService.registerAdmin(admin);

        // Validate the results
        assertNotNull(savedAdmin, "Saved admin should not be null");
        assertEquals("John Doe", savedAdmin.getName(), "Admin name should match");
    }

    @Test
    void testLoginAdmin()
    {
        // Create an admin for testing
        Admin admin = new Admin("John Doe", "john.doe@example.com", "securePassword123", 1001, "Admin");
        adminService.registerAdmin(admin);
        // Call the service method
        Admin loggedInAdmin = adminService.loginAdmin("john.doe@example.com", "securePassword123", 1001);
        // Validate the results
        assertNotNull(loggedInAdmin, "Logged in admin should not be null");
        assertEquals("John Doe", loggedInAdmin.getName(), "Admin name should match");
    }


    @Test
    void testAddProduct()
    {
        // Create a product for testing
        Product product = new Product(1, "Apple", "Fruit", 100, true);
        adminService.addProduct(product);
        // Call the service method
        Product savedProduct = productService.getProductById(1);
        // Validate the results
        assertNotNull(savedProduct, "Add product should not be null");
    }

    @Test
    void testUpdateProduct() {
        // Create a product for testing
        Product product = new Product(1, "Apple", "Fruit", 100, true);
        adminService.updateProduct(product);
        // Validate the results
        assertNotNull(product, "Update product should not be null");
        assertEquals("Apple", product.getName(), "Product name should match");

    }


    @Test
    void testDeleteProduct() {
        // Create a product for testing
        Product product = new Product(1, "Apple", "Fruit", 100, true);
        productService.addProduct(product);
        // Call the service method
        adminService.deleteProduct(1);
        // Validate the results
        assertNull(productService.getProductById(1), "Product should be deleted");
    }




    @Test
    void testAddSubscription() {
        Product product = new Product(1, "Apple", "Fruit", 100, true);
        Customer customer = new Customer("Vikas Singh", "987.s345ingh@gmail.com", "hc1234","Pune","1234567890");
        LocalDate localStartDate = LocalDate.of(2024, 8, 24);
        LocalDate localEndDate = LocalDate.of(2025, 8, 24);
        Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Subscription subscription = new Subscription(10, customer, product, "biweekly", startDate, endDate, true);
        Subscription sub=adminService.addSubscription(subscription);
        // Validate the results
        assertNotNull(sub, "Subscription should not be null");
    }


    @Test
    void testViewActiveSubscription()
    {
        Product product = new Product(1, "Apple", "Fruit", 100, true);
        Customer customer = new Customer("Vikas Singh", "987.s345ingh@gmail.com", "hc1234","Pune","1234567890");
        LocalDate localStartDate = LocalDate.of(2024, 8, 24);
        LocalDate localEndDate = LocalDate.of(2025, 8, 24);
        Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Subscription subscription = new Subscription(10, customer, product, "biweekly", startDate, endDate, true);
        Subscription sub=adminService.addSubscription(subscription);
        List<Subscription> subscriptions = adminService.viewActiveSubscriptions();

        assertNotNull(subscriptions, "Subscription should not be null");
    }


    @Test
    void testViewInactiveSubscription()
    {
        Product product = new Product(1, "Apple", "Fruit", 100, true);
        Customer customer = new Customer("Vikas Singh", "987.s345ingh@gmail.com", "hc1234", "Pune", "1234567890");
        LocalDate localStartDate = LocalDate.of(2024, 8, 24);
        LocalDate localEndDate = LocalDate.of(2025, 8, 24);
        Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Subscription subscription = new Subscription(10, customer, product, "biweekly", startDate, endDate, false);
        Subscription sub = adminService.addSubscription(subscription);
        List<Subscription> subscriptions = adminService.viewInactiveSubscriptions();

        assertNotNull(subscriptions, "Subscription should not be null");
    }

}
