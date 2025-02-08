package com.devsurfer.purepicks.model.vo.system.brand;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.Date;

@Data
@Tag(name = "品牌信息VO", description = "品牌信息VO")
public class BrandVo {

    @Schema(description = "品牌ID")
    private Long id;

    @Schema(description = "品牌名称")
    private String name;

    @Schema(description = "品牌LOGO")
    private String logo;

    @Schema(description = "品牌排序")
    private Integer sort;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
