package com.dispinar.butcetakip.server.iteminstances.dao;

import com.dispinar.butcetakip.server.iteminstances.entity.Resource;
import com.dispinar.butcetakip.server.iteminstances.query.ResourceQueryParamsWrapper;

import java.util.List;

public interface ResourceDao {
	
	public void save(Resource resource);

	public Resource findById(Long id);
	
	public List<Resource> findAll(Long userId);
	
	public List<Resource> queryResources(Long userId, ResourceQueryParamsWrapper queryParams);
	
	public Resource update(Resource resource);
	
	public void delete(Resource resource);

}
