package com.jaarquesuoc.shop.carts.services;

import com.jaarquesuoc.shop.carts.dtos.CartDto;
import com.jaarquesuoc.shop.carts.dtos.NextOrderIdDto;
import com.jaarquesuoc.shop.carts.dtos.OrderDto;
import com.jaarquesuoc.shop.carts.mappers.CartsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrdersService {

    private final OrdersClient ordersClient;

    public NextOrderIdDto getNextOrderId(final String customerId) {
        return ordersClient.getNextOrderId(customerId);
    }

    public OrderDto createCustomerOrderDto(final CartDto cartDto, final String customerId) {
        final OrderDto orderDto = CartsMapper.INSTANCE.toOrderDto(cartDto);

        return ordersClient.createCustomerOrder(orderDto, customerId);
    }
}
