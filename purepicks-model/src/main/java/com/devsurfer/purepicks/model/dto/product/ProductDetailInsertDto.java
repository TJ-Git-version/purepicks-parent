package com.devsurfer.purepicks.model.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 15:28
 * description TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailInsertDto {

    @Schema(description = "商品id")
    private Long productId;

    @Schema(description = "详情图片")
    private String imageUrl;

}
