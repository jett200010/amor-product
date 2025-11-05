package com.example.amorproduct.service;

import com.example.amorproduct.domain.ProductFullInfo;
import com.example.amorproduct.domain.ProductReview;
import com.example.amorproduct.domain.ProductPriceComparison;
import com.example.amorproduct.domain.vo.ProductQueryVO;
import com.example.amorproduct.mapper.ProductMapper;
import com.example.amorproduct.mapper.ProductReviewMapper;
import com.example.amorproduct.mapper.ProductPriceComparisonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductReviewMapper productReviewMapper;

    @Autowired
    private ProductPriceComparisonMapper productPriceComparisonMapper;

    /**
     * 根据商品ID查询完整商品信息（包含评论和价格对比）
     */
    public ProductFullInfo getProductFullInfoById(String productId) {
        // 查询商品基本信息
        ProductFullInfo product = productMapper.getProductFullInfoById(productId);

        if (product != null) {
            // 查询评论列表
            List<ProductReview> reviewList = productReviewMapper.getReviewsByProductId(productId);
            product.setReviewList(reviewList);

            // 查询价格对比列表
            List<ProductPriceComparison> priceComparisonList = productPriceComparisonMapper.getPriceComparisonByProductId(productId);
            product.setPriceComparisonList(priceComparisonList);
        }

        return product;
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

    /**
     * 高级查询商品（支持多条件组合查询和分页）
     * 使用手动分页方式
     */
    public Map<String, Object> queryProducts(ProductQueryVO query) {
        // 查询数据列表
        List<ProductFullInfo> records = new ArrayList<>();
        int total = 0;
        int pages = 0;
        if (query.getQueryType().equals("1")) {
            records = productMapper.getTopSellingProducts(query.getSize());
            total = query.getSize();
            pages = 1;
        } else {
            records = productMapper.queryProducts(query);
            total = productMapper.countProducts(query);
            // 计算总页数
            pages = (int) Math.ceil((double) total / query.getSize());
        }


        // 构造返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("records", records);
        result.put("total", total);
        result.put("size", query.getSize());
        result.put("current", query.getPage());
        result.put("pages", pages);

        return result;
    }
}
