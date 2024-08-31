package com.ecommerce.service.impl;

import com.ecommerce.dao.AdminDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.SubscriptionDao;
import com.ecommerce.exception.QtyNotValidException;
import com.ecommerce.factory.StorageFactory;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Product;
import com.ecommerce.model.Subscription;
import com.ecommerce.service.AdminService;

import java.sql.Date;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao;
    private ProductDao productDao;
    private SubscriptionDao subscriptionDao;

    public AdminServiceImpl() {
        adminDao = StorageFactory.getAdminDao();
        productDao = StorageFactory.getProductDao();
        subscriptionDao = StorageFactory.getSubscriptionDao();
    }

    // Registers a new admin
    @Override
    public Admin registerAdmin(Admin admin) {
        return adminDao.save(admin);
    }

    // Logs in an admin with given credentials
    @Override
    public Admin loginAdmin(String email, String password, int adminId) {
        return adminDao.checkLogin(email, password, adminId);
    }

    // Adds a new product
    @Override
    public void addProduct(Product product) throws QtyNotValidException {
        productDao.save(product);
    }

    // Updates an existing product
    @Override
    public void updateProduct(Product product) throws QtyNotValidException {
        productDao.update(product);
    }

    // Deletes a product by ID
    @Override
    public void deleteProduct(int productId) {
        productDao.delete(productId);
    }

    // Adds a new subscription
    @Override
    public Subscription addSubscription(Subscription subscription) {
        return subscriptionDao.save(subscription);
    }

    // Retrieves a list of active subscriptions
    @Override
    public List<Subscription> viewActiveSubscriptions() {
        return subscriptionDao.findActiveSubscriptions();
    }

    // Retrieves a list of inactive subscriptions
    @Override
    public List<Subscription> viewInactiveSubscriptions() {
        return subscriptionDao.findInactiveSubscriptions();
    }

    // Retrieves order history within a date range
    @Override
    public void getOrderHistory(Date startDate, Date endDate) {
        adminDao.getOrderHistory(startDate, endDate);
    }

    // Retrieves the delivery list
    @Override
    public void getDeliveryList() {
        adminDao.getDeliveryList();
    }
}
