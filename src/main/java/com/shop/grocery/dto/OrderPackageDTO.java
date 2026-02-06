package com.shop.grocery.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderPackageDTO {
    String code;
    Integer quantity;
    Double price;
    Integer packages;

}
