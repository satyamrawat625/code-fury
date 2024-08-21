package com.ecommerce.dao.impl;

import com.ecommerce.dao.SubscriptionDao;
import com.ecommerce.exception.InvalidSubscriptionException;
import com.ecommerce.exception.SubscriptionAlreadyExistsException;
import com.ecommerce.exception.SubscriptionNotFoundException;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.model.Subscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {

    private Connection connection;

    public SubscriptionDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public Subscription findById(Long id) throws SQLException {
        String query = "SELECT s.id AS sub_id, s.frequency, s.is_active, " +
                "c.id AS customer_id, c.name AS customer_name, c.email, " +
                "p.id AS product_id, p.name AS product_name, p.description, p.price " +
                "FROM subscriptions s " +
                "JOIN customers c ON s.customer_id = c.id " +
                "JOIN products p ON s.product_id = p.id " +
                "WHERE s.id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Subscription subscription = new Subscription();
                    subscription.setId(rs.getLong("sub_id"));
                    subscription.setFrequency(rs.getString("frequency"));
                    subscription.setActive(rs.getBoolean("is_active"));

                    Customer customer = new Customer();
                    customer.setId(rs.getLong("customer_id"));
                    customer.setName(rs.getString("customer_name"));
                    customer.setEmail(rs.getString("email"));
                    subscription.setCustomer(customer);

                    Product product = new Product();
                    product.setId(rs.getLong("product_id"));
                    product.setName(rs.getString("product_name"));
                    product.setDescription(rs.getString("description"));
                    product.setPrice(rs.getDouble("price"));
                    subscription.setProduct(product);

                    return subscription;
                } else {
                    throw new SubscriptionNotFoundException("Subscription with ID " + id + " not found.");
                }
            }
        }
    }

    @Override
    public Subscription save(Subscription subscription) {
        String query = "INSERT INTO subscriptions (customer_id, product_id, frequency, is_active) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, subscription.getCustomer().getId());
            stmt.setLong(2, subscription.getProduct().getId());
            stmt.setString(3, subscription.getFrequency());
            stmt.setBoolean(4, subscription.isActive());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                subscription.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            try {
                throw new SubscriptionAlreadyExistsException("Error saving subscription");
            } catch (SubscriptionAlreadyExistsException ex) {
                throw new RuntimeException(ex);
            }
        }
        return subscription;
    }

    @Override
    public List<Subscription> findActiveSubscriptions() {
        String query = "SELECT * FROM subscriptions WHERE is_active = true";
        List<Subscription> subscriptions = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                subscriptions.add(mapRowToSubscription(rs));
            }
        } catch (SQLException e) {
            throw new InvalidSubscriptionException("Error finding active subscriptions");
        }
        return subscriptions;
    }

    @Override
    public List<Subscription> findInactiveSubscriptions() {
        String query = "SELECT * FROM subscriptions WHERE is_active = false";
        List<Subscription> subscriptions = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                subscriptions.add(mapRowToSubscription(rs));
            }
        } catch (SQLException e) {
            throw new InvalidSubscriptionException("Error finding inactive subscriptions");
        }
        return subscriptions;
    }

    @Override
    public void deactivate(Long subscriptionId) {
        String query = "UPDATE subscriptions SET is_active = false WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, subscriptionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSubscriptionException("Error deactivating subscription");
        }
    }

    @Override
    public void activate(Long subscriptionId) {
        String query = "UPDATE subscriptions SET is_active = true WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, subscriptionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidSubscriptionException("Error activating subscription");
        }
    }

    private Subscription mapRowToSubscription(ResultSet rs) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setId(rs.getLong("id"));
        // Assuming Customer and Product objects will be fetched and set separately
        subscription.setFrequency(rs.getString("frequency"));
        subscription.setActive(rs.getBoolean("is_active"));
        return subscription;
    }
}
