package com.deloitte.inspection.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.deloitte.inspection.constant.DBConstants;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:local-persistence-mysql.properties")
public class DBConfiguration {

	 @Value("${spring.mysql.driverClassName}")
	 private String DRIVER;

	 @Value("${spring.mysql.password}")
	 private String PASSWORD;

	 @Value("${spring.mysql.url}")
	 private String URL;

	 @Value("${spring.mysql.username}")
	 private String USERNAME;

	 @Value("${hibernate.dialect}")
	 private String DIALECT;

	 @Value("${hibernate.show_sql}")
	 private String SHOW_SQL;

	 @Value("${hibernate.hbm2ddl.auto}")
	 private String HBM2DDL_AUTO;

	 @Value("${entitymanager.packagesToScan}")
	 private String PACKAGES_TO_SCAN;
	 
	 @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(DRIVER);
	        dataSource.setUrl(URL);
	        dataSource.setUsername(USERNAME);
	        dataSource.setPassword(PASSWORD);
	        return dataSource;
	    }

	    @Bean
	    public LocalSessionFactoryBean sessionFactory() {
	        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource());
	        sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
	        Properties hibernateProperties = new Properties();
	        hibernateProperties.put(DBConstants.HIBERNATE_DIALECT, DIALECT);
	        hibernateProperties.put(DBConstants.HIBERNATE_SHOW_SQL, SHOW_SQL);
	        hibernateProperties.put(DBConstants.HIBERNATE_HBM2DDL_AUTO, HBM2DDL_AUTO);
	        sessionFactory.setHibernateProperties(hibernateProperties);

	        return sessionFactory;
	    }

	    @Bean
	    public HibernateTransactionManager transactionManager() {
	        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	        transactionManager.setSessionFactory(sessionFactory().getObject());
	        return transactionManager;
	    }
	    
}
