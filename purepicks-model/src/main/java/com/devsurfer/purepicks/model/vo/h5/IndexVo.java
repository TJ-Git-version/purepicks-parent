package com.devsurfer.purepicks.model.vo.h5;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/11 23:33
 * description TODO
 */
@Tag(name = "H5-首页列表数据", description = "首页列表数据")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexVo {

    @Schema(description = "一级分类列表")
    private List<CategoryVo> categoryList;

    @Schema(description = "前二十条数据的商品列表")
    private List<ProductSkuVo> productSkuList;

}
