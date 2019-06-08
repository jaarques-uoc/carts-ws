package com.jaarquesuoc.shop.carts.services;

import com.jaarquesuoc.shop.carts.dtos.NextOrderIdDto;
import com.jaarquesuoc.shop.carts.dtos.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("orders-ws")
public interface OrdersClient {

    @GetMapping("/customers/{customerId}/nextOrderId")
    NextOrderIdDto getNextOrderId(@PathVariable("customerId") final String customerId);

    @PostMapping("/customers/{customerId}/orders")
    OrderDto createCustomerOrder(@RequestBody final OrderDto orderDto,
                                 @PathVariable("customerId") final String customerId);
}
