package com.hyperlogy_ban_hang_2.mapper;

import com.hyperlogy_ban_hang_2.dto.ProductDetailDto;
import com.hyperlogy_ban_hang_2.entity.ProductDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                ProductMapper.class,
                SizeMapper.class,
                ColorMapper.class,
                MarterialMapper.class,
                CategoryMapper.class
        }
)
public interface ProductDetailMapper {
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "size.id", target = "sizeId")
    @Mapping(source = "color.id", target = "colorId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "material.id", target = "materialId")
    ProductDetailDto mapProDetailToProDetailDto(ProductDetail productDetail);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "sizeId", target = "size.id")
    @Mapping(source = "colorId", target = "color.id")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "materialId", target = "material.id")
    ProductDetail mapProductDetailDtoToProductDetail(ProductDetailDto productDetailDto);
}
