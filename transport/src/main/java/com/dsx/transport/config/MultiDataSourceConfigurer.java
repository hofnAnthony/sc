package com.dsx.transport.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author dsx
 * 多数据源配置文件
 */
@Configuration
public class MultiDataSourceConfigurer {

    /**
     * first
     *
     * @return DruidDataSource
     */
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.first")
    public DataSource dataSourceFirst() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * second
     *
     * @return DruidDataSource
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.second")
    public DataSource dataSourceSecond() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean
    public JdbcTemplate jdbcTemplateHpi(@Qualifier("dataSourceFirst") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate jdbcTemplateK24(@Qualifier("dataSourceSecond") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}
