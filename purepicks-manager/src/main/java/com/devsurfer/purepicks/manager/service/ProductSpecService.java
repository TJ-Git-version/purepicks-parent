package com.devsurfer.purepicks.manager.service;

import com.devsurfer.purepicks.model.dto.system.product.ProductSpecDeleteDto;
import com.devsurfer.purepicks.model.dto.system.product.ProductSpecInsertDto;
import com.devsurfer.purepicks.model.dto.system.product.ProductSpecQueryDto;
import com.devsurfer.purepicks.model.dto.system.product.ProductSpecUpdateDto;
import com.devsurfer.purepicks.model.vo.system.product.ProductSpecVo;
import com.github.pagehelper.PageInfo;

public interface ProductSpecService {

    PageInfo<ProductSpecVo> pageList(ProductSpecQueryDto productSpecQueryDto);

    void insertProductSpec(ProductSpecInsertDto productSpecInsertDto);

    void updateProductSpec(ProductSpecUpdateDto productSpecUpdateDto);

    void deleteProductSpec(ProductSpecDeleteDto productSpecDeleteDto);

}
