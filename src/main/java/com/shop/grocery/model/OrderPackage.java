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

    Integer quantity;
    Double price;

    @ManyToOne
    @JoinColumn(name="code", referencedColumnName = "code")
    private Product product;

    @ManyToOne
    @JoinColumn(name="order_item_id", referencedColumnName = "id")
    private OrderItem orderItem;

    @ManyToOne
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private Order order;

    public OrderPackage(Product product, Integer quantity, Double price){
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }


}
