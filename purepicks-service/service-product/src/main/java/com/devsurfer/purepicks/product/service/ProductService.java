package com.devsurfer.purepicks.product.service;

import com.devsurfer.purepicks.model.dto.h5.ProductSkuDto;
import com.devsurfer.purepicks.model.vo.h5.ProductSkuVo;
import com.devsurfer.purepicks.model.vo.h5.ProductVo;
import com.github.pagehelper.PageInfo;

/**
 * @author Dev Surfer
 */
public interface ProductService {

    PageInfo<ProductSkuVo> pageProductList(Integer page, Integer limit, ProductSkuDto productSkuDto);

    ProductVo findProductItemBySkuId(String skuId);

}
