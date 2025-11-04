package com.example.amorproduct.domain.vo;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品列表展示对象
 * 用于商品列表页面，包含用户最关心的核心信息
 */
@Data
public class ProductListVO {

    private String productId;
    private String title;
    private String brand;
    private String platform;
    private BigDecimal currentPrice;
    private BigDecimal originalPrice;
    private String currency;
    private String discountPercentage;
    private BigDecimal rating;
    private Integer reviewsCount;
    private String availability;
    private String productUrl;
    private LocalDateTime updatedAt;
}
