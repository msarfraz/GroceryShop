package com.shop.grocery.service;

import com.shop.grocery.model.Product;
import com.shop.grocery.repository.ProductRepository;
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
    public void getProduct_success(){
        when(productRepository.findByCode("ME")).thenReturn(Optional.of(new Product()));

        Product product = productService.getProduct("ME");

        Assertions.assertNotNull(product);

    }
    @Test
    public void getProduct_notfound(){
        when(productRepository.findByCode("ME")).thenReturn(Optional.empty());

        Product product = productService.getProduct("ME");

        Assertions.assertNull(product);
    }
    @Test
    public void saveProduct_success(){

        when(productRepository.findByCode("ME")).thenReturn(Optional.empty());
        when(productRepository.save(any(Product.class))).thenReturn(new Product(1l, "ME", "name", 10d));

        Product product = productService.saveProduct(new Product(0L, "ME", "name", 10d));

        Assertions.assertNotNull(product);
        Assertions.assertEquals(1, product.getId());

    }
    @Test
    public void saveProduct_invalid(){
        when(productRepository.findByCode("ME")).thenReturn(Optional.of(new Product(1L, "ME", "name", 10d)));
       // when(productRepository.save(any(Product.class))).thenReturn(new Product(1l, "ME", "name", 10d));

        Product product = productService.saveProduct(new Product(1l, "ME", "name", 10d));

        Assertions.assertNull(product);

    }

    @Test
    public void deleteProduct_success(){
        when(productRepository.findByCode("ME")).thenReturn(Optional.of(new Product(1L, "ME", "name", 10d)));
//        doNothing().when(productRepository).deleteById(1L);

        boolean result = productService.deleteProduct("ME");

        Assertions.assertTrue(result);


    }
    @Test
    public void deleteProduct_invalid(){
        when(productRepository.findByCode("ME")).thenReturn(Optional.empty());

        boolean result = productService.deleteProduct("ME");

        Assertions.assertFalse(result);
    }
    @Test
    public void updateProduct_success(){
        when(productRepository.findByCode("ME")).thenReturn(Optional.of(new Product(1l, "ME", "name", 10d)));
        when(productRepository.save(any(Product.class))).thenReturn(new Product(1l, "ME", "new name", 10d));

        Product product = productService.updateProduct(new Product(1l, "ME", "new name", 10d));

        Assertions.assertNotNull(product);
        Assertions.assertEquals(1, product.getId());
        Assertions.assertEquals("new name", product.getName());
    }
    @Test
    public void updateProduct_invalid(){
        when(productRepository.findByCode("ME")).thenReturn(Optional.empty());
        //when(productRepository.save(any(Product.class))).thenReturn(new Product(1l, "ME", "new name", 10d));

        Product product = productService.updateProduct(new Product(1L, "ME", "new name", 10d));

        Assertions.assertNull(product);
    }
}
