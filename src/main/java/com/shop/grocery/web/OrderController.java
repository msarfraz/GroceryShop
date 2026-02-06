package com.shop.grocery.web;

import com.shop.grocery.api.OrderAPI;
import com.shop.grocery.dto.OrderItemDTO;
import com.shop.grocery.dto.OrderPackageDTO;
import com.shop.grocery.model.OrderPackage;
import com.shop.grocery.service.OrderService;
import com.shop.grocery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;


@RestController
public class OrderController implements OrderAPI {
    @Autowired
    OrderService orderService;


    @Override
    public ResponseEntity<List<OrderPackageDTO>> getOrder(Long orderId) {

        var order = orderService.getOrder(orderId);
        if(order != null){
            List<OrderPackage> orderPackages = order.getOrderItems();
            var orderItems = orderPackages.stream().map(orderPackage ->
                    new OrderPackageDTO(orderPackage.getProduct().getCode(),orderPackage.getItemQuantity(),orderPackage.getItemPrice(), orderPackage.getPackagesQuantity(), orderPackage.getPackagesPrice())).toList();

            return ResponseEntity.ok(orderItems);
        }
        else
            return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<OrderPackageDTO>> createOrder(List<OrderItemDTO> order) {
        var orderSaved = orderService.saveOrder(order);
        if(orderSaved != null){
            List<OrderPackage> orderPackages = orderSaved.getOrderItems();
            var orderItems = orderPackages.stream().map(orderPackage ->
                    new OrderPackageDTO(orderPackage.getProduct().getCode(),orderPackage.getItemQuantity(),orderPackage.getItemPrice(), orderPackage.getPackagesQuantity(), orderPackage.getPackagesPrice())).toList();

            return ResponseEntity.created(URI.create("/"+orderSaved.getId())).body(orderItems);
        }
        else
            return ResponseEntity.badRequest().build();
    }
}
