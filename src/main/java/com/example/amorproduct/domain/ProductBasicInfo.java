package com.example.amorproduct.domain;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商品基础信息实体类
 * 对应表：product_basic_info
 * 用于存储不同电商平台（Amazon / eBay / Walmart）的商品核心信息
 */
@Data
public class ProductBasicInfo {

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 平台唯一商品ID
     * 例如：Amazon 的 ASIN、eBay 的 itemId、Walmart 的 productId
     */
    private String productId;

    /**
     * 父商品ID（变体父级）
     * 例如：多颜色、多规格商品共用的父ASIN
     */
    private String parentProductId;

    /**
     * 商品完整标题
     * 通常为平台展示的完整商品名称
     */
    private String title;

    /**
     * 品牌名称
     * 如：Nike、Apple、VIVEBEI 等
     */
    private String brand;

    /**
     * 卖家ID
     * 平台分配的唯一卖家标识
     */
    private String sellerId;

    /**
     * 卖家名称
     * 平台店铺或销售方名称
     */
    private String sellerName;

    /**
     * 平台标识
     * 可选值：amazon / ebay / walmart
     */
    private String platform;

    /**
     * 商品页面URL
     * 商品详情页链接地址
     */
    private String productUrl;

    /**
     * 商品状态
     * 可选值：active（上架）、inactive（下架）、out_of_stock（缺货）
     */
    private String status;

    /**
     * 主图URL
     * 商品主要展示图片链接
     */
    private String mainImageUrl;

    /**
     * 商品图片URL列表（JSON格式）
     * 示例：["url1", "url2", "url3"]
     */
    private String imageUrls;

    /**
     * 商品详细描述
     * 包含商品的详细说明信息
     */
    private String productDescription;

    /**
     * 卖点描述/推荐理由
     * 示例：完美契合您的心脏健康目标，并深受老年人好评。
     */
    private String sellingPoints;

    /**
     * 商品规格参数（JSON格式）
     * 示例：{"weight":"2000毫克", "count":"3鱼油", "servings":"200毫克"}
     */
    private String specifications;

    /**
     * 快速购买链接
     * 商品快速购买页面URL
     */
    private String quickBuyUrl;

    /**
     * 主要成分信息（JSON格式）
     * 示例：["EPA (1200毫克)", "DHA (800毫克)", "维生素E", "天然柠檬味"]
     */
    private String mainIngredients;

    /**
     * 健康益处信息（JSON格式）
     * 示例：["有益心脏健康", "促进大脑功能", "减轻关节炎症", "改善胆固醇水平"]
     */
    private String healthBenefits;

    /**
     * 适合老年人的功能信息（JSON格式）
     * 示例：["易于吞咽的软胶囊", "标签大而清晰，便于阅读", "经第三方检测纯度", "没有鱼腥味", "推荐剂量已明确标示"]
     */
    private String suitableForElderly;

    /**
     * 创建时间
     * 记录数据首次插入时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     * 记录最近一次修改时间
     */
    private LocalDateTime updatedAt;
}
