package com.devsurfer.purepicks.product.service.impl;

import com.devsurfer.purepicks.model.vo.brand.BrandVo;
import com.devsurfer.purepicks.product.mapper.BrandMapper;
import com.devsurfer.purepicks.product.service.BrandService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.devsurfer.purepicks.model.constant.RedisKeyConstant.APPLET_BRAND_KEY;
import static com.devsurfer.purepicks.model.constant.RedisKeyConstant.APPLET_BRAND_PREFIX;

/**
 * @author Dev Surfer
 */
@Service
public class BrandServiceImpl implements BrandService {

    private final BrandMapper brandMapper;

    public BrandServiceImpl(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    @Cacheable(
            value = APPLET_BRAND_PREFIX,
            key = APPLET_BRAND_KEY,
            unless = "#result.size() == 0"
    )
    @Override
    public List<BrandVo> findBrandAll() {
        return brandMapper.findBrandAll();
    }
}
