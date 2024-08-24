package com.ecommerce.factory;

import com.ecommerce.service.CustomerService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.SubscriptionService;
import com.ecommerce.service.impl.CustomerServiceImpl;
import com.ecommerce.service.impl.ProductServiceImpl;
import com.ecommerce.service.impl.SubscriptionServiceImpl;

public class ServiceFactory {

    public static CustomerService getCustomerService() {
        return new CustomerServiceImpl();
    }

    public static ProductService getProductService() {
        return new ProductServiceImpl();
    }

    public static SubscriptionService getSubscriptionService() {
        return new SubscriptionServiceImpl();
    }
}
