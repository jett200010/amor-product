package com.example.amorproduct.mapper;

import com.example.amorproduct.domain.ProductPriceComparison;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品价格对比 Mapper 接口
 */
@Mapper
public interface ProductPriceComparisonMapper {

    /**
     * 根据商品ID查询价格对比列表
     */
    List<ProductPriceComparison> getPriceComparisonByProductId(@Param("productId") String productId);

    /**
     * 插入价格对比记录
     */
    int insertPriceComparison(ProductPriceComparison priceComparison);

    /**
     * 更新价格对比记录
     */
    int updatePriceComparison(ProductPriceComparison priceComparison);

    /**
     * 根据商品ID和平台删除价格对比记录
     */
    int deletePriceComparison(@Param("productId") String productId, @Param("platform") String platform);

    /**
     * 根据商品ID获取最低价格的平台
     */
    ProductPriceComparison getLowestPriceByProductId(@Param("productId") String productId);
}

