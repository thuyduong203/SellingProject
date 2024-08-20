package com.hyperlogy_ban_hang_2.service.impl;

import com.hyperlogy_ban_hang_2.dto.ForeignKeyOfProductDetailDto;
import com.hyperlogy_ban_hang_2.dto.ProductDetailDto;
import com.hyperlogy_ban_hang_2.entity.Category;
import com.hyperlogy_ban_hang_2.entity.Color;
import com.hyperlogy_ban_hang_2.entity.Material;
import com.hyperlogy_ban_hang_2.entity.Product;
import com.hyperlogy_ban_hang_2.entity.ProductDetail;
import com.hyperlogy_ban_hang_2.entity.Size;
import com.hyperlogy_ban_hang_2.mapper.ProductDetailMapper;
import com.hyperlogy_ban_hang_2.mapper.ProductMapper;
import com.hyperlogy_ban_hang_2.repository.CategoryRepository;
import com.hyperlogy_ban_hang_2.repository.ColorRepository;
import com.hyperlogy_ban_hang_2.repository.MaterialRepository;
import com.hyperlogy_ban_hang_2.repository.ProductDetailRepository;
import com.hyperlogy_ban_hang_2.repository.ProductRepository;
import com.hyperlogy_ban_hang_2.repository.SizeRepository;
import com.hyperlogy_ban_hang_2.service.GeneralService;
import com.hyperlogy_ban_hang_2.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements GeneralService<ProductDetailDto>, ProductDetailService {


    private final ProductDetailMapper productDetailMapper;
    private final ProductDetailRepository productDetailRepository;
    private final CategoryRepository categoryRepository;
    private final ColorRepository colorRepository;
    private final MaterialRepository materialRepository;
    private final SizeRepository sizeRepository;
    private final ProductServiceImpl productService;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public List<ProductDetailDto> getAll() {
        return productDetailRepository.findAll().stream().map(productDetail -> productDetailMapper.mapProDetailToProDetailDto(productDetail)).toList();
    }

    @Override
    public String saveOrUpdate(ProductDetailDto productDetailDto) {
        Long idPr = productDetailDto.getProductId();
        Product product = productMapper.mapProDtoToPro(productService.getOne(idPr));
        Category category = categoryRepository.findById(productDetailDto.getCategoryId()).orElseThrow(() -> new RuntimeException("Khong tim thay category"));
        Material material = materialRepository.findById(productDetailDto.getMaterialId()).orElseThrow(() -> new RuntimeException("Khong tim thay marterial"));
        Color color = colorRepository.findById(productDetailDto.getColorId()).orElseThrow(() -> new RuntimeException("Khong tim thay color"));
        Size size = sizeRepository.findById(productDetailDto.getSizeId()).orElseThrow(() -> new RuntimeException("Khong tim thay size"));
        ProductDetail productDetail = productDetailMapper.mapProductDetailDtoToProductDetail(productDetailDto);
        productDetail.setProduct(product);
        productDetail.setCategory(category);
        productDetail.setColor(color);
        productDetail.setMaterial(material);
        productDetail.setSize(size);
        productDetailRepository.save(productDetail);
        return "Thanh cong";
    }

    @Override
    public ProductDetailDto getOne(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            ProductDetail productDetail = productDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay"));
            return productDetailMapper.mapProDetailToProDetailDto(productDetail);
        }
    }

    @Override
    public String delete(Long id) {
        if (id == null) {
            throw new RuntimeException("Id null");
        } else {
            ProductDetail productDetail = productDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Khong tim thay"));
            productDetailRepository.delete(productDetail);
        }
        return "Da xoa";
    }

    @Override
    public boolean checkSLTon(ProductDetail productDetail, int soLuong) {
        if (soLuong > productDetail.getQuanity()) {
            return false;
        } else {
            return true;
        }
    }

    //get one theo ForeignKey full
    public ProductDetail getProDetail(ForeignKeyOfProductDetailDto productDetailDto, int i) {
        Product product = productRepository.findById(Long.valueOf(productDetailDto.getProductId())).orElse(null);
        Material material = materialRepository.findById(Long.valueOf(productDetailDto.getMaterialId())).orElse(null);
        Color color = colorRepository.findById(Long.valueOf(productDetailDto.getColorId())).orElse(null);
        Size size = sizeRepository.findById(Long.valueOf(productDetailDto.getSizeId())).orElse(null);
        Category category = categoryRepository.findById(Long.valueOf(productDetailDto.getCategoryId())).orElse(null);
        return productDetailRepository.getProductDetailByCategoryAndColorAndMaterialAndProductAndSizeAndTrangThai(category, color, material, product, size, 1);
    }

    //loc sp theo cac thuoc tinh
    @Override
    public List<ProductDetail> filterByAttribute(ForeignKeyOfProductDetailDto productDetailDto) {
        List<ProductDetail> productDetails = productDetailRepository.findAll();
        if (productDetailDto.getCategoryId() != null) {
            for (int i = 0; i < productDetails.size(); i++) {
                if (productDetails.get(i).getCategory().getId() != Long.valueOf(productDetailDto.getCategoryId())) {
                    productDetails.remove(i);
                }
            }
        }
        if (productDetailDto.getColorId() != null) {
            for (int i = 0; i < productDetails.size(); i++) {
                if (productDetails.get(i).getColor().getId() != Long.valueOf(productDetailDto.getColorId())) {
                    productDetails.remove(i);
                }
            }
        }
        if (productDetailDto.getProductId() != null) {
            for (int i = 0; i < productDetails.size(); i++) {
                if (productDetails.get(i).getProduct().getId() != Long.valueOf(productDetailDto.getProductId())) {
                    productDetails.remove(i);
                }
            }
        }
        if (productDetailDto.getMaterialId() != null) {
            for (int i = 0; i < productDetails.size(); i++) {
                if (productDetails.get(i).getMaterial().getId() != Long.valueOf(productDetailDto.getMaterialId())) {
                    productDetails.remove(i);
                }
            }
        }
        if (productDetailDto.getSizeId() != null) {
            for (int i = 0; i < productDetails.size(); i++) {
                if (productDetails.get(i).getSize().getId() != Long.valueOf(productDetailDto.getSizeId())) {
                    productDetails.remove(i);
                }
            }
        }
        return productDetails;
    }

    @Override
    public List<ProductDetail> timSpTheoTen(String tenSP) {
        if (tenSP == null) {
            return productDetailRepository.findAll();
        } else {
            return productDetailRepository.getProductDetailsByProductName(tenSP);
        }
    }

    @Override
    public List<ProductDetail> getbyCategory(Long categoryId) {
        return productDetailRepository.getProductDetailsByCategory_Id(categoryId);
    }

    @Override
    public List<ProductDetail> getByProduct(Long productId) {
        return productDetailRepository.getProductDetailsByProductId(productId);
    }

    @Override
    public List<ProductDetail> getByMaterial(Long materialId) {
        return productDetailRepository.getProductDetailsByMaterial_Id(materialId);
    }

    @Override
    public List<ProductDetail> getByColor(Long colorId) {
        return productDetailRepository.getProductDetailsByColor_Id(colorId);
    }

    @Override
    public List<ProductDetail> getBySize(Long sizeId) {
        return productDetailRepository.getProductDetailsBySize_Id(sizeId);
    }

    @Override
    public List<ProductDetail> getAllEntity() {
        return productDetailRepository.findAll();
    }

    @Override
    public Page<ProductDetail> getAllPhanTrang(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return productDetailRepository.findAll(pageable);
    }

    @Override
    public void importProductsFromExcel(String filePath) {
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    // Bỏ qua dòng tiêu đề
                    continue;
                }
