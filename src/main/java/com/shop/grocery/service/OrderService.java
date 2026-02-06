package com.shop.grocery.service;

import com.shop.grocery.dto.OrderItemDTO;
import com.shop.grocery.dto.OrderPackageDTO;
import com.shop.grocery.model.Order;
import com.shop.grocery.model.OrderPackage;
import com.shop.grocery.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    Order saveOrder(List<OrderItemDTO> orderItems);

    // Get operation
    Order getOrder(Long orderId);


}
