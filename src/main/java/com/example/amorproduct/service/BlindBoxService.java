package com.example.amorproduct.service;

import com.example.amorproduct.domain.BlindBoxRule;
import com.example.amorproduct.domain.ProductFullInfo;
import com.example.amorproduct.domain.vo.BlindBoxVO;
import com.example.amorproduct.mapper.BlindBoxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BlindBoxService {

    @Autowired
    private BlindBoxMapper blindBoxMapper;

    /**
     * 获取所有可用的盲盒（动态生成）
     */
    public List<BlindBoxVO> getAllActiveBlindBoxes() {
        List<BlindBoxRule> rules = blindBoxMapper.getAllActiveRules();
        return rules.stream()
                .map(this::generateBlindBox)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    /**
     * 根据规则ID生成盲盒
     */
    public BlindBoxVO generateBlindBoxByRuleId(String ruleId) {
        BlindBoxRule rule = blindBoxMapper.getRuleById(ruleId);
        if (rule == null) {
            return null;
        }
        return generateBlindBox(rule);
    }

    /**
     * 根据规则动态生成盲盒
     */
    private BlindBoxVO generateBlindBox(BlindBoxRule rule) {
        // 随机确定商品数量
        int productCount = randomBetween(rule.getMinProductCount(), rule.getMaxProductCount());

        // 计算每件商品的平均价格范围
        BigDecimal avgMinPrice = rule.getMinTotalPrice().divide(
            new BigDecimal(rule.getMaxProductCount()), 2, RoundingMode.HALF_UP);
        BigDecimal avgMaxPrice = rule.getMaxTotalPrice().divide(
            new BigDecimal(rule.getMinProductCount()), 2, RoundingMode.HALF_UP);

        // 从数据库随机获取商品（获取更多商品以便筛选）
        List<ProductFullInfo> candidateProducts = blindBoxMapper.getRandomProductsByCategory(
            rule.getCategory(),
            rule.getCategoryValue(),
            avgMinPrice,
            avgMaxPrice,
            productCount * 3
        );

        if (candidateProducts == null || candidateProducts.isEmpty()) {
            return null;
        }

        // 智能选择商品，使总价在规定范围内
        List<ProductFullInfo> selectedProducts = selectProductsWithinPriceRange(
            candidateProducts,
            productCount,
            rule.getMinTotalPrice(),
            rule.getMaxTotalPrice()
        );

        if (selectedProducts.isEmpty()) {
            return null;
        }

        // 构建返回对象
        BlindBoxVO vo = new BlindBoxVO();
        vo.setRuleId(rule.getRuleId());
        vo.setName(rule.getName());
        vo.setDescription(rule.getDescription());
        vo.setTheme(rule.getTheme());
        vo.setDiscountPercentage(rule.getDiscountPercentage());
        vo.setProductCount(selectedProducts.size());

        // 计算价格
        BigDecimal totalOriginal = BigDecimal.ZERO;
        BigDecimal totalCurrent = BigDecimal.ZERO;
        String currency = "美元";

        List<BlindBoxVO.BlindBoxProductItem> productItems = new ArrayList<>();
        for (ProductFullInfo product : selectedProducts) {
            BlindBoxVO.BlindBoxProductItem item = new BlindBoxVO.BlindBoxProductItem();
            item.setProductId(product.getProductId());
            item.setTitle(product.getTitle());
            item.setBrand(product.getBrand());
            item.setOriginalPrice(product.getOriginalPrice());
            item.setCurrentPrice(product.getCurrentPrice());
            item.setCurrency(product.getCurrency());
            item.setMainImageUrl(product.getMainImageUrl());
            item.setCategoryName(product.getCategoryName());
            item.setRating(product.getRating());
            item.setReviewsCount(product.getReviewsCount());
            item.setAvailability(product.getAvailability());
            item.setPrimeEligible(product.getPrimeEligible());

            productItems.add(item);

            if (product.getOriginalPrice() != null) {
                totalOriginal = totalOriginal.add(product.getOriginalPrice());
            }
            if (product.getCurrentPrice() != null) {
                totalCurrent = totalCurrent.add(product.getCurrentPrice());
            }
            if (product.getCurrency() != null) {
                currency = product.getCurrency();
            }
        }

        vo.setProducts(productItems);
        vo.setTotalOriginalPrice(totalOriginal);
        vo.setTotalCurrentPrice(totalCurrent);
        vo.setCurrency(currency);
        vo.setSavedAmount(totalOriginal.subtract(totalCurrent));

        return vo;
    }

    /**
     * 智能选择商品，使总价在指定范围内
     */
    private List<ProductFullInfo> selectProductsWithinPriceRange(
            List<ProductFullInfo> candidates,
            int targetCount,
            BigDecimal minTotal,
            BigDecimal maxTotal) {

        // 过滤掉价格为空的商品
        List<ProductFullInfo> validProducts = candidates.stream()
            .filter(p -> p.getCurrentPrice() != null)
            .collect(Collectors.toList());

        if (validProducts.size() < targetCount) {
            targetCount = validProducts.size();
        }

        // 尝试多次随机组合，找到符合价格范围的组合
        int maxAttempts = 50;
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            Collections.shuffle(validProducts);
            List<ProductFullInfo> selected = validProducts.stream()
                .limit(targetCount)
                .collect(Collectors.toList());

            BigDecimal total = selected.stream()
                .map(ProductFullInfo::getCurrentPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            if (total.compareTo(minTotal) >= 0 && total.compareTo(maxTotal) <= 0) {
                return selected;
            }
        }

        // 如果找不到完美匹配，返回最接近的组合
        Collections.shuffle(validProducts);
        return validProducts.stream()
            .limit(targetCount)
            .collect(Collectors.toList());
    }

    /**
     * 生成指定范围内的随机数
     */
    private int randomBetween(int min, int max) {
        if (min >= max) {
            return min;
        }
        return min + new Random().nextInt(max - min + 1);
    }

    /**
     * 创建盲盒规则
     */
    public String createRule(BlindBoxRule rule) {
        String ruleId = "RULE" + UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
        rule.setRuleId(ruleId);

        if (rule.getStatus() == null) {
            rule.setStatus("ACTIVE");
        }

        blindBoxMapper.createRule(rule);
        return ruleId;
    }

    /**
     * 更新盲盒规则
     */
    public boolean updateRule(BlindBoxRule rule) {
        return blindBoxMapper.updateRule(rule) > 0;
    }

    /**
     * 删除盲盒规则
     */
    public boolean deleteRule(String ruleId) {
        return blindBoxMapper.deleteRule(ruleId) > 0;
    }
}

