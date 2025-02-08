package com.devsurfer.purepicks.model.dto.system.product;

import com.devsurfer.purepicks.model.dto.base.BaseQueryPageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Tag(name = "商品规格查询实体", description = "商品规格查询实体")
@Data
public class ProductSpecQueryDto extends BaseQueryPageDto {

    @Schema(description = "查询字段")
    private String keyword;

}