//file excel co thu tu cac truong dung de set:
                ProductDetailDto productDetail = ProductDetailDto.builder()
                        .productId(Long.valueOf((long) row.getCell(1).getNumericCellValue()))
                        .categoryId(Long.valueOf((long) row.getCell(2).getNumericCellValue()))
                        .sizeId(Long.valueOf((long) row.getCell(3).getNumericCellValue()))
                        .materialId(Long.valueOf((long) row.getCell(4).getNumericCellValue()))
                        .colorId(Long.valueOf((long) row.getCell(5).getNumericCellValue()))
                        .style(String.valueOf(row.getCell(6).getNumericCellValue()))
                        .quanity(((int) row.getCell(7).getNumericCellValue()))
                        //.price(BigDecimal.valueOf(Long.valueOf(row.getCell(8).getStringCellValue()))
                        .moTa(row.getCell(9).getStringCellValue())
                        .trangThai((int) row.getCell(10).getNumericCellValue())
                        .build();
                productDetail.setPrice(BigDecimal.valueOf(Long.valueOf((long) row.getCell(8).getNumericCellValue())));
                productDetailRepository.save(productDetailMapper.mapProductDetailDtoToProductDetail(productDetail));
            }

            workbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public List<ProductDetail> timProductDetailTheoThuocTinh(ForeignKeyOfProductDetailDto productDetailDto) {
//        return productDetailRepository.timProductDetailTheoThuocTinh(Long.valueOf(productDetailDto.getCategoryId()), Long.valueOf(productDetailDto.getColorId()), Long.valueOf(productDetailDto.getMaterialId()), Long.valueOf(productDetailDto.getProductId()), Long.valueOf(productDetailDto.getSizeId()));
//    }

    @Override
    public List<ProductDetail> timProductDetailTheoThuocTinh(ForeignKeyOfProductDetailDto productDetailDto) {
        return productDetailRepository.timProductDetailTheoThuocTinh(productDetailDto.getCategoryId(), productDetailDto.getColorId(), productDetailDto.getMaterialId(), productDetailDto.getProductId(), productDetailDto.getSizeId());
    }

    @Override
    public List<Size> getSize(Long productId) {
        return productDetailRepository.getSize(productId);
    }

    @Override
    public List<Category> getCategory(Long productId) {
        return productDetailRepository.getCategory(productId);
    }

    @Override
    public List<Color> getColor(Long productId) {
        return productDetailRepository.getColor(productId);
    }

    @Override
    public List<Material> getMaterial(Long productId) {
        return productDetailRepository.getMaterial(productId);
    }


//    @Override
//    public List<ProductDetail> timProductDetailTheoThuocTinh(String categoryId, String colorId, String materialId, String productId, String sizeId) {
//        return productDetailRepository.timProductDetailTheoThuocTinh(categoryId, colorId, materialId, productId, sizeId);
//    }

}
