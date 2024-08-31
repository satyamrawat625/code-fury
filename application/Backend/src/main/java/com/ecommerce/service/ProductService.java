package com.ecommerce.service;

import com.ecommerce.exception.QtyNotValidException;
import com.ecommerce.model.Product;
import java.util.List;

public interface ProductService {
    void addProduct(Product product) throws QtyNotValidException;

    void updateProduct(Product product) throws QtyNotValidException;

    void deleteProduct(int productId);

    List<Product> getAllProducts();

    Product getProductById(int productId);

}
