package com.dispinar.butcetakip.server.service;

import java.util.List;

import javax.transaction.Transactional;

import com.dispinar.butcetakip.server.dao.ResourceItemDao;
import com.dispinar.butcetakip.server.entity.ResourceItem;

@Transactional
public class ResourceItemServiceImpl implements ResourceItemService{
	
	private ResourceItemDao resourceItemDao;

	public ResourceItemDao getResourceItemDao() {
		return resourceItemDao;
	}

	public void setResourceItemDao(ResourceItemDao resourceItemDao) {
		this.resourceItemDao = resourceItemDao;
	}
	
	public void saveResourceItem(ResourceItem resourceItem) {
		getResourceItemDao().save(resourceItem);
	}

	public List<ResourceItem> getAllResourceItems() {
		return resourceItemDao.findAll();
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

}
