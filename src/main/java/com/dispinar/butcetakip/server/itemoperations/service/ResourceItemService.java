package com.dispinar.butcetakip.server.itemoperations.service;

import java.util.List;

import com.dispinar.butcetakip.server.itemoperations.entity.ResourceItem;

public interface ResourceItemService {

	public void saveResourceItem(ResourceItem resourceItem, String username);
	
	public List<ResourceItem> getAllResourceItems(String username);
	
	public ResourceItem getResourceItem(Long id);
	
	public ResourceItem updateResourceItem(ResourceItem resourceItem);
	
	public void deleteResourceItem(Long id);
}
