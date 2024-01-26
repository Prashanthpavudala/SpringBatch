package com.learning.springbatchprocess.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableTransactionManagement
public class InMemoryDataSource {

    @Primary
    @Bean(name = "monitoringSource")
    public DataSource readDataSource() {
        log.info("Getting In Memory DataSource");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:mem:PROPERTY;MODE=MySQL;INIT=CREATE SCHEMA IF NOT EXISTS PROPERTY");
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}
