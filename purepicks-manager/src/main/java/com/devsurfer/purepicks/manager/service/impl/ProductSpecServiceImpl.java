package com.devsurfer.purepicks.manager.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.devsurfer.purepicks.manager.mapper.ProductSpecMapper;
import com.devsurfer.purepicks.manager.service.ProductSpecService;
import com.devsurfer.purepicks.model.dto.system.product.ProductSpecDeleteDto;
import com.devsurfer.purepicks.model.dto.system.product.ProductSpecInsertDto;
import com.devsurfer.purepicks.model.dto.system.product.ProductSpecQueryDto;
import com.devsurfer.purepicks.model.dto.system.product.ProductSpecUpdateDto;
import com.devsurfer.purepicks.model.entity.product.ProductSpec;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.vo.system.product.ProductSpecVo;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSpecServiceImpl implements ProductSpecService {

    private final ProductSpecMapper productSpecMapper;

    public ProductSpecServiceImpl(ProductSpecMapper productSpecMapper) {
        this.productSpecMapper = productSpecMapper;
    }

    @Override
    public PageInfo<ProductSpecVo> pageList(ProductSpecQueryDto productSpecQueryDto) {
        try (Page<ProductSpecVo> page = PageHelper.startPage(productSpecQueryDto.getPageNum(), productSpecQueryDto.getPageSize())) {
            productSpecMapper.findProductSpecList(productSpecQueryDto);
            return page.toPageInfo();
        }
    }

    @Override
    public List<ProductSpecVo> listAllProductSpec() {
        return productSpecMapper.findProductSpecList(null);
    }

    @Override
    public void insertProductSpec(ProductSpecInsertDto productSpecInsertDto) {
        ProductSpec productSpec = productSpecMapper.selectBySpecName(productSpecInsertDto.getSpecName());
        if (productSpec != null) {
            PurePicksException.error(ResultCodeEnum.PRODUCT_SPEC_NAME_EXIST_ERROR);
        }
        productSpecMapper.insertProductSpec(BeanUtil.copyProperties(productSpecInsertDto, ProductSpec.class));
    }

    @Override
    public void updateProductSpec(ProductSpecUpdateDto productSpecUpdateDto) {
        ProductSpec productSpec = productSpecMapper.selectBySpecNameNotId(productSpecUpdateDto.getId(), productSpecUpdateDto.getSpecName());
        if (productSpec != null) {
            PurePicksException.error(ResultCodeEnum.PRODUCT_SPEC_NAME_EXIST_ERROR);
        }
        productSpecMapper.updateProductSpec(BeanUtil.copyProperties(productSpecUpdateDto, ProductSpec.class));
    }

    @Override
    public void deleteProductSpec(ProductSpecDeleteDto productSpecDeleteDto) {
        productSpecMapper.deleteProductSpecById(productSpecDeleteDto.getProductSpecId());
    }

}
