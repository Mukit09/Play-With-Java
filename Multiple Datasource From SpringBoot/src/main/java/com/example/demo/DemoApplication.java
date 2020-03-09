package com.example.demo;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.service.DataSourceService;

@SpringBootApplication
public class DemoApplication {

	@Autowired
	public DataSource primaryDataSource;
	@Autowired
	@Qualifier("secondaryDataSource")
	public DataSource secondaryDataSource;

	@Autowired
	ApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}


	@Bean
	@PostConstruct
	void doTest() {
		DataSourceService service = context.getBean(DataSourceService.class);


		JdbcTemplate templateForPrimary = service.getJdbcTemplate(primaryDataSource);
		service.runQueryInPrimaryDB(templateForPrimary);

		JdbcTemplate templateForSecondary = service.getJdbcTemplate(secondaryDataSource);
		service.runQueryInSecondaryDB(templateForSecondary);
	}
}
