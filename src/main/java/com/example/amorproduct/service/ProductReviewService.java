package com.example.amorproduct.service;

import com.example.amorproduct.domain.ProductReview;
import com.example.amorproduct.domain.vo.ProductReviewSummaryVO;
import com.example.amorproduct.domain.vo.ProductReviewVO;
import com.example.amorproduct.mapper.ProductReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品评论服务类
 */
@Service
public class ProductReviewService {

    @Autowired
    private ProductReviewMapper productReviewMapper;

    /**
     * 获取商品评论汇总信息（包含好评和差评）
     */
    public ProductReviewSummaryVO getReviewSummary(String productId, Integer limit) {
        ProductReviewSummaryVO summary = new ProductReviewSummaryVO();
        summary.setProductId(productId);

        // 获取好评列表
        List<ProductReviewVO> positiveReviews = productReviewMapper.getPositiveReviewsByProductId(productId, limit);
        summary.setPositiveReviews(positiveReviews);
        summary.setPositiveCount(productReviewMapper.countPositiveReviews(productId));

        // 获取差评列表
        List<ProductReviewVO> negativeReviews = productReviewMapper.getNegativeReviewsByProductId(productId, limit);
        summary.setNegativeReviews(negativeReviews);
        summary.setNegativeCount(productReviewMapper.countNegativeReviews(productId));

        // 总评论数
        summary.setTotalCount(productReviewMapper.countTotalReviews(productId));

        return summary;
    }

    /**
     * 获取商品所有评论
     */
    public List<ProductReview> getReviewsByProductId(String productId) {
        return productReviewMapper.getReviewsByProductId(productId);
    }

    /**
     * 获取好评列表
     */
    public List<ProductReviewVO> getPositiveReviews(String productId, Integer limit) {
        return productReviewMapper.getPositiveReviewsByProductId(productId, limit);
    }

    /**
     * 获取差评列表
     */
    public List<ProductReviewVO> getNegativeReviews(String productId, Integer limit) {
        return productReviewMapper.getNegativeReviewsByProductId(productId, limit);
    }

    /**
     * 添加评论
     */
    public int addReview(ProductReview review) {
        return productReviewMapper.insertReview(review);
    }

    /**
     * 更新点赞数
     */
    public int updateLikeCount(Long id, Integer likeCount) {
        return productReviewMapper.updateLikeCount(id, likeCount);
    }

    /**
     * 删除评论
     */
    public int deleteReview(Long id) {
        return productReviewMapper.deleteReviewById(id);
    }
}

