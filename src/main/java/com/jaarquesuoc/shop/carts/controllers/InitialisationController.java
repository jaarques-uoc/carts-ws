package com.jaarquesuoc.shop.carts.controllers;

import com.jaarquesuoc.shop.carts.dtos.InitialisationDto;
import com.jaarquesuoc.shop.carts.services.CartsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jaarquesuoc.shop.carts.dtos.InitialisationDto.InitialisationStatus.OK;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InitialisationController {

    private final CartsService cartsService;

    @GetMapping("/init")
    public InitialisationDto initialiseDB() {
        cartsService.cleanDb();

        return InitialisationDto.builder()
                .initialisationStatus(OK)
                .metadata(cartsService.getAllCartDtos())
                .build();
    }
}
