package com.ecommerce.dao;

import com.ecommerce.model.Admin;

public interface AdminDao {
    Admin save(Admin admin);
    Admin checkLogin(String email, String password,Long adminId);

}
