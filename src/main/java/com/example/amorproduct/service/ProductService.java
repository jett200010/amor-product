package com.example.amorproduct.service;

import com.example.amorproduct.domain.ProductFullInfo;
import com.example.amorproduct.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 根据商品ID查询完整商品信息
     */
    public ProductFullInfo getProductFullInfoById(String productId) {
        return productMapper.getProductFullInfoById(productId);
    }

    /**
     * 根据平台查询商品列表
     */
    public List<ProductFullInfo> getProductsByPlatform(String platform) {
        return productMapper.getProductsByPlatform(platform);
    }

    /**
     * 根据品牌查询商品列表
     */
    public List<ProductFullInfo> getProductsByBrand(String brand) {
        return productMapper.getProductsByBrand(brand);
    }

    /**
     * 根据卖家ID查询商品列表
     */
    public List<ProductFullInfo> getProductsBySellerId(String sellerId) {
        return productMapper.getProductsBySellerId(sellerId);
    }

    /**
     * 根据根分类查询商品列表
     */
    public List<ProductFullInfo> getProductsByRootCategory(String rootCategory) {
        return productMapper.getProductsByRootCategory(rootCategory);
    }

    /**
     * 分页查询商品信息
     */
    public List<ProductFullInfo> getProductsWithPagination(int page, int size) {
        int offset = (page - 1) * size;
        return productMapper.getProductsWithPagination(offset, size);
    }

    /**
     * 根据价格区间查询商品
     */
    public List<ProductFullInfo> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productMapper.getProductsByPriceRange(minPrice, maxPrice);
    }

    /**
     * 根据评分区间查询商品
     */
    public List<ProductFullInfo> getProductsByRatingRange(Double minRating, Double maxRating) {
        return productMapper.getProductsByRatingRange(minRating, maxRating);
    }

    /**
     * 根据商品状态查询商品
     */
    public List<ProductFullInfo> getProductsByStatus(String status) {
        return productMapper.getProductsByStatus(status);
    }

    /**
     * 查询热销商品（根据排名）
     */
    public List<ProductFullInfo> getTopSellingProducts(int limit) {
        return productMapper.getTopSellingProducts(limit);
    }

    /**
     * 统计商品总数
     */
    public int getTotalProductCount() {
        return productMapper.getTotalProductCount();
    }
}
