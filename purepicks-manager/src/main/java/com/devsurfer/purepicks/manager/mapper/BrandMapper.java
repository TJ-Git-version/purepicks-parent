package com.devsurfer.purepicks.manager.mapper;

import com.devsurfer.purepicks.model.dto.system.brand.BrandQueryDto;
import com.devsurfer.purepicks.model.entity.brand.Brand;
import com.devsurfer.purepicks.model.vo.system.brand.BrandVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {

    List<BrandVo> findBrandList(BrandQueryDto brandQueryDto);

    Long selectCountByName(String brandName);

    void insertBrand(Brand brand);

    Long selectCountByNameNotId(@Param("brandId") Long brandId, @Param("brandName") String brandName);

    void updateBrand(Brand brand);

    void deleteBrandBatch(@Param("idList") List<Long> idList);

    List<Brand> findCategoryBrandInBrandId(@Param("idList") List<Long> idList);

}
