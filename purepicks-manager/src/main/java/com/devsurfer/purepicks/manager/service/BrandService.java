package com.devsurfer.purepicks.manager.service;

import com.devsurfer.purepicks.model.dto.system.brand.BrandDeleteDto;
import com.devsurfer.purepicks.model.dto.system.brand.BrandInsertDto;
import com.devsurfer.purepicks.model.dto.system.brand.BrandQueryDto;
import com.devsurfer.purepicks.model.dto.system.brand.BrandUpdateDto;
import com.devsurfer.purepicks.model.vo.system.brand.BrandVo;
import com.github.pagehelper.PageInfo;

public interface BrandService {

    PageInfo<BrandVo> pageList(BrandQueryDto brandQueryDto);

    void insertBrand(BrandInsertDto brandInsertDto);

    void updateBrand(BrandUpdateDto brandUpdateDto);

    void deleteBrand(BrandDeleteDto brandDeleteDto);

}
