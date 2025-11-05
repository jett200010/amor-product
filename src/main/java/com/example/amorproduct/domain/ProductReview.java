package com.example.amorproduct.domain;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商品评论实体类
 *
 * 用于存储用户对商品的评价信息，包括评价内容、评分、点赞数等
 */
@Data
public class ProductReview {

    /** 自增主键 */
    private Long id;

    /** 商品唯一ID（关联 Product 表中的 productId） */
    private String productId;

    /** 评论用户名 */
    private String username;

    /** 评论内容 */
    private String content;

    /** 是否为好评（1：好评，0：差评） */
    private Boolean isPositive;

    /** 点赞数 */
    private Integer likeCount;

    /** 评论时间 */
    private LocalDateTime reviewTime;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新时间 */
    private LocalDateTime updatedAt;

    /** 是否已验证购买（1：是，0：否） */
    private Boolean isVerifiedPurchase;
}

