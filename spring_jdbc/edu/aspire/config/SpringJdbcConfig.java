package edu.aspire.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.aspire.daos.EmployeeDao;
import edu.aspire.daos.EmployeeDaoImpl;

@Configuration
public class SpringJdbcConfig {
	@Bean
	public DataSource dataSource() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		bds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		bds.setUsername("system");
		bds.setPassword("manager");
		bds.setInitialSize(10);
		bds.setMaxActive(15);
		return bds;
	}

	@Bean(autowire=Autowire.BY_TYPE)
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate();
	}
	
	@Bean(name = "empdao" )
	public EmployeeDao empDao(){
		return new EmployeeDaoImpl();
	}
}
