package com.example.amorproduct.mapper;

import com.example.amorproduct.domain.ProductReview;
import com.example.amorproduct.domain.vo.ProductReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品评论 Mapper 接口
 */
@Mapper
public interface ProductReviewMapper {

    /**
     * 根据商品ID查询所有评论
     */
    List<ProductReview> getReviewsByProductId(@Param("productId") String productId);

    /**
     * 根据商品ID查询好评列表
     */
    List<ProductReviewVO> getPositiveReviewsByProductId(@Param("productId") String productId,
                                                         @Param("limit") Integer limit);

    /**
     * 根据商品ID查询差评列表
     */
    List<ProductReviewVO> getNegativeReviewsByProductId(@Param("productId") String productId,
                                                         @Param("limit") Integer limit);

    /**
     * 统计商品好评数量
     */
    Integer countPositiveReviews(@Param("productId") String productId);

    /**
     * 统计商品差评数量
     */
    Integer countNegativeReviews(@Param("productId") String productId);

    /**
     * 插入评论
     */
    int insertReview(ProductReview review);

    /**
     * 更新评论点赞数
     */
    int updateLikeCount(@Param("id") Long id, @Param("likeCount") Integer likeCount);

    /**
     * 根据ID删除评论
     */
    int deleteReviewById(@Param("id") Long id);

    /**
     * 根据商品ID统计总评论数
     */
    Integer countTotalReviews(@Param("productId") String productId);
}

