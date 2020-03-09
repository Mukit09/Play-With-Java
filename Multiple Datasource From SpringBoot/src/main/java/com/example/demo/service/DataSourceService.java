package com.example.demo.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CountDto;

@Service
public class DataSourceService {

	/*
	 * @Autowired public DataSourceService(final DataSource primaryDataSource,
	 * 
	 * @Qualifier("secondaryDataSource") final DataSource secondaryDataSource) {
	 * this.primaryDataSource = primaryDataSource; this.secondaryDataSource =
	 * secondaryDataSource; }
	 */
    
    public JdbcTemplate getJdbcTemplate(DataSource datasource) {
		return new JdbcTemplate(datasource);
	}

	public void runQueryWithTemplate(String query, JdbcTemplate template) {
		List<CountDto> serverNutshell = template.query(query, new RowMapper<CountDto>() {

			@Override
			public CountDto mapRow(ResultSet rs, int rowNum) throws SQLException {
				int count = rs.getInt("cnt");
				System.out.println("Count found: " + count);
				return new CountDto(count);
			}
		});
		System.out.println("size found: " + serverNutshell.size());
	}
    
    public void runQueryInPrimaryDB(JdbcTemplate template) {
    	
    	String query = "SELECT COUNT(*) as cnt FROM vbBill;";
		runQueryWithTemplate(query, template);
    }

	public void runQueryInSecondaryDB(JdbcTemplate template) {

		String query = "SELECT COUNT(*) as cnt FROM vbfailedcdr_2018_05;";
		runQueryWithTemplate(query, template);
	}
}
