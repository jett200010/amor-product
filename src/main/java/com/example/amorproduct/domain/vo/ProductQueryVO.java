package com.example.amorproduct.domain.vo;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 商品查询请求对象
 */
@Data
public class ProductQueryVO {
    // 分页参数
    private Integer page = 1;
    private Integer size = 10;

    // 0:正常分页查询 1:查询出热销的商品
    private String queryType;

    // 基础信息查询
    private String productId;
    private String title; // 支持模糊查询
    private String brand;
    private String sellerId;
    private String sellerName;
    private String platform;
    private String status;

    // 分类查询
    private String rootCategory;
    private String subcategory;
    private String categoryName;
    private String productType;
    private String targetAudience;

    // 价格区间
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

    // 评分区间
    private BigDecimal minRating;
    private BigDecimal maxRating;

    // 库存状态
    private String availability;
    private Boolean primeEligible;

    // 排序字段
    private String sortBy; // 如: currentPrice, rating, reviewsCount, createTime
    private String sortOrder = "DESC"; // ASC 或 DESC
}

