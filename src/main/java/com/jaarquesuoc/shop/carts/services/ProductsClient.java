package com.jaarquesuoc.shop.carts.services;

import com.jaarquesuoc.shop.carts.models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("products-ws")
public interface ProductsClient {

    @GetMapping("/products/{productId}")
    Product getProduct(@PathVariable("productId") final String productId);
}
