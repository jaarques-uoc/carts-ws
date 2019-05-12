package com.jaarquesuoc.shop.carts.services;

import com.jaarquesuoc.shop.carts.dtos.CartDto;
import com.jaarquesuoc.shop.carts.dtos.NextOrderIdDto;
import com.jaarquesuoc.shop.carts.dtos.OrderItemDto;
import com.jaarquesuoc.shop.carts.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CartsService {

    private final OrdersService ordersService;

    private final ProductsService productsService;

    public CartDto getCart(final String customerId) {
        NextOrderIdDto nextOrderIdDto = ordersService.getNextOrderId(customerId);

        return buildCart(nextOrderIdDto.getNextOrderId(), customerId);
    }

    public CartDto updateOrderItem(final String customerId, final OrderItemDto updatedOrderItemDto) {
        CartDto cartDto = getCart(customerId);

        cartDto.getOrderItemDtos().stream()
            .filter(orderItemDto -> orderItemDto.equalsProductId(updatedOrderItemDto))
            .forEach(orderItemDto -> orderItemDto.setQuantity(updatedOrderItemDto.getQuantity()));

        return cartDto;
    }

    private CartDto buildCart(final String id, final String customerId) {
        return CartDto.builder()
            .id(id)
            .date(LocalDateTime.now())
            .customerId(customerId)
            .orderItemDtos(buildOrderItems())
            .build();
    }

    private List<OrderItemDto> buildOrderItems() {
        List<String> productIds = IntStream.range(0, 5)
            .mapToObj(String::valueOf)
            .collect(toList());

        List<ProductDto> productDtos = productsService.getProducts(productIds);

        return productDtos.stream()
            .map(this::buildOrderItem)
            .collect(toList());
    }

    private OrderItemDto buildOrderItem(final ProductDto productDto) {
        return OrderItemDto.builder()
            .productDto(productDto)
            .quantity(2)
            .build();
    }
}
