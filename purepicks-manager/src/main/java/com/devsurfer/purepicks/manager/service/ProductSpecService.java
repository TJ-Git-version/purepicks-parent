package com.devsurfer.purepicks.manager.service;

import com.devsurfer.purepicks.model.dto.product.ProductSpecDeleteDto;
import com.devsurfer.purepicks.model.dto.product.ProductSpecInsertDto;
import com.devsurfer.purepicks.model.dto.product.ProductSpecQueryDto;
import com.devsurfer.purepicks.model.dto.product.ProductSpecUpdateDto;
import com.devsurfer.purepicks.model.vo.product.ProductSpecVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductSpecService {

    PageInfo<ProductSpecVo> pageList(ProductSpecQueryDto productSpecQueryDto);

    void insertProductSpec(ProductSpecInsertDto productSpecInsertDto);

    void updateProductSpec(ProductSpecUpdateDto productSpecUpdateDto);

    void deleteProductSpec(ProductSpecDeleteDto productSpecDeleteDto);

    List<ProductSpecVo> listAllProductSpec();

}
