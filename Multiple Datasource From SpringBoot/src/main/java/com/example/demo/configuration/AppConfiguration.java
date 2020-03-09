package com.example.demo.configuration;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
public class AppConfiguration {
	
	@Bean(name = "primaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        System.out.println("Primary Data Source Called");
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/billing");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("");
	    return dataSourceBuilder.build();
    }

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSource secondaryDataSource() {
        System.out.println("Secondary Data Source Called");
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/billingfailed");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("");
	    return DataSourceBuilder.create().build();
    }
}
