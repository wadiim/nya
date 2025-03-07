package org.nya.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;

import lombok.extern.java.Log;

@Log
@EnableWebMvc
@Configuration
@ComponentScan("org.nya")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class WebConfig {

    @Autowired
    private Environment env;

    // TODO: Add pooling
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(
            env.getProperty("jdbc.datasource.driverClassName")
        );
        dataSource.setUrl(
            env.getProperty("jdbc.datasource.url")
        );
        dataSource.setUsername(
            env.getProperty("jdbc.datasource.superuser.username")
        );
        dataSource.setPassword(
            env.getProperty("jdbc.datasource.superuser.password")
        );

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
            = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("org.nya.entities");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());
 
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager
            = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
            entityManagerFactory().getObject()
        );

        return transactionManager;
    }

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty(
            "hibernate.dialect",
            env.getProperty("hibernate.dialect")
        );
		properties.setProperty(
            "hibernate.show_sql",
            env.getProperty("hibernate.show_sql")
        );
		properties.setProperty(
            "hibernate.hbm2ddl.auto",
            env.getProperty("hibernate.hbm2ddl.auto")
        );

		return properties;
	}
}
