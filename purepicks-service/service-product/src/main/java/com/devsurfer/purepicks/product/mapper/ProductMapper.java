package com.devsurfer.purepicks.product.mapper;

import com.devsurfer.purepicks.model.dto.h5.ProductSkuDto;
import com.devsurfer.purepicks.model.vo.h5.Product;
import com.devsurfer.purepicks.model.vo.h5.ProductSkuVo;
import com.devsurfer.purepicks.model.vo.product.ProductDetailVo;
import com.devsurfer.purepicks.model.vo.product.ProductSpecVo;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/13 20:40
 * description TODO
 */
public interface ProductMapper {
    List<ProductSkuVo> findProductSkuList();

    List<ProductSkuVo> findProductSkuPage(ProductSkuDto productSkuDto);

    ProductSkuVo getProductSkuById(String skuId);

    Product findProductById(Long productId);

    ProductSpecVo findProductSpecById(Long specId);

    List<ProductDetailVo> findProductDetailByProductId(Long productId);
}
