package com.jaarquesuoc.shop.carts.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private String id;

    private String orderId;

    private LocalDateTime date;

    private String customerId;

    private List<OrderItemDto> orderItemDtos;
}
