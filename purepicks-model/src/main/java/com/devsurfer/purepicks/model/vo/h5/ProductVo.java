package com.devsurfer.purepicks.model.vo.h5;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Dev Surfer
 */
@Tag(name = "H5-商品响应实体", description = "商品响应实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVo implements Serializable {

    @Schema(description = "商品sku详情信息")
    private ProductSkuVo productSku;

    @Schema(description = "商品信息详情信息")
    private Product product;

    @Schema(description = "商品规格列表")
    private List<JSONObject> specValueList;

    @Schema(description = "商品详情图片列表")
    private List<String> detailsImageUrlList;

    @Schema(description = "商品规格Map")
    private Map<String, Object> skuSpecValueMap;

    @Schema(description = "轮播图列表")
    private List<String> sliderUrlList;

}
