package edu.aspire.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import edu.aspire.dao.AccountDao;
import edu.aspire.dao.AccountDaoImpl;

@Configuration
public class SpringDaoConfig {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		ds.setUsername("system");
		ds.setPassword("manager");
		return ds;
	}

	@Bean(name = "accdao", autowire=Autowire.NO)
	public AccountDao getAccDao(DataSource ds) {
		AccountDaoImpl adao = new AccountDaoImpl();
		adao.setDataSource(ds);
		return adao;
	}
}
