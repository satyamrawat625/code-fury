package com.ecommerce.dao.impl;


import com.ecommerce.dao.AdminDao;
import com.ecommerce.dao.CustomerDao;
import com.ecommerce.exception.CustomerNotFoundException;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    private Connection connection;

    public AdminDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Admin save(Admin admin) {
        String query = "INSERT INTO admin (name, email, password, adminId, adminRole) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getEmail());
            stmt.setString(3, admin.getPassword());
            stmt.setInt(4, admin.getId());
            stmt.setString(5, admin.getAdminRole());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                admin.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving admin", e);
        }
        System.out.println("Registered successfully with ID: " + admin.getId());
        return admin;
    }

    @Override
    public Admin checkLogin(String email, String password,int adminId) {
        String query = "SELECT * FROM admin WHERE email = ? AND password = ? AND adminId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setInt(3,adminId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToAdmin(rs);
            }
        } catch (SQLException e) {
            throw new CustomerNotFoundException("Invalid email or password");
        }
        return null;
    }

    private Admin mapRowToAdmin(ResultSet rs) throws SQLException {
        Admin admin= new Admin();
        admin.setId(rs.getInt("id"));
        admin.setName(rs.getString("name"));
        admin.setEmail(rs.getString("email"));
        admin.setPassword(rs.getString("password"));
        admin.setAdminID(rs.getInt("adminId"));
        admin.setAdminRole(rs.getString("adminRole"));
        return admin;
    }
}
