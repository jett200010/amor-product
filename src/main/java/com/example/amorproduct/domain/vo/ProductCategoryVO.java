package com.example.amorproduct.domain.vo;

import lombok.Data;

/**
 * 商品分类标签展示对象
 * 用于分类相关的查询接口
 */
@Data
public class ProductCategoryVO {

    private String productId;
    private String title;
    private String brand;
    private String platform;
    private String categories;
    private String rootCategory;
    private String subcategory;
    private String productType;
    private String targetAudience;
}
