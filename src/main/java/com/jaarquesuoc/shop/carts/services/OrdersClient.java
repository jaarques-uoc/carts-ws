package com.jaarquesuoc.shop.carts.services;

import com.jaarquesuoc.shop.carts.models.NextOrderId;
import com.jaarquesuoc.shop.carts.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("orders-ws")
public interface OrdersClient {

    @GetMapping("/customers/{customerId}/nextOrderId")
    NextOrderId getNextOrderId(@PathVariable("customerId") final String customerId);
}
