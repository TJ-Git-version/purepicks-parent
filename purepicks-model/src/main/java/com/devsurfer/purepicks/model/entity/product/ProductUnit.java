package com.devsurfer.purepicks.model.entity.product;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 15:28
 * description TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUnit extends BaseEntity {

    @Schema(description = "单元名称")
    private String name;

}
