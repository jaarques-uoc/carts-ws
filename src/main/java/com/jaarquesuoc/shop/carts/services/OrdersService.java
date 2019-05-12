package com.jaarquesuoc.shop.carts.services;

import com.jaarquesuoc.shop.carts.models.NextOrderId;
import com.jaarquesuoc.shop.carts.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrdersService {

    private final OrdersClient ordersClient;

    public NextOrderId getNextOrderId(final String customerId) {
        return ordersClient.getNextOrderId(customerId);
    }
}
