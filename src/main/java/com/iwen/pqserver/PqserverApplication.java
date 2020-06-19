package com.iwen.pqserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PqserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(PqserverApplication.class, args);
    }

}
