package com.dispinar.butcetakip.server.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dispinar.butcetakip.server.common.dao.PeriodDao;
import com.dispinar.butcetakip.server.common.dao.PeriodDaoImpl;
import com.dispinar.butcetakip.server.common.dao.UserDao;
import com.dispinar.butcetakip.server.common.dao.UserDaoJpaImpl;
import com.dispinar.butcetakip.server.common.query.PeriodQueryWithParamsPreparator;
import com.dispinar.butcetakip.server.common.service.PeriodService;
import com.dispinar.butcetakip.server.common.service.PeriodServiceImpl;
import com.dispinar.butcetakip.server.common.service.UserService;
import com.dispinar.butcetakip.server.common.service.UserServiceImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.dispinar.butcetakip.server"})
@EnableTransactionManagement
public class CommonConfig {

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
	
	@Bean
	public PeriodDao periodDao(){
		PeriodDaoImpl periodDao = new PeriodDaoImpl();
		periodDao.setQueryWithParamsPreparator(queryWithParamsPreparator());
		return periodDao;
	}
	
	@Bean
	public PeriodService periodService(){
		PeriodServiceImpl periodService = new PeriodServiceImpl();
		periodService.setPeriodDao(periodDao());
		periodService.setUserService(userService());
		
		return periodService;
	}
	
	@Bean
	public PeriodQueryWithParamsPreparator queryWithParamsPreparator(){
		PeriodQueryWithParamsPreparator queryWithParamsPreparator = new PeriodQueryWithParamsPreparator();
		return queryWithParamsPreparator;
	}
}
