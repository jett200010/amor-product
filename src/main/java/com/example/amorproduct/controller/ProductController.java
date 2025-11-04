package com.example.amorproduct.controller;

import com.example.amorproduct.domain.ProductFullInfo;
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
     * 根据平台查询商品列表
     * GET /api/products/platform/{platform}
     */
    @GetMapping("/platform/{platform}")
    public R<List<ProductFullInfo>> getProductsByPlatform(@PathVariable String platform) {
        try {
            List<ProductFullInfo> products = productService.getProductsByPlatform(platform);
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
    public R<List<ProductFullInfo>> getProductsByBrand(@PathVariable String brand) {
        try {
            List<ProductFullInfo> products = productService.getProductsByBrand(brand);
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
    public R<List<ProductFullInfo>> getProductsBySellerId(@PathVariable String sellerId) {
        try {
            List<ProductFullInfo> products = productService.getProductsBySellerId(sellerId);
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
    public R<List<ProductFullInfo>> getProductsByRootCategory(@PathVariable String rootCategory) {
        try {
            List<ProductFullInfo> products = productService.getProductsByRootCategory(rootCategory);
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
    public R<List<ProductFullInfo>> getProductsWithPagination(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            if (page <= 0 || size <= 0) {
                return R.badRequest("页码和每页大小必须大于0");
            }
            List<ProductFullInfo> products = productService.getProductsWithPagination(page, size);
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
    public R<List<ProductFullInfo>> getProductsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        try {
            if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
                return R.badRequest("价格区间参数不正确");
            }
            List<ProductFullInfo> products = productService.getProductsByPriceRange(minPrice, maxPrice);
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
    public R<List<ProductFullInfo>> getProductsByRatingRange(
            @RequestParam Double minRating,
            @RequestParam Double maxRating) {
        try {
            if (minRating < 0 || maxRating < 0 || minRating > maxRating || maxRating > 5.0) {
                return R.badRequest("评分区间参数不正确");
            }
            List<ProductFullInfo> products = productService.getProductsByRatingRange(minRating, maxRating);
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
    public R<List<ProductFullInfo>> getProductsByStatus(@PathVariable String status) {
        try {
            List<ProductFullInfo> products = productService.getProductsByStatus(status);
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
    public R<List<ProductFullInfo>> getTopSellingProducts(
            @RequestParam(defaultValue = "20") int limit) {
        try {
            if (limit <= 0) {
                return R.badRequest("限制数量必须大于0");
            }
            List<ProductFullInfo> products = productService.getTopSellingProducts(limit);
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
