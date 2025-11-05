package com.example.amorproduct.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品完整信息聚合实体类
 *
 * 仅用于需要展示商品完整详情的场景，如商品详情页面。
 * 包含所有相关表的核心字段的平铺结构。
 */
@Data
public class ProductFullInfo {

    // ==================== 基础信息 ====================
    private Long id;
    private String productId;
    private String parentProductId;
    private String title;
    private String brand;
    private String sellerId;
    private String sellerName;
    private String platform;
    private String productUrl;
    private String status;

    // ==================== 商品图片信息 ====================
    private String mainImageUrl;
    private String imageUrls;

    // ==================== 商品描述信息 ====================
    private String productDescription;
    private String sellingPoints;
    private String specifications;
    private String quickBuyUrl;

    // ==================== 价格信息 ====================
    private BigDecimal currentPrice;
    private BigDecimal originalPrice;
    private String currency;
    private String discountPercentage;
    private BigDecimal discountAmount;
    private String unitPrice;

    // ==================== 评价排名信息 ====================
    private BigDecimal rating;
    private Integer reviewsCount;
    private Integer answeredQuestions;
    private Integer bestSellersRank;
    private Integer categoryRank;
    private String categoryName;

    // ==================== 匹配度信息 ====================
    private BigDecimal matchPercentage;
    private String recommendationReason;

    // ==================== 库存物流信息 ====================
    private String availability;
    private Integer stockQuantity;
    private String shipsFrom;
    private Boolean primeEligible;
    private String returnPolicy;

    // ==================== 分类标签信息 ====================
    private String categories;
    private String rootCategory;
    private String subcategory;
    private String productType;
    private String targetAudience;

    // ==================== 商品徽章和标签 ====================
    private String badges;
    private String promotionTags;

    // ==================== 时间戳 ====================
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ==================== 商品详细信息 ====================
    /** 主要成分信息 */
    private String mainIngredients;

    /** 健康益处信息 */
    private String healthBenefits;

    /** 适合老年人的功能信息 */
    private String suitableForElderly;

    // ==================== 评论信息 ====================
    /** 商品评论列表 */
    private List<ProductReview> reviewList;

    // ==================== 价格对比信息 ====================
    /** 价格对比列表（多平台） */
    private List<ProductPriceComparison> priceComparisonList;
}
