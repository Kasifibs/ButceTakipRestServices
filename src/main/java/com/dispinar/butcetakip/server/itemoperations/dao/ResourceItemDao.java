package com.dispinar.butcetakip.server.itemoperations.dao;

import java.util.List;

import com.dispinar.butcetakip.server.itemoperations.entity.ResourceItem;
import com.dispinar.butcetakip.server.itemoperations.query.ResourceItemQueryParamsWrapper;

public interface ResourceItemDao {
	
	void save(ResourceItem resourceItem);

	ResourceItem findById(Long id);
	
	ResourceItem findByName(String name);
	
	List<ResourceItem> findAll(Long userId);
	
	List<ResourceItem> queryResourceItems(Long userId, ResourceItemQueryParamsWrapper queryParams);

    Long queryCountOfResourceItems(Long userId, ResourceItemQueryParamsWrapper queryParams);
	
	ResourceItem update(ResourceItem resourceItem);
	
	void delete(ResourceItem resourceItem);
}
