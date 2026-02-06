package com.shop.grocery.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderItemDTO {
    String code;
    Integer quantity;
}
