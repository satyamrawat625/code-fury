package com.ecommerce.dao.impl;

import com.ecommerce.dao.CustomerDao;
import com.ecommerce.exception.CustomerNotFoundException;
import com.ecommerce.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    private Connection connection;

    public CustomerDaoImpl(Connection connection) {
        this.connection = connection;
    }

    // Save a new customer record to the database
    @Override
    public Customer save(Customer customer) {
        String query = "INSERT INTO customers (name, email, password,address,phoneNumber) VALUES (?, ?, ?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getPhoneNumber());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                customer.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving customer", e);
        }
        System.out.println("Register successfully with ID: " + customer.getId());
        return customer;
    }

    //Find a customer by ID
    @Override
    public Customer findById(int customerId) {
        String query = "SELECT * FROM customers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToCustomer(rs);
            }
        } catch (SQLException e) {
            throw new CustomerNotFoundException("Error finding customer by ID: "+customerId);
        }
        return null;
    }

    // Find a customer by email and password
    @Override
    public Customer findByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM customers WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToCustomer(rs);
            }
        } catch (SQLException e) {
            throw new CustomerNotFoundException("Error finding customer by email: "+email);
        }
        return null;
    }

    //Find all customers
    @Override
    public List<Customer> findAll() {
        String query = "SELECT * FROM customers";
        List<Customer> customers = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                customers.add(mapRowToCustomer(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all customers", e);
        }
        return customers;
    }

    // Place a new order for a customer
    @Override
    public boolean placeOrder(int orderId, String orderDate, String deliveryDate, String status, int customerId, int productId) {
        String query = "INSERT INTO orders VALUES (?, ?, ?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, orderId);
            stmt.setString(2, orderDate);
            stmt.setString(3, deliveryDate);
            stmt.setString(4, status);
            stmt.setInt(5, customerId);
            stmt.setInt(6, productId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error saving customer, please check order details and retry", e);
        }
        return false;
    }


    // Map a ResultSet row to a Customer object
    private Customer mapRowToCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        customer.setEmail(rs.getString("email"));
        customer.setPassword(rs.getString("password"));
        customer.setAddress(rs.getString("address"));
        customer.setPhoneNumber(rs.getString("phoneNumber"));
        return customer;
    }
}
