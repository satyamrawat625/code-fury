package com.ecommerce.service.impl;

import com.ecommerce.dao.CustomerDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.SubscriptionDao;
import com.ecommerce.factory.StorageFactory;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.model.Subscription;
import com.ecommerce.service.SubscriptionService;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

    private SubscriptionDao subscriptionDao;
    private CustomerDao customerDao;
    private ProductDao productDao;

    public SubscriptionServiceImpl() {
        subscriptionDao= StorageFactory.getSubscriptionDao();
        customerDao= StorageFactory.getCustomerDao();
        productDao= StorageFactory.getProductDao();  // initialize other DAOs here  // inject dependencies as needed  // consider using dependency injection for better testability and reusability   // ensure proper error handling and exception management  // consider using a database transaction for atomicity  // consider using a caching mechanism to improve performance  // consider using a caching mechanism with expiration time for frequently accessed data  // consider using a caching mechanism with distributed caching for high-traffic applications  // consider using a
    }

    @Override
    public List<Subscription> getActiveSubscriptions() {
        return subscriptionDao.findActiveSubscriptions();
    }

    @Override
    public List<Subscription> getInactiveSubscriptions() {
        return subscriptionDao.findInactiveSubscriptions();
    }

    @Override
    public void deactivateSubscription(Long subscriptionId) {
        subscriptionDao.deactivate(subscriptionId);
    }

    @Override
    public void activateSubscription(Long subscriptionId) {
        subscriptionDao.activate(subscriptionId);
    }
    @Override
    public Subscription subscribeProduct(Long customerId, Long productId, String frequency) {
        Customer customer = customerDao.findById(customerId);
        Product product = productDao.findById(productId);
        Subscription subscription = new Subscription();
        subscription.setCustomer(customer);
        subscription.setProduct(product);
        subscription.setFrequency(frequency);
        subscription.setActive(true);

        return subscriptionDao.save(subscription);
    }
}
