package com.ecommerce.dao;

import com.ecommerce.model.Product;
import java.util.List;

public interface ProductDao {
    void save(Product product);

    void update(Product product);

    void delete(int productId);

    List<Product> findAll();

    Product findById(int productId);

}
