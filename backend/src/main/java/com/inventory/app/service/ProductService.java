package com.inventory.app.service;

import com.inventory.app.entity.Product;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProductById(Long id);
    Product updateProduct(Long id, Product updatedProduct);
    void deleteProduct(Long id);
    List<Product> getAllProducts();
}
