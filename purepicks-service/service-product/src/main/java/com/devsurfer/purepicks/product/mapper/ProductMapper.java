package com.devsurfer.purepicks.product.mapper;

import com.devsurfer.purepicks.model.vo.h5.ProductSkuVo;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/13 20:40
 * description TODO
 */
public interface ProductMapper {
    List<ProductSkuVo> findProductSkuList();

}
