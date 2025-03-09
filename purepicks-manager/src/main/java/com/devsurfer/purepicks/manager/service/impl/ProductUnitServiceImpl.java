package com.devsurfer.purepicks.manager.service.impl;

import com.devsurfer.purepicks.manager.mapper.ProductUnitMapper;
import com.devsurfer.purepicks.manager.service.ProductUnitService;
import com.devsurfer.purepicks.model.vo.product.ProductUnitVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 15:31
 * description TODO
 */
@Service
public class ProductUnitServiceImpl implements ProductUnitService {

    private final ProductUnitMapper productUnitMapper;

    public ProductUnitServiceImpl(ProductUnitMapper productUnitMapper) {
        this.productUnitMapper = productUnitMapper;
    }


    @Override
    public List<ProductUnitVo> listAllProductUnit() {
        return productUnitMapper.findProductUnitList();
    }
}
