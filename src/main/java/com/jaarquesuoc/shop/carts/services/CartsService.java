package com.jaarquesuoc.shop.carts.services;

import com.jaarquesuoc.shop.carts.dtos.CartDto;
import com.jaarquesuoc.shop.carts.dtos.CustomerDto;
import com.jaarquesuoc.shop.carts.dtos.NextOrderIdDto;
import com.jaarquesuoc.shop.carts.dtos.OrderItemDto;
import com.jaarquesuoc.shop.carts.dtos.ProductDto;
import com.jaarquesuoc.shop.carts.mappers.CartMapper;
import com.jaarquesuoc.shop.carts.models.Cart;
import com.jaarquesuoc.shop.carts.models.OrderItem;
import com.jaarquesuoc.shop.carts.repositories.CartsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CartsService {

    private final OrdersService ordersService;

    private final ProductsService productsService;

    private final CustomersService customersService;

    private final CartsRepository cartsRepository;

    private static final int MAX_ORDER_ITEMS = 10;

    public CartDto getCartDto(final String customerId) {
        CartDto cartDto = CartMapper.INSTANCE.toCartDto(getCart(customerId));

        return populateCartDtoWithProducts(cartDto);
    }

    public List<CartDto> getAllCartDtos() {
        return cartsRepository.findAll().stream()
            .map(CartMapper.INSTANCE::toCartDto)
            .collect(toList());
    }

    public void cleanDb() {
        cartsRepository.deleteAll();
    }

    public CartDto incrementOrderItem(final String customerId, final ProductDto productDto) {
        CustomerDto customerDto = customersService.getCustomerDto(customerId);

        return incrementOrderItem(customerDto, productDto);
    }

    private CartDto incrementOrderItem(final CustomerDto customerDto, final ProductDto productDto) {
        Cart cart = CartMapper.INSTANCE.replicate(getCart(customerDto.getId()));

        List<OrderItem> orderItems = Optional.ofNullable(cart.getOrderItems())
            .orElse(new ArrayList<>());

        incrementOrderItem(orderItems, productDto);

        cart.setOrderItems(orderItems);

        Cart updatedCart = cartsRepository.save(cart);

        return populateCartDtoWithProducts(CartMapper.INSTANCE.toCartDto(updatedCart));

    }

    private void incrementOrderItem(final List<OrderItem> orderItems, final ProductDto productDto) {
        OrderItem orderItem = orderItems.stream()
            .filter(oi -> oi.getProductId().equals(productDto.getId()))
            .findFirst()
            .orElse(OrderItem.builder()
                .productId(productDto.getId())
                .quantity(0)
                .build());

        orderItems.remove(orderItem);

        if (orderItem.getQuantity() < MAX_ORDER_ITEMS) {
            orderItem.setQuantity(orderItem.getQuantity() + 1);
        }

        orderItems.add(orderItem);
    }

    public CartDto upsertOrderItem(final String customerId, final OrderItemDto upsertOrderItemDto) {
        CustomerDto customerDto = customersService.getCustomerDto(customerId);

        return upsertOrderItem(customerDto, upsertOrderItemDto);
    }

    private CartDto upsertOrderItem(final CustomerDto customerDto, final OrderItemDto upsertOrderItemDto) {
        Cart cart = CartMapper.INSTANCE.replicate(getCart(customerDto.getId()));

        List<OrderItem> orderItems = Optional.ofNullable(cart.getOrderItems())
            .orElse(new ArrayList<>());

        removeOrderItem(orderItems, upsertOrderItemDto);

        if (upsertOrderItemDto.getQuantity() > 0 && upsertOrderItemDto.getQuantity() < MAX_ORDER_ITEMS) {
            orderItems.add(CartMapper.INSTANCE.toOrderItem(upsertOrderItemDto));
        }

        cart.setOrderItems(orderItems);

        Cart updatedCart = cartsRepository.save(cart);

        return populateCartDtoWithProducts(CartMapper.INSTANCE.toCartDto(updatedCart));
    }

    private void removeOrderItem(final List<OrderItem> orderItems, final OrderItemDto upsertOrderItemDto) {
        List<OrderItem> toBeRemoved = orderItems.stream()
            .filter(orderItem -> orderItem.getProductId().equals(upsertOrderItemDto.getProductDto().getId()))
            .collect(toList());

        orderItems.removeAll(toBeRemoved);
    }

    private Cart getCart(final String customerId) {
        NextOrderIdDto nextOrderIdDto = ordersService.getNextOrderId(customerId);

        return cartsRepository.findFirstByOrderIdOrderByDateDesc(nextOrderIdDto.getNextOrderId())
            .orElse(createNewCart(nextOrderIdDto.getNextOrderId(), customerId));
    }

    private Cart createNewCart(final String orderId, final String customerId) {
        return Cart.builder()
            .orderId(orderId)
            .customerId(customerId)
            .build();
    }

    private CartDto populateCartDtoWithProducts(final CartDto cartDto) {
        if (cartDto.getOrderItemDtos() == null) {
            return cartDto;
        }

        List<String> productIds = cartDto.getOrderItemDtos().stream()
            .map(OrderItemDto::getProductDto)
            .map(ProductDto::getId)
            .collect(toList());

        Map<String, ProductDto> productDtos = productsService.getProducts(productIds);

        cartDto.getOrderItemDtos()
            .forEach(orderItemDto -> orderItemDto.setProductDto(productDtos.get(orderItemDto.getProductDto().getId())));

        return cartDto;
    }
}
