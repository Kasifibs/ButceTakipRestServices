package com.dispinar.butcetakip.server.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.dispinar.butcetakip.server.common.config.CommonConfig;
import com.dispinar.butcetakip.server.config.security.SecurityConfig;
import com.dispinar.butcetakip.server.iteminstances.config.ItemInstancesConfig;
import com.dispinar.butcetakip.server.itemoperations.config.ItemOperationsConfig;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {AppConfig.class, AvoidJacksonSerializationOnNonFethedLazyObjectsConfig.class, ItemOperationsConfig.class, ItemInstancesConfig.class, CommonConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
		
	}

}
