package com.shop.grocery.model;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreationTimestamp
    @Column(name = "order_date", nullable = false, updatable = false)
    Date orderDate;


    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private List<OrderPackage> orderItems = new ArrayList<>();



}
