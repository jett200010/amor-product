# 商品详情接口增强说明

## 功能概述
在原有的商品详情查询接口基础上，现在返回的 `ProductFullInfo` 对象中新增了两个列表：
1. **评论列表** (`reviewList`) - 商品的所有评论信息
2. **价格对比列表** (`priceComparisonList`) - 商品在不同平台的价格对比信息

## API 接口

### 查询商品详情（含评论和价格对比）
**接口地址：** `GET /api/products/{productId}`

**请求示例：**
```bash
GET http://localhost:8080/api/products/B0FFSRKCRD
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "id": 1,
    "productId": "B0FFSRKCRD",
    "title": "商品标题",
    "brand": "品牌名称",
    "platform": "Amazon",
    "currentPrice": 29.99,
    "originalPrice": 39.99,
    "currency": "USD",
    "rating": 4.8,
    "reviewsCount": 209,
    "mainImageUrl": "https://...",
    "productDescription": "商品描述...",
    
    "reviewList": [
      {
        "id": 1,
        "productId": "B0FFSRKCRD",
        "username": "老年人客易吞咽",
        "content": "没有鱼腥味",
        "isPositive": true,
        "likeCount": 186,
        "reviewTime": "2025-01-15T10:30:00",
        "isVerifiedPurchase": true
      },
      {
        "id": 2,
        "productId": "B0FFSRKCRD",
        "username": "医生",
        "content": "医生推荐了这个牌子。",
        "isPositive": true,
        "likeCount": 89,
        "reviewTime": "2025-01-20T14:25:00",
        "isVerifiedPurchase": true
      },
      {
        "id": 4,
        "productId": "B0FFSRKCRD",
        "username": "某用户",
        "content": "欺骗对某些人来说有点大。",
        "isPositive": false,
        "likeCount": 23,
        "reviewTime": "2025-01-18T16:45:00",
        "isVerifiedPurchase": true
      }
    ],
    
    "priceComparisonList": [
      {
        "id": 1,
        "productId": "B0FFSRKCRD",
        "platform": "iHerb",
        "platformProductId": "IH456789",
        "platformUrl": "https://www.iherb.com/pr/456789",
        "currentPrice": 27.99,
        "originalPrice": 35.99,
        "discountRate": 22.23,
        "currency": "USD",
        "stockStatus": "in_stock",
        "shippingFee": 3.50,
        "isPrime": false,
        "estimatedDeliveryDays": 7,
        "sellerName": "iHerb",
        "sellerRating": 4.7,
        "priceUpdateTime": "2025-11-04T08:45:00"
      },
      {
        "id": 2,
        "productId": "B0FFSRKCRD",
        "platform": "Walmart",
        "platformProductId": "WM987654321",
        "platformUrl": "https://www.walmart.com/ip/987654321",
        "currentPrice": 28.88,
        "originalPrice": 38.00,
        "discountRate": 24.00,
        "currency": "USD",
        "stockStatus": "in_stock",
        "shippingFee": 0.00,
        "isPrime": true,
        "estimatedDeliveryDays": 3,
        "sellerName": "Walmart",
        "sellerRating": 4.6,
        "priceUpdateTime": "2025-11-04T10:15:00"
      },
      {
        "id": 3,
        "productId": "B0FFSRKCRD",
        "platform": "Amazon",
        "platformProductId": "B0FFSRKCRD",
        "platformUrl": "https://www.amazon.com/dp/B0FFSRKCRD",
        "currentPrice": 29.99,
        "originalPrice": 39.99,
        "discountRate": 25.00,
        "currency": "USD",
        "stockStatus": "in_stock",
        "shippingFee": 0.00,
        "isPrime": true,
        "estimatedDeliveryDays": 2,
        "sellerName": "Amazon.com",
        "sellerRating": 4.8,
        "priceUpdateTime": "2025-11-04T10:00:00"
      },
      {
        "id": 4,
        "productId": "B0FFSRKCRD",
        "platform": "eBay",
        "platformProductId": "EB123456789",
        "platformUrl": "https://www.ebay.com/itm/123456789",
        "currentPrice": 32.50,
        "originalPrice": 42.00,
        "discountRate": 22.62,
        "currency": "USD",
        "stockStatus": "in_stock",
        "shippingFee": 5.99,
        "isPrime": false,
        "estimatedDeliveryDays": 5,
        "sellerName": "HealthStore",
        "sellerRating": 4.5,
        "priceUpdateTime": "2025-11-04T09:30:00"
      }
    ]
  }
}
```

## 新增字段说明

### ProductFullInfo 新增字段

| 字段名 | 类型 | 说明 |
|--------|------|------|
| reviewList | List<ProductReview> | 商品评论列表 |
| priceComparisonList | List<ProductPriceComparison> | 价格对比列表 |

### ReviewList 字段说明

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | Long | 评论ID |
| productId | String | 商��ID |
| username | String | 评论用户名 |
| content | String | 评论内容 |
| isPositive | Boolean | 是否为好评（true=好评，false=差评） |
| likeCount | Integer | 点赞数 |
| reviewTime | LocalDateTime | 评论时间 |
| isVerifiedPurchase | Boolean | 是否已验证购买 |

### PriceComparisonList 字段说明

| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | Long | 记录ID |
| productId | String | 商品ID |
| platform | String | 平台名称 |
| platformProductId | String | 平台商品ID |
| platformUrl | String | 平台商品链接 |
| currentPrice | BigDecimal | 当前价格 |
| originalPrice | BigDecimal | 原价 |
| discountRate | BigDecimal | 折扣率（百分比） |
| currency | String | 货币类型 |
| stockStatus | String | 库存状态 |
| shippingFee | BigDecimal | 运费 |
| isPrime | Boolean | 是否支持Prime/会员免邮 |
| estimatedDeliveryDays | Integer | 预计送达天数 |
| sellerName | String | 卖家名称 |
| sellerRating | BigDecimal | 卖家评分 |
| priceUpdateTime | LocalDateTime | 价格更新时间 |

## 实现逻辑

1. **查询商品基本信息**：从原有的商品表中查询商品详细信息
2. **查询评论列表**：根据商品ID从 `product_review` 表查询所有评论
3. **查询价格对比列表**：根据商品ID从 `product_price_comparison` 表查询各平台价格，按价格升序排列

## 数据特点

- **评论数据**：包含好评和差评，可以根据 `isPositive` 字段进行分类展示
- **价格对比数据**：按价格从低到高排序，方便用户快速找到最优价格
- **空列表处理**：如果商品没有评论或价格对比数据，返回空数组 `[]`

## 使用建议

前端在展示商品详情时，可以：
1. 将好评和差评分开展示（参考图片中的布局）
2. 显示价格对比表格，帮助用户选择最优购买渠道
3. 高亮显示最低价格的平台
4. 显示评论的点赞数和验证购买标识

