package com.jaarquesuoc.shop.carts.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private ProductDto productDto;

    private int quantity;

    public boolean equalsProductId(final OrderItemDto orderItemDto) {
        return productDto.getId().equals(orderItemDto.getProductDto().getId());
    }
}
