package com.jaarquesuoc.shop.carts.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {

    private Product product;

    private int quantity;
}
