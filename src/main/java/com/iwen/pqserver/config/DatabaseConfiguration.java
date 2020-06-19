package com.iwen.pqserver.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@MapperScan({"com.iwen.pqserver.dao"})
public class DatabaseConfiguration {

}
