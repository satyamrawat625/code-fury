package com.ecommerce.dao;

import com.ecommerce.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer save(Customer customer);

    Customer findByEmailAndPassword(String email, String password);

    Customer findById(Long id);


    List<Customer> findAll();
}
