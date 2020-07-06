package edu.aspire.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

@Configuration
@ComponentScan(basePackages = { "edu.aspire.daos" })
@PropertySource(value={"classpath:connection.properties"})
public class SpringHibernateConfig {
	@Bean
	public PropertySourcesPlaceholderConfigurer placeHolderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public DataSource dataSource(@Value("${jdbc.driverClass}") String driverClass, 
								 @Value("${jdbc.url}") String url, 
								 @Value("${jdbc.username}") String username,
								 @Value("${jdbc.password}") String password,
								 @Value("${jdbc.initPoolSize}") int initPoolSize,
								 @Value("${jdbc.maxPoolSize}") int maxPoolSize) {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(driverClass);
		bds.setUrl(url);
		bds.setUsername(username);
		bds.setPassword(password);
		bds.setInitialSize(initPoolSize);
		bds.setMaxActive(maxPoolSize);
		return bds;
	}
	
	/*@Autowired
	Environment env;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(env.getProperty("jdbc.driverClass"));
		bds.setUrl(env.getProperty("jdbc.url"));
		bds.setUsername(env.getProperty("jdbc.username"));
		bds.setPassword(env.getProperty("jdbc.password"));
		//bds.setInitialSize(Integer.parseInt(env.getProperty("jdbc.initPoolSize")));
		bds.setInitialSize(env.getProperty("jdbc.initPoolSize", Integer.class));
		bds.setMaxActive(env.getProperty("jdbc.maxPoolSize", Integer.class));
		return bds;
	}*/

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource ds) {
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(ds);

		Properties props = new Properties();
		props.put("hibernate.show_sql", "true");
		props.put("hibernate.format_sql", "true");
		props.put("hibernate.use_sql_comments", "true");
		props.put("hibernate.hbm2ddl.auto", "create");
		props.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");

		lsfb.setHibernateProperties(props);
		lsfb.setMappingResources(new String[] { "Customer.hbm.xml" });

		return lsfb;
	}

	@Bean(autowire=Autowire.BY_TYPE)
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate();
	}
}
