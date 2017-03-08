package com.dispinar.butcetakip.server.iteminstances.service;

import java.util.List;

import com.dispinar.butcetakip.server.iteminstances.entity.Resource;

public interface ResourceService {
	
	public void saveResource(Resource resource, String username);
	
	public List<Resource> getAllResources(String username);
	
	//public List<Resource> queryResources(String username, ResourceItemQueryParamsWrapper queryParamsWrapper);
	
	public Resource getResource(Long id);
	
	public Resource updateResource(Resource resource);
	
	public void deleteResource(Long id);

}
