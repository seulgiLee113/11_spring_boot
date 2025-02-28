package com.ohgiraffers.thymeleaf.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ohgiraffers.thymeleaf") // config 폴더 안이라서 직접 저렇게 설정해줌.
public class Chap07ThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap07ThymeleafApplication.class, args);
    }

}
