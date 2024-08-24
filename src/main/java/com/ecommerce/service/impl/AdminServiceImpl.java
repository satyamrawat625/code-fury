package com.ecommerce.service.impl;

import com.ecommerce.dao.AdminDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.SubscriptionDao;
import com.ecommerce.factory.StorageFactory;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.model.Subscription;
import com.ecommerce.service.AdminService;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao;
    private ProductDao productDao;
    private SubscriptionDao subscriptionDao;

    public AdminServiceImpl() {
        adminDao = StorageFactory.getAdminDao();
    }



    @Override
    public Admin registerAdmin(Admin admin) {
        return adminDao.save(admin);
    }

    @Override
    public Admin loginAdmin(String email, String password, Long adminId) {
        return adminDao.checkLogin(email, password, adminId);
    }

    @Override
    public void addProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.update(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        productDao.delete(productId);
    }

    @Override
    public Subscription addSubscription(Subscription subscription) {
        return subscriptionDao.save(subscription);
    }

    @Override
    public List<Subscription> viewActiveSubscriptions() {
        return subscriptionDao.findActiveSubscriptions();
    }

    @Override
    public List<Subscription> viewInactiveSubscriptions() {
        return subscriptionDao.findInactiveSubscriptions();
    }


}
