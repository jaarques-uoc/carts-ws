package com.jaarquesuoc.shop.carts.services;

import com.jaarquesuoc.shop.carts.dtos.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("customers-ws")
public interface CustomersClient {

    @GetMapping("/customers/{customerId}")
    CustomerDto getCustomer(@PathVariable("customerId") final String customerId);
}
