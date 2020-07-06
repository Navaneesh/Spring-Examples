package edu.aspire.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
@ComponentScan(basePackages = { "edu.aspire.tx.programmatic", "edu.aspire.prog.daos" })
public class SpringProgTxsConfig {
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

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource ds) {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(ds);

		Properties props = new Properties();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.format_sql", "true");
		props.put("hibernate.use_sql_comments", "true");
		props.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");

		lsfb.setHibernateProperties(props);
		lsfb.setMappingResources(new String[] { "Withdraw.hbm.xml", "Deposit.hbm.xml" });

		return lsfb;
	}

	@Bean(autowire = Autowire.BY_TYPE)
	public HibernateTemplate hibTemplate() {
		return new HibernateTemplate();
	}

	@Bean(autowire = Autowire.BY_TYPE)
	public HibernateTransactionManager transactionManager() {
		return new HibernateTransactionManager();
	}

	@Bean(autowire = Autowire.BY_TYPE)
	public TransactionTemplate txTemplate() {
		return new TransactionTemplate();
	}
}
