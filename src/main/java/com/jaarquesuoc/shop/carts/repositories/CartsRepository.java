package com.jaarquesuoc.shop.carts.repositories;

import com.jaarquesuoc.shop.carts.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CartsRepository extends MongoRepository<Cart, String> {

    List<Cart> findAllByOrderIdOrderByDateAsc(final String orderId);

    Optional<Cart> findFirstByOrderIdOrderByDateDesc(final String orderId);
}
