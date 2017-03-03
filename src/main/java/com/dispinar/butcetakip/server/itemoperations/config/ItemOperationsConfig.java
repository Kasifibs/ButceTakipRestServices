package com.dispinar.butcetakip.server.itemoperations.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dispinar.butcetakip.server.common.service.UserService;
import com.dispinar.butcetakip.server.itemoperations.dao.ExpenseItemDao;
import com.dispinar.butcetakip.server.itemoperations.dao.ExpenseItemDaoJpaImpl;
import com.dispinar.butcetakip.server.itemoperations.dao.IncomeItemDao;
import com.dispinar.butcetakip.server.itemoperations.dao.IncomeItemDaoJpaImpl;
import com.dispinar.butcetakip.server.itemoperations.dao.ResourceItemDao;
import com.dispinar.butcetakip.server.itemoperations.dao.ResourceItemDaoJpaImpl;
import com.dispinar.butcetakip.server.itemoperations.query.ExpenseItemQueryWithParamsPreparator;
import com.dispinar.butcetakip.server.itemoperations.query.IncomeItemQueryWithParamsPreparator;
import com.dispinar.butcetakip.server.itemoperations.query.ResourceItemQueryWithParamsPreparator;
import com.dispinar.butcetakip.server.itemoperations.service.ExpenseItemService;
import com.dispinar.butcetakip.server.itemoperations.service.ExpenseItemServiceImpl;
import com.dispinar.butcetakip.server.itemoperations.service.IncomeItemService;
import com.dispinar.butcetakip.server.itemoperations.service.IncomeItemServiceImpl;
import com.dispinar.butcetakip.server.itemoperations.service.ResourceItemService;
import com.dispinar.butcetakip.server.itemoperations.service.ResourceItemServiceImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.dispinar.butcetakip.server"})
@EnableTransactionManagement
public class ItemOperationsConfig {
	
	@Autowired UserService userService;

	@Bean
	public ResourceItemDao resourceItemDao(){
		ResourceItemDaoJpaImpl resourceItemDao = new ResourceItemDaoJpaImpl();
		resourceItemDao.setQueryWithParamsPreparator(resItemQueryWithParamsPreparator());
		return resourceItemDao;
	}
	
	@Bean
	public ResourceItemService resourceItemService(){
		ResourceItemServiceImpl resourceItemService = new ResourceItemServiceImpl();
		resourceItemService.setResourceItemDao(resourceItemDao());
		resourceItemService.setUserService(userService);
		
		return resourceItemService;
	}
	
	@Bean
	public ResourceItemQueryWithParamsPreparator resItemQueryWithParamsPreparator(){
		ResourceItemQueryWithParamsPreparator queryWithParamsPreparator = new ResourceItemQueryWithParamsPreparator();
		return queryWithParamsPreparator;
	}
	
	@Bean
	public IncomeItemDao incomeItemDao(){
		IncomeItemDaoJpaImpl incomeItemDao = new IncomeItemDaoJpaImpl();
		incomeItemDao.setQueryWithParamsPreparator(incItemQueryWithParamsPreparator());
		return incomeItemDao;
	}
	
	@Bean
	public IncomeItemService incomeItemService(){
		IncomeItemServiceImpl incomeItemService = new IncomeItemServiceImpl();
		incomeItemService.setIncomeItemDao(incomeItemDao());
		incomeItemService.setUserService(userService);
		
		return incomeItemService;
	}
	
	@Bean
	public IncomeItemQueryWithParamsPreparator incItemQueryWithParamsPreparator(){
		IncomeItemQueryWithParamsPreparator queryWithParamsPreparator = new IncomeItemQueryWithParamsPreparator();
		return queryWithParamsPreparator;
	}
	
	@Bean
	public ExpenseItemDao expenseItemDao(){
		ExpenseItemDaoJpaImpl expenseItemDao = new ExpenseItemDaoJpaImpl();
		expenseItemDao.setQueryWithParamsPreparator(expenItemQueryWithParamsPreparator());
		return expenseItemDao;
	}
	
	@Bean
	public ExpenseItemService expenseItemService(){
		ExpenseItemServiceImpl expenseItemService = new ExpenseItemServiceImpl();
		expenseItemService.setExpenseItemDao(expenseItemDao());
		expenseItemService.setUserService(userService);
		
		return expenseItemService;
	}
	
	@Bean
	public ExpenseItemQueryWithParamsPreparator expenItemQueryWithParamsPreparator(){
		ExpenseItemQueryWithParamsPreparator queryWithParamsPreparator = new ExpenseItemQueryWithParamsPreparator();
		return queryWithParamsPreparator;
	}
}
