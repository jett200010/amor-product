package com.example.amorproduct.mapper;

import com.example.amorproduct.domain.ProductFullInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {

    /**
     * 根据商品ID查询完整商品信息（商品详情页使用）
     */
    ProductFullInfo getProductFullInfoById(@Param("productId") String productId);

    /**
     * 根据平台查询商品列表（返回完整商品信息）
     */
    List<ProductFullInfo> getProductsByPlatform(@Param("platform") String platform);

    /**
     * 根据品牌查询商品列表（返回完整商品信息）
     */
    List<ProductFullInfo> getProductsByBrand(@Param("brand") String brand);

    /**
     * 根据卖家ID查询商品列表（返回完整商品信息）
     */
    List<ProductFullInfo> getProductsBySellerId(@Param("sellerId") String sellerId);

    /**
     * 根据根分类查询商品列表（返回完整商品信息）
     */
    List<ProductFullInfo> getProductsByRootCategory(@Param("rootCategory") String rootCategory);

    /**
     * 分页查询商品信息（返回完整商品信息）
     */
    List<ProductFullInfo> getProductsWithPagination(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 根据价格区间查询商品（返回完整商品信息）
     */
    List<ProductFullInfo> getProductsByPriceRange(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

    /**
     * 根据评分区间查询商品（返回完整商品信息）
     */
    List<ProductFullInfo> getProductsByRatingRange(@Param("minRating") Double minRating, @Param("maxRating") Double maxRating);

    /**
     * 根据商品状态查询商品（返回完整商品信息）
     */
    List<ProductFullInfo> getProductsByStatus(@Param("status") String status);

    /**
     * 查询热销商品（返回完整商品信息）
     */
    List<ProductFullInfo> getTopSellingProducts(@Param("limit") int limit);

    /**
     * 统计商品总数
     */
    int getTotalProductCount();
}
