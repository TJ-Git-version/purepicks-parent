package com.devsurfer.purepicks.product.service.impl;

import com.devsurfer.purepicks.model.vo.h5.IndexVo;
import com.devsurfer.purepicks.product.mapper.CategoryMapper;
import com.devsurfer.purepicks.product.mapper.ProductMapper;
import com.devsurfer.purepicks.product.service.IndexService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/13 20:39
 * description TODO
 */
@Service
@AllArgsConstructor
public class IndexServiceImpl implements IndexService {

    private final CategoryMapper categoryMapper;

    private final ProductMapper productMapper;

    @Override
    public IndexVo findIndexDate() {
        return new IndexVo(categoryMapper.findOneCategory(), productMapper.findProductSkuList());
    }
}
