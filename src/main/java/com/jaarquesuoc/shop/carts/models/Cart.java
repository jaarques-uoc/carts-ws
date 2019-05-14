package com.jaarquesuoc.shop.carts.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    private String id;

    private String orderId;

    @Builder.Default()
    private LocalDateTime date = LocalDateTime.now();

    private String customerId;

    private List<OrderItem> orderItems;
}
