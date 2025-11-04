package com.example.amorproduct.domain.vo;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 商品价格信息展示对象
 * 用于价格相关的查询接口
 */
@Data
public class ProductPriceVO {

    private String productId;
    private String title;
    private String brand;
    private String platform;
    private BigDecimal currentPrice;
    private BigDecimal originalPrice;
    private String currency;
    private String discountPercentage;
    private BigDecimal discountAmount;
    private String unitPrice;
}
