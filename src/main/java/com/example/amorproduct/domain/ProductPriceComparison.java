package com.example.amorproduct.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品价格对比实体类
 *
 * 用于存储同一商品在不同平台的价格信息
 */
@Data
public class ProductPriceComparison {

    /** 自增主键 */
    private Long id;

    /** 商品唯一ID（关联 Product 表中的 productId） */
    private String productId;

    /** 平台名称（Amazon/eBay/Walmart/JD/Taobao等） */
    private String platform;

    /** 平台商品ID */
    private String platformProductId;

    /** 平台商品链接 */
    private String platformUrl;

    /** 当前价格 */
    private BigDecimal currentPrice;

    /** 原价 */
    private BigDecimal originalPrice;

    /** 折扣率（百分比） */
    private BigDecimal discountRate;

    /** 货币类型（USD/CNY/EUR等） */
    private String currency;

    /** 库存状态（in_stock/out_of_stock/pre_order） */
    private String stockStatus;

    /** 运费 */
    private BigDecimal shippingFee;

    /** 是否支持Prime/会员免邮（1：是，0：否） */
    private Boolean isPrime;

    /** 预计送达天数 */
    private Integer estimatedDeliveryDays;

    /** 卖家名称 */
    private String sellerName;

    /** 卖家评分（1.0-5.0） */
    private BigDecimal sellerRating;

    /** 价格更新时间 */
    private LocalDateTime priceUpdateTime;

    /** 创建时间 */
    private LocalDateTime createdAt;

    /** 更新时间 */
    private LocalDateTime updatedAt;
}
