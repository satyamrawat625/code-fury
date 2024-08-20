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

    @Override
    public Customer save(Customer customer) {
        String query = "INSERT INTO customers (name, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                customer.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving customer", e);
        }
        return customer;
    }

    @Override
    public Customer findById(Long customerId) {
        String query = "SELECT * FROM customers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, customerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToCustomer(rs);
            }
        } catch (SQLException e) {
            throw new CustomerNotFoundException("Error finding customer by ID: "+customerId);
        }
        return null;
    }

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

    private Customer mapRowToCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setName(rs.getString("name"));
        customer.setEmail(rs.getString("email"));
        customer.setPassword(rs.getString("password"));
        return customer;
    }
}
