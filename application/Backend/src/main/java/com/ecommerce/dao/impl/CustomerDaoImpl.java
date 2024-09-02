package com.ecommerce.dao.impl;

import com.ecommerce.dao.CustomerDao;
import com.ecommerce.exception.CustomerNotFoundException;
import com.ecommerce.exception.InsufficientQuantityException;
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
        String query = "INSERT INTO customers (name, email, password, address, phoneNumber) VALUES (?, ?, ?,?,?)";
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
            System.out.println(e);
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
    public boolean placeOrder(int orderId, int qty, String orderDate, String deliveryDate, String status, int customerId, int productId)
            throws SQLException, InsufficientQuantityException {

        String queryQty = "SELECT qty FROM products WHERE id = ?";
        String insertOrderQuery = "INSERT INTO Orders (order_date, delivery_date, status, customer_id, product_id, qty) VALUES ( ?, ?, ?, ?, ?, ?)";
        String updateProductQtyQuery = "UPDATE products SET qty = ? WHERE id = ?";

        try (PreparedStatement stmtQty = connection.prepareStatement(queryQty);
             PreparedStatement stmtOrder = connection.prepareStatement(insertOrderQuery);
             PreparedStatement stmtUpdateQty = connection.prepareStatement(updateProductQtyQuery)) {

            // Retrieve the current quantity of the product
            stmtQty.setInt(1, productId);
            try (ResultSet rs = stmtQty.executeQuery()) {
                if (rs.next()) {
                    int remQty = rs.getInt("qty");

                    // Check if there is enough quantity
                    if (remQty >= qty) {
                        // Insert the order
                        stmtOrder.setString(1, orderDate);
                        stmtOrder.setString(2, deliveryDate);
                        stmtOrder.setString(3, status);
                        stmtOrder.setInt(4, customerId);
                        stmtOrder.setInt(5, productId);
                        stmtOrder.setInt(6, qty);
                        stmtOrder.executeUpdate();



                        // Update the product quantity
                        stmtUpdateQty.setInt(1, remQty - qty);
                        stmtUpdateQty.setInt(2, productId);
                        stmtUpdateQty.executeUpdate();

                        return true;
                    }
                    else {
                        System.out.println("insufficient qty");
                        return  false;
                    }
                }}
        catch (Exception e) {
        throw new RuntimeException("Error placing order", e);
        }
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
