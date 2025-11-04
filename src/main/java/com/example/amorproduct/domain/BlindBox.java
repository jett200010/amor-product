package com.example.amorproduct.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 盲盒主体实体类
 */
@Data
public class BlindBox {
    private Long id;
    private String blindBoxId;
    private String name;
    private String description;
    private String theme; // 主题分类（如"易延容颜"、"适合初学者"、"经老年人测试配方"）
    private BigDecimal originalPrice;
    private BigDecimal salePrice;
    private String currency; // 货币单位
    private Integer discountPercentage; // 折扣百分比
    private BigDecimal savedAmount; // 节省金额
    private String status; // ACTIVE, INACTIVE
    private Integer totalQuantity; // 总库存
    private Integer soldQuantity; // 已售数量
    private String imageUrl; // 盲盒图片
    private String badgeText; // 徽章文本（如"七折优惠"、"七五折"）
    private String badgeColor; // 徽章颜色
    private String buttonText; // 按钮文本（如"查看详情"）
    private String buttonColor; // 按钮颜色
    private Boolean freeShipping; // 是否免运费
    private Boolean hasReturnPolicy; // 是否支持30天退货
    private Boolean isExclusiveSelection; // 是否专家精选
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 关联数据
    private List<BlindBoxItem> items; // 盲盒包含的商品
    private List<String> features; // 特性列表
}