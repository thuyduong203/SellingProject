package com.hyperlogy_ban_hang_2.mapper;

import com.hyperlogy_ban_hang_2.dto.CartDetailDto;
import com.hyperlogy_ban_hang_2.entity.CartDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                CartMapper.class,
                ProductDetailMapper.class
        }
)
public interface CartDetailMapper {

    @Mapping(source = "cartId", target = "cart.id")
    @Mapping(source = "productDetailId", target = "productDetail.id")
    CartDetail mapCartDetailDtoToCartDetail(CartDetailDto cartDetailDto);

    @Mapping(source = "cart.id", target = "cartId")
    @Mapping(source = "productDetail.id", target = "productDetailId")
    CartDetailDto mapCartDetailToCartDetailDto(CartDetail cartDetail);
}
