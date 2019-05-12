package com.jaarquesuoc.shop.carts.services;

import com.jaarquesuoc.shop.carts.dtos.NextOrderIdDto;
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
}
