package com.example.amorproduct.domain.vo;

import lombok.Data;
import java.util.List;

/**
 * 商品评论汇总视图对象
 *
 * 包含好评列表、差评列表和统计信息
 */
@Data
public class ProductReviewSummaryVO {

    /** 商品ID */
    private String productId;

    /** 好评总数 */
    private Integer positiveCount;

    /** 差评总数 */
    private Integer negativeCount;

    /** 好评列表 */
    private List<ProductReviewVO> positiveReviews;

    /** 差评列表 */
    private List<ProductReviewVO> negativeReviews;

    /** 总评论数 */
    private Integer totalCount;
}

