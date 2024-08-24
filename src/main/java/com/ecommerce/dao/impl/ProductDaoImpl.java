package com.ecommerce.dao.impl;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.exception.DataAccessException;
import com.ecommerce.exception.InvalidProductException;
import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private Connection connection;

    public ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Product product) {
        String query = "INSERT INTO products (name, description, price) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving product", e);
        }
    }

    @Override
    public void update(Product product) {
        String query = "UPDATE products SET name = ?, description = ?, price = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setLong(4, product.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ProductNotFoundException("The Product details could not be updated");
        }
    }

    @Override
    public void delete(Long productId) {
        String query = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ProductNotFoundException("Error deleting product");
        }
    }

    @Override
    public List<Product> findAll() {
        String query = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                products.add(mapRowToProduct(rs));
            }
        } catch (SQLException e) {
            throw new ProductNotFoundException("Error finding all products");
        }
        return products;
    }

    @Override
    public Product findById(Long productId) {
        String query = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToProduct(rs);
            }
            else{
                throw new ProductNotFoundException("Product with ID " + productId + " not found.");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error accessing product data", e);
        }
    }

    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        return product;
    }
}
