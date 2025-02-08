package com.devsurfer.purepicks.model.vo.system.product;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Tag(name = "商品规格响应实体", description = "商品规格响应实体")
@Data
public class ProductSpecVo {

    @Schema(description = "商品规格ID")
    private Long id;

    @Schema(description = "商品规格名称")
    private String specName;

    @Schema(description = "商品规格值")
    private String specValue;

    @Schema(description = "商品规格创建时间")
    private String createTime;

    @Schema(description = "商品规格更新时间")
    private String updateTime;

}
