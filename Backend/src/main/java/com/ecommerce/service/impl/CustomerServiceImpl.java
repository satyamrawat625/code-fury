package com.ecommerce.service.impl;

import com.ecommerce.dao.CustomerDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.SubscriptionDao;
import com.ecommerce.factory.StorageFactory;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;
    private SubscriptionDao subscriptionDao;
    private ProductDao productDao;

    public CustomerServiceImpl(){
        customerDao = StorageFactory.getCustomerDao();
    }

    public CustomerServiceImpl(CustomerDao customerDao, ProductDao productDao) {
        this.customerDao = customerDao;
        this.productDao = productDao; // Missing initialization
    }

    // Registers a new customer
    @Override
    public Customer registerCustomer(Customer customer) {
        return customerDao.save(customer);
    }

    // Logs in a customer with given credentials
    @Override
    public Customer loginCustomer(String email, String password) {
        return customerDao.findByEmailAndPassword(email, password);
    }

    // Activates a subscription by ID
    public void activateSubscription(int id) {
        subscriptionDao.activate(id);
    }

    // Deactivates a subscription by ID
    @Override
    public void deactivateSubsciption(int l) {
        subscriptionDao.deactivate(l);
    }

    // Places an order and returns success status
    @Override
    public boolean placeOrder(int order_id, String order_date, String delivery_date, String status, int customer_id, int product_id) {
        boolean flag = customerDao.placeOrder(order_id, order_date, delivery_date, status, customer_id, product_id);
        return flag;
    }

    // Finds a product by ID
    @Override
    public Product findProductById(int id) {
        return productDao.findById(id);
    }

    // Retrieves a list of all products
    @Override
    public List<Product> BrowseProduct() {
        return productDao.findAll();
    }
}

