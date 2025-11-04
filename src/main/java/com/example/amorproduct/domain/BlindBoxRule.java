package com.example.amorproduct.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 盲盒配置规则实体类
 * 用于配置盲盒的生成规则，而不是固定的商品组合
 */
@Data
public class BlindBoxRule {
    private Long id;
    private String ruleId;
    private String name;
    private String description;
    private String theme; // 主题分类

    // 商品筛选条件
    private String category; // 从哪个分类抽取商品（如：rootCategory）
    private String categoryValue; // 分类值（如："Health & Household"）

    // 数量规则
    private Integer minProductCount; // 最少包含几件商品
    private Integer maxProductCount; // 最多包含几件商品

    // 价格规则
    private BigDecimal minTotalPrice; // 总价最低
    private BigDecimal maxTotalPrice; // 总价最高

    // 折扣规则
    private Integer discountPercentage; // 折扣百分比（用于显示徽章）

    // 状态
    private String status; // ACTIVE, INACTIVE

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

