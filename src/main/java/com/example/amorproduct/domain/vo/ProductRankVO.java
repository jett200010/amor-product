package com.example.amorproduct.domain.vo;

import lombok.Data;
import java.math.BigDecimal;

/**
 * 商品评价排名展示对象
 * 用于评价和排名相关的查询接口
 */
@Data
public class ProductRankVO {

    private String productId;
    private String title;
    private String brand;
    private String platform;
    private BigDecimal rating;
    private Integer reviewsCount;
    private Integer answeredQuestions;
    private Integer bestSellersRank;
    private Integer categoryRank;
    private String categoryName;
}
