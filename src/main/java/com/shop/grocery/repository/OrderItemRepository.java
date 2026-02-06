package com.shop.grocery.repository;

import com.shop.grocery.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    public List<OrderItem> findAllByOrderId(Long orderId);
    public void deleteAllByOrderId(Long orderId);
}
