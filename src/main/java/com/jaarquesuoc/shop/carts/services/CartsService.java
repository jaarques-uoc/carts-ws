package com.jaarquesuoc.shop.carts.services;

import com.jaarquesuoc.shop.carts.dtos.Cart;
import com.jaarquesuoc.shop.carts.dtos.NextOrderId;
import com.jaarquesuoc.shop.carts.dtos.OrderItem;
import com.jaarquesuoc.shop.carts.dtos.Product;
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

    public Cart getCart(final String customerId) {
        NextOrderId nextOrderId = ordersService.getNextOrderId(customerId);

        return buildCart(nextOrderId.getNextOrderId(), customerId);
    }

    public Cart updateOrderItem(final String customerId, final OrderItem updatedOrderItem) {
        Cart cart = getCart(customerId);

        cart.getOrderItems().stream()
            .filter(orderItem -> orderItem.equalsProductId(updatedOrderItem))
            .forEach(orderItem -> orderItem.setQuantity(updatedOrderItem.getQuantity()));

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
        List<String> productIds = IntStream.range(0, 5)
            .mapToObj(String::valueOf)
            .collect(toList());

        List<Product> products = productsService.getProducts(productIds);

        return products.stream()
            .map(this::buildOrderItem)
            .collect(toList());
    }

    private OrderItem buildOrderItem(final Product product) {
        return OrderItem.builder()
            .product(product)
            .quantity(2)
            .build();
    }
}
