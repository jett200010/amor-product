package com.example.amorproduct.domain.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

/**
 * 盲盒视图对象 - 返回给前端的数据
 * 包含动态生成的商品组合信息
 */
@Data
public class BlindBoxVO {
    private String ruleId;
    private String name;
    private String description;
    private String theme;

    // 价格信息
    private BigDecimal totalOriginalPrice; // 商品原价总和
    private BigDecimal totalCurrentPrice; // 商品现价总和
    private String currency;
    private Integer discountPercentage; // 折扣百分比（用于徽章显示）
    private BigDecimal savedAmount; // 节省金额

    // 包含的商品列表
    private List<BlindBoxProductItem> products;

    // 商品数量
    private Integer productCount;

    /**
     * 盲盒中的商品项
     */
    @Data
    public static class BlindBoxProductItem {
        private String productId;
        private String title;
        private String brand;
        private BigDecimal originalPrice;
        private BigDecimal currentPrice;
        private String currency;
        private String mainImageUrl;
        private String categoryName;
        private BigDecimal rating;
        private Integer reviewsCount;
        private String availability;
        private Boolean primeEligible;
    }
}

