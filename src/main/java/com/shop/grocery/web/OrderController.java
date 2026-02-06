package com.shop.grocery.web;

import com.shop.grocery.api.OrderAPI;
import com.shop.grocery.dto.OrderItemDTO;
import com.shop.grocery.dto.OrderPackageDTO;
import com.shop.grocery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class OrderController implements OrderAPI {
    @Autowired
    ProductService productService;


    @Override
    public List<OrderPackageDTO> getOrder(Long orderId) {
        return List.of();
    }

    @Override
    public ResponseEntity<List<OrderPackageDTO>> createOrder(List<OrderItemDTO> order) {
        return null;
    }
}
