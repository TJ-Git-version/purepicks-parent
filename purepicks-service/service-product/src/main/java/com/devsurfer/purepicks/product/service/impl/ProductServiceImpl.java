package com.devsurfer.purepicks.product.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.devsurfer.purepicks.model.dto.h5.ProductSkuDto;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.vo.h5.Product;
import com.devsurfer.purepicks.model.vo.h5.ProductSkuVo;
import com.devsurfer.purepicks.model.vo.h5.ProductVo;
import com.devsurfer.purepicks.model.vo.product.ProductDetailVo;
import com.devsurfer.purepicks.model.vo.product.ProductSpecVo;
import com.devsurfer.purepicks.product.mapper.ProductMapper;
import com.devsurfer.purepicks.product.service.ProductService;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Dev Surfer
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }


    @Override
    public PageInfo<ProductSkuVo> pageProductList(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        try (Page<ProductSkuVo> pageInfo = PageHelper.startPage(page, limit)) {
            productMapper.findProductSkuPage(productSkuDto);
            return pageInfo.toPageInfo();
        }
    }

    @Override
    public ProductVo findProductItemBySkuId(Long skuId) {
        ProductVo productVo = new ProductVo();
        ProductSkuVo productSkuVo = productMapper.getProductSkuById(skuId);
        if (productSkuVo == null || productSkuVo.getStatus() == 0) {
            PurePicksException.error(ResultCodeEnum.PRODUCT_SKU_EXIST_ERROR);
        }
        productVo.setProductSku(productSkuVo);
        Product product = productMapper.findProductById(productSkuVo.getProductId());
        if (product == null || product.getAuditStatus() == 0 || product.getAuditStatus() == 2) {
            PurePicksException.error(ResultCodeEnum.PRODUCT_SKU_EXIST_ERROR);
        }
        productVo.setProduct(product);
        ProductSpecVo productSpecVo = productMapper.findProductSpecById(product.getProductSpecId());
        if (productSpecVo != null && StrUtil.isNotBlank(productSpecVo.getSpecValue())) {
            productVo.setSpecValueList(JSONUtil.toList(productSpecVo.getSpecValue(), JSONObject.class));
        }
        List<ProductDetailVo> productDetailList = productMapper.findProductDetailByProductId(product.getId());
        if (productDetailList != null && !productDetailList.isEmpty()) {
            List<String> imageUrl = productDetailList.stream().map(ProductDetailVo::getImageUrl).distinct().toList();
            productVo.setDetailsImageUrlList(imageUrl);
        }
        if (StrUtil.isNotBlank(product.getSpecValue()) && JSONUtil.isTypeJSON(product.getSpecValue())) {
            productVo.setSkuSpecValueMap(JSONUtil.toBean(product.getSpecValue(), Map.class));
        }
        if (StrUtil.isNotBlank(product.getSliderUrls())) {
            productVo.setSliderUrlList(JSONUtil.toList(product.getSliderUrls(), String.class));
        }
        return productVo;
    }

    @Override
    public ProductSkuVo getBySkuId(Long skuId) {
        ProductSkuVo productSkuVo = productMapper.getProductSkuById(skuId);
        if (productSkuVo == null || productSkuVo.getStatus() == 0) {
            PurePicksException.error(ResultCodeEnum.PRODUCT_SKU_EXIST_ERROR);
        }
        return productSkuVo;
    }
}
