package com.hyperlogy_ban_hang_2.service;

import com.hyperlogy_ban_hang_2.dto.ForeignKeyOfProductDetailDto;
import com.hyperlogy_ban_hang_2.entity.Category;
import com.hyperlogy_ban_hang_2.entity.Color;
import com.hyperlogy_ban_hang_2.entity.Material;
import com.hyperlogy_ban_hang_2.entity.ProductDetail;
import com.hyperlogy_ban_hang_2.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetailService {
    boolean checkSLTon(ProductDetail productDetail, int soLuong);

    //get 1 product theo full thuoc tinh khoa ngoai
    ProductDetail getProDetail(ForeignKeyOfProductDetailDto productDetailDto, int status);

    //get prductDetail theo 1 trong cac thuoc tinh khoa  ngoai
    List<ProductDetail> filterByAttribute(ForeignKeyOfProductDetailDto productDetailDto);

    List<ProductDetail> timSpTheoTen(String tenSP);

    List<ProductDetail> getbyCategory(Long categoryId);

    List<ProductDetail> getByProduct(Long productId);

    List<ProductDetail> getByMaterial(Long materialId);

    List<ProductDetail> getByColor(Long colorId);

    List<ProductDetail> getBySize(Long sizeId);

    List<ProductDetail> getAllEntity();

    Page<ProductDetail> getAllPhanTrang(Integer pageNo, Integer size);

    void importProductsFromExcel(String filePath);

    //    List<ProductDetail> timProductDetailTheoThuocTinh(
//            String categoryId,
//            String colorId,
//            String materialId,
//            String productId,
//            String sizeId
//    );
    List<ProductDetail> timProductDetailTheoThuocTinh(ForeignKeyOfProductDetailDto productDetailDto);

    List<Size> getSize(Long productId);

    List<Category> getCategory(Long productId);

    List<Color> getColor(Long productId);

    List<Material> getMaterial(Long productId);

}
