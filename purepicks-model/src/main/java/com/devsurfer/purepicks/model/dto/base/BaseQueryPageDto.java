package com.devsurfer.purepicks.model.dto.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 17:50
 * description TODO
 */
@Data
public class BaseQueryPageDto {

    @Schema(description = "当前页码")
    private Integer pageNum;

    @Schema(description = "当前页显示数量")
    private Integer pageSize;

}
