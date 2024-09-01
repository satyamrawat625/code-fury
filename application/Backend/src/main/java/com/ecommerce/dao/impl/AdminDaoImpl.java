package com.ecommerce.dao.impl;


import com.ecommerce.dao.AdminDao;
import com.ecommerce.dao.CustomerDao;
import com.ecommerce.exception.CustomerNotFoundException;
import com.ecommerce.model.Admin;
import com.ecommerce.model.Customer;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    private Connection connection;

    public AdminDaoImpl(Connection connection) {
        this.connection = connection;
    }

    // Save a new admin record to the database
    @Override
    public Admin save(Admin admin) {
        String query = "INSERT INTO admin (name, email, password, adminId, adminRole) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getEmail());
            stmt.setString(3, admin.getPassword());
            stmt.setInt(4, admin.getAdminID());
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

    // Check admin login credentials
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

    //Get the Order history for a specific date range
    @Override
    public void getOrderHistory(Date startDate, Date endDate) {
        String query = "SELECT * FROM Orders WHERE delivery_date BETWEEN ? AND ?";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set the start and end dates in the query
            stmt.setString(1, dateFormat.format(startDate));
            stmt.setString(2, dateFormat.format(endDate));

            try (ResultSet rs = stmt.executeQuery()) {
                // Print out the column headers
                System.out.printf("%-10s %-20s %-20s %-10s %-15s %-15s%n",
                        "Order ID", "Order Date", "Delivery Date", "Status", "Customer ID", "Product ID");

                // Loop through the result set and print each row
                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    Date orderDate = rs.getDate("order_date");
                    Date deliveryDate = rs.getDate("delivery_date");
                    String status = rs.getString("status");
                    int customerId = rs.getInt("customer_id");
                    int productId = rs.getInt("product_id");

                    System.out.printf("%-10d %-20s %-20s %-10s %-15d %-15d%n",
                            orderId, orderDate.toString(), deliveryDate.toString(), status, customerId, productId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get the delivery list for the current date
    @Override
    public void getDeliveryList() {
        String query = "SELECT * FROM Orders WHERE delivery_date = ?";
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Set the current date in the query
            stmt.setString(1, currentDate.format(dateFormat));

            try (ResultSet rs = stmt.executeQuery()) {
                // Print out the column headers
                System.out.printf("%-10s %-20s %-20s %-10s %-15s %-15s%n",
                        "Order ID", "Order Date", "Delivery Date", "Status", "Customer ID", "Product ID");

                // Loop through the result set and print each row
                while (rs.next()) {
                    int orderId = rs.getInt("order_id");
                    Date orderDate = rs.getDate("order_date");
                    Date deliveryDate = rs.getDate("delivery_date");
                    String status = rs.getString("status");
                    int customerId = rs.getInt("customer_id");
                    int productId = rs.getInt("product_id");

                    System.out.printf("%-10d %-20s %-20s %-10s %-15d %-15d%n",
                            orderId, orderDate.toString(), deliveryDate.toString(), status, customerId, productId);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Map the result set to an Admin object
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
