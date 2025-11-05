# 商品评论 API 文档

## 概述
商品评论系统用于管理商品的用户评价，支持好评和差评的区分展示，包含评论内容、点赞数、评论时间等信息。

## 数据库表结构

### product_review 表
| 字段名 | 类型 | 说明 |
|--------|------|------|
| id | BIGINT | 自增主键 |
| product_id | VARCHAR(50) | 商品ID |
| username | VARCHAR(100) | 评论用户名 |
| content | TEXT | 评论内容 |
| is_positive | TINYINT(1) | 是否为好评（1：好评，0：差评） |
| like_count | INT | 点赞数 |
| review_time | DATETIME | 评论时间 |
| is_verified_purchase | TINYINT(1) | 是否已验证购买 |
| created_at | DATETIME | 创建时间 |
| updated_at | DATETIME | 更新时间 |

## API 接口

### 1. 获取商品评论汇总（好评和差评分开）
**接口地址：** `GET /api/product/review/summary/{productId}`

**请求参数：**
- `productId` (路径参数): 商品ID
- `limit` (查询参数，可选): 每类评论返回数量，默认10条

**响应示例：**
```json
{
  "code": 200,
  "msg": "success",
  "data": {
    "productId": "B0FFSRKCRD",
    "positiveCount": 186,
    "negativeCount": 23,
    "totalCount": 209,
    "positiveReviews": [
      {
        "id": 1,
        "productId": "B0FFSRKCRD",
        "username": "老年人客易吞咽",
        "content": "没有鱼腥味",
        "isPositive": true,
        "likeCount": 186,
        "reviewTime": "2025-01-15T10:30:00",
        "isVerifiedPurchase": true
      }
    ],
    "negativeReviews": [
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
    ]
  }
}
```

### 2. 获取商品所有评论
**接口地址：** `GET /api/product/review/{productId}`

**请求参数：**
- `productId` (路径参数): 商品ID

**响应示例：**
```json
{
  "code": 200,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "productId": "B0FFSRKCRD",
      "username": "老年人客易吞咽",
      "content": "没有鱼腥味",
      "isPositive": true,
      "likeCount": 186,
      "reviewTime": "2025-01-15T10:30:00",
      "isVerifiedPurchase": true
    }
  ]
}
```

### 3. 获取好评列表
**接口地址：** `GET /api/product/review/{productId}/positive`

**请求参数：**
- `productId` (路径参数): 商品ID
- `limit` (查询参数，可选): 返回数量，默认10条

**响应示例：**
```json
{
  "code": 200,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "username": "老年人客易吞咽",
      "content": "没有鱼腥味",
      "likeCount": 186,
      "reviewTime": "2025-01-15T10:30:00"
    }
  ],
  "count": 186
}
```

### 4. 获取差评列表
**接口地址：** `GET /api/product/review/{productId}/negative`

**请求参数：**
- `productId` (路径参数): 商品ID
- `limit` (查询参数，可选): 返回数量，默认10条

**响应示例：**
```json
{
  "code": 200,
  "msg": "success",
  "data": [
    {
      "id": 4,
      "username": "某用户",
      "content": "欺骗对某些人来说有点大。",
      "likeCount": 23,
      "reviewTime": "2025-01-18T16:45:00"
    }
  ],
  "count": 23
}
```

### 5. 添加评论
**接口地址：** `POST /api/product/review`

**请求体：**
```json
{
  "productId": "B0FFSRKCRD",
  "username": "测试用户",
  "content": "商品质量不错，值得购买",
  "isPositive": true,
  "likeCount": 0,
  "reviewTime": "2025-11-04T10:00:00",
  "isVerifiedPurchase": true
}
```

**响应示例：**
```json
{
  "code": 200,
  "msg": "success",
  "message": "评论添加成功"
}
```

### 6. 更新评论点赞数
**接口地址：** `PUT /api/product/review/{id}/like`

**请求参数：**
- `id` (路径参数): 评论ID
- `likeCount` (查询参数): 新的点赞数

**响应示例：**
```json
{
  "code": 200,
  "msg": "success",
  "message": "点赞成功"
}
```

### 7. 删除评论
**接口地址：** `DELETE /api/product/review/{id}`

**请求参数：**
- `id` (路径参数): 评论ID

**响应示例：**
```json
{
  "code": 200,
  "msg": "success",
  "message": "评论删除成功"
}
```

## 使用说明

### 1. 创建数据库表
执行 `review-table.sql` 文件创建评论表：
```sql
mysql -u your_username -p amor_product < review-table.sql
```

### 2. 测试接口
使用 Postman 或 curl 测试接口：

**获取评论汇总：**
```bash
curl http://localhost:8080/api/product/review/summary/B0FFSRKCRD?limit=5
```

**获取好评：**
```bash
curl http://localhost:8080/api/product/review/B0FFSRKCRD/positive?limit=10
```

**获取差评：**
```bash
curl http://localhost:8080/api/product/review/B0FFSRKCRD/negative?limit=10
```

## 特性说明

1. **好评差评分类**：通过 `is_positive` 字段区分好评（1）和差评（0）
2. **点赞排序**：评论按点赞数和时间排序，热门评论优先显示
3. **验证购买标识**：`is_verified_purchase` 标识是否为已验证的购买用户
4. **统计信息**：提供好评数、差评数、总评论数的统计

## 数据示例

根据图片中的评价内容，已预置了示例数据：

**好评示例：**
- "老年人客易吞咽，没有鱼腥味" - 186位顾客提及
- "医生推荐了这个牌子。" - 89位顾客提及
- "两周内关节疼痛明显减轻" - 134位顾客提及

**差评示例：**
- "欺骗对某些人来说有点大。" - 23位顾客提及

