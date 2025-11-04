# 智能盲盒 API 文档

## 概述
智能盲盒系统根据配置的规则（商品数量、价格区间、分类）**动态从商品库中随机抽取商品**组合成盲盒。
- **不是预先配置好的固定套餐**
- **每次调用可能返回不同的商品组合**
- **自动确保总价在指定范围内**

## 核心设计思路

### 1. 盲盒规则配置（后端管理）
管理员配置规则，而不是配置具体商品：
```json
{
  "name": "高级健康套餐",
  "theme": "易延容颜",
  "category": "rootCategory",           // 从哪个字段筛选
  "categoryValue": "Health & Household", // 分类值
  "minProductCount": 3,                  // 最少3件
  "maxProductCount": 5,                  // 最多5件
  "minTotalPrice": 100.00,               // 总价至少100
  "maxTotalPrice": 200.00,               // 总价最多200
  "discountPercentage": 92               // 显示"匹配度92%"
}
```

### 2. 智能生成算法
系统会：
1. 从指定分类随机抽取候选商品
2. 智能选择商品组合，确保总价在范围内
3. 每次调用返回不同的商品组合

### 3. 前端负责展示
后端只返回商品数据，前端负责：
- 徽章样式（匹配度92%、七折优惠）
- 按钮文本和样式
- 特性列表展示
- 配送选项显示

## 返回数据结构

```json
{
  "code": 200,
  "message": "查询成功",
  "data": [
    {
      "ruleId": "RULE001",
      "name": "高级健康套餐",
      "description": "包含优质维生素、欧米伽-3、益生菌和健康日志的综合健康套装",
      "theme": "易延容颜",
      "totalOriginalPrice": 189.99,
      "totalCurrentPrice": 129.99,
      "currency": "USD",
      "discountPercentage": 92,
      "savedAmount": 60.00,
      "productCount": 4,
      "products": [
        {
          "productId": "PROD001",
          "title": "Nature Made Multi Complete 维生素",
          "brand": "Nature Made",
          "originalPrice": 45.99,
          "currentPrice": 32.99,
          "currency": "USD",
          "mainImageUrl": "/images/product1.jpg",
          "categoryName": "Vitamins & Supplements",
          "rating": 4.5,
          "reviewsCount": 1234,
          "availability": "In Stock",
          "primeEligible": true
        },
        {
          "productId": "PROD002",
          "title": "Nordic Naturals Omega-3 鱼油",
          "brand": "Nordic Naturals",
          "originalPrice": 52.00,
          "currentPrice": 38.00,
          "currency": "USD",
          "mainImageUrl": "/images/product2.jpg",
          "categoryName": "Omega-3",
          "rating": 4.7,
          "reviewsCount": 890,
          "availability": "In Stock",
          "primeEligible": true
        }
        // ... 更多商品
      ]
    }
  ]
}
```

## API 接口

### 1. 获取所有盲盒（动态生成）
**GET** `/api/blind-boxes`

**说明:** 
- 根据所有激活的规则动态生成盲盒
- 每次调用可能返回不同的商品组合
- 适合首页展示盲盒列表

**响应示例:** 见上方数据结构

---

### 2. 根据规则生成盲盒
**GET** `/api/blind-boxes/generate/{ruleId}`

**说明:** 
- 根据指定规则ID生成盲盒
- 适合"再次生成"或"换一批"功能

**参数:**
- `ruleId`: 规则ID（如："RULE001"）

**响应示例:**
```json
{
  "code": 200,
  "message": "生成成功",
  "data": {
    "ruleId": "RULE001",
    "name": "高级健康套餐",
    "totalOriginalPrice": 189.99,
    "totalCurrentPrice": 129.99,
    "currency": "USD",
    "discountPercentage": 92,
    "savedAmount": 60.00,
    "productCount": 4,
    "products": [...]
  }
}
```

---

### 3. 创建盲盒规则（管理员）
**POST** `/api/blind-boxes/rules`

**请求体:**
```json
{
  "name": "高级健康套餐",
  "description": "包含优质维生素、欧米伽-3、益生菌和健康日志的综合健康套装",
  "theme": "易延容颜",
  "category": "rootCategory",
  "categoryValue": "Health & Household",
  "minProductCount": 3,
  "maxProductCount": 5,
  "minTotalPrice": 100.00,
  "maxTotalPrice": 200.00,
  "discountPercentage": 92,
  "status": "ACTIVE"
}
```

