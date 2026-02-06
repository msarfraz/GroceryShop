package com.shop.grocery.repository;

import com.shop.grocery.model.OrderPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderPackageRepository extends JpaRepository<OrderPackage, Long> {
    public List<OrderPackage> findAllByOrderId(Long orderId);
     public void deleteAllByOrderId(Long orderId);
}
