package com.devsurfer.purepicks.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.devsurfer.purepicks.manager.mapper.ProductMapper;
import com.devsurfer.purepicks.manager.service.ProductService;
import com.devsurfer.purepicks.model.dto.system.product.ProductInsertDto;
import com.devsurfer.purepicks.model.dto.system.product.ProductQueryDto;
import com.devsurfer.purepicks.model.dto.system.product.ProductUpdateDto;
import com.devsurfer.purepicks.model.dto.system.product.ReviewProductDto;
import com.devsurfer.purepicks.model.entity.product.Product;
import com.devsurfer.purepicks.model.entity.product.ProductDetail;
import com.devsurfer.purepicks.model.entity.product.ProductSku;
import com.devsurfer.purepicks.model.vo.system.product.ProductVo;
import com.devsurfer.purepicks.utils.AuthContextUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/16 16:12
 * description TODO
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductVo findProductById(Long productId) {
        return productMapper.findProductById(productId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertProduct(ProductInsertDto productInsertDto) {
        // 保存商品基础信息
        Product product = BeanUtil.copyProperties(productInsertDto, Product.class);
        // 创建人id
        product.setCreateUserId(AuthContextUtil.getUserId());
        productMapper.insert(product);
        // 保存 商品SKU信息
        if (CollectionUtil.isNotEmpty(productInsertDto.getProductSkuList())) {
            List<ProductSku> productSkuList = BeanUtil.copyToList(productInsertDto.getProductSkuList(), ProductSku.class);
            for (int i = 0; i < productSkuList.size(); i++) {
                ProductSku productSku = productSkuList.get(i);
                productSku.setCode(product.getId() + "_" + i);
                productSku.setProductId(product.getId());
                productSku.setName(product.getProductName() + "_" + productSku.getSpecName());
            }
            productMapper.insertProductSkuBatch(productSkuList);
        }
        // 保存商品详情
        ProductDetail productDetail = BeanUtil.copyProperties(productInsertDto.getProductDetail(), ProductDetail.class);
        if (productDetail != null) {
            productDetail.setProductId(product.getId());
            productMapper.insertProductDetail(productDetail);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void editProduct(ProductUpdateDto productUpdateDto) {
        // 更新商品基础信息
        Product product = BeanUtil.copyProperties(productUpdateDto, Product.class);
        productMapper.update(product);
        // 更新 商品SKU信息
        if (CollectionUtil.isNotEmpty(productUpdateDto.getProductSkuList())) {
            List<ProductSku> productSkuList = BeanUtil.copyToList(productUpdateDto.getProductSkuList(), ProductSku.class);
            for (int i = 0; i < productSkuList.size(); i++) {
                ProductSku productSku = productSkuList.get(i);
                productSku.setCode(product.getId() + "_" + i);
                productSku.setProductId(product.getId());
                productSku.setName(product.getProductName() + "_" + productSku.getSpecName());
            }
            productMapper.deleteProductSkuByProductId(product.getId());
            productMapper.insertProductSkuBatch(productSkuList);
        }
        // 更新商品详情
        ProductDetail productDetail = BeanUtil.copyProperties(productUpdateDto.getProductDetail(), ProductDetail.class);
        if (productDetail != null) {
            productDetail.setProductId(product.getId());
            productMapper.updateProductDetail(productDetail);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProduct(Long productId) {
        // 删除商品信息
        productMapper.deleteProductById(productId);
        // 删除商品sku信息
        productMapper.deleteProductSkuByProductId(productId);
        // 删除商品详情信息
        productMapper.deleteProductDetailByProductId(productId);
    }

    @Override
    public PageInfo<ProductVo> pageList(ProductQueryDto productQueryDto) {
        try (Page<ProductVo> page = PageHelper.startPage(productQueryDto.getPageNum(), productQueryDto.getPageSize())) {
            productMapper.findProductList(productQueryDto);
            return page.toPageInfo();
        }
    }

    @Override
    public void reviewProduct(ReviewProductDto reviewProductDto) {
        productMapper.reviewProduct(reviewProductDto);
    }

    @Override
    public void updateStatus(Long productId, Integer status) {
        productMapper.updateStatus(productId, status);
    }

}
