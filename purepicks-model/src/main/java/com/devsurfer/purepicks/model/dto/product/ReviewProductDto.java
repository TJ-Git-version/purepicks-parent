package com.devsurfer.purepicks.model.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 22:05
 * description TODO
 */
@Tag(name = "商品审核实体", description = "商品审核实体")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewProductDto {
    @Schema(description = "商品id")
    private Long productId;

    @Schema(description = "审核状态：0-未审核，1-通过，2-驳回")
    private Integer reviewStatus;

    @Schema(description = "审核信息")
    private String reviewInfo;
}
