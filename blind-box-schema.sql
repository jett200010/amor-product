-- 智能盲盒规则配置表

-- 盲盒规则表
CREATE TABLE IF NOT EXISTS blind_box_rule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    rule_id VARCHAR(64) NOT NULL UNIQUE COMMENT '规则ID',
    name VARCHAR(255) NOT NULL COMMENT '盲盒名称',
    description TEXT COMMENT '盲盒描述',
    theme VARCHAR(100) COMMENT '主题分类（如：易延容颜、适合初学者）',

    -- 商品筛选条件
    category VARCHAR(50) NOT NULL COMMENT '分类字段（rootCategory/subcategory/categoryName）',
    category_value VARCHAR(255) NOT NULL COMMENT '分类值',

    -- 数量规则
    min_product_count INT NOT NULL DEFAULT 3 COMMENT '最少商品数量',
    max_product_count INT NOT NULL DEFAULT 5 COMMENT '最多商品数量',

    -- 价格规则
    min_total_price DECIMAL(10,2) NOT NULL COMMENT '总价最低',
    max_total_price DECIMAL(10,2) NOT NULL COMMENT '总价最高',

    -- 折扣规则
    discount_percentage INT COMMENT '折扣百分比（用于徽章显示）',

    -- 状态
    status VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '状态（ACTIVE/INACTIVE）',

    create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    INDEX idx_status (status),
    INDEX idx_category (category, category_value)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='盲盒规则配置表';

-- 插入测试规则

-- ================================
-- 智能盲盒规则配置数据
-- ================================
INSERT INTO `blind_box_rule` (
    `rule_id`, `name`, `description`, `theme`,
    `category`, `category_value`,
    `min_product_count`, `max_product_count`,
    `min_total_price`, `max_total_price`,
    `discount_percentage`, `status`
) VALUES
-- ① 科技潮玩盲盒：电子产品类
('RULE_TEC001', '科技潮玩盲盒',
 '精选智能手机、蓝牙耳机、电子阅读器等高科技产品，适合追求前沿科技体验的用户。',
 '科技爱好者',
 'root_category', '电子产品',
 3, 5,
 5000.00, 12000.00,
 10, 'ACTIVE'),

-- ② 家庭好物盲盒：家用电器类
('RULE_HOME001', '家庭好物盲盒',
 '包含家用电器与娱乐设备的组合，如吸尘器、扫地机器人、游戏主机等，满足家庭生活场景。',
 '家庭生活',
 'root_category', '家用电器',
 2, 4,
 3000.00, 7000.00,
 12, 'ACTIVE'),

-- ③ 办公高效盲盒：办公设备类
('RULE_OFFICE001', '办公高效盲盒',
 '集合笔记本电脑、无线鼠标、充电器等，提高工作效率的数码办公装备。',
 '职场办公',
 'subcategory', '办公设备',
 2, 3,
 4000.00, 10000.00,
 8, 'ACTIVE'),

-- ④ 健康穿戴盲盒：智能穿戴类
('RULE_HEALTH001', '健康穿戴盲盒',
 '包含智能手表、运动耳机等穿戴设备，帮助用户科学管理健康与运动。',
 '健康生活',
 'subcategory', '穿戴设备',
 2, 3,
 1500.00, 3000.00,
 15, 'ACTIVE'),

-- ⑤ 游戏娱乐盲盒：游戏设备类
('RULE_FUN001', '游戏娱乐盲盒',
 '精选Switch主机、高端耳机、游戏配件等，为玩家打造沉浸式娱乐体验。',
 '娱乐玩家',
 'subcategory', '游戏设备',
 2, 3,
 4000.00, 6000.00,
 10, 'ACTIVE');


