# Amor Product API

一个基于Spring Boot + MyBatis + MySQL的商品信息查询接口系统，支持多平台电商商品数据的统一管理和查询。

## 项目概述

本项目提供了完整的商品信息查询API，支持Amazon、eBay、Walmart等电商平台的商品数据管理。通过统一的接口，可以查询商品的基础信息、价格信息、评价排名、库存物流、分类标签等多维度数据。

## 技术栈

- **后端框架**: Spring Boot 3.5.6
- **持久化框架**: MyBatis 3.0.5
- **数据库**: MySQL 8.0+
- **Java版本**: Java 17
- **其他依赖**: Lombok, MySQL Connector

## 项目结构

```
src/main/java/com/example/amorproduct/
├── AmorProductApplication.java          # 主启动类
├── controller/
│   └── ProductController.java          # REST API控制器
├── service/
│   └── ProductService.java             # 业务逻辑层
├── mapper/
│   └── ProductMapper.java              # MyBatis数据访问层
└── domain/
    ├── ProductBasicInfo.java           # 商品基础信息实体
    ├── ProductPricing.java             # 价格信息实体
    ├── ProductReviewsRank.java         # 评价排名实体
    ├── ProductInventory.java           # 库存物流实体
    ├── ProductCategoryTags.java        # 分类标签实体
    └── ProductFullInfo.java            # 完整商品信息DTO

src/main/resources/
├── application.yml                      # 应用配置文件
└── mapper/
    └── ProductMapper.xml               # MyBatis SQL映射文件
```

## 数据库表结构

系统包含5个核心数据表：

1. **product_basic_info** - 商品基础信息表
2. **product_pricing** - 商品价格信息表
3. **product_reviews_rank** - 商品评价与排名表
4. **product_inventory** - 商品库存与物流信息表
5. **product_category_tags** - 商品分类与标签表

## 快速开始

### 1. 环境要求

- Java 17+
- MySQL 8.0+
- Maven 3.6+

### 2. 数据库配置

1. 创建数据库：
```sql
CREATE DATABASE amor_product CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. 修改 `src/main/resources/application.yml` 中的数据库连接配置：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/amor_product?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC
    username: your_username  # 修改为你的数据库用户名
    password: your_password  # 修改为你的数据库密码
```

### 3. 运行项目

```bash
# 克隆项目
git clone <repository-url>
cd amor-product

# 编译项目
mvn clean compile

# 运行项目
mvn spring-boot:run
```

应用启动后，服务将在 `http://localhost:8081` 运行。

## API 文档

### 基础查询接口

#### 1. 根据商品ID查询完整信息
```http
GET /api/products/{productId}
```

**响应示例：**
```json
{
  "id": 1,
  "productId": "PROD123",
  "title": "商品标题",
  "brand": "品牌名称",
  "platform": "amazon",
  "currentPrice": 99.99,
  "currency": "USD",
  "rating": 4.5,
  "reviewsCount": 1250,
  "availability": "in_stock",
  "rootCategory": "Electronics",
  "createdAt": "2024-01-01T10:00:00"
}
```

#### 2. 根据商品ID查询基础信息
```http
GET /api/products/{productId}/basic
```

#### 3. 统计商品总数
```http
GET /api/products/count
```

### 分类查询接口

#### 4. 根据平台查询商品
```http
GET /api/products/platform/{platform}
```
支持的平台：`amazon`、`ebay`、`walmart`

#### 5. 根据品牌查询商品
```http
GET /api/products/brand/{brand}
```

#### 6. 根据卖家ID查询商品
```http
GET /api/products/seller/{sellerId}
```

#### 7. 根据根分类查询商品
```http
GET /api/products/category/{rootCategory}
```

#### 8. 根据商品状态查询
```http
GET /api/products/status/{status}
```
支持的状态：`active`、`inactive`、`out_of_stock`

### 条件查询接口

#### 9. 分页查询商品
```http
GET /api/products?page=1&size=10
```

**参数说明：**
- `page`: 页码（从1开始，默认值：1）
- `size`: 每页大小（默认值：10）

#### 10. 价格区间查询
```http
GET /api/products/price-range?minPrice=10&maxPrice=100
```

**参数说明：**
- `minPrice`: 最低价格（必填）
- `maxPrice`: 最高价格（必填）

#### 11. 评分区间查询
```http
GET /api/products/rating-range?minRating=4.0&maxRating=5.0
```

**参数说明：**
- `minRating`: 最低评分（必填）
- `maxRating`: 最高评分（必填）

#### 12. 热销商品查询
```http
GET /api/products/top-selling?limit=20
```

**参数说明：**
- `limit`: 返回数量限制（默认值：20）

## 使用示例

### 使用curl测试接口

```bash
# 查询特定商品
curl http://localhost:8081/api/products/B0FFSRKCRD

# 分页查询商品
curl http://localhost:8081/api/products?page=1&size=10

# 查询Amazon平台商品
curl http://localhost:8081/api/products/platform/amazon

# 价格区间查询
curl "http://localhost:8081/api/products/price-range?minPrice=10&maxPrice=100"

# 查询热销商品
curl "http://localhost:8081/api/products/top-selling?limit=20"

# 统计商品总数
curl http://localhost:8081/api/products/count
```

## 错误处理

API采用标准的HTTP状态码：

- **200 OK** - 请求成功
- **404 Not Found** - 资源未找到
- **400 Bad Request** - 请求参数错误
- **500 Internal Server Error** - 服务器内部错误

## 性能优化建议

1. **数据库索引**：确保在以下字段上创建索引
   ```sql
   -- 商品基础信息表索引
   CREATE INDEX idx_product_id ON product_basic_info(product_id);
   CREATE INDEX idx_platform ON product_basic_info(platform);
   CREATE INDEX idx_brand ON product_basic_info(brand);
   CREATE INDEX idx_seller_id ON product_basic_info(seller_id);
   
   -- 价格信息表索引
   CREATE INDEX idx_product_pricing_id ON product_pricing(product_id);
   CREATE INDEX idx_current_price ON product_pricing(current_price);
   
   -- 评价排名表索引
   CREATE INDEX idx_product_reviews_id ON product_reviews_rank(product_id);
   CREATE INDEX idx_rating ON product_reviews_rank(rating);
   CREATE INDEX idx_best_sellers_rank ON product_reviews_rank(best_sellers_rank);
   ```

2. **分页查询**：对于大数据量查询，建议使用分页参数控制返回数据量

3. **缓存策略**：可考虑在Service层添加Redis缓存来提高查询性能

## 部署说明

### Docker部署

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/amor-product-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### 生产环境配置

在生产环境中，建议：

1. 使用专用的数据库服务器
2. 配置连接池参数
3. 启用SQL日志监控
4. 配置适当的JVM参数

## 贡献指南

1. Fork项目
2. 创建功能分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启Pull Request

## 许可证

本项目采用MIT许可证 - 详情请查看 [LICENSE](LICENSE) 文件

## 联系方式

如有问题或建议，请通过以下方式联系：

- 项目Issues: [GitHub Issues](https://github.com/your-username/amor-product/issues)
- 邮箱: your-email@example.com

## 更新日志

### v1.0.0 (2024-01-01)
- 初始版本发布
- 实现基础商品查询功能
- 支持多平台商品数据管理
- 提供完整的REST API接口
