package com.example.amorproduct.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商品评论视图对象
 *
 * 用于前端展示评论信息，包含好评和差评的分类统计
 */
@Data
public class ProductReviewVO {

    /** 评论ID */
    private Long id;

    /** 商品ID */
    private String productId;

    /** 评论用户名 */
    private String username;

    /** 评论内容 */
    private String content;

    /** 是否为好评 */
    private Boolean isPositive;

    /** 点赞数 */
    private Integer likeCount;

    /** 评论时间 */
    private LocalDateTime reviewTime;

    /** 是否已验证购买 */
    private Boolean isVerifiedPurchase;
}
