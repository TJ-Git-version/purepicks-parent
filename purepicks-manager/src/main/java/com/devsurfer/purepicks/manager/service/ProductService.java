package com.devsurfer.purepicks.manager.service;

import com.devsurfer.purepicks.model.dto.product.ProductInsertDto;
import com.devsurfer.purepicks.model.dto.product.ProductQueryDto;
import com.devsurfer.purepicks.model.dto.product.ProductUpdateDto;
import com.devsurfer.purepicks.model.dto.product.ReviewProductDto;
import com.devsurfer.purepicks.model.vo.product.ProductVo;
import com.github.pagehelper.PageInfo;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 16:12
 * description TODO
 */
public interface ProductService {

    void insertProduct(ProductInsertDto productInsertDto);

    ProductVo findProductById(Long productId);

    void editProduct(ProductUpdateDto productUpdateDto);

    void deleteProduct(Long productId);

    PageInfo<ProductVo> pageList(ProductQueryDto productQueryDto);

    void reviewProduct(ReviewProductDto reviewProductDto);

    void updateStatus(Long productId, Integer status);

}
