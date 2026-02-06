package com.shop.grocery.service;

import com.shop.grocery.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    Product saveProduct(Product product);

    // Read operation
    List<Product> fetchAllProducts();

    // Get operation
    Product getProduct(String code);


    Product updateProduct(Product product);

    boolean deleteProduct(String code);
}
