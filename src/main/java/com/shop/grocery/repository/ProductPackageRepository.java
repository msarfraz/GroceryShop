package com.shop.grocery.repository;

import com.shop.grocery.model.Product;
import com.shop.grocery.model.ProductPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductPackageRepository extends JpaRepository<ProductPackage, Long> {
    List<ProductPackage> findAllByQuantity(Integer quantity);
    List<ProductPackage> findAllByCode(String code);



}
