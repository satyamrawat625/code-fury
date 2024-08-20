package com.ecommerce.dao;

import com.ecommerce.model.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionDao {
    List<Subscription> findActiveSubscriptions();

    List<Subscription> findInactiveSubscriptions();

    void deactivate(Long subscriptionId);

    void activate(Long subscriptionId);

    Subscription save(Subscription subscription);

    Subscription findById(Long subscriptionId) throws SQLException;

}
