package com.dispinar.butcetakip.server.iteminstances.service;

import com.dispinar.butcetakip.server.iteminstances.entity.Resource;
import com.dispinar.butcetakip.server.iteminstances.query.ResourceQueryParamsWrapper;

import java.util.List;

public interface ResourceService {
	
	public void saveResource(Resource resource, String username);
	
	public List<Resource> getAllResources(String username);
	
	public List<Resource> queryResources(String username, ResourceQueryParamsWrapper queryParamsWrapper);
	
	public Resource getResource(Long id);
	
	public Resource updateResource(Resource resource);
	
	public void deleteResource(Long id);

}
