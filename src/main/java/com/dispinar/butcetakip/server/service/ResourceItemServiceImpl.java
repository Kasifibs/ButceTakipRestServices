package com.dispinar.butcetakip.server.service;

import javax.transaction.Transactional;

import com.dispinar.butcetakip.server.dao.ResourceItemDao;
import com.dispinar.butcetakip.server.entity.ResourceItem;

@Transactional
public class ResourceItemServiceImpl implements ResourceItemService{
	
	private ResourceItemDao resourceItemDao;

	public void saveResourceItem(ResourceItem resourceItem) {
		getResourceItemDao().save(resourceItem);
	}

	public ResourceItemDao getResourceItemDao() {
		return resourceItemDao;
	}

	public void setResourceItemDao(ResourceItemDao resourceItemDao) {
		this.resourceItemDao = resourceItemDao;
	}

}
