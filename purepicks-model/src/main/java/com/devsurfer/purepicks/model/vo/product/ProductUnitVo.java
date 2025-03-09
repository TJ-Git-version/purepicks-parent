package com.devsurfer.purepicks.model.vo.product;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 15:29
 * description TODO
 */
@Tag(name = "商品单元响应实体", description = "商品单元响应实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUnitVo {

    @Schema(description = "商品单元ID")
    private Long id;

    @Schema(description = "商品单元名称")
    private String name;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

}
