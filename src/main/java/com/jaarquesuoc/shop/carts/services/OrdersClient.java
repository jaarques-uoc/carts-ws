package com.jaarquesuoc.shop.carts.services;

import com.jaarquesuoc.shop.carts.dtos.NextOrderIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("orders-ws")
public interface OrdersClient {

    @GetMapping("/customers/{customerId}/nextOrderId")
    NextOrderIdDto getNextOrderId(@PathVariable("customerId") final String customerId);
}
