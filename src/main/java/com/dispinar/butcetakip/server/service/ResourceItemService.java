package com.dispinar.butcetakip.server.service;

import java.util.List;

import com.dispinar.butcetakip.server.entity.ResourceItem;

public interface ResourceItemService {

	public void saveResourceItem(ResourceItem resourceItem);
	
	public List<ResourceItem> getAllResourceItems();
	
	public ResourceItem getResourceItem(Long id);
	
	public ResourceItem updateResourceItem(ResourceItem resourceItem);
	
	public void deleteResourceItem(Long id);
}
