package com.hyperlogy_ban_hang_2.controller;

import com.hyperlogy_ban_hang_2.dto.ForeignKeyOfProductDetailDto;
import com.hyperlogy_ban_hang_2.dto.ProductDetailDto;
import com.hyperlogy_ban_hang_2.entity.Category;
import com.hyperlogy_ban_hang_2.entity.Color;
import com.hyperlogy_ban_hang_2.entity.Material;
import com.hyperlogy_ban_hang_2.entity.ProductDetail;
import com.hyperlogy_ban_hang_2.entity.Size;
import com.hyperlogy_ban_hang_2.repository.ProductDetailRepository;
import com.hyperlogy_ban_hang_2.service.impl.ProductDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/guest/product-detail")
@CrossOrigin(origins = {"*"})
public class ProductDetailController {

    private final ProductDetailServiceImpl productDetailService;
    private final ProductDetailRepository productDetailRepository;

    @GetMapping("/get-all-dto")
    public List<ProductDetailDto> getAllDto() {
        return productDetailService.getAll();
    }

    @GetMapping("/get-all")
    public List<ProductDetail> getAll() {
        return productDetailService.getAllEntity();
    }

    @PostMapping("/add")
    public void save(@RequestBody ProductDetailDto productDetailDto) {
        String add = productDetailService.saveOrUpdate(productDetailDto);
    }

    @PutMapping("/update")
    public String update(@RequestBody ProductDetailDto productDetailDto) {
        return productDetailService.saveOrUpdate(productDetailDto);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long id) {
        String delete = productDetailService.delete(id);
    }

    @GetMapping("/get-one/{id}")
    public ProductDetailDto getOne(@PathVariable("id") Long id) {
        return productDetailService.getOne(id);
    }

    @GetMapping("/get-all-entity")
    public List<ProductDetail> getAllEntity() {
        return productDetailRepository.findAll();
    }

    //lay 1 sp theo cac khoa ngoai
//    @GetMapping("/get-one-by-attribute")
//    public ProductDetail getOneByAttribute(
//            @RequestParam ForeignKeyOfProductDetailDto productDetailDto
//    ) {
//        return productDetailService.getProDetail(productDetailDto, 1);
//    }
    @GetMapping("/get-one-by-attribute")
    public ProductDetail getOneByAttribute(
            @RequestParam("categoryId") String categoryId,
            @RequestParam("productId") String productId,
            @RequestParam("colorId") String colorId,
            @RequestParam("sizeId") String sizeId,
            @RequestParam("materialId") String materialId
    ) {
        ForeignKeyOfProductDetailDto foreignKeyOfProductDetailDto = new ForeignKeyOfProductDetailDto(categoryId, productId, colorId, sizeId, materialId);
        return productDetailService.getProDetail(foreignKeyOfProductDetailDto, 1);
    }

    //loc sp theo cac thuoc tinh
    @GetMapping("/filter-by-attribute")
    public List<ProductDetail> filterByAttribute(
            @RequestBody ForeignKeyOfProductDetailDto productDetailDto
    ) {
        return productDetailService.filterByAttribute(productDetailDto);
    }

    //tim theo ten
    @GetMapping("/get-by-product-name")
    public List<ProductDetail> getByProductName(@RequestParam("productName") String productName) {
        return productDetailService.timSpTheoTen(productName);
    }

    //tim theo category
    @GetMapping("/get-by-category")
    public List<ProductDetail> getByCategory(@RequestParam("categoryId") Long categoryId) {
        return productDetailService.getbyCategory(categoryId);
    }

    @GetMapping("/get-all-phan-trang")
    public Page<ProductDetail> getAllPhanTrang(
            @RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo
    ) {
        return productDetailService.getAllPhanTrang(pageNo, 5);
    }

    @PostMapping("/import-excel")
    public String importProducts(@RequestParam("file") MultipartFile file) {
        try {
            File tempFile = File.createTempFile("temp", file.getOriginalFilename());
            file.transferTo(tempFile);
            productDetailService.importProductsFromExcel(tempFile.getAbsolutePath());
            tempFile.delete();
            return "Ok";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Hmm";
    }

    //    @GetMapping("/tim-theo-thuoc-tinh")
//    public List<ProductDetail> timTheoThuocTinh(@RequestBody ForeignKeyOfProductDetailDto productDetailDto) {
//        return productDetailService.timProductDetailTheoThuocTinh(productDetailDto.getCategoryId(), productDetailDto.getColorId(), productDetailDto.getMaterialId(), productDetailDto.getProductId(), productDetailDto.getSizeId());
//    }
    @GetMapping("/tim-theo-thuoc-tinh")
    public List<ProductDetail> timTheoThuocTinh(@RequestBody ForeignKeyOfProductDetailDto productDetailDto) {
        return productDetailService.timProductDetailTheoThuocTinh(productDetailDto);
    }

    @GetMapping("/get-color-by-product-id/{productId}")
    public List<Color> getColorByProductId(@PathVariable("productId") Long productId) {
        return productDetailService.getColor(productId);
    }

    @GetMapping("/get-size-by-product-id/{productId}")
    public List<Size> getSizeByProductId(@PathVariable("productId") Long productId) {
        return productDetailService.getSize(productId);
    }

    @GetMapping("/get-category-by-product-id/{productId}")
    public List<Category> getCateogryByProductId(@PathVariable("productId") Long productId) {
        return productDetailService.getCategory(productId);
    }

    @GetMapping("/get-material-by-product-id/{productId}")
    public List<Material> getMaterialByProductId(@PathVariable("productId") Long productId) {
        return productDetailService.getMaterial(productId);
    }



}
