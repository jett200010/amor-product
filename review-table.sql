-- ===================================================================
-- 商品评论表创建 SQL
-- 用于存储商品评价信息，区分好评和差评
-- ===================================================================

USE amor_product;

-- ===================================================================
-- 创建商品评论表
-- ===================================================================

CREATE TABLE IF NOT EXISTS product_review (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    product_id VARCHAR(50) NOT NULL COMMENT '商品唯一ID（关联商品表）',
    username VARCHAR(100) NOT NULL COMMENT '评论用户名',
    content TEXT NOT NULL COMMENT '评论内容',
    is_positive TINYINT(1) NOT NULL DEFAULT 1 COMMENT '是否为好评（1：好评，0：差评）',
    like_count INT DEFAULT 0 COMMENT '点赞数',
    review_time DATETIME NOT NULL COMMENT '评论时间',
    is_verified_purchase TINYINT(1) DEFAULT 0 COMMENT '是否已验证购买（1：是，0：否）',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_product_id (product_id),
    INDEX idx_is_positive (is_positive),
    INDEX idx_review_time (review_time),
    INDEX idx_like_count (like_count)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品评论表';


-- ===================================================================
-- 创建价格对比表
-- ===================================================================

CREATE TABLE IF NOT EXISTS product_price_comparison (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    product_id VARCHAR(50) NOT NULL COMMENT '商品唯一ID（关联商品表）',
    platform VARCHAR(50) NOT NULL COMMENT '平台名称（Amazon/eBay/Walmart/JD/Taobao等）',
    platform_product_id VARCHAR(100) COMMENT '平台商品ID',
    platform_url VARCHAR(500) COMMENT '平台商品链接',
    current_price DECIMAL(10,2) NOT NULL COMMENT '当前价格',
    original_price DECIMAL(10,2) COMMENT '原价',
    discount_rate DECIMAL(5,2) COMMENT '折扣率（百分比）',
    currency VARCHAR(10) DEFAULT 'USD' COMMENT '货��类型（USD/CNY/EUR等）',
    stock_status VARCHAR(20) COMMENT '库存状态（in_stock/out_of_stock/pre_order）',
    shipping_fee DECIMAL(10,2) DEFAULT 0.00 COMMENT '运费',
    is_prime TINYINT(1) DEFAULT 0 COMMENT '是否支持Prime/会员免邮（1：是，0：否）',
    estimated_delivery_days INT COMMENT '预计送达天数',
    seller_name VARCHAR(100) COMMENT '卖家名称',
    seller_rating DECIMAL(3,2) COMMENT '卖家评分（1.0-5.0）',
    price_update_time DATETIME NOT NULL COMMENT '价格更新时间',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_product_id (product_id),
    INDEX idx_platform (platform),
    INDEX idx_current_price (current_price),
    INDEX idx_price_update_time (price_update_time),
    UNIQUE KEY uk_product_platform (product_id, platform)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品价格对比表';


-- ===================================================================
-- 插入示例数据（参考图片中的评价内容）
-- ===================================================================

-- 好评示例数据
INSERT INTO product_review (product_id, username, content, is_positive, like_count, review_time, is_verified_purchase)
VALUES
    ('B0FFSRKCRD', '老年人客易吞咽', '没有鱼腥味', 1, 186, '2025-01-15 10:30:00', 1),
    ('B0FFSRKCRD', '医生', '医生推荐了这个牌子。', 1, 89, '2025-01-20 14:25:00', 1),
    ('B0FFSRKCRD', '药剂师', '两周内关节疼痛明显减轻', 1, 134, '2025-02-01 09:15:00', 1);

-- 差评示例数据
INSERT INTO product_review (product_id, username, content, is_positive, like_count, review_time, is_verified_purchase)
VALUES
    ('B0FFSRKCRD', '某用户', '欺骗对某些人来说有点大。', 0, 23, '2025-01-18 16:45:00', 1);


-- ===================================================================
-- 插入价格对比��例数据
-- ===================================================================

-- 价格对比示例数据（同一商品在不同平台的价格）
INSERT INTO product_price_comparison (product_id, platform, platform_product_id, platform_url, current_price, original_price, discount_rate, currency, stock_status, shipping_fee, is_prime, estimated_delivery_days, seller_name, seller_rating, price_update_time)
VALUES
    ('B0FFSRKCRD', 'Amazon', 'B0FFSRKCRD', 'https://www.amazon.com/dp/B0FFSRKCRD', 29.99, 39.99, 25.00, 'USD', 'in_stock', 0.00, 1, 2, 'Amazon.com', 4.8, '2025-11-04 10:00:00'),
    ('B0FFSRKCRD', 'eBay', 'EB123456789', 'https://www.ebay.com/itm/123456789', 32.50, 42.00, 22.62, 'USD', 'in_stock', 5.99, 0, 5, 'HealthStore', 4.5, '2025-11-04 09:30:00'),
    ('B0FFSRKCRD', 'Walmart', 'WM987654321', 'https://www.walmart.com/ip/987654321', 28.88, 38.00, 24.00, 'USD', 'in_stock', 0.00, 1, 3, 'Walmart', 4.6, '2025-11-04 10:15:00'),
    ('B0FFSRKCRD', 'iHerb', 'IH456789', 'https://www.iherb.com/pr/456789', 27.99, 35.99, 22.23, 'USD', 'in_stock', 3.50, 0, 7, 'iHerb', 4.7, '2025-11-04 08:45:00');
