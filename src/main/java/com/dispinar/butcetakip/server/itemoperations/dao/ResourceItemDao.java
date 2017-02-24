package com.dispinar.butcetakip.server.itemoperations.dao;

import java.util.List;

import com.dispinar.butcetakip.server.itemoperations.entity.ResourceItem;
import com.dispinar.butcetakip.server.itemoperations.query.ResourceItemQueryParamsWrapper;

public interface ResourceItemDao {
	
	public void save(ResourceItem resourceItem);

	public ResourceItem findById(Long id);
	
	public ResourceItem findByName(String name);
	
	public List<ResourceItem> findAll(Long userId);
	
	public List<ResourceItem> queryResourceItems(Long userId, ResourceItemQueryParamsWrapper queryParams);
	
	public ResourceItem update(ResourceItem resourceItem);
	
	public void delete(ResourceItem resourceItem);
}
