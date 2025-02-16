package com.devsurfer.purepicks.manager.service;

import com.devsurfer.purepicks.model.dto.system.brand.*;
import com.devsurfer.purepicks.model.vo.system.brand.BrandVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {

    PageInfo<BrandVo> pageList(BrandQueryDto brandQueryDto);

    void insertBrand(BrandInsertDto brandInsertDto);

    void updateBrand(BrandUpdateDto brandUpdateDto);

    void deleteBrand(BrandDeleteDto brandDeleteDto);

    void insertCategoryBrand(CategoryBrandDto categoryBrandDto);

    List<BrandVo> listBrandByCategoryId(Long categoryId);

}
