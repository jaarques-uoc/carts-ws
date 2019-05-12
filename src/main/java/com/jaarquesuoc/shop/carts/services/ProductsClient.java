package com.jaarquesuoc.shop.carts.services;

import com.jaarquesuoc.shop.carts.dtos.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("products-ws")
public interface ProductsClient {

    @GetMapping("/products/{productId}")
    Product getProduct(@PathVariable("productId") final String productId);

    @GetMapping("/products")
    List<Product> getProducts(@RequestParam("ids") final List<String> productIds);
}
