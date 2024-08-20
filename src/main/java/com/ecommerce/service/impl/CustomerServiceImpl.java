package com.ecommerce.service.impl;

import com.ecommerce.dao.CustomerDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.SubscriptionDao;
import com.ecommerce.factory.StorageFactory;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.model.Subscription;
import com.ecommerce.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;

    public CustomerServiceImpl(){
        customerDao= StorageFactory.getCustomerDao() ;
    }

    public CustomerServiceImpl(CustomerDao customerDao, ProductDao productDao) {
        this.customerDao = customerDao;
    }

    @Override
    public Customer registerCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public Customer loginCustomer(String email, String password) {
        return customerDao.findByEmailAndPassword(email, password);
    }
}
