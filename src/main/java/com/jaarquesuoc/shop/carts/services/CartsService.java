package com.jaarquesuoc.shop.carts.services;

import com.jaarquesuoc.shop.carts.dtos.Cart;
import com.jaarquesuoc.shop.carts.dtos.NextOrderId;
import com.jaarquesuoc.shop.carts.dtos.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CartsService {

    private final OrdersService ordersService;

    private final ProductsService productsService;

    public Cart getCart(final String customerId) {
        NextOrderId nextOrderId = ordersService.getNextOrderId(customerId);

        return buildCart(nextOrderId.getNextOrderId(), customerId);
    }

    public Cart updateOrderItem(final String customerId, final OrderItem updatedOrderItem) {
        Cart cart = getCart(customerId);

        cart.getOrderItems()
            .forEach(orderItem -> {
                if (orderItem.getProduct().getId().equals(updatedOrderItem.getProduct().getId())) {
                    orderItem.setQuantity(updatedOrderItem.getQuantity());
                }
            });

        return cart;
    }

    private Cart buildCart(final String id, final String customerId) {
        return Cart.builder()
            .id(id)
            .date(LocalDateTime.now())
            .customerId(customerId)
            .orderItems(buildOrderItems())
            .build();
    }

    private List<OrderItem> buildOrderItems() {
        return IntStream.range(0, 5)
            .mapToObj(i -> buildOrderItem(String.valueOf(i)))
            .collect(Collectors.toList());
    }

    private OrderItem buildOrderItem(final String id) {
        return OrderItem.builder()
            .product(productsService.getProduct(id))
            .quantity(2)
            .build();
    }
}
