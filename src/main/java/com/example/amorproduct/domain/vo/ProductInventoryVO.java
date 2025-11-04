package com.example.amorproduct.domain.vo;

import lombok.Data;

/**
 * 商品库存物流展示对象
 * 用于库存和物流相关的查询接口
 */
@Data
public class ProductInventoryVO {

    private String productId;
    private String title;
    private String platform;
    private String availability;
    private Integer stockQuantity;
    private String shipsFrom;
    private String deliveryOptions;
    private Boolean primeEligible;
    private String returnPolicy;
}
