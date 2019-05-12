package com.jaarquesuoc.shop.carts.services;

import com.jaarquesuoc.shop.carts.models.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductsService {

    private final ProductsClient productsClient;

    public Product getProduct(final String productId) {
        return productsClient.getProduct(productId);
    }
}