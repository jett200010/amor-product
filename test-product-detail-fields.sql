-- ===================================================================
-- 测试新添加的商品详细信息字段
-- 用于验证 main_ingredients, health_benefits, suitable_for_elderly 字段
-- ===================================================================

USE amor_product;

-- 更新示例商品数据，添加新字段的内容
UPDATE product_basic_info
SET
    main_ingredients = '["EPA (1200毫克)", "DHA (800毫克)", "维生素E", "天然柠檬味"]',
    health_benefits = '["有益心脏健康", "促进大脑功能", "减轻关节炎症", "改善胆固醇水平"]',
    suitable_for_elderly = '["易于吞咽的软胶囊", "标签大而清晰，便于阅读", "经第三方检测纯度", "没有鱼腥味", "推荐剂量已明确标示"]'
WHERE product_id = 'B0FFSRKCRD';

-- 如果没有示例数据，插入一条测试数据
INSERT IGNORE INTO product_basic_info (
    product_id, title, brand, seller_name, platform, product_url, status,
    main_image_url, product_description, selling_points,
    main_ingredients, health_benefits, suitable_for_elderly,
    created_at, updated_at
) VALUES (
    'B0FFSRKCRD',
    'VIVEBEI 鱼油胶囊 2000毫克 - 含1200毫克EPA + 800毫克DHA',
    'VIVEBEI',
    'VIVEBEI Official Store',
    'amazon',
    'https://www.amazon.com/dp/B0FFSRKCRD',
    'active',
    'https://example.com/images/fish-oil-main.jpg',
    '高品质鱼油胶囊，富含EPA和DHA，支持心脏和大脑健康',
    '完美契合您的心脏健康目标，并深受老年人好评',
    '["EPA (1200毫克)", "DHA (800毫克)", "维生素E", "天然柠檬味"]',
    '["有益心脏健康", "促进大脑功能", "减轻关节炎症", "改善胆固醇水平"]',
    '["易于吞咽的软胶囊", "标签大而清晰，便于阅读", "经第三方检测纯度", "没有鱼腥味", "推荐剂量已明确标示"]',
    NOW(),
    NOW()
);

-- 查询验证数据
SELECT
    product_id,
    title,
    brand,
    main_ingredients,
    health_benefits,
    suitable_for_elderly
FROM product_basic_info
WHERE product_id = 'B0FFSRKCRD';

-- 测试完整查询（模拟API调用的查询）
SELECT
    pbi.id, pbi.product_id, pbi.title, pbi.brand,
    pbi.main_ingredients, pbi.health_benefits, pbi.suitable_for_elderly,
    pbi.product_description, pbi.selling_points,
    pbi.created_at, pbi.updated_at
FROM product_basic_info pbi
WHERE pbi.product_id = 'B0FFSRKCRD';
