package com.example.amorproduct.controller;

import com.example.amorproduct.domain.ProductReview;
import com.example.amorproduct.domain.vo.ProductReviewSummaryVO;
import com.example.amorproduct.domain.vo.ProductReviewVO;
import com.example.amorproduct.service.ProductReviewService;
import com.example.amorproduct.utils.HttpStatus;
import com.example.amorproduct.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品评论控制器
 *
 * 提供评论的增删改查接口
 */
@RestController
@RequestMapping("/api/product/review")
public class ProductReviewController {

    @Autowired
    private ProductReviewService productReviewService;

    /**
     * 获取商品评论汇总（好评和差评分开显示）
     * GET /api/product/review/summary/{productId}
     */
    @GetMapping("/summary/{productId}")
    public R<ProductReviewSummaryVO> getReviewSummary(@PathVariable String productId,
                                                      @RequestParam(defaultValue = "10") Integer limit) {
        ProductReviewSummaryVO summary = productReviewService.getReviewSummary(productId, limit);
        return R.ok(summary);
    }

    /**
     * 获取商品所有评论
     * GET /api/product/review/{productId}
     */
    @GetMapping("/{productId}")
    public R<List<ProductReview>> getReviews(@PathVariable String productId) {
        List<ProductReview> reviews = productReviewService.getReviewsByProductId(productId);
        return R.ok(reviews);
    }

    /**
     * 获取好评列表
     * GET /api/product/review/{productId}/positive
     */
    @GetMapping("/{productId}/positive")
    public R<Map<String, Object>> getPositiveReviews(@PathVariable String productId,
                                                     @RequestParam(defaultValue = "10") Integer limit) {
        List<ProductReviewVO> reviews = productReviewService.getPositiveReviews(productId, limit);
        Integer count = reviews.size();
        Map<String, Object> result = new HashMap<>();
        result.put("data", reviews);
        result.put("count", count);
        return R.ok(result);
    }

    /**
     * 获取差评列表
     * GET /api/product/review/{productId}/negative
     */
    @GetMapping("/{productId}/negative")
    public R<Map<String, Object>> getNegativeReviews(@PathVariable String productId,
                                                     @RequestParam(defaultValue = "10") Integer limit) {
        List<ProductReviewVO> reviews = productReviewService.getNegativeReviews(productId, limit);
        Integer count = reviews.size();
        Map<String, Object> result = new HashMap<>();
        result.put("data", reviews);
        result.put("count", count);
        return R.ok(result);
    }

    /**
     * 添加评论
     * POST /api/product/review
     */
    @PostMapping
    public R<String> addReview(@RequestBody ProductReview review) {
        int result = productReviewService.addReview(review);
        if (result > 0) {
            return R.ok("评论添加成功");
        }
        return R.fail(HttpStatus.ERROR, "评论添加失败");
    }

    /**
     * 更新评论点赞数
     * PUT /api/product/review/{id}/like
     */
    @PutMapping("/{id}/like")
    public R<String> updateLikeCount(@PathVariable Long id, @RequestParam Integer likeCount) {
        int result = productReviewService.updateLikeCount(id, likeCount);
        if (result > 0) {
            return R.ok("点赞成功");
        }
        return R.fail(HttpStatus.ERROR, "点赞失败");
    }

    /**
     * 删除评论
     * DELETE /api/product/review/{id}
     */
    @DeleteMapping("/{id}")
    public R<String> deleteReview(@PathVariable Long id) {
        int result = productReviewService.deleteReview(id);
        if (result > 0) {
            return R.ok("评论删除成功");
        }
        return R.fail(HttpStatus.ERROR, "评论删除失败");
    }
}
