package com.lph.app.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
/*表示该类包含注解为 @Bean生产Bean管理是由Spring容器的一个或多个bean的方法。
在我们的例子中，这个类代表hibernate配置。*/
@EnableTransactionManagement
/*
 * 相当于 Spring’s tx:* XML 命名空间, 使Spring注解驱动事务管理能力。
 */
@ComponentScan({ "com.lph.app" })
/*
 * 相当于 context:component-scan base-package="..." 在xml文件中配置, 
 * 提供Spring在哪里寻找管理 beans/classes。
 */
@PropertySource(value = { "classpath:spring/jdbc.properties" })
/*
 *  用于声明一组属性(在属性中定义的应用程序类路径文件)在Spring运行时 Environment, 
 *  提供了灵活性，可以在不同的应用环境的不同值。
 */
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
	LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	sessionFactory.setDataSource(dataSource());
	sessionFactory.setPackagesToScan(new String[] { "com.lph.app" });
	sessionFactory.setHibernateProperties(hibernateProperties());
	return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
	dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
	dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
	dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
	return dataSource;
    }

    private Properties hibernateProperties() {
	Properties properties = new Properties();
	properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
	properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
	properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
	properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
	return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
	HibernateTransactionManager txManager = new HibernateTransactionManager();
	txManager.setSessionFactory(s);
	return txManager;
    }
}