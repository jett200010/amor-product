package com.example.amorproduct.domain.vo;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 商品卖家信息展示对象
 * 用于卖家相关的查询接口
 */
@Data
public class ProductSellerVO {

    private String productId;
    private String title;
    private String brand;
    private String platform;
    private String sellerId;
    private String sellerName;
    private BigDecimal currentPrice;
    private String currency;
    private BigDecimal rating;
    private Integer reviewsCount;
    private String availability;
}
