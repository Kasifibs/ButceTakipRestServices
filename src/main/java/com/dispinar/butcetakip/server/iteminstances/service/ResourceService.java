package com.dispinar.butcetakip.server.iteminstances.service;

import com.dispinar.butcetakip.server.common.query.QueryResultWrapper;
import com.dispinar.butcetakip.server.iteminstances.entity.Resource;
import com.dispinar.butcetakip.server.iteminstances.query.ResourceQueryParamsWrapper;

import java.util.List;

public interface ResourceService {
	
	void saveResource(Resource resource, String username);
	
	List<Resource> getAllResources(String username);

	List<Resource> getAllResourcesByPeriod(String username, Long periodId);

    QueryResultWrapper<Resource> performQueryAndPrepareResultWrapper(String username, ResourceQueryParamsWrapper queryParams);
	
	List<Resource> queryResources(String username, ResourceQueryParamsWrapper queryParamsWrapper);

    Long queryCountOfResources(String username, ResourceQueryParamsWrapper queryParams);
	
	Resource getResource(Long id);
	
	Resource updateResource(Resource resource);
	
	void deleteResource(Long id);

}
