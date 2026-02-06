package com.shop.grocery.service;

import com.shop.grocery.dto.OrderPackageDTO;
import com.shop.grocery.model.Product;
import com.shop.grocery.model.Package;
import com.shop.grocery.model.OrderItem;
import com.shop.grocery.repository.ProductRepository;
import com.shop.grocery.repository.OrderRepository;
import com.shop.grocery.repository.OrderItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    public void init(){

    }

    @Test
    public void fetchProductList_empty(){
        when(productRepository.findAll()).thenReturn(List.of());

        List<Product> products = productService.fetchAllProducts();

        Assertions.assertEquals(0, products.size());
     }
    @Test
    public void fetchAllProductList_success(){
        when(productRepository.findAll()).thenReturn(List.of(new Product()));

        List<Product> products = productService.fetchAllProducts();

        Assertions.assertEquals(1, products.size());

    }


    @Test
    public void getContact_success(){
        when(productRepository.findByCode("CE")).thenReturn(Optional.of(new Product()));

        Product product = productService.getProduct("CE");

        Assertions.assertNotNull(product);

    }
    @Test
    public void getContact_notfound(){
        when(productRepository.findByCode("CE")).thenReturn(Optional.empty());

        Product product = productService.getProduct("CE");

        Assertions.assertNull(product);
    }
    @Test
    public void saveContact_success(){

        when(productRepository.findByCode("CE")).thenReturn(Optional.empty());
        when(productRepository.save(any(Product.class))).thenReturn(new Product(1l, "CE", "name", 10d));

        Product product = productService.saveProduct(new Product(0l, "CE", "name", 10d));

        Assertions.assertNotNull(product);
        Assertions.assertEquals(1, product.getId());

    }
    @Test
    public void saveContact_invalid(){
        when(productRepository.findByCode("CE")).thenReturn(Optional.of(new Product(1l, "CE", "name", 10d)));
       // when(productRepository.save(any(Product.class))).thenReturn(new Product(1l, "CE", "name", 10d));

        Product product = productService.saveProduct(new Product(1l, "CE", "name", 10d));

        Assertions.assertNull(product);

    }

    @Test
    public void deleteContact_success(){
        when(productRepository.findByCode("CE")).thenReturn(Optional.of(new Product(1l, "CE", "name", 10d)));
        doNothing().when(productRepository).deleteById(1l);

        boolean result = productService.deleteProduct("CE");

        Assertions.assertEquals(true, result);


    }
    @Test
    public void deleteContact_invalid(){
        when(productRepository.findByCode("CE")).thenReturn(Optional.empty());

        boolean result = productService.deleteProduct("CE");

        Assertions.assertEquals(false, result);
    }
    @Test
    public void updateContact_success(){
        when(productRepository.findByCode("CE")).thenReturn(Optional.of(new Product(1l, "CE", "name", 10d)));
        when(productRepository.save(any(Product.class))).thenReturn(new Product(1l, "CE", "new name", 10d));

        Product product = productService.saveProduct(new Product(0l, "CE", "new name", 10d));

        Assertions.assertNotNull(product);
        Assertions.assertEquals(1, product.getId());
        Assertions.assertEquals("new name", product.getName());
    }
    @Test
    public void updateContact_invalid(){
        when(productRepository.findByCode("CE")).thenReturn(Optional.empty());
        //when(productRepository.save(any(Product.class))).thenReturn(new Product(1l, "CE", "new name", 10d));

        Product product = productService.saveProduct(new Product(0l, "CE", "new name", 10d));

        Assertions.assertNull(product);
    }
}
