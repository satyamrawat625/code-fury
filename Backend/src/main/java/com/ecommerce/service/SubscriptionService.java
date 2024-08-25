package com.ecommerce.service;

import com.ecommerce.model.Subscription;
import java.util.List;

public interface SubscriptionService {
    List<Subscription> getActiveSubscriptions();

    List<Subscription> getInactiveSubscriptions();

    void deactivateSubscription(int subscriptionId);

    void activateSubscription(int subscriptionId);

    Subscription subscribeProduct(int customerId, int productId, String frequency);

}
