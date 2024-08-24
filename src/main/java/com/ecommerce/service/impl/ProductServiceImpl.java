package com.ecommerce.service.impl;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.factory.StorageFactory;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    public ProductServiceImpl() {
        productDao=  StorageFactory.getProductDao();
    }

    @Override
    public void addProduct(Product product) {
        productDao.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.update(product);
    }

    @Override
    public void deleteProduct(int productId) {
        productDao.delete(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public Product getProductById(int productId) {
        return productDao.findById(productId);
    }
}
