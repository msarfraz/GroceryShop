package com.shop.grocery.repository;
import com.shop.grocery.model.Order;
import com.shop.grocery.model.OrderItem;
import com.shop.grocery.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Annotation
@Repository

// Interface extending CrudRepository
public interface OrderRepository
        extends JpaRepository<Order, Long> {

}

