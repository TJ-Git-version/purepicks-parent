package com.devsurfer.purepicks.model.dto.system.product;

import com.devsurfer.purepicks.model.dto.base.BaseQueryPageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Tag(name = "商品查询实体", description = "商品查询实体")
@Data
public class ProductQueryDto extends BaseQueryPageDto {

    @Schema(description = "查询字段")
    private String keyword;

    @Schema(description = "分类查询")
    private Long categoryId;

}
