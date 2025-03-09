package com.devsurfer.purepicks.manager.mapper;

import com.devsurfer.purepicks.model.dto.product.ProductSpecQueryDto;
import com.devsurfer.purepicks.model.entity.product.ProductSpec;
import com.devsurfer.purepicks.model.vo.product.ProductSpecVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductSpecMapper {

    List<ProductSpecVo> findProductSpecList(ProductSpecQueryDto productSpecQueryDto);

    ProductSpec selectBySpecName(String specName);

    ProductSpec selectBySpecNameNotId(@Param("id") Long id, @Param("specName") String specName);

    void insertProductSpec(ProductSpec productSpec);

    void updateProductSpec(ProductSpec productSpec);

    void deleteProductSpecById(Long productSpecId);

}
