-- ===================================================================
-- 商品数据库字段更新 SQL
-- 用于添加商品图片、描述、匹配度、徽章等新字段
-- ===================================================================

USE amor_product;

-- ===================================================================
-- 1. 商品基础信息表 (product_basic_info) - 新增字段
-- ===================================================================

-- 商品图片相关字段
ALTER TABLE product_basic_info ADD COLUMN main_image_url VARCHAR(500) COMMENT '主图URL' AFTER status;
ALTER TABLE product_basic_info ADD COLUMN image_urls JSON COMMENT '商品图片URL列表' AFTER main_image_url;

-- 商品描述相关字段
ALTER TABLE product_basic_info ADD COLUMN product_description TEXT COMMENT '商品详细描述' AFTER image_urls;
ALTER TABLE product_basic_info ADD COLUMN selling_points TEXT COMMENT '卖点描述/推荐理由' AFTER product_description;
ALTER TABLE product_basic_info ADD COLUMN specifications JSON COMMENT '商品规格参数(重量、容量等)' AFTER selling_points;

-- 快速购买链接
ALTER TABLE product_basic_info ADD COLUMN quick_buy_url VARCHAR(500) COMMENT '快速购买链接' AFTER specifications;


-- ===================================================================
-- 2. 商品评价排名表 (product_reviews_rank) - 新增字段
-- ===================================================================

-- 匹配度相关字段
ALTER TABLE product_reviews_rank ADD COLUMN match_percentage DECIMAL(5,2) COMMENT '匹配度百分比(如96、92、88)' AFTER category_name;
ALTER TABLE product_reviews_rank ADD COLUMN recommendation_reason VARCHAR(500) COMMENT '推荐原因' AFTER match_percentage;


-- ===================================================================
-- 3. 商品分类标签表 (product_category_tags) - 新增字段
-- ===================================================================

-- 商品徽章和促销标签
ALTER TABLE product_category_tags ADD COLUMN badges JSON COMMENT '商品徽章(易于吞咽、大字体标签、值得信赖的品牌等)' AFTER target_audience;
ALTER TABLE product_category_tags ADD COLUMN promotion_tags JSON COMMENT '促销标签(限时优惠、买一送一等)' AFTER badges;


-- ===================================================================
-- 4. 创建索引以优化查询性能
-- ===================================================================

-- 商品基础信息表索引
CREATE INDEX idx_main_image_url ON product_basic_info(main_image_url(255));

-- 评价排名表索引
CREATE INDEX idx_match_percentage ON product_reviews_rank(match_percentage);

-- 验证索引创建
SHOW INDEX FROM product_basic_info;
SHOW INDEX FROM product_reviews_rank;
SHOW INDEX FROM product_category_tags;


-- ===================================================================
-- 5. 查询示例 - 测试新字段
-- ===================================================================

-- 查询完整商品信息（包含所有新字段）
SELECT
    pbi.id, pbi.product_id, pbi.title, pbi.brand, pbi.platform,
    pbi.main_image_url, pbi.image_urls, pbi.product_description,
    pbi.selling_points, pbi.specifications, pbi.quick_buy_url,
    pp.current_price, pp.original_price, pp.currency,
    prr.rating, prr.reviews_count, prr.match_percentage, prr.recommendation_reason,
    pi.availability, pi.stock_quantity,
    pct.categories, pct.root_category, pct.badges, pct.promotion_tags,
    pbi.created_at, pbi.updated_at
FROM product_basic_info pbi
LEFT JOIN product_pricing pp ON pbi.product_id = pp.product_id
LEFT JOIN product_reviews_rank prr ON pbi.product_id = prr.product_id
LEFT JOIN product_inventory pi ON pbi.product_id = pi.product_id
LEFT JOIN product_category_tags pct ON pbi.product_id = pct.product_id
WHERE pbi.product_id = 'B0FFSRKCRD';

-- 查询带匹配度的商品列表
SELECT
    pbi.product_id, pbi.title, pbi.brand,
    pbi.main_image_url, pbi.selling_points,
    pp.current_price, pp.currency,
    prr.rating, prr.reviews_count, prr.match_percentage,
    pct.badges
FROM product_basic_info pbi
LEFT JOIN product_pricing pp ON pbi.product_id = pp.product_id
LEFT JOIN product_reviews_rank prr ON pbi.product_id = prr.product_id
LEFT JOIN product_category_tags pct ON pbi.product_id = pct.product_id
WHERE prr.match_percentage IS NOT NULL
ORDER BY prr.match_percentage DESC
LIMIT 10;

