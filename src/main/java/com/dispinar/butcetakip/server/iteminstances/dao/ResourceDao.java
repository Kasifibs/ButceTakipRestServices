package com.dispinar.butcetakip.server.iteminstances.dao;

import com.dispinar.butcetakip.server.iteminstances.entity.Resource;
import com.dispinar.butcetakip.server.iteminstances.query.ResourceQueryParamsWrapper;

import java.util.List;

public interface ResourceDao {
	
	void save(Resource resource);

	Resource findById(Long id);
	
	List<Resource> findAll(Long userId);

	List<Resource> findAllByPeriod(Long userId, Long periodId);
	
	List<Resource> queryResources(Long userId, ResourceQueryParamsWrapper queryParams);
	
	Resource update(Resource resource);
	
	void delete(Resource resource);

}
