package com.devsurfer.purepicks.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.devsurfer.purepicks.manager.mapper.BrandMapper;
import com.devsurfer.purepicks.manager.service.BrandService;
import com.devsurfer.purepicks.model.dto.system.brand.BrandDeleteDto;
import com.devsurfer.purepicks.model.dto.system.brand.BrandInsertDto;
import com.devsurfer.purepicks.model.dto.system.brand.BrandQueryDto;
import com.devsurfer.purepicks.model.dto.system.brand.BrandUpdateDto;
import com.devsurfer.purepicks.model.entity.brand.Brand;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.vo.system.brand.BrandVo;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    @Override
    public PageInfo<BrandVo> pageList(BrandQueryDto brandQueryDto) {
        Page<BrandVo> page = PageHelper.startPage(brandQueryDto.getPageNum(), brandQueryDto.getPageSize());
        brandMapper.findBrandList(brandQueryDto);
        return page.toPageInfo();
    }

    @Override
    public void insertBrand(BrandInsertDto brandInsertDto) {
        // 判断品牌名称是否存在
        if (brandMapper.selectCountByName(brandInsertDto.getName()) > 0) {
            PurePicksException.error(ResultCodeEnum.BRAND_NAME_EXISTS_ERROR);
        }
        brandMapper.insertBrand(BeanUtil.copyProperties(brandInsertDto, Brand.class));
    }

    @Override
    public void updateBrand(BrandUpdateDto brandUpdateDto) {
        // 判断品牌名称是否存在
        if (brandMapper.selectCountByNameNotId(brandUpdateDto.getId(), brandUpdateDto.getName()) > 0) {
            PurePicksException.error(ResultCodeEnum.BRAND_NAME_EXISTS_ERROR);
        }
        brandMapper.updateBrand(BeanUtil.copyProperties(brandUpdateDto, Brand.class));
    }

    @Override
    public void deleteBrand(BrandDeleteDto brandDeleteDto) {
        // 判断当前品牌是否关联分类
        List<Brand> brandList = brandMapper.findCategoryBrandInBrandId(brandDeleteDto.getIdList());
        if (CollectionUtil.isNotEmpty(brandList)) {
            String brandName = brandList.stream().map(Brand::getName).reduce((a, b) -> a + "," + b).get();
            ResultCodeEnum resultCodeEnum = ResultCodeEnum.BRAND_BOUND_CATEGORY_ERROR;
            throw new PurePicksException(resultCodeEnum.getCode(), StrUtil.format(resultCodeEnum.getMessage(), brandName));
        }
        brandMapper.deleteBrandBatch(brandDeleteDto.getIdList());
    }
}
