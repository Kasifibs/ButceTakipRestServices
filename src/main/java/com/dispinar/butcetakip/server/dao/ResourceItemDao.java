package com.dispinar.butcetakip.server.dao;

import java.util.List;

import com.dispinar.butcetakip.server.entity.ResourceItem;

public interface ResourceItemDao {
	
	public void save(ResourceItem resourceItem);

	public ResourceItem findById(Long id);
	
	public ResourceItem findByName(String name);
	
	public List<ResourceItem> findAll();
	
	public ResourceItem update(ResourceItem resourceItem);
	
	public void delete(ResourceItem resourceItem);
}
