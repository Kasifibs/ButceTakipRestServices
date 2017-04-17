package com.dispinar.butcetakip.server.iteminstances.config;

import com.dispinar.butcetakip.server.common.service.UserService;
import com.dispinar.butcetakip.server.iteminstances.dao.*;
import com.dispinar.butcetakip.server.iteminstances.query.ExpenseQueryWithParamsPreparator;
import com.dispinar.butcetakip.server.iteminstances.query.IncomeQueryWithParamsPreparator;
import com.dispinar.butcetakip.server.iteminstances.query.ResourceQueryWithParamsPreparator;
import com.dispinar.butcetakip.server.iteminstances.service.*;
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

	@Bean
	public ExpenseQueryWithParamsPreparator expenseQueryWithParamsPreparator(){
		ExpenseQueryWithParamsPreparator queryWithParamsPreparator = new ExpenseQueryWithParamsPreparator();
		return queryWithParamsPreparator;
	}

	@Bean
	public ExpenseDao expenseDao(){
		ExpenseDaoImpl expenseDao = new ExpenseDaoImpl();
		expenseDao.setQueryWithParamsPreparator(expenseQueryWithParamsPreparator());

		return expenseDao;
	}

	@Bean
	public ExpenseService expenseService(){
		ExpenseServiceImpl expenseService = new ExpenseServiceImpl();
		expenseService.setExpenseDao(expenseDao());
		expenseService.setUserService(userService);

		return expenseService;
	}

}