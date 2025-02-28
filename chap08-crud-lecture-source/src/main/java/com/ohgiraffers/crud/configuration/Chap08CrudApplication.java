package com.ohgiraffers.crud.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// Spring Boot 애플리케이션을 나타내는 어노테이션
@SpringBootApplication
// 지정된 패키지(com.ohgiraffers.crud)를 스캔하여 Spring bean으로 등록.
@ComponentScan(basePackages = "com.ohgiraffers.crud")
// 지정된 패키지(com.ohgiraffers.crud)를 스캔하여 MyBatis 매퍼 인터페이스를 검색 및 등록.
// 여기서는 "Mapper.class"라는 어노테이션이 있는 클래스를 검색.
@MapperScan(basePackages = "com.ohgiraffers.crud", annotationClass = Mapper.class)
public class Chap08CrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap08CrudApplication.class, args);
    }

}
