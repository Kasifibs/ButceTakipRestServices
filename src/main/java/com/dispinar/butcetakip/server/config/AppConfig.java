package com.dispinar.butcetakip.server.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dispinar.butcetakip.server.dao.ResourceItemDao;
import com.dispinar.butcetakip.server.dao.ResourceItemDaoJpaImpl;
import com.dispinar.butcetakip.server.dao.UserDao;
import com.dispinar.butcetakip.server.dao.UserDaoJpaImpl;
import com.dispinar.butcetakip.server.service.ResourceItemService;
import com.dispinar.butcetakip.server.service.ResourceItemServiceImpl;
import com.dispinar.butcetakip.server.service.UserService;
import com.dispinar.butcetakip.server.service.UserServiceImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.dispinar.butcetakip.server"})
@EnableTransactionManagement
public class AppConfig {

	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/BUTCE_TAKIP");
		dataSource.setUsername("butceTakipApp");
		dataSource.setPassword("pec24hed");
		
		return dataSource;
	}
	
	private Map<String, ?> jpaProperties(){
		Map<String,String> jpaPropertiesMap = new HashMap<String,String>();
        jpaPropertiesMap.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        
        return jpaPropertiesMap;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("com.dispinar.butcetakip.server");
		factoryBean.setJpaPropertyMap(jpaProperties());
		
		return factoryBean;
	}
	
	@Bean
	@Autowired
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		
		return transactionManager;
	}
	
	@Bean
	public ResourceItemDao resourceItemDao(){
		ResourceItemDaoJpaImpl resourceItemDao = new ResourceItemDaoJpaImpl();
		return resourceItemDao;
	}
	
	@Bean
	public ResourceItemService resourceItemService(){
		ResourceItemServiceImpl resourceItemService = new ResourceItemServiceImpl();
		resourceItemService.setResourceItemDao(resourceItemDao());
		resourceItemService.setUserService(userService());
		
		return resourceItemService;
	}
	
	@Bean
	public UserDao userDao(){
		UserDaoJpaImpl userDao = new UserDaoJpaImpl();
		return userDao;
	}
	
	@Bean 
	public UserService userService(){
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserDao(userDao());
		
		return userService;
	}
	
}
