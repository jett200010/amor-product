package com.example.amorproduct.domain;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 商品库存与物流信息实体类
 *
 * 用于记录商品的库存状态、发货来源、配送选项、退货政策等物流相关属性，
 * 支持多平台（Amazon / eBay / Walmart 等）库存信息统一管理。
 */
@Data
public class ProductInventory {

    /** 自增主键 */
    private Long id;

    /** 商品唯一ID（关联 Product 表中的 productId） */
    private String productId;

    /**
     * 库存状态
     * 例如：in_stock（有货）、out_of_stock（缺货）、preorder（预售）、unknown（未知）
     */
    private String availability;

    /**
     * 库存数量
     * 若平台不返回具体数值，可置为 null 或 -1 表示未知
     */
    private Integer stockQuantity;

    /**
     * 发货来源
     * 如：Amazon、Third-Party Seller、US Warehouse、CN Warehouse 等
     */
    private String shipsFrom;

    /**
     * 配送选项（JSON字符串）
     * 例如：{"standard":"3-5 days", "express":"1-2 days"}
     */
    private String deliveryOptions;

    /**
     * 是否支持 Prime / 会员免邮服务
     * true：支持，false：不支持
     */
    private Boolean primeEligible;

    /**
     * 退货政策说明
     * 如：“30-day return policy” 或平台标准退货规则
     */
    private String returnPolicy;

    /** 数据创建时间（入库时间） */
    private LocalDateTime createdAt;

    /** 数据最后更新时间 */
    private LocalDateTime updatedAt;
}
