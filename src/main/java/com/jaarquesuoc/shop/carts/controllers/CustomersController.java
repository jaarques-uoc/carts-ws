package com.jaarquesuoc.shop.carts.controllers;

import com.jaarquesuoc.shop.carts.dtos.CartDto;
import com.jaarquesuoc.shop.carts.dtos.OrderItemDto;
import com.jaarquesuoc.shop.carts.dtos.ProductDto;
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

    @GetMapping("/customers/{customerId}/carts/current")
    public CartDto getCart(@PathVariable("customerId") final String customerId) {
        return cartsService.getCartDto(customerId);
    }

    @PostMapping("/customers/{customerId}/carts/current/items")
    public CartDto updateItemInCart(@PathVariable("customerId") final String customerId,
                                    @RequestBody OrderItemDto updatedOrderItemDto) {
        return cartsService.upsertOrderItem(customerId, updatedOrderItemDto);
    }

    @PostMapping("/customers/{customerId}/carts/current/items/increment")
    public CartDto incrementItemInCart(@PathVariable("customerId") final String customerId,
                                       @RequestBody ProductDto productDto) {
        return cartsService.incrementOrderItem(customerId, productDto);
    }
}
