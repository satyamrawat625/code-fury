package com.ecommerce.service.impl;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.exception.QtyNotValidException;
import com.ecommerce.factory.StorageFactory;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public ProductServiceImpl() {
        productDao = StorageFactory.getProductDao();
    }

    // Adds a new product
    @Override
    public void addProduct(Product product) throws QtyNotValidException {
        productDao.save(product);
    }

    // Updates an existing product
    @Override
    public void updateProduct(Product product) throws QtyNotValidException {
        productDao.update(product);
    }

    // Deletes a product by ID
    @Override
    public void deleteProduct(int productId) {
        productDao.delete(productId);
    }

    // Retrieves a list of all products
    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    // Retrieves a product by ID
    @Override
    public Product getProductById(int productId) {
        return productDao.findById(productId);
    }
}
