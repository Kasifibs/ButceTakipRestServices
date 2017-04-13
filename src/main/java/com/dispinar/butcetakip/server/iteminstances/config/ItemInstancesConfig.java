package com.dispinar.butcetakip.server.iteminstances.config;

import com.dispinar.butcetakip.server.common.service.UserService;
import com.dispinar.butcetakip.server.iteminstances.dao.IncomeDao;
import com.dispinar.butcetakip.server.iteminstances.dao.IncomeDaoImpl;
import com.dispinar.butcetakip.server.iteminstances.dao.ResourceDao;
import com.dispinar.butcetakip.server.iteminstances.dao.ResourceDaoJpaImpl;
import com.dispinar.butcetakip.server.iteminstances.query.IncomeQueryWithParamsPreparator;
import com.dispinar.butcetakip.server.iteminstances.query.ResourceQueryWithParamsPreparator;
import com.dispinar.butcetakip.server.iteminstances.service.IncomeService;
import com.dispinar.butcetakip.server.iteminstances.service.IncomeServiceImpl;
import com.dispinar.butcetakip.server.iteminstances.service.ResourceService;
import com.dispinar.butcetakip.server.iteminstances.service.ResourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.dispinar.butcetakip.server"})
@EnableTransactionManagement
public class ItemInstancesConfig {
	
	@Autowired UserService userService;

	@Bean
	public ResourceService resourceService(){
		ResourceServiceImpl resourceService = new ResourceServiceImpl();
		resourceService.setResourceDao(resourceDao());
		resourceService.setUserService(userService);

		return resourceService;
	}

	@Bean
	public ResourceDao resourceDao(){
		ResourceDaoJpaImpl resourceDao = new ResourceDaoJpaImpl();
		resourceDao.setQueryWithParamsPreparator(resourceQueryWithParamsPreparator());
		return resourceDao;
	}

	@Bean
	public ResourceQueryWithParamsPreparator resourceQueryWithParamsPreparator(){
		ResourceQueryWithParamsPreparator queryWithParamsPreparator = new ResourceQueryWithParamsPreparator();
		return queryWithParamsPreparator;
	}

	@Bean
	public IncomeService incomeService(){
		IncomeServiceImpl incomeService = new IncomeServiceImpl();
		incomeService.setIncomeDao(incomeDao());
		incomeService.setUserService(userService);

		return incomeService;
	}

	@Bean
	public IncomeDao incomeDao(){
		IncomeDaoImpl incomeDao = new IncomeDaoImpl();
		incomeDao.setQueryWithParamsPreparator(incomeQueryWithParamsPreparator());

		return incomeDao;
	}

	@Bean
	public IncomeQueryWithParamsPreparator incomeQueryWithParamsPreparator(){
		IncomeQueryWithParamsPreparator queryWithParamsPreparator = new IncomeQueryWithParamsPreparator();
		return queryWithParamsPreparator;
	}

}
