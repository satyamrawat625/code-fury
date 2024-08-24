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
        subscriptionDao = StorageFactory.getSubscriptionDao();
        customerDao = StorageFactory.getCustomerDao();
        productDao = StorageFactory.getProductDao(); // Initialize DAOs
    }

    // Retrieves a list of active subscriptions
    @Override
    public List<Subscription> getActiveSubscriptions() {
        return subscriptionDao.findActiveSubscriptions();
    }

    // Retrieves a list of inactive subscriptions
    @Override
    public List<Subscription> getInactiveSubscriptions() {
        return subscriptionDao.findInactiveSubscriptions();
    }

    // Deactivates a subscription by ID
    @Override
    public void deactivateSubscription(int subscriptionId) {
        subscriptionDao.deactivate(subscriptionId);
    }

    // Activates a subscription by ID
    @Override
    public void activateSubscription(int subscriptionId) {
        subscriptionDao.activate(subscriptionId);
    }

    // Subscribes a customer to a product with a specified frequency
    @Override
    public Subscription subscribeProduct(int customerId, int productId, String frequency) {
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
