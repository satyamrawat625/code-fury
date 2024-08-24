package com.ecommerce.dao;

import com.ecommerce.model.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionDao {
    List<Subscription> findActiveSubscriptions();

    List<Subscription> findInactiveSubscriptions();

    void deactivate(int subscriptionId);

    void activate(int subscriptionId);

    Subscription save(Subscription subscription);

    Subscription findById(int subscriptionId) throws SQLException;

}
