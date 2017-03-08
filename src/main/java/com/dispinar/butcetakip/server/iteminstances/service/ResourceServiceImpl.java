package com.dispinar.butcetakip.server.iteminstances.service;

import java.util.List;

import javax.transaction.Transactional;

import com.dispinar.butcetakip.server.common.entity.User;
import com.dispinar.butcetakip.server.common.service.UserService;
import com.dispinar.butcetakip.server.iteminstances.dao.ResourceDao;
import com.dispinar.butcetakip.server.iteminstances.entity.Resource;

@Transactional
public class ResourceServiceImpl implements ResourceService{
	
	private ResourceDao resourceDao;
	private UserService userService;

	public void saveResource(Resource resource, String username) {
		User user = userService.getUserByUsername(username);
		resource.setUser(user);
		
		resourceDao.save(resource);
		
	}

	public List<Resource> getAllResources(String username) {
		User user = userService.getUserByUsername(username);
		
		return resourceDao.findAll(user.getId());
	}

	public Resource getResource(Long id) {
		return resourceDao.findById(id);
	}

	public Resource updateResource(Resource resource) {
		return resourceDao.update(resource);
	}

	public void deleteResource(Long id) {
		Resource resource = resourceDao.findById(id);
		resourceDao.delete(resource);
	}

	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}