package com.example.amorproduct.controller;

import com.example.amorproduct.domain.ProductBasicInfo;
import com.example.amorproduct.domain.ProductFullInfo;
import com.example.amorproduct.domain.vo.*;
import com.example.amorproduct.service.ProductService;
import com.example.amorproduct.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 根据商品ID查询完整商品信息
     * GET /api/products/{productId}
     */
    @GetMapping("/{productId}")
    public R<ProductFullInfo> getProductById(@PathVariable String productId) {
        try {
            ProductFullInfo product = productService.getProductFullInfoById(productId);
            if (product != null) {
                return R.ok(product);
            }
            return R.notFound("商品不存在");
        } catch (Exception e) {
            return R.fail("查询商品信息失败：" + e.getMessage());
        }
    }

    /**
     * 根据商品ID查询基础信息
     * GET /api/products/{productId}/basic
     */
    @GetMapping("/{productId}/basic")
    public R<ProductBasicInfo> getBasicInfoById(@PathVariable String productId) {
        try {
            ProductBasicInfo basicInfo = productService.getBasicInfoByProductId(productId);
            if (basicInfo != null) {
                return R.ok(basicInfo);
            }
            return R.notFound("商品基础信息不存在");
        } catch (Exception e) {
            return R.fail("查询商品基础信息失败：" + e.getMessage());
        }
    }

    /**
     * 根据平台查询商品列表
     * GET /api/products/platform/{platform}
     */
    @GetMapping("/platform/{platform}")
    public R<List<ProductListVO>> getProductsByPlatform(@PathVariable String platform) {
        try {
            List<ProductListVO> products = productService.getProductsByPlatform(platform);
            return R.ok("查询成功", products);
        } catch (Exception e) {
            return R.fail("查询平台商品失败：" + e.getMessage());
        }
    }

    /**
     * 根据品牌查询商品列表
     * GET /api/products/brand/{brand}
     */
    @GetMapping("/brand/{brand}")
    public R<List<ProductListVO>> getProductsByBrand(@PathVariable String brand) {
        try {
            List<ProductListVO> products = productService.getProductsByBrand(brand);
            return R.ok("查询成功", products);
        } catch (Exception e) {
            return R.fail("查询品牌商品失败：" + e.getMessage());
        }
    }

    /**
     * 根据卖家ID查询商品列表
     * GET /api/products/seller/{sellerId}
     */
    @GetMapping("/seller/{sellerId}")
    public R<List<ProductSellerVO>> getProductsBySellerId(@PathVariable String sellerId) {
        try {
            List<ProductSellerVO> products = productService.getProductsBySellerId(sellerId);
            return R.ok("查询成功", products);
        } catch (Exception e) {
            return R.fail("查询卖家商品失败：" + e.getMessage());
        }
    }

    /**
     * 根据根分类查询商品列表
     * GET /api/products/category/{rootCategory}
     */
    @GetMapping("/category/{rootCategory}")
    public R<List<ProductCategoryVO>> getProductsByRootCategory(@PathVariable String rootCategory) {
        try {
            List<ProductCategoryVO> products = productService.getProductsByRootCategory(rootCategory);
            return R.ok("查询成功", products);
        } catch (Exception e) {
            return R.fail("查询分类商品失败：" + e.getMessage());
        }
    }

    /**
     * 分页查询商品信息
     * GET /api/products?page=1&size=10
     */
    @GetMapping
    public R<List<ProductListVO>> getProductsWithPagination(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            if (page <= 0 || size <= 0) {
                return R.badRequest("页码和每页大小必须大于0");
            }
            List<ProductListVO> products = productService.getProductsWithPagination(page, size);
            return R.ok("分页查询成功", products);
        } catch (Exception e) {
            return R.fail("分页查询失败：" + e.getMessage());
        }
    }

    /**
     * 根据价格区间查询商品
     * GET /api/products/price-range?minPrice=10&maxPrice=100
     */
    @GetMapping("/price-range")
    public R<List<ProductPriceVO>> getProductsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        try {
            if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
                return R.badRequest("价格区间参数不正确");
            }
            List<ProductPriceVO> products = productService.getProductsByPriceRange(minPrice, maxPrice);
            return R.ok("价格区间查询成功", products);
        } catch (Exception e) {
            return R.fail("价格区间查询失败：" + e.getMessage());
        }
    }

    /**
     * 根据评分区间查询商品
     * GET /api/products/rating-range?minRating=4.0&maxRating=5.0
     */
    @GetMapping("/rating-range")
    public R<List<ProductRankVO>> getProductsByRatingRange(
            @RequestParam Double minRating,
            @RequestParam Double maxRating) {
        try {
            if (minRating < 0 || maxRating < 0 || minRating > maxRating || maxRating > 5.0) {
                return R.badRequest("评分区间参数不正确");
            }
            List<ProductRankVO> products = productService.getProductsByRatingRange(minRating, maxRating);
            return R.ok("评分区间查询成功", products);
        } catch (Exception e) {
            return R.fail("评分区间查询失败：" + e.getMessage());
        }
    }

    /**
     * 根据商品状态查询商品
     * GET /api/products/status/{status}
     */
    @GetMapping("/status/{status}")
    public R<List<ProductInventoryVO>> getProductsByStatus(@PathVariable String status) {
        try {
            List<ProductInventoryVO> products = productService.getProductsByStatus(status);
            return R.ok("状态查询成功", products);
        } catch (Exception e) {
            return R.fail("状态查询失败：" + e.getMessage());
        }
    }

    /**
     * 查询热销商品（根据排名）
     * GET /api/products/top-selling?limit=20
     */
    @GetMapping("/top-selling")
    public R<List<ProductRankVO>> getTopSellingProducts(
            @RequestParam(defaultValue = "20") int limit) {
        try {
            if (limit <= 0) {
                return R.badRequest("限制数量必须大于0");
            }
            List<ProductRankVO> products = productService.getTopSellingProducts(limit);
            return R.ok("热销商品查询成功", products);
        } catch (Exception e) {
            return R.fail("热销商品查询失败：" + e.getMessage());
        }
    }

    /**
     * 统计商品总数
     * GET /api/products/count
     */
    @GetMapping("/count")
    public R<Integer> getTotalProductCount() {
        try {
            int count = productService.getTotalProductCount();
            return R.ok("统计成功", count);
        } catch (Exception e) {
            return R.fail("统计商品总数失败：" + e.getMessage());
        }
    }
}
