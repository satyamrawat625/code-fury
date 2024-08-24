package com.ecommerce.service.impl;

import com.ecommerce.dao.CustomerDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.SubscriptionDao;
import com.ecommerce.factory.StorageFactory;
import com.ecommerce.model.Customer;
import com.ecommerce.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao customerDao;
    private SubscriptionDao subscriptionDao;

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

    public void activateSubscription(int id) {
        subscriptionDao.activate(id);
    }

    @Override
    public void deactivateSubsciption(int l) {
        subscriptionDao.deactivate(l);
    }

    @Override
    public boolean placeOrder(int order_id, String order_date, String delivery_date, String status, int customer_id, int product_id) {
        boolean flag=customerDao.placeOrder(order_id,order_date,delivery_date,status,customer_id,product_id);
        if(flag)
            return true;
        return false;
    }


}
