package com.hyperlogy_ban_hang_2.repository;

import com.hyperlogy_ban_hang_2.entity.Category;
import com.hyperlogy_ban_hang_2.entity.Color;
import com.hyperlogy_ban_hang_2.entity.Material;
import com.hyperlogy_ban_hang_2.entity.Product;
import com.hyperlogy_ban_hang_2.entity.ProductDetail;
import com.hyperlogy_ban_hang_2.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    ProductDetail getProductDetailByCategoryAndColorAndMaterialAndProductAndSizeAndTrangThai(Category category, Color color, Material material, Product product, Size size, int status);

    List<ProductDetail> getProductDetailsByProductName(String productName);

    List<ProductDetail> getProductDetailsByProductId(Long productId);

    List<ProductDetail> getProductDetailsBySize_Id(Long sizeId);

    List<ProductDetail> getProductDetailsByColor_Id(Long colorId);

    List<ProductDetail> getProductDetailsByMaterial_Id(Long materialId);

    List<ProductDetail> getProductDetailsByCategory_Id(Long categoryId);

    @Query("SELECT p FROM ProductDetail p " +
            "WHERE p.category.id IS NULL OR p.category.id = :categoryId " +
            "OR p.color.id IS NULL OR p.color.id = :colorId " +
            "OR p.material.id IS NULL OR p.material.id = :materialId " +
            "OR p.product.id IS NULL OR p.product.id = :productId " +
            "OR p.size.id IS NULL OR p.size.id = :sizeId ")
    List<ProductDetail> timProductDetailTheoThuocTinh(
            @Param("categoryId") String categoryId,
            @Param("colorId") String colorId,
            @Param("materialId") String materialId,
            @Param("productId") String productId,
            @Param("sizeId") String sizeId
    );

    @Query("SELECT DISTINCT p.category FROM ProductDetail  p " +
            "WHERE p.product.id = :productId")
    List<Category> getCategory(@Param("productId") Long productId);

    @Query("SELECT DISTINCT p.material FROM ProductDetail  p " +
            "WHERE p.product.id = :productId")
    List<Material> getMaterial(@Param("productId") Long productId);

    @Query("SELECT DISTINCT p.color FROM ProductDetail  p " +
            "WHERE p.product.id = :productId")
    List<Color> getColor(@Param("productId") Long productId);

    @Query("SELECT DISTINCT p.size FROM ProductDetail  p " +
            "WHERE p.product.id = :productId")
    List<Size> getSize(@Param("productId") Long productId);
}
