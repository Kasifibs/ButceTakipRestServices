package com.dispinar.butcetakip.server.iteminstances.dao;

import java.util.List;

import com.dispinar.butcetakip.server.iteminstances.entity.Resource;

public interface ResourceDao {
	
	public void save(Resource resource);

	public Resource findById(Long id);
	
	public List<Resource> findAll(Long userId);
	
	//public List<Resource> queryResources(Long userId, ResourceItemQueryParamsWrapper queryParams);
	
	public Resource update(Resource resource);
	
	public void delete(Resource resource);

}
