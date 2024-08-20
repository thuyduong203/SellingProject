
package com.hyperlogy_ban_hang_2.mapper;

import com.hyperlogy_ban_hang_2.dto.CartDto;
import com.hyperlogy_ban_hang_2.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                CustomerMapper.class
        }
)
public interface CartMapper {

    @Mapping(source = "customerId", target = "customer.id")
    Cart mapCartDtoToCart(CartDto cartDto);

    @Mapping(source = "customer.id", target = "customerId")
    CartDto mapCartToCartDto(Cart cart);
}
