package com.jaarquesuoc.shop.carts.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Cart {

    private String id;

    private LocalDateTime date;

    private String customerId;

    private List<OrderItem> orderItems;
}
