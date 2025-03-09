package com.devsurfer.purepicks.model.dto.brand;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.List;

@Data
@Tag(name = "品牌信息删除实体", description = "品牌信息删除实体")
public class BrandDeleteDto {

    @Schema(description = "删除品牌id集合")
    private List<Long> idList;

}
