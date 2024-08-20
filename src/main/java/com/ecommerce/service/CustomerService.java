package com.ecommerce.service;

import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.model.Subscription;
import java.util.List;

public interface CustomerService {
    Customer registerCustomer(Customer customer);

    Customer loginCustomer(String email, String password);


}
