package com.devsurfer.purepicks.model.entity.brand;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 14:34
 * description TODO
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryBrand extends BaseEntity {

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "品牌ID")
    private Long brandId;

}
