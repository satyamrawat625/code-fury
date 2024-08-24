package com.ecommerce.factory;

import com.ecommerce.dao.AdminDao;
import com.ecommerce.dao.CustomerDao;
import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.SubscriptionDao;
import com.ecommerce.dao.impl.AdminDaoImpl;
import com.ecommerce.dao.impl.CustomerDaoImpl;
import com.ecommerce.dao.impl.ProductDaoImpl;
import com.ecommerce.dao.impl.SubscriptionDaoImpl;

import java.sql.Connection;
import java.sql.SQLException;

import com.ecommerce.util.DBUtil;

public class StorageFactory {

    public static CustomerDao getCustomerDao() {
        Connection connection = DBUtil.getConnection();
        return new CustomerDaoImpl(connection);
    }

    public static ProductDao getProductDao() {
        Connection connection = DBUtil.getConnection();
        return new ProductDaoImpl(connection);
    }

    public static AdminDao getAdminDao() {
        Connection connection = DBUtil.getConnection();
        return new AdminDaoImpl(connection);
    }


    public static SubscriptionDao getSubscriptionDao() {
        Connection connection = DBUtil.getConnection();
        return new SubscriptionDaoImpl(connection);
    }
}
