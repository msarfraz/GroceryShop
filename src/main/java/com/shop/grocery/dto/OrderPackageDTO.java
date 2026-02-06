package com.shop.grocery.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderPackageDTO {
    String code;
    Integer itemQuantity;
    Double itemPrice;
    Integer packagesQuantity;
    Double packagesPrice;
}
