package com.example.orderservice.vo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestOrder {
    private String productId;

    private Integer qty;

    private Integer unitPrice;
}
