package com.jaarquesuoc.shop.carts.controllers;

import com.jaarquesuoc.shop.carts.dtos.Cart;
import com.jaarquesuoc.shop.carts.dtos.OrderItem;
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
    public Cart getCart(@PathVariable("customerId") final String customerId) {
        return cartsService.getCart(customerId);
    }

    @PostMapping("/customers/{customerId}/cart")
    public Cart updateCart(@PathVariable("customerId") final String customerId,
                           @RequestBody OrderItem updatedOrderItem) {
        return cartsService.updateOrderItem(customerId, updatedOrderItem);
    }
}
