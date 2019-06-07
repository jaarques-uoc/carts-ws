package com.jaarquesuoc.shop.carts.mappers;

import com.jaarquesuoc.shop.carts.dtos.CartDto;
import com.jaarquesuoc.shop.carts.dtos.OrderItemDto;
import com.jaarquesuoc.shop.carts.models.Cart;
import com.jaarquesuoc.shop.carts.models.OrderItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartsMapper {

    CartsMapper INSTANCE = Mappers.getMapper(CartsMapper.class);

    @Mapping(source = "orderItems", target = "orderItemDtos")
    CartDto toCartDto(Cart cart);

    @InheritInverseConfiguration
    Cart toCart(CartDto cartDto);

    @Mapping(source = "productId", target = "productDto.id")
    OrderItemDto toOrderItemDto(OrderItem orderItem);

    @InheritInverseConfiguration
    OrderItem toOrderItem(OrderItemDto orderItemDto);

    @Mapping(target = "date", ignore = true)
    @Mapping(target = "id", ignore = true)
    Cart replicate(Cart cart);
}
