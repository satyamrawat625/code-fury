package com.ecommerce.dao;

import com.ecommerce.model.Admin;

import java.sql.Date;

public interface AdminDao {
    Admin save(Admin admin);
    Admin checkLogin(String email, String password,int adminId);

    void getOrderHistory(Date startDate, Date endDate);

    void getDeliveryList();
}
