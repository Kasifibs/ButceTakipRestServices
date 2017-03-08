package com.dispinar.butcetakip.server.iteminstances.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dispinar.butcetakip.server.common.service.UserService;
import com.dispinar.butcetakip.server.iteminstances.dao.ResourceDao;
import com.dispinar.butcetakip.server.iteminstances.dao.ResourceDaoJpaImpl;
import com.dispinar.butcetakip.server.iteminstances.service.ResourceService;
import com.dispinar.butcetakip.server.iteminstances.service.ResourceServiceImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.dispinar.butcetakip.server"})
@EnableTransactionManagement
public class ItemInstancesConfig {
	
	@Autowired UserService userService;
	
	@Bean
	public ResourceDao resourceDao(){
		ResourceDaoJpaImpl resourceDao = new ResourceDaoJpaImpl();
		return resourceDao;
	}
	
	@Bean
	public ResourceService resourceService(){
		ResourceServiceImpl resourceService = new ResourceServiceImpl();
		resourceService.setResourceDao(resourceDao());
		resourceService.setUserService(userService);
		
		return resourceService;
	}

}
