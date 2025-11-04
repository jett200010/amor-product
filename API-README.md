# 商品查询接口文档

## 项目概述

本项目提供了完整的商品信息查询接口，支持多平台（Amazon、eBay、Walmart）商品数据的统一查询。

## 数据库表结构

### 1. product_basic_info - 商品基础信息表
- 存储商品的基本信息：ID、标题、品牌、卖家、平台等

### 2. product_pricing - 商品价格信息表
- 存储价格、折扣、货币类型、价格历史等信息

### 3. product_reviews_rank - 商品评价与排名表
- 存储评分、评价数量、排名等信息

### 4. product_inventory - 商品库存与物流表
- 存储库存状态、发货地、配送选项等信息

### 5. product_category_tags - 商品分类标签表
- 存储分类路径、根分类、子分类、产品类型等信息

## API接口列表

### 基础查询接口

#### 1. 根据商品ID查询完整信息
```
GET /api/products/{productId}
```
**参数：**
- `productId` (路径参数): 商品唯一ID

**响应示例：**
```json
{
  "code": 200,
  "msg": "操作成功",
  "data": {
    "id": 1,
    "productId": "B0XXXXXX",
    "title": "商品标题",
    "brand": "品牌名",
    "platform": "amazon",
    "currentPrice": 29.99,
    "currency": "USD",
    "rating": 4.5,
    "reviewsCount": 1250,
    "availability": "in_stock",
    "categories": "[\"Electronics\", \"Computers\"]",
    "createdAt": "2025-10-29T10:00:00"
  }
}
```

#### 2. 根据商品ID查询基础信息
```
GET /api/products/{productId}/basic
```
**参数：**
- `productId` (路径参数): 商品唯一ID

**响应：** 返回商品基础信息对象

### 分类查询接口

#### 3. 根据平台查询商品
```
GET /api/products/platform/{platform}
```
**参数：**
- `platform` (路径参数): 平台名称 (amazon/ebay/walmart)

#### 4. 根据品牌查询商品
```
GET /api/products/brand/{brand}
```
**参数：**
- `brand` (路径参数): 品牌名称

#### 5. 根据卖家ID查询商品
```
GET /api/products/seller/{sellerId}
```
**参数：**
- `sellerId` (路径参数): 卖家ID

#### 6. 根据根分类查询商品
```
GET /api/products/category/{rootCategory}
```
**参数：**
- `rootCategory` (路径参数): 根分类名称

#### 7. 根据商品状态查询
```
GET /api/products/status/{status}
```
**参数：**
- `status` (路径参数): 商品状态 (active/inactive/out_of_stock)

### 高级查询接口

#### 8. 分页查询商品
```
GET /api/products?page=1&size=10
```
**参数：**
- `page` (查询参数): 页码，默认1
- `size` (查询参数): 每页大小，默认10

#### 9. 价格区间查询
```
GET /api/products/price-range?minPrice=10&maxPrice=100
```
**参数：**
- `minPrice` (查询参数): 最低价格
- `maxPrice` (查询参数): 最高价格

#### 10. 评分区间查询
```
GET /api/products/rating-range?minRating=4.0&maxRating=5.0
```
**参数：**
- `minRating` (查询参数): 最低评分
- `maxRating` (查询参数): 最高评分

#### 11. 热销商品查询
```
GET /api/products/top-selling?limit=20
```
**参数：**
- `limit` (查询参数): 限制数量，默认20

### 统计接口

#### 12. 商品总数统计
```
GET /api/products/count
```
**响应示例：**
```json
{
  "code": 200,
  "msg": "统计成功",
  "data": 15420
}
```

## 响应格式说明

所有接口都使用统一的响应格式 `R<T>`：

```json
{
  "code": 200,          // 状态码：200成功，500失败，404未找到，400参数错误
  "msg": "操作成功",     // 响应消息
  "data": {}            // 响应数据，可能是对象或数组
}
```

### 状态码说明
- `200`: 操作成功
- `400`: 请求参数错误
- `404`: 资源未找到
- `500`: 服务器内部错误

## 实体类说明

### ProductFullInfo - 完整商品信息
包含所有相关表的字段，用于完整展示商品信息：
- 基础信息：ID、标题、品牌、平台等
- 价格信息：当前价格、原价、货币、折扣等
- 评价排名：评分、评价数量、排名等
- 库存物流：库存状态、发货地、配送选项等
- 分类标签：分类路径、根分类、产品类型等

### ProductBasicInfo - 基础商品信息
只包含商品的基础信息字段

## 使用示例

### 1. 查询特定商品详情
```bash
curl -X GET "http://localhost:8080/api/products/B08N5WRWNW"
```

### 2. 查询Amazon平台的所有商品
```bash
curl -X GET "http://localhost:8080/api/products/platform/amazon"
```

### 3. 分页查询商品（第2页，每页20条）
```bash
curl -X GET "http://localhost:8080/api/products?page=2&size=20"
```

### 4. 查询价格在50-200美元之间的商品
```bash
curl -X GET "http://localhost:8080/api/products/price-range?minPrice=50&maxPrice=200"
```

### 5. 查询评分4星以上的商品
```bash
curl -X GET "http://localhost:8080/api/products/rating-range?minRating=4.0&maxRating=5.0"
```

## 技术栈
- **Framework**: Spring Boot
- **ORM**: MyBatis
- **Database**: MySQL
- **Build Tool**: Maven
- **Java Version**: 11+

## 注意事项

1. 所有查询接口都支持跨域访问
2. 价格查询时，minPrice和maxPrice必须大于等于0，且minPrice不能大于maxPrice
3. 评分查询时，评分范围必须在0-5之间
4. 分页查询时，page和size必须大于0
5. 所有异常都会被捕获并返回友好的错误信息

## 错误处理

当请求出现错误时，接口会返回相应的错误信息：

```json
{
  "code": 400,
  "msg": "价格区间参数不正确",
  "data": null
}
```

常见错误类型：
- 参数验证错误
- 数据库连接错误
- 数据不存在错误
- 服务器内部错误
