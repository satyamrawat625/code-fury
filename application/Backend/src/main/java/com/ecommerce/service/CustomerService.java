package com.ecommerce.service;

import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface CustomerService {
    Customer registerCustomer(Customer customer);

    Customer loginCustomer(String email, String password);

    public void activateSubscription(int id);

    void deactivateSubsciption(int l);

    boolean placeOrder(int i, int qty,String s, String s1, String pending, int i1, int i2) throws SQLException;

    Product findProductById(int id);
    List<Product> BrowseProduct();
}
