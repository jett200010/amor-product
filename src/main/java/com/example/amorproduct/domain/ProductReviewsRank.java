package com.example.amorproduct.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品评价与排名信息实体类
 *
 * 用于存储商品在电商平台上的评分、评价数量、回答问题数及各类排名信息，
 * 支持多平台（Amazon / eBay / Walmart）的统一数据结构。
 */
@Data
public class ProductReviewsRank {

    /** 自增主键 */
    private Long id;

    /** 商品唯一ID（关联 Product 表中的 productId） */
    private String productId;

    /** 平均评分（如 4.8），范围通常为 1.0 ~ 5.0 */
    private BigDecimal rating;

    /** 评价总数 */
    private Integer reviewsCount;

    /** 已回答问题数量 */
    private Integer answeredQuestions;

    /** 商品畅销总排名 */
    private Integer bestSellersRank;

    /** 类目内排名 */
    private Integer categoryRank;

    /** 商品所属类目名称 */
    private String categoryName;

    /**
     * 匹配度百分比
     * 示例：96、92、88 等，用于表示商品与用户需求的匹配程度
     */
    private BigDecimal matchPercentage;

    /**
     * 推荐原因
     * 示例：完美契合您的心脏健康目标，并深受老年人好评。
     */
    private String recommendationReason;

    /** 数据创建时间（入库时间） */
    private LocalDateTime createdAt;

    /** 数据最后更新时间 */
    private LocalDateTime updatedAt;
}
