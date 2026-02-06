package com.shop.grocery.repository;
import com.shop.grocery.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Annotation
@Repository

// Interface extending CrudRepository
public interface OrderRepository
        extends JpaRepository<Order, Long> {

}

