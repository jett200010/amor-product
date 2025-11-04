package com.example.amorproduct.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 商品价格信息实体类
 *
 * 用于存储商品的价格、折扣、货币类型、价格历史等数据。
 * 支持多平台（Amazon / eBay / Walmart 等）价格追踪与对比。
 */
@Data
public class ProductPricing {

    /** 自增主键 */
    private Long id;

    /** 商品唯一ID（关联 ProductBasicInfo 表中的 productId） */
    private String productId;

    /**
     * 当前售价
     * 例如：29.99
     */
    private BigDecimal currentPrice;

    /**
     * 原价（未打折前价格）
     * 例如：39.99
     */
    private BigDecimal originalPrice;

    /**
     * 货币类型
     * 例如：USD、CNY、EUR、GBP
     */
    private String currency;

    /**
     * 折扣百分比
     * 例如："25%"，注意为字符串类型方便保留符号
     */
    private String discountPercentage;

    /**
     * 折扣金额
     * 例如：10.00（表示优惠了10美元）
     */
    private BigDecimal discountAmount;

    /**
     * 单位价格信息（如每件、每升、每克）
     * 示例："$2.99 / 100ml" 或 "￥5 / 件"
     */
    private String unitPrice;

    /**
     * 价格历史（JSON或字符串形式）
     * 示例：{"2025-09-01":29.99,"2025-09-15":27.49}
     */
    private String priceHistory;

    /** 创建时间（记录入库时间） */
    private LocalDateTime createdAt;

    /** 更新时间（最近一次价格更新） */
    private LocalDateTime updatedAt;
}
