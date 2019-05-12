package com.jaarquesuoc.shop.carts.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private Product product;

    private int quantity;

    public boolean equalsProductId(final OrderItem orderItem) {
        return product.getId().equals(orderItem.getProduct().getId());
    }
}
