package com.dispinar.butcetakip.server.itemoperations.service;

import java.util.List;

import javax.transaction.Transactional;

import com.dispinar.butcetakip.server.common.entity.User;
import com.dispinar.butcetakip.server.common.service.UserService;
import com.dispinar.butcetakip.server.itemoperations.dao.ResourceItemDao;
import com.dispinar.butcetakip.server.itemoperations.entity.ResourceItem;

@Transactional
public class ResourceItemServiceImpl implements ResourceItemService{
	
	private ResourceItemDao resourceItemDao;
	private UserService userService;
	
	public void saveResourceItem(ResourceItem resourceItem, String username) {
		User user = userService.getUserByUsername(username);
		resourceItem.setUser(user);
		
		getResourceItemDao().save(resourceItem);
	}

	public List<ResourceItem> getAllResourceItems(String username) {
		User user = userService.getUserByUsername(username);
		
		return resourceItemDao.findAll(user.getId());
	}
	
	public ResourceItem getResourceItem(Long id) {		
		return resourceItemDao.findById(id);
	}

	public ResourceItem updateResourceItem(ResourceItem resourceItem) {
		return resourceItemDao.update(resourceItem);
	}

	public void deleteResourceItem(Long id) {
		ResourceItem resourceItem = resourceItemDao.findById(id);
		resourceItemDao.delete(resourceItem);
	}
	
	
	//---------------------Getter&Setters------------------------------
	public ResourceItemDao getResourceItemDao() {
		return resourceItemDao;
	}

	public void setResourceItemDao(ResourceItemDao resourceItemDao) {
		this.resourceItemDao = resourceItemDao;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
