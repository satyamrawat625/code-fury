package com.ecommerce.Testing.JunitTesting;

import com.ecommerce.model.Admin;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.model.Subscription;
import com.ecommerce.service.impl.AdminServiceImpl;
import com.ecommerce.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerTestJunit {
    private CustomerServiceImpl customerServiceimpl;
    private AdminServiceImpl adminService;


    @BeforeEach
    public void setUp() {
        customerServiceimpl = new CustomerServiceImpl();
    }

    @Test
    void testRegisterCustomer() {
        Customer customer = new Customer("Vikas Singh", "987.s345ingh@gmail.com", "hc1234", "Pune", "1234567890");
        Customer regCustomer=customerServiceimpl.registerCustomer(customer);

        // Validate the results
        assertNotNull(regCustomer, "Register Customer should not be null");
        assertEquals("Vikas Singh", regCustomer.getName(), "Customer name should match");

    }

    @Test
    void testLoginCustomer()
    {
        Customer customer = new Customer("Vikas Singh", "987.s345ingh@gmail.com", "hc1234", "Pune", "1234567890");
        customerServiceimpl.registerCustomer(customer);
        Customer loginCustomer=customerServiceimpl.loginCustomer("987.s345ingh@gmail.com", "hcbc");

        // Validate the results
        assertNotNull(loginCustomer, "Login Customer should not be null");
        assertEquals("Vikas Singh", loginCustomer.getName(), "Customer name should match");
    }

    @Test
    void testActivateSubscription()
    {
        Product product = new Product(1, "Apple", "Fruit", 100, true);
        Customer customer = new Customer("Vikas Singh", "987.s345ingh@gmail.com", "hc1234","Pune","1234567890");
        LocalDate localStartDate = LocalDate.of(2024, 8, 24);
        LocalDate localEndDate = LocalDate.of(2025, 8, 24);
        Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Subscription subscription = new Subscription(10, customer, product, "biweekly", startDate, endDate, false);
        Subscription sub=adminService.addSubscription(subscription);
        customerServiceimpl.activateSubscription(10);
        // Validate the results
        assertEquals(true, sub.isActive(), "Subscription should be active");
    }


    @Test
    void testdEActivateSubscription()
    {
        Product product = new Product(1, "Milk", "Food", 100, true);
        Customer customer = new Customer("Vikas Singh", "987.s345ingh@gmail.com", "hc1234","Pune","1234567890");
        LocalDate localStartDate = LocalDate.of(2024, 8, 24);
        LocalDate localEndDate = LocalDate.of(2025, 8, 24);
        Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Subscription subscription = new Subscription(10, customer, product, "biweekly", startDate, endDate, true);
        Subscription sub=adminService.addSubscription(subscription);
        customerServiceimpl.deactivateSubsciption(10);
        // Validate the results
        assertEquals(false, sub.isActive(), "Subscription should be deactive");
    }

    @Test
    void testfindProductById()
    {
        Product product = new Product(1, "Apple", "Fruit", 100, true);
        Product product1 = customerServiceimpl.findProductById(1);
        // Validate the results
        assertNotNull(product1, "Product should not be null");
    }

    @Test
    void testfBrowseProducts()
    {
        Product product = new Product(1, "Apple", "Fruit", 100, true);
        Product product1 = new Product(12, "Milk", "Food", 70, true);
        adminService.addProduct(product);
        adminService.addProduct(product1);
        List<Product> productlist = customerServiceimpl.BrowseProduct();
        // Validate the results
        assertNotNull(productlist, "Product should not be null");
    }

}
