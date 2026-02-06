package com.shop.grocery.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order_packages")
public class OrderPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer itemQuantity;
    Double itemPrice;
    Integer packagesQuantity;
    Double packagesPrice;
    @Column(name="order_id",insertable=false, updatable=false)
    Long orderId;

    @ManyToOne
    @JoinColumn(name="code", referencedColumnName = "code")
    Product product;


    @ManyToOne
    Order order;

    public OrderPackage(Product product, Integer itemQuantity,Double itemPrice, Integer packagesQuantity, Double packagesPrice){
        this.product = product;
        this.itemQuantity = itemQuantity;
        this.itemPrice = itemPrice;
        this.packagesQuantity = packagesQuantity;
        this.packagesPrice = packagesPrice;

    }


}
