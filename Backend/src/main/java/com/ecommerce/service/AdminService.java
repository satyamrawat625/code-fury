package com.ecommerce.service;

import com.ecommerce.model.Admin;
import com.ecommerce.model.Product;
import com.ecommerce.model.Subscription;

import java.sql.Date;
import java.util.List;

public interface AdminService {
    Admin registerAdmin(Admin admin);
    Admin loginAdmin(String email, String password, int adminId);
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(int productId);
    Subscription addSubscription(Subscription subscription);
    List<Subscription> viewActiveSubscriptions();
    List<Subscription> viewInactiveSubscriptions();

    void getOrderHistory(Date startDate, Date endDate);

    void getDeliveryList();
}
