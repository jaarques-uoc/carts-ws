package com.jaarquesuoc.shop.carts.dtos;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CartDto {

    private String id;

    private LocalDateTime date;

    private String customerId;

    private List<OrderItemDto> orderItemDtos;
}
