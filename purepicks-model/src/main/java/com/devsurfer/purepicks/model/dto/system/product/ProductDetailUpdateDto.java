package com.devsurfer.purepicks.model.dto.system.product;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 15:28
 * description TODO
 */
@Tag(name = "商品详情更新实体", description = "商品详情更新实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailUpdateDto {

    @Schema(description = "商品详情id")
    private Long id;

    @Schema(description = "商品id")
    private Long productId;

    @Schema(description = "详情图片")
    private String imageUrl;

}
