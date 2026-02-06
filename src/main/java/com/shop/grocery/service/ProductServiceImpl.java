package com.shop.grocery.service;

import com.shop.grocery.model.Product;
import com.shop.grocery.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }


    @Override
    public List<Product> fetchAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(String code) {
        Optional<Product> product = productRepository.findByCode(code);
        if(product.isPresent())
            return product.get();
        else
            return null;
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> prodModel = productRepository.findByCode(product.getCode());
        if(prodModel.isPresent()){
            prodModel.get().setName(product.getName());
            prodModel.get().setPrice(product.getPrice());
            return productRepository.save(prodModel.get());
        }
        else
            return null;
    }

    @Override
    public boolean deleteProduct(String code) {
        Optional<Product> product = productRepository.findByCode(code);
        if(product.isPresent()){
            productRepository.delete(product.get());
            return true;
        }
        return false;
    }
}
