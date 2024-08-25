package com.ecommerce.dao;

import com.ecommerce.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer save(Customer customer);

    Customer findByEmailAndPassword(String email, String password);

    Customer findById(int id);


    List<Customer> findAll();

    boolean placeOrder(int orderId, String orderDate, String deliveryDate, String status, int customerId, int productId);
}
