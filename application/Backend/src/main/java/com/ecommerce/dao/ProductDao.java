package com.ecommerce.dao;

import com.ecommerce.exception.ProductNotFoundException;
import com.ecommerce.exception.QtyNotValidException;
import com.ecommerce.model.Product;
import java.util.List;

public interface ProductDao {
    void save(Product product) throws QtyNotValidException;

    void update(Product product) throws ProductNotFoundException,QtyNotValidException;

    void delete(int productId);

    List<Product> findAll();

    Product findById(int productId);

}
