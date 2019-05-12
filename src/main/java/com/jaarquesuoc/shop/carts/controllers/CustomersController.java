package com.jaarquesuoc.shop.carts.controllers;

import com.jaarquesuoc.shop.carts.dtos.CartDto;
import com.jaarquesuoc.shop.carts.dtos.OrderItemDto;
import com.jaarquesuoc.shop.carts.services.CartsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomersController {

    private final CartsService cartsService;

    @GetMapping("/customers/{customerId}/cart")
    public CartDto getCart(@PathVariable("customerId") final String customerId) {
        return cartsService.getCart(customerId);
    }

    @PostMapping("/customers/{customerId}/cart")
    public CartDto updateCart(@PathVariable("customerId") final String customerId,
                              @RequestBody OrderItemDto updatedOrderItemDto) {
        return cartsService.updateOrderItem(customerId, updatedOrderItemDto);
    }
}
