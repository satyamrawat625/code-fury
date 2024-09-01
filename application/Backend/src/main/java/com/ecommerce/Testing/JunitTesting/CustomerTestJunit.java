package com.ecommerce.service;

import com.ecommerce.exception.SubscriptionNotFoundException;
import com.ecommerce.model.Customer;
import com.ecommerce.service.CustomerService;
import com.ecommerce.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerTestJunit {

    private static CustomerService customerService;

    @BeforeAll
    public static void setUp() {
        customerService = new CustomerServiceImpl();
    }

    @Test
    @Order(1)
    public void testRegisterCustomer() {
        Customer customer = new Customer("Vikas Singh", "vikas.singh@gmail.com", "hc1234", "Pune", "1234567890");
        assertDoesNotThrow(() -> {
            customerService.registerCustomer(customer);
        });
    }

    @Test
    @Order(2)
    public void testActivateSubscription() {
        assertDoesNotThrow(() -> {
            customerService.activateSubscription(10);
        });
    }

    @Test
    @Order(3)
    public void testDeactivateSubscription() {
        assertDoesNotThrow(() -> {
            customerService.deactivateSubsciption(10);
        });
    }

//    @Test
//    @Order(4)
//    public void testActivateSubscription_NotFound() {
//        Exception exception = assertThrows(SubscriptionNotFoundException.class, () -> {
//            customerService.activateSubscription(999);  // Non-existing subscription ID
//        });
//        assertEquals("Subscription not found with ID: 999", exception.getMessage());
//    }

    @Test
    @Order(5)
    public void testPlaceOrder() {
        boolean status = assertDoesNotThrow(() -> {
            return customerService.placeOrder(1, 5, "21-08-2024", "23-08-2024", "PENDING", 20, 21);
        });
        assertFalse(status);
    }

    @Test
    @Order(6)
    public void testPlaceOrder_InvalidSQL() {

        try {
            customerService.placeOrder(1, 5, "21-08-2024", "23-08-2024", "PENDING", 20, 9999);  // Invalid product or customer ID
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
