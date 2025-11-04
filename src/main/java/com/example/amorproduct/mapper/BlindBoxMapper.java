package com.example.amorproduct.mapper;

import com.example.amorproduct.domain.BlindBoxRule;
import com.example.amorproduct.domain.ProductFullInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface BlindBoxMapper {

    /**
     * 获取所有可用的盲盒规则
     */
    List<BlindBoxRule> getAllActiveRules();

    /**
     * 根据ID获取盲盒规则
     */
    BlindBoxRule getRuleById(@Param("ruleId") String ruleId);

    /**
     * 根据分类和价格区间随机获取商品
     */
    List<ProductFullInfo> getRandomProductsByCategory(
        @Param("categoryField") String categoryField,
        @Param("categoryValue") String categoryValue,
        @Param("minPrice") BigDecimal minPrice,
        @Param("maxPrice") BigDecimal maxPrice,
        @Param("limit") Integer limit
    );

    /**
     * 创建盲盒规则
     */
    int createRule(BlindBoxRule rule);

    /**
     * 更新盲盒规则
     */
    int updateRule(BlindBoxRule rule);

    /**
     * 删除盲盒规则
     */
    int deleteRule(@Param("ruleId") String ruleId);
}

