package com.hyperlogy_ban_hang_2.mapper;

import com.hyperlogy_ban_hang_2.dto.ProductDto;
import com.hyperlogy_ban_hang_2.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product mapProDtoToPro(ProductDto productDto);

    ProductDto mapProToProDto(Product product);
}
