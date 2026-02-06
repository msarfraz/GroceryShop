package com.shop.grocery.web;

import com.shop.grocery.api.ProductAPI;
import com.shop.grocery.model.Product;
import com.shop.grocery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;


@RestController
public class ProductController implements ProductAPI {
    @Autowired
    ProductService productService;


    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        var newProduct = productService.saveProduct(product);
        if(newProduct == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.created(URI.create("/"+newProduct.getId())).body(newProduct);
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product product) {
        Product updatedProduct = productService.updateProduct(product);
        if(updatedProduct != null)
            return ResponseEntity.ok(updatedProduct);
        else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Product> getProduct(String code) {
        Product product = productService.getProduct(code);
        if(product != null)
            return ResponseEntity.ok(product);
        else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.fetchAllProducts());
    }

    @Override
    public ResponseEntity deleteProduct(String code) {
        boolean success = productService.deleteProduct(code);
        if(success)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().build();
    }
}
