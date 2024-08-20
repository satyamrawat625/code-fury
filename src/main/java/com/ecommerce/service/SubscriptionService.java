package com.ecommerce.service;

import com.ecommerce.model.Subscription;
import java.util.List;

public interface SubscriptionService {
    List<Subscription> getActiveSubscriptions();

    List<Subscription> getInactiveSubscriptions();

    void deactivateSubscription(Long subscriptionId);

    void activateSubscription(Long subscriptionId);

    Subscription subscribeProduct(Long customerId, Long productId, String frequency);

}
