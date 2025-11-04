package com.example.amorproduct;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.amorproduct.mapper")
public class AmorProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmorProductApplication.class, args);
    }

}
