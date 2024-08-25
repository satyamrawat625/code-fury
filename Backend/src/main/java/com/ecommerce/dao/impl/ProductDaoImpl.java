package com.ecommerce.dao.impl;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.exception.DataAccessException;
import com.ecommerce.exception.ProductNotFoundException;
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

    // Saves a new product to the database
    @Override
    public void save(Product product) {
        String query = "INSERT INTO products (name, description, price,isavailabel) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setBoolean(4, product.isAvailable());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getInt(1));
            }
            System.out.println("Product saved successfully with ID: " + product.getId());
        } catch (SQLException e) {
            throw new RuntimeException("Product with ID: " + product.getId() + " already exists");
        }
    }

    //Update the product details
    @Override
    public void update(Product product) {
        String query = "UPDATE products SET name = ?, description = ?, price = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getId());
            stmt.executeUpdate();
            System.out.println("Product updated successfully");
        } catch (SQLException e) {
            throw new ProductNotFoundException("Product with product id" + product.getId() + " not found");
        }
    }

    //Delete the product from productid
    @Override
    public void delete(int productId) {
        String query = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new ProductNotFoundException("Product with product id" + productId + " not found");
        }
    }

    //Retrieve all the products
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
            throw new ProductNotFoundException("Error finding all products, please try again");
        }
        return products;
    }

    //Retrieve the product by productid
    @Override
    public Product findById(int productId) {
        String query = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRowToProduct(rs);
            }
            else{
                throw new ProductNotFoundException("Product with ID " + productId + " not found.");
            }
        } catch (SQLException e) {
            throw new ProductNotFoundException("Product with product id" + productId + " not found");
        }
    }

    // Map the result set row to a Product object
    private Product mapRowToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        return product;
    }
}
