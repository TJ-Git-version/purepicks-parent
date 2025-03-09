package com.devsurfer.purepicks.manager.mapper;

import com.devsurfer.purepicks.model.dto.product.ProductQueryDto;
import com.devsurfer.purepicks.model.dto.product.ReviewProductDto;
import com.devsurfer.purepicks.model.entity.product.Product;
import com.devsurfer.purepicks.model.entity.product.ProductDetail;
import com.devsurfer.purepicks.model.entity.product.ProductSku;
import com.devsurfer.purepicks.model.vo.product.ProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 16:13
 * description TODO
 */
public interface ProductMapper {

    void insert(Product product);

    void insertProductSkuBatch(@Param("productSkuList") List<ProductSku> productSkuList);

    void insertProductDetail(ProductDetail productDetail);

    ProductVo findProductById(Long productId);

    void update(Product product);

    void deleteProductSkuByProductId(Long productId);

    void updateProductDetail(ProductDetail productDetail);

    void deleteProductById(Long productId);

    void deleteProductDetailByProductId(Long productId);

    List<ProductVo> findProductList(ProductQueryDto productQueryDto);

    void reviewProduct(ReviewProductDto reviewProductDto);

    void updateStatus(@Param("productId") Long productId,
                      @Param("status") Integer status);

}
