package com.ecommerce.service;

import com.ecommerce.model.Customer;

public interface CustomerService {
    Customer registerCustomer(Customer customer);

    Customer loginCustomer(String email, String password);

    public void activateSubscription(long id);

    void deactivateSubsciption(long l);

    boolean placeOrder(int i, String s, String s1, String pending, int i1, int i2);
}
