package com.jaarquesuoc.shop.carts.controllers;

import com.jaarquesuoc.shop.carts.dtos.CartDto;
import com.jaarquesuoc.shop.carts.services.CartsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InitialisationController {

    private final CartsService cartsService;

    @GetMapping("/init")
    public List<CartDto> initialiseDB() {
        cartsService.cleanDb();
        return cartsService.getAllCartDtos();
    }
}
