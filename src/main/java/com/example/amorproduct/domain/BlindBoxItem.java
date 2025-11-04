package com.example.amorproduct.domain;

import lombok.Data;

/**
 * 盲盒商品项实体类
 */
@Data
public class BlindBoxItem {
    private Long id;
    private String itemId;
    private String blindBoxId;
    private String productId;
    private Integer quantity;
    private String description; // 商品在盲盒中的描述
    private Integer sortOrder; // 排序顺序

    // 关联的商品信息
    private ProductFullInfo product;
}

