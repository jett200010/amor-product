package com.example.amorproduct.domain;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商品分类与标签信息实体类
 * 对应表：product_category_tags
 *
 * 主要用于存储商品的分类路径、根分类、子分类、
 * 产品类型、目标人群等标签信息。
 * 适用于多平台（Amazon / eBay / Walmart）商品数据统一结构。
 */
@Data
public class ProductCategoryTags {

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 关联商品ID
     * 与 product_basic_info 表中的 productId 对应
     */
    private String productId;

    /**
     * 分类路径（JSON格式）
     * 示例：["Clothing, Shoes & Jewelry", "Shoe Accessories", "Shoe Horns & Boot Jacks"]
     */
    private String categories;

    /**
     * 根分类
     * 示例：Clothing, Shoes & Jewelry
     */
    private String rootCategory;

    /**
     * 子分类
     * 示例：Shoe Horns & Boot Jacks
     */
    private String subcategory;

    /**
     * 产品类型
     * 通常从分类中推断得到，如：shoe_horn / office_chair / smart_light_bulb
     */
    private String productType;

    /**
     * 目标人群（JSON格式）
     * 示例：["men", "women", "seniors"]
     */
    private String targetAudience;

    /**
     * 商品徽章（JSON格式）
     * 示例：["易于吞咽", "大字体标签", "值得信赖的品牌"]
     */
    private String badges;

    /**
     * 促销标签（JSON格式）
     * 示例：["限时优惠", "买一送一", "新品上市"]
     */
    private String promotionTags;

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
