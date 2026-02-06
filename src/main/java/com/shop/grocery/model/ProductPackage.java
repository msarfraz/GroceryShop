package com.shop.grocery.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="product_packages")
public class ProductPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer quantity;
    @Column(insertable=false, updatable=false)
    String code;
    Double price;

    @ManyToOne
    @JoinColumn(name="code", referencedColumnName = "Code")
    Product product;

    public ProductPackage(Integer quantity, Double price, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;

    }
}