**响应:**
```json
{
  "code": 200,
  "message": "创建成功",
  "data": "RULE001"
}
```

---

### 4. 更新盲盒规则（管理员）
**PUT** `/api/blind-boxes/rules/{ruleId}`

**请求体:** 同创建接口

---

### 5. 删除盲盒规则（管理员）
**DELETE** `/api/blind-boxes/rules/{ruleId}`

---

## 前端使用示例

### 示例1: 展示盲盒列表
```javascript
// 获取盲盒列表
fetch('/api/blind-boxes')
  .then(response => response.json())
  .then(data => {
    if (data.code === 200) {
      data.data.forEach(blindBox => {
        // 后端返回的数据
        console.log('名称:', blindBox.name);
        console.log('主题:', blindBox.theme);
        console.log('总原价:', blindBox.totalOriginalPrice);
        console.log('总现价:', blindBox.totalCurrentPrice);
        console.log('节省:', blindBox.savedAmount);
        console.log('商品数量:', blindBox.productCount);
        console.log('折扣百分比:', blindBox.discountPercentage);
        
        // 前端处理显示
        const discountBadge = `匹配度${blindBox.discountPercentage}%`;
        const promotionBadge = calculatePromotionText(blindBox.discountPercentage);
        const priceDisplay = `${blindBox.totalCurrentPrice}${blindBox.currency}`;
        const savedDisplay = `节省 ${blindBox.savedAmount}`;
        
        // 遍历商品
        blindBox.products.forEach(product => {
          console.log('商品:', product.title);
          console.log('品牌:', product.brand);
          console.log('价格:', product.currentPrice);
          console.log('图片:', product.mainImageUrl);
          console.log('评分:', product.rating);
          console.log('评论数:', product.reviewsCount);
        });
      });
    }
  });

function calculatePromotionText(discountPercentage) {
  if (discountPercentage >= 90) return '七折优惠';
  if (discountPercentage >= 80) return '七五折';
  if (discountPercentage >= 70) return '八折';
  return '优惠';
}
```

### 示例2: "换一批"功能
```javascript
// 点击"换一批"按钮
function refreshBlindBox(ruleId) {
  fetch(`/api/blind-boxes/generate/${ruleId}`)
    .then(response => response.json())
    .then(data => {
      if (data.code === 200) {
        // 更新页面显示新的商品组合
        updateBlindBoxDisplay(data.data);
      }
    });
}
```

### 示例3: 前端特性列表生成
```javascript
// 根据商品数据生成特性列表
function generateFeatures(products) {
  return products.map(product => ({
    icon: getCategoryIcon(product.categoryName),
    text: product.title.substring(0, 20),
    color: '#E91E63'
  }));
}

function getCategoryIcon(categoryName) {
  const iconMap = {
    'Vitamins & Supplements': '💊',
    'Omega-3': '🐟',
    'Probiotics': '🌿',
    'Health Journals': '📔'
  };
  return iconMap[categoryName] || '⭐';
}
```

## 规则配置说明

### category 字段可选值
- `rootCategory`: 根分类（如："Health & Household"）
- `subcategory`: 子分类（如："Vitamins & Supplements"）
- `categoryName`: 分类名称

### 价格范围设置建议
- `minTotalPrice` 和 `maxTotalPrice` 差距不要太小
- 建议差距至少50以上，便于算法找到合适的组合
- 如果范围太窄，可能无法生成盲盒

### 商品数量设置
- `minProductCount` 最少1件
- `maxProductCount` 建议不超过10件
- 系统会随机选择范围内的数量

## 数据库初始化

执行 `blind-box-schema.sql` 创建规则表和测试数据：

```bash
mysql -u your_user -p your_database < blind-box-schema.sql
```

## 优势

1. **灵活性高**: 不需要手动配置每个商品，只需要设置规则
2. **自动化**: 系统自动筛选符合条件的商品
3. **随机性**: 每次生成不同组合，增加趣味性
4. **价格控制**: 自动确保总价在范围内
5. **易维护**: 只需维护规则，不需要维护具体商品组合

## 注意事项

1. **商品数据要完整**: 确保商品表有足够的数据，且价格字段不为空
2. **分类要准确**: category_value 要与数据库中的分类值匹配
3. **价格范围合理**: 范围太窄可能找不到合适的商品组合
4. **缓存考虑**: 如果商品数据量大，可以考虑缓存候选商品列表

