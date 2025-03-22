package com.devsurfer.purepicks.product.service.impl;

import com.devsurfer.purepicks.model.vo.h5.IndexVo;
import com.devsurfer.purepicks.product.mapper.ProductMapper;
import com.devsurfer.purepicks.product.service.CategoryService;
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

    private final CategoryService categoryService;


    private final ProductMapper productMapper;

    @Override
    public IndexVo findIndexDate() {
        return new IndexVo(categoryService.findOneCategory(), productMapper.findProductSkuList());
    }
}
